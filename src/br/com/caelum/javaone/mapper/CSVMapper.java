package br.com.caelum.javaone.mapper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.caelum.javaone.model.Sale;

public class CSVMapper {
	
	private static SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
	private static NumberFormat numberFormat = new DecimalFormat("###.00");

	public String toCsv(Sale venda) {
		StringBuilder line = new StringBuilder();
		line.append(venda.getNumber());
		line.append(";");
		line.append(venda.getTerminal());
		line.append(";");
		line.append(format.format(venda.getDate().getTime()));
		line.append(";");
		line.append(venda.getDescription());
		line.append(";");
		line.append(venda.getAmount());
		line.append(";");
		line.append(numberFormat.format(venda.getPrice()));
		return line.toString();
	}
	
	public Sale fromCsv(String line) throws ParseException{
		String[] contents = line.split(";");

		int numero = Integer.parseInt(contents[0]);
		int terminal = Integer.parseInt(contents[1]);
		Calendar data = Calendar.getInstance();
		data.setTime(format.parse(contents[2]));
		String descricao = contents[3];
		int qtde = Integer.parseInt(contents[4]);
		double preco = Double.parseDouble(contents[4]);
		
		return new Sale(numero,terminal,data,descricao,qtde,preco);
	}
	

}
