/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.center;

import com.mrm.beans.CreditCard;
import com.mrm.beans.Movie;
import com.mrm.beans.Transaction;
import com.mrm.db.DataProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Java class that provides services to the Movie Rental Machine
 * @author RaghuNandan
 */
public class ServiceProvider {
    /**
     * 
     */
    private DataProvider dataProviderObj;
    /**
     * Map that stores all the transaction related information
     */
    private Map<Integer,Transaction> transactions;
    private Map<Long,Integer> creditCardMap = new HashMap<>();
    /**
     * private variables for instantiating logging
     */
    private static final Logger transactionLogger = Logger.getLogger("Transaction Hanlder");
    private Handler fileHandler;
    private SimpleFormatter simpleFormatter;
    /**
     * Constructor
     */
    public ServiceProvider(){
        dataProviderObj = new DataProvider();
        transactions = new HashMap<>();
        
        try {
            //Code for handling the loggging
            fileHandler = new FileHandler("src\\com\\mrm\\handler\\center.log",true);
        } catch (IOException ex) {
            transactionLogger.log(Level.SEVERE, ex.toString());
        } catch (SecurityException ex) {
           transactionLogger.log(Level.SEVERE, ex.toString());
        }
            simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            transactionLogger.addHandler(fileHandler);
    }
    /**
     * Method that returns the movies reserved by the given card
     * @param card - user's credit card
     * @return List of reserved Movies
     */
    public List<Movie> getReservedMovies(CreditCard card){
         List<Movie> reservedMovies = new ArrayList<>();
         for(Integer transactionId: transactions.keySet()){
             Transaction tran = transactions.get(transactionId);
             if(tran.getTransactionCode()==3 && Objects.equals(tran.getCardNumber(), card.getCardNumber())){
                 reservedMovies.addAll(tran.getAllMovies());
             }
         }
         transactionLogger.log(Level.INFO,"Reserved Movies retrieved");
         return reservedMovies;
    }
    
        
    /**
     * Method that returns the movies reserved by the given card
     * @param card
     * @return 
     */
    public List<Movie> getAllAvailableMovies(){
         List<Movie> availableMovies = new ArrayList<>();
         availableMovies.addAll(dataProviderObj.getMovieData());
         availableMovies.removeAll(getAllRentedMovies());
          transactionLogger.log(Level.INFO,"All available Movies retrieved");
         return availableMovies;
    }
    
    /**
     * Method that returns the movies reserved by the given card
     * @param card
     * @return 
     */
    public List<Movie> getRentedMovies(CreditCard card){
         List<Movie> rentedMovies = new ArrayList<>();
         for(Integer transactionId: transactions.keySet()){
             Transaction tran = transactions.get(transactionId);
             if(tran.getTransactionCode()==2 && Objects.equals(tran.getCardNumber(), card.getCardNumber())){
                 rentedMovies.addAll(tran.getAllMovies());
             }
         }
          transactionLogger.log(Level.INFO,"Rented Movies retrieved");
          return rentedMovies;
    }
    
    /**
     * Method that returns the movies reserved by the given card
     * @param card
     * @return 
     */
    public List<Movie> getAllRentedMovies(){
         List<Movie> rentedMovies = new ArrayList<>();
         for(Integer transactionId: transactions.keySet()){
             Transaction tran = transactions.get(transactionId);
             if(tran.getTransactionCode()==1){
                 rentedMovies.addAll(tran.getAllMovies());
             }
         }
         return rentedMovies;
    }
    
    /**
     * Method for retrieving all the reserved movies
     * @return List of all movies reserved by various users 
     */
    public List<Movie> getAllReservedMovies(){
        List<Movie> allReservedMovies = new ArrayList<>();
        Transaction userTransaction;
         for(Integer transactionId:transactions.keySet()){
             userTransaction = transactions.get(transactionId);
             if(userTransaction.getTransactionCode()==3){
                 allReservedMovies.addAll(userTransaction.getAllMovies());
             }
         }
          transactionLogger.log(Level.INFO,"Reserved Movies retrieved");
        return allReservedMovies;
    }
    
    public DataProvider getDataProviderObj() {
        return dataProviderObj;
    }

    public void setDataProviderObj(DataProvider dataProviderObj) {
        this.dataProviderObj = dataProviderObj;
    }

    public Map<Integer, Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<Integer, Transaction> transactions) {
        this.transactions = transactions;
    }

    public Map<Long, Integer> getCreditCardMap() {
        return creditCardMap;
    }

    public void setCreditCardMap(Map<Long, Integer> creditCardMap) {
        this.creditCardMap = creditCardMap;
    }
    
    public int generateTransactionId(){
        int nextTransactionId=0;
        for(Integer tid:transactions.keySet()){
            nextTransactionId=tid;
        }
        return ++nextTransactionId;
    }
    
    public boolean addTransaction(Transaction tobj){
        try {
            transactions.put(tobj.getTransactionId(), tobj);
            List<Transaction> tlist = new ArrayList<>();
            tlist.add(tobj);
            transactionLogger.log(Level.INFO,"Transaction Logged..."+tobj.getTransactionId());
            dataProviderObj.updateTransactions(tlist);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public boolean removeHoldOnTransaction(CreditCard cardobj,List<Movie> returnedList){
        for(int tid:transactions.keySet()){
            Transaction tobj = transactions.get(tid);
            if(tobj.getCardNumber()==cardobj.getCardNumber() && tobj.getTransactionCode()==2){
                Iterator<Movie> itr = tobj.getAllMovies().listIterator();
                while(itr.hasNext()){
                    Movie m = itr.next();
                    for(Movie m2:returnedList){
                        if(m2.getMovieName()==m.getMovieName()){
                            itr.remove();
                            break;
                        }
                    }
                }
            }
        }
        transactionLogger.log(Level.INFO,"Removed hold on : "+cardobj.getCardNumber());
        return true;
    }
}
