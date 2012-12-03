package br.com.caelum.javaone.converter;

import java.util.List;

import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Venda;

public class JsonSequentialConverter extends JsonConverter {

	@Override
	protected List<String> convertToJson(List<Venda> vendas) {
		System.out.println("Convert to json....");
		
		JsonMapper jsonMapper = new JsonMapper();
		List<String> jsons = jsonMapper.toJson(vendas);
	
		System.out.println("Finished!\n	");
		return jsons;
	}

}
