package org.jaxrs.rocompany.dao;

import org.jaxrs.rocompany.domain.Account;
import org.jaxrs.rocompany.domain.CardType;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends AbstractDAO<Account> {

    public AccountDAO(final Connection conn) {
        super(Account.class, conn);
    }

    @Override
    public Account get(final Serializable name) throws SQLException {
        Account result = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT name, amount, type from ACCOUNT acc where acc.NAME= ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, (String) name);

            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new Account();
                result.setName(rs.getString(1));
                result.setAmount(rs.getLong(2));
                result.setCardType(CardType.valueOf(rs.getString(3)));
            }
            rs.close();
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return result;
    }

    @Override
    public boolean insert(Account entity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT into ACCOUNT(name, amount, type) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, entity.getName());
            stmt.setLong(2, entity.getAmount());
            stmt.setString(3, entity.getCardType().toString());
            return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    @Override
    public boolean update(Account entity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE ACCOUNT set amount=? where name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(2, entity.getName());
            stmt.setLong(1, entity.getAmount());

            return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    @Override
    public boolean delete(Account entity) throws SQLException {
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE from ACCOUNT where name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, entity.getName());

            return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
}
