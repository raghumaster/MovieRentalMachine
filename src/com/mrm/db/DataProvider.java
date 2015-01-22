package com.mrm.db;

import com.mrm.beans.Movie;
import com.mrm.beans.Transaction;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Java class used for extracting and persisting data from/to XML files
 * Contains utility methods for getting and setting new data
 * @author RaghuNandan
 */
public class DataProvider {
    /**
     * Private instance variables for pointing to XML files in the database
     */
    private final File cardXMLFile;
    private final File movieXMLFile;
    private final File transactionXMLFile;
    /**
     * Private instance variables for parsing XML files
     */
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    /**
     * Private instance variables for modifying XML files
     */
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DOMSource source;
    private StreamResult result;
    /**
     * Logger object for logging all the events/Information/exceptions 
     * Logs are stored in "XML-Database.log" file
     */
    private static final Logger LOGGER = Logger.getLogger("DBAccessUpdateLogger");
    private Handler logFileHandler;
    
    /**
    *   Constructor
    *   Initialize the instance variables of the class
    **/
    public DataProvider(){
        cardXMLFile = new File("src\\com\\mrm\\db\\CreditCardData.xml");
        movieXMLFile = new File("src\\com\\mrm\\db\\MovieData.xml");
        transactionXMLFile = new File("src\\com\\mrm\\db\\Transactions.xml");
        try {
            //Code for initializing the document builder factory
            //For reading data from the xml file
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            //Code for instantiating the transformer factory
            //For appending/modifying content of the xml files
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            //Code for instantiating the loggers and relevant handlers
            logFileHandler = new FileHandler("src\\com\\mrm\\db\\XML-Database.log",true);
            LOGGER.addHandler(logFileHandler);
            logFileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (ParserConfigurationException | TransformerConfigurationException ex) {
            LOGGER.log(Level.SEVERE, "Exception Occured", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception Occured", ex);
        } 
    }
    
    /**
     * Method that retrieves card data from XML file
     * @return 
    **/
    public Map<Long,Integer> getCreditCardData() {
        Map<Long,Integer> cardMap = new HashMap<>();
        Long key;
        Integer value;
        try {
            Document doc = dBuilder.parse(cardXMLFile);   
            doc.getDocumentElement().normalize();            
            NodeList nList = doc.getElementsByTagName("card");        
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);      
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element)nNode;
                    key = Long.parseLong(eElement.getElementsByTagName("number").item(0).getTextContent());
                    value = Integer.parseInt(eElement.getElementsByTagName("zipcode").item(0).getTextContent());
                   cardMap.put(key,value);
                }
            }
            LOGGER.log(Level.INFO, "Credit Card Data retrieved");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception Occured", ex);
        }
        return cardMap;
    }
    
    /**
     * Method that retrieves movie data from XML file
     * @param 
     * @return 
    **/
    public Set<Movie> getMovieData() {
        Set<Movie> movieInfo = new HashSet<>();
        String key;
        Integer value;
        try {
            Document doc = dBuilder.parse(movieXMLFile);   
            doc.getDocumentElement().normalize();            
            NodeList nList = doc.getElementsByTagName("movie");        
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);      
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element)nNode;
                    key = eElement.getElementsByTagName("name").item(0).getTextContent();
                    value = Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent());
                    movieInfo.add(new Movie(key,value));
                }
            }
            LOGGER.log(Level.INFO, "Movie Data retrieved");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception Occured", ex);
            return null;
        }
        return movieInfo;
    }
    
    /**
     * Method for adding a new movies to the XML file
     * @param newMoviesList - Contains a list of newly added movies
     * @return true if the movie added successfully false otherwise
     */
    public boolean addNewMoviesToXML(List<Movie> newMoviesList){
        try {
            if(newMoviesList.isEmpty()){
                LOGGER.log(Level.INFO, "Empty List Passed as Parameter");
                return false;
            }else{
                Document doc = dBuilder.parse(movieXMLFile); 
                Element root = doc.getDocumentElement();
                for(Movie movie:newMoviesList){
                    Element newMovie = doc.createElement("movie");
                    Element movieName = doc.createElement("name");
                    movieName.setTextContent(movie.getMovieName());
                    newMovie.appendChild(movieName);
                    Element moviePrice = doc.createElement("price");
                    moviePrice.setTextContent(Integer.toString(movie.getPrice()));
                    newMovie.appendChild(moviePrice);
                    root.appendChild(newMovie);
                }
                source = new DOMSource(doc);
                result = new StreamResult(movieXMLFile);
                transformer.transform(source, result);
                LOGGER.log(Level.INFO, "New Movies Added");
                return true;
            }
        } catch (SAXException | IOException | DOMException | TransformerException ex) {
            LOGGER.log(Level.SEVERE, "Exception Occured", ex);
            return false;
        }
    }
    
    /**
     * Remove a given Movie from the database
     * @param oldMovies - list that contains the movie objects to be removed
     * @return true - if the deletion is successful false otherwise
     */
    public boolean removeOldMoviesFromXML(List<Movie> oldMoviesList){
        try {
            if(oldMoviesList.isEmpty()){
                return false;
            }else{
                Document doc = dBuilder.parse(movieXMLFile);
                Element root = doc.getDocumentElement();
                NodeList nodeList = doc.getElementsByTagName("movie");
                System.out.println(nodeList.getLength());
                for(Movie movie:oldMoviesList){
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node currentMovie = nodeList.item(i);
                        Node movieName = currentMovie.getFirstChild();
                        if(movieName.getTextContent().equals(movie.getMovieName())){
                            root.removeChild(currentMovie);
                            break;
                        }
                    }
                }
                source = new DOMSource(doc);
                result = new StreamResult(movieXMLFile);
                transformer.transform(source, result);
                LOGGER.log(Level.INFO, "Old Movies Removed");
                return true;
            }    
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception Occured", ex);
            return false;
        }
    }
    
    public boolean updateTransactions(List<Transaction> updateTransactions){
        try {
            if(updateTransactions.isEmpty()){
                return false;
            }else{
                Document doc = dBuilder.parse(transactionXMLFile); 
                Element root = doc.getDocumentElement();
                for(Transaction tran:updateTransactions){
                    Element newTran = doc.createElement("transaction");
                    newTran.setAttribute("id", Integer.toString(tran.getTransactionId()));
                    newTran.setAttribute("code", Integer.toString(tran.getTransactionCode()));
                    Element tranCardNumber = doc.createElement("cardNumber");
                    tranCardNumber.setTextContent(Long.toString(tran.getCardNumber()));
                    newTran.appendChild(tranCardNumber);
                    Element tranStatus = doc.createElement("transactionStatus");
                    tranStatus.setTextContent(tran.getTransactionStatus());
                    newTran.appendChild(tranStatus);
                    Element newMovies = doc.createElement("movies");
                    for(Movie movie:tran.getAllMovies()){
                        Element newMovie = doc.createElement("movie");
                        Element movieName = doc.createElement("name");
                        movieName.setTextContent(movie.getMovieName());
                        newMovie.appendChild(movieName);
                        Element moviePrice = doc.createElement("price");
                        moviePrice.setTextContent(Integer.toString(movie.getPrice()));
                        newMovie.appendChild(moviePrice);
                        newMovies.appendChild(newMovie);
                    }
                    newTran.appendChild(newMovies);
                    root.appendChild(newTran);
                }
                source = new DOMSource(doc);
                result = new StreamResult(transactionXMLFile);
                transformer.transform(source, result);
                 LOGGER.log(Level.INFO, "Transaction details updated");
                return true;
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Exception during Transaction details updated");
            return false;
        }
    }
    
    /**
     * Method for retrieving all the transaction data from XML Files
     * @return Map of transaction Id and transaction Object
     */
    public Map<Integer,Transaction> getTransactionData(){
        Map<Integer,Transaction> transactions = new HashMap<>();
        int transactionId=0;
        Transaction transactionObj;
        List<Movie> tranMoviesList;
        String movieName="";Integer moviePrice=-1;
        try {
            Document doc = dBuilder.parse(transactionXMLFile);   
            doc.getDocumentElement().normalize();            
            NodeList nList = doc.getElementsByTagName("transaction"); 
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                transactionObj = new Transaction();
                tranMoviesList = new ArrayList<>();
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element)nNode;
                    transactionId = Integer.parseInt(eElement.getAttribute("id"));
                    transactionObj.setTransactionCode(Integer.parseInt(eElement.getAttribute("code")));
                    transactionObj.setCardNumber(Long.parseLong(eElement.getElementsByTagName("cardNumber").item(0).getTextContent()));
                    transactionObj.setTransactionStatus(eElement.getElementsByTagName("transactionStatus").item(0).getTextContent());
                    NodeList movieList = eElement.getElementsByTagName("movie");
                    for (int j = 0; j < movieList.getLength(); j++) {
                        Element nMovieElement = (Element)movieList.item(j);
                        movieName = nMovieElement.getElementsByTagName("name").item(0).getTextContent();
                        if(!nMovieElement.getElementsByTagName("price").item(0).getTextContent().equals("")){
                            moviePrice = Integer.parseInt(nMovieElement.getElementsByTagName("price").item(0).getTextContent());
                        }
                        tranMoviesList.add(new Movie(movieName,moviePrice));
                    }
                    transactionObj.setAllMovies(tranMoviesList);
                }
                transactions.put(transactionId, transactionObj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.log(Level.INFO, "Transaction details retreived", ex);
            return null;
        }
        return transactions;
    }
    
    /**
     * Method for performing resource clean up
     */
    public void perfromResourceCleanUp(){
        logFileHandler.close();
    }
    
}
