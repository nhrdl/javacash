//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated;


/**
 * Java content class for anonymous complex type.
 * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 1128)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="slot_key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}slot_value"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface SlotType {


    /**
     * Gets the value of the slotValue property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType}
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValue}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType getSlotValue();

    /**
     * Sets the value of the slotValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType}
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValue}
     */
    void setSlotValue(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType value);

    /**
     * Gets the value of the slotKey property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getSlotKey();

    /**
     * Sets the value of the slotKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setSlotKey(java.lang.String value);

}
