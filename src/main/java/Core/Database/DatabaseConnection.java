package Core.Database;

import java.sql.SQLException;

public class DatabaseConnection extends Abstracts.Database.DatabaseConnection {
    public DatabaseConnection(String url, String user, String password) throws SQLException {
        super(url, user, password);
    }
}
