package main;

import java.util.Scanner;

public class AccountMenu {

    private static final int EXIT_SELECTION = 6;
	private static final int MAX_SELECTION = 6;

	private BankAccount userAccount;
    private Scanner keyboardInput;

    public AccountMenu(BankAccount selectedAccount) {
        this.userAccount = selectedAccount;
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("");
        System.out.println("Welcome to the account menu for account: " + userAccount.getName());
        
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Check balance");
        System.out.println("4. Get transaction history");
        System.out.println("5. Manage low balance alert");
        System.out.println("6. Exit the account menu");
    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdraw();
                break;
            case 3:
                displayBalance();
                break;
            case 4:
                performTransactionHistory();
                break;
            case 5:
                performLowBalanceAlertManagement();
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount.deposit(depositAmount);
    }

    public void performWithdraw() {
        double withdrawAmount = -1;
        while (withdrawAmount < 0) {
            System.out.print("How much would you like to withdraw: ");
            withdrawAmount = keyboardInput.nextInt();
        }

        try {
            userAccount.withdraw(withdrawAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void performTransactionHistory(){
        System.out.println("Transaction History:");
        userAccount.printHistory();
    }
    
    public void displayBalance() {
    System.out.println("Current balance: " + userAccount.getBalance());
    }

    public void performLowBalanceAlertManagement() {
        String status = userAccount.isLowBalanceAlertEnabled()
            ? "ENABLED (threshold: $" + userAccount.getLowBalanceThreshold() + ")"
            : "DISABLED";
        System.out.println("Low balance alert is currently: " + status);
        System.out.println("1. Enable low balance alert");
        System.out.println("2. Disable low balance alert");
        System.out.println("3. Back");
        int choice = getUserSelection(3);
        if (choice == 1) {
            System.out.print("Enter low balance threshold: ");
            double threshold = keyboardInput.nextDouble();
            if (threshold < 0) {
                System.out.println("Invalid threshold.");
                return;
            }
            userAccount.setLowBalanceThreshold(threshold);
            userAccount.setLowBalanceAlertEnabled(true);
            System.out.println("Low balance alert enabled at: $" + threshold);
        } else if (choice == 2) {
            userAccount.setLowBalanceAlertEnabled(false);
            System.out.println("Low balance alert disabled.");
        }
    }

    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    
}
