package main;

import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 6;
	private static final int MAX_SELECTION = 6;

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
        System.out.println("3. Close an account");
        System.out.println("4. Transfer funds");
        System.out.println("5. Login as administrator");
        System.out.println("6. Exit the app");

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
                performAccountMenuCreation();
                break;
            case 2:
                performAccountCreation();
                break;
            case 3:
                performAccountClose();
                break;
            case 4:
                performTransfer();
                break;
            case 5:
                performAdministratorLogin();
                break;
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

    public String setAccountPin(){
        System.out.print("Please set a pin for your account: ");
        String pin = "";
        while (pin.isEmpty()){
            pin = keyboardInput.nextLine();
        }
        return pin;
    }

    public String attemptAccountPin(){
        System.out.print("Please enter the pin for your account: ");
        String pin = "";
        while (pin.isEmpty()){
            pin = keyboardInput.nextLine();
        }
        return pin;
    }

    public int attemptAccountSelection(){
        accountList.printAccountList();
        if (accountList.getLength() != 0){
            int bankNumber = getUserSelection(accountList.getLength());
            String attemptedPin = attemptAccountPin();
            if (accountList.getAccount(bankNumber - 1).checkPin(attemptedPin)){
                return bankNumber;
            }else{
                System.out.print("Incorrect pin, please try again. ");
                return -1;
            }
        }else{
            System.out.print("Please create an account first. ");
            return -1;
        }
    }

     public void performAccountMenuCreation(){
        int bankNumber = attemptAccountSelection();
        if (bankNumber != -1){
            AccountMenu selectedMenu = new AccountMenu(accountList.getAccount(bankNumber - 1));
            selectedMenu.run();
        }else{
            System.out.print("Failed to select account");
        }   
    }

    public void performAccountClose(){
        int bankNumber = attemptAccountSelection();
        if (bankNumber != -1){
            accountList.removeAccount(bankNumber - 1);
            System.out.println("Account closed.");
        }else{
            System.out.print("Failed to close account");
        }   
    }

    public void performTransfer(){
        if (accountList.getLength() < 2) {
            System.out.println("You need at least two accounts to transfer funds.");
            return;
        }
        System.out.println("Select the account to transfer FROM:");
        int fromIndex = attemptAccountSelection() - 1;
        if(fromIndex == -2){
            return;
        }
        System.out.println("Select the account to transfer TO:");
        int toIndex = attemptAccountSelection() - 1;
        if(toIndex == -2){
            return;
        }
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

    public String selectAccountType() {
        System.out.println("Select account type:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int selection = getUserSelection(2);
        return selection == 1 ? "Checking" : "Savings";
    }

    public void performAccountCreation(){
        String newName = nameNewAccount();
        String newPin = setAccountPin();
        String accountType = selectAccountType();
        BankAccount newAccount = new BankAccount();
        newAccount.setName(newName);
        newAccount.setPin(newPin);
        newAccount.setAccountType(accountType);
        accountList.addAccount(newAccount);
    }

    public void performAdministratorLogin(){
        AdministratorMenu administratorMenu = new AdministratorMenu(accountList);
        administratorMenu.run();
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
