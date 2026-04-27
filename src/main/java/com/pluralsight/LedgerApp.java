package com.pluralsight;

import java.util.Scanner;

public class LedgerApp {
    public static void main(String[] args) {
        // creating variables and scanner.

        Scanner usersInput = new Scanner(System.in);
        boolean appRunning = true;

        while (appRunning) {

            // This is the home screen:
            System.out.println();
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            String inputHomeScreen = usersInput.nextLine().toUpperCase();

            // Switch case that will deliver the user's input from the main menu to the methods.
            switch (inputHomeScreen) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledger();
                    break;
                case "X":
                    exit();
                    appRunning = false;
                    break;
            }
        }
    }

    // main menu methods
    public static void addDeposit(){
        System.out.println("Add deposit selected!");
    }
    public static void makePayment(){
        System.out.println("Make payment selected!");
    }
    public static void ledger(){

        Scanner usersInputLedger = new Scanner(System.in);
        System.out.println("Ledger selected!");

        // Ledger menu
        System.out.println("A) All");
        System.out.println("D) Deposit");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        String inputLedgerScreen = usersInputLedger.nextLine().toUpperCase();

        // Switch case that will deliver the user's input from the ledger menu to the methods.

        switch (inputLedgerScreen){
            case "A":
                displayAllEntries();
                break;
            case "D":
                displayOnlyDeposits();
                break;
            case "P":
                displayOnlyPayments();
                break;
            case "R":
                reports();
                break;
            case "H":

        }

    }
    public static void exit(){
        System.out.println("Goodbye!");
    }


    // ledger menu methods
    public static void displayAllEntries(){
        System.out.println("Display All Entries selected!");
    }
    public static void displayOnlyDeposits(){
        System.out.println("Display Only Deposits selected!");

    }
    public static void displayOnlyPayments(){
        System.out.println("Display only deposits selected!");
    }
    public static void reports(){

        System.out.println("Reports selected!");

        Scanner usersInputReports = new Scanner(System.in);
        System.out.println("Reports selected!");

        // Reports menu
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back <- ");
        int inputReportsScreen = usersInputReports.nextInt();
        usersInputReports.nextLine(); // this code is to clean the enter floating in the memory.

        // Switch case that will deliver the user's input from the report menu.

        switch (inputReportsScreen){
            case 1:
                System.out.println("Month to date selected");
                break;
            case 2:
                System.out.println("Previous month selected");
                break;
            case 3:
                System.out.println("Year to date selected");
                break;
            case 4:
                System.out.println("Previous year selected!");
                break;
            case 5:
                System.out.println("Search by vendor selected!");
            case 0:
        }

    }

    //reports menu



}
