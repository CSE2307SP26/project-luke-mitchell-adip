package main;

import java.util.Scanner;

public class AccountMenu {

    private static final int EXIT_SELECTION = 12;
    private static final int MAX_SELECTION = 12;

    private BankAccount userAccount;
    private AccountList accountList;
    private Scanner keyboardInput;

    public AccountMenu(BankAccount selectedAccount, AccountList list) {
        this.userAccount = selectedAccount;
        this.accountList = list;
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("");
        System.out.println("Welcome to the account menu for account: " + userAccount.getName() + " (" + userAccount.getAccountType() + ")");

        System.out.println("1.  Make a deposit");
        System.out.println("2.  Make a withdrawal");
        System.out.println("3.  Check balance");
        System.out.println("4.  Get transaction history");
        System.out.println("5.  Manage overdraft protection");
        System.out.println("6.  Login as administrator");
        System.out.println("7.  Manage low balance alert");
        System.out.println("8.  Rename account");
        System.out.println("9.  Freeze account");
        System.out.println("10. Unfreeze account");
        System.out.println("11. View freeze status");
        System.out.println("12. Exit the account menu");
    }

    public int getUserSelection(int max) {
        int selection = -1;
        while (selection < 1 || selection > max) {
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
                performOverdraftManagement();
                break;
            case 6:
                performAdministratorLogin();
                break;
            case 7:
                performLowBalanceAlertManagement();
                break;
            case 8:
                performRename();
                break;
            case 9:
                performFreezeAccount();
                break;
            case 10:
                performUnfreezeAccount();
                break;
            case 11:
                displayFreezeStatus();
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while (depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        try {
            userAccount.deposit(depositAmount);
        } catch (IllegalStateException e) {
            System.out.println("Account is frozen. Transaction not allowed.");
        }
    }

    public void performWithdraw() {
        System.out.println("Note: A $0.50 withdrawal fee is added on top of the amount you withdraw.");
        double withdrawAmount = -1;
        while (withdrawAmount < 0) {
            System.out.print("How much would you like to withdraw: ");
            withdrawAmount = keyboardInput.nextInt();
        }
        try {
            userAccount.withdraw(withdrawAmount);
        } catch (IllegalStateException e) {
            System.out.println("Account is frozen. Transaction not allowed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void performTransactionHistory() {
        System.out.println("Transaction History:");
        userAccount.printHistory();
    }

    public void displayBalance() {
        System.out.println("Current balance: " + userAccount.getBalance());
    }

    public void performOverdraftManagement() {
        new OverdraftMenu(userAccount, keyboardInput).run();
    }

    public void performAdministratorLogin() {
        AdministratorMenu administratorMenu = new AdministratorMenu(accountList);
        administratorMenu.run();
    }

    public void performLowBalanceAlertManagement() {
        new LowBalanceAlertMenu(userAccount, keyboardInput).run();
    }

    public void performRename() {
        keyboardInput.nextLine(); // consume leftover newline
        System.out.print("Enter new account name: ");
        String newName = keyboardInput.nextLine().trim();
        try {
            userAccount.setName(newName);
            System.out.println("Account renamed successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Account name cannot be empty.");
        }
    }

    public void performFreezeAccount() {
        if (userAccount.isFrozen()) {
            System.out.println("Account is already frozen.");
            return;
        }
        userAccount.freezeAccount();
        System.out.println("Account has been frozen.");
    }

    public void performUnfreezeAccount() {
        if (!userAccount.isFrozen()) {
            System.out.println("Account is already active.");
            return;
        }
        userAccount.unfreezeAccount();
        System.out.println("Account has been unfrozen.");
    }

    public void displayFreezeStatus() {
        String status = userAccount.isFrozen() ? "FROZEN" : "ACTIVE";
        System.out.println("Account freeze status: " + status);
    }

    public void run() {
        int selection = -1;
        while (selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }
}
