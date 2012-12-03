package br.com.caelum.javaone.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Venda;

public class JsonTask extends RecursiveTask<List<String>>{

	private final int THRESHOLD = 250;
	private List<Venda> list;

	public JsonTask(List<Venda> list) {
		this.list = list;
	}

	@Override
	protected List<String> compute() {
		
		List<String> jsons = new ArrayList<>();
		
        if ( list.size() > THRESHOLD){
        	JsonTask jsonTaskFirst =  new JsonTask(list.subList(0, list.size() / 2));
        	jsonTaskFirst.fork();
        	
        	JsonTask jsonTaskSecond = new JsonTask(list.subList(list.size() / 2, list.size()));
        	
        	jsons.addAll(jsonTaskSecond.compute());
        	jsons.addAll(jsonTaskFirst.join());
        }
        else{
        	jsons.addAll(new JsonMapper().toJson(list));
        }
         
		return jsons;
	}

}
