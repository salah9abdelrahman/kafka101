package com.salah.kafkabasics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Constants {
    BOOTSTRAP_SERVERS_CONFIG( "127.0.0.1:9092"),
    TOPIC_1("topic_1"),
    GROUP_ID_1("groupId_1");

    private final String value;
}
