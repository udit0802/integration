package com.airtel.prod.engg.integrate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;
import org.json.JSONException;
import org.json.JSONObject;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Value.GeoJSONValue;
import com.airtel.agile.model.LatLng;
import com.airtel.agile.utility.LatLonUtils;
import com.airtel.prod.engg.consumers.inputPojo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeviationFunction extends BaseFunction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<LatLng> getRecord(String travelId) throws AerospikeException, JSONException, FileNotFoundException{
		
		AerospikeClient client = new AerospikeClient("10.5.213.43", 3000);
		

		Key key = new Key("FIBERFORCE_TRACKING", "TrackingMap", travelId);
		Record record = client.get(null, key);
		
		
		PrintWriter pw = new PrintWriter(new File("/Users/b0096703/Documents/loc.txt"));
		
		Map<Long, GeoJSONValue> map=(Map<Long, GeoJSONValue>) record.bins.get("coordinates");
//		Map<Long, GeoJSONValue> map=(Map<Long, GeoJSONValue>) record.bins.get_by_key_interval("coordinates", System.currentTimeMillis()-1800000, System.currentTimeMillis());
		System.out.println(map.keySet().size());
		ArrayList<LatLng> list = new ArrayList<>();
		for(Map.Entry<Long, GeoJSONValue> entry :map.entrySet()){
			String routeInfo = entry.getValue().toString();
			JSONObject jsonObj = new JSONObject(routeInfo);
			double lat = (double)jsonObj.getJSONArray("coordinates").get(0);
			double lon= (double)jsonObj.getJSONArray("coordinates").get(1);
			LatLng point = new LatLng(lat, lon);
			list.add(point);
			System.out.println("lat = " + lat +" lon = " + lon + " travelId = " + travelId);
			pw.println("lat = " + lat +" lon = " + lon + " travelId = " + travelId);
			
		}
		client.close();
		pw.close();
		return list;
	}

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		System.out.println(tuple.getString(0));
		
		ObjectMapper mapper = new ObjectMapper();
		inputPojo pointwithUserId = null;
			try {
				pointwithUserId = mapper.readValue(tuple.getString(0), inputPojo.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			List<LatLng> list = null;
			try {
				try {
					list = getRecord(pointwithUserId.getTravelId());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (AerospikeException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if(LatLonUtils.isLocationOnPath(new LatLng(pointwithUserId.getLatitude(), pointwithUserId.getLongitude()),list,true,100)){
				System.out.println("within deviation");
				collector.emit(new Values("within deviation"));
			}else{
				System.out.println("outside deviation");
				collector.emit(new Values("outside deviation"));
			}

	}

}
