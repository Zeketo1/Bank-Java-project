package bankproject;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Customer {
    String name;
    String location;
    int accountNumber;
    int bvn;
    double balance = 0.00;
    String state;
    int atmPin;
    int transferPin;
    String country;
    String DOB;
    String emailAddress;
    String address;
    String phoneNumber;
    String nextOfKin;
    String gender;
    String accountType;
    String occupation;
    final int ADMINPIN = 1002;
    Scanner input = new Scanner(System.in);

    public void createAccount() {
        Random rand = new Random();

        try {
            // Collect inputs from users
            System.out.print("Enter your full name: ");
            this.name = input.nextLine();

            System.out.print("Enter your email address: ");
            this.emailAddress = input.nextLine();

            System.out.print("Enter your phone number: ");
            this.phoneNumber = input.nextLine();

            System.out.print("Enter your home address: ");
            this.address = input.nextLine();

            System.out.print("Enter your gender: ");
            this.gender = input.nextLine();

            System.out.print("Enter your country: ");
            this.country = input.nextLine();

            System.out.print("Enter date of birth: ");
            this.DOB = input.nextLine();

            System.out.print("Enter your state: ");
            this.state = input.nextLine();

            System.out.print("Enter occupation: ");
            this.occupation = input.nextLine();

            System.out.print("Enter of next of kin: ");
            this.nextOfKin = input.nextLine();

            System.out.print("Enter account type (Savings - S, Current - C): ");
            this.accountType = input.nextLine();

            boolean state = true;
            while (state) {
                System.out.print("Enter your ATM PIN: ");
                String pin = input.next();

                if (pin.length() != 4) {
                    System.out.println("Pin must be 4 digits");
                    continue;
                }
                try {
                    this.atmPin = Integer.parseInt(pin);
                    state = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            boolean stateForTransferPin = true;
            while (stateForTransferPin) {
                System.out.print("Enter your Transfer PIN: ");
                String pin = input.next();

                if (pin.length() != 4) {
                    System.out.println("Pin must be 4 digits");
                    continue;
                } else {
                    this.transferPin = Integer.parseInt(pin);
                    stateForTransferPin = false;
                }
            }


            // Create account number:
            this.accountNumber = Helper.generateNum();
//            this.accountNumber = Math;

            // Create BVN
            this.bvn = Helper.generateNum();

            System.out.printf("Your account number is: %d\n", this.accountNumber);
            System.out.printf("Your BVN is: %d\n", this.bvn);
            System.out.printf("Your Balance is: %f\n", this.balance);

            System.out.println("========================= \n\n");
            signIn();

            input.close();
        } catch(Exception e) {
//            createAccount();
            System.out.println(e.getMessage());
        }
    }

    public void signIn() {
        System.out.println("======= Login to dashboard ========");
        try {
            System.out.print("Enter account number: ");
            int accNum = input.nextInt();

            System.out.print("Enter account pin: ");
            int accPin = input.nextInt();

            while (accNum != this.accountNumber) {
                while (accPin != this.atmPin) {
                    System.out.println("Invalid Credentials");

                    System.out.print("Enter account number: ");
                    accNum = input.nextInt();

                    System.out.print("Enter account pin: ");
                    accPin = input.nextInt();
                }
            }

            System.out.println("Logged in successful!\n\n\n");
            Bank.landing();

        } catch(Exception e) {
            signIn();
        }
    }

    public void changeTransferPin () {

        boolean condition = true;

        while (condition) {

            System.out.print("Input previous transfer PIN: ");
            int previousPin = input.nextInt();

            try {
                if (previousPin == transferPin) {
                    condition = false;
                    boolean confirmCondition = true;

                    System.out.print("Input your new transfer PIN: ");
                    int newPIn = input.nextInt();

                    while (confirmCondition){
                        System.out.print("Confirm new transfer PIN: ");
                        int confirm = input.nextInt();

                        if (newPIn == confirm) {
                            confirmCondition = false;
                            System.out.println("Transfer PIN has been changed successfully");
                            Bank.landing();
                        } else {
                            System.out.println("New transfer PIN doesn't match");
                            continue;
                        }
                    }
                } else {
                    System.out.println("Invalid Credentials!!!!!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        Bank.landing();

    }

    public void checkBalance() {
        System.out.printf("Your account Balance is: %d",balance);
        Bank.landing();
    }

    public void viewBvn() {
        System.out.println(bvn);
        Bank.landing();
    }

    public void deposit () {
        System.out.print("How much do you want to deposit: ");
        double amount = input.nextDouble();
        boolean condition = true;

        while (condition) {
            System.out.printf("Are you sure you want to deposit %f Y / N: ", amount);
            String choice = input.next().toLowerCase();

            if (choice.equals("y")) {
                balance += amount;
                System.out.println("Deposit was successful");
                Bank.landing();
                condition = false;
            } else if (choice.equals("n")) {
                System.out.println("Deposit has been terminated");
                condition = false;
                Bank.landing();
            } else {
                System.out.println("Invalid Choice");
            }
        }
    }
}