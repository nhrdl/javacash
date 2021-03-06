//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated;


/**
 * Java content class for anonymous complex type.
 * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 301)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vendor_guid">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="vendor_name">
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="vendor_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendor_addr" type="{}address"/>
 *         &lt;element name="vendor_terms" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="type" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *                       &lt;enumeration value="guid"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="vendor_taxincluded" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendor_active" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vendor_currency">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{}cmdty_space"/>
 *                   &lt;element ref="{}cmdty_id"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="vendor_use-tt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface GncGncVendorType {


    /**
     * Gets the value of the vendorUseTt property.
     * 
     */
    int getVendorUseTt();

    /**
     * Sets the value of the vendorUseTt property.
     * 
     */
    void setVendorUseTt(int value);

    /**
     * Gets the value of the vendorName property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getVendorName();

    /**
     * Sets the value of the vendorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setVendorName(java.lang.String value);

    /**
     * Gets the value of the vendorTerms property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorTermsType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorTermsType getVendorTerms();

    /**
     * Sets the value of the vendorTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorTermsType}
     */
    void setVendorTerms(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorTermsType value);

    /**
     * Gets the value of the vendorCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorCurrencyType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorCurrencyType getVendorCurrency();

    /**
     * Sets the value of the vendorCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorCurrencyType}
     */
    void setVendorCurrency(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorCurrencyType value);

    /**
     * Gets the value of the vendorId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getVendorId();

    /**
     * Sets the value of the vendorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setVendorId(java.lang.String value);

    /**
     * Gets the value of the vendorAddr property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address getVendorAddr();

    /**
     * Sets the value of the vendorAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    void setVendorAddr(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address value);

    /**
     * Gets the value of the vendorGuid property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorGuidType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorGuidType getVendorGuid();

    /**
     * Sets the value of the vendorGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorGuidType}
     */
    void setVendorGuid(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncVendorType.VendorGuidType value);

    /**
     * Gets the value of the vendorActive property.
     * 
     */
    int getVendorActive();

    /**
     * Sets the value of the vendorActive property.
     * 
     */
    void setVendorActive(int value);

    /**
     * Gets the value of the vendorTaxincluded property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getVendorTaxincluded();

    /**
     * Sets the value of the vendorTaxincluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setVendorTaxincluded(java.lang.String value);

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getVersion();

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setVersion(java.lang.String value);


    /**
     * Java content class for anonymous complex type.
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 339)
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{}cmdty_space"/>
     *         &lt;element ref="{}cmdty_id"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     */
    public interface VendorCurrencyType {


        /**
         * Gets the value of the cmdtyId property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getCmdtyId();

        /**
         * Sets the value of the cmdtyId property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setCmdtyId(java.lang.String value);

        /**
         * Gets the value of the cmdtySpace property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getCmdtySpace();

        /**
         * Sets the value of the cmdtySpace property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setCmdtySpace(java.lang.String value);

    }


    /**
     * Java content class for anonymous complex type.
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 304)
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     */
    public interface VendorGuidType {


        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getValue();

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setValue(java.lang.String value);

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getType();

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setType(java.lang.String value);

    }


    /**
     * Java content class for anonymous complex type.
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 322)
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="type" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
     *             &lt;enumeration value="guid"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     */
    public interface VendorTermsType {


        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getValue();

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setValue(java.lang.String value);

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link java.lang.String}
         */
        java.lang.String getType();

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link java.lang.String}
         */
        void setType(java.lang.String value);

    }

}
