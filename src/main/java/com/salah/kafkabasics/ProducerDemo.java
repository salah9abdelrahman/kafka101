package com.salah.kafkabasics;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j

public class ProducerDemo {
    public static void main(String[] args) {
        log.info("yo");

        // Producer configs
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        for (int i = 0; i < 100; i++) {
            // Create a producer record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("topic_1", "hello world " + i);


            // send the data - async
            producer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    log.info("message received successfully Topic {}, Partition {}, Offset {}, Timestamp {}",
                            metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());

                }
            });
        }

        // block the code until the data is sent
        producer.close();

    }
}
