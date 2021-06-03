package com.company;

import database.Connect;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetBook {
    public static double savings;

    static {
        try {
            savings = Connect.getDeductions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static double totalEarnings = 0.0;





    public void viewAllStreams() throws SQLException {

        ArrayList<StreamEntry> streamLog = Connect.getStreamEntry();;
        for (StreamEntry i : streamLog) {
            int index = streamLog.indexOf(i);
            String streamInfo = "Day streamed: " + i.getStreamDay() + ", Time Streamed: " + i.getStreamTime().getFormattedTime() + ", Earnings: $" + i.getEarnings();
            System.out.printf("%s. %s\n", index + 1, streamInfo);
        }
    }

    public void viewAllEquipment() throws SQLException {
        ArrayList<Equipment> itemList = Connect.getEquipment();

        for (Equipment i : itemList) {
            String equipmentInfo = "Equipment Name: " + i.getDesiredItem() + ", Price: " + i.getDesiredItemCost();
            int index = itemList.indexOf(i);
            System.out.printf("%s. %s\n", index + 1, equipmentInfo);
        }
    }

    public static void updateDeductions(Double newSavings) {
        System.out.println("Old savings amount: " + savings);
        savings = newSavings;
        System.out.println("New savings amount: " + savings);
    }

    public void viewEquipmentInfo(String equipment) throws SQLException {
        ArrayList<Equipment> itemList = Connect.getEquipment();
        for (Equipment i : itemList) {
            if (equipment.equalsIgnoreCase(i.getDesiredItem())) {
                Double priceChecker = (i.getDesiredItemCost() + savings) - totalEarnings;
                System.out.println("Equipment: " + i.getDesiredItem());
                System.out.println("Cost: " + i.getDesiredItemCost());
                if (priceChecker > 0) {
                    System.out.println("Amount you need to earn before purchase: $" + ((i.getDesiredItemCost() + savings) - totalEarnings));
                } else if (priceChecker <= 0) {
                    System.out.println("You can purchase this item.");
                    Double moneyLeft = totalEarnings - priceChecker;
                    if (moneyLeft > 0) {
                        System.out.println("You would have " + (totalEarnings - priceChecker) + " left to spend.");
                    } else if (moneyLeft <= 0) {
                        System.out.println("You wont have anymore money to spend.");
                    }

                }

            }
        }

    }
}
