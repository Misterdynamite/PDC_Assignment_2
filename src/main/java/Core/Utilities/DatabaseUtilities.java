package Core.Utilities;

import Core.Player.Inventory;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseUtilities {

    // Convert a Clob to an ArrayList representing the journey log
    public static ArrayList<String> clobToJourneyLog(Clob clob) {
        if (clob == null) {
            return null;
        }
        try {
            String str = clob.getSubString(1, (int) clob.length());
            String[] lines = str.split("\\r?\\n");
            return new ArrayList<>(Arrays.asList(lines));
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert CLOB to journey log", e);
        }
    }

    // Converts an ArrayList of Journey log entries to a CLOB
    public static Clob journeyLogToClob(ArrayList<String> log) {
        try {
            if (log == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (String entry : log) {
                sb.append(entry).append("\n");
            }
            return new javax.sql.rowset.serial.SerialClob(sb.toString().toCharArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert journey log to CLOB", e);
        }
    }

    // Converts a Clob to an ArrayList of Inventory.Item
    public static Clob inventoryToClob(ArrayList<Inventory.Item> items) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Inventory.Item item : items) {
                sb.append(item.name()).append(",");
            }
            return new javax.sql.rowset.serial.SerialClob(sb.toString().toCharArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert inventory to CLOB", e);
        }
    }

    // Converts a CLOB to an ArrayList of Inventory.Item
    public static ArrayList<Inventory.Item> clobToInventory(Clob clob) {
        if (clob == null) {
            return null;
        }

        try {
            String str = clob.getSubString(1, (int) clob.length());
            String[] names = str.split(",");
            ArrayList<Inventory.Item> items = new ArrayList<>();
            for (String name : names) {
                if (!name.isEmpty()) {
                    items.add(Inventory.Item.valueOf(name));
                }
            }
            return items;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert CLOB to inventory", e);
        }
    }


}
