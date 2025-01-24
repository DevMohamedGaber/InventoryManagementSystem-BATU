/*
    this class only responsible for holds the definition of a User
    only definitions, no logic
    will be extended while the project is growing and getting more complex to hold more data about the user
*/
package DataAccess.Models;

import Core.UserRole;

public class User
{
    public int Id;
    public String Username;
    public String Password;
    public String Email;
    public UserRole Role;

    
    public User(String[] data)
    {
        this.Id = Integer.parseInt(data[0]);
        this.Username = data[1];
        this.Password = data[2];
        this.Email = data[3];
        this.Role = UserRole.Set(Integer.parseInt(data[4]));
    }
}
