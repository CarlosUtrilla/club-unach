
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
 *         &lt;element name="HistorialComprasResult" type="{http://tempuri.org/}ArrayOfArrayOfAnyType" minOccurs="0"/>
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
    "historialComprasResult"
})
@XmlRootElement(name = "HistorialComprasResponse")
public class HistorialComprasResponse {

    @XmlElement(name = "HistorialComprasResult")
    protected ArrayOfArrayOfAnyType historialComprasResult;

    /**
     * Obtiene el valor de la propiedad historialComprasResult.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfArrayOfAnyType }
     *     
     */
    public ArrayOfArrayOfAnyType getHistorialComprasResult() {
        return historialComprasResult;
    }

    /**
     * Define el valor de la propiedad historialComprasResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfArrayOfAnyType }
     *     
     */
    public void setHistorialComprasResult(ArrayOfArrayOfAnyType value) {
        this.historialComprasResult = value;
    }

}
