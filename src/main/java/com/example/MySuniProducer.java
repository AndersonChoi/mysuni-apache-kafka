package com.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class MySuniProducer {
    private static final String TOPIC_NAME = "test";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        Properties configs = new Properties();
        configs.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        configs.put("key.serializer", StringSerializer.class.getName());
        configs.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer(configs);
        String messageValue = "hello-kafka";
        ProducerRecord<String, String> record = new ProducerRecord(TOPIC_NAME, messageValue);
        producer.send(record);
        producer.flush();

        producer.close();
    }
}
