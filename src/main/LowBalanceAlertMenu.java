package main;

import java.util.Scanner;

public class LowBalanceAlertMenu {

    private static final int EXIT_SELECTION = 3;
    private static final int MAX_SELECTION = 3;

    private BankAccount userAccount;
    private Scanner keyboardInput;

    public LowBalanceAlertMenu(BankAccount account, Scanner scanner) {
        this.userAccount = account;
        this.keyboardInput = scanner;
    }

    public void displayOptions() {
        String status = userAccount.isLowBalanceAlertEnabled()
            ? "ENABLED (threshold: $" + userAccount.getLowBalanceThreshold() + ")"
            : "DISABLED";
        System.out.println("Low balance alert is currently: " + status);
        System.out.println("1. Enable low balance alert");
        System.out.println("2. Disable low balance alert");
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
        System.out.print("Enter low balance threshold: ");
        double threshold = keyboardInput.nextDouble();
        if (threshold < 0) {
            System.out.println("Invalid threshold.");
            return;
        }
        userAccount.setLowBalanceThreshold(threshold);
        userAccount.setLowBalanceAlertEnabled(true);
        System.out.println("Low balance alert enabled at: $" + threshold);
    }

    public void performDisable() {
        userAccount.setLowBalanceAlertEnabled(false);
        System.out.println("Low balance alert disabled.");
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
