/**
 * GnucashWritableCustomer.java
 * Created on 11.06.2005
 * (c) 2005 by "Wolschon Softwaredesign und Beratung".
 *
 *  Permission is granted to use, modify, publish and sub-license this code
 *  as specified in the contract. If nothing else is specified these rights
 *  are given non-exclusively with no restrictions solely to the contractor(s).
 *  If no specified otherwise I reserve the right to use, modify, publish and
 *  sub-license this code to other parties myself.
 *
 * Otherwise, this code is made available under GPLv3 or later.
 *
 * -----------------------------------------------------------
 * major Changes:
 *  11.06.2005 - initial version
 * ...
 *
 */
package biz.wolschon.fileformats.gnucash;

import javax.xml.bind.JAXBException;

import biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashObject;

/**
 * created: 11.06.2005 .<br/>
 * Customer that can be modified
 * @author <a href="mailto:Marcus@Wolschon.biz">Marcus Wolschon</a>
 */
public interface GnucashWritableCustomer extends GnucashCustomer , GnucashWritableObject{

    void remove();





    public interface  WritableAddress extends GnucashCustomer.Address {


        void setAddressName(String a) ;

        void setAddressLine1(String a);

        void setAddressLine2(String a) ;

        void setAddressLine3(String a) ;

        void setAddressLine4(String a) ;

        void setTel(String a) ;

        void setFax(String a) ;

        void setEmail(String a) ;
    }



    /**
     * @see ${@link GnucashCustomer#getCustomerNumber()}
     * @param number the user-assigned number of this customer (may contain non-digits)
     */
    void setCustomerNumber(String number);

    void setDiscount(String discount);

    /**
     * @param notes user-defined notes about the customer (may be null)
     */
    void setNotes(String notes);



    void setName(String name);

    void setAddress(GnucashCustomer.Address adr);

    void setShippingAddress(GnucashCustomer.Address adr);

    GnucashWritableCustomer.WritableAddress getWritableAddress();

    GnucashWritableCustomer.WritableAddress getWritableShippingAddress();

    GnucashWritableCustomer.WritableAddress getAddress();

    GnucashWritableCustomer.WritableAddress getShippingAddress();


    /**
     * @param name the name of the user-defined attribute
     * @param value the value or null if not set
     * @throws JAXBException on problems with the xml-backend
     * @see {@link GnucashObject#getUserDefinedAttribute(String)}
     */
    void setUserDefinedAttribute(final String name, final String value) throws JAXBException;
}
