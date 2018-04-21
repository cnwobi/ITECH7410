package controller;

import domain.LoyaltyMember;
import domain.StaffMember;
import domain.StandardMember;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static int hardcodedLoyalty = 0;
    private static int hardcodedEmployee = 0;
    private static int hardcodedStandard = 0;
    ArrayList<StandardMember> standardMembers = new ArrayList<StandardMember>();
    ArrayList<LoyaltyMember> loyaltyMembers = new ArrayList<>();
    ArrayList<StaffMember> staffMembers = new ArrayList<>();

    public void displayUserMenu() {
        // Control the menu navigation.  Includes display of menu, acceptance and processing of user input and
        // exiting the menu based on the user's selections.
        boolean blnContinue = true;
        Scanner scanInput = null;

        header();

        try {        //try-catch-finally to ensure compatibility with all java versions.
            scanInput = new Scanner(System.in);
            while (blnContinue) {
                displayMenu();

                if (scanInput.hasNextInt()) {
                    blnContinue = processMenuSelection(scanInput.nextInt());
                } else {
                    scanInput.nextLine();
                    blnContinue = processMenuSelection(0);
                }
            }
        } catch (Exception e) {
            // Generic error catch - this can be made more specific to the expected errors.
            System.out.println("An unexpected exception has occurred with input");
        } finally {
            // Close the scanner
            if (scanInput != null) {
                scanInput.close();
            }
        }

    }

    private void header() {
        // Display program header information
        System.out.println("FedHire Car Hire");
        System.out.println("Payment Tracking System");
        System.out.println("==========================");
        System.out.println();
    }

    private void displayMenu() {

        System.out.println("Select an option from the menu below:");
        System.out.println("1. Standard Payment");
        System.out.println("2. Loyalty Payment");
        System.out.println("3. Employee Payment");
        System.out.println("4. Display List of Clients");
        System.out.println("5. Generate Report To View Payments Received");
        System.out.println("6. Exit");
        System.out.print("Enter your option: ");

    }

    private boolean processMenuSelection(int menuSelection) {
        // Use the input provided by the user to activate the appropriate code function

        boolean blnContinue = true;

        switch (menuSelection) {
            case 1:
                // call method to process standard payments here
                standardPayment();
                break;
            case 2:
                // call method to process loyalty rewards payments here
                System.out.println("--Processing Loyalty Payment--");
                loyaltyPayments();
                break;
            case 3:
                // call method to process employee payments here
                System.out.println("--Processing Employee Payment--");
                employeePayment();
                break;
            case 4:
                // call method to  display list of clients here

                listClients();
                break;
            case 5:
                // call method to generate report to display payments received here
                generateReport();
                break;
            case 6:
                System.out.println("-- Exiting FedHire Payment System --\n ....\n  -- Goodbye! --\n");
                blnContinue = false;
                break;
            default:
                // no valid selection made
                System.out.println("Invalid selection! A number between 1 and 6 was expected.");
        }
        return blnContinue;
    }





    public void standardPayment() {
        //This method processes the standard Member payment logic

        // hardcodedStandard used to ensure hardcoded member is created once
        if (hardcodedStandard < 1) {
            standardMembers.add(new StandardMember("Chuka", "Nwobi", 100));
            hardcodedStandard++;
        }
        printListHeader();
//print out the list of all standard members with payments and applicable discounts
        standardMembers.forEach(standardMember -> System.out.printf("\n%1d. %-23s %-2s %-20s %2s %.2f %-20s %.2f ", standardMember.getId(),
                "", standardMember.getFirstName(), standardMember.getLastName(), "", standardMember.getPayment(), "", standardMember.getDISCOUNTEDAMOUNT()));
        System.out.println();

        boolean processing = true;
        Scanner scanInput = new Scanner(System.in);

        while (processing) {

            try {
                System.out.println("Enter ID of an existing client or  0 to enter a new one: ");
                int choice = scanInput.nextInt();


                if (choice == 0) {
                    // if choice ==0 create a new standard member client
                    System.out.println("Please Enter the First name of the new member: ");
                    scanInput.nextLine();
                    String firstName = scanInput.nextLine();

                    System.out.println("Please Enter the Last Name For this new member");
                    String lastName = scanInput.nextLine();

                    //validate that amount input for the new customer is a positive integer
                    while (processing) {
                        try {
                            System.out.println("Enter Amount For This Payment: ");
                            double payment = scanInput.nextDouble();
                            if (payment >= 0) {
                                processingMessage("Standard");
                                standardMembers.add(new StandardMember(firstName, lastName, payment));
                                processing = false;
                            } else {
                                System.out.println("Amount must be a positive integer or decimal");
                            }
                        } catch (Exception e) {
                            System.out.println("Error Amount entered in the incorrect format");
                            System.out.println();
                            scanInput.next();
                        }
                    }
                } else if (choice < 0) {
                    System.out.println("Invalid Entry");
                    System.out.println("Enter ID of an existing client or  0 to enter a new one: ");
                    scanInput.next();

                } else if (choice >= 1) {


                    if (standardMembers.size() > 0) {

                        for (StandardMember standardMember : standardMembers) {

                            try {
                                if (standardMember.getId() == choice) {
                                    System.out.println("Member found " + standardMember.getFirstName());
                                    // validate that input is positive
                                    while (processing) {
                                        try {

                                            System.out.println("Enter Amount For This Payment: ");
                                            double payment = scanInput.nextDouble();

                                            if (payment >= 0) {
                                                processingMessage("Standard");
                                                standardMember.addPayment(payment);
                                                processing = false;
                                            } else {
                                                System.out.println("Amount must be a positive integer or decimal");
                                            }
                                        }
                                        catch (Exception e){
                                            System.out.println("Error Amount entered in the incorrect format");
                                            System.out.println();
                                            scanInput.next();
                                        }


                                    }

                                } else {
                                    System.out.println("No Member with the Id: " + choice + " was found");
                                }
                            } catch (Exception e) {
                                System.out.println("Error! Payment must be an integer or decimal number only");
                                scanInput.next();
                            }
                        }
                    } else {
                        System.out.println("No Member with the Id: " + choice + " was found");
                    }

                }


                System.out.println(standardMembers.toString());
            } catch (Exception e) {
                System.out.println("Unexpected Input...An Integer ID was expected   \nPlease Try Again");
                scanInput.next();
                System.out.println("\n\n");
            }
        }

    }

    public void loyaltyPayments() {
        //This method processes loyalty payments
        //hardcoded loyalty client

        if (hardcodedLoyalty < 1) {
            loyaltyMembers.add(new LoyaltyMember("Malcom", "Turnbull", 170));
            hardcodedLoyalty++;
        }
        printListHeader();
        loyaltyMembers.forEach(loyaltyMember -> System.out.printf("\n%1d. %-23s %-2s %-20s %2s %.2f %-20s %.2f", loyaltyMember.getId(),
                "", loyaltyMember.getFirstName(), loyaltyMember.getLastName(), "", loyaltyMember.getPayment(), "", loyaltyMember.getDiscount()));
        System.out.println();

        boolean processing = true;
        Scanner scanInput = new Scanner(System.in);
        while (processing) {

            try {
                System.out.println("Enter ID of an existing client or  0 to enter a new one: ");
                int choice = scanInput.nextInt();
                if (choice == 0) {

                    System.out.println("Please Enter the First name of the new member: ");
                    scanInput.nextLine();
                    String firstName = scanInput.nextLine();

                    System.out.println("Please Enter the Last Name For this new member");
                    String lastName = scanInput.nextLine();
                    //validate that amount is a positive integer or decimal
                    while (processing) {
                        try {
                            System.out.println("Enter Amount For This Payment: ");
                            double payment = scanInput.nextDouble();
                            if (payment >= 0) {
                                processingMessage("Loyalty");
                                loyaltyMembers.add(new LoyaltyMember(firstName, lastName, payment));
                                processing = false;
                            } else {
                                System.out.println("Error! Payment must be an integer or decimal number only");
                            }
                        } catch (Exception e) {
                            System.out.println("Error Amount entered in the incorrect format");
                            System.out.println();
                            scanInput.next();
                        }
                    }


                } else if (choice < 0) {
                    System.out.println("Invalid Entry");
                    System.out.println("Enter ID of an existing client or  0 to enter a new one: ");
                    scanInput.next();
                } else if (choice >= 1) {

                    if (loyaltyMembers.size() > 0) {
                        for (LoyaltyMember loyaltyMember : loyaltyMembers) {
                            if (loyaltyMember.getId() == choice) {
                                System.out.println("Member found " + loyaltyMember.getFirstName());
                                // validate that input is positive on updating an existing client
                                while (processing) {
                                    try {
                                        System.out.println("Enter Amount For This Payment: ");
                                        double payment = scanInput.nextDouble();

                                        if (payment >= 0) {
                                            processingMessage("Loyalty");
                                            loyaltyMember.addPayment(payment);
                                            processing = false;
                                        } else {
                                            System.out.println("Amount must be a positive integer or decimal");
                                        }
                                    }
                                    catch (Exception e){
                                        System.out.println("Error Amount entered in the incorrect format");
                                        System.out.println();
                                        scanInput.next();
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("No Member with the Id: " + choice + " was found");
                    }

                }



            } catch (Exception e) {
                System.out.println("Unexpected Input...An Integer ID was expected   \nPlease Try Again");
                scanInput.next();
                System.out.println("\n\n");
            }
        }


    }

    public void employeePayment() {
        //This method processes employee payments
        //hardcoded employee client

        if (hardcodedEmployee < 1) {
            staffMembers.add(new StaffMember("Donald", "Trump", 1000));
            hardcodedEmployee++;
        }
        printListHeader();
        staffMembers.forEach(staffMember -> System.out.printf("\n%1d. %-23s %-2s %-20s %2s %.2f %-20s %.2f", staffMember.getId(),
                "", staffMember.getFirstName(), staffMember.getLastName(), "", staffMember.getPayment(), "", staffMember.getDiscount()));
        System.out.println();

        boolean processing = true;
        Scanner scanInput = new Scanner(System.in);
        while (processing) {

            try {
                System.out.println("Enter ID of an existing client or  0 to enter a new one: ");
                int choice = scanInput.nextInt();
                if (choice == 0) {

                    System.out.println("Please Enter the First name of the new member: ");
                    scanInput.nextLine();
                    String firstName = scanInput.nextLine();

                    System.out.println("Please Enter the Last Name For this new member");
                    String lastName = scanInput.nextLine();
                    while (processing) {
                        try {
                            System.out.println("Enter Amount For This Payment: ");
                            double payment = scanInput.nextDouble();
                            if (payment >= 0) {
                                processingMessage("Employee");
                                staffMembers.add(new StaffMember(firstName, lastName, payment));
                                processing = false;
                            } else {
                                System.out.println("Amount must be a positive integer or decimal");
                            }
                        } catch (Exception e) {
                            System.out.println("Error Amount entered in the incorrect format");
                            System.out.println();
                            scanInput.next();
                        }
                    }

                } else if (choice < 0) {
                    System.out.println("Invalid Entry");
                    System.out.println("Enter ID of an existing client or  0 to enter a new one: ");
                    scanInput.next();
                } else if (choice >= 1) {

                    if (staffMembers.size() > 0) {
                        for (StaffMember staffMember : staffMembers) {
                            if (staffMember.getId() == choice) {
                                System.out.println("Member found " + staffMember.getFirstName());
                                // validate that input is positive on updating an existing client
                                /*
                                *Input is wrapped in a try catch block to handle input mismatch exceptions
                                * The if statement in the while block is used to ensure negative amounts are not accepted in the systems
                                */
                                while (processing) {
                                    try {
                                    System.out.println("Enter Amount For This Payment: ");
                                    double payment = scanInput.nextDouble();

                                    if (payment >= 0) {
                                        processingMessage("Employee");
                                        staffMember.addPayment(payment);
                                        processing = false;
                                    } else {
                                        System.out.println("\nAmount must be a positive integer or decimal");
                                        System.out.println();
                                    }
                                }
                                catch (Exception e){
                                    System.out.println("Error Amount entered in the incorrect format");
                                    System.out.println();
                                    scanInput.next();
                                }
                                }
                            } else {
                                System.out.println("No Member with the Id: " + choice + " was found");
                            }
                        }
                    } else {
                        System.out.println("No Member with the Id: " + choice + " was found");
                    }

                }


            } catch (Exception e) {
                System.out.println("Unexpected Input...An Integer ID was expected   \nPlease Try Again");
                scanInput.next();
                System.out.println("\n\n");
            }
        }

    }

    public void listClients() {
// this method lists all clients according to their respective membership types
        printListHeader();
        System.out.println("Standard Clients: ");
        standardMembers.forEach(standardMember -> System.out.printf("\n%1d. %-23s %-2s %-20s %2s %.2f %-20s %.2f ", standardMember.getId(),
                "", standardMember.getFirstName(), standardMember.getLastName(), "", standardMember.getPayment(), "", standardMember.getDISCOUNTEDAMOUNT()));
        System.out.println("\n Loyalty Members: ");
        loyaltyMembers.forEach(loyaltyMember -> System.out.printf("\n%1d. %-23s %-2s %-20s %2s %.2f %-20s %.2f", loyaltyMember.getId(),
                "", loyaltyMember.getFirstName(), loyaltyMember.getLastName(), "", loyaltyMember.getPayment(), "", loyaltyMember.getDiscount()));
        System.out.println("\nEmployee Clients: ");
        staffMembers.forEach(staffMember -> System.out.printf("\n%1d. %-23s %-2s %-20s %2s %.2f %-20s %.2f", staffMember.getId(),
                "", staffMember.getFirstName(), staffMember.getLastName(), "", staffMember.getPayment(), "", staffMember.getDiscount()));
        System.out.println();
    }

    public void printListHeader() {
        //header of all printed lists of payment
        System.out.println("--List of Clients--\n" +
                "Client ID                  Name                   Total Amount              Discounted Amount");
    }

    public void processingMessage(String type) {
        //standard processing message on console
        System.out.println("--Processing " + type + " Payment--");
        System.out.println("..." + " \n..." + " \nFinished processing payment");

    }
    public void generateReport(){
        // call method to generate report to display payments received here
        System.out.println("--Report to View Payments Received--");
        dash();
        System.out.println();
        System.out.printf("%-30s %-30s %-30s  ", "Type of Account", "Total Amount Received", "Discounts Given");
        System.out.println();
        dash();
        System.out.println();
        System.out.printf("%-30s %-15s %.2f %-15s %.2f  ", "Standard", "", StandardMember.getTotalPayment(), "", StandardMember.getDISCOUNTEDAMOUNT());
        System.out.println();
        System.out.printf("%-30s %-15s %.2f %-15s %.2f  ", "Loyalty", "", LoyaltyMember.getTotalDiscountedPayment(), "", LoyaltyMember.getTotalDiscountGiven());
        System.out.println();
        System.out.printf("%-30s %-15s %.2f %-15s %.2f  ", "Employee", "", StaffMember.getTotalDiscountedPayment(), "", StaffMember.getTotalDiscountGiven());
        System.out.println();
        dash();
        double grandTotalDiscount = StaffMember.getTotalDiscountGiven() + LoyaltyMember.getTotalDiscountGiven();
        double grandTotal = StandardMember.getTotalPayment() + LoyaltyMember.getTotalDiscountedPayment() + StaffMember.getTotalDiscountedPayment();
        System.out.printf("\n%-30s %-15s %.2f %-15s %.2f  ", "Total", "", grandTotal, "", grandTotalDiscount);
        System.out.println();
        dash();
        System.out.println();
    }

    public void dash() {
        //this method just prints the dash
        for (int i = 0; i < 78; i++) {
            System.out.print("-");
        }
    }
}//public class Menu


