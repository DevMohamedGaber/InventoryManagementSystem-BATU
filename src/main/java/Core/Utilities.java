/*
   general purpose class that contains helper functions
 */
package Core;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Utilities {
    public static void SetTableAllignment(JTable table, int allignment) {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(allignment);
        
        int colCount = table.getColumnCount();
        for(int i = 0; i < colCount; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }
}
