package com.airtel.prod.engg.consumers;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {

	public static void main(String[] args) throws Exception {

		String topicName = "SimpleProducerTopic";
		KafkaConsumer<String, String> consumer = null;
		String groupName = "group";
		int rCount;

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//		props.put("enable.auto.commit", "false");
		props.put("group.id", groupName);

		consumer = new KafkaConsumer<>(props);
//		TopicPartition p0 = new TopicPartition(topicName, 0);
//		TopicPartition p1 = new TopicPartition(topicName, 1);

//		consumer.assign(Arrays.asList(p0, p1));
		consumer.subscribe(Arrays.asList(topicName));

		System.out.println("Start Fetching Now");
		try {
			do {
				ConsumerRecords<String, String> records = consumer.poll(1000);
				System.out.println("Record polled " + records.count());
				rCount = records.count();
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("key = " + record.key());
					System.out.println("value = " + record.value());
				}
			} while (rCount > 0);
		} catch (Exception ex) {
			System.out.println("Exception in main.");
		} finally {
			consumer.close();
		}
	}

}
