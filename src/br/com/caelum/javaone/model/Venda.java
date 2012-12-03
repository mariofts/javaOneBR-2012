package br.com.caelum.javaone.model;

import java.util.Calendar;

public class Venda {

	private int numero;

	private int terminal;

	private Calendar data;

	private String descricao;

	private int quantidade;

	private double preco;
	
	public Venda(){
	}

	public Venda(int numero, int terminal, Calendar data, String descricao,
			int quantidade, double preco) {
		this.numero = numero;
		this.terminal = terminal;
		this.data = data;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public double getTotal() {
		return preco * quantidade;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Venda [numero=" + numero + ", terminal=" + terminal
				+ ", descricao=" + descricao + ", quantidade=" + quantidade
				+ ", preco=" + preco + "]";
	}

}
