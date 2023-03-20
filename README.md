 # Kafka 101

## Commands

_For not M chip replace ```/opt/homebrew``` with ```/uxsr/local```_

* Start Zookeeper
```
  /opt/homebrew/bin/zookeeper-server-start /opt/homebrew/etc/zookeeper/zoo.cfg
```
* Start kafka
```
 - /opt/homebrew/bin/kafka-server-start /opt/homebrew/etc/kafka/server.properties
 - using brew: brew services start kafka

```

* Create a topic
```
 kafka-topics --bootstrap-server 127.0.0.1:9092 --create --topic topic_1 --partitions 3 --replication-factor 1
```

*  _PS: allow parallel instances on ConsumerDemo, so you can see the Consumer Group thing_

* Start kafka console consumer
```
 kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic topic_1
```
