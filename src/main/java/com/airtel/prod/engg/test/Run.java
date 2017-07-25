package com.airtel.prod.engg.test;

import java.util.List;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.airtel.fibreforce.route.db.entity.GeoPoint;
import com.airtel.prod.engg.consumers.SimpleConsumer;
import com.airtel.prod.engg.producers.SimpleProducer;

public class Run {
    public static void main(String[] args) throws Exception {
//        if (args.length < 1) {
//            throw new IllegalArgumentException("Must have either 'producer' or 'consumer' as argument");
//        }
//        switch (args[0]) {
//            case "producer":
//                SimpleProducer.main(args);
//                break;
//            case "consumer":
//                SimpleConsumer.main(args);
//                break;
//            default:
//                throw new IllegalArgumentException("Don't know how to do " + args[0]);
//        }
    	
    	AerospikeClient client = new AerospikeClient("10.5.213.43", 3000);
//    	Key key = new Key("FIBERFORCE_TOKEN", "RouteReferenceASEntity", 564);
    	Statement stmt = new Statement();
    	stmt.setNamespace("FIBERFORCE_TOKEN");
    	stmt.setSetName("RouteReferenceASEntity");
    	stmt.setBinNames(new String[] {"patrolUsrId","patrolNm","points"});
//    	stmt.setFilters(Filter.equal("patrolUsrId", 186));
    			
    	RecordSet rs = client.query(null, stmt);
    	try {
    	    while (rs.next()) {
    	        Key key = rs.getKey();
    	        Record record = rs.getRecord();
    	        if(record.bins.get("points") == null)
    	        		continue;
    	        System.out.println("patrolNm" + record.bins.get("patrolNm") +" ,patrolUsrId" + record.bins.get("patrolUsrId") + " ,key = " + key + " ,record = " + record.bins.get("points"));
//    	        List<GeoPoint> points = (List<GeoPoint>)record.bins.get("points");
//    	        for(GeoPoint point : points) {
//    	        		System.out.println(point.toString());
//    	        }
    	    }
    	}
    	finally {
    	    rs.close();
    	}
//		System.out.println("record = " + record);
    }
}
