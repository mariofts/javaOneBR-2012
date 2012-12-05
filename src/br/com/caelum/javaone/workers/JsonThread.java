package br.com.caelum.javaone.workers;

import java.util.List;

import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Sale;

public class JsonThread implements Runnable {

	private List<Sale> vendas;
	private List<String> jsons;

	public JsonThread(List<Sale> vendas, List<String> jsons) {
		this.vendas = vendas;
		this.jsons = jsons;
	}

	@Override
	public void run() {
		JsonMapper jsonMapper = new JsonMapper();
		for (Sale venda : vendas) {
			jsons.add(jsonMapper.toJSON(venda));
		}
	}

}
