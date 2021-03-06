//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated;


/**
 * Java content class for anonymous complex type.
 * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 416)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cust_guid">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="cust_name">
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="cust_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cust_addr" type="{}address"/>
 *         &lt;element name="cust_shipaddr" type="{}address"/>
 *         &lt;element name="cust_notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cust_terms" minOccurs="0">
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
 *         &lt;element name="cust_taxincluded" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cust_active" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cust_discount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cust_credit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cust_currency">
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
 *         &lt;element name="cust_use-tt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cust_taxtable" minOccurs="0">
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
 *         &lt;element name="cust_slots" type="{}slots_type" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface GncGncCustomerType {


    /**
     * Gets the value of the custShipaddr property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address getCustShipaddr();

    /**
     * Sets the value of the custShipaddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    void setCustShipaddr(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address value);

    /**
     * Gets the value of the custTaxincluded property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCustTaxincluded();

    /**
     * Sets the value of the custTaxincluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCustTaxincluded(java.lang.String value);

    /**
     * Gets the value of the custTerms property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTermsType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTermsType getCustTerms();

    /**
     * Sets the value of the custTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTermsType}
     */
    void setCustTerms(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTermsType value);

    /**
     * Gets the value of the custActive property.
     * 
     */
    int getCustActive();

    /**
     * Sets the value of the custActive property.
     * 
     */
    void setCustActive(int value);

    /**
     * Gets the value of the custCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustCurrencyType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustCurrencyType getCustCurrency();

    /**
     * Sets the value of the custCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustCurrencyType}
     */
    void setCustCurrency(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustCurrencyType value);

    /**
     * Gets the value of the custTaxtable property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTaxtableType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTaxtableType getCustTaxtable();

    /**
     * Sets the value of the custTaxtable property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTaxtableType}
     */
    void setCustTaxtable(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustTaxtableType value);

    /**
     * Gets the value of the custUseTt property.
     * 
     */
    int getCustUseTt();

    /**
     * Sets the value of the custUseTt property.
     * 
     */
    void setCustUseTt(int value);

    /**
     * Gets the value of the custDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCustDiscount();

    /**
     * Sets the value of the custDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCustDiscount(java.lang.String value);

    /**
     * Gets the value of the custCredit property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCustCredit();

    /**
     * Sets the value of the custCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCustCredit(java.lang.String value);

    /**
     * Gets the value of the custAddr property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address getCustAddr();

    /**
     * Sets the value of the custAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    void setCustAddr(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address value);

    /**
     * Gets the value of the custSlots property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotsType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotsType getCustSlots();

    /**
     * Sets the value of the custSlots property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotsType}
     */
    void setCustSlots(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotsType value);

    /**
     * Gets the value of the custId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCustId();

    /**
     * Sets the value of the custId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCustId(java.lang.String value);

    /**
     * Gets the value of the custName property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCustName();

    /**
     * Sets the value of the custName property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCustName(java.lang.String value);

    /**
     * Gets the value of the custGuid property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustGuidType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustGuidType getCustGuid();

    /**
     * Sets the value of the custGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustGuidType}
     */
    void setCustGuid(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType.CustGuidType value);

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
     * Gets the value of the custNotes property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getCustNotes();

    /**
     * Sets the value of the custNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setCustNotes(java.lang.String value);


    /**
     * Java content class for anonymous complex type.
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 458)
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
    public interface CustCurrencyType {


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
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 419)
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
    public interface CustGuidType {


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
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 467)
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
    public interface CustTaxtableType {


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
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 439)
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
    public interface CustTermsType {


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
