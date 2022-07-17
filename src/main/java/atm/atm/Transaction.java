/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm.atm;

import java.util.Date;

/**
 *
 * @author rjphi
 */
public class Transaction {
    
    private double amount;
    private Date timestamp;
    private String memo; 
    private Account inAccount;
    
    /**
     * Create a new transaction
     * @param amount the amount of the transaction
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {
        
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
        
    }
    
    /**
     * Create a new transaction
     * @param amount the amount of the transaction
     * @param inAccount the account the transaction belongs to
     * @param memo memo for transaction
     */
    public Transaction(double amount, String memo, Account inAccount) {
        
        //call the two-arg constructor first
        this(amount, inAccount);
        
        this.memo = memo;
        
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    /**
     * Get a string summarizing the transaction
     * @return Summary string
     */
    public String getSummaryLine() {
        
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(),
                    this.amount, this.memo);
        } else {
             return String.format("%s : $(%.02f) : %s", this.timestamp.toString(),
                    -this.amount, this.memo);
        }
    }
}
