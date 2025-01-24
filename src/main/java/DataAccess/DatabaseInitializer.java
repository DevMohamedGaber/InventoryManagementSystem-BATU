/*
    This class is only responsible for
    - making sure the database is created correctly
    - all the table definitions are here
    - seeding the database (for example: if no users found it inserts the default user)
*/
package DataAccess;

import Core.PasswordUtils;
import java.util.List;

public class DatabaseInitializer {
    private static final String[] tablesQueries = {
        "users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL, email TEXT NOT NULL, role INTEGER NOT NULL)"
    };
    
    public static void EnsureDatabaseCreated()
    {
        if(tablesQueries.length == 0)
        {
            return;
        }
        for (String tablesQuery : tablesQueries)
        {
            Database.executeUpdate("CREATE TABLE IF NOT EXISTS " + tablesQuery);
        }
        Seeder();
    }
    private static void Seeder()
    {
        // Insert default admin user if no users found
        String checkQuery = "SELECT COUNT(*) FROM users";
        List<String[]> result = Database.executeQuery(checkQuery);
        if(!result.isEmpty() && result.getFirst().length > 0 && result.getFirst()[0].equals("0"))
        {
            String adminQuery = "INSERT INTO users(username,password,email,role) VALUES('admin',?,'admin@batu.com',0)";
            Database.executeUpdate(adminQuery, PasswordUtils.hashPassword("admin"));
            System.out.println("No users found, auto-created admin user");
        }
    }
}
