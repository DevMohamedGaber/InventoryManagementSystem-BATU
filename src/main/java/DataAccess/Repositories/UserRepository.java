/*
    This class is responsible to interact with the database to get (User) data
*/
package DataAccess.Repositories;

import DataAccess.Database;
import DataAccess.Models.User;
import java.util.List;

public class UserRepository
{ 
    public User GetUserByUsername(String username)
    {
        String stmt = "SELECT * FROM users WHERE username = ?";
        List<String[]> userData = Database.executeQuery(stmt, username);
        if(userData.isEmpty())
        {
            return null;
        }
        User user = new User(userData.getFirst());
        return user;
    }
}
