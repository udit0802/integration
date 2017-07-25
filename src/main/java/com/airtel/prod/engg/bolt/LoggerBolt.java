package com.airtel.prod.engg.bolt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.json.JSONException;
import org.json.JSONObject;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Value.GeoJSONValue;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.IndexCollectionType;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.airtel.agile.model.LatLng;
import com.airtel.agile.utility.LatLonUtils;
import com.airtel.fibreforce.route.db.entity.GeoPoint;
import com.airtel.prod.engg.consumers.inputPojo;
import com.fasterxml.jackson.databind.ObjectMapper;


public class LoggerBolt extends BaseBasicBolt {
	
	
	/*
	 * {lat: 28.4914053, lng: 77.0800213}
2.  1:{lat: 28.491550137076338, lng: 77.08040352956925}
3.  2:{lat: 28.491146443228217, lng: 77.08096109587439}
4.  3:{lat: 28.48710828153369, lng: 77.07715686058305}
5.  4:{lat: 28.48767178960149, lng: 77.07627094588247}
6.  5:{lat: 28.48800668699448, lng: 77.07582949127868}
7.  6:{lat: 28.4883558610208, lng: 77.07569759385262}
8.  7:{lat: 28.48964786656141, lng: 77.0756313519862}
9.  8:{lat: 28.49093987206991, lng: 77.07556510849838}
10. 9:{lat: 28.490975961016296, lng: 77.07723285973702}
11. 10:{lat: 28.490797103610696, lng: 77.07777360884552}
12. 11:{lat: 28.49178823015815, lng: 77.07869228021775}
13. 12:{lat: 28.49238523332666, lng: 77.07928471524008}
14. 13:{lat: 28.49161050050355, lng: 77.08032457679064}
15. 14:{lat: 28.4914277, lng: 77.0800213}
	 * 
	 * */
	
	
	private static final long serialVersionUID = 1L;
	
	private List<LatLng> getRecord(long userId) throws AerospikeException, JSONException, FileNotFoundException{
		
//		AerospikeClient client = new AerospikeClient("10.5.213.43", 3000);
		

//		Key key = new Key("FIBERFORCE_TRACKING", "TrackingMap", travelId);
//		ArrayList<LatLng> list = new ArrayList<>();
//		Record record = client.get(null, key);
//		if(record == null) {
//			client.close();
//			return list;
//		}
//		
//		
//		PrintWriter pw = new PrintWriter(new File("/Users/b0096703/Documents/loc.txt"));
//		
//		Map<Long, GeoJSONValue> map=(Map<Long, GeoJSONValue>) record.bins.get("coordinates");
//		System.out.println(map.keySet().size());
//		for(Map.Entry<Long, GeoJSONValue> entry :map.entrySet()){
//			String routeInfo = entry.getValue().toString();
//			JSONObject jsonObj = new JSONObject(routeInfo);
//			double lat = (double)jsonObj.getJSONArray("coordinates").get(0);
//			double lon= (double)jsonObj.getJSONArray("coordinates").get(1);
//			LatLng point = new LatLng(lat, lon);
//			list.add(point);
//			System.out.println("lat = " + lat +" lon = " + lon + " travelId = " + travelId);
//			pw.println("lat = " + lat +" lon = " + lon + " travelId = " + travelId);
//			
//		}
//		client.close();
//		pw.close();
//		return list;
		
		AerospikeClient client = new AerospikeClient("10.5.213.43", 3000);
//		Client client = Client.getInstance();
//		AerospikeClient aClient = client.generateClient();
		ArrayList<LatLng> list = new ArrayList<>();
//    	Key key = new Key("FIBERFORCE_TOKEN", "RouteReferenceASEntity", 564);
	    	Statement stmt = new Statement();
	    	stmt.setNamespace("FIBERFORCE_TOKEN");
	    	stmt.setSetName("RouteReferenceASEntity");
	    	stmt.setBinNames(new String[] {"patrolUsrId","patrolNm","points"});
	    	stmt.setFilters(Filter.equal("patrolUsrId", userId));
	    			
	    	RecordSet rs = client.query(null, stmt);
	    	try {
	    	    while (rs.next()) {
	    	        Key key = rs.getKey();
	    	        Record record = rs.getRecord();
	    	        if(record.bins.get("points") == null)
	    	        		continue;
	    	        System.out.println("patrolNm" + record.bins.get("patrolNm") +" ,patrolUsrId" + record.bins.get("patrolUsrId") + " ,key = " + key + " ,record = " + record.bins.get("points"));
	    	        List<GeoPoint> points = (List<GeoPoint>)record.bins.get("points");
	    	        for(GeoPoint point : points) {
	    	        		LatLng pointOnPath = new LatLng(point.getLat(), point.getLng());
	    	        		list.add(pointOnPath);
	    	        }
	    	    }
	    	}
	    	finally {
	    	    rs.close();
	    	}
	    	return list;
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		System.out.println(input.getString(0));
		
		ObjectMapper mapper = new ObjectMapper();
		inputPojo pointwithUserId = null;
			try {
				pointwithUserId = mapper.readValue(input.getString(0), inputPojo.class);
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<LatLng> list = null;
		try {
			try {
				list = getRecord(pointwithUserId.getUserId());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (AerospikeException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String message = null;
		if(list != null && !list.isEmpty()) {
			if(LatLonUtils.isLocationOnPath(new LatLng(pointwithUserId.getLatitude(), pointwithUserId.getLongitude()),list,true,100)){
				System.out.println("within deviation");
				message = "within deviation";
			}else{
				System.out.println("outside deviation");
				message = "outside deviation";
			}
			collector.emit(new Values(pointwithUserId.getLatitude(),pointwithUserId.getLongitude(),pointwithUserId.getTravelId(),pointwithUserId.getUserId(),message));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("lat","lon","travelId","userId","message"));
	}

}
