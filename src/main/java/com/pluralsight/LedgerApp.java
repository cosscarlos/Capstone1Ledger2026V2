package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                    System.out.println("Goodbye!");
                    appRunning = false;
                    break;
            }
        }
    }

    // main menu methods
    public static void addDeposit(){

        //Creating the date and time in real time and formatted.
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String dateFormatted = now.format(formatter);

        // Creating Scanner
        Scanner Input = new Scanner(System.in);
        System.out.println("Add deposit selected!");
        System.out.println();

        // asking for deposit users input.
        System.out.println("Add the following deposit information:");
        System.out.println();
        System.out.println("Deposit description: ");
        String description = Input.nextLine();
        System.out.println();
        System.out.println("Provider: ");
        String provider = Input.nextLine();
        System.out.println();
        System.out.println("Deposit amount: ");
        double amount = Math.abs(Input.nextDouble());  // With Math.abs(); we are making sure the amount is always POSITIVE.
        Input.nextLine(); //cleaning buffer from Scanner after int or double input.


        // creating method that writes the users input into the transaction.csv file.
        saveTransaction(dateFormatted, description, provider, amount);

    }
    public static void makePayment(){

        //Creating the date and time in real time and formatted.
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String dateFormatted = now.format(formatter);

        // Creating Scanner
        Scanner Input = new Scanner(System.in);
        System.out.println("Make payment selected!");
        System.out.println();

        // asking for users input for deposit.
        System.out.println("Add the following payment information:");
        System.out.println();
        System.out.println("Payment description: ");
        String description = Input.nextLine();
        System.out.println();
        System.out.println("Provider: ");
        String provider = Input.nextLine();
        System.out.println();
        System.out.println("Payment amount: ");
        double amount = -Math.abs(Input.nextDouble());  // With -Math.abs(); we are making sure the amount is always NEGATIVE.
        Input.nextLine(); //cleaning buffer from Scanner after int or double input.


        // creating method that writes the users input into the transaction.csv file.
        saveTransaction(dateFormatted, description, provider, amount);
    }
    public static void ledger(){

        Scanner usersInputLedger = new Scanner(System.in);
        System.out.println("Ledger selected!");

        // Ledger menu
        System.out.println("A) All transactions");
        System.out.println("D) Deposit Only");
        System.out.println("P) Payments Only");
        System.out.println("R) Reports");
        System.out.println("H) Home menu");
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


    // ledger menu methods
    public static void displayAllEntries(){
        System.out.println("Display All Entries selected!");



        ArrayList<Transaction> list = loadTransactions();
        for (int i = list.size() -1; i >= 0; i--){
            System.out.println(list.get(i).toCSVLine());
        }

    }
    public static void displayOnlyDeposits(){


        System.out.println("Display only deposits selected!");



        ArrayList<Transaction> transaction = loadTransactions();


        for (int i = transaction.size() -1; i >= 0; i--){

            Transaction transactionList = transaction.get(i); //extracting the current transaction list.

            //Using if statement to filter all the positive transactions.
            if(transactionList.getAmount() > 0){
                System.out.println(transactionList.toCSVLine());
            }

        }

    }
    public static void displayOnlyPayments(){


        System.out.println("Display only payments selected!");



        ArrayList<Transaction> transaction = loadTransactions();


        for (int i = transaction.size() -1; i >= 0; i--){

            Transaction transactionList = transaction.get(i); //extracting the current transaction list.

            //Using if statement to filter all the positive transactions.
            if(transactionList.getAmount() < 0){
                System.out.println(transactionList.toCSVLine());
            }

        }
    }
    public static void reports(){



        Scanner usersInputReports = new Scanner(System.in);
        System.out.println("Reports selected!");

        // Reports menu
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("6) Custom Search");
        System.out.println("0) Back <- ");
        int inputReportsScreen = usersInputReports.nextInt();
        usersInputReports.nextLine(); // this code is to clean the enter floating in the memory.

        // Switch case that will deliver the user's input from the report menu.

        switch (inputReportsScreen){
            case 1:
                monthToDateFilter();
                break;
            case 2:
                previousMonthFilter();
                break;
            case 3:
                yearToDateFilter();
                break;
            case 4:
                previousYearFilter();
                break;
            case 5:
                searchByVendorFilter();
                break;
            case 6:
                customSearchFilter();
            case 0:
                ledger();
                break;
        }

    }

    //reports menu methods
    public static void monthToDateFilter(){
        System.out.println("Month to date filter selected!");

        //getting today's month and year.
        LocalDateTime now = LocalDateTime.now();
        String currentMonthYear = now.format(DateTimeFormatter.ofPattern("yyyy-MM"));



        ArrayList<Transaction> list = loadTransactions();

        for (int i = list.size() -1; i >= 0; i--){
            Transaction transactionList = list.get(i); //extracting the current transaction list.

            // the filter to display only month to date transactions.
            if(transactionList.getDate().startsWith(currentMonthYear)) {
                System.out.println(list.get(i).toCSVLine());
            }
        }





    }
    public static void previousMonthFilter(){
        System.out.println("Previous Month Filter selected!");
        //getting today's month and year.
        LocalDate now = LocalDate.now();
        LocalDate previousMonthDate = now.minusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String filter = previousMonthDate.format(formatter);
        ArrayList<Transaction> list = loadTransactions();
        boolean found = false;

        for (int i = list.size() - 1; i >= 0; i--) {
            Transaction transaction = list.get(i);

            if (transaction.getDate().startsWith((filter))) {
                System.out.println(transaction.toCSVLine());
                found = true;
            }

        }
        if(!found){
            System.out.println("No transactions found for " + filter);
        }

    }
    public static void yearToDateFilter(){
        System.out.println("Year to date filter selected!");

        //getting today's month and year.
        LocalDateTime now = LocalDateTime.now();
        String currentYear = now.format(DateTimeFormatter.ofPattern("yyyy"));



        ArrayList<Transaction> list = loadTransactions();

        for (int i = list.size() -1; i >= 0; i--){
            Transaction transactionList = list.get(i); //extracting the current transaction list.

            // the filter to display only month to date transactions.
            if(transactionList.getDate().startsWith(currentYear)) {
                System.out.println(list.get(i).toCSVLine());
            }
        }
    }
    public static void previousYearFilter(){
        System.out.println("Previous year filter selected!");

        //getting today's month and year.
        LocalDate now = LocalDate.now();
        LocalDate previousYear = now.minusYears(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String filter = previousYear.format(formatter);
        ArrayList<Transaction> list = loadTransactions();
        boolean found = false;

        for (int i = list.size() - 1; i >= 0; i--) {
            Transaction transaction = list.get(i);

            if (transaction.getDate().startsWith((filter))) {
                System.out.println(transaction.toCSVLine());
                found = true;
            }

        }
        if(!found){
            System.out.println("No transactions found for " + filter);
        }
    }
    public static void searchByVendorFilter(){
        System.out.println("Search by vendor filter selected!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter de vendor name to search: ");
        String vendor = scanner.nextLine().trim();

        ArrayList<Transaction> list = loadTransactions();
        boolean found = false;

        for (int i = list.size() - 1; i >= 0; i--){
            Transaction transaction = list.get(i);

            if (transaction.getVendor().equalsIgnoreCase(vendor)){
                System.out.println(transaction.toCSVLine());
                found = true;
            }
        }

        if (!found){
            System.out.println("No transactions found for vendor " + vendor);
        }

    }
    public static void customSearchFilter(){
        System.out.println("Custom Search Selected!");

        Scanner customInput = new Scanner(System.in);

        System.out.println("What is the start date? (you can leave it empty if you don't know)");
        String startDate = customInput.nextLine();
        System.out.println("What is the end date? (you can leave it empty if you don't know)");
        String endDate = customInput.nextLine();
        System.out.println("What is the description? (you can leave it empty if you don't know)");
        String usersDescription = customInput.nextLine();
        System.out.println("What is the vendor? (you can leave it empty if you don't know)");
        String usersVendor = customInput.nextLine();
        System.out.println("What is the amount? (you can leave it empty if you don't know)");
        String usersAmount = customInput.nextLine();

        String [] usersInput = {startDate, endDate, usersDescription, usersVendor, usersAmount};



    }

    //Other methods:
    public static void saveTransaction(String date, String description, String provider, double amount){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions.csv", true))){
            String line = String.format("%s|%s|%s|%.2f", date, description, provider, amount);
            writer.write(line);
            writer.newLine();
            writer.close();

            System.out.println();
            System.out.println("Deposit saved successful!");
        }catch(IOException e){
            System.out.println("Error, transaction could not be saved.");
        }
    }
    public static ArrayList<Transaction> loadTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Transactions.csv"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] part = line.split("\\|");

                //Calling the constructor Transaction.java to create a new transaction.
                Transaction transaction = new Transaction(part[0], part[1], part[2], part[3], Double.parseDouble(part[4]));
                transactions.add(transaction);
            }

        }catch (IOException e){
            System.out.println("Error reading the file");
        }
        return transactions;
    }



}
