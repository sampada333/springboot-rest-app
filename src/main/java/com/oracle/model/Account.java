package com.oracle.model;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component("account1") 
@Scope(scopeName = "prototype")
@Entity
@Primary
@Table(name="myaccount2")
public class Account {

	@Id
	@Column(name= "acc_id")
	private int accountId;
	
	@Column(name= "balance")
	private String balance;
	public Account() {
		System.out.println("Account obj created");
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}
}
