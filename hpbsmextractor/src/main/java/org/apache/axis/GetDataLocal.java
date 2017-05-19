/**
 * GetDataLocal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.apache.axis;

public interface GetDataLocal extends java.rmi.Remote {
    public java.lang.String getDataWebService(java.lang.String user, java.lang.String password, java.lang.String query, javax.xml.rpc.holders.IntHolder errorCode, javax.xml.rpc.holders.IntHolder origRowCount) throws java.rmi.RemoteException;
}
