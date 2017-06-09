package com.tsoftlatam.tab.utils;


import com.tsoftlatam.tab.model.Location;
import com.tsoftlatam.tab.model.Profile;
import com.tsoftlatam.tab.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parser {

    public static List<Profile> parseProfiles(List<String> samples){

        List<Profile> profiles = new ArrayList<Profile>();


        String[] line;


        for (String sample: samples) {

            line = sample.split(",");

            //NOTA, todas las muestras de BSM traen un COLUMN 0 como primer valor que no debe ser tenido en cuenta
            if (!Objects.equals(line[0], "COLUMN 0")) {
                Profile profile = new Profile(line[0]);
                profiles.add(profile);
            }

        }

        return profiles;
    }

    public static List<Transaction> parseTransactions(List<String> samples){

        List<Transaction> transactions = new ArrayList<Transaction>();


        String[] line;

        for (String sample: samples) {

            line = sample.split(",");

            //NOTA, todas las muestras de BSM traen un COLUMN 0 como primer valor que no debe ser tenido en cuenta
            if (!Objects.equals(line[0], "COLUMN 0")) {
                Transaction transaction = new Transaction(line[0]);
                transactions.add(transaction);
            }

        }
        return transactions;
    }

    public static List<Location> parseLocations(List<String> samples){

        List<Location> locations = new ArrayList<Location>();


        String[] line;

        for (String sample: samples) {

            line = sample.split(",");

            //NOTA, todas las muestras de BSM traen un COLUMN 0 como primer valor que no debe ser tenido en cuenta
            if (!Objects.equals(line[0], "COLUMN 0")) {
                Location location = new Location(line[0]);
                locations.add(location);
            }

        }
        return locations;
    }
}
