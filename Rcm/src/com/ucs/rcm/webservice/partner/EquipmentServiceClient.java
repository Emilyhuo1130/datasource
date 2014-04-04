package com.ucs.rcm.webservice.partner;






import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class EquipmentServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public EquipmentServiceClient() {
        create0();
        Endpoint EquipmentSoapPortEP = service0 .addEndpoint(new QName("http://www.china-gentech.com/sggis/service", "EquipmentSoapPort"), new QName("http://www.china-gentech.com/sggis/service", "EquipmentServiceSoapBinding"), "http://10.201.2.2:7001/Equipment_Service/EquipmentService");
        endpoints.put(new QName("http://www.china-gentech.com/sggis/service", "EquipmentSoapPort"), EquipmentSoapPortEP);
        Endpoint EquipmentServiceLocalEndpointEP = service0 .addEndpoint(new QName("http://www.china-gentech.com/sggis/service", "EquipmentServiceLocalEndpoint"), new QName("http://www.china-gentech.com/sggis/service", "EquipmentServiceLocalBinding"), "xfire.local://EquipmentService");
        endpoints.put(new QName("http://www.china-gentech.com/sggis/service", "EquipmentServiceLocalEndpoint"), EquipmentServiceLocalEndpointEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((com.ucs.rcm.webservice.partner.EquipmentService.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://www.china-gentech.com/sggis/service", "EquipmentServiceSoapBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://www.china-gentech.com/sggis/service", "EquipmentServiceLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public EquipmentService getEquipmentSoapPort() {
        return ((EquipmentService)(this).getEndpoint(new QName("http://www.china-gentech.com/sggis/service", "EquipmentSoapPort")));
    }

    public EquipmentService getEquipmentSoapPort(String url) {
        EquipmentService var = getEquipmentSoapPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public EquipmentService getEquipmentServiceLocalEndpoint() {
        return ((EquipmentService)(this).getEndpoint(new QName("http://www.china-gentech.com/sggis/service", "EquipmentServiceLocalEndpoint")));
    }

    public EquipmentService getEquipmentServiceLocalEndpoint(String url) {
        EquipmentService var = getEquipmentServiceLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

}
