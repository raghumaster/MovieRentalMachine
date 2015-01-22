package com.mrm.uix;

import com.mrm.beans.CreditCard;
import com.mrm.beans.Movie;
import com.mrm.handler.TransactionHandler;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Java class for displaying the Rental Machine UI Panel
 * @author RaghuNandan
 */
public class RentalMachineTopPanel extends javax.swing.JPanel {
    /**
     * Transaction Handler object
     */
    private TransactionHandler tHandlerObj;
     List<JCheckBox> allcheckboxes = new ArrayList<>();
     List<JCheckBox> allreturnboxes = new ArrayList<>();
      CreditCard cardObj = new CreditCard();
     StringBuffer printReceipt=new StringBuffer("You have not made any transaction");
     StringBuffer dispenseMessage=new StringBuffer("Enjoy Your Movies, Thank You !");
     
    JPanel rentMoviePanel = new JPanel();
    JPanel reserveMoviePanel = new JPanel();
    JPanel returnMoviePanel = new JPanel();
    
    Date currentTime = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    /**
     * Creates new form RentalMachineUI
     * @param status
     */
    public RentalMachineTopPanel(int status) {
        tHandlerObj = new TransactionHandler();
        initComponents();
 
        rentMovieButton.setEnabled(false);
        reserveMovieButton.setEnabled(false);
        returnMovieButton.setEnabled(false);
        dispenseButton.setEnabled(false);
        printReceiptButton.setEnabled(false);
        
        if(status==1){
            machineStatusToggleButton.setBackground(Color.GREEN);
            ccNumberField.requestFocus();
        }else{
            machineStatusToggleButton.setBackground(Color.RED);
            validateButton.setEnabled(false);
            ccNumberField.setEnabled(false);
            zipCodeField.setEnabled(false);
            printReceiptButton.setEnabled(false);
            dispenseButton.setEnabled(false);
            rentMovieButton.setEnabled(false);
            reserveMovieButton.setEnabled(false);
            returnMovieButton.setEnabled(false);
        }
        
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               tHandlerObj.performResourceCleanUp();
            }
        };
        
        ChangeListener changeListener = new ChangeListener() {
            
         public void stateChanged(ChangeEvent changeEvent) {
        jTabbedPane1 = (JTabbedPane) changeEvent.getSource();
        int index = jTabbedPane1.getSelectedIndex();
        System.out.println("Tab changed to: " + jTabbedPane1.getTitleAt(index));
        if(index==1){
            paintRentAMoviePanel();
        }else if(index ==2){
            paintReturnMoviePanel();
        }else if(index ==3){
            paintReserveMoviePanel();
        }else{
            jTabbedPane1.setSelectedIndex(0);
        }
      }
    };
        jTabbedPane1.addChangeListener(changeListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        machineStatusToggleButton = new javax.swing.JToggleButton();
        machineStatus = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        ccNumberField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        zipCodeField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        validateButton = new javax.swing.JButton();
        printReceiptButton = new javax.swing.JButton();
        dispenseButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rentMovieButton = new javax.swing.JButton();
        returnMovieButton = new javax.swing.JButton();
        reserveMovieButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        machineStatusToggleButton.setText("ON/OFF");
        machineStatusToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machineStatusToggleButtonActionPerformed(evt);
            }
        });

        machineStatus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        machineStatus.setText("Movie Rental Machine");

        ccNumberField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1.setText("CREDITCARD NUMBER * :");

        zipCodeField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setText("ENTER ZIP CODE *  : ");

        validateButton.setText("Validate");
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });

        printReceiptButton.setText("Print Receipt");
        printReceiptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptButtonActionPerformed(evt);
            }
        });

        dispenseButton.setText("Dispense & Exit");
        dispenseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispenseButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("WELCOME");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("PLEASE ENTER VALID CREDIT CARD DETAILS TO PROCEED ..!!");

        rentMovieButton.setText("1. RENT A MOVIE");
        rentMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentMovieButtonActionPerformed(evt);
            }
        });

        returnMovieButton.setText("2. RETURN MOVIE");
        returnMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnMovieButtonActionPerformed(evt);
            }
        });

        reserveMovieButton.setText("3. RESERVE MOVIE");
        reserveMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveMovieButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rentMovieButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnMovieButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reserveMovieButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(30, 30, 30)
                .addComponent(rentMovieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(returnMovieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reserveMovieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(machineStatus)
                        .addGap(76, 76, 76)
                        .addComponent(machineStatusToggleButton)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dispenseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(printReceiptButton, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ccNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(zipCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(validateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(machineStatusToggleButton)
                    .addComponent(machineStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ccNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dispenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zipCodeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(printReceiptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void machineStatusToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machineStatusToggleButtonActionPerformed
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"LOGIN", "CANCEL"};
        int option = JOptionPane.showOptionDialog(null, panel, "Operator Login",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0) // pressing OK button
        {
            char[] password = pass.getPassword();
            String pwd = new String(password);
            System.out.println("Your password is: " + new String(password));
            if(pwd.equals("farid")){
                new OperatorUI();
            }else{
                JOptionPane.showMessageDialog(panel, "Invalid Password");
            }
        }
    }//GEN-LAST:event_machineStatusToggleButtonActionPerformed

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateButtonActionPerformed
        Long cardNumber=null;
        Integer zipCode=null;
        if(ccNumberField.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter a valid card number");
            ccNumberField.setText("");
            ccNumberField.setFocusable(true);
        }else if(zipCodeField.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Enter a valid zip code");
            zipCodeField.setText("");
            zipCodeField.setFocusable(true);
        }else{
            try {
                cardNumber=Long.parseLong(ccNumberField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please Enter a valid Numerical Input");
                ccNumberField.setText("");
                ccNumberField.setFocusable(true);
            }
            if(cardNumber != null){
                try {
                    zipCode=Integer.parseInt(zipCodeField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please Enter a valid zip Input");
                     zipCodeField.setText("");
                     zipCodeField.setFocusable(true);
                }
            }
            if(zipCode != null && cardNumber != null){
                if(tHandlerObj.authenticateLogin(cardNumber, zipCode)){
                    cardObj.setCardNumber(cardNumber);
                    cardObj.setZipCode(zipCode);
                    populatePanels(cardObj); 
                    zipCodeField.setEditable(false);
                    ccNumberField.setEditable(false);
                    rentMovieButton.setEnabled(true);
                    reserveMovieButton.setEnabled(true);
                    returnMovieButton.setEnabled(true);
                    dispenseButton.setEnabled(true);
                    printReceiptButton.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(this, "Invalid Credit card information, Please Re-enter");
                    zipCodeField.setText("");
                    ccNumberField.setText("");
                    ccNumberField.setFocusable(true);
                }
            }
        }
        
    }//GEN-LAST:event_validateButtonActionPerformed

    private void reserveMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveMovieButtonActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        paintReserveMoviePanel();
    }//GEN-LAST:event_reserveMovieButtonActionPerformed

    private void returnMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnMovieButtonActionPerformed
       jTabbedPane1.setSelectedIndex(2);
       paintReturnMoviePanel();
    }//GEN-LAST:event_returnMovieButtonActionPerformed

    private void rentMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentMovieButtonActionPerformed
       jTabbedPane1.setSelectedIndex(1);
       paintRentAMoviePanel();
    }//GEN-LAST:event_rentMovieButtonActionPerformed

    private void printReceiptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceiptButtonActionPerformed
        JOptionPane.showMessageDialog(this, printReceipt);
    }//GEN-LAST:event_printReceiptButtonActionPerformed

    private void dispenseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispenseButtonActionPerformed
         JOptionPane.showMessageDialog(this, dispenseMessage);
         new RentalMachineGUI(1);
    }//GEN-LAST:event_dispenseButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ccNumberField;
    private javax.swing.JButton dispenseButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel machineStatus;
    private javax.swing.JToggleButton machineStatusToggleButton;
    private javax.swing.JButton printReceiptButton;
    private javax.swing.JButton rentMovieButton;
    private javax.swing.JButton reserveMovieButton;
    private javax.swing.JButton returnMovieButton;
    private javax.swing.JButton validateButton;
    private javax.swing.JTextField zipCodeField;
    // End of variables declaration//GEN-END:variables

    private void populatePanels(CreditCard cardObj) {
        
       jTabbedPane1.add("RENT A MOVIE", rentMoviePanel);
        jTabbedPane1.add("RETURN A MOVIE",returnMoviePanel);
        jTabbedPane1.add("RESERVE A MOVIE",reserveMoviePanel);
  
    }
    
    
    public void paintRentAMoviePanel(){
        rentMoviePanel.removeAll();
         rentMoviePanel.setLayout(new GridLayout(12,2));
          
        
        List<Movie> availableMovies = tHandlerObj.getAllAvailableMovies();

         for(Movie m:availableMovies){
             JCheckBox checkBoxObj = new JCheckBox(m.getMovieName());
             allcheckboxes.add(checkBoxObj);
             rentMoviePanel.add(checkBoxObj);
         }
            
                
        JButton removeButton = new JButton();
        rentMoviePanel.add(removeButton);
        removeButton.setText("Pay Now");
        removeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Movie> userSelectedMovies= new ArrayList<>();
            int totalPrice=0;
            System.out.println(allcheckboxes.size());
            for(JCheckBox box:allcheckboxes){
                if(box.isSelected()){
                    for(Movie m:availableMovies){
                        if(Objects.equals(m.getMovieName(), box.getText())){
                            totalPrice+=m.getPrice();
                            userSelectedMovies.add(new Movie(m.getMovieName(),m.getPrice()));
                        }
                    }
                }
            }
            int choice=JOptionPane.showConfirmDialog(rentMoviePanel, "Total Price : "+totalPrice+", Do you want to proceed ?");
                if(choice==0){
                    if(tHandlerObj.processTransaction(userSelectedMovies,cardObj,1)){
                        JOptionPane.showMessageDialog(rentMoviePanel, "Payment processed Successfully"+'\n'+'\n'+"Press Dispense button to collect the movie");
                        if(printReceipt.equals("You have not made any transaction")){
                            System.out.println("Clearing the buffer...............................");
                            printReceipt.setLength(0);
                        }    
                        printReceipt.append("Rented Movies"+'\n');
                        for(Movie movie:userSelectedMovies){
                            printReceipt.append(movie.getMovieName()+":"+movie.getPrice());
                            printReceipt.append('\n');
                            printReceipt.append(dateFormat.format(currentTime));
                            printReceipt.append('\n');
                        }
                        jTabbedPane1.setSelectedIndex(0);
                    }else{
                        JOptionPane.showMessageDialog(rentMoviePanel, "Error while Processing transaction");
                    }        
                }else{
                    
                }
            }
         });
        
       JLabel resLabel = new JLabel();
       String resOutput="";
       List<Movie> reservedMovieList = tHandlerObj.getAllReservedMovies(cardObj);
       if(reservedMovieList==null){
           resLabel.setText("You Have not reserved any movies");
       }else{
           resOutput+="<html><ul>";
           for(Movie m:reservedMovieList){
              resOutput+="<li>"+m.getMovieName()+"</li>"; 
           }
           resOutput+="</ul></html>";
           resLabel.setText(resOutput);
           reserveMoviePanel.add(resLabel);
       }
    }
    
    public void paintReserveMoviePanel(){
        reserveMoviePanel.removeAll();
        JLabel newResLabel = new JLabel("Movie Name * :  ");
       JTextField newResMovieText=  new JTextField(30);
       JButton newResButton = new JButton("Reserve");
       reserveMoviePanel.add(newResLabel);
       reserveMoviePanel.add(newResMovieText);
       reserveMoviePanel.add(newResButton);
       
       newResButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newResMovieText.getText()==""){
                    JOptionPane.showMessageDialog(rentMovieButton, "Move name cannot be empty");
                }else{
                    List<Movie> userReservedMovies = new ArrayList<>();
                    userReservedMovies.add(new Movie(newResMovieText.getText(),0));
                    if(tHandlerObj.processTransaction(userReservedMovies, cardObj, 3)){
                     JOptionPane.showMessageDialog(rentMoviePanel, "Movie Reserved Successfully");
                        if(Objects.equals(printReceipt.toString(),"You have not made any transaction")){
                            printReceipt.setLength(0);
                        }    
                        printReceipt.append("Reserved Movies"+'\n');
                        for(Movie movie:userReservedMovies){
                            printReceipt.append(movie.getMovieName());
                            printReceipt.append('\n');
                            printReceipt.append(dateFormat.format(currentTime));
                            printReceipt.append('\n');
                        }
                        jTabbedPane1.setSelectedIndex(0);
                    }else{
                        JOptionPane.showMessageDialog(rentMoviePanel, "Error while Processing transaction");
                    }  
                }
            }
        });
    }
    
    
    public void paintReturnMoviePanel(){
       returnMoviePanel.removeAll();
//       returnMoviePanel.setLayout(new GridLayout(12,2));
       
       JLabel rentLabel = new JLabel();
       List<Movie> rentedMovieList = tHandlerObj.getAllRentedMovies(cardObj);
       if(rentedMovieList==null){
           rentLabel.setText("You Have not rented any movies");
           returnMoviePanel.add(rentLabel);
       }else{
           for(Movie m:rentedMovieList){
             JCheckBox checkBoxObj = new JCheckBox(m.getMovieName());
             allreturnboxes.add(checkBoxObj);
             returnMoviePanel.add(checkBoxObj);
           }
           returnMoviePanel.add(rentLabel);
       }
        
        JButton returnButton = new JButton();
        returnMoviePanel.add(returnButton);
        returnButton.setText("Return Now");
        returnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Movie> userSelectedMovies= new ArrayList<>();
            int totalPrice=0;
            System.out.println(allreturnboxes.size());
            for(JCheckBox box:allreturnboxes){
                if(box.isSelected()){
                    for(Movie m:rentedMovieList){
                        if(Objects.equals(m.getMovieName(), box.getText())){
                            totalPrice+=m.getPrice();
                            userSelectedMovies.add(new Movie(m.getMovieName(),m.getPrice()));
                        }
                    }
                }
            }
            int choice=JOptionPane.showConfirmDialog(returnMoviePanel, "Do you wish to return now ?");
                if(choice==0){
                    if(tHandlerObj.processTransaction(userSelectedMovies,cardObj,1)){
                        tHandlerObj.removeHoldOnTransaction(cardObj, userSelectedMovies);
                        JOptionPane.showMessageDialog(returnMoviePanel, "Movie Returned Successfull");
                        if(Objects.equals(printReceipt.toString(),"You have not made any transaction")){
                            printReceipt.setLength(0);
                        }    
                        printReceipt.append("Returned Movies"+'\n');
                        for(Movie movie:userSelectedMovies){
                            printReceipt.append(movie.getMovieName()+":"+movie.getPrice());
                            printReceipt.append('\n');
                            printReceipt.append(dateFormat.format(currentTime));
                            printReceipt.append('\n');
                        }
                        jTabbedPane1.setSelectedIndex(0);
                    }else{
                        JOptionPane.showMessageDialog(rentMoviePanel, "Error while Processing transaction, Please try again");
                    }        
                }else{
                    
                }
            
        }}); 
    }
}