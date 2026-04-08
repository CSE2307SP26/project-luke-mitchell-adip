package main;

import java.util.Scanner;

public class AdministratorMenu {
    
    private static final int EXIT_SELECTION = 3;
	private static final int MAX_SELECTION = 3;

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
        System.out.println("3. Log out as administrator");
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

     public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }
}
