/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.handler;

import com.mrm.beans.CreditCard;
import com.mrm.beans.Movie;
import com.mrm.beans.Transaction;
import com.mrm.center.ServiceProvider;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class for handling the transactions and controlling the movie rental machine
 * @author RaghuNandan
 */
public class TransactionHandler {
    
    /**
     * Instance variable for performing logging
     */
    private static final Logger transactionLogger = Logger.getLogger("Transaction Hanlder");
    private Handler fileHandler;
    private SimpleFormatter simpleFormatter;
    /**
     * Service Provider instance
     */
    private ServiceProvider serviceProvider = new ServiceProvider();
    
    /**
     * Constructor for initializing the instance variables
     */
    public TransactionHandler(){
        try {
            
            //Code for handling the loggging
            fileHandler = new FileHandler("src\\com\\mrm\\handler\\transactions.log",true);
            simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            transactionLogger.addHandler(fileHandler);
            
            //Populate the serviceProvide Object with data
            serviceProvider.setCreditCardMap(serviceProvider.getDataProviderObj().getCreditCardData());
            serviceProvider.getDataProviderObj().getMovieData();
            serviceProvider.setTransactions(serviceProvider.getDataProviderObj().getTransactionData());
            
        } catch (SecurityException ex) {
            transactionLogger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            transactionLogger.log(Level.SEVERE, null, ex);
        } 
    }
    
    public boolean authenticateLogin(Long creditCardNumber,Integer zipCode){
        System.out.println(creditCardNumber+": "+zipCode);
        System.out.println(serviceProvider.getCreditCardMap());
        if(serviceProvider.getCreditCardMap().containsKey(creditCardNumber)){
            System.out.println("step1");
            if(Objects.equals(serviceProvider.getCreditCardMap().get(creditCardNumber), zipCode)){
                System.out.println("step2");
                transactionLogger.log(Level.OFF,creditCardNumber+" : Logged In");
                System.out.println("Success...");
                return true;
            }
        }
        transactionLogger.log(Level.OFF, creditCardNumber+" : Unsuccessfull login attempt");
        return false;
    }

    public List<Movie> getAllAvailableMovies(){
        return serviceProvider.getAllAvailableMovies();
    }
    
    /**
     * Perform resource clean up
     */
    public void performResourceCleanUp() {
        fileHandler.close();
        serviceProvider.getDataProviderObj().perfromResourceCleanUp();
    }

    public boolean processTransaction(List<Movie> userSelectedMovies,CreditCard card,int tranCode) {
        try {
            Transaction tranObj = new Transaction();
            tranObj.setTransactionId(serviceProvider.generateTransactionId());
            tranObj.setAllMovies(userSelectedMovies);
            tranObj.setCardNumber(card.getCardNumber());
            tranObj.setTransactionCode(tranCode);
            tranObj.setTransactionStatus("Success");
            serviceProvider.addTransaction(tranObj);
            transactionLogger.log(Level.OFF, " : Transaction Success ["+userSelectedMovies+" Transaction code : "+tranCode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Movie> getAllReservedMovies(CreditCard cardObj) {
        return serviceProvider.getReservedMovies(cardObj);
    }

    public List<Movie> getAllRentedMovies(CreditCard cardObj) {
        return serviceProvider.getRentedMovies(cardObj);
    }
    
     public boolean removeHoldOnTransaction(CreditCard cardobj,List<Movie> returnedList){
         return serviceProvider.removeHoldOnTransaction(cardobj, returnedList);
     }
}
