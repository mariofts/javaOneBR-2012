package br.com.caelum.javaone.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.caelum.javaone.model.Sale;
import br.com.caelum.javaone.workers.JsonThread;

public class JsonMultiThreadConverter extends JsonConverter {

	private int NUMBER_OF_THREADS = 4;

	public JsonMultiThreadConverter(int count) {
		super(count);
	}

	@Override
	protected List<String> convertToJson(List<Sale> vendas) throws Exception {

		System.out.println("Convert to json....");

		int size = vendas.size();
		
		int amount = size / NUMBER_OF_THREADS; //4
		int beginIndex = 0;
		int finalIndex = amount;

		List<Thread> threads = new ArrayList<>();
		List<String> jsons = Collections.synchronizedList(new ArrayList<String>());

		while (true) {
			threads.add(createANewThread(vendas, jsons, beginIndex, finalIndex));
			beginIndex = finalIndex;
			finalIndex = finalIndex + amount >= size ? size	: finalIndex + amount;

			if (beginIndex == size) {
				break;
			}
			
		}

		for (Thread thread : threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			thread.join();
		}

		System.out.println("Finished!\n	");
		return jsons;

	}

	protected Thread createANewThread(List<Sale> vendas, List<String> jsons,
			int beginIndex, int finalIndex) {
		return new Thread(new JsonThread(
				vendas.subList(beginIndex, finalIndex), jsons));
	}

}
