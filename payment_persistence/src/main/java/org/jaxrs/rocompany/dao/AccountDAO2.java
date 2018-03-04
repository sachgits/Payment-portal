package org.jaxrs.rocompany.dao;

import org.jaxrs.rocompany.domain.Account;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.SQLException;

public class AccountDAO2 extends AbstractDAO<Account> {

    public AccountDAO2(final EntityManager entityManager) {
        super(Account.class, entityManager);
    }

    @Override
    public Account get(Serializable name) throws SQLException {
        return super.get(name);
    }

    @Override
    public boolean insert(Account entity) throws SQLException {
        return super.insert(entity);
    }

    @Override
    public boolean update(Account entity) throws SQLException {
        entityManager.getTransaction().begin();
        final Account managedEntity = entityManager.find(Account.class, entity.getName());
        if (managedEntity != null) {
            managedEntity.setAmount(managedEntity.getAmount() - entity.getAmount());
        }
        entityManager.getTransaction().commit();
        return managedEntity != null;
    }

    @Override
    public boolean delete(Account entity) throws SQLException {
        entityManager.getTransaction().begin();
        final Account managedEntity = entityManager.find(Account.class, entity.getName());
        if (managedEntity != null) {
            entityManager.remove(managedEntity);
        }
        entityManager.getTransaction().commit();
        return managedEntity != null;
    }
}
