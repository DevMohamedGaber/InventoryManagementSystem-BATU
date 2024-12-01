/*
    this class only responsible for holds the definition of a User
    only definitions, no logic
    will be extended while the project is growing and getting more complex to hold more data about the user
*/
package Data;

public class User
{
    private int Id;
    private String Username;
    private String Password;
    
    public User(String[] data)
    {
        this.Id = Integer.parseInt(data[0]);
        this.Username = data[1];
        this.Password = data[2];
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
