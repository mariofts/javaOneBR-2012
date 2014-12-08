package br.com.caelum.javaone.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Sale;

public class JsonTask extends RecursiveTask<List<String>>{

	private final int THRESHOLD = 10000;
	private List<Sale> list;
	
	private int from;
	private int to;

	public JsonTask(List<Sale> list) {
		this.list = list;
	}

	public JsonTask(List<Sale> list, int from, int to) {
		this(list);
		this.from = from;
		this.to = to;
	}

	@Override
	protected List<String> compute() {
		List<String> jsons = new ArrayList<>();
		if (to - from < THRESHOLD){
			jsons.addAll(new JsonMapper().toJson(list));
		} else{
		
		int split = (from + to)/2	
			
        	JsonTask jsonTaskFirst =  new JsonTask(list, from, split);
        	jsonTaskFirst.fork();
        	
        	JsonTask jsonTaskSecond = new JsonTask(list, from + split, to);
        	
        	jsons.addAll(jsonTaskSecond.compute());
        	jsons.addAll(jsonTaskFirst.join());
        }
         
		return jsons;
	}

}
