package br.com.caelum.javaone.converter;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import br.com.caelum.javaone.model.Sale;
import br.com.caelum.javaone.workers.JsonTask;

public class JsonForkJoinConverter extends JsonConverter {
	
	private final ForkJoinPool forkJoinPool = new ForkJoinPool();

	public JsonForkJoinConverter(int count) {
		super(count);
	}

	@Override
	protected List<String> convertToJson(List<Sale> vendas) throws Exception {
		return forkJoinPool.invoke(new JsonTask(vendas));
	}

}
