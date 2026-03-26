package main;

import java.util.ArrayList;
import java.util.List;

public class AccountList {
    
    public List<BankAccount> accountList = new ArrayList<>();

    public void printAccountList(){
        int accountListLength = this.accountList.size();
        if (accountListLength == 0){
            System.out.println("No accounts to select. Please create account.");
        }
        else{
            int i = 1;
            for (BankAccount account: this.accountList){
            System.out.println(i + ": " + account.getName());
            i++;
            }
        }
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
