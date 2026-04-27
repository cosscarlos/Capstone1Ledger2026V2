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

    }
    public static void makePayment(){

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
    public static void appRunning(){

    }
    public static void exit(){

    }


}
