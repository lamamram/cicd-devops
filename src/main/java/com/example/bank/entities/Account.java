package com.example.bank.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "account")
public class Account {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "balance", nullable = false)
    private Float balance;

    @Column(name = "overdraft", nullable = false)
    private Float overdraft;

    public Account() {}

    public Account(String title, Float balance, Float overdraft) {
      this.title = title;
      this.balance = balance;
      this.overdraft = overdraft;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public Float getBalance() {
      return balance;
    }

    public void setBalance(Float balance) {
      this.balance = balance;
    }

    public Float getOverdraft() {
      return overdraft;
    }

    public void setOverdraft(Float overdraft) {
      this.overdraft = overdraft;
    }

}
