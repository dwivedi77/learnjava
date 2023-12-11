package abhi.learn.java.blackrock;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Abhishek on 3/11/2019.
 */
public class Solution {


    private static final String TEXT = "I am a {0} account with {1,number,#} units of {2} currency";

    public static void main(String args[]) throws Exception {

        List<BankAccount> accounts = new ArrayList<BankAccount>();
        accounts.add(new SavingsAccount("USD", 3));//Savings
        accounts.add(new SavingsAccount("EUR", 2));//Savings
        accounts.add(new CheckingAccount("HUF", 100));//Checking
        accounts.add(new CheckingAccount("COP", 10000));//Checking
        accounts.add(new BrokerageAccount("GBP", 2));//Brokerage
        accounts.add(new BrokerageAccount("INR", 600));//Brokerage

        accounts.stream().forEach(
                account -> System.out.println(
                        MessageFormat.format(TEXT,
                                new Object[]{
                                        account.getAccountType().getName(),//make this work
                                        account.getUnits(),//make this work
                                        account.getCurrency()//make this work
                                })));

    }

}

class SavingsAccount extends BankAccount{
    private final AccountType accountType = AccountType.Savings;
    public SavingsAccount(String currency, int units) {
        super(currency, units);
    }
    @Override
    public AccountType getAccountType() {
        return accountType;
    }
}

class CheckingAccount extends BankAccount{
    private final AccountType accountType = AccountType.Checking;
    public CheckingAccount(String currency, int units) {
        super(currency, units);
    }
    @Override
    public AccountType getAccountType() {
        return accountType;
    }
}

class BrokerageAccount extends BankAccount{
    private final AccountType accountType = AccountType.Brokerage;
    public BrokerageAccount(String currency, int units) {
        super(currency, units);
    }
    @Override
    public AccountType getAccountType() {
        return accountType;
    }
}


abstract class BankAccount {
    private String currency;
    private int units;

    public BankAccount(String currency, int units) {
        this.currency = currency;
        this.units = units;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public abstract AccountType getAccountType();
}

enum AccountType{
    Savings("Savings"),
    Checking("Checking"),
    Brokerage("Brokerage");
    private String name;
    AccountType(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

