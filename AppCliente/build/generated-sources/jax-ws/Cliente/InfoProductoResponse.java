
package Cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InfoProductoResult" type="{http://tempuri.org/}ArrayOfArrayOfAnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "infoProductoResult"
})
@XmlRootElement(name = "InfoProductoResponse")
public class InfoProductoResponse {

    @XmlElement(name = "InfoProductoResult")
    protected ArrayOfArrayOfAnyType infoProductoResult;

    /**
     * Obtiene el valor de la propiedad infoProductoResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfArrayOfAnyType }
     *     
     */
    public ArrayOfArrayOfAnyType getInfoProductoResult() {
        return infoProductoResult;
    }

    /**
     * Define el valor de la propiedad infoProductoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfArrayOfAnyType }
     *     
     */
    public void setInfoProductoResult(ArrayOfArrayOfAnyType value) {
        this.infoProductoResult = value;
    }

}
