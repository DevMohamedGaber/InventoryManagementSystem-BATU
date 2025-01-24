/*
    This class is only responsible for Handling user Authentication (Login)
    and if we add Register functionality it will be here too
*/
package Services;

import Core.PasswordUtils;
import DataAccess.Models.User;
import DataAccess.Repositories.UserRepository;

public class AuthenticationService
{
    public static User currentUser = null;
    private final UserRepository _repository;
    
    public AuthenticationService()
    {
        _repository = new UserRepository();
    }
    
    public String Login(String username, String password)
    {
        // check if user enetered right data
        if(username.length() == 0) {
            return "you can't leave username field empty";
        }
        if(password.length() == 0) {
            return "you can't leave password field empty";
        }
        
        // check if username in database
        var user = _repository.GetUserByUsername(username);
        
        if(user == null)
        {
            return "Username can't be found";
        }
        
        // check if the password is correct (hashed password)
        if(!PasswordUtils.CheckPassword(password, user.Password))
        {
            return "Wrong Password";
        }
        
        // set the user as current logged in user for all frames to access
        currentUser = user;
        return null;
    }
    
}
