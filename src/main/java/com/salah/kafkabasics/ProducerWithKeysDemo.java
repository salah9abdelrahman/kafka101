package com.salah.kafkabasics;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerWithKeysDemo {
    public static void main(String[] args) {

        // Producer configs
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.BOOTSTRAP_SERVERS_CONFIG.getValue());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        produceMessages(producer);

        produceMessages(producer);

        // block the code until the data is sent
        producer.close();

    }

    private static void produceMessages(KafkaProducer<String, String> producer) {
        for (int i = 0; i < 10; i++) {
            // Create a producer record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(Constants.TOPIC_1.getValue(),
                    "key_" + i, "hello world " + i);

            // send the data - async
            producer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    log.info("message received successfully  with Key {}, to Partition {}, Offset {}",
                            producerRecord.key(), metadata.partition(), metadata.offset());
                }
            });
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
