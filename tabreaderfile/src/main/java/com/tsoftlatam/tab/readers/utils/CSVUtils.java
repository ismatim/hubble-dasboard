package com.tsoftlatam.tab.readers.utils;

import com.opencsv.CSVReader;
import com.tsoftlatam.tab.readers.models.Profile;
import com.tsoftlatam.tab.readers.models.BacData;
import com.tsoftlatam.tab.readers.models.Location;
import com.tsoftlatam.tab.readers.models.Transaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    List<BacData> bacDataList = new ArrayList<BacData>();
    List<Profile> profiles = new ArrayList<Profile>();
    List<Transaction> transactions = new ArrayList<Transaction>();
    List<Location> locations = new ArrayList<Location>();
    String csvFilePath;
    String csvFileName;
    CSVReader reader;

    public CSVUtils(String csvFilePath, String csvFileName){
        this.csvFilePath = csvFilePath;
        this.csvFileName = csvFileName;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public List<BacData> parseBacData(){
        try {
            reader = new CSVReader(new FileReader(csvFilePath+csvFileName));
            String[] line;
            try {
                while ((line = reader.readNext()) != null) {

                    BacData bacData = new BacData(line[0],line[1],line[2],line[3],
                            line[4],line[5],line[6],
                            line[7]);

                    bacDataList.add(bacData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bacDataList;
    }

    public List<Profile> parseProfiles(){
        try {
            reader = new CSVReader(new FileReader(csvFilePath+csvFileName));
            String[] line;
            try {
                while ((line = reader.readNext()) != null) {

                    Profile profile = new Profile(line[0]);

                    profiles.add(profile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    public List<Transaction> parseTransactions(){
        try {
            reader = new CSVReader(new FileReader(csvFilePath+csvFileName));
            String[] line;
            try {
                while ((line = reader.readNext()) != null) {

                    Transaction transaction = new Transaction(line[0],line[1]);

                    transactions.add(transaction);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Location> parseLocations(){
        try {
            reader = new CSVReader(new FileReader(csvFilePath+csvFileName));
            String[] line;
            try {
                while ((line = reader.readNext()) != null) {

                    Location location = new Location(line[0],line[1],line[2]);

                    locations.add(location);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
