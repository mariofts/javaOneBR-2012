package br.com.caelum.javaone.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.caelum.javaone.model.Venda;
import br.com.caelum.javaone.workers.JsonThread;

public class JsonMultiThreadConverter extends JsonConverter {

	@Override
	protected List<String> convertToJson(List<Venda> vendas) throws Exception {
		
		System.out.println("Convert to json....");
		
		List<String> jsons = Collections.synchronizedList(new ArrayList<String>());
//		List<String> jsons = new ArrayList<String>();
		
		int procs = 4;
		
		int qtde = vendas.size() / procs;
		int beginIndex = 0;
		int finalIndex = qtde;
		
		List<Thread> threads = new ArrayList<>();
		
		for (int i = 1; i < procs; i++) {
			threads.add(createANewThread(vendas, jsons, beginIndex, finalIndex));
			beginIndex = finalIndex;
			finalIndex = finalIndex + qtde;
		}

		int resto = vendas.size() % procs;
		threads.add(createANewThread(vendas, jsons, beginIndex,finalIndex+resto ));
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join();
		}
		
		System.out.println("Finished!\n	");
		return jsons;
		
	}

	protected Thread createANewThread(List<Venda> vendas, List<String> jsons,
			int beginIndex, int finalIndex) {
		return new Thread(new JsonThread(beginIndex,finalIndex,vendas,jsons));
	}

}
