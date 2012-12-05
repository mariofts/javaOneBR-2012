package br.com.caelum.javaone.model;

import java.util.Calendar;

public class Sale {

	private int number;

	private int terminal;

	private Calendar date;

	private String description;

	private int amount;

	private double price;
	
	public Sale(){
	}

	public Sale(int number, int terminal, Calendar date, String description,
			int amount, double price) {
		this.number = number;
		this.terminal = terminal;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.price = price;
	}

	public double getTotal() {
		return price * amount;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Sale [number=" + number + ", terminal=" + terminal + ", date="
				+ date + ", description=" + description + ", amount=" + amount
				+ ", price=" + price + "]";
	}

}
