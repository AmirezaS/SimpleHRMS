package model.entity;


public class BankAccount {
    private String accountNumber;
    private String bankName;

    public String getAccountNumber() {
        return accountNumber;
    }

    public BankAccount setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public BankAccount setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }
}
