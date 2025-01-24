/*
    this is the enrey point for the entire program
    here we make sure the database is initialized and connected to it
    as well as loading the program window
*/
package Core;

public class Program
{
    public static void main(String[] args)
    {
        // make sure database is initialized correctly
        DataAccess.DatabaseInitializer.EnsureDatabaseCreated();
        // load first frame
        UIManager.Instantiate();
        UIManager.Instance.LoadDashboardWindow();
    }
}
