package DataAccess;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class Database
{
    private static Connection connection = null;
    private static final String URL = "jdbc:sqlite:IMS_Database.db";
    
    private Database() {}
    
    private static Connection GetConnection()
    {
       try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("Connection to SQLite has been established.");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }
    
    // Method to execute a query and return the result set (for SELECT queries)
    public static List<String[]> executeQuery(String sql, Object... params)
    {
        List<String[]> results = null;

        try (Connection conn = GetConnection())
        {
            if (conn == null || conn.isClosed())
            {
                throw new SQLException("Connection is null or already closed.");
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                if(params.length > 0)
                {
                    for (int i = 0; i < params.length; i++)
                    {
                        pstmt.setObject(i + 1, params[i]);
                    }
                }
                System.out.println("Executing query: " + sql);
                
                ResultSet rs = pstmt.executeQuery();
                
                results = PackResult(rs);
                System.out.println("Query returned (" + results.size() + ") rows");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return results;
    }
    
    // Method to execute an update, insert, or delete query (for non-SELECT queries)
    public static int executeUpdate(String sql, Object... params)
    {
        int rowsAffected = 0;

        try (Connection conn = GetConnection(); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            for (int i = 0; i < params.length; i++)
            {
                pstmt.setObject(i + 1, params[i]);
            }
            rowsAffected = pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Error executing update: " + e.getMessage());
            rowsAffected = -1;
        }

        return rowsAffected;
    }
    
    private static List<String[]> PackResult(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        List<String[]> results = new ArrayList<>();
        while (resultSet.next()) {
            String[] row = new String[columnsNumber];
            for (int i = 1; i <= columnsNumber; i++) {
                row[i - 1] = resultSet.getString(i);
            }
            results.add(row);
        }
        return results;
    }
//    private static void PrintResultSet(ResultSet resultSet) throws SQLException {
//        ResultSetMetaData rsmd = resultSet.getMetaData();
//        int columnsNumber = rsmd.getColumnCount();
//        while (resultSet.next()) {
//            for (int i = 1; i <= columnsNumber; i++) {
//                if (i > 1) System.out.print(",  ");
//                String columnValue = resultSet.getString(i);
//                System.out.print("[" + rsmd.getColumnName(i) + "] => " + columnValue);
//            }
//            System.out.println("");
//        }
//    }
}
