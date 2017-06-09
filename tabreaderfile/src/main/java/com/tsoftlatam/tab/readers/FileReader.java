package com.tsoftlatam.tab.readers;

import com.tsoftlatam.tab.readers.models.Profile;
import com.tsoftlatam.tab.readers.models.BacData;
import com.tsoftlatam.tab.readers.models.Location;
import com.tsoftlatam.tab.readers.models.Transaction;
import com.tsoftlatam.tab.readers.utils.CSVUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class FileReader {

    private CSVUtils csvUtils;

    @RequestMapping("readers/hpbac/getRawData")
    public List<BacData> getBacData(){
            File currentDir = new File (".");
        try {
            csvUtils = new CSVUtils(currentDir.getCanonicalPath()+"/tabreaderfile/", "metricas.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


            return csvUtils.parseBacData();

    }

    @RequestMapping("readers/hpbac/getProfiles")
    public List<Profile> getProfiles(){
        File currentDir = new File (".");
        try {
            csvUtils = new CSVUtils(currentDir.getCanonicalPath()+"/tabreaderfile/", "aplicaciones.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return csvUtils.parseProfiles();

    }

    @RequestMapping("readers/hpbac/getTransactions")
    public List<Transaction> getTransactions(){
        File currentDir = new File (".");
        try {
            csvUtils = new CSVUtils(currentDir.getCanonicalPath()+"/tabreaderfile/", "transacciones.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return csvUtils.parseTransactions();

    }

    @RequestMapping("readers/hpbac/getLocations")
    public List<Location> getLocations(){
        File currentDir = new File (".");
        try {
            csvUtils = new CSVUtils(currentDir.getCanonicalPath()+"/tabreaderfile/", "locaciones.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return csvUtils.parseLocations();

    }

}
