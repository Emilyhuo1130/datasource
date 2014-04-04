
package bean;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "RtsipServicePortType", targetNamespace = "http://soap")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface RtsipServicePortType {


    @WebMethod(operationName = "GetDigitalProperty", action = "")
    @WebResult(name = "out", targetNamespace = "http://soap")
    public ArrayOfMyDigitalProperty getDigitalProperty(
        @WebParam(name = "in0", targetNamespace = "http://soap")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://soap")
        String in1);

    @WebMethod(operationName = "GetAnalogProperty", action = "")
    @WebResult(name = "out", targetNamespace = "http://soap")
    public ArrayOfMyAnalogProperty getAnalogProperty(
        @WebParam(name = "in0", targetNamespace = "http://soap")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://soap")
        String in1);

    @WebMethod(operationName = "RegisterWebService", action = "")
    public void registerWebService(
        @WebParam(name = "in0", targetNamespace = "http://soap")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://soap")
        String in1);

    @WebMethod(operationName = "GetEventProperty", action = "")
    @WebResult(name = "out", targetNamespace = "http://soap")
    public ArrayOfMyEventProperty getEventProperty(
        @WebParam(name = "in0", targetNamespace = "http://soap")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://soap")
        int in1);

}
