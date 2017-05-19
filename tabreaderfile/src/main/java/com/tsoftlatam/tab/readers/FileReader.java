package com.tsoftlatam.tab.readers;

import com.tsoftlatam.tab.readers.models.BacData;
import com.tsoftlatam.tab.readers.utils.CSVUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class FileReader {

    private CSVUtils csvUtils;

    @RequestMapping("/getBacData")
    public List<BacData> getBacData(){
            File currentDir = new File (".");
        try {
            csvUtils = new CSVUtils(currentDir.getCanonicalPath()+"/tabreaderfile/", "metricas.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


            return csvUtils.parseBacData();

    }

}
