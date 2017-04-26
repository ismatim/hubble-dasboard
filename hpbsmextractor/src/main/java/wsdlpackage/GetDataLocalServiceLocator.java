/**
 * GetDataLocalServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsdlpackage;

public class GetDataLocalServiceLocator extends org.apache.axis.client.Service implements wsdlpackage.GetDataLocalService {

    public GetDataLocalServiceLocator() {
    }


    public GetDataLocalServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GetDataLocalServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GdeWsOpenAPI
    private java.lang.String GdeWsOpenAPI_address = "http://monitoreo.tsoftlatam.com/topaz/gdeopenapi/services/GdeWsOpenAPI";

    public java.lang.String getGdeWsOpenAPIAddress() {
        return GdeWsOpenAPI_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GdeWsOpenAPIWSDDServiceName = "GdeWsOpenAPI";

    public java.lang.String getGdeWsOpenAPIWSDDServiceName() {
        return GdeWsOpenAPIWSDDServiceName;
    }

    public void setGdeWsOpenAPIWSDDServiceName(java.lang.String name) {
        GdeWsOpenAPIWSDDServiceName = name;
    }

    public wsdlpackage.GetDataLocal getGdeWsOpenAPI() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GdeWsOpenAPI_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGdeWsOpenAPI(endpoint);
    }

    public wsdlpackage.GetDataLocal getGdeWsOpenAPI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            wsdlpackage.GdeWsOpenAPISoapBindingStub _stub = new wsdlpackage.GdeWsOpenAPISoapBindingStub(portAddress, this);
            _stub.setPortName(getGdeWsOpenAPIWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGdeWsOpenAPIEndpointAddress(java.lang.String address) {
        GdeWsOpenAPI_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (wsdlpackage.GetDataLocal.class.isAssignableFrom(serviceEndpointInterface)) {
                wsdlpackage.GdeWsOpenAPISoapBindingStub _stub = new wsdlpackage.GdeWsOpenAPISoapBindingStub(new java.net.URL(GdeWsOpenAPI_address), this);
                _stub.setPortName(getGdeWsOpenAPIWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GdeWsOpenAPI".equals(inputPortName)) {
            return getGdeWsOpenAPI();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://monitoreo.tsoftlatam.com/topaz/gdeopenapi/services/GdeWsOpenAPI", "GetDataLocalService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://monitoreo.tsoftlatam.com/topaz/gdeopenapi/services/GdeWsOpenAPI", "GdeWsOpenAPI"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GdeWsOpenAPI".equals(portName)) {
            setGdeWsOpenAPIEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
