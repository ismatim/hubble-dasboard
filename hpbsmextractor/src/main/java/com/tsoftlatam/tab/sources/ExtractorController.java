package com.tsoftlatam.tab.sources;

import junit.framework.AssertionFailedError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * Created by david.malagueno on 25/4/2017.
 */
@RestController
public class ExtractorController {

    @RequestMapping("/res/{user}")
    public String home(@PathVariable String user) throws RemoteException {
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

        String query = "select application_name as ApplicationName, szTransactionName as TransactionName ,szLocationName, ErrorCount, availability_status, u_iStatus, dResponseTime from trans_t where time_stamp>=" + currentTimestamp.getTime()/1000;
        String res = binding.getDataWebService(user, "123456", query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

        String resArray[] = res.split("\n");
        return res;

        }

}
