package org.jaxrs.rocompany.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private static final String JDBC_URL = "jdbc:sqlite:database.db";

    private static final String PERSISTENCE_UNIT = "PaymentPU";

    private static DBManager instance = null;

    private Connection conn = null;

    private EntityManagerFactory factory = null;

    private DBManager() {
    }

    public static void initialize() throws SQLException {
        synchronized (DBManager.class) {
            if (instance == null) {
                instance = new DBManager();
                loadDriver();
                loadPersistenceUnit();
                createTables();
            }
        }
    }

    public static void destroy() throws SQLException {
        synchronized (DBManager.class) {
            if (instance != null) {
                dropTables();
                closeConnection();
                closeEntityFactory();
                instance = null;
            }
        }
    }

    /**
     * SQLite handles 1 connection per database. </br>
     * Close the connection when finished
     */
    public static Connection getConnection() throws SQLException {
        synchronized (instance) {
            if (instance.conn == null) {
                instance.conn = DriverManager.getConnection(JDBC_URL);
            }
            return instance.conn;
        }
    }

    public static void closeConnection() {
        synchronized (instance) {
            if (instance.conn != null) {
                try {
                    instance.conn.close();
                    instance.conn = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Create new entity manager, to be used per business function
     */
    public static EntityManager getEntityManager() {
        return instance.factory.createEntityManager();
    }

    public static boolean createTable(final String statement) throws SQLException {
        Statement stmt = getConnection().createStatement();
        return stmt.execute(statement);
    }

    public static boolean dropTable(final String table) throws SQLException {
        Statement stmt = getConnection().createStatement();
        return stmt.execute("DROP table \'" + table + "\'");
    }

    private static void createTables() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS ACCOUNT (\n" +
                " NAME text PRIMARY KEY,\n" +
                " AMOUNT integer NOT NULL,\n" +
                " TYPE text NOT NULL\n" +
                ");";

        DBManager.createTable(sql);
    }

    private static void dropTables() throws SQLException {
        dropTable("ACCOUNT");
    }

    private static void loadDriver() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Failed to load JDBC driver", e);
        }
    }

    private static void loadPersistenceUnit() throws SQLException {
        try {
            instance.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        } catch (final Exception e) {
            throw new SQLException("Failed to load persistence unit", e);
        }
    }

    private static void closeEntityFactory() {
        if (instance.factory != null) {
            instance.factory.close();
            instance.factory = null;
        }
    }

}
