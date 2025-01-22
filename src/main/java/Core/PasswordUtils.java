/*
    This class is only responsible for 
    - Hashing the password in plain text to hashed text for database storage
    - comparing plain password to a hashed one from the database to see if they're the same or not
*/
package Core;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils
{
    // plain => hashed
    public static String hashPassword(String plainTextPassword)
    {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    
    // checking if they're the same or not
    public static boolean CheckPassword(String plainTextPassword, String hashedPassword)
    {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
