package com.mrm.handler;

import com.mrm.beans.Movie;
import com.mrm.center.ServiceProvider;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Java class for handling the inventory in the movie rental machine
 * Used by the operatorUI to update the machine status/load or remove movies
 * @author RaghuNandan
 */
public class InventoryHandler {
    /**
     * Instance variable for performing logging
     */
    private static final Logger inventoryLogger = Logger.getLogger("Transaction Hanlder");
    private Handler inventoryFileHandler;
    private SimpleFormatter simpleFormatter;
    
    /**
     * Service Provider instance
     */
    private ServiceProvider serviceProvider = new ServiceProvider();
    
    /**
     * Constructor for initializing the instance variables
     */
    public InventoryHandler(){
        try {
            
            //Code for handling the loggging
            inventoryFileHandler = new FileHandler("src\\com\\mrm\\handler\\machinestatus.log",true);
            simpleFormatter = new SimpleFormatter();
            inventoryFileHandler.setFormatter(simpleFormatter);
            inventoryLogger.addHandler(inventoryFileHandler);
            
            //Populate the serviceProvide Object with data
            serviceProvider.getDataProviderObj().getCreditCardData();
            serviceProvider.getDataProviderObj().getMovieData();
            serviceProvider.setTransactions(serviceProvider.getDataProviderObj().getTransactionData());
            
        }  catch (SecurityException ex) {
            inventoryLogger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            inventoryLogger.log(Level.SEVERE, null, ex);
        } 
        
    }
    
    public List<String> getAllReservedMovies(){
        List<String> movieNames = new ArrayList<>();
        List<Movie> movieList = serviceProvider.getAllReservedMovies();
        for(Movie movie:movieList){
            movieNames.add(movie.getMovieName());
        }
        inventoryLogger.log(Level.INFO,"Reserved movies data retrieved");
        return movieNames;
    }
    
    /**
     * Method for adding new movie to the database
     * @return 
     */
    public boolean addNewMovie(String movieName,Integer price){
        Movie newMovie = new Movie(movieName,price);
        List<Movie> newMovieList = new ArrayList<>();
        newMovieList.add(newMovie);
        try {
           serviceProvider.getDataProviderObj().addNewMoviesToXML(newMovieList);
           serviceProvider.getDataProviderObj().getMovieData();
           return true; 
        } catch (Exception e) {
            inventoryLogger.log(Level.INFO,"Add movie operation failed");
            return false;
        }
    }
    
    /**
     * Method for performing explicit resource clean up
     * Close file handlers and other streams
     */
    public void performResourceCleanUp(){
//        inventoryFileHandler.close();
        serviceProvider.getDataProviderObj().perfromResourceCleanUp();
    }
    
    public List<Movie> getAllAvailableMovies(){
        return serviceProvider.getAllAvailableMovies();
    }

    public List<Movie> getAllRentedMovies() {
        return serviceProvider.getAllRentedMovies();
    }
    
    public String getTransactionLog(){
        String output="",temp="";
        try {
            FileReader fileReader = new FileReader("src\\com\\mrm\\handler\\transactions.log");
            BufferedReader br = new BufferedReader(fileReader);        
            while((temp=br.readLine())!=null){
                output += temp+'\n';
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InventoryHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InventoryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
}
