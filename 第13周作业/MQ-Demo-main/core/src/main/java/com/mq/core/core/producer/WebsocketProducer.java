/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mq.core.core.producer;

import com.google.gson.Gson;
import com.mq.core.core.common.Constants;
import com.mq.core.core.common.UUIDUtil;
import lombok.SneakyThrows;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxk
 */
public class WebsocketProducer implements Producer {

    private Map<String, Object> properties;
    private Map<String, Boolean> resultCache = new HashMap<>();
    private WebSocketClient client;
    private Gson gson = new Gson();

    public WebsocketProducer(Map<String, Object> properties) {
        this.properties = properties;
        connect();
    }

    @SneakyThrows
    private void connect() {
        client = new WebSocketClient(new URI(properties.get(Constants.URL).toString())) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("producer websocket client connected");
            }

            @Override
            public void onMessage(String s) {
//                System.out.println("receive message : " + s);
                if (!"null".equals(s)) {
                    resultCache.put(s, true);
                }
                notifyAll();
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("producer websocket client close");
            }

            @Override
            public void onError(Exception e) {

            }
        };
        System.out.println(client.connectBlocking());
    }

    @SneakyThrows
    @Override
    public synchronized boolean send(String topic, String message) {
        String id = UUIDUtil.getUUID();
        String sendTimeout = Constants.SEND_TIMEOUT;
        int defaultTimeout = Constants.DEFAULT_SEND_TIMEOUT;
        long timeout = Long.parseLong(properties.getOrDefault(sendTimeout, defaultTimeout).toString());

        Map<String, Object> map = new HashMap<>(3);
        map.put(Constants.TOPIC, topic);
        map.put(Constants.MESSAGE, message);
        map.put(Constants.SEND_ID, id);

        client.send(gson.toJson(map));
        while (!resultCache.getOrDefault(id, false)) {
            this.wait(timeout);
        }
        if (!resultCache.getOrDefault(id, false)) {
            return false;
        }
        resultCache.remove(id);
        return true;
    }
}
