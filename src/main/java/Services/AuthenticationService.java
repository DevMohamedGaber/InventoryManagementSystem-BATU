/*
    This class is only responsible for Handling user Authentication (Login)
    and if we add Register functionality it will be here too
*/
package Services;

import Core.PasswordUtils;
import Core.UIManager;
import DataAccess.Models.User;
import DataAccess.Repositories.UserRepository;

public class AuthenticationService {
    public static User currentUser = null;
    private final UserRepository _repository;
    
    public AuthenticationService() {
        _repository = new UserRepository();
    }
    
    public String Login(String username, String password) {
        // check if user enetered right data
        if(username.length() == 0) {
            return "you can't leave username field empty";
        }
        if(password.length() == 0) {
            return "you can't leave password field empty";
        }
        
        // check if username in database
        var user = _repository.GetUserByUsername(username);
        
        if(user == null) {
            return "Username can't be found";
        }
        
        // check if the password is correct (hashed password)
        if(!PasswordUtils.CheckPassword(password, user.Password)) {
            return "Wrong Password";
        }
        
        // set the user as current logged in user for all frames to access
        currentUser = user;
        return null;
    }
    
    public String Register(String username, String password, String email, int roleId) {
        if(username.isEmpty()) {
            return "Username is empty";
        }
        if(password.isEmpty()) {
            return "Password is empty";
        }
        if(email.isEmpty()) {
            return "Email is empty";
        }
        if(password.isEmpty()) {
            return "Password is empty";
        }
        if(roleId < 0 || roleId > 2) {
            return "Password is empty";
        }
        
        if(_repository.GetUserByUsername(username) != null) {
            return "username already taken by another user";
        }
        
        String hashedPassword = PasswordUtils.hashPassword(password);
        boolean userCreated = _repository.CreateUser(username, hashedPassword, email, roleId);
        
        if(userCreated == false) {
            return "failed to add user to database";
        }
        
        return null;
    }
    
    public void Logout() {
        if(currentUser == null) {
            System.out.println("No user logged in the system, please login first");
            return;
        }
        currentUser = null;
        UIManager.Instance.LoadLoginWindow();
    }
    
}
