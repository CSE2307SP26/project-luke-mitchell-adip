package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(40);
        assertEquals(60, testAccount.getBalance(), 0.01);

    }

    @Test
    public void testWithdrawTooMuch() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);

        try {
            testAccount.withdraw(200);
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testWithdrawExactBalance() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(100);
        assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawNegative() {
        BankAccount testAccount = new BankAccount();

        try {
            testAccount.withdraw(-10);
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testInitialBalance() {
        BankAccount testAccount = new BankAccount();
        assertEquals(0, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testBalanceAfterDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(75);
        assertEquals(75, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testBalanceAfterMultipleTransactions() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(40);
        assertEquals(60, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransactionHistoryDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        List<String> testTransactionHistory = new ArrayList<>();
        testTransactionHistory.add("Deposited 100");
        assertEquals(testTransactionHistory, testAccount.getTransactionHistory());
    }

    @Test
    public void testTransactionHistoryWithdrawl() {
        BankAccount testAccount = new BankAccount();
        testAccount.withdraw(100);
        List<String> testTransactionHistory = new ArrayList<>();
        testTransactionHistory.add("Withdrew 100");
        assertEquals(testTransactionHistory, testAccount.getTransactionHistory());
    }

    @Test
    public void testTransactionHistoryCombined() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(100);
        List<String> testTransactionHistory = new ArrayList<>();
        testTransactionHistory.add("Deposited 100");
        testTransactionHistory.add("Withdrew 100");
        assertEquals(testTransactionHistory, testAccount.getTransactionHistory());
    }

    @Test
    public void testTransfer() {
        BankAccount source = new BankAccount();
        BankAccount destination = new BankAccount();
        source.deposit(100);
        source.transfer(destination, 40);
        assertEquals(60, source.getBalance(), 0.01);
        assertEquals(40, destination.getBalance(), 0.01);
    }

    @Test
    public void testTransferInsufficientFunds() {
        BankAccount source = new BankAccount();
        BankAccount destination = new BankAccount();
        source.deposit(50);
        try {
            source.transfer(destination, 100);
            fail();
        } catch (IllegalArgumentException e) {
            // test passes
        }
    }

    @Test
    public void testTransactionHistoryNone() {
        BankAccount testAccount = new BankAccount();
        List<String> testTransactionHistory = new ArrayList<>();
        assertEquals(testTransactionHistory, testAccount.getTransactionHistory());
    }
}
