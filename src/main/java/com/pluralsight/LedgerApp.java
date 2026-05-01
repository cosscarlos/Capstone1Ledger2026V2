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
            System.out.println("""
        ==================================================
        |                                                |
        |               Carlos bank 👍                   |
        |        Accounting & Ledger System              |
        |                                                |
        ==================================================
        
        Please select an operation to continue:
        
          [ D ] Add Deposit  (Daily Sales/Income)
          [ P ] Make Payment (Vendor/Supplies)
          [ L ] Open Ledger  (Transaction History)
          [ X ] Exit System
        
        ==================================================
        """);
            System.out.print("Enter your command: ");
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
        System.out.println("""
        
        --------------------------------------------------
                  [+] NEW DEPOSIT ENTRY [+]
        --------------------------------------------------
        Please enter the details of the incoming funds.
        """);


        System.out.print("  > Deposit description : ");
        String description = Input.nextLine();

        System.out.print("  > Vendor / Provider   : ");
        String provider = Input.nextLine();

        System.out.print("  > Deposit amount      : $ ");
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
        System.out.println("""
        
        ==================================================
        |                                                |
        |             [L] THE LEDGER VAULT               |
        |             Transaction History                |
        |                                                |
        ==================================================
        
        Filter your view by selecting an option below:
        
          [ A ] View All Transactions
          [ D ] View Deposits Only (Income)
          [ P ] View Payments Only (Expenses)
          [ R ] Advanced Reports & Custom Search
          [ H ] Return to Home Menu
        
        ==================================================
        """);
        System.out.print("Enter your command: ");
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
        System.out.println("""
        
        ==================================================
        |                                                |
        |             [R] REPORTS & FILTERS              |
        |             Financial Analytics                |
        |                                                |
        ==================================================
        
        Select a report to generate:
        
          [ 1 ] Month To Date
          [ 2 ] Previous Month
          [ 3 ] Year To Date
          [ 4 ] Previous Year
          [ 5 ] Search by Vendor
          [ 6 ] Custom Search Filter
          
          [ 0 ] Go Back <-
        
        ==================================================
        """);
        System.out.print("Enter your command: ");
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


        System.out.println("""
        
        --------------------------------------------------
                   [*] CUSTOM SEARCH FILTER [*]
        --------------------------------------------------
        Enter your search criteria. 
        (Press ENTER to leave a field blank and skip it)
        """);


        System.out.print("  > Start Date (yyyy-MM-dd) : ");
        String startDate = customInput.nextLine();

        System.out.print("  > End Date   (yyyy-MM-dd) : ");
        String endDate = customInput.nextLine();

        System.out.print("  > Description             : ");
        String usersDescription = customInput.nextLine();

        System.out.print("  > Vendor                  : ");
        String usersVendor = customInput.nextLine();

        System.out.print("  > Amount                  : $ ");
        String usersAmount = customInput.nextLine();

        System.out.println("--------------------------------------------------");
        System.out.println("Searching...");
        System.out.println();


        ArrayList<Transaction> list = loadTransactions();

      for (int i = 0; i < list.size() ; i++){ // running through the Transaction.CSV file.
          Transaction transactionList = list.get(i);
          boolean passFilter = true;

          if (!startDate.isEmpty()){

              LocalDate transactionDate = LocalDate.parse(transactionList.getDate());
              LocalDate localStartDate = LocalDate.parse(startDate);

              if(transactionDate.isBefore(localStartDate)){
                  passFilter = false;
              }


          }
          if (!endDate.isEmpty()){
              LocalDate transactionDate = LocalDate.parse(transactionList.getDate());
              LocalDate localEndDate = LocalDate.parse(endDate);

              if (transactionDate.isAfter(localEndDate)){
                  passFilter = false;
              }
          }
          if (!usersDescription.isEmpty() && !transactionList.getDescription().toLowerCase().contains(usersDescription.toLowerCase())){
              passFilter = false;
          }
          if (!usersVendor.isEmpty() && !transactionList.getVendor().toLowerCase().contains(usersVendor.toLowerCase())){
              passFilter = false;
          }
          if (!usersAmount.isEmpty()){
              double realAmount = Double.parseDouble(usersAmount);
              if(transactionList.getAmount() != realAmount){
                  passFilter = false;
              }
          }
          if (passFilter){
              System.out.println(transactionList.toCSVLine());
          }
      }
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
