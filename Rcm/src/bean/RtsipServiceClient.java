
package bean;

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

public class RtsipServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public RtsipServiceClient() {
        create0();
        Endpoint RtsipServicePortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://soap", "RtsipServicePortTypeLocalEndpoint"), new QName("http://soap", "RtsipServicePortTypeLocalBinding"), "xfire.local://RtsipService");
        endpoints.put(new QName("http://soap", "RtsipServicePortTypeLocalEndpoint"), RtsipServicePortTypeLocalEndpointEP);
        Endpoint RtsipServiceHttpPortEP = service0 .addEndpoint(new QName("http://soap", "RtsipServiceHttpPort"), new QName("http://soap", "RtsipServiceHttpBinding"), "http://10.110.42.3:8080/rtsip/services/RtsipService");
        endpoints.put(new QName("http://soap", "RtsipServiceHttpPort"), RtsipServiceHttpPortEP);
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
        service0 = asf.create((bean.RtsipServicePortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://soap", "RtsipServicePortTypeLocalBinding"), "urn:xfire:transport:local");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://soap", "RtsipServiceHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
    }

    public RtsipServicePortType getRtsipServicePortTypeLocalEndpoint() {
        return ((RtsipServicePortType)(this).getEndpoint(new QName("http://soap", "RtsipServicePortTypeLocalEndpoint")));
    }

    public RtsipServicePortType getRtsipServicePortTypeLocalEndpoint(String url) {
        RtsipServicePortType var = getRtsipServicePortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public RtsipServicePortType getRtsipServiceHttpPort() {
        return ((RtsipServicePortType)(this).getEndpoint(new QName("http://soap", "RtsipServiceHttpPort")));
    }

    public RtsipServicePortType getRtsipServiceHttpPort(String url) {
        RtsipServicePortType var = getRtsipServiceHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

}
