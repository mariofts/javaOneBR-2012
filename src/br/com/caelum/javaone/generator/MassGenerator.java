package br.com.caelum.javaone.generator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.caelum.javaone.mapper.CSVMapper;
import br.com.caelum.javaone.model.Sale;

public class MassGenerator {
	
	private Random random = new Random();
	
	private static String[] prods = {"caneta","cartolina","lápis","lapiseira", "grampeador", "caderno", "fichario"};
	private static String[] cores = {"azul","branco","preto", "verde","vermelho","roxo", "cinza"}; 	

	public void generate(int size) throws FileNotFoundException {
		List<Sale> vendas = generateData(size);
		writeToCSVFile(vendas);
		System.out.println("Generated " + size + " sales...");
	}

	private void writeToCSVFile(List<Sale> vendas) throws FileNotFoundException {
		
		PrintStream ps = new PrintStream("vendas.csv");
		for (Sale venda : vendas) {
			String line = new CSVMapper().toCsv(venda);
			ps.println(line.toString());
		}
		ps.close();
	}

	private List<Sale> generateData(int qtde) {
		List<Sale> vendas = new ArrayList<>();
		
		Calendar data = Calendar.getInstance();
		
		for(int i = 0; i< qtde; i++){
			Sale v = new Sale();
			v.setDescription(getRandomDesc());
			v.setNumber(i + 1);
			v.setDate(data);
			v.setPrice(getRandomPreco());
			v.setAmount(getRandomQtde());
			v.setTerminal(getRandomTerm());
			vendas.add(v);
		}
		
		return vendas;
	}

	private int getRandomTerm() {
		return random.nextInt(5);
	}

	private int getRandomQtde() {
		return random.nextInt(15);
	}

	private double getRandomPreco() {
		return random.nextDouble() * 100.0;
	}

	private String getRandomDesc() {
		int prod = random.nextInt(prods.length);
		int cor = random.nextInt(cores.length);
		
		return String.format("%s %s", prods[prod], cores[cor]);
	}

}
