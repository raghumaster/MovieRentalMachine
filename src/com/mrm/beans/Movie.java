/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.beans;

/**
 *
 * @author RaghuNandan
 */
public class Movie {
    private String movieName;
    private int price;
    /**
     * Variable for storing the status of the movie
     * 1-Available 2-Rented
     */
    private int status;

    public Movie(String movieName,int price){
        this.movieName=movieName;
        this.price=price;
    }
    
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return "["+movieName+" : "+price+"]";
    }
}
