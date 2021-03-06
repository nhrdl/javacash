//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v@@BUILD_VERSION@@ 
// 	See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 	Any modifications to this file will be lost upon recompilation of the source schema. 
// 	Generated on: 2011.03.27 um 12:23:14 MESZ 
//


package biz.wolschon.fileformats.gnucash.jwsdpimpl.generated;


/**
 * Java content class for anonymous complex type.
 * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 489)
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employee_guid">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="employee_username">
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/element>
 *         &lt;element name="employee_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employee_addr" type="{}address"/>
 *         &lt;element name="employee_language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employee_active" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="employee_workday" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employee_rate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employee_currency">
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
 *         &lt;element name="employee_ccard" minOccurs="0">
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
public interface GncGncEmployeeType {


    /**
     * Gets the value of the employeeRate property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getEmployeeRate();

    /**
     * Sets the value of the employeeRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setEmployeeRate(java.lang.String value);

    /**
     * Gets the value of the employeeUsername property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getEmployeeUsername();

    /**
     * Sets the value of the employeeUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setEmployeeUsername(java.lang.String value);

    /**
     * Gets the value of the employeeCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCurrencyType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCurrencyType getEmployeeCurrency();

    /**
     * Sets the value of the employeeCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCurrencyType}
     */
    void setEmployeeCurrency(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCurrencyType value);

    /**
     * Gets the value of the employeeAddr property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address getEmployeeAddr();

    /**
     * Sets the value of the employeeAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address}
     */
    void setEmployeeAddr(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address value);

    /**
     * Gets the value of the employeeWorkday property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getEmployeeWorkday();

    /**
     * Sets the value of the employeeWorkday property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setEmployeeWorkday(java.lang.String value);

    /**
     * Gets the value of the employeeCcard property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCcardType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCcardType getEmployeeCcard();

    /**
     * Sets the value of the employeeCcard property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCcardType}
     */
    void setEmployeeCcard(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeCcardType value);

    /**
     * Gets the value of the employeeActive property.
     * 
     */
    int getEmployeeActive();

    /**
     * Sets the value of the employeeActive property.
     * 
     */
    void setEmployeeActive(int value);

    /**
     * Gets the value of the employeeLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getEmployeeLanguage();

    /**
     * Sets the value of the employeeLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setEmployeeLanguage(java.lang.String value);

    /**
     * Gets the value of the employeeGuid property.
     * 
     * @return
     *     possible object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeGuidType}
     */
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeGuidType getEmployeeGuid();

    /**
     * Sets the value of the employeeGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeGuidType}
     */
    void setEmployeeGuid(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEmployeeType.EmployeeGuidType value);

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
     * Gets the value of the employeeId property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    java.lang.String getEmployeeId();

    /**
     * Sets the value of the employeeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    void setEmployeeId(java.lang.String value);


    /**
     * Java content class for anonymous complex type.
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 522)
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
    public interface EmployeeCcardType {


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
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 514)
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
    public interface EmployeeCurrencyType {


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
     * 	<p>The following schema fragment specifies the expected 	content contained within this java content object. 	(defined at file:/C:/Users/fox/Documents/My%20Dropbox/Sourcecode/jGnucashLib-GPL/plugins/biz.wolschon.finance.jgnucash.viewer.main/source/gnucash.xsd line 492)
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
    public interface EmployeeGuidType {


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
