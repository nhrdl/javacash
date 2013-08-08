/**
 * GnucashCustomerWritingImpl.java
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
package biz.wolschon.fileformats.gnucash.jwsdpimpl;


import java.beans.PropertyChangeSupport;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.wolschon.fileformats.gnucash.GnucashWritableCustomer;
import biz.wolschon.fileformats.gnucash.GnucashWritableFile;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.ObjectFactory;

/**
 * created: 11.06.2005<br/>
 * Modifiable version of a customer.<br/>
 *
 * Additional supported properties for PropertyChangeListeners:
 * <ul>
 *  <li>name</li>
 *  <li>notes</li>
 *  <li>discount</li>
 *  <li>customerNumber</li>
 * </ul>
 * @author <a href="mailto:Marcus@Wolschon.biz">Marcus Wolschon</a>
 */
public class GnucashCustomerWritingImpl extends GnucashCustomerImpl implements GnucashWritableCustomer {

    /**
     * Automatically created logger for debug and error-output.
     */
    private static final Log LOGGER = LogFactory.getLog(GnucashCustomerWritingImpl.class);

    /**
     * Our helper to implement the GnucashWritableObject-interface.
     */
    private final GnucashWritableObjectHelper helper = new GnucashWritableObjectHelper(this);

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableObject#setUserDefinedAttribute(java.lang.String, java.lang.String)
     */
    public void setUserDefinedAttribute(final String name, final String value) throws JAXBException {
        helper.setUserDefinedAttribute(name, value);
    }

    /**
     * Please use ${@link GnucashWritableFile#createWritableCustomer()}.
     * @param file the file we belong to
     * @param jwsdpPeer the JWSDP-object we are facading.
     * @throws JAXBException on problems with the xml-backend
     */
    protected GnucashCustomerWritingImpl(final GncGncCustomerType jwsdpPeer, final GnucashFileWritingImpl file) throws JAXBException {
        super(jwsdpPeer, file);
    }

    /**
     * Please use ${@link GnucashWritableFile#createWritableCustomer()}.
     * @param file the file we belong to
     * @param jwsdpPeer the JWSDP-object we are facading.
     * @param id the ID we shall have
     */
    protected GnucashCustomerWritingImpl(final GnucashFileWritingImpl file,
                                         final String id) throws JAXBException {
        super(createCustomer(file, id), file);
    }

    /**
     * Delete this customer and remove it from the file.
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#remove()
     */
    public void remove() {
        GncGncCustomerType peer = getJwsdpPeer();
        (getGnucashFile()).getRootElement().getGncBook().getBookElements().remove(peer);
        (getGnucashFile()).removeCustomer(this);
    }


    /**
     * Creates a new Transaction and add's it to the given gnucash-file
     * Don't modify the ID of the new transaction!
     *
     * @param file the file we will belong to
     * @param guid the ID we shall have
     * @return a new jwsdp-peer alredy entered into th jwsdp-peer of the file
     * @throws JAXBException on XML-backend-errors
     */
    protected static GncGncCustomerType createCustomer(final GnucashFileWritingImpl file, final String guid) throws JAXBException {

        if (guid == null) {
            throw new IllegalArgumentException("null guid given!");
        }


        ObjectFactory factory = file.getObjectFactory();

        GncGncCustomerType customer = file.createGncGncCustomerType();

        customer.setCustTaxincluded("USEGLOBAL");
        customer.setVersion("2.0.0");
        customer.setCustDiscount("0/1");
        customer.setCustCredit("0/1");
        customer.setCustUseTt(0);
        customer.setCustName("no name given");


        {
            GncGncCustomerType.CustGuidType id = factory.createGncGncCustomerTypeCustGuidType();
            id.setType("guid");
            id.setValue(guid);
            customer.setCustGuid(id);
            customer.setCustId(id.getValue());
        }

        {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address addr = factory.createAddress();
            addr.setAddrAddr1("");
            addr.setAddrAddr2("");
            addr.setAddrName("");
            addr.setAddrAddr3("");
            addr.setAddrAddr4("");
            addr.setAddrName("");
            addr.setAddrEmail("");
            addr.setAddrFax("");
            addr.setAddrPhone("");
            addr.setVersion("2.0.0");
            customer.setCustAddr(addr);
        }

        {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address saddr = factory.createAddress();
            saddr.setAddrAddr1("");
            saddr.setAddrAddr2("");
            saddr.setAddrAddr3("");
            saddr.setAddrAddr4("");
            saddr.setAddrName("");
            saddr.setAddrEmail("");
            saddr.setAddrFax("");
            saddr.setAddrPhone("");
            saddr.setVersion("2.0.0");
            customer.setCustShipaddr(saddr);
        }

        {
            GncGncCustomerType.CustCurrencyType currency = factory.createGncGncCustomerTypeCustCurrencyType();
            currency.setCmdtyId(file.getDefaultCurrencyID());
            currency.setCmdtySpace("ISO4217");
            customer.setCustCurrency(currency);
        }

        customer.setCustActive(1);


        file.getRootElement().getGncBook().getBookElements().add(customer);
        file.setModified(true);
        return customer;
    }



    /**
     * The gnucash-file is the top-level class to contain everything.
     * @return the file we are associated with
     */
    public GnucashFileWritingImpl getWritableGnucashFile() {
        return (GnucashFileWritingImpl) super.getGnucashFile();
    }


    /**
     * The gnucash-file is the top-level class to contain everything.
     * @return the file we are associated with
     */
    @Override
    public GnucashFileWritingImpl getGnucashFile() {
        return (GnucashFileWritingImpl) super.getGnucashFile();
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#setCustomerNumber(java.lang.String)
     */
    public void setCustomerNumber(final String number) {
        Object old = getCustomerNumber();
        getJwsdpPeer().setCustId(number);
        getGnucashFile().setModified(true);

        PropertyChangeSupport propertyChangeSupport = getPropertyChangeSupport();
        if (propertyChangeSupport != null) {
            propertyChangeSupport.firePropertyChange("customerNumber", old, number);
        }
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#setDiscount(java.lang.String)
     */
    public void setDiscount(final String discount) {
        Object old = getDiscount();
        getJwsdpPeer().setCustDiscount(discount);
        getGnucashFile().setModified(true);

        PropertyChangeSupport propertyChangeSupport = getPropertyChangeSupport();
        if (propertyChangeSupport != null) {
            propertyChangeSupport.firePropertyChange("discount", old, discount);
        }
    }

    /**
     * @param notes user-defined notes about the customer (may be null)
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#setNotes(String)
     */
    public void setNotes(final String notes) {
        Object old = getNotes();
        getJwsdpPeer().setCustNotes(notes);
        getGnucashFile().setModified(true);

        PropertyChangeSupport propertyChangeSupport = getPropertyChangeSupport();
        if (propertyChangeSupport != null) {
            propertyChangeSupport.firePropertyChange("notes", old, notes);
        }
    }


    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#setName(java.lang.String)
     */
    public void setName(final String name) {
        Object old = getName();
        getJwsdpPeer().setCustName(name);
        getGnucashFile().setModified(true);

        PropertyChangeSupport propertyChangeSupport = getPropertyChangeSupport();
        if (propertyChangeSupport != null) {
            propertyChangeSupport.firePropertyChange("name", old, name);
        }
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashCustomer#getAddress()
     */
    @Override
    public GnucashWritableCustomer.WritableAddress getAddress() {
        return getWritableAddress();
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashCustomer#getShippingAddress()
     */
    @Override
    public GnucashWritableCustomer.WritableAddress getShippingAddress() {
        return getWritableShippingAddress();
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#getWritableAddress()
     */
    public GnucashWritableCustomer.WritableAddress getWritableAddress() {
        return new WritableAddressImpl(getJwsdpPeer().getCustAddr());
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#getWritableShippingAddress()
     */
    public GnucashWritableCustomer.WritableAddress getWritableShippingAddress() {
        return new WritableAddressImpl(getJwsdpPeer().getCustShipaddr());
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#setAdress(biz.wolschon.fileformats.gnucash.GnucashCustomer.ShippingAdress)
     */
    public void setAddress(final Address adr) {
        /*if (adr instanceof AddressImpl) {
            AddressImpl adrImpl = (AddressImpl) adr;
            getJwsdpPeer().setCustAddr(adrImpl.getJwsdpPeer());
        } else */{
            try {
                if (getJwsdpPeer().getCustAddr() == null) {
                    getJwsdpPeer().setCustAddr(getGnucashFile().getObjectFactory().createAddress());
                }
            } catch (JAXBException e) {
                LOGGER.error("[JAXBException] Problem in "
                           + getClass().getName(),
                             e);
                // we will throw a NullPointerException in a second
            }

            getJwsdpPeer().getCustAddr().setAddrAddr1(adr.getAddressLine1());
            getJwsdpPeer().getCustAddr().setAddrAddr2(adr.getAddressLine2());
            getJwsdpPeer().getCustAddr().setAddrAddr3(adr.getAddressLine3());
            getJwsdpPeer().getCustAddr().setAddrAddr4(adr.getAddressLine4());
            getJwsdpPeer().getCustAddr().setAddrName(adr.getAddressName());
            getJwsdpPeer().getCustAddr().setAddrEmail(adr.getEmail());
            getJwsdpPeer().getCustAddr().setAddrFax(adr.getFax());
            getJwsdpPeer().getCustAddr().setAddrPhone(adr.getTel());
        }

        getGnucashFile().setModified(true);
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer#setShippingAddress(biz.wolschon.fileformats.gnucash.GnucashCustomer.ShippingAdress)
     */
    public void setShippingAddress(final Address adr) {
        /*if (adr instanceof AddressImpl) {
            AddressImpl adrImpl = (AddressImpl) adr;
            getJwsdpPeer().setCustShipaddr(adrImpl.getJwsdpPeer());
        } else */{
            try {
                if (getJwsdpPeer().getCustShipaddr() == null) {
                    getJwsdpPeer().setCustShipaddr(getGnucashFile().getObjectFactory().createAddress());
                }
            } catch (JAXBException e) {
                LOGGER.error("[JAXBException] Problem in "
                           + getClass().getName(),
                             e);
                // we will throw a NullPointerException in a second
            }

            getJwsdpPeer().getCustShipaddr().setAddrAddr1(adr.getAddressLine1());
            getJwsdpPeer().getCustShipaddr().setAddrAddr2(adr.getAddressLine2());
            getJwsdpPeer().getCustShipaddr().setAddrAddr3(adr.getAddressLine3());
            getJwsdpPeer().getCustShipaddr().setAddrAddr4(adr.getAddressLine4());
            getJwsdpPeer().getCustShipaddr().setAddrName(adr.getAddressName());
            getJwsdpPeer().getCustShipaddr().setAddrEmail(adr.getEmail());
            getJwsdpPeer().getCustShipaddr().setAddrFax(adr.getFax());
            getJwsdpPeer().getCustShipaddr().setAddrPhone(adr.getTel());
        }
        getGnucashFile().setModified(true);
    }

    /**
     *
     * (c) 2005 by <a href="http://Wolschon.biz>Wolschon Softwaredesign und Beratung</a>.<br/>
     * Project: gnucashReader<br/>
     * GnucashCustomerWritingImpl.java<br/>
     * created: 03.09.2005 11:31:32 <br/>
     * <br/><br/>
     * Writable implementation in {@link GnucashCustomerImpl.AddressImpl}
     * @author <a href="mailto:Marcus@Wolschon.biz">Marcus Wolschon</a>
     */
    public static class WritableAddressImpl extends GnucashCustomerImpl.AddressImpl implements GnucashWritableCustomer.WritableAddress {

        /**
         * @param jwsdpPeer
         */
        public WritableAddressImpl(final biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Address jwsdpPeer) {
            super(jwsdpPeer);
        }


        /**
         * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setAddressName(java.lang.String)
         */
        public void setAddressName(final String a) {
            getJwsdpPeer().setAddrName(a);
            //TODO: setModified()
        }

        /**
         *
         * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setAddressLine1(java.lang.String)
         */
        public void setAddressLine1(final String a) {
            getJwsdpPeer().setAddrAddr1(a);
            //TODO: setModified()
        }

        /**
         *
         * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setAddressLine2(java.lang.String)
         */
        public void setAddressLine2(final String a) {
            getJwsdpPeer().setAddrAddr2(a);
            //TODO: setModified()
        }

        /**
        *
        * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setAddressLine4(java.lang.String)
        */
       public void setAddressLine3(final String a) {
           getJwsdpPeer().setAddrAddr3(a);
           //TODO: setModified()
       }

       /**
       *
       * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setAddressLine4(java.lang.String)
       */
      public void setAddressLine4(final String a) {
          getJwsdpPeer().setAddrAddr4(a);
          //TODO: setModified()
      }

      /**
      *
      * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setTel(java.lang.String)
      */
     public void setTel(final String a) {
         getJwsdpPeer().setAddrPhone(a);
         //TODO: setModified()
     }

     /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setFac(java.lang.String)
     */
    public void setFax(final String a) {
        getJwsdpPeer().setAddrFax(a);
        //TODO: setModified()
    }
    /**
    *
    * @see biz.wolschon.fileformats.gnucash.GnucashWritableCustomer.WritableAddress#setEmail(java.lang.String)
    */
   public void setEmail(final String a) {
       getJwsdpPeer().setAddrEmail(a);
       //TODO: setModified()
   }

    }



}
