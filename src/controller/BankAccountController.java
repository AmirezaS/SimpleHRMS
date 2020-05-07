package controller;

import controller.Exception.ExceptionWrapper;
import model.entity.BankAccount;
import model.service.BankAccountService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/BankAccount")
public class BankAccountController {

    @Path("/saveAccount")
    @Produces("text/plain")
    @GET
    public String save(@QueryParam("EmpID") String empID, @QueryParam("Number") String number, @QueryParam("BankName") String bankName){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(number);
        bankAccount.setBankName(bankName);
        try {
            if (BankAccountService.getInsatnce().isPersist(number)){
                return "vojood darad";
            }
            return BankAccountService.getInsatnce().save(bankAccount,empID);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/findAccount")
    @Produces("text/plain")
    @GET
    public String findAll() {
        try {
            return BankAccountService.getInsatnce().findAll();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/findAccountName")
    @Produces("text/plain")
    @GET
    public String findAll(@QueryParam("name") String name) {
        try {
            return BankAccountService.getInsatnce().findAll();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/updateAccount")
    @Produces("text/plain")
    @GET
    public String update(@QueryParam("EmpID") String empID, @QueryParam("Number") String number, @QueryParam("BankName") String bankName){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(number);
        bankAccount.setBankName(bankName);

        try {
            return BankAccountService.getInsatnce().update(bankAccount,empID);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }


    @Path("/removeAccount")
    @Produces("text/plain")
    @GET
    public String remove(@QueryParam("EmpID") String empID){
        try {
            return BankAccountService.getInsatnce().remove(empID);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }
}
