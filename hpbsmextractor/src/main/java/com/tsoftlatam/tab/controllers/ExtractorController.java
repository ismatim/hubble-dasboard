package com.tsoftlatam.tab.controllers;

import com.tsoftlatam.tab.entities.Sample;
import junit.framework.AssertionFailedError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Created by david.malagueno on 25/4/2017.
 */
@RestController
public class ExtractorController {

    @RequestMapping("/res")
    public String[] home() throws IOException {
        wsdlpackage.GdeWsOpenAPISoapBindingStub binding;
        try{
            binding = (wsdlpackage.GdeWsOpenAPISoapBindingStub) new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPI();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        // Time out after a minute
        binding.setTimeout(600000);

        //Creo un objeto calendar al que le asigno la fecha y hora actual y le resto una hora (ultimos 60 min)
        //Luego lo convierto a Date para que luego pueda pasarlo a Timestamp (divido por 1000 xq el resultado da en exceso)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        String query = "select application_name as ApplicationName, szTransactionName as TransactionName ,szLocationName, ErrorCount, availability_status, u_iStatus, dResponseTime, time_stamp from trans_t where time_stamp>=" + currentTimestamp.getTime()/1000 ;
        String res = binding.getDataWebService("mlapalma", "123456", query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

        String[] resArray = res.split("\n");
        WriteFile(resArray);

        List<Sample> muestras = CreateSamples(resArray);
        WriteFileSamples(muestras);

        return resArray;

        }

    private void WriteFileSamples(List<Sample> muestras)throws IOException {
        List<String> lista = new ArrayList();
        for (Sample list:muestras) {
            lista.add(list.getApplicationName() + "," + list.getTransactionName() + "," + list.getLocationName() + "," + list.getAvailabilityStatus() + "," + list.getErrorCount() + "," + list.getTransactionStatus() + "," + list.getResponseTime()  + "," + list.getTimestamp()  + "," + list.getFecha());
        }
        Path file = Paths.get("..\\metricas2.txt");
        Files.write(file, lista, Charset.forName("UTF-8"));

    }

    private List<Sample> CreateSamples(String[] resArray) {

        List<Sample> samples = new ArrayList<>();
        for (String item:resArray) {
            String[] elements = item.split(",");

            if(Objects.equals(elements[3], "<empty Variant>"))
                elements[3] = "0";

            if (!Objects.equals(elements[0], "ApplicationName")) {
                samples.add(new Sample(elements[0], elements[1], elements[2], Integer.parseInt(elements[3]), Float.parseFloat(elements[4]), Float.parseFloat(elements[5]), Double.parseDouble(elements[6]), Double.valueOf(elements[7]).longValue()));
            }
        }
        return samples;
    }

    private void WriteFile(String[] resArray) throws IOException {
        List<String> lista = new ArrayList();
        for (String list:resArray) {

            lista.add(list);
        }

        Path file = Paths.get("..\\metricas.txt");
        Files.write(file, lista, Charset.forName("UTF-8"));

//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }

}
