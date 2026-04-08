# project26

## Description

A command-line Java banking application that allows customers to manage bank accounts, including depositing, withdrawing, checking balances, viewing transaction history, creating and closing accounts, and transferring funds between accounts.

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
8. A bank adminstrator should be able to collect fees from existing accounts when necessary.
9. A bank adminstrator should be able to add an interest payment to an existing account when necessary.

## What user stories were completed this iteration?

8. A bank administrator should be able to collect fees from existing accounts when necessary.
9. A bank administrator should be able to add an interest payment to an existing account when necessary.

Additionally: overdraft protection was added for customer accounts.

Unit tests were also fixed this iteration: repaired broken/incomplete test methods, added missing @Test annotations, fixed transaction history format mismatch, and fixed a test that attempted a withdrawal without a prior deposit.

## What user stories do you intend to complete next iteration?

We intend to add additional features such as account statements and improved input validation.

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
