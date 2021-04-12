package com.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;


public class MyRebalanceListener implements ConsumerRebalanceListener {

    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("Partitions are assigned");
    }

    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("Partitions are revoked");
    }
}