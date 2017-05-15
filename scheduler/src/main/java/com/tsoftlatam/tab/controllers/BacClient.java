package com.tsoftlatam.tab.controllers;

import feign.RequestLine;

import java.util.List;

public interface BacClient {

    @RequestLine("GET /")
    void findAll();
}

