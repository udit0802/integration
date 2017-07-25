package com.airtel.prod.engg.producers;

import java.util.Map;

import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;

public class DeviationSpout extends KafkaSpout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector spoutOutputCollector;

	public DeviationSpout(SpoutConfig spoutConf) {
		super(spoutConf);
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		spoutOutputCollector = collector;
	}
}
