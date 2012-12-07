package br.com.caelum.javaone.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Sale;

public class JsonTask extends RecursiveTask<List<String>>{

	private final int THRESHOLD = 10000;
	private List<Sale> list;

	public JsonTask(List<Sale> list) {
		this.list = list;
	}

	@Override
	protected List<String> compute() {
		List<String> jsons = new ArrayList<>();
        int size = list.size();
		if ( size < THRESHOLD){
			jsons.addAll(new JsonMapper().toJson(list));
		} else{
        	JsonTask jsonTaskFirst =  new JsonTask(list.subList(0, size / 2));
        	jsonTaskFirst.fork();
        	
        	JsonTask jsonTaskSecond = new JsonTask(list.subList(size / 2, size));
        	
        	jsons.addAll(jsonTaskSecond.compute());
        	jsons.addAll(jsonTaskFirst.join());
        }
         
		return jsons;
	}

}
