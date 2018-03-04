package org.jaxrs.rocompany.business;

import org.jaxrs.rocompany.dao.AccountDAO2;
import org.jaxrs.rocompany.database.DBManager;
import org.jaxrs.rocompany.domain.Account;

import javax.persistence.EntityManager;

public class PersistenceBFBean implements PersistenceBF {

    public PersistenceBFBean() {
        try {
            DBManager.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccount(String name) throws PersistenceException {
        EntityManager em = null;
        try {
            em = DBManager.getEntityManager();
            AccountDAO2 dao = new AccountDAO2(em);
            return dao.get(name);
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean createAccount(Account account) throws PersistenceException {
        EntityManager em = null;
        try {
            em = DBManager.getEntityManager();
            AccountDAO2 dao = new AccountDAO2(em);
            return dao.insert(account);
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean updateAccount(Account account) throws PersistenceException {
        EntityManager em = null;
        try {
            em = DBManager.getEntityManager();
            AccountDAO2 dao = new AccountDAO2(em);
            return dao.update(account);
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean deleteAccount(Account account) throws PersistenceException {
        EntityManager em = null;
        try {
            em = DBManager.getEntityManager();
            AccountDAO2 dao = new AccountDAO2(em);
            return dao.delete(account);
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
