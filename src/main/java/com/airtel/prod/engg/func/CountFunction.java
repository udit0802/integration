package com.airtel.prod.engg.func;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;

public class CountFunction extends BaseFunction {

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		String word = tuple.getString(0);
		int count = word.length();
		collector.emit(new Values(word,count));
	}

}
