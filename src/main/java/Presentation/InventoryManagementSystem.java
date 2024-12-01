package Presentation;

public class InventoryManagementSystem
{
    public static void main(String[] args)
    {
        // make sure database is initialized correctly
        Data.DatabaseInitializer.EnsureDatabaseCreated();
        // load first frame
        UIManager.LoadLogin();
    }
}
