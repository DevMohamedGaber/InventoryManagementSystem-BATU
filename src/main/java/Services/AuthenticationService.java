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
        if(roleId < 0 || roleId > 2) {
            return "RoleId is outside of range";
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
    
    public String ChangePassword(int id,String oldPassword,String newPassword,String confirmPassword) {
        if(id <= 0) {
            return "User id can't be 0";
        }
        if(oldPassword.isEmpty()) {
            return "please enter old password";
        }
        if(newPassword.isEmpty()) {
            return "please enter new password";
        }
        if(confirmPassword.isEmpty()) {
            return "please confirm password";
        }
        if(newPassword.equals(confirmPassword) == false) {
            return "password entered doesn't match the confirm password";
        }
        
        User user = _repository.GetUserById(id);
        
        if(user == null) {
            return "user not found";
        }

        if(!PasswordUtils.CheckPassword(oldPassword, user.Password)) {
            return "old password is wrong";
        }
        
        String newPasswordHashed = PasswordUtils.hashPassword(newPassword);
        
        boolean passwordUpdated = _repository.UpdateUserPassword(id, newPasswordHashed);
        
        if(passwordUpdated == false) {
            return "failed to change password in database";
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
