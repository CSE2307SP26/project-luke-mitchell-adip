package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private boolean overdraftEnabled;
    private double overdraftLimit;
    public List<String> transactionHistory = new ArrayList<>();
    String name;

    public BankAccount() {
        this.balance = 0;
        this.overdraftEnabled = false;
        this.overdraftLimit = 0.0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            transactionHistory.add("Deposited " + String.valueOf(amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (amount <= this.balance) {
            this.balance -= amount;
            transactionHistory.add("Withdrew " + String.valueOf(amount));
        } else if (overdraftEnabled && amount <= this.balance + overdraftLimit) {
            this.balance -= amount;
            transactionHistory.add("Overdraft: Withdrew " + String.valueOf(amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void transfer(BankAccount destination, double amount) {
        this.withdraw(amount);
        destination.deposit(amount);
    }

    public void printHistory(){
        int transactionHistoryLength = this.transactionHistory.size();
        if (transactionHistoryLength == 0){
            System.out.println("No transaction history");
        }else{
            for (String transaction: this.transactionHistory){
            System.out.println(transaction);
            }
        }
    }

    public List<String> getTransactionHistory(){
        return transactionHistory;
    }
    
    public double getBalance() {
        return this.balance;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setOverdraftEnabled(boolean enabled) {
        this.overdraftEnabled = enabled;
    }

    public boolean isOverdraftEnabled() {
        return this.overdraftEnabled;
    }

    public void setOverdraftLimit(double limit) {
        if (limit < 0) {
            throw new IllegalArgumentException();
        }
        this.overdraftLimit = limit;
    }

    public double getOverdraftLimit() {
        return this.overdraftLimit;
    }

    public void addIntrest(Double intrestPercentage){
        if(intrestPercentage > 0 && this.balance > 0) {
            this.balance = Math.round(this.balance * ((intrestPercentage / 100) + 1) * 100) / 100;
            transactionHistory.add("Added " + String.valueOf(intrestPercentage) + " of intrest");
        } else {
            throw new IllegalArgumentException();
        }
    }

    
}
