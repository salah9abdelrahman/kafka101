package com.salah.kafkabasics;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerDemo {
    public static void main(String[] args) {

        // Producer configs
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS_CONFIG.getValue());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());

        // Create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        for (int i = 0; i < 100; i++) {
            // Create a producer record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(Constants.TOPIC_1.getValue(),
                    "hello world " + i);

            // send the data - async
            producer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    log.info("message received successfully Topic {}, Partition {}, Offset {}, Timestamp {}",
                            metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());

                }
            });
            // The simplest way to send a message synchronously
            //    .get();
        }

        // block the code until the data is sent
        producer.close();

    }
}
