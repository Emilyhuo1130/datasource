package com.ucs.rcm.webservice.partner;





import javax.jws.WebService;

@WebService(serviceName = "EquipmentService", targetNamespace = "http://www.china-gentech.com/sggis/service", endpointInterface = "com.china_gentech.sggis.service.EquipmentService")
public class EquipmentServiceImpl
    implements EquipmentService
{


    public String getEquipment(String equipmentId, String type) {
        throw new UnsupportedOperationException();
    }

}
