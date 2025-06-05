package Core.Database;

import java.sql.Clob;

public class DatabaseUtilities {
    public static String clobToString(Clob clob) {
        if (clob == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String str = clob.getSubString(1, (int) clob.length());
            sb.append(str);
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert CLOB to String", e);
        }
    }
    public static Clob stringToClob(String str) {
        try {
            if (str == null) {
                return null;
            }
            java.sql.Clob clob = new javax.sql.rowset.serial.SerialClob(str.toCharArray());
            return clob;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert String to CLOB", e);
        }
    }
}
