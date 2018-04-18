package controller;

import domain.StandardMember;

import java.util.*;

public class Menu {
    int id = 1;
    ArrayList<StandardMember> standardMembers = new ArrayList<StandardMember>();
    
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
        System.out.println("Matthew's Emporium");
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
                System.out.println("--Processing Standard Payment--");


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
                break;
            case 4:
                // call method to  display list of clients here
                System.out.println("--List of Clients--");
                break;
            case 5:
                // call method to generate report to display payments received here
                System.out.println("--Report to View Payments Received--");
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
        //print out the list of Standard Payment clients
        System.out.println("--List of Clients--\n" +
                "Client ID                  Name                   Total Amount              Discounted Amount");

        standardMembers.forEach(standardMember -> System.out.printf("\n%1d. %-23s %-2s %-20s %2s %.2f",standardMember.getId(),
                "",standardMember.getFirstName(),standardMember.getLastName(),"",standardMember.getPayment()));
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
                    System.out.println("Enter Amount For This Payment: ");
                    double payment = scanInput.nextDouble();

                    standardMembers.add(new StandardMember(firstName, lastName, payment));
                    processing = false;


                } else if (choice < 0) {
                    System.out.println("Invalid Entry");
                    System.out.println("Enter ID of an existing client or  0 to enter a new one: ");
                    scanInput.next();
                } else if (choice >= 1) {

                    if(standardMembers.size()> 0){
                        for (StandardMember standardMember : standardMembers) {
                            if (  standardMember.getId() == choice) {
                                System.out.println("Member found " + standardMember.getFirstName());
                                System.out.println("Enter Amount For This Payment: ");
                                double payment = scanInput.nextDouble();
                                standardMember.addPayment(payment);
                                processing = false;
                            } else {
                                System.out.println("No Member with the Id: " + choice + " was found");
                            }
                        }
                    }

                    else{
                        System.out.println("No Member with the Id: " + choice + " was found");
                    }

                }


                System.out.println(standardMembers.toString());
            }
            catch (Exception e){
                System.out.println("Unexpected Input...An Integer ID was expected   \nPlease Try Again");
                scanInput.next();
                System.out.println("\n\n");
            }
        }

    }
    public void loyaltyPayments(){
        //This method processes loyalty payments

    }
}//public class Menu


