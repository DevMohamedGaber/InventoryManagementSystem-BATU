/*
    This class is only responsible for calling diffrent frames from the same place
    each frame will have a function here to call it
    if a frame have a certain rules to be called this is the place to check if those rules are met or not
*/
package Core;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;
import Presentation.DashboardWindow;
import Presentation.HomePage;
import Presentation.LoginWindow;
import Presentation.Users.AddUser;
import Presentation.Users.ListUsers;

public class UIManager
{
    public static UIManager Instance; // Singleton instance
    
    private final String _windowFixedTitle = "BATU Inventory Management System - ";
    private final JFrame _window; // Window container
    private JPanel _currentPanel; // current window loaded
    private JPanel _routerPanel; // page container (width=875,height=624)
    private JPanel _currentSubPanel; // current page loaded

    // constractor to setup the default window (_window)
    public UIManager()
    {
        _window = new JFrame();
        _window.setTitle("BATU - Inventory Management System");
        _window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _window.setResizable(false);
        _window.setVisible(true);
    }
    
    /**** Window Control ****/
    public void LoadLoginWindow()
    {
        ResizeAndCenterWindow(390, 240);
        LoadPanelToWindow(new LoginWindow(), true);
        _window.setTitle("Login");
    }
    public void LoadDashboardWindow()
    {
        ResizeAndCenterWindow(1200, 680);
        SetWindowTitle("Home");
        LoadPanelToWindow(new DashboardWindow(), false);
        // get dashboard router panel
        for(Component component : _currentPanel.getComponents())
        {
            if(component.getName() != null && component.getName().equals("dashboardRouterPanel"))
            {
                _routerPanel = (JPanel)component;
                _routerPanel.setLayout(new BorderLayout());
                break;
            }
        }
        GoToHomePage();
    }
    
    /**** Page Control ****/
    public void GoToHomePage() {
        LoadSubPanelToWindow(new HomePage());
    }
    
    // User Pages
    public void GoToUsersListPage() {
        LoadSubPanelToWindow(new ListUsers());
    }
    public void GoToAddUserPage() {
        LoadSubPanelToWindow(new AddUser());
    }
    
    /**** Helpers ****/
    private void LoadPanelToWindow(JPanel panel, boolean repaint)
    {
        if(_currentPanel != null)
        {
            _window.remove(_currentPanel);
        }
        _currentPanel = panel;
        _window.add(_currentPanel, BorderLayout.CENTER);
        if(repaint)
        {
            RepaintWindow();
        }
    }
    private void LoadSubPanelToWindow(JPanel panel)
    {
        _routerPanel.removeAll();
        _currentSubPanel = panel;
        _routerPanel.add(_currentSubPanel);
        RepaintWindow();
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
    private void RepaintWindow()
    {
        _window.revalidate();
        _window.repaint();
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
