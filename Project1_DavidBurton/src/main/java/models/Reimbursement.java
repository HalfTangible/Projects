package models;

import java.sql.Date;

public class Reimbursement {

    private int reim_ID;
    private double amount;
    private String submitted; //thank you garrett for reminding me Date exists
    private String resolved;
    private String description;
    private byte receipt;
    private int author;
    private int resolver;
    private int status;
    private int type;

    public String toString(){

        return null;
    }

    public int getReim_ID() {
        return reim_ID;
    }

    public void setReim_ID(int reim_ID) {
        this.reim_ID = reim_ID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getReceipt() {
        return receipt;
    }

    public void setReceipt(byte receipt) {
        this.receipt = receipt;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
