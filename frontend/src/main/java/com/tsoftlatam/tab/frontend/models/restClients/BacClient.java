package com.tsoftlatam.tab.frontend.models.restClients;

import com.tsoftlatam.tab.frontend.models.BacSample;
import feign.RequestLine;

import java.util.List;

public interface BacClient {

    @RequestLine("GET /")
    List<BacSample> findAll();
}

