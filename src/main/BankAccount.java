package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    public List<String> transactionHistory = new ArrayList<>();

    public BankAccount() {
        this.balance = 0;
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
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            transactionHistory.add("Withdrew " + String.valueOf(amount));
        } else {
            throw new IllegalArgumentException();
        }
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
}
