/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.ui;

import com.mrm.beans.Movie;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author RaghuNandan
 */
public class RentMoviePanel extends javax.swing.JPanel {

    List<JCheckBox> allcheckboxes = new ArrayList<>();
    private static JPanel topPanel = new JPanel();
    private static JPanel bottomPanel = new JPanel();

    /**
     * Creates new form RentMoviePanel
     * @param availableMovies
     */
    public RentMoviePanel(List<Movie> availableMovies) {
        initComponents();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcConleft = new GridBagConstraints();
        gbcConleft.anchor = GridBagConstraints.LINE_START;
        GridBagConstraints gbcConright = new GridBagConstraints();
        gbcConright.anchor = GridBagConstraints.WEST;
        gbcConright.gridwidth = GridBagConstraints.REMAINDER;
//        topPanel.setPreferredSize(new Dimension( 2000,200));
        JScrollPane scrollFrame = new JScrollPane(topPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        topPanel.setAutoscrolls(true);
//        scrollFrame.setPreferredSize(new Dimension( 2000,300));
        this.add(scrollFrame);
        for (Movie movie : availableMovies) {
            System.out.println(movie.getMovieName());
            JCheckBox newMovieNameBox = new JCheckBox(movie.getMovieName());
            allcheckboxes.add(newMovieNameBox);
            topPanel.add(new JCheckBox(movie.getMovieName()),gbcConleft);
            topPanel.add(new JLabel("$"+movie.getPrice()),gbcConright);
            this.validate();
            this.repaint();
        }
        JButton returnButton = new JButton("Return To Main Menu");
        JButton payButton = new JButton("Pay Now");
        JButton printButton = new JButton("Print Receipt");
        bottomPanel.add(returnButton);
        bottomPanel.add(payButton);
        bottomPanel.add(printButton);
        topPanel.validate();
        topPanel.setVisible(true);
        this.add(bottomPanel);
        
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentalMachineGUI.handleButtonClick("backToMain");
            }
        });
        
       payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalPrice=0;
                System.out.println(allcheckboxes.size());
                for(JCheckBox box:allcheckboxes){
                    System.out.println(box.isSelected());
                    if(box.isSelected()){
                        System.out.println("selected..."+box.getText());
                        for(Movie m:availableMovies){
                            if(Objects.equals(m.getMovieName(), box.getText())){
                                totalPrice+=m.getPrice();
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(RentMoviePanel.this, "Total Price : "+totalPrice);
            }
        });
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}