package br.com.caelum.javaone.converter;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import br.com.caelum.javaone.model.Venda;
import br.com.caelum.javaone.workers.JsonTask;

public class JsonForkJoinConverter extends JsonConverter {
	
	private final ForkJoinPool forkJoinPool = new ForkJoinPool();

	@Override
	protected List<String> convertToJson(List<Venda> vendas) throws Exception {
		return forkJoinPool.invoke(new JsonTask(vendas));
	}

}
