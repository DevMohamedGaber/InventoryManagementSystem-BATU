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
}
