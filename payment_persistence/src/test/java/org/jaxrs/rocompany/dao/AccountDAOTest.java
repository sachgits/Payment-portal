package org.jaxrs.rocompany.dao;

import org.jaxrs.rocompany.database.DBManager;
import org.jaxrs.rocompany.domain.Account;
import org.jaxrs.rocompany.domain.CardType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountDAOTest {

    @BeforeClass
    public static void init() throws Exception {
        DBManager.initialize();
    }

    @AfterClass
    public static void destroy() throws Exception {
        DBManager.destroy();
    }

    @Test
    public void CtorTest() throws Exception {
        AccountDAO dao = new AccountDAO(DBManager.getConnection());
        Assert.assertNotNull(dao);
    }

    @Test
    public void testGet() throws Exception {
        AccountDAO dao = new AccountDAO(DBManager.getConnection());
        Account result = dao.get("john");
        Assert.assertNull(result);
    }

    @Test
    public void testInsert() throws Exception {
        final AccountDAO dao = new AccountDAO(DBManager.getConnection());
        final Account bob = mock("bob");

        // insert
        Assert.assertTrue(dao.insert(bob));

        // test
        Account result = dao.get("bob");
        Assert.assertNotNull(result);
        Assert.assertEquals("bob", result.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        AccountDAO dao = new AccountDAO(DBManager.getConnection());
        Account mary = mock("mary");

        // insert
        Assert.assertTrue(dao.insert(mary));

        // update
        mary.setAmount(-100l);
        Assert.assertTrue(dao.update(mary));

        // test
        Account result = dao.get("mary");
        Assert.assertNotNull(result);
        Assert.assertEquals(-100l, result.getAmount().longValue());
    }

    @Test
    public void testDelete() throws Exception {
        AccountDAO dao = new AccountDAO(DBManager.getConnection());
        Account jenny = mock("jenny");

        // insert
        Assert.assertTrue(dao.insert(jenny));

        // delete
        Assert.assertTrue(dao.delete(jenny));

        // test
        Account result = dao.get("jenny");
        Assert.assertNull(result);
    }

    private static Account mock(String name) {
        Account result = new Account();
        result.setName(name);
        result.setAmount(1600l);
        result.setCardType(CardType.VISA);
        return result;
    }
}
