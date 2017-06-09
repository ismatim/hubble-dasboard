package com.tsoftlatam.tab.controllers;

import com.google.gson.Gson;
import com.tsoftlatam.tab.model.Location;
import com.tsoftlatam.tab.model.Profile;
import com.tsoftlatam.tab.model.SampleV8;
import com.tsoftlatam.tab.model.Transaction;
import com.tsoftlatam.tab.utils.Parser;
import junit.framework.AssertionFailedError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

@RestController
public class ExtractorController {

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @RequestMapping("/extractor/hpbac/getProfiles")
    public List<Profile> getProfiles(){
        wsdlpackage.GdeWsOpenAPISoapBindingStub binding;

        try{
            binding = (wsdlpackage.GdeWsOpenAPISoapBindingStub) new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPI();

            // Time out
            binding.setTimeout(60000);

            //Query para BSM Version 8
            //Query que trae las diferentes aplicaciones
            //String query = "select distinct(application_name) as ApplicationName, szTransactionName as TransactionName ,szLocationName, ErrorCount, availability_status, u_iStatus, dResponseTime, time_stamp from trans_t where time_stamp>=" + currentTimestamp.getTime()/1000 ;
            String query = "select distinct(profile_name) " +
                    "from trans_t " +
                    "where time_stamp>=" + getTimestamp60Minutes();

            String result = binding.getDataWebService(user, password, query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

            //Separo cada linea
            String[] resArray = result.split("\n");

            //Convierto el array en lista de strings
            List<String> resultadoLista = new ArrayList<String>(Arrays.asList(resArray));

            //Creo una lista del modelo requerido para el resultado
            List<Profile> muestras = Parser.parseProfiles(resultadoLista);

            //dejo listo un Json para una etapa posterior
            //String json = CrearJson(muestras);

            return muestras;
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;

    }

    //Método que trae todas las transacciones disponibles para un determinado perfil
    @RequestMapping("/extractor/hpbac/{profileId}/getTransactions")
    public List<Transaction> getTransactions(@PathVariable String profileId){

        wsdlpackage.GdeWsOpenAPISoapBindingStub binding;

        try{
            binding = (wsdlpackage.GdeWsOpenAPISoapBindingStub) new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPI();

            // Time out
            binding.setTimeout(60000);

            //Query para BSM Version 8
            //Query que trae las diferentes transacciones por cada aplicación
            //String query = "select distinct(application_name) as ApplicationName, szTransactionName as TransactionName ,szLocationName, ErrorCount, availability_status, u_iStatus, dResponseTime, time_stamp from trans_t where time_stamp>=" + currentTimestamp.getTime()/1000 ;
            String query = "select distinct(szTransactionName) " +
                    "from trans_t " +
                    "where time_stamp>=" + getTimestamp60Minutes()+
                    "and profile_name='"+ profileId+"'";

            String result = binding.getDataWebService(user, password, query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

            //Separo cada linea
            String[] resArray = result.split("\n");

            //Convierto el array en lista de strings
            List<String> resultadoLista = new ArrayList<String>(Arrays.asList(resArray));

            //Creo una lista del modelo requerido para el resultado
            List<Transaction> muestras = Parser.parseTransactions(resultadoLista);

            //dejo listo un Json para una etapa posterior
            //String json = CrearJson(muestras);

            return muestras;
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;

    }

    //Método que trae todas las locaciones disponibles para un determinado perfil
    @RequestMapping("/extractor/hpbac/{profileName}/getLocations")
    public List<Location> getLocations(@PathVariable String profileName){

        wsdlpackage.GdeWsOpenAPISoapBindingStub binding;

        try{
            binding = (wsdlpackage.GdeWsOpenAPISoapBindingStub) new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPI();

            // Time out
            binding.setTimeout(60000);

            //Query para BSM Version 8
            //Query que trae las diferentes transacciones por cada aplicación
            //String query = "select distinct(application_name) as ApplicationName, szTransactionName as TransactionName ,szLocationName, ErrorCount, availability_status, u_iStatus, dResponseTime, time_stamp from trans_t where time_stamp>=" + currentTimestamp.getTime()/1000 ;
            String query = "select distinct(szLocationName) " +
                    "from trans_t " +
                    "where time_stamp>=" + getTimestamp60Minutes()+
                    "and profile_name='"+ profileName+"'";

            String result = binding.getDataWebService(user, password, query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

            //Separo cada linea
            String[] resArray = result.split("\n");

            //Convierto el array en lista de strings
            List<String> resultadoLista = new ArrayList<String>(Arrays.asList(resArray));

            //Creo una lista del modelo requerido para el resultado
            List<Location> muestras = Parser.parseLocations(resultadoLista);

            //dejo listo un Json para una etapa posterior
            //String json = CrearJson(muestras);

            return muestras;
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;

    }

    @RequestMapping("/res")
    public String getMetricas() throws IOException {
        wsdlpackage.GdeWsOpenAPISoapBindingStub binding;


        try{
            binding = (wsdlpackage.GdeWsOpenAPISoapBindingStub) new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPI();

            // Time out
            binding.setTimeout(60000);

            //Query para BSM Version 9
            //String query = "select application_name as ApplicationName, szTransactionName as TransactionName ,szLocationName, ErrorCount, availability_status, u_iStatus, dResponseTime, time_stamp from trans_t where time_stamp>=" + currentTimestamp.getTime()/1000 ;
            String query = "select profile_name, " +
                    "szTransactionName, " +
                    "szLocationName, " +
                    "iComponentErrorCount, " +
                    "szStatusName, " +
                    "u_iStatus, " +
                    "dResponseTime, " +
                    "time_stamp, " +
                    "trans_instance_id " +
                    "from trans_t " +
                    "where time_stamp>=" + getTimestamp60Minutes();

            String res = binding.getDataWebService(user, password, query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

            //Separo cada linea
            String[] resArray = res.split("\n");

            //Creo una lista de Samples
            List<SampleV8> muestras = CreateSamples(resArray);

            //dejo listo un Json para una etapa posterior
            String json = CrearJson(muestras);

            return json;
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }

    }

    @RequestMapping(value="/fechaInicial/{date1}/fechaFinal/{date2}", method = RequestMethod.GET)
    public String getMetricasEntreFechas(@PathVariable String date1,@PathVariable String date2) throws IOException {
        wsdlpackage.GdeWsOpenAPISoapBindingStub binding;

        try {
            binding = (wsdlpackage.GdeWsOpenAPISoapBindingStub) new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPI();

            // Time out
            binding.setTimeout(60000);

            String query = "select profile_name, " +
                    "szTransactionName, " +
                    "szLocationName, " +
                    "iComponentErrorCount, " +
                    "szStatusName, " +
                    "u_iStatus, " +
                    "dResponseTime, " +
                    "time_stamp, " +
                    "trans_instance_id " +
                    "from trans_t " +
                    "where time_stamp>=" + date1 +
                    " and time_stamp<=" + date2 ;

            String res = binding.getDataWebService(user, password, query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

            //Separo cada linea
            String[] resArray = res.split("\n");

            //Creo una lista de Samples
            List<SampleV8> muestras = CreateSamples(resArray);

            //dejo listo un Json para una etapa posterior
            //TODO: acoplar este Json con el de Jhon
            String json = CrearJson(muestras);


            return json;

        }catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }


    }

    private Long getTimestamp60Minutes() {
        //Creo un objeto calendar al que le asigno la fecha y hora actual y le resto una hora (ultimos 60 min)
        //Luego lo convierto a Date para que luego pueda pasarlo a Timestamp (divido por 1000 xq el resultado da en exceso)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        return currentTimestamp.getTime()/1000 ;
    }

    private void WriteFileSamples(List<SampleV8> muestras)throws IOException {

        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter("..\\metricas.txt", true));

            for (SampleV8 list:muestras) {
                bw.write(list.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {                       // always close the file
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                // just ignore it
            }
        } // end try/catch/finally

    }

    //Crea a partir de un array de samples, un array de SampleV8
    private List<SampleV8> CreateSamples(String[] resArray) {

        List<SampleV8> sampleV8s = new ArrayList<>();
        for (String item:resArray) {
            String[] elements = item.split(",");

            //En BSM, a veces aparece el <Empty Variant> y hay que sacarlo
            if(Objects.equals(elements[3], "<empty Variant>"))
                elements[3] = "0";

            if (!Objects.equals(elements[0], "COLUMN 0"))
                sampleV8s.add(new SampleV8(elements[0],
                        elements[1],
                        elements[2],
                        Integer.parseInt(elements[3]),
                        elements[4],
                        Float.parseFloat(elements[5]),
                        Double.parseDouble(elements[6]),
                        Double.valueOf(elements[7]).longValue(),
                        Integer.parseInt(elements[8])));
        }

        return sampleV8s;
    }

    private String CrearJson(List<SampleV8> samples){
        Gson gson = new Gson();
        String json = gson.toJson(samples);
        return json;
    }
}
