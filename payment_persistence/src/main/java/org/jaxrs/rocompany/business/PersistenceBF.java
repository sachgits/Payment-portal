package org.jaxrs.rocompany.business;

import org.jaxrs.rocompany.domain.Account;

public interface PersistenceBF {

    Account getAccount(final String name) throws PersistenceException;

    boolean createAccount(final Account account) throws PersistenceException;

    boolean updateAccount(final Account account) throws PersistenceException;

    boolean deleteAccount(final Account account) throws PersistenceException;
}
