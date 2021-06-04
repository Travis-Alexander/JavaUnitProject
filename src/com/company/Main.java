package com.company;

import database.Connect;

import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Main {
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        BudgetBook streamLog = new BudgetBook();
        BudgetBook itemList = new BudgetBook();


        while (true) {
            String option = menu();

            if (option.equalsIgnoreCase("1")) {
                String day = days();


                System.out.println("What time did you stream? ");

                String hour = hourValidation();
                String minutes = minuteValidation();
                String seconds = secondValidation();

                String time = hour + ":" + minutes + ":" + seconds;
                Time realTime = Time.parseTime(time);


                Double earnings = Double.parseDouble(streamEarnings());
                keyboard.nextLine();

                StreamEntry stream = new StreamEntry(day, time, earnings);
                Connect.addStreamEntry(stream);
            } else if (option.equalsIgnoreCase("2")) {
                System.out.print("Equipment Name: ");
                String name = keyboard.nextLine();
                double price = Double.parseDouble(equipmentCost());
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

    private static String menu() throws SQLException {
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

    private static String days() throws SQLException {
        System.out.print("What day did you stream? ");
        String choice = keyboard.nextLine();
        String day = "";
        while (!choice.equalsIgnoreCase("monday") && !choice.equalsIgnoreCase("tuesday") && !choice.equalsIgnoreCase("wednesday") && !choice.equalsIgnoreCase("thursday") && !choice.equalsIgnoreCase("friday") && !choice.equalsIgnoreCase("saturday") && !choice.equalsIgnoreCase("sunday")) {
            System.out.println("Please choose a valid day.");
            System.out.print("What day did you stream? ");
            choice = keyboard.nextLine();
            if (choice.equalsIgnoreCase("monday") || choice.equalsIgnoreCase("tuesday") || choice.equalsIgnoreCase("wednesday") || choice.equalsIgnoreCase("thursday") || choice.equalsIgnoreCase("friday") || choice.equalsIgnoreCase("saturday") || choice.equalsIgnoreCase("sunday")) {
                break;
            }

        }
        return choice;

    }

    private static String hourValidation() throws SQLException {
        System.out.print("Enter Hour: ");
        String hour = keyboard.nextLine();
        while (true) {
            while (!hour.matches("[0-9]+")) {
                System.out.println("Numbers Only!");
                System.out.print("Enter Hour: ");
                hour = keyboard.nextLine();
                if (hour.matches("[0-9]")) {
                    break;
                }
            }
            if (Integer.parseInt(hour) <= 23) {
                break;
            } else {
                System.out.println("That's too high!");
                System.out.print("Enter Hour: ");
                hour = keyboard.nextLine();

            }
        }

        return hour;
    }

    private static String minuteValidation() throws SQLException {
        System.out.print("Enter minutes: ");
        String minute = keyboard.nextLine();
        while (true) {
            while (!minute.matches("[0-9]+")) {
                System.out.println("Numbers Only!");
                System.out.print("Enter minutes: ");
                minute = keyboard.nextLine();
                if (minute.matches("[0-9]") && Integer.parseInt(minute) <= 59) {
                    break;
                } else if (minute.matches("[0-9]") && Integer.parseInt(minute) > 59) {
                    System.out.println("Enter valid minutes!");
                }
            }
            if (Integer.parseInt(minute) <= 59) {
                break;
            } else {
                System.out.println("That's too high!");
                System.out.print("Enter Minute: ");
                minute = keyboard.nextLine();
            }

        }
        return minute;
    }

    private static String secondValidation() throws SQLException {
        System.out.print("Enter seconds: ");
        String second = keyboard.nextLine();
        while (true) {
            while (!second.matches("[0-9]+")) {
                System.out.println("Numbers Only!");
                System.out.print("Enter seconds: ");
                second = keyboard.nextLine();
                if (second.matches("[0-9]") && Integer.parseInt(second) <= 59) {
                    break;
                } else if (second.matches("[0-9]") && Integer.parseInt(second) > 59) {
                    System.out.println("Enter valid seconds!");
                }
            }
            if (Integer.parseInt(second) <= 59) {
                break;
            } else {
                System.out.println("That's too high!");
                System.out.print("Enter Seconds: ");
                second = keyboard.nextLine();
            }
        }

        return second;
    }

    private static String streamEarnings() throws SQLException {
        System.out.println();
        System.out.print("How much did you make? ");
        String earnings = keyboard.nextLine();
        while (!earnings.matches("[0-9]")) {
            System.out.println();
            System.out.println();
            System.out.println("Enter a valid number!");
            System.out.print("How much did you make? ");
            earnings = keyboard.nextLine();
            if (earnings.matches("[0-9]")) {
                break;
            }
        }
        return earnings;
    }

    private static String equipmentCost() throws SQLException {
        System.out.println();
        System.out.print("Equipment Price: ");
        String price = keyboard.nextLine();
        keyboard.nextLine();
        while (!price.matches("[0-9]")) {
            System.out.println();
            System.out.println();
            System.out.println("Enter a valid number!");
            System.out.print("Equipment Price: ");
            price = keyboard.nextLine();
            if (price.matches("[0-9]")) {
                break;
            }
        }
        return price;
    }
}
