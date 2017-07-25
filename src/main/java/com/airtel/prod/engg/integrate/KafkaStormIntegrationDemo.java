package com.airtel.prod.engg.integrate;

import java.util.HashMap;

import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.builtin.Debug;
import org.apache.storm.tuple.Fields;

import com.airtel.prod.engg.bolt.DumpBolt;
import com.airtel.prod.engg.bolt.LoggerBolt;
import com.airtel.prod.engg.producers.DeviationSpout;

public class KafkaStormIntegrationDemo {
	
//	private static final Logger LOG = Logger.getLogger(KafkaStormIntegrationDemo.class);
	
	public static void main(String[] args) {
//		final BrokerHosts zkrHosts = new ZkHosts("localhost:2181");
//		zookeeper id -> 10.5.203.11:2181
//		final String kafkaTopic = "test-topic";
//		final String zkRoot = "";
//		final String clientId = "id7";
//		final SpoutConfig kafkaConf = new SpoutConfig(zkrHosts, kafkaTopic, zkRoot, clientId);
//		kafkaConf.scheme = new SchemeAsMultiScheme(new StringScheme());
		
//		TridentTopology topology = new TridentTopology();
//		TridentKafkaConfig spoutConf = new TridentKafkaConfig(zkrHosts, kafkaTopic);
//		spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
//		OpaqueTridentKafkaSpout spout = new OpaqueTridentKafkaSpout(spoutConf);
		

		// Build topology to consume message from kafka and print them on console
//		final TopologyBuilder topologyBuilder = new TopologyBuilder();
		// Create KafkaSpout instance using Kafka configuration and add it to topology
//		topologyBuilder.setSpout("kafka-spout", new KafkaSpout(kafkaConf), 1);
		//Route the output of Kafka Spout to Logger bolt to log messages consumed from Kafka
//		topologyBuilder.setBolt("print-messages", new LoggerBolt()).globalGrouping("kafka-spout");
		
		
//		TridentTopology topology = new TridentTopology();
//		topology.newStream("lines", new KafkaSpout(kafkaConf)).each(new Fields("str"), new CountFunction(), new Fields("word_count")).
//		each(new Fields("word_count"), new Debug());
		
		// Submit topology to local cluster i.e. embedded storm instance in eclipse
		//final LocalCluster localCluster = new LocalCluster();
//		localCluster.submitTopology("kafka-topology", new HashMap<>(), topologyBuilder.createTopology());
//		localCluster.submitTopology("Trident-Topology-local", new HashMap<>(), topologyBuilder.createTopology());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		Fields fields = new Fields("word", "count");
//        FixedBatchSpout spout = new FixedBatchSpout(fields, 4,
//                new Values("storm", "1"),
//                new Values("trident", "1"),
//                new Values("needs", "1"),
//                new Values("javadoc", "1")
//        );
//        spout.setCycle(true);
//
//        TridentTopology topology = new TridentTopology();
//        Stream stream = topology.newStream("spout1", spout).each(new Fields("word", "count"), new Debug());
//
//        //set producer properties.
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "1");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        TridentKafkaStateFactory stateFactory = new TridentKafkaStateFactory()
//                .withProducerProperties(props)
//                .withKafkaTopicSelector(new DefaultTopicSelector("test-topic"))
//                .withTridentTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("word", "count"));
//        stream.partitionPersist(stateFactory, fields, new TridentKafkaUpdater(), new Fields());
//
//        Config conf = new Config();
//        
//        final LocalCluster localCluster = new LocalCluster();
//		localCluster.submitTopology("kafka-topology-trident", new HashMap<>(), topology.build());
		
		
		
		
		
		
		
		
		
		
		
		
//		Fields fields = new Fields("word","count");
//		TridentKafkaConfig spoutConf = new TridentKafkaConfig(new ZkHosts("localhost:2181"), "test-topic");
//		spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
//		OpaqueTridentKafkaSpout spout = new OpaqueTridentKafkaSpout(spoutConf);
//
//        TridentTopology topology = new TridentTopology();
//        Stream stream = topology.newStream("spout1", spout).each(new CountFunction(), new Fields("word","count"));
//
//        //set producer properties.
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "1");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        TridentKafkaStateFactory stateFactory = new TridentKafkaStateFactory()
//                .withProducerProperties(props)
//                .withKafkaTopicSelector(new DefaultTopicSelector("test-topic"))
//                .withTridentTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper("word", "count"));
//        stream.partitionPersist(stateFactory, fields, new TridentKafkaUpdater(), new Fields());
//
//        Config conf = new Config();
//        
//        final LocalCluster localCluster = new LocalCluster();
//		localCluster.submitTopology("kafka-topology-trident", new HashMap<>(), topology.build());
		
		
		
		final BrokerHosts zkrHosts = new ZkHosts("10.5.203.11:2181");
		final String kafkaTopic = "tracking_main";
		final String zkRoot = "/Users/b0096703";
		final String clientId = "deviation-consumer";
		
		final SpoutConfig kafkaConf = new SpoutConfig(zkrHosts, kafkaTopic, zkRoot, clientId);
		kafkaConf.scheme = new SchemeAsMultiScheme(new StringScheme());
		
		final TopologyBuilder topologyBuilder = new TopologyBuilder();
		
		
		// Create KafkaSpout instance using Kafka configuration and add it to topology
		topologyBuilder.setSpout("kafka-spout", new KafkaSpout(kafkaConf), 1);
		
		
		//Route the output of Kafka Spout to Logger bolt to log messages consumed from Kafka
		topologyBuilder.setBolt("calculate-deviation", new LoggerBolt()).globalGrouping("kafka-spout");
		
//		topologyBuilder.setBolt("print-messages", new DumpBolt()).globalGrouping("calculate-deviation");

		final LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("kafka-topology", new HashMap<>(), topologyBuilder.createTopology());
//		try {
//			StormSubmitter.submitTopology("kafka-topology", new HashMap<>(), topologyBuilder.createTopology());
//		} catch (AlreadyAliveException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidTopologyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (AuthorizationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		
		
//		TridentTopology topology = new TridentTopology();
//		topology.newStream("lines", new KafkaSpout(kafkaConf)).each(new DeviationFunction(), new Fields("determine_deviation")).
//		each(new Fields("determine_deviation"), new Debug());
		
//		TridentTopology topology = new TridentTopology();
//		topology.newStream("lines", new DeviationSpout(kafkaConf)).each(new DeviationFunction(), new Fields("determine_deviation")).
//		each(new Fields("determine_deviation"), new Debug());
//		
//		final LocalCluster localCluster = new LocalCluster();
//		localCluster.submitTopology("kafka8-topology", new HashMap<>(), topology.build());
//		try {
//			StormSubmitter.submitTopology("kafka-topology", new HashMap<>(), topology.build());
//		} catch (AlreadyAliveException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidTopologyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (AuthorizationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
