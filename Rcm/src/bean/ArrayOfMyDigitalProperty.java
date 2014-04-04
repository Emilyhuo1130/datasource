
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMyDigitalProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMyDigitalProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MyDigitalProperty" type="{http://bean}MyDigitalProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMyDigitalProperty", propOrder = {
    "myDigitalProperty"
})
public class ArrayOfMyDigitalProperty {

    @XmlElement(name = "MyDigitalProperty", required = true, nillable = true)
    protected List<MyDigitalProperty> myDigitalProperty;

    /**
     * Gets the value of the myDigitalProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the myDigitalProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMyDigitalProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MyDigitalProperty }
     * 
     * 
     */
    public List<MyDigitalProperty> getMyDigitalProperty() {
        if (myDigitalProperty == null) {
            myDigitalProperty = new ArrayList<MyDigitalProperty>();
        }
        return this.myDigitalProperty;
    }

}
