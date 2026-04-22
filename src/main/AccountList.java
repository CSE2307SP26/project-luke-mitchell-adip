package main;

import java.util.ArrayList;
import java.util.List;

public class AccountList {
    
    public List<BankAccount> accountList = new ArrayList<>();

    public void printAccountList(){
        int accountListLength = this.accountList.size();
        if (accountListLength !=0){
            int i = 1;
            for (BankAccount account: this.accountList){
                System.out.println(i + ": " + account.getName());
                i++;
            }
        }
    }

    public double totalAccountListBalance(){
        double totalBalance = 0;
        for (int i = 0; i < accountList.size(); i++){
            double accountBalence = accountList.get(i).getBalance();
            totalBalance = totalBalance + accountBalence;
        }
        return totalBalance;
    }

    public int getLength(){
        return accountList.size();
    }

    public BankAccount getAccount(int num){
        return accountList.get(num);
    }

    public void addAccount(BankAccount newAccount){
        accountList.add(newAccount);
    }

    public void removeAccount(int num){
        accountList.remove(num);
    }

    public List<BankAccount> getAccountList() {
        return accountList;
    }

}
