package com.tsoftlatam.tab.frontend.readers;

import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacApplication;
import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacSample;
import com.tsoftlatam.tab.frontend.bles.hpbacble.models.HPBacTransaction;
import feign.RequestLine;

import java.util.List;

public interface HPBacRestClient {

    @RequestLine("GET /")
    List<HPBacSample> findAll();

    @RequestLine("GET /")
    List<HPBacApplication> findAllApplications();
}

