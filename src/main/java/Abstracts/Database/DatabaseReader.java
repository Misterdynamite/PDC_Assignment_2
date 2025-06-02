package Abstracts.Database;

import java.sql.Clob;
import java.sql.Statement;

public abstract class DatabaseReader {
    protected Database database;
    protected Statement statement;

    public DatabaseReader() {
    }

    public String clobToString(Clob clob) {
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
}
