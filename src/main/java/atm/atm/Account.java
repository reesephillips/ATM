/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atm.atm;

import java.util.ArrayList;
/**
 *
 * @author rjphi
 */
public class Account {
    
    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;
    
    /**
     * Create a new accoutn
     * @param name name of account
     * @param holder the User object that holds the account
     * @param theBank the bank that issue the account
     */
    
    public Account(String name, User holder, Bank theBank) {
        
        //set account name and holder
        this.name = name;
        this.holder = holder;
        
        //get the new account UUID
        this.uuid = theBank.getNewAccountUUID();
        
        
       //Initialize transactions
       this.transactions = new ArrayList<Transaction>();
       
    }
    
    /**
     * Get account uuid
     * @return 
     */
    public String getUUID() {
        return this.uuid;
    }
    
    /**
     * Get summary line for account
     * @return string summary
     */
    public String getSummaryLine() {
        
        //get the account balance
        double balance = this.getBalance();
        
        //format summary line depending on whether balance is negative
        if(balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
             return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }
    
    /**
     * get balance of account by adding all transactions
     * @return balance value
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    } 
    
    /**
     * Print transaction history of the account
     */
    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n",
                this.uuid);
        for (int t = this.transactions.size()-1; t>=0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }
    
    /**
     * Add a new transaction in this amount
     * @param amount the amount transacted
     * @param memo   The transaction memo
     */
    public void addTransaction(double amount, String memo) {
        //create new transaction object and add to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        
        this.transactions.add(newTrans);
    }
}
