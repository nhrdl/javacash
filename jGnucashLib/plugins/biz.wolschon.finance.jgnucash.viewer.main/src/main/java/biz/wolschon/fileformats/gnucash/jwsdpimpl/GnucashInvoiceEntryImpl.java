/**
 * GnucashInvoiceEntryImpl.java
 * License: GPLv3 or later
 * Created on 13.05.2005
 * (c) 2005 by "Wolschon Softwaredesign und Beratung".
 *
 *
 * -----------------------------------------------------------
 * major Changes:
 *  13.05.2005 - initial version
 *  03.01.2010 - support for invoice-entries without an invoice-id
 * ...
 *
 */
package biz.wolschon.fileformats.gnucash.jwsdpimpl;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.wolschon.fileformats.gnucash.GnucashInvoice;
import biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry;
import biz.wolschon.fileformats.gnucash.GnucashTaxTable;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEntryType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.ObjectFactory;
import biz.wolschon.numbers.FixedPointNumber;

/**
 *
 * created: 13.05.2005 <br/>
 * Implementation of GnucashInvoiceEntry that uses JWSDP.
 * @author <a href="mailto:Marcus@Wolschon.biz">Marcus Wolschon</a>
 */
public class GnucashInvoiceEntryImpl extends GnucashObjectImpl implements GnucashInvoiceEntry {

    /**
     * Our logger for debug- and error-ourput.
     */
    private static final Log LOG = LogFactory.getLog(GnucashInvoiceEntryImpl.class);


    /**
     * Format of the JWSDP-Field for the entry-date.
     */
    protected static final DateFormat ENTRYDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZZZZ");

    /**
     * the JWSDP-object we are facading.
     */
    private final GncGncEntryType jwsdpPeer;



    /**
     * This constructor is used when an invoice is created
     * by java-code.
     * @param invoice The invoice we belong to.
     * @param peer the JWSDP-Object we are wrapping.
     * @throws JAXBException on problems with the xml-backend
     */
    public GnucashInvoiceEntryImpl(
            final GnucashInvoice invoice,
            final GncGncEntryType peer) throws JAXBException {
        super((peer.getEntrySlots() == null) ? new ObjectFactory().createSlotsType() : peer.getEntrySlots(), invoice.getFile());
        if (peer.getEntrySlots() == null) {
            peer.setEntrySlots(getSlots());
        }

        myInvoice = invoice;
        jwsdpPeer = peer;

       invoice.addEntry(this);
    }

    /**
     * This code is used when an invoice is loaded from a file.
     * @param gncFile tne file we belong to
     * @param peer the JWSDP-object we are facading.
     * @throws JAXBException on problems with the xml-backend
     * @see #jwsdpPeer
     */
    public GnucashInvoiceEntryImpl(
            final GncGncEntryType peer,
            final GnucashFileImpl gncFile) throws JAXBException {
        super((peer.getEntrySlots() == null) ? new ObjectFactory().createSlotsType() : peer.getEntrySlots(), gncFile);
        jwsdpPeer = peer;

        // an exception is thrown here if we have an invoice-ID but the invoice does not exist
        GnucashInvoice invoice = getInvoice();
        if (invoice != null) {
           // ...so we only need to handle the case of having no invoice-id at all
           invoice.addEntry(this);
        }
    }
    /**
     * {@inheritDoc}
     */
    public String getId() {
        return jwsdpPeer.getEntryGuid().getValue();
    }

    /**
     * MAY RETURN NULL.
     * {@inheritDoc}
     */
    public String getInvoiceID() {
        if (jwsdpPeer.getEntryInvoice() == null && jwsdpPeer.getEntryBill() == null) {
            LOG.error("file contains an invoice-entry with GUID="
                    + getId() + " without an invoice-element(customer) AND "
                    + "without a bill-element(supplier)");
        }
        if (jwsdpPeer.getEntryInvoice() == null) {
            return null;
        }
        return jwsdpPeer.getEntryInvoice().getValue();
    }


    /**
     * The invoice this entry is from.
     */
    private GnucashInvoice myInvoice;

    /**
     * {@inheritDoc}
     */
    public GnucashInvoice getInvoice() {
        if (myInvoice == null) {
            String id = getInvoiceID();
            if (id != null) {
               myInvoice = getGnucashFile().getInvoiceByID(id);
               if (myInvoice == null) {
                   throw new IllegalStateException("no invoice with id '"
                                    + getInvoiceID()
                                    + "' for invoiceEntry with id '"
                                    + getId()
                                    + "'");
               }
            }
        }
        return myInvoice;
    }


    /**
     * The taxtable in the gnucash xml-file.
     * It defines what sales-tax-rates are known.
     */
    private GnucashTaxTable myTaxtable;

    /**
     * @param aTaxtable the taxtable to set
     */
    protected void setTaxtable(final GnucashTaxTable aTaxtable) {
        myTaxtable = aTaxtable;
    }

    /**
     *
     * @return The taxtable in the gnucash xml-file.
     *         It defines what sales-tax-rates are known.
     */
    protected GnucashTaxTable getTaxTable() {
        if (myTaxtable == null) {
            String taxTableId = jwsdpPeer.getEntryITaxtable().getValue();
            if (taxTableId == null) {
                System.err.println("Invoice with id '"
                         + getId()
                         + "' is taxable but has empty id for the taxtable");
                return null;
            }
            myTaxtable = getGnucashFile().getTaxTableByID(taxTableId);

            if (myTaxtable == null) {
                System.err.println("Invoice with id '"
                                 + getId()
                                 + "' is taxable but has an unknown "
                                 + "taxtable-id '"
                                 + taxTableId
                                 + "'!");
            }
        }

        return myTaxtable;
    }

    /**
     * @return never null, "0%" if no taxtable is there
     */
    public String getApplicableTaxPercendFormatet() {
        FixedPointNumber applicableTaxPercend = getApplicableTaxPercend();
        if (applicableTaxPercend == null) {
            return this.getPercentFormat().format(0);
        }
        return this.getPercentFormat().format(applicableTaxPercend);
    }

    /**
     * @return e.g. "0.16" for "16%"
     */
    public FixedPointNumber getApplicableTaxPercend() {

        if (!isTaxable()) {
            return new FixedPointNumber();
        }


        if (!jwsdpPeer.getEntryITaxtable().getType().equals("guid")) {
            System.err.println("Invoice with id '"
                             + getId()
                             + "' has i-taxtable with type='"
                             + jwsdpPeer.getEntryITaxtable().getType()
                             + "' != 'guid'");
        }


        GnucashTaxTable taxtable = getTaxTable();

        if (taxtable == null) {
             System.err.println("Invoice with id '"
                              + getId()
                              + "' is taxable but has an unknown taxtable! "
                              + "Assuming 16%");
             return new FixedPointNumber("1600000/10000000");
        }


        GnucashTaxTable.TaxTableEntry taxTableEntry = taxtable.getEntries().iterator().next();
        if (!taxTableEntry.getType().equals(GnucashTaxTable.TaxTableEntry.TYPE_PERCENT)) {
            System.err.println("Invoice with id '"
                              + getId()
                              + "' is taxable but has a taxtable "
                              + "that is not in percent but in '"
                              + taxTableEntry.getType()
                              + "' ! Assuming 16%");
            return new FixedPointNumber("1600000/10000000");
       }

        FixedPointNumber val = taxTableEntry.getAmount();

//      the file contains 16 for 16%, we need 0,16
        return ((FixedPointNumber) val.clone()).divideBy(new FixedPointNumber("100"));

    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#getPrice()
     */
    public FixedPointNumber getPrice() {
        return new FixedPointNumber(jwsdpPeer.getEntryIPrice());
    }

    /**
     * @return the price of a single of the ${@link #getQuantity()} items of
     * type ${@link #getAction()}.
     */
    public String getPriceFormatet() {
        return ((GnucashInvoiceImpl) getInvoice()).getCurrencyFormat().format(getPrice());
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#getSum()
     */
    public FixedPointNumber getSum() {
        return getPrice().multiply(getQuantity());
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#getSum()
     */
    public String getSumFormatet() {
        return ((GnucashInvoiceImpl) getInvoice()).getCurrencyFormat().format(getSum());
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#isTaxable()
     */
    public boolean isTaxable() {
        return (jwsdpPeer.getEntryITaxable() == 1);
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#getSumInclTaxes()
     */
    public FixedPointNumber getSumInclTaxes() {
        if (jwsdpPeer.getEntryITaxincluded() == 1) {
            return getSum();
        }


        return getSum().multiply(getApplicableTaxPercend().add(1));
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#getSumExclTaxes()
     */
    public FixedPointNumber getSumExclTaxes() {

//      System.err.println("debug: GnucashInvoiceEntryImpl.getSumExclTaxes():"
//      taxIncluded="+jwsdpPeer.getEntryITaxincluded()+" getSum()="+getSum()+" getApplicableTaxPercend()="+getApplicableTaxPercend());

        if (jwsdpPeer.getEntryITaxincluded() == 0) {
            return getSum();
        }


        return getSum().divideBy(getApplicableTaxPercend().add(1));
    }


    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#getAction()
     */
    public String getAction() {
        return jwsdpPeer.getEntryAction();
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry#getQuantity()
     */
    public FixedPointNumber getQuantity() {
        String val = getJwsdpPeer().getEntryQty();
        return new FixedPointNumber(val);
    }
    /**
     * {@inheritDoc}
     * @deprecated use ${@link #getQuantityFormated()}
     */
    @Deprecated
    public String getQuantityFormatet() {
        return getNumberFormat().format(getQuantity());
    }

    /**
     * {@inheritDoc}
     */
    public String getQuantityFormated() {
        return getNumberFormat().format(getQuantity());
    }

    /**
     *
     * {@inheritDoc}
     */
    public String getDescription() {
        if (getJwsdpPeer().getEntryDescription() == null) {
            return "";
        }

        return getJwsdpPeer().getEntryDescription();
    }


    /**
     * {@inheritDoc}
     */
    public int compareTo(final GnucashInvoiceEntry o) {
        try {
            GnucashInvoiceEntry otherSplit = o;
            GnucashInvoice otherTrans = otherSplit.getInvoice();
            if (otherTrans != null && getInvoice() != null) {
                int c = otherTrans.compareTo(getInvoice());
                if (c != 0) {
                    return c;
                }
            }


            int c = otherSplit.getId().compareTo(getId());
            if (c != 0) {
                return c;
            }


            if (o != this) {
                LOG.error("doublicate invoice-entry-id!! "
                                 + otherSplit.getId()
                                 + " and "
                                 + getId());
            }


            return 0;

        } catch (Exception e) {
            LOG.error("error comparing", e);
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[GnucashInvoiceEntryImpl:");
        buffer.append(" id: ");
        buffer.append(getId());
        buffer.append(" invoiceID: ");
        buffer.append(getInvoiceID());
//      buffer.append(" invoice: ");
//      GnucashInvoice invoice = getInvoice();
//      buffer.append(invoice==null?"null":invoice.getName());
        buffer.append(" description: ");
        buffer.append(getDescription());
        buffer.append(" action: ");
        buffer.append(getAction());
        buffer.append(" value X quantity: ");
        buffer.append(getPrice()).append(" X ").append(getQuantity());
        buffer.append("]");
        return buffer.toString();
    }

    /**
     *
     * @return The JWSDP-Object we are wrapping.
     */
    protected GncGncEntryType getJwsdpPeer() {
        return jwsdpPeer;
    }

    /**
     * {@inheritDoc}
     */
    public String getSumInclTaxesFormatet() {
        return ((GnucashInvoiceImpl) getInvoice()).getCurrencyFormat().format(getSumInclTaxes());
    }

    /**
     * {@inheritDoc}
     */
    public String getSumExclTaxesFormatet() {
        return ((GnucashInvoiceImpl) getInvoice()).getCurrencyFormat().format(getSumExclTaxes());
    }


    /**
     * The numberFormat to use for non-currency-numbers  for default-formating.<br/>
     * Please access only using {@link #getNumberFormat()}.
     * @see #getNumberFormat()
     */
    private NumberFormat numberFormat = null;

    /**
    *
    * @return the number-format to use for non-currency-numbers if no locale is given.
    */
   protected NumberFormat getNumberFormat() {
       if (numberFormat == null) {
           numberFormat = NumberFormat.getInstance();
       }

       return numberFormat;
   }
   /**
    * The numberFormat to use for percentFormat-numbers  for default-formating.<br/>
    * Please access only using {@link #getPercentFormat()}.
    * @see #getPercentFormat()
    */
   private NumberFormat percentFormat = null;

   /**
   *
   * @return the number-format to use for percentage-numbers if no locale is given.
   */
  protected NumberFormat getPercentFormat() {
      if (percentFormat == null) {
          percentFormat = NumberFormat.getPercentInstance();
      }

      return percentFormat;
  }


}
