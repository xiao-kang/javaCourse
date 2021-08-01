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

package com.mq.core.core.consumer;

import com.mq.core.core.common.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author chenxk
 */
public class HttpConsumer implements Consumer {

    private final RestTemplate restTemplate = new RestTemplate();

    private Map<String, Object> properties;

    public HttpConsumer(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public List poll(int rate) {
        String topic = properties.get(Constants.TOPIC).toString();
        String group = properties.get(Constants.GROUP).toString();
        String url = properties.get(Constants.URL).toString();
        String brokerUrl = url + "/poll?topic=" + topic + "&rate=" + rate + "&group=" + group;
        ResponseEntity<List> response = restTemplate.getForEntity(brokerUrl, List.class);
        return response.getBody();
    }
}
