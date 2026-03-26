package test;

import main.BankAccount;
import main.AccountList;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class AccountListTest {
    
    @Test
    public void testAccountListPrint() {
        BankAccount testAccount1 = new BankAccount();
        BankAccount testAccount2 = new BankAccount();
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.addAccount(testAccount2);
        List<BankAccount> expectedAccountList = new ArrayList<>();
        expectedAccountList.add(testAccount1);
        expectedAccountList.add(testAccount2);
        assertEquals(expectedAccountList, testAccountList.getAccountList());
    }

    @Test
    public void testAccountAdd() {
        BankAccount testAccount1 = new BankAccount();
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        List<BankAccount> expectedAccountList = new ArrayList<>();
        expectedAccountList.add(testAccount1);
        assertEquals(expectedAccountList, testAccountList.getAccountList());
    }

    @Test
    public void testAccountFetch() {
        BankAccount testAccount1 = new BankAccount();
        BankAccount testAccount2 = new BankAccount();
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.addAccount(testAccount2);
        testAccountList.getAccount(0);
        assertEquals(testAccount1, testAccountList.getAccountList());
    }
}
