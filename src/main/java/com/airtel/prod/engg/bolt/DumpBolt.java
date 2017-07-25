package com.airtel.prod.engg.bolt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

public class DumpBolt extends BaseBasicBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		double lat = input.getDoubleByField("lat");
		double lon = input.getDoubleByField("lon");
		String travelId = input.getStringByField("travelId");
		String userId = input.getStringByField("userId");
		Long deviationMessage = input.getLongByField("message");
		
		try {
			//dump somewhere else
			PrintWriter pw = new PrintWriter(new File("/Users/b0096703/Documents/deviation.txt"));
			pw.println("lat = " + lat +" lon = " + lon + " travelId = " + travelId + " userId = " + userId + " message = " + deviationMessage);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
