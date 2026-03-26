package main;

import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 3;
	private static final int MAX_SELECTION = 3;

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
        System.out.println("3. Exit the app");

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
