
package soap;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDigitalProperty }
     * 
     */
    public GetDigitalProperty createGetDigitalProperty() {
        return new GetDigitalProperty();
    }

    /**
     * Create an instance of {@link RegisterWebServiceResponse }
     * 
     */
    public RegisterWebServiceResponse createRegisterWebServiceResponse() {
        return new RegisterWebServiceResponse();
    }

    /**
     * Create an instance of {@link GetDigitalPropertyResponse }
     * 
     */
    public GetDigitalPropertyResponse createGetDigitalPropertyResponse() {
        return new GetDigitalPropertyResponse();
    }

    /**
     * Create an instance of {@link GetEventProperty }
     * 
     */
    public GetEventProperty createGetEventProperty() {
        return new GetEventProperty();
    }

    /**
     * Create an instance of {@link GetAnalogPropertyResponse }
     * 
     */
    public GetAnalogPropertyResponse createGetAnalogPropertyResponse() {
        return new GetAnalogPropertyResponse();
    }

    /**
     * Create an instance of {@link GetEventPropertyResponse }
     * 
     */
    public GetEventPropertyResponse createGetEventPropertyResponse() {
        return new GetEventPropertyResponse();
    }

    /**
     * Create an instance of {@link GetAnalogProperty }
     * 
     */
    public GetAnalogProperty createGetAnalogProperty() {
        return new GetAnalogProperty();
    }

    /**
     * Create an instance of {@link RegisterWebService }
     * 
     */
    public RegisterWebService createRegisterWebService() {
        return new RegisterWebService();
    }

}
