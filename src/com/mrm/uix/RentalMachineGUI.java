/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.uix;

import com.mrm.handler.TransactionHandler;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * Main Controller GUI class that controls various GUI windows
 * @author RaghuNandan
 */
public class RentalMachineGUI {
    /**
     * Static instance variables for displaying the UI
     */
    private static JFrame rentalMachineFrame = new JFrame();
    private static JLayeredPane lpane = new JLayeredPane();
    private JPanel outerPanel;
    /**
     * Transaction Handler Object
     */
    private static TransactionHandler transactionObj;
    /**
     * Constructor
     * @param status 1-ON 0-OFF 
     */
    public RentalMachineGUI(int status)
    {      
        outerPanel = new RentalMachineTopPanel(status);
        transactionObj = new TransactionHandler();
        rentalMachineFrame.setSize(590, 590);
        rentalMachineFrame.setPreferredSize(new Dimension(590, 590));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        rentalMachineFrame.setLocation(dim.width/2-rentalMachineFrame.getSize().width/2, dim.height/2-rentalMachineFrame.getSize().height/2);
        rentalMachineFrame.setLayout(new BorderLayout());
        rentalMachineFrame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 570, 570);
        outerPanel.setBounds(0, 0, 570, 570);
        outerPanel.setOpaque(true);
//        innerPanel.setBounds(40, 80, 500, 340);
//        innerPanel.setOpaque(true);
        lpane.add(outerPanel, new Integer(0), 0);
//        lpane.add(innerPanel, new Integer(1), 0);
        rentalMachineFrame.pack();
        rentalMachineFrame.setVisible(true);
        rentalMachineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new RentalMachineGUI(1);
    }
}
