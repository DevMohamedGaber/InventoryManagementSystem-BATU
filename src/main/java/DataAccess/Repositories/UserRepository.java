/*
    This class is responsible to interact with the database to get (User) data
*/
package DataAccess.Repositories;

import DataAccess.Database;
import DataAccess.Models.User;
import java.util.ArrayList;
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
    
    public List<User> GetAllUsers() {
        List<User> result = new ArrayList();
        String stmt = "SELECT * FROM users";
        List<String[]> users = Database.executeQuery(stmt);
        if(users.isEmpty())
        {
            return null;
        }
        for(String[] user : users) {
            result.add(new User(user));
        }
        return result;
    }
}
