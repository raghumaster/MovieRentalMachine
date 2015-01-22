/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.uix;

import com.mrm.beans.Movie;
import com.mrm.handler.InventoryHandler;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author RaghuNandan
 */
public class OperatorUI extends javax.swing.JPanel {
    /**
     * Creates new form OperatorUI
     */
    JFrame operatorUIFrame;
    List<JCheckBox> allcheckboxes = new ArrayList<>();
    /**
     * Inventory handler object 
     */
    InventoryHandler inventoryObj;
    
    /**
     * Constructor for initializing the UI components
     */
    public OperatorUI() {
        inventoryObj = new InventoryHandler();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initComponents();
                operatorUIFrame=new JFrame("Operator (Authorized Users Only)"); 
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                operatorUIFrame.add(OperatorUI.this);
                operatorUIFrame.setSize(420, 350);
                operatorUIFrame.setLocation(dim.width/2-operatorUIFrame.getSize().width/2, dim.height/2-operatorUIFrame.getSize().height/2);
                operatorUIFrame.setVisible(true);
                operatorUIFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                operatorUIFrame.setResizable(false);
                
                statusRadioButtonGroup.add(onMachineRadio);
                statusRadioButtonGroup.add(offMachineRadio);
                
                reservePane.setLayout(new ScrollPaneLayout());
                
                List<String> movieList = inventoryObj.getAllReservedMovies();
                
                String output="<html><ul>";
                for(String movieName:movieList){
                    output = output +"<li>"+ movieName + "</li>";
                }
                output+="</ul></html>";
                movieNamesLabel.setText(output);
                System.out.println(output);
                
                
                WindowListener exitListener = new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                       inventoryObj.performResourceCleanUp();
                    }
                };
                operatorUIFrame.addWindowListener(exitListener);
                
             
              removeMoviePanel.setLayout(new GridLayout(12,2));
              
               List<Movie> availableMovies = inventoryObj.getAllAvailableMovies();
               
                for(Movie m:availableMovies){
                    System.out.println(m.getMovieName());
                    JCheckBox checkBoxObj = new JCheckBox(m.getMovieName());
                    allcheckboxes.add(checkBoxObj);
                    removeMoviePanel.add(checkBoxObj);
                }
            
                List<Movie> rentedMovies = inventoryObj.getAllRentedMovies();
                jLabel3.setText("Rented Movies :  "+rentedMovies.size() );
                jLabel2.setText("Available Movies :  "+availableMovies.size());
                jLabel8.setText("Reserved Movies :  "+movieList.size());
                
             JButton removeButton = new JButton();
             removeMoviePanel.add(removeButton);
             removeButton.setText("Remove Movies ");
            removeButton.addActionListener(new ActionListener() {
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
                JOptionPane.showMessageDialog(removeMoviePanel, "Total Price : "+totalPrice);
            }
        });
                
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

        statusRadioButtonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        operatorUITabbedPane = new javax.swing.JTabbedPane();
        statusPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        onMachineRadio = new javax.swing.JRadioButton();
        offMachineRadio = new javax.swing.JRadioButton();
        saveExitButton = new javax.swing.JButton();
        viewLogButton = new javax.swing.JButton();
        addMoviePane = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        movieName = new javax.swing.JTextField();
        moviePrice = new javax.swing.JTextField();
        addMovieButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        reservePane = new javax.swing.JScrollPane();
        movieNamesLabel = new javax.swing.JLabel();
        removeMoviePanel = new javax.swing.JPanel();

        jLabel1.setText("Machine Status : ");

        jLabel2.setText("Available Movies :");

        jLabel3.setText("Rented Movies :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 51));
        jLabel4.setText("Summary");

        jLabel8.setText("Reserved Movies :");

        onMachineRadio.setSelected(true);
        onMachineRadio.setText("ON");

        offMachineRadio.setText("OFF");
        offMachineRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offMachineRadioActionPerformed(evt);
            }
        });

        saveExitButton.setText("Save & Exit");
        saveExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveExitButtonActionPerformed(evt);
            }
        });

        viewLogButton.setText("View Log");
        viewLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewLogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statusPaneLayout = new javax.swing.GroupLayout(statusPane);
        statusPane.setLayout(statusPaneLayout);
        statusPaneLayout.setHorizontalGroup(
            statusPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(statusPaneLayout.createSequentialGroup()
                .addGroup(statusPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPaneLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(onMachineRadio)
                        .addGap(18, 18, 18)
                        .addComponent(offMachineRadio))
                    .addGroup(statusPaneLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(saveExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(viewLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(statusPaneLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(statusPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)))
                    .addGroup(statusPaneLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel4)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        statusPaneLayout.setVerticalGroup(
            statusPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPaneLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(statusPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(onMachineRadio)
                    .addComponent(offMachineRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addGroup(statusPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewLogButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(saveExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        operatorUITabbedPane.addTab("STATUS", statusPane);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("ADD NEW MOVIE");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Movie Name * : ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Price * :");

        addMovieButton.setText("ADD");
        addMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMovieButtonActionPerformed(evt);
            }
        });

        resetButton.setText("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addMoviePaneLayout = new javax.swing.GroupLayout(addMoviePane);
        addMoviePane.setLayout(addMoviePaneLayout);
        addMoviePaneLayout.setHorizontalGroup(
            addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviePaneLayout.createSequentialGroup()
                .addGroup(addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addMoviePaneLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(27, 27, 27)
                        .addGroup(addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(movieName, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moviePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addMoviePaneLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel5))
                    .addGroup(addMoviePaneLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(addMovieButton)
                        .addGap(18, 18, 18)
                        .addComponent(resetButton)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        addMoviePaneLayout.setVerticalGroup(
            addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMoviePaneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(movieName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(moviePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addMoviePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addMovieButton)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        operatorUITabbedPane.addTab("ADD MOVIE", addMoviePane);

        reservePane.setViewportView(movieNamesLabel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reservePane, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reservePane, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
        );

        operatorUITabbedPane.addTab("RESERVED MOVIES", jPanel3);

        javax.swing.GroupLayout removeMoviePanelLayout = new javax.swing.GroupLayout(removeMoviePanel);
        removeMoviePanel.setLayout(removeMoviePanelLayout);
        removeMoviePanelLayout.setHorizontalGroup(
            removeMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        removeMoviePanelLayout.setVerticalGroup(
            removeMoviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        operatorUITabbedPane.addTab("REMOVE MOVIE", removeMoviePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(operatorUITabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(operatorUITabbedPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Retrieve the form data and add new movie
     * @param evt 
     */
    private void addMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMovieButtonActionPerformed
        if(movieName.getText().equals("")){
            JOptionPane.showMessageDialog(addMoviePane,"Please enter a valid movie name");
        }else if(moviePrice.getText().equals("")){
            JOptionPane.showMessageDialog(addMoviePane,"Please enter a movie price");
        }else{
            try {
                Integer price = Integer.parseInt(moviePrice.getText());
                if(inventoryObj.addNewMovie(movieName.getText(),price )){
                    JOptionPane.showMessageDialog(addMoviePane,"Movie Added Successfully");
                }else{
                    JOptionPane.showMessageDialog(addMoviePane,"Error, Please try again");
                }
            } catch (Exception e) {
                moviePrice.setText("");
                JOptionPane.showMessageDialog(addMoviePane,"Please enter a valid movie price (Numbers 0-9)");
            }
        }
    }//GEN-LAST:event_addMovieButtonActionPerformed
    
    /**
     * Method for reseting the fields 
     * @param evt 
     */
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        movieName.setText("");
        moviePrice.setText("");
    }//GEN-LAST:event_resetButtonActionPerformed

    /**
     * Option to turn off the machine confirm the turn off of the machine
     * @param evt 
     */
    private void offMachineRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offMachineRadioActionPerformed
        int option = JOptionPane.showConfirmDialog(statusPane, "Do you want to Turn off the machine ?");
        if(option ==1 || option == 2){
            offMachineRadio.setSelected(false);
            onMachineRadio.setSelected(true);
        }
    }//GEN-LAST:event_offMachineRadioActionPerformed

    private void saveExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveExitButtonActionPerformed
        operatorUIFrame.dispose();
        if(onMachineRadio.isSelected()){
            System.out.println("Machine ON");
            new RentalMachineGUI(1);
        }else{
            System.out.println("Machine turned Off");
             new RentalMachineGUI(0);
        }
    }//GEN-LAST:event_saveExitButtonActionPerformed

    private void viewLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewLogButtonActionPerformed
       JOptionPane.showMessageDialog(operatorUIFrame,inventoryObj.getTransactionLog());
    }//GEN-LAST:event_viewLogButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMovieButton;
    private javax.swing.JPanel addMoviePane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField movieName;
    private javax.swing.JLabel movieNamesLabel;
    private javax.swing.JTextField moviePrice;
    private javax.swing.JRadioButton offMachineRadio;
    private javax.swing.JRadioButton onMachineRadio;
    private javax.swing.JTabbedPane operatorUITabbedPane;
    private javax.swing.JPanel removeMoviePanel;
    private javax.swing.JScrollPane reservePane;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveExitButton;
    private javax.swing.JPanel statusPane;
    private javax.swing.ButtonGroup statusRadioButtonGroup;
    private javax.swing.JButton viewLogButton;
    // End of variables declaration//GEN-END:variables
}
