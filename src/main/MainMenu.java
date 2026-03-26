package main;

import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 4;
	private static final int MAX_SELECTION = 4;

	public AccountList accountList;
    private Scanner keyboardInput;

    public MainMenu() {
        this.accountList = new AccountList();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("");
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Select an account");
        System.out.println("2. Create an account");
        System.out.println("3. Transfer funds");
        System.out.println("4. Exit the app");

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
                performAccountSelection();
                break;
            case 2:
                performAccountCreation();
                break;
            case 3:
                performTransfer();
                break;
        }
    }

    public void performAccountSelection(){
        accountList.printAccountList();
        if (accountList.getLength() != 0){
            int bankNumber = getUserSelection(accountList.getLength());
            AccountMenu selectedMenu = new AccountMenu(accountList.getAccount(bankNumber - 1));
            selectedMenu.run();
        }
    }

    public String nameNewAccount(){
        System.out.print("Please name your new account: ");
        String newName = "";
        while (newName.isEmpty()){
            newName = keyboardInput.nextLine();
        }
        return newName;
    }

    public void performTransfer(){
        if (accountList.getLength() < 2) {
            System.out.println("You need at least two accounts to transfer funds.");
            return;
        }
        System.out.println("Select the account to transfer FROM:");
        accountList.printAccountList();
        int fromIndex = getUserSelection(accountList.getLength()) - 1;
        System.out.println("Select the account to transfer TO:");
        accountList.printAccountList();
        int toIndex = getUserSelection(accountList.getLength()) - 1;
        if (fromIndex == toIndex) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }
        System.out.print("Enter amount to transfer: ");
        double amount = keyboardInput.nextDouble();
        try {
            accountList.getAccount(fromIndex).transfer(accountList.getAccount(toIndex), amount);
            System.out.println("Transfer successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed. Check the amount and account balance.");
        }
    }

    public void performAccountCreation(){
        String newName = nameNewAccount();
        BankAccount newAccount = new BankAccount();
        newAccount.setName(newName);
        accountList.addAccount(newAccount);
    }

    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }
    
}
