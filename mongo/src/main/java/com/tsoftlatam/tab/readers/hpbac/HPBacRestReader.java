package com.tsoftlatam.tab.readers.hpbac;

import feign.RequestLine;

import java.util.List;

public interface HPBacRestReader {

    @RequestLine("GET /")
    List<ProfileReaderModel> findAllProfiles();

}

