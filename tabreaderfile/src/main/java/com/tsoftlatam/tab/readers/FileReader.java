package com.tsoftlatam.tab.readers;

import com.tsoftlatam.tab.readers.models.BacData;
import com.tsoftlatam.tab.readers.utils.CSVUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class FileReader {

    private CSVUtils csvUtils;

    @RequestMapping("/getBacData")
    public List<BacData> getBacData(){
            csvUtils = new CSVUtils("C:\\","metricas.txt");
            return csvUtils.parseBacData();
    }

}
