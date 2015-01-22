/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.beans;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RaghuNandan
 */
public class Transaction {
    //Each transaction is identified by a unique id
    private int transactionId;
    /**Internally used by the system to differentiate the transactions
    *Code=1 Rent Movie
    *Code=2 Return Movie
    *Code=3 Reserve Movie
    */
    private int transactionCode;
    //Store the status of the transaction
    //Success/NetworkFailure/AbortedbyUser/AuthenticationFailure
    private String transactionStatus;
    //List for storing all the movies associated with the transaction
    private List<Movie> allMovies;
    //variable for storing the card number
    private long cardNumber;

    public Transaction(){
        //Get the unique transactionId from ServiceProvider class
        allMovies = new ArrayList<>();
    }
    
    public int getTransactionId() {
        return transactionId;
    }

    public int getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(int transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public List<Movie> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(List<Movie> allMovies) {
        this.allMovies = allMovies;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public String toString(){
        return "["+cardNumber+" : #"+transactionCode+" - "+transactionStatus+" - "+allMovies+"]";
    }
    
}
