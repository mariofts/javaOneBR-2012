package br.com.caelum.javaone.mapper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.caelum.javaone.model.Venda;

public class CSVMapper {
	
	private static SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
	private static NumberFormat numberFormat = new DecimalFormat("###.00");

	public String toCsv(Venda venda) {
		StringBuilder line = new StringBuilder();
		line.append(venda.getNumero());
		line.append(";");
		line.append(venda.getTerminal());
		line.append(";");
		line.append(format.format(venda.getData().getTime()));
		line.append(";");
		line.append(venda.getDescricao());
		line.append(";");
		line.append(venda.getQuantidade());
		line.append(";");
		line.append(numberFormat.format(venda.getPreco()));
		return line.toString();
	}
	
	public Venda fromCsv(String line) throws ParseException{
		String[] contents = line.split(";");

		int numero = Integer.parseInt(contents[0]);
		int terminal = Integer.parseInt(contents[1]);
		Calendar data = Calendar.getInstance();
		data.setTime(format.parse(contents[2]));
		String descricao = contents[3];
		int qtde = Integer.parseInt(contents[4]);
		double preco = Double.parseDouble(contents[4]);
		
		return new Venda(numero,terminal,data,descricao,qtde,preco);
	}
	

}
