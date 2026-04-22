package main;

import java.util.Scanner;

public class AdministratorMenu {
    
    private static final int EXIT_SELECTION = 4;
	private static final int MAX_SELECTION = 4;

    public AccountList accountList;
    private Scanner keyboardInput;

    public AdministratorMenu(AccountList currentAccountList) {
        this.accountList = currentAccountList;
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("");
        System.out.println("You are logged in as administrator");

        System.out.println("1. Add intrest to an account");
        System.out.println("2. Collect fees from an account");
        System.out.println("3. Print balance of all accounts and total balance");
        System.out.println("4. Log out as administrator");
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
                performAddIntrest();
                break;
            case 2:
                performCollectFees();
                break;
            case 3:
                performPrintAccountList();
                break;
        }
    }

    public void performAddIntrest(){
        if (accountList.getLength() < 1) {
            System.out.println("You need to create an account before you can add intrest.");
            return;
        }
        System.out.println("Select the account to add intrest to:");
        accountList.printAccountList();
        int fromIndex = getUserSelection(accountList.getLength()) - 1;

        System.out.print("Enter intrest percentage: ");
        double percentage = keyboardInput.nextDouble();

        try {
            accountList.getAccount(fromIndex).addIntrest(percentage);
            System.out.println("Add intrest successfull.");
        } catch (IllegalArgumentException e) {
            System.out.println("Add intrest failed. Check the intrest percentage and account balance. Both must be above 0");
        }
    }

    public void performCollectFees() {
        if (accountList.getLength() < 1) {
            System.out.println("You need to create an account before you can collect fees.");
            return;
        }
        System.out.println("Select the account to collect fees from:");
        accountList.printAccountList();
        int fromIndex = getUserSelection(accountList.getLength()) - 1;

        System.out.print("Enter fee amount: ");
        double amount = keyboardInput.nextDouble();

        try {
            accountList.getAccount(fromIndex).collectFees(amount);
            System.out.println("Fee collected successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Fee collection failed. Amount must be positive and not exceed the account balance.");
        }
    }

    public void performPrintAccountList(){
        for (int i = 0; i < accountList.getLength(); i++){
            String accountName = accountList.getAccount(i).getName();
            String accountType = accountList.getAccount(i).getAccountType();
            double accountBalence = accountList.getAccount(i).getBalance();
            System.out.println("Bank account: " + accountName + " (" + accountType + ") has a balance of " + accountBalence);
        }
        double totalBalance = accountList.totalAccountListBalance();
        System.out.println("Total balance of all accounts is " + totalBalance);
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
