package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private static final double WITHDRAWAL_FEE = 0.5;

    private double balance;
    private boolean frozen;
    private boolean overdraftEnabled;
    private double overdraftLimit;
    private boolean lowBalanceAlertEnabled;
    private double lowBalanceThreshold;
    private String accountType;
    private String accountPin;
    public List<String> transactionHistory = new ArrayList<>();
    String name;

    public BankAccount() {
        this.balance = 0;
        this.frozen = false;
        this.lowBalanceAlertEnabled = false;
        this.lowBalanceThreshold = 0.0;
        this.overdraftEnabled = false;
        this.overdraftLimit = 0.0;
        this.accountType = "Checking";
    }

    private String formatAmount(double amount) {
        if (amount == Math.floor(amount)) {
            return String.valueOf((int) amount);
        }
        return String.valueOf(amount);
    }

    public void deposit(double amount) {
        if (this.frozen) {
            throw new IllegalStateException();
        }
        if(amount > 0) {
            this.balance += amount;
            transactionHistory.add("Deposited " + formatAmount(amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount) {
        withdrawInternal(amount, true);
    }

    private void withdrawInternal(double amount, boolean applyWithdrawalFee) {
        if (this.frozen) {
            throw new IllegalStateException();
        }
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        double fee = applyWithdrawalFee ? WITHDRAWAL_FEE : 0;
        double totalDebit = amount + fee;
        if (totalDebit <= this.balance) {
            this.balance -= totalDebit;
            transactionHistory.add("Withdrew " + formatAmount(amount));
            if (applyWithdrawalFee) {
                transactionHistory.add("Withdrawal fee " + formatAmount(WITHDRAWAL_FEE));
            }
        } else if (overdraftEnabled && totalDebit <= this.balance + overdraftLimit) {
            this.balance -= totalDebit;
            transactionHistory.add("Overdraft: Withdrew " + formatAmount(amount));
            if (applyWithdrawalFee) {
                transactionHistory.add("Withdrawal fee " + formatAmount(WITHDRAWAL_FEE));
            }
        } else {
            throw new IllegalArgumentException();
        }
        if (lowBalanceAlertEnabled && this.balance < lowBalanceThreshold) {
            System.out.println("Warning: Low balance! Current balance: $" + this.balance);
        }
    }

    public void transfer(BankAccount destination, double amount) {
        if (this.frozen) {
            throw new IllegalStateException();
        }
        withdrawInternal(amount, false);
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

    public String getAccountType() {
        return this.accountType;
    }

    public void setPin(String pin){
        this.accountPin = pin;
    }

    public boolean checkPin(String inputPin){
        if (this.accountPin.compareTo(inputPin) == 0){
            return true;
        }else{
            return false;
        }
    }

    public void setAccountType(String type) {
        if (!type.equals("Savings") && !type.equals("Checking")) {
            throw new IllegalArgumentException();
        }
        this.accountType = type;
    }

    public void setName(String newName){
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = newName;
    }

    public void setOverdraftEnabled(boolean enabled) {
        this.overdraftEnabled = enabled;
    }

    public boolean isOverdraftEnabled() {
        return this.overdraftEnabled;
    }

    public void freezeAccount() {
        this.frozen = true;
        transactionHistory.add("Account frozen");
    }

    public void unfreezeAccount() {
        this.frozen = false;
        transactionHistory.add("Account unfrozen");
    }

    public boolean isFrozen() {
        return this.frozen;
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

    public void collectFees(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException();
        }
        this.balance -= amount;
        transactionHistory.add("Collected fee of " + formatAmount(amount));
    }

    public void addIntrest(Double intrestPercentage){
        if(intrestPercentage > 0 && this.balance > 0) {
            this.balance = Math.round(this.balance * ((intrestPercentage / 100) + 1) * 100) / 100.0;
            transactionHistory.add("Added " + String.valueOf(intrestPercentage) + " of intrest");
        } else {
            throw new IllegalArgumentException();
        }
    }

    
    public void setLowBalanceAlertEnabled(boolean enabled) {
        this.lowBalanceAlertEnabled = enabled;
    }

    public boolean isLowBalanceAlertEnabled() {
        return this.lowBalanceAlertEnabled;
    }

    public void setLowBalanceThreshold(double threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException();
        }
        this.lowBalanceThreshold = threshold;
    }

    public double getLowBalanceThreshold() {
        return this.lowBalanceThreshold;
    }

}
