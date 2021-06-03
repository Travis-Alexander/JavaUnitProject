package com.company;

import database.Connect;

import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        BudgetBook streamLog = new BudgetBook();
        BudgetBook itemList = new BudgetBook();


        while (true) {
            String option = menu();

            if (option.equalsIgnoreCase("1")) {
                String day = days();


                System.out.print("What time did you stream? ");
                String time = keyboard.nextLine();
                Time realTime = Time.parseTime(time);

                System.out.print("How much did you make? ");
                double earnings = keyboard.nextDouble();
                keyboard.nextLine();

                StreamEntry stream = new StreamEntry(day, time, earnings);
                Connect.addStreamEntry(stream);
            } else if (option.equalsIgnoreCase("2")) {
                System.out.print("Equipment Name: ");
                String name = keyboard.nextLine();

                System.out.print("Equipment Price: ");
                Double price = keyboard.nextDouble();
                keyboard.nextLine();

                Equipment item = new Equipment(name, price);
                Connect.addEquipment(item);
            } else if (option.equalsIgnoreCase("3")) {
                System.out.println("Which piece of equipment do you wish to delete");
                itemList.viewAllEquipment();
                System.out.print("> ");
                String name = keyboard.nextLine();
                Connect.deleteEquipment(name);
            } else if (option.equalsIgnoreCase("4")) {
                System.out.println("Note: This is how much we take away from your earnings each month to determine how much you actually have.");
                System.out.println("Please enter in your total for your monthly bills.");
                System.out.println();
                System.out.print("What is your new savings amount? ");
                Double savings = keyboard.nextDouble();
                keyboard.nextLine();
                Connect.updateDeduction(savings);
            } else if (option.equalsIgnoreCase("5")) {
                System.out.println("Which piece of equipment do you want to view? ");
                itemList.viewAllEquipment();
                System.out.print("> ");
                String equipmentName = keyboard.nextLine();
                itemList.viewEquipmentInfo(equipmentName);
            } else if (option.equalsIgnoreCase("6")) {
                streamLog.viewAllStreams();
            } else if (option.equalsIgnoreCase("7")) {
                itemList.viewAllEquipment();
            } else if (option.equalsIgnoreCase("8")) {
                System.exit(0);
            }
        }


    }

    private static String menu() throws SQLException{
        System.out.println();
        System.out.println("Current Monthly Deduction: $" + Connect.getDeductions());
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1. Add a Stream");
        System.out.println("2. Add equipment to wishlist");
        System.out.println("3. Take equipment out of wishlist");
        System.out.println("4. Update Savings");
        System.out.println("5. View Specific Equipment");
        System.out.println("6. View all Streams");
        System.out.println("7. View all Equipment");
        System.out.println("8. Quit");
        System.out.print("> ");
        String choice = keyboard.nextLine();

        while (!choice.equalsIgnoreCase("1") && !choice.equalsIgnoreCase("2") && !choice.equalsIgnoreCase("3") && !choice.equalsIgnoreCase("4") && !choice.equalsIgnoreCase("5") && !choice.equalsIgnoreCase("6") && !choice.equalsIgnoreCase("7") && !choice.equalsIgnoreCase("8")) {
            System.out.println("Pick a valid choice!");
            System.out.println("What would you like to do?");
            System.out.println("1. Add a Stream");
            System.out.println("2. Add equipment to wishlist");
            System.out.println("3. Take equipment out of wishlist");
            System.out.println("4. Update Savings");
            System.out.println("5. View Specific Equipment");
            System.out.println("6. View all Streams");
            System.out.println("7. View all Equipment");
            System.out.println("8. Quit");
            System.out.print("> ");
            choice = keyboard.nextLine();

            if (choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("4") || choice.equalsIgnoreCase("5") || choice.equalsIgnoreCase("6") || choice.equalsIgnoreCase("7") || choice.equalsIgnoreCase("8")) {
                break;
            }
        }
        return choice;
    }

    private static String days() throws SQLException{
        System.out.print("What day did you stream? ");
        String choice = keyboard.nextLine();
        String day = "";
        while (!choice.equalsIgnoreCase("monday") && !choice.equalsIgnoreCase("tuesday") && !choice.equalsIgnoreCase("wednesday") && !choice.equalsIgnoreCase("thursday") && !choice.equalsIgnoreCase("friday") && !choice.equalsIgnoreCase("saturday") && !choice.equalsIgnoreCase("sunday")){
            System.out.println("Please choose a valid day.");
            choice = keyboard.nextLine();
            if (choice.equalsIgnoreCase("monday") || choice.equalsIgnoreCase("tuesday") || choice.equalsIgnoreCase("wednesday") || choice.equalsIgnoreCase("thursday") || choice.equalsIgnoreCase("friday") || choice.equalsIgnoreCase("saturday") || choice.equalsIgnoreCase("sunday")){
                break;
            }

        }
        return choice;

    }
}
