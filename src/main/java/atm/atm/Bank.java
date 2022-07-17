/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm.atm;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rjphi
 */
public class Bank {
    
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    
    /**
     * Create new bank with empty lists of users and accounts
     * @param name name of bank
     */
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    
    /**
     * Generate a new UUID for user
     * @return the uuid
     */
    public String getNewUserUUID() {
        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;
        
        //continue generating uuid until no longer unique
        do {
            //generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            
            //check to make sure its unique
            nonUnique = false;
            for(User u:this.users){
                if (uuid.compareTo(u.getUUID()) == 0) {
                nonUnique = true;
                break;
            }
            }
        } while (nonUnique);
        
        return uuid;
    }
    
    /**
     * Generate a new UUID for account
     * @return the uuid
     */
    public String getNewAccountUUID() {
        //inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;
        
        //continue generating uuid until no longer unique
        do {
            //generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            
            //check to make sure its unique
            nonUnique = false;
            for(Account a:this.accounts){
                if (uuid.compareTo(a.getUUID()) == 0) {
                nonUnique = true;
                break;
            }
            }
        } while (nonUnique);
        
        return uuid;
    }

    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }
    
    /**
     * Create new user of the bank
     * @param firstName
     * @param lastName
     * @param pin
     * @return 
     */
    public User addUser(String firstName, String lastName, String pin) {
        
        //create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);
        
        //create a savings account
        Account newAccount = new Account("Savings", newUser, this);
        //add to holder and bank lists
       newUser.addAccount(newAccount);
       this.accounts.add(newAccount);
       
       return newUser;
    }
    
    /**
     * Get user associated with a userID and pin if they are valid
     * @param userID
     * @param pin
     * @return 
     */
    public User userLogin(String userID, String pin) {
        
        //search list of users
        for(User u: this.users) {
            
            //check user id is correct
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        
        //unable to find valid user
        return null; 
    }
    
    /**
     * get bank name
     * @return name
     */
    public String getName() {
        return this.name;
    }
    
}
