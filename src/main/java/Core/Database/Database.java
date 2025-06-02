package Core.Database;

import java.sql.SQLException;
import java.sql.Statement;

public class Database extends Abstracts.Database.Database {

    public Database() {
        super();
        this.url = "jdbc:derby:CYOADatabase;create=true";
        this.user = "DBAccess";
        this.password = "ReallyCoolAdmin42";

        this.reader = new DatabaseReader(this);
        this.writer = new DatabaseWriter(this);
        this.connect();
    }

    @Override
    public void connect() {
        try {
            this.connection = java.sql.DriverManager.getConnection(url, user, password);
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to embedded database", e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
            }
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close the database connection", e);
        }
    }
}
