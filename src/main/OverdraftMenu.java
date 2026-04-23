package main;

import java.util.Scanner;

public class OverdraftMenu {

    private static final int EXIT_SELECTION = 3;
    private static final int MAX_SELECTION = 3;

    private BankAccount userAccount;
    private Scanner keyboardInput;

    public OverdraftMenu(BankAccount account, Scanner scanner) {
        this.userAccount = account;
        this.keyboardInput = scanner;
    }

    public void displayOptions() {
        String status = userAccount.isOverdraftEnabled()
            ? "ENABLED (limit: $" + userAccount.getOverdraftLimit() + ")"
            : "DISABLED";
        System.out.println("Overdraft protection is currently: " + status);
        System.out.println("1. Enable overdraft protection");
        System.out.println("2. Disable overdraft protection");
        System.out.println("3. Back");
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
                performEnable();
                break;
            case 2:
                performDisable();
                break;
        }
    }

    public void performEnable() {
        System.out.print("Enter overdraft limit amount: ");
        double limit = keyboardInput.nextDouble();
        if (limit < 0) {
            System.out.println("Invalid limit.");
            return;
        }
        userAccount.setOverdraftLimit(limit);
        userAccount.setOverdraftEnabled(true);
        System.out.println("Overdraft protection enabled with limit: $" + limit);
    }

    public void performDisable() {
        userAccount.setOverdraftEnabled(false);
        System.out.println("Overdraft protection disabled.");
    }

    public void run() {
        int selection = -1;
        while (selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            if (selection != EXIT_SELECTION) {
                processInput(selection);
            }
        }
    }
}
