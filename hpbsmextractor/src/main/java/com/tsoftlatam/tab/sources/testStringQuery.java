package com.tsoftlatam.tab.sources;

import junit.framework.AssertionFailedError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;

/**
 * Created by david.malagueno on 25/4/2017.
 */
@RestController
public class testStringQuery {

    @RequestMapping("/res")
    public String home() throws RemoteException {
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
        String query = "select application_name as ApplicationName, szTransactionName as TransactionName ,szLocationName, ErrorCount, availability_status, u_iStatus, dResponseTime from trans_t where time_stamp>=1493078400.64";
        String res = binding.getDataWebService("mlapalma", "123456", query, new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());

        String resArray[] = res.split("\n");
        return resArray[2];
        }

}
