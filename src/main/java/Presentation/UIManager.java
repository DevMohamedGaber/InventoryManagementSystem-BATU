/*
    This class is only responsible for calling diffrent frames from the same place
    each frame will have a function here to call it
    if a frame have a certain rules to be called this is the place to check if those rules are met or not
*/
package Presentation;

import javax.swing.SwingUtilities;

public class UIManager
{
    public static void LoadLogin()
    {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
    public static void LoadHome()
    {
        SwingUtilities.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }
}
