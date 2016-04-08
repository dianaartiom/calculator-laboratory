package com.company;

/**
 * Created by diana on 4/8/16.
 */


import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {


    private static MainWindow ourInstance = new MainWindow();
    public static MainWindow getInstance() {
        return ourInstance;
    }
    private static String widowTitle = "Calculator";

    private MainWindow() {
        super(widowTitle);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                windowSetups();
            }
        });
    }
    private void windowSetups(){
        this.setContentPane(StartWidnow.getInstance().mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(430,240));
        this.setResizable(false);
        this.setVisible(true);
    }
}