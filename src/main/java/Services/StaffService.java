package Services;

import DataAccess.Models.User;
import DataAccess.Repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class StaffService
{
    private final UserRepository _repository;
    
    public StaffService()
    {
        _repository = new UserRepository();
    }
    
    public List<String[]> GetUsersList() {
        List<String[]> result = new ArrayList<>();
        List<User> users = _repository.GetAllUsers();
        
        if(users == null) {
            return null;
        }
        
        for(User user : users) {
            String[] userRow = {
                String.valueOf(user.Id), 
                user.Username, 
                user.Email, 
                user.Role.toString()};
            result.add(userRow);
        }
        
        return result;
    }
    
    public User GetUserById(int userId) {
        if(userId == 0) {
            System.out.println("UserId is empty");
            return null;
        }
        
        var user = _repository.GetUserById(userId);
        
        return user;
    }
    public String UpdateUser(User user, String username, String email, int roleId) {
        if(username.isEmpty()) {
            return "Username is empty";
        }
        if(email.isEmpty()) {
            return "Email is empty";
        }
        if(roleId < 0 || roleId > 2) {
            return "RoleId is outside of range";
        }
        if(username.equals(user.Username) == false && _repository.GetUserByUsername(username) != null) {
            return "username already taken by another user";
        }
        
        boolean userUpdated = _repository.UpdateUserData(user.Id, username, email, roleId);
        
        if(userUpdated == false) {
            return "failed to update user data in database";
        }
        
        return null;
        
    }
    public boolean DeleteUser(int id) {
        return _repository.DeleteUser(id);
    }
}
