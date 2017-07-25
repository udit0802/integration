package com.airtel.prod.engg.bolt;

import com.aerospike.client.AerospikeClient;

public class Client {

	private static Client client = new Client();
	
	public static AerospikeClient aerospikeClient;
	
	private Client() { }
	
	public static Client getInstance( ) {
	      return client;
	   }
	
	protected static AerospikeClient generateClient() {
		aerospikeClient = new AerospikeClient("10.5.213.43", 3000);
	      return aerospikeClient;
	   }
}
