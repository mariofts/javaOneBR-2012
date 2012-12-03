package br.com.caelum.javaone.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.javaone.mapper.CSVMapper;
import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Venda;

public class CsvToJsonConverter {
	
	public static void main(String[] args) throws ParseException, IOException {
		
		Scanner sc = new Scanner(new FileInputStream("vendas.csv"));
		CSVMapper csvMapper = new CSVMapper();
		JsonMapper jsonMapper = new JsonMapper();
		String line = null;
		
		List<Venda> vendas = new ArrayList<>();
		
		while(sc.hasNextLine()){
			line = sc.nextLine();
			Venda v = csvMapper.fromCsv(line);
			vendas.add(v);
		}
		sc.close();
		
		System.out.println(jsonMapper.toJson(vendas));
		
//		FileOutputStream out = new FileOutputStream("vendas.json");
//		jsonMapper.toJson(vendas,out);
		
	}

}
