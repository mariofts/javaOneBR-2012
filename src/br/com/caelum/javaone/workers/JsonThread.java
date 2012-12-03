package br.com.caelum.javaone.workers;

import java.util.List;

import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Venda;

public class JsonThread implements Runnable {

	private int beginIndex;
	private int finalIndex;
	private List<Venda> vendas;
	private List<String> jsons;

	public JsonThread(int beginIndex, int finalIndex, List<Venda> vendas,
			List<String> jsons) {
		this.beginIndex = beginIndex;
		this.finalIndex = finalIndex;
		this.vendas = vendas;
		this.jsons = jsons;
	}

	@Override
	public void run() {
//		System.out.println("ID:"+Thread.currentThread().getId()+":"+beginIndex + ":"+finalIndex);
		JsonMapper jsonMapper = new JsonMapper();
		List<Venda> subList = vendas.subList(beginIndex,finalIndex);
//		System.out.println("ID:"+Thread.currentThread().getId()+":size: " + subList.size());

		for (Venda venda : subList) {
			jsons.add(jsonMapper.toJSON(venda));
		}
//		jsons.addAll(jsonMapper.toJson(subList));
	}

}
