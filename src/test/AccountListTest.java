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
    public void testAccountRemove() {
        BankAccount testAccount1 = new BankAccount();
        BankAccount testAccount2 = new BankAccount();
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.addAccount(testAccount2);
        testAccountList.removeAccount(0);
        List<BankAccount> expectedAccountList = new ArrayList<>();
        expectedAccountList.add(testAccount2);
        assertEquals(expectedAccountList, testAccountList.getAccountList());
    }

    @Test
    public void testAccountRemoveReducesLength() {
        BankAccount testAccount1 = new BankAccount();
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.removeAccount(0);
        assertEquals(0, testAccountList.getLength());
    }

    @Test
    public void testAccountFetch() {
        BankAccount testAccount1 = new BankAccount();
        BankAccount testAccount2 = new BankAccount();
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.addAccount(testAccount2);
        BankAccount result = testAccountList.getAccount(0);
        assertEquals(testAccount1, result);
    }

    @Test
    public void testTotalBalance() {
        BankAccount testAccount1 = new BankAccount();
        testAccount1.deposit(100);
        BankAccount testAccount2 = new BankAccount();
        testAccount2.deposit(133);
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.addAccount(testAccount2);
        assertEquals(233.0, testAccountList.totalAccountListBalance(), 0.01);
    }

    @Test
    public void testIntrestAppliedToAllAccounts() {
        BankAccount testAccount1 = new BankAccount();
        testAccount1.deposit(100);
        BankAccount testAccount2 = new BankAccount();
        testAccount2.deposit(200);
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.addAccount(testAccount2);
        for (int i = 0; i < testAccountList.getLength(); i++) {
            testAccountList.getAccount(i).addIntrest(10.0);
        }
        assertEquals(110.0, testAccount1.getBalance(), 0.01);
        assertEquals(220.0, testAccount2.getBalance(), 0.01);
    }

    @Test
    public void testIntrestAppliedByAccountType() {
        BankAccount checking = new BankAccount();
        checking.setAccountType("Checking");
        checking.deposit(100);
        BankAccount savings = new BankAccount();
        savings.setAccountType("Savings");
        savings.deposit(100);
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(checking);
        testAccountList.addAccount(savings);
        for (int i = 0; i < testAccountList.getLength(); i++) {
            if (testAccountList.getAccount(i).getAccountType().equals("Checking")) {
                testAccountList.getAccount(i).addIntrest(10.0);
            }
        }
        assertEquals(110.0, checking.getBalance(), 0.01);
        assertEquals(100.0, savings.getBalance(), 0.01);
    }

    @Test
    public void testFeesCollectedFromAllAccounts() {
        BankAccount testAccount1 = new BankAccount();
        testAccount1.deposit(100);
        BankAccount testAccount2 = new BankAccount();
        testAccount2.deposit(200);
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(testAccount1);
        testAccountList.addAccount(testAccount2);
        for (int i = 0; i < testAccountList.getLength(); i++) {
            testAccountList.getAccount(i).collectFees(10.0);
        }
        assertEquals(90.0, testAccount1.getBalance(), 0.01);
        assertEquals(190.0, testAccount2.getBalance(), 0.01);
    }

    @Test
    public void testFeesCollectedByAccountType() {
        BankAccount checking = new BankAccount();
        checking.setAccountType("Checking");
        checking.deposit(100);
        BankAccount savings = new BankAccount();
        savings.setAccountType("Savings");
        savings.deposit(100);
        AccountList testAccountList = new AccountList();
        testAccountList.addAccount(checking);
        testAccountList.addAccount(savings);
        for (int i = 0; i < testAccountList.getLength(); i++) {
            if (testAccountList.getAccount(i).getAccountType().equals("Savings")) {
                testAccountList.getAccount(i).collectFees(10.0);
            }
        }
        assertEquals(100.0, checking.getBalance(), 0.01);
        assertEquals(90.0, savings.getBalance(), 0.01);
    }
}
