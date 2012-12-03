package br.com.caelum.javaone.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.javaone.mapper.CSVMapper;
import br.com.caelum.javaone.model.Venda;

public abstract class JsonConverter {

	public void convert() throws Exception {
		
		Scanner sc = openFile();
		
		List<Venda> vendas = readToMemory(sc);
		
		long ini = System.currentTimeMillis();
		List<String> jsons = convertToJson(vendas);
		long end = System.currentTimeMillis();
		
		System.out.printf("Conversion time is [ %d ] milliseconds %n",end-ini);
		System.out.printf("Converted %d objects %n", jsons.size());
		
		System.out.println();
		
	}

	protected abstract List<String> convertToJson(List<Venda> vendas) throws Exception;

	protected List<Venda> readToMemory(Scanner sc) throws ParseException {
		System.out.println("Reading file to memory....");
		CSVMapper csvMapper = new CSVMapper();
		String line = null;
		
		List<Venda> vendas = new ArrayList<>();
		
		while(sc.hasNextLine()){
			line = sc.nextLine();
			Venda v = csvMapper.fromCsv(line);
			vendas.add(v);
		}
		sc.close();
		
		System.out.printf("Found %d sales! %n%n",vendas.size());
		return vendas;
	}

	protected Scanner openFile() throws FileNotFoundException {
		System.out.println("Opening file....");
		Scanner sc = new Scanner(new FileInputStream("vendas.csv"));
		System.out.println("Done!");
		return sc;
	}

}
