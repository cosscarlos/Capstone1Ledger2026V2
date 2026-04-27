package com.pluralsight;

import java.util.Scanner;

public class LedgerApp {
    public static void main(String[] args) {
    // creating variables and scanner.

        Scanner usersInput = new Scanner(System.in);
        boolean appRunning = true;

        while(appRunning){

            // This is the home screen:
            System.out.println();
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            String inputHomeScreen = usersInput.nextLine().toUpperCase();
        }

    }
}
