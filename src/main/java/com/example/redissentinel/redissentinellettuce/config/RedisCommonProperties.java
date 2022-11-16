package com.example.redissentinel.redissentinellettuce.config;

import lombok.Data;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;
@Data
public class RedisCommonProperties implements Serializable {

    private Set<String> host;
    private  String sentinelName;
    private transient String sentinelPassword;
    private transient String redisPassword;

    private void writeObject(ObjectOutputStream stream) throws IOException {
        throw new IllegalStateException("Property cannot be serialized");

    }
}
