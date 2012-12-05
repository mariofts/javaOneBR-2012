package br.com.caelum.javaone.converter;

import java.util.List;

import br.com.caelum.javaone.mapper.JsonMapper;
import br.com.caelum.javaone.model.Sale;

public class JsonSequentialConverter extends JsonConverter {

	public JsonSequentialConverter(int count) {
		super(count);
	}

	@Override
	protected List<String> convertToJson(List<Sale> vendas) {
		JsonMapper jsonMapper = new JsonMapper();
		List<String> jsons = jsonMapper.toJson(vendas);
		return jsons;
	}

}
