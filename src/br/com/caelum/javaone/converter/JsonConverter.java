package br.com.caelum.javaone.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.javaone.mapper.CSVMapper;
import br.com.caelum.javaone.model.Sale;

public abstract class JsonConverter {

	private int count;

	public JsonConverter(int count) {
		this.count = count;
	}

	public void convert() throws Exception {
		
		Scanner sc = openFile();
		
		List<Sale> vendas = readToMemory(sc);
		
		long[] times = new long[count];
		
		for (int i = 0; i < count; i++) {
			System.out.printf("Iteration %d %n",i);
			
			long ini = System.currentTimeMillis();
			List<String> jsons = convertToJson(vendas);
			long end = System.currentTimeMillis();
			
			long time = end-ini;
			times[i] = time;
			System.out.printf("Conversion time is [ %d ] milliseconds %n",time);
			System.out.println("Converted : " + jsons.size());
			
		}

//		System.out.printf("Converted %d objects %n", jsons.size());
		Arrays.sort(times);
		System.out.println("Times => " + Arrays.toString(times));
		System.out.println();
		
	}

	protected abstract List<String> convertToJson(List<Sale> vendas) throws Exception;

	protected List<Sale> readToMemory(Scanner sc) throws ParseException {
		System.out.println("Reading file to memory....");
		CSVMapper csvMapper = new CSVMapper();
		String line = null;
		
		List<Sale> vendas = new ArrayList<>();
		
		while(sc.hasNextLine()){
			line = sc.nextLine();
			Sale v = csvMapper.fromCsv(line);
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
