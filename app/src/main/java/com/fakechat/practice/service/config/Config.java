package com.fakechat.practice.service.config;


import com.fakechat.practice.service.protocol.Serializer;

public abstract class Config {

    public static String host = "10.0.2.2";
    public static int port = 8080;

    public static Serializer.Algorithm getSerializerAlgorithm() {
        return Serializer.Algorithm.Json;
    }
}