package com.tsoftlatam.tab.readers.utils;

import com.opencsv.CSVReader;
import com.tsoftlatam.tab.readers.models.BacData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    List<BacData> bacDataList = new ArrayList<BacData>();
    String csvFilePath;
    String csvFileName;
    CSVReader reader;

    public CSVUtils(String csvFilePath, String csvFileName){
        this.csvFilePath = csvFilePath;
        this.csvFileName = csvFileName;
    }

    public List<BacData> parseBacData(){
        try {
            reader = new CSVReader(new FileReader(csvFilePath+csvFileName));
            String[] line;
            try {
                /*while ((line = reader.readNext()) != null) {
                    BacData bacData = new BacData(line[0],line[1],line[2],Integer.parseInt(line[3]),
                            line[4],Float.valueOf(line[5]),Double.parseDouble(line[6]),
                            Long.valueOf(line[7]));
                    bacDataList.add(bacData);
                }*/
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
}
