
package bean;

import javax.jws.WebService;

@WebService(serviceName = "RtsipService", targetNamespace = "http://soap", endpointInterface = "bean.RtsipServicePortType")
public class RtsipServiceImpl
    implements RtsipServicePortType
{


    public ArrayOfMyDigitalProperty getDigitalProperty(String in0, String in1) {
        throw new UnsupportedOperationException();
    }

    public ArrayOfMyAnalogProperty getAnalogProperty(String in0, String in1) {
        throw new UnsupportedOperationException();
    }

    public void registerWebService(String in0, String in1) {
        throw new UnsupportedOperationException();
    }

    public ArrayOfMyEventProperty getEventProperty(String in0, int in1) {
        throw new UnsupportedOperationException();
    }

}
