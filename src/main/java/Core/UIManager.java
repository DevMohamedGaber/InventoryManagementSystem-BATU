/*
    This class is only responsible for calling diffrent frames from the same place
    each frame will have a function here to call it
    if a frame have a certain rules to be called this is the place to check if those rules are met or not
*/
package Core;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Presentation.HomeWindow;
import Presentation.LoginWindow;

public class UIManager
{
    public static UIManager Instance; // Singleton
    
    private final String _windowFixedTitle = "BATU Inventory Management System - ";
    private final JFrame _window;
    private JPanel _currentPanel;
    
    public UIManager()
    {
        _window = new JFrame();
        _window.setTitle("BATU - Inventory Management System");
        _window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _window.setResizable(false);
        _window.setVisible(true);
    }
    
    public void LoadLoginWindow()
    {
        ResizeAndCenterWindow(350, 200);
        LoadPanelToWindow(new LoginWindow());
        _window.setTitle("Login");
    }
    public void LoadHomeWindow()
    {
        ResizeAndCenterWindow(1200, 680);
        LoadPanelToWindow(new HomeWindow());
        SetWindowTitle("Home");
    }
    
    private void LoadPanelToWindow(JPanel panel)
    {
        if(_currentPanel != null)
        {
            _window.remove(_currentPanel);
        }
        _currentPanel = panel;
        _window.add(_currentPanel, BorderLayout.CENTER);
        
        _window.revalidate();
        _window.repaint();
    }
    private void SetWindowTitle(String windowTitle)
    {
        _window.setTitle(_windowFixedTitle + windowTitle);
    }
    private void ResizeAndCenterWindow(int width, int height)
    {
        _window.setSize(width, height);
        _window.setLocationRelativeTo(null);
    }
    public static void Instantiate()
    {
        if(Instance != null)
        {
            System.out.println("An instance of UIManager already exists");
            return;
        }
        Instance = new UIManager();
    }
}
