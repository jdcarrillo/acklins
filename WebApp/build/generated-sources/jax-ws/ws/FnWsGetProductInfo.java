
package ws;

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
 *         &lt;element name="snum" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="is_back_office" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="security_key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "snum",
    "isBackOffice",
    "securityKey"
})
@XmlRootElement(name = "fn_ws_get_product_info", namespace = "http://tempuri.org/")
public class FnWsGetProductInfo {

    @XmlElement(namespace = "http://tempuri.org/")
    protected long snum;
    @XmlElement(name = "is_back_office", namespace = "http://tempuri.org/")
    protected int isBackOffice;
    @XmlElement(name = "security_key", namespace = "http://tempuri.org/")
    protected String securityKey;

    /**
     * Obtiene el valor de la propiedad snum.
     * 
     */
    public long getSnum() {
        return snum;
    }

    /**
     * Define el valor de la propiedad snum.
     * 
     */
    public void setSnum(long value) {
        this.snum = value;
    }

    /**
     * Obtiene el valor de la propiedad isBackOffice.
     * 
     */
    public int getIsBackOffice() {
        return isBackOffice;
    }

    /**
     * Define el valor de la propiedad isBackOffice.
     * 
     */
    public void setIsBackOffice(int value) {
        this.isBackOffice = value;
    }

    /**
     * Obtiene el valor de la propiedad securityKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityKey() {
        return securityKey;
    }

    /**
     * Define el valor de la propiedad securityKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityKey(String value) {
        this.securityKey = value;
    }

}
