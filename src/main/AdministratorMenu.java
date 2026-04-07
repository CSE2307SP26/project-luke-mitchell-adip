package main;

import java.util.Scanner;

public class AdministratorMenu {
    
    private static final int EXIT_SELECTION = 2;
	private static final int MAX_SELECTION = 2;

    public AccountList accountList;
    private Scanner keyboardInput;

    public AdministratorMenu(AccountList currentAccountList) {
        this.accountList = currentAccountList;
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("");
        System.out.println("You are logged in as administrator");

        System.out.println("1. Collect fees from an account");
        System.out.println("2. Log out as administrator");
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
                performCollectFees();
                break;
        }
    }

    public void performCollectFees(){
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
            System.out.println("Collect fee successfull.");
        } catch (IllegalArgumentException e) {
            System.out.println("Collect fee failed. Check the fee amount and account balance.");
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
