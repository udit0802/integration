package com.airtel.prod.engg.producers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleProducer {

	public static void main(String[] args) throws Exception{
        
	      String topicName = "SimpleProducerTopic";
//		  String key = "Key1";
//		  String value = "Value-1";
//		  
//		  String key2 = "Key2";
//		  String value2 = "Value-2";
	      
	      Properties props = new Properties();
	      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
	      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
	      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		        
	      Producer<String, String> producer = new KafkaProducer <>(props);
		
//		  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
//		  ProducerRecord<String, String> record2 = new ProducerRecord<>(topicName,1,key2,value2);
		  
		  for (int i=0 ; i<10 ; i++){
			  RecordMetadata metadata = producer.send(new ProducerRecord<>(topicName,0,"SSP"+i,"500"+i)).get(); 
			  System.out.println("message is sent to partition = " + metadata.partition());
		  }

		         for (int i=0 ; i<10 ; i++){
		        	 RecordMetadata metadata = producer.send(new ProducerRecord<>(topicName,1,"TSS","500"+i)).get();
		        	 System.out.println("message is sent to partition = " + metadata.partition());
		         }
		         
//		  RecordMetadata metadata1 = producer.send(record).get();
		  
		  
//		  RecordMetadata metadata2 = producer.send(record2).get();
//		  System.out.println("message is sent to partition = " + metadata2.partition() + "with key = " + record2.key());
	      producer.close();
		  
		  System.out.println("SimpleProducer Completed.");
	   }
}
