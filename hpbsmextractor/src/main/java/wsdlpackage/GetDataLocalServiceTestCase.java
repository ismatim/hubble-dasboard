/**
 * GetDataLocalServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsdlpackage;

public class GetDataLocalServiceTestCase extends junit.framework.TestCase {
    public GetDataLocalServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testGdeWsOpenAPIWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPIAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new wsdlpackage.GetDataLocalServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1GdeWsOpenAPIGetDataWebService() throws Exception {
        wsdlpackage.GdeWsOpenAPISoapBindingStub binding;
        try {
            binding = (wsdlpackage.GdeWsOpenAPISoapBindingStub)
                          new wsdlpackage.GetDataLocalServiceLocator().getGdeWsOpenAPI();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.getDataWebService(new java.lang.String(), new java.lang.String(), new java.lang.String(), new javax.xml.rpc.holders.IntHolder(), new javax.xml.rpc.holders.IntHolder());
        // TBD - validate results
    }

}
