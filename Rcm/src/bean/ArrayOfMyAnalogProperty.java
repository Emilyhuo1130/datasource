
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMyAnalogProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMyAnalogProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MyAnalogProperty" type="{http://bean}MyAnalogProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMyAnalogProperty", propOrder = {
    "myAnalogProperty"
})
public class ArrayOfMyAnalogProperty {

    @XmlElement(name = "MyAnalogProperty", required = true, nillable = true)
    protected List<MyAnalogProperty> myAnalogProperty;

    /**
     * Gets the value of the myAnalogProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the myAnalogProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMyAnalogProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MyAnalogProperty }
     * 
     * 
     */
    public List<MyAnalogProperty> getMyAnalogProperty() {
        if (myAnalogProperty == null) {
            myAnalogProperty = new ArrayList<MyAnalogProperty>();
        }
        return this.myAnalogProperty;
    }

}
