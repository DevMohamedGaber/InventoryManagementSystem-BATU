package Core;

public enum UserRole {
    Admin(0), Manager(1), Staff(2);
    
    public int numValue;
    
    UserRole(int numValue) {
        this.numValue = numValue;
    }
    public int GetInt() {
        return numValue;
    }
    public static UserRole Set(int val) {
        return switch (val) {
            case 0 -> UserRole.Admin;
            case 1 -> UserRole.Manager;
            default -> UserRole.Staff;
        };
    }
}
