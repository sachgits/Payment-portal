package org.jaxrs.rocompany.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract class AbstractDAO<T> {

    private final Class<T> clazz;

    protected final Connection conn;

    protected final EntityManager entityManager;

    AbstractDAO(final Class<T> clazz, final Connection conn) {
        this.clazz = clazz;
        this.conn = conn;
        this.entityManager = null;
    }

    AbstractDAO(final Class<T> clazz, final EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
        this.conn = null;
    }

    protected T get(final Serializable id) throws SQLException {
        if (entityManager != null) {
            return entityManager.find(clazz, id);
        }
        return null;
    }

    protected List<T> list() throws SQLException {
        if (entityManager != null) {
            return entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
        }
        return null;
    }

    protected boolean insert(final T entity) throws SQLException {
        if (entityManager != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    protected boolean update(final T entity) throws SQLException {
        return false;
    }

    protected boolean delete(final T entity) throws SQLException {
        return false;
    }
}
