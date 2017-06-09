package com.tsoftlatam.tab.frontend.readers.hpbac;

import com.tsoftlatam.tab.frontend.sources.hpbac.models.Location;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Profile;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Sample;
import com.tsoftlatam.tab.frontend.sources.hpbac.models.Transaction;
import feign.RequestLine;

import java.util.List;

public interface HPBacRestReader {

    @RequestLine("GET /")
    List<Sample> findAll();

    @RequestLine("GET /")
    List<ProfileReaderModel> findAllProfiles();

    @RequestLine("GET /")
    List<TransactionReaderModel> findAllTransactions();

    @RequestLine("GET /")
    List<LocationReaderModel> findAllLocations();
}

