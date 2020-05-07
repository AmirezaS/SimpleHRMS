package model.service;


import model.entity.BankAccount;
import model.repository.BankAccountDA;

import java.sql.SQLException;

public class BankAccountService {

    private static BankAccountService bankAccountService = new BankAccountService();

    private BankAccountService(){

    }

    public static BankAccountService getInsatnce(){
        return bankAccountService;
    }

    public String save(BankAccount bankAccount, String id) throws Exception {
        try(BankAccountDA bankAccountDA = new BankAccountDA()){
            return bankAccountDA.insert(bankAccount,id);
        }

    }

    public String findAll() throws Exception {
        try(BankAccountDA bankAccountDA = new BankAccountDA()){
            return bankAccountDA.select();
        }
    }

    public String findAll(String name) throws Exception {
        try(BankAccountDA bankAccountDA = new BankAccountDA()){
            return bankAccountDA.select(name);
        }
    }

    public String update(BankAccount bankAccount, String id) throws Exception {
        try(BankAccountDA bankAccountDA = new BankAccountDA()){
            return bankAccountDA.update(bankAccount,id);
        }
    }

    public String remove(String id) throws Exception {
        try(BankAccountDA bankAccountDA = new BankAccountDA()){
            return bankAccountDA.delete(id);
        }
    }

    public boolean isPersist(String id) throws Exception {
        try(BankAccountDA bankAccountDA = new BankAccountDA()){
            return bankAccountDA.isPersist(id);
        }
    }
}
