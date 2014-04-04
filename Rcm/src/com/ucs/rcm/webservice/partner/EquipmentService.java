package com.ucs.rcm.webservice.partner;






import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "EquipmentService", targetNamespace = "http://www.china-gentech.com/sggis/service")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface EquipmentService {


    @WebMethod(operationName = "getEquipment", action = "")
    @WebResult(name = "outputXML", targetNamespace = "http://www.china-gentech.com/sggis/service")
    public String getEquipment(
        @WebParam(name = "equipmentId", targetNamespace = "http://www.china-gentech.com/sggis/service")
        String equipmentId,
        @WebParam(name = "type", targetNamespace = "http://www.china-gentech.com/sggis/service")
        String type);

}
