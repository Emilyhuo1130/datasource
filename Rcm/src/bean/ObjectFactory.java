
package bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bean package. 
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

    private final static QName _MyDigitalPropertyDescription_QNAME = new QName("http://bean", "description");
    private final static QName _MyDigitalPropertyPointCode_QNAME = new QName("http://bean", "pointCode");
    private final static QName _MyEventPropertyDevicecode_QNAME = new QName("http://bean", "devicecode");
    private final static QName _MyEventPropertyPointcode_QNAME = new QName("http://bean", "pointcode");
    private final static QName _MyEventPropertyMessage_QNAME = new QName("http://bean", "message");
    private final static QName _MyEventPropertyGroupcode_QNAME = new QName("http://bean", "groupcode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfMyAnalogProperty }
     * 
     */
    public ArrayOfMyAnalogProperty createArrayOfMyAnalogProperty() {
        return new ArrayOfMyAnalogProperty();
    }

    /**
     * Create an instance of {@link ArrayOfMyEventProperty }
     * 
     */
    public ArrayOfMyEventProperty createArrayOfMyEventProperty() {
        return new ArrayOfMyEventProperty();
    }

    /**
     * Create an instance of {@link MyDigitalProperty }
     * 
     */
    public MyDigitalProperty createMyDigitalProperty() {
        return new MyDigitalProperty();
    }

    /**
     * Create an instance of {@link ArrayOfMyDigitalProperty }
     * 
     */
    public ArrayOfMyDigitalProperty createArrayOfMyDigitalProperty() {
        return new ArrayOfMyDigitalProperty();
    }

    /**
     * Create an instance of {@link MyEventProperty }
     * 
     */
    public MyEventProperty createMyEventProperty() {
        return new MyEventProperty();
    }

    /**
     * Create an instance of {@link MyAnalogProperty }
     * 
     */
    public MyAnalogProperty createMyAnalogProperty() {
        return new MyAnalogProperty();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "description", scope = MyDigitalProperty.class)
    public JAXBElement<String> createMyDigitalPropertyDescription(String value) {
        return new JAXBElement<String>(_MyDigitalPropertyDescription_QNAME, String.class, MyDigitalProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "pointCode", scope = MyDigitalProperty.class)
    public JAXBElement<String> createMyDigitalPropertyPointCode(String value) {
        return new JAXBElement<String>(_MyDigitalPropertyPointCode_QNAME, String.class, MyDigitalProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "devicecode", scope = MyEventProperty.class)
    public JAXBElement<String> createMyEventPropertyDevicecode(String value) {
        return new JAXBElement<String>(_MyEventPropertyDevicecode_QNAME, String.class, MyEventProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "pointcode", scope = MyEventProperty.class)
    public JAXBElement<String> createMyEventPropertyPointcode(String value) {
        return new JAXBElement<String>(_MyEventPropertyPointcode_QNAME, String.class, MyEventProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "message", scope = MyEventProperty.class)
    public JAXBElement<String> createMyEventPropertyMessage(String value) {
        return new JAXBElement<String>(_MyEventPropertyMessage_QNAME, String.class, MyEventProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "groupcode", scope = MyEventProperty.class)
    public JAXBElement<String> createMyEventPropertyGroupcode(String value) {
        return new JAXBElement<String>(_MyEventPropertyGroupcode_QNAME, String.class, MyEventProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "description", scope = MyAnalogProperty.class)
    public JAXBElement<String> createMyAnalogPropertyDescription(String value) {
        return new JAXBElement<String>(_MyDigitalPropertyDescription_QNAME, String.class, MyAnalogProperty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bean", name = "pointCode", scope = MyAnalogProperty.class)
    public JAXBElement<String> createMyAnalogPropertyPointCode(String value) {
        return new JAXBElement<String>(_MyDigitalPropertyPointCode_QNAME, String.class, MyAnalogProperty.class, value);
    }

}
