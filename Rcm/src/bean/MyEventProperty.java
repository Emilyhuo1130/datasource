
package bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MyEventProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MyEventProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appsysid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="devicecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stamptime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MyEventProperty", propOrder = {
    "appsysid",
    "devicecode",
    "groupcode",
    "message",
    "pointcode",
    "stamptime"
})
public class MyEventProperty {

    protected Integer appsysid;
    @XmlElementRef(name = "devicecode", namespace = "http://bean", type = JAXBElement.class)
    protected JAXBElement<String> devicecode;
    @XmlElementRef(name = "groupcode", namespace = "http://bean", type = JAXBElement.class)
    protected JAXBElement<String> groupcode;
    @XmlElementRef(name = "message", namespace = "http://bean", type = JAXBElement.class)
    protected JAXBElement<String> message;
    @XmlElementRef(name = "pointcode", namespace = "http://bean", type = JAXBElement.class)
    protected JAXBElement<String> pointcode;
    protected Long stamptime;

    /**
     * Gets the value of the appsysid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAppsysid() {
        return appsysid;
    }

    /**
     * Sets the value of the appsysid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAppsysid(Integer value) {
        this.appsysid = value;
    }

    /**
     * Gets the value of the devicecode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDevicecode() {
        return devicecode;
    }

    /**
     * Sets the value of the devicecode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDevicecode(JAXBElement<String> value) {
        this.devicecode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the groupcode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGroupcode() {
        return groupcode;
    }

    /**
     * Sets the value of the groupcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGroupcode(JAXBElement<String> value) {
        this.groupcode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMessage(JAXBElement<String> value) {
        this.message = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the pointcode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPointcode() {
        return pointcode;
    }

    /**
     * Sets the value of the pointcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPointcode(JAXBElement<String> value) {
        this.pointcode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the stamptime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStamptime() {
        return stamptime;
    }

    /**
     * Sets the value of the stamptime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStamptime(Long value) {
        this.stamptime = value;
    }

}
