package com.tsoftlatam.tab.frontend.readers;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Application;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Sample;
import feign.RequestLine;

import java.util.List;

public interface HPBacRestReader {

    @RequestLine("GET /")
    List<Sample> findAll();

    @RequestLine("GET /")
    List<Application> findAllApplications();
}

