# project26

## Description

A command-line Java banking application that allows customers to manage bank accounts, including depositing, withdrawing, checking balances, viewing transaction history, creating and closing accounts, transferring funds, and designating account types. Administrators can collect fees, add interest, and customers can enable overdraft protection and receive low balance alerts. Customers can **freeze** an account to block deposits, withdrawals, and transfers until it is unfrozen. Withdrawals from the account menu include a **$0.50 withdrawal fee** (shown before confirming and recorded in transaction history); **transfers to another account do not charge this fee.**

## Team Members:

* Mitchell Sampson
* Luke Bekemeyer
* Adip Shrestha

## User stories

1. A bank customer should be able to deposit into an existing account. (Shook)
2. A bank customer should be able to withdraw from an account.
3. A bank customer should be able to check their account balance.
4. A bank customer should be able to view their transaction history for an account.
5. A bank customer should be able to create an additional account with the bank.
6. A bank customer should be able to close an existing account.
7. A bank customer should be able to transfer money from one account to another.
8. A bank administrator should be able to collect fees from existing accounts when necessary.
9. A bank administrator should be able to add an interest payment to an existing account when necessary.
10. A bank customer should be able to enable overdraft protection on their account.
11. A bank customer should be able to receive an alert when their balance falls below $20.
12. A bank customer should be able to rename their bank account.
13. A bank customer should be able to designate their account as Savings or Checking.
14. A bank administrator should be able to collect fees from existing accounts when necessary.
15. A bank administrator should be able to add an interest payment to an existing account when necessary.
16. A bank customer should be able to enable overdraft protection on their account.
17. A bank customer should be able to receive an alert when their balance falls below $20.
18. A bank customer should be able to rename their bank account.
19. A bank customer should be able to designate their account as Savings or Checking.
20. The AccountMenu overdraft and low balance alert sub-menus should be refactored into separate classes following proper OOP design.
21. A bank administrator should be able to apply interest or fees to all accounts, all checking accounts, or all savings accounts at once.

## What user stories were completed this iteration?

1. A bank customer should have to use a pin in order to open, close, or transfer funds with their account. (Luke)
2. A bank administrator should be able to print the name, type, and balance for all accounts, as well as the total balance for all accounts combined. (Luke)
3. A bank customer should be able to freeze and unfreeze their account to block transactions until unfrozen. (Adip)
4. A bank customer should pay a $0.50 withdrawal fee on each withdrawal from the account menu (not on transfers), with notice before withdrawing. (Adip)
5. The AccountMenu overdraft and low balance alert sub-menus were refactored into separate OverdraftMenu and LowBalanceAlertMenu classes, each following the same displayOptions/getUserSelection/processInput/run pattern as the main menus. Admin login from the account menu was also fixed. (Mitchell)
6. A bank administrator can now apply interest or fees to all accounts, all checking accounts, or all savings accounts at once, in addition to individual accounts. (Mitchell)

## What user stories do you intend to complete next iteration?

This is the final iteration. All planned features have been implemented.

## Is there anything that you implemented but doesn't currently work?

All currently implemented features are working and tests pass.

## What commands are needed to compile and run your code from the command line?

To run the app:
```
bash runApp.sh
```

To compile and run tests manually:
```
javac -cp test-lib/junit-platform-console-standalone-1.13.0-M3.jar src/main/*.java src/test/*.java -d out
java -jar test-lib/junit-platform-console-standalone-1.13.0-M3.jar --class-path out --scan-class-path
```
