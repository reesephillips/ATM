/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm.atm;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rjphi
 */
public class User {
    private String firstName;
    private String lastName;
    private String uuid;
    /*The MD5 hash of the user's pin*/
    private byte pinHash[];
    private ArrayList<Account> accounts;
    
    public User(String firstName, String lastName, String pin, Bank theBank) {
        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;
        
        try {
            //store the pin's in MD5 hash
            //security reasons
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("error, caught NoSushAlgorithmException");
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        //get a new unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();
        
        //empty list of accounts
        this.accounts = new ArrayList<Account>();
        
        //print log message
        System.out.printf("New User %s, %s with ID %s created. \n", lastName, firstName, this.uuid);
        
    }
    /**
     * Add an account for the user
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }
    /**
     * Get user uuid
     * @return 
     */
    public String getUUID() {
        return this.uuid;
    }
    
    /**
     * Check whether a given pin matches the true User pin
     * @param aPin
     * @return whether pin is valid or not
     */
    public boolean validatePin(String aPin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("error, caught NoSushAlgorithmException");
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        return false;
    }
    
    /**
     * return first name
     * @return 
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    public void printAccountSummary(){
        
        System.out.printf("\n\n%s's accoutns summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine()); //1:12
        }
    }
    
    /**
     * Get the number of accounts of the user
     * @return the number of accounts 
     */
    public int numAccounts() {
        return this.accounts.size();
    }
    
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }
    
    /**
     * Get the balance of a particular account
     * @param acctIdx the index of the account to use
     * @return the balance of the account
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }
    
    /**
     * Get the UUID of a particular account
     * @param acctIdx index of account to use
     * @return the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }
    
    public void addAcctTransaction(int acctIx, double amount, String memo) {
        this.accounts.get(acctIx).addTransaction(amount, memo);
    }
}
