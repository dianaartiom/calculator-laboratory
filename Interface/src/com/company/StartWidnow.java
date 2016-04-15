package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by diana on 4/8/16.
 */
public class StartWidnow implements ActionListener {
    private static StartWidnow instance = new StartWidnow();
    public static StartWidnow getInstance(){return instance;}

    public JPanel mainPanel;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton leftPar;
    private JButton rightPar;
    private JButton b4;
    private JButton b1;
    private JButton signInvers;
    private JButton b5;
    private JButton b6;
    private JButton add;
    private JButton mul;
    private JButton b2;
    private JButton b3;
    private JButton sub;
    private JButton div;
    private JButton b0;
    private JButton point;
    private JButton result;
    private JButton pow;
    private JTextField textField1;
    private JButton remove;
    private Calculator calculator = new Calculator();

    public StartWidnow() {
        windowsetups();
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText().length() != 0) {
                    textField1.setText(calculator.parse(textField1.getText()));
                }
            }
        });

        pow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "^");
            }
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
            }
        });

        signInvers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(textField1.getText().contains("-") || textField1.getText().contains("+") || textField1.getText().contains("(") ||textField1.getText().contains(")") || textField1.getText().contains("^")))
                textField1.setText(String.valueOf(0 - Float.parseFloat(textField1.getText())));
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        JButton bt = (JButton)e.getSource();
        //if(textField1.getText(textField1.getText().length()-1) == );
        textField1.setText(textField1.getText() + bt.getText());
    }



    private void windowsetups(){
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b0.addActionListener(this);
        leftPar.addActionListener(this);
        rightPar.addActionListener(this);
        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        point.addActionListener(this);
    }
}
