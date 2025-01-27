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
    public User GetUserByUsername(String username) {
        String stmt = "SELECT * FROM users WHERE username = ?";
        List<String[]> userData = Database.executeQuery(stmt, username);
        if(userData.isEmpty())
        {
            return null;
        }
        User user = new User(userData.getFirst());
        return user;
    }
    
    public User GetUserById(int id) {
        String stmt = "SELECT * FROM users WHERE id = ?";
        List<String[]> userData = Database.executeQuery(stmt, id);
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
    
    public boolean CreateUser(String username, String password, String email, int roleId) {
        String stmt = "INSERT INTO users(username,password,email,role) VALUES(?,?,?,?)";
        int isAdded = Database.executeUpdate(stmt, username, password, email, roleId);
        return isAdded > 0; 
    }
    
    public boolean UpdateUserData(int id, String username, String email, int roleId) {
        String stmt = "UPDATE users SET username = ?,email = ?, role = ? WHERE id = ?";
        int isUpdated = Database.executeUpdate(stmt, username, email, roleId, id);
        return isUpdated > 0;
    }
    
    public boolean DeleteUser(int id) {
        String stmt = "DELETE FROM users WHERE id = ?";
        int isDeleted = Database.executeUpdate(stmt, id);
        return isDeleted > 0;
    }
    public boolean UpdateUserPassword(int id, String password) {
        String stmt = "UPDATE users SET password = ? WHERE id = ?";
        int isUpdated = Database.executeUpdate(stmt, password, id);
        return isUpdated > 0;
    }
}
