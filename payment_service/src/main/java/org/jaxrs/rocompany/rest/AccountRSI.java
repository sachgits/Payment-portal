package org.jaxrs.rocompany.rest;


import org.jaxrs.rocompany.business.PersistenceBF;
import org.jaxrs.rocompany.business.PersistenceBFBean;
import org.jaxrs.rocompany.domain.Account;
import org.jaxrs.rocompany.util.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account/v1")
public class AccountRSI {

    // @EJB
    private PersistenceBF bean = new PersistenceBFBean();

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("name") final String accountName) {
        Logger.info("getAccount() -> " + accountName);
        try {
            final Account account = bean.getAccount(accountName);
            if (account != null) {
                return Response.ok(account).build();
            } else {
                return Response.ok("{\"error\" : \"Account not found\"}").build();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return Response.serverError().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAccount(final Account account) {
        Logger.info("createAccount() -> " + account);
        try {
            final boolean result = bean.createAccount(account);
            if (result) {
                return Response.ok("Account created successfully").build();
            } else {
                return Response.ok("{\"error\" : \"Database error\"}").build();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return Response.serverError().build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAccount(@FormParam("name") final String accountName,
                                  @FormParam("amount") final Long amount) {
        Logger.info("updateAccount() -> " + accountName);
        try {
            Account account = new Account();
            account.setName(accountName);
            account.setAmount(amount);
            final boolean result = bean.updateAccount(account);
            if (result) {
                return Response.ok("Account update successfully").build();
            } else {
                return Response.ok("{\"error\" : \"Account not found\"}").build();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return Response.serverError().build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAccount(@FormParam("name") final String accountName) {
        Logger.info("deleteAccount() -> " + accountName);
        try {
            Account account = new Account();
            account.setName(accountName);
            final boolean result = bean.deleteAccount(account);
            if (result) {
                return Response.ok("Account removed successfully").build();
            } else {
                return Response.ok("{\"error\" : \"Account not found\"}").build();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            return Response.serverError().build();
        }
    }
}
