/**
 * GnucashFileWritingImpl.java
 * Created on 16.05.2005
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
 *  16.05.2005 - initial version
 * ...
 *
 */
package biz.wolschon.fileformats.gnucash.jwsdpimpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.zip.GZIPOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import biz.wolschon.fileformats.gnucash.GnucashAccount;
import biz.wolschon.fileformats.gnucash.GnucashCustomer;
import biz.wolschon.fileformats.gnucash.GnucashFile;
import biz.wolschon.fileformats.gnucash.GnucashInvoice;
import biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry;
import biz.wolschon.fileformats.gnucash.GnucashJob;
import biz.wolschon.fileformats.gnucash.GnucashTaxTable;
import biz.wolschon.fileformats.gnucash.GnucashTransaction;
import biz.wolschon.fileformats.gnucash.GnucashWritableAccount;
import biz.wolschon.fileformats.gnucash.GnucashWritableCustomer;
import biz.wolschon.fileformats.gnucash.GnucashWritableFile;
import biz.wolschon.fileformats.gnucash.GnucashWritableInvoice;
import biz.wolschon.fileformats.gnucash.GnucashWritableJob;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransaction;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncBudget;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncCommodity;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncBillTerm;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncCustomer;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEmployee;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEntry;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncInvoice;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncJob;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncTaxTable;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncVendor;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncPricedb;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncSchedxaction;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncTemplateTransactions;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccount;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCommodityType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountData;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncCustomerType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncEntryType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncInvoiceType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncJobType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncTaxTableType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncPricedbType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncPricedbType.PriceType.PriceCommodityType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncPricedbType.PriceType.PriceCurrencyType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransaction;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransactionType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncV2;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.ObjectFactory;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.impl.GncPricedbTypeImpl.PriceTypeImpl.PriceCommodityTypeImpl;
import biz.wolschon.numbers.FixedPointNumber;

/**
 * @author Marcus@Wolschon.biz
 * created: 16.05.2005
 *
 * Implementation of GnucashWritableFile based on GnucashFileImpl.
 * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl
 *
 */
public class GnucashFileWritingImpl extends GnucashFileImpl implements GnucashWritableFile {

    /**
     *
     */
    private static final int HEX = 16;

    /**
     * true if this file has been modified.
     */
    private boolean modified = false;

    /**
     * @see ${@link #getLastWriteTime()}
     */
    private long lastWriteTime = 0;

    /**
     * @return true if this file has been modified
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * @return the time in ms (compatible with File.lastModified) of the last write-operation
     */
    public long getLastWriteTime() {
        return lastWriteTime;
    }

    /**
     *
     * @param pModified true if this file has been modified false after save, load or undo of changes
     */
    public void setModified(final boolean pModified) {
        //boolean old = this.modified;
        modified = pModified;
//      if (propertyChange != null)
//         propertyChange.firePropertyChange("modified", old, pModified);
    }

    /**
     * @return the jaxb object-factory used to create new peer-objects to extend
     *         this
     * @throws JAXBException
     *             o jaxb-errors
     */
    @Override
    protected ObjectFactory getObjectFactory() throws JAXBException {
        return super.getObjectFactory();
    }

    /**
     * Keep the count-data up to date.
     * The count-data is re-calculated on the fly before
     * writing but we like to keep our internal model up-to-date
     * just to be defensive.
     * <gnc:count-data cd:type="commodity">2</gnc:count-data>
     * <gnc:count-data cd:type="account">394</gnc:count-data>
     * <gnc:count-data cd:type="transaction">1576</gnc:count-data>
     * <gnc:count-data cd:type="schedxaction">4</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncCustomer">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncJob">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncTaxTable">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncInvoice">5</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncEntry">18</gnc:count-data>
     * @param type the type to set it for
     */
    protected void incrementCountDataFor(final String type) {

        if (type == null) {
            throw new IllegalArgumentException("null type given");
        }

        List l = getRootElement().getGncBook().getGncCountData();
        for (Iterator iter = l.iterator(); iter.hasNext();) {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountData gncCountData = (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountData) iter.next();

            if (type.equals(gncCountData.getCdType())) {
                gncCountData.setValue(gncCountData.getValue() + 1);
                setModified(true);
            }
        }
    }
    /**
     * Keep the count-data up to date.
     * The count-data is re-calculated on the fly before
     * writing but we like to keep our internal model up-to-date
     * just to be defensive.
     * <gnc:count-data cd:type="commodity">2</gnc:count-data>
     * <gnc:count-data cd:type="account">394</gnc:count-data>
     * <gnc:count-data cd:type="transaction">1576</gnc:count-data>
     * <gnc:count-data cd:type="schedxaction">4</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncCustomer">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncJob">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncTaxTable">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncInvoice">5</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncEntry">18</gnc:count-data>
     * @param type the type to set it for
     */
    protected void decrementCountDataFor(final String type) {

        if (type == null) {
            throw new IllegalArgumentException("null type given");
        }

        List l = getRootElement().getGncBook().getGncCountData();
        for (Iterator iter = l.iterator(); iter.hasNext();) {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountData gncCountData = (biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncCountData) iter.next();

            if (type.equals(gncCountData.getCdType())) {
                gncCountData.setValue(gncCountData.getValue() - 1);
                setModified(true);
            }
        }
    }

    /**
     * keep the count-data up to date.
     * <gnc:count-data cd:type="commodity">2</gnc:count-data>
     * <gnc:count-data cd:type="account">394</gnc:count-data>
     * <gnc:count-data cd:type="transaction">1576</gnc:count-data>
     * <gnc:count-data cd:type="schedxaction">4</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncCustomer">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncJob">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncTaxTable">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncInvoice">5</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncEntry">18</gnc:count-data>
     * @param type the type to set it for
     * @param count the value
     */
    @SuppressWarnings("unchecked")
    protected void setCountDataFor(final String type, final int count) {

        if (type == null) {
            throw new IllegalArgumentException("null type given");
        }

        List<GncCountData> l = getRootElement().getGncBook().getGncCountData();
        for (GncCountData gncCountData : l) {
            if (type.equals(gncCountData.getCdType())) {
                gncCountData.setValue(count);
                setModified(true);
            }
        }
    }






    /**
     * @param file the file to load
     * @throws IOException on bsic io-problems such as a FileNotFoundException
     * @throws JAXBException on jaxb(xml-related) problems
     */
    public GnucashFileWritingImpl(final File file) throws IOException, JAXBException {
        super(file);
        setModified(false);
    }

    /**
     * Used by GnucashTransactionImpl.createTransaction to add a new Transaction
     * to this file.
     *
     * @param impl
     * @throws JAXBException
     * @see GnucashTransactionImpl#createTransaction(GnucashFileImpl)
     */
    @SuppressWarnings("unchecked")
    protected void addTransaction(final GnucashTransactionImpl impl) throws JAXBException {
        incrementCountDataFor("transaction");

        getRootElement().getGncBook().getBookElements().add(impl.getJwsdpPeer());
        setModified(true);
        transactionid2transaction.put(impl.getId(), impl);

    }


    /**
     * @return all TaxTables defined in the book
     * @see ${@link GnucashTaxTable}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<GnucashTaxTable> getTaxTables() {
        if (taxTablesById == null) {

            taxTablesById = new HashMap<String, GnucashTaxTable>();
            List bookElements = this.getRootElement().getGncBook().getBookElements();
            for (Object bookElement : bookElements) {
                if (bookElement instanceof GncGncTaxTableType) {
                    BookElementsGncGncTaxTable jwsdpPeer = (BookElementsGncGncTaxTable) bookElement;
                    GnucashTaxTableImpl gnucashTaxTable = new GnucashTaxTableImpl(jwsdpPeer, this);
                    taxTablesById.put(gnucashTaxTable.getId(), gnucashTaxTable);
                }
            }
        }

        return taxTablesById.values();
    }


    /**
     * @see ${@link biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#loadFile(java.io.File)}
     */
    @Override
    protected void loadFile(final File pFile) throws IOException, JAXBException {
        super.loadFile(pFile);
        lastWriteTime = Math.max(pFile.lastModified(), System.currentTimeMillis());
    }

    /**
     *
     * @throws JAXBException if there are issues with the underlying XML-model
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#setRootElement(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncV2)
     */
    @Override
    public void setRootElement(final GncV2 rootElement) throws JAXBException {
     super.setRootElement(rootElement);
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#writeFile(java.io.File)
     */
    public void writeFile(final File file) throws FileNotFoundException, IOException, JAXBException {

        if (file == null) {
            throw new IllegalArgumentException(
            "null not allowed for field this.file");
        }

        if (file.exists()) {
            throw new IllegalArgumentException(
            "Given file '" + file.getAbsolutePath() + "' does exist!");
        }

        checkAllCountData();


        setFile(file);

        OutputStream out = new FileOutputStream(file);
        out = new BufferedOutputStream(out);
        if (file.getName().endsWith(".gz")) {
            out = new GZIPOutputStream(out);
        }

        //Writer writer = new NamespaceAdderWriter(new OutputStreamWriter(out, "ISO8859-15"));
        Writer writer = new NamespaceAdderWriter(new OutputStreamWriter(out, "UTF-8"));
        try {

            JAXBContext context = getJAXBContext();
            Marshaller marshaller = context.createMarshaller();

            //marshaller.marshal(getRootElement(), writer);
            marshaller.marshal(getRootElement(), new WritingContentHandler(writer));

            setModified(false);
        } finally {
            writer.close();
        }
        lastWriteTime = Math.max(file.lastModified(), System.currentTimeMillis());
    }



    /**
     * Calculate and set the correct valued for all the following count-data.<br/>
     * Also check the that only valid elements are in the book-element
     * and that they have the correct order.
     *
     * <gnc:count-data cd:type="commodity">2</gnc:count-data>
     * <gnc:count-data cd:type="account">394</gnc:count-data>
     * <gnc:count-data cd:type="transaction">1576</gnc:count-data>
     * <gnc:count-data cd:type="schedxaction">4</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncCustomer">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncJob">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncTaxTable">2</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncInvoice">5</gnc:count-data>
     * <gnc:count-data cd:type="gnc:GncEntry">18</gnc:count-data>
     *
     */
    @SuppressWarnings("unchecked")
    private void checkAllCountData() {

        int commodity = 0;
        int account = 0;
        int transaction = 0;
        int cntCustomer = 0;
        int GncJob = 0;
        int GncTaxTable = 0;
        int GncInvoice = 0;
        int GncEntry = 0;
        /*
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncTemplateTransactions}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncInvoice}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncEntry}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncJob}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncTaxTable}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncCommodity}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncGncCustomer}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncSchedxaction}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncBudget}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccount}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.BookElementsGncPricedb}
         * {@link biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransaction}
         */
        List<Object> bookElements = getRootElement().getGncBook().getBookElements();
        for (Object element : bookElements) {
            if (element instanceof BookElementsGncCommodity) {
                commodity++;
            } else if (element instanceof GncAccount) {
                account++;
            } else if (element instanceof GncTransaction) {
                transaction++;
            } else if (element instanceof BookElementsGncGncCustomer) {
                cntCustomer++;
            } else if (element instanceof BookElementsGncGncJob) {
                GncJob++;
            } else if (element instanceof BookElementsGncGncTaxTable) {
                GncTaxTable++;
            } else if (element instanceof BookElementsGncGncInvoice) {
                GncInvoice++;
            } else if (element instanceof BookElementsGncGncEntry) {
                GncEntry++;
            } else if (element instanceof BookElementsGncTemplateTransactions) {
            } else if (element instanceof BookElementsGncSchedxaction) {
            } else if (element instanceof BookElementsGncBudget) {
            } else if (element instanceof BookElementsGncPricedb) {
            } else if (element instanceof BookElementsGncGncEmployee) {
            } else if (element instanceof BookElementsGncGncBillTerm) {
            } else if (element instanceof BookElementsGncGncVendor) {
            } else {
                throw new IllegalStateException("Unecpected element in GNC:Book found! <" + element.toString() + ">");
            }
        }

        setCountDataFor("commodity", commodity);
        setCountDataFor("account", account);
        setCountDataFor("transaction", transaction);
        setCountDataFor("gnc:GncCustomer", cntCustomer);
        setCountDataFor("gnc:GncJob", GncJob);
        setCountDataFor("gnc:GncTaxTable", GncTaxTable);
        setCountDataFor("gnc:GncInvoice", GncInvoice);
        setCountDataFor("gnc:GncEntry", GncEntry);
        // make sure the correct sort-order of the entity-types is obeyed in writing.
        // (we do not enforce this in the xml-schema to allow for reading out of order files)
        java.util.Collections.sort(bookElements, new BookElementsSorter());
    }
    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#getRootElement()
     * @return the underlying JAXB-element
     */
    @Override
    public GncV2 getRootElement() {
     return super.getRootElement();
    }

    /**
     * create a GUID for a new element.
     * (guids are globally unique and not tied
     *  to a specific kind of entity)
     * @return the new gnucash-guid
     */
    protected String createGUID() {

           int len = "74e492edf60d6a28b6c1d01cc410c058".length();

           StringBuffer sb = new StringBuffer(Long.toHexString(System.currentTimeMillis()));

           while (sb.length() < len) {
               sb.append(Integer.toHexString((int) (Math.random() * HEX)).charAt(0));
           }

           return sb.toString();
       }

    /**
     *
     * @return
     * @throws JAXBException on jaxb-problems
     */
    protected GncTransaction createGncTransaction() throws JAXBException {
        GncTransaction retval = getObjectFactory().createGncTransaction();
        incrementCountDataFor("transaction");
        return retval;
    }

    /**
     *
     * @return
     * @throws JAXBException on jaxb-problems
     */
    protected GncTransactionType.TrnSplitsType.TrnSplitType createGncTransactionTypeTrnSplitsTypeTrnSplitType() throws JAXBException {
        GncTransactionType.TrnSplitsType.TrnSplitType retval =getObjectFactory().createGncTransactionTypeTrnSplitsTypeTrnSplitType();
        //incrementCountDataFor();
        return retval;
    }

    /**
     *
     * @return
     * @throws JAXBException on jaxb-problems
     */
    protected GncGncInvoiceType createGncGncInvoiceType() throws JAXBException {
        GncGncInvoiceType retval =  getObjectFactory().createBookElementsGncGncInvoice();
        incrementCountDataFor("gnc:GncInvoice");
        return retval;
    }

    /**
     *
     * @return
     * @throws JAXBException on jaxb-problems
     */
    protected GncGncEntryType createGncGncEntryType() throws JAXBException {
        GncGncEntryType retval = getObjectFactory().createBookElementsGncGncEntry();
        incrementCountDataFor("gnc:GncEntry");
        return retval;
    }

    /**
     * @return
     * @throws JAXBException on jaxb-problems
     */
    protected GncGncCustomerType createGncGncCustomerType() throws JAXBException {
        GncGncCustomerType retval = getObjectFactory().createBookElementsGncGncCustomer();
        incrementCountDataFor("gnc:GncCustomer");
        return retval;
    }

    /**
     *
     * @return the jaxb-job
     * @throws JAXBException on jaxb-errors
     */
    public GncGncJobType createGncGncJobType() throws JAXBException {
        GncGncJobType retval = getObjectFactory().createBookElementsGncGncJob();
        incrementCountDataFor("gnc:GncJob");
        return retval;
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashFile#getCustomerByID(java.lang.String)
     */
    @Override
    public GnucashWritableCustomer getCustomerByID(final String arg0) {
        return (GnucashWritableCustomer) super.getCustomerByID(arg0);
    }

    /**
     * This overridden method creates the writable version of the returned object.
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#createAccount(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType)
     */
    @Override
    protected GnucashAccount createAccount(final GncAccountType jwsdpAccount) throws JAXBException {
        GnucashAccount account = new GnucashAccountWritingImpl(jwsdpAccount, this);
        return account;
    }


    /**
     * This overridden method creates the writable version of the returned object.
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#createInvoice(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncV2Type.GncBookType.GncGncInvoiceType)
     */
    @Override
    protected GnucashInvoice createInvoice(final GncGncInvoiceType jwsdpInvoice) {
        GnucashInvoice invoice = new GnucashInvoiceWritingImpl(jwsdpInvoice, this);
        return invoice;
    }

    /**
     * This overridden method creates the writable version of the returned object.
     * @param jwsdpInvoiceEntry the xml-object to represent in the entry.
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#createInvoiceEntry(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncV2Type.GncBookType.GncGncEntryType)
     * @return a new invoice-entry, already registred with this file.
     * @throws JAXBException on problems with the xml-backend
     */
    @Override
    protected GnucashInvoiceEntry createInvoiceEntry(final GncGncEntryType jwsdpInvoiceEntry) throws JAXBException {
        GnucashInvoiceEntry entry = new GnucashInvoiceEntryWritingImpl(jwsdpInvoiceEntry, this);
        return entry;
    }

    /**
     * This overridden method creates the writable version of the returned object.
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#createJob(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncV2Type.GncBookType.GncGncJobType)
     */
    @Override
    protected GnucashJobImpl createJob(final GncGncJobType jwsdpjob) {
        GnucashJobImpl job = new GnucashJobWritingImpl(jwsdpjob, this);
        return job;
    }

    /**
     * This overridden method creates the writable version of the returned object.
     *
     * @param jwsdpCustomer the jwsdp-object the customer shall wrap
     * @return the new customer
     * @throws JAXBException on problems with the xml-backend
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#createCustomer(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncV2Type.GncBookType.GncGncCustomerType)
     */
    @Override
    protected GnucashCustomerImpl createCustomer(final GncGncCustomerType jwsdpCustomer) throws JAXBException {
        GnucashCustomerImpl customer = new GnucashCustomerWritingImpl(jwsdpCustomer, this);
        return customer;
    }

    /**
     * This overridden method creates the writable version of the returned object.
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#createTransaction(biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransactionType)
     */
    @Override
    protected GnucashTransactionImpl createTransaction(final GncTransactionType jwsdpTransaction) throws JAXBException {
        GnucashTransactionImpl account = new GnucashTransactionWritingImpl(jwsdpTransaction, this);
        return account;
    }

    /**
     *
     * (c) 2005 by Wolschon Softwaredesign und Beratung - All rights reserved<br>
     * Project: gnucashReader <br>
     * GnucashFileWritingImpl.java <br>
     *
     * Helper-Class needed for writing Gnucash-Files that are binary-identical
     * to what gnucash itself writes.
     *
     * @author <a href="mailto:marcus@wolschon.biz">Marcus Wolschon</a>
     */
    private static class WritingContentHandler implements ContentHandler {

        /**
         * where to write it to.
         */
        private final Writer writer;

        /**
         * Our logger for debug- and error-ourput.
         */
        private static final Log LOGGER = LogFactory.getLog(WritingContentHandler.class);


        /**
         *
         * @param pwriter where to write it to
         */
        public WritingContentHandler(final Writer pwriter) {
            writer = pwriter;
        }

        /**
         *
         * @see org.xml.sax.ContentHandler#endDocument()
         */
        public void endDocument() throws SAXException {

            try {
                writer.write("\n\n"
                           + "<!-- Local variables: -->\n"
                           + "<!-- mode: xml        -->\n"
                           + "<!-- End:             -->\n");
            } catch (IOException e) {
                LOGGER.error("Problem in WritingContentHandler", e);
            }

        }

        /**
         *
         * @see org.xml.sax.ContentHandler#startDocument()
         */
        public void startDocument() throws SAXException {

            try {
                //old gnucash-version writer.write("<?xml version=\"1.0\"?>\n");
            	writer.write("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
            } catch (IOException e) {
                LOGGER.error("Problem in WritingContentHandler", e);
            }
        }

        private final String encodeme[]  = new String[]   { "&", ">", "<"};//old gnucash-versions, "\"",     "�" ,     "�" ,      "�", "�",      "�",      "�",      "�",   "�" /*,  "?"0xa4*/,      "�"};
        private final String encoded[] = new String[] {"&amp;",  "&gt;", "&lt;"};//old gnucash-versions, "&quot;", "&#214;", "&#246;", "&#220;", "&#252;", "&#196;", "&#228;", "&#223;", "&#164;"/*, "&#164;"*/, "&#167;"};

        /**
         *
         * @see org.xml.sax.ContentHandler#characters(char[], int, int)
         */
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            try {
                if (last_was == LAST_WAS_OPENELEMENT) {
                    writer.write(">");
                }


                if (last_was==LAST_WAS_CLOSEELEMENT) {
                    return;
                }


                // make shure GUIDs are written with non-capital letters
                if (isGUID) {
                    String s = new String(ch, start, length);
                    writer.write(s.toLowerCase());
                } else {

                    StringBuffer sb = new StringBuffer();
                    sb.append(ch, start, length);

                    for (int j = 0; j < encodeme.length; j++) {
                        int index = 0;
                        while ((index=sb.indexOf(encodeme[j], index))  != -1) {
                            sb.replace(index,
                                       index + encodeme[j].length(),
                                       encoded[j]);
                            index += encoded[j].length() - encodeme[j].length() + 1;
                        }

                    }


//                    String s = sb.toString();
//                    if(s.indexOf("bis 410") != -1) {
//                     System.err.println(s+"---"+Integer.toHexString(s.charAt(s.length()-1)));
//                    }

                    writer.write(sb.toString());
                }


                last_was = LAST_WAS_CHARACTERDATA;
            } catch (IOException e) {
                LOGGER.error("Problem in WritingContentHandler", e);
            }

        }

        public void ignorableWhitespace(final char[] ch, final int start, final int length)
                throws SAXException {
            /*try {
                writer.write(ch, start, length);
                last_was = LAST_WAS_CHARACTERDATA;
            } catch (IOException e) {
                LOGGER.error("Problem in WritingContentHandler", e);
            }*/

        }

        /**
         *
         * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
         */
        public void endPrefixMapping(final String prefix) throws SAXException {
            LOGGER.debug("WritingContentHandler.endPrefixMapping(prefix='" + prefix + "')");

        }

        /**
         *
         * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
         */
        public void skippedEntity(final String name) throws SAXException {
            LOGGER.debug("WritingContentHandler.skippedEntity(name='" + name + "')");

        }

        /**
         *
         * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
         */
        public void setDocumentLocator(final Locator locator) {

        }

        /**
         *
         *
         * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
         */
        public void processingInstruction(final String target,final  String data)
                throws SAXException {
            try {
                writer.write("<?" + target);
                if (data != null) {
                    writer.write(data);
                }

                writer.write("?>\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void startPrefixMapping(final String prefix,final String uri)
                throws SAXException {
            LOGGER.debug("WritingContentHandler.startPrefixMapping(prefix='"
                       + prefix + "')");

        }


        public void endElement(
                final String namespaceURI,
                final String localName,
                final String qName) throws SAXException {
            try {

                // create <slot:value type="string"></slot:value> instead of <slot:value type="string"/>
                if ((isTrnDescription || isSlotvalueTypeString)
                        && last_was != LAST_WAS_CHARACTERDATA) {
                       characters(new char[0], 0, 0);
                }


                if (qName.equals("gnc_template-transactions")) {
                    insideGncTemplateTransactions = false;
                }


                depth -= 2;

                if (last_was == LAST_WAS_CLOSEELEMENT) {
                    writer.write("\n");
                    writeSpaces();
                    writer.write("</"+qName+">");
                }

                if (last_was == LAST_WAS_OPENELEMENT) {
                    writer.write("/>");
                }

                if (last_was == LAST_WAS_CHARACTERDATA) {
                    writer.write("</"+qName+">");
                }


                last_was = LAST_WAS_CLOSEELEMENT;
            } catch (IOException e) {
                LOGGER.error("Problem in WritingContentHandler", e);
            }

        }


        boolean isGUID = false;
        boolean isSlotvalueTypeString = false;
        boolean isTrnDescription = false;
        boolean insideGncTemplateTransactions = false;

        /**
         *
         * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
         */
        public void startElement(
                final String namespaceURI,
                final String localName,
                final String qName,
                final Attributes atts) throws SAXException {
            try {
                if (last_was == LAST_WAS_OPENELEMENT) {
                    writer.write(">\n");
                    writeSpaces();

                }

                if (last_was == LAST_WAS_CLOSEELEMENT) {
                    writer.write("\n");
                    writeSpaces();
                }

                writer.write("<" + qName);

                if (qName.equals("gnc_template-transactions")) {
                    insideGncTemplateTransactions = true;
                }


                isTrnDescription = qName.equals("trn_description");
                isGUID = false;
                isSlotvalueTypeString = false;
                for (int i = 0; i < atts.getLength(); i++) {
                    writer.write(" " + atts.getQName(i)
                               + "=\"" + atts.getValue(i) + "\"");

                    if (atts.getQName(i).equals("type")
                            &&
                        atts.getValue(i).equals("guid")) {
                        isGUID = true;
                    }


                    if (qName.equals("slot_value")
                            &&
                        atts.getQName(i).equals("type")
                            &&
                        atts.getValue(i).equals("string")) {
                        isSlotvalueTypeString = true;
                    }


                }
                depth += 2;

                last_was = LAST_WAS_OPENELEMENT;
            } catch (IOException e) {
                LOGGER.error("Problem in WritingContentHandler", e);
            }

        }

        /**
         *
         * @throws IOException
         */
        private void writeSpaces() throws IOException {

            if (insideGncTemplateTransactions) {
                   if (depth < 6) {
                       return;
                   }

                   writer.write(getSpaces(), 0, depth-6);
                   return;
            }


            if(depth<4) {
                return;
            }

            writer.write(getSpaces(), 0, depth-4);
        }

        int depth = 0;

        int last_was = 0;
        private static final int LAST_WAS_OPENELEMENT = 1;
        private static final int LAST_WAS_CLOSEELEMENT = 2;
        private static final int LAST_WAS_CHARACTERDATA = 3;


        private char[] spaces;

        protected char[] getSpaces() {
            if(spaces==null || spaces.length<depth) {
             spaces = new char[depth];
             Arrays.fill(spaces, ' ');
            }

            return spaces;
        }
    }



    /**
     *
     * @author Marcus@Wolschon.biz
     * created: 13.05.2005
     *
     *
     * replaces ':' in tag-names and attribute-names by '_'
     *
     */
    public static class NamespaceAdderWriter extends Writer {

        /**
         * @param input where to write to
         */
        public NamespaceAdderWriter(final Writer input) {
            super();
            output = input;
        }
        /**
         *
         * @return where to write to
         */
        public Writer getWriter() {
            return output;
        }

        /**
         * where to write to.
         */
        private final Writer output;

        /**
         *
         */
        private boolean isInQuotation = false;

        /**
         *
         */
        private boolean isInTag = false;

        /**
         *
         * @see java.io.Writer#flush()
         */
        @Override
        public void flush() throws IOException {
            output.flush();
        }
        /**
         * @see java.io.Writer#write(char[], int, int)
         */
        @Override
        public void write(final char[] cbuf, final int off, final int len) throws IOException {


            for (int i = off; i < off + len; i++) {
                if ( isInTag && (cbuf[i] == '"' || cbuf[i] == '\'') ) {
                    toggleIsInQuotation();
                } else
                if (cbuf[i] == '<' && !isInQuotation) {
                    isInTag = true;
                } else
                if (cbuf[i] == '>' && !isInQuotation) {
                    isInTag = false;
                } else
                if (cbuf[i] == '_' && isInTag && !isInQuotation) {

                	// do NOT replace the second "_" in but everywhere else inside tag-names
                	// cmdty:quote_source
                	// cmdty:get_quotes
                	// fs:ui_type
                	// invoice:billing_id
			// recurrence:period_type

                    if (i <= "fs:ui".length() || !(new String(cbuf, i - "fs:ui".length(), "fs:ui".length()).equals("fs:ui"))) {
                        if (i <= "cmdty:get".length() || !(new String(cbuf, i - "cmdty:get".length(), "cmdty:get".length()).equals("cmdty:get"))) {
                            if (i <= "cmdty:quote".length() || !(new String(cbuf, i - "cmdty:quote".length(), "cmdty:quote".length()).equals("cmdty:quote"))) {
                                if (i <= "invoice:billing".length() || !(new String(cbuf, i - "invoice:billing".length(), "invoice:billing".length()).equals("invoice:billing"))) {
                                    if (i <= "recurrence:period".length() || !(new String(cbuf, i - "recurrence:period".length(), "recurrence:period".length()).equals("recurrence:period"))) {
                                        cbuf[i] = ':';
                                    }
                                }
                            }
                        }
                    }
                }


            }



            output.write(cbuf, off, len);

            // this is a quick hack to add the missing xmlns-declarations
            if (len == 7 && new String(cbuf, off, len).equals("<gnc-v2")) {
            	output.write("\n" +
"     xmlns:gnc=\"http://www.gnucash.org/XML/gnc\"\n" +
"     xmlns:act=\"http://www.gnucash.org/XML/act\"\n" +
"     xmlns:book=\"http://www.gnucash.org/XML/book\"\n" +
"     xmlns:cd=\"http://www.gnucash.org/XML/cd\"\n" +
"     xmlns:cmdty=\"http://www.gnucash.org/XML/cmdty\"\n" +
"     xmlns:price=\"http://www.gnucash.org/XML/price\"\n" +
"     xmlns:slot=\"http://www.gnucash.org/XML/slot\"\n" +
"     xmlns:split=\"http://www.gnucash.org/XML/split\"\n" +
"     xmlns:sx=\"http://www.gnucash.org/XML/sx\"\n" +
"     xmlns:trn=\"http://www.gnucash.org/XML/trn\"\n" +
"     xmlns:ts=\"http://www.gnucash.org/XML/ts\"\n" +
"     xmlns:fs=\"http://www.gnucash.org/XML/fs\"\n" +
"     xmlns:bgt=\"http://www.gnucash.org/XML/bgt\"\n" +
"     xmlns:recurrence=\"http://www.gnucash.org/XML/recurrence\"\n" +
"     xmlns:lot=\"http://www.gnucash.org/XML/lot\"\n" +
"     xmlns:cust=\"http://www.gnucash.org/XML/cust\"\n" +
"     xmlns:job=\"http://www.gnucash.org/XML/job\"\n" +
"     xmlns:addr=\"http://www.gnucash.org/XML/addr\"\n" +
"     xmlns:owner=\"http://www.gnucash.org/XML/owner\"\n" +
"     xmlns:taxtable=\"http://www.gnucash.org/XML/taxtable\"\n" +
"     xmlns:tte=\"http://www.gnucash.org/XML/tte\"\n" +
"     xmlns:employee=\"http://www.gnucash.org/XML/employee\"\n" +
"     xmlns:order=\"http://www.gnucash.org/XML/order\"\n" +
"     xmlns:billterm=\"http://www.gnucash.org/XML/billterm\"\n" +
"     xmlns:bt-days=\"http://www.gnucash.org/XML/bt-days\"\n" +
"     xmlns:bt-prox=\"http://www.gnucash.org/XML/bt-prox\"\n" +
"     xmlns:invoice=\"http://www.gnucash.org/XML/invoice\"\n" +
"     xmlns:entry=\"http://www.gnucash.org/XML/entry\"\n" +
"     xmlns:vendor=\"http://www.gnucash.org/XML/vendor\"");
            }


        }
        /**
         *
         * @see java.io.Writer#close()
         */
        @Override
        public void close() throws IOException {
                output.close();
        }

        /**
         *
         */
        private void toggleIsInQuotation() {
            if (isInQuotation) {
                isInQuotation = false;
            } else {
                isInQuotation = true;
            }
        }
}


    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#getTransactionByID(java.lang.String)
     */
    @Override
    public GnucashWritableTransaction getTransactionByID(final String id) {
        return (GnucashWritableTransaction) super.getTransactionByID(id);
    }

    /**
     * @see GnucashFile#getJobByID(String)
     * @param jobID the id of the job to fetch
     * @return A changable version of the job or null of not found.
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#getJobByID(String)
     */
    @Override
    public GnucashWritableJob getJobByID(final String jobID) {
        return (GnucashWritableJob) super.getJobByID(jobID);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#getWritableJobs()
     */
    public Collection<GnucashWritableJob> getWritableJobs() {

        Collection<GnucashJob> jobs = getJobs();
        if (jobs == null) {
            throw new IllegalStateException("getJobs() returned null");
        }
        Collection<GnucashWritableJob> retval = new ArrayList<GnucashWritableJob>(jobs.size());
        for (GnucashJob job : jobs) {
            retval.add((GnucashWritableJob) job);
        }
        return retval;
    }

    /**
     * @see GnucashFile#getInvoiceByID(String)
     * @param id the unique invoice-id
     * @return A changable version of the Invoice or null if not found.
     */
    @Override
    public GnucashWritableInvoice getInvoiceByID(final String id) {
        return (GnucashWritableInvoice) super.getInvoiceByID(id);
    }

    /**
     * @param type the type to look for
     * @return A changable version of all accounts of that type.
     * @see {@link GnucashWritableFile#getAccountsByType(String)}
     */
    public Collection getAccountsByType(final String type) {
        Collection retval = new LinkedList();
        for (Object element : getWritableAccounts()) {
            GnucashWritableAccount account = (GnucashWritableAccount) element;

            if (account.getType() == null) {
                if (type == null) {
                    retval.add(account);
                }
            } else if (account.getType().equals(type)) {
                retval.add(account);
            }

        }
        return retval;
    }




    /**
     * @see GnucashFile#getAccountByName(String)
     * @param name the name of the account
     * @return A changable version of the first account with that name.
     */
    @Override
    public GnucashWritableAccount getAccountByName(final String name) {
        return (GnucashWritableAccount) super.getAccountByName(name);
    }

    /**
     * @see GnucashFile#getAccountByID(String)
     * @param id the unique account-id
     * @return A changable version of the account or null if not found.
     */
    @Override
    public GnucashWritableAccount getAccountByID(final String id) {
        return (GnucashWritableAccount) super.getAccountByID(id);
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#getWritableTransactions()
     */
    @SuppressWarnings("unchecked")
    public Collection<? extends GnucashWritableTransaction> getWritableTransactions() {
        return (Collection<? extends GnucashWritableTransaction>) getTransactions();
    }
    /**
     * @param impl what to remove
     * @throws JAXBException if we have issues with the XML-backend
     */
    public void removeTransaction(final GnucashWritableTransaction impl) throws JAXBException {

        Collection<GnucashWritableTransactionSplit> c = new LinkedList<GnucashWritableTransactionSplit>();
        c.addAll(impl.getWritingSplits());
        for (GnucashWritableTransactionSplit element : c) {
            element.remove();
        }

        getRootElement().getGncBook().getBookElements().remove(((GnucashTransactionWritingImpl) impl).getJwsdpPeer());
        setModified(true);
        transactionid2transaction.remove(impl.getId());

    }

    /**
     * Add a new currency.<br/>
     * If the currency already exists, add a new price-quote for it.
     * @param pCmdtySpace the namespace (e.g. "GOODS" or "ISO4217")
     * @param pCmdtyId the currency-name
     * @param conversionFactor the conversion-factor from the base-currency (EUR).
     * @param pCmdtyNameFraction number of decimal-places after the comma
     * @param pCmdtyName common name of the new currency
     * @throws JAXBException if we cannot create the underlying XML-entities
     */
    @SuppressWarnings("unchecked")
    public void addCurrency(final String pCmdtySpace, final String pCmdtyId, final FixedPointNumber conversionFactor, final int pCmdtyNameFraction, final String pCmdtyName) throws JAXBException {

       if (conversionFactor == null) {
           throw new IllegalArgumentException("null conversionFactor given");
       }
       if (pCmdtySpace == null) {
           throw new IllegalArgumentException("null comodity-space given");
       }
       if (pCmdtyId == null) {
           throw new IllegalArgumentException("null comodity-id given");
       }
       if (pCmdtyName == null) {
           throw new IllegalArgumentException("null comodity-name given");
       }
       if (getCurrencyTable().getConversionFactor(pCmdtySpace, pCmdtyId) == null) {

           GncCommodityType newCurrency = getObjectFactory().createBookElementsGncCommodity();
           newCurrency.setCmdtyFraction(pCmdtyNameFraction);
           newCurrency.setCmdtySpace(pCmdtySpace);
           newCurrency.setCmdtyId(pCmdtyId);
           newCurrency.setCmdtyName(pCmdtyName);
           newCurrency.setVersion("2.0.0");
           getRootElement().getGncBook().getBookElements().add(newCurrency);
           incrementCountDataFor("commodity");
       }
       // add price-quote
       PriceCommodityType currency = new PriceCommodityTypeImpl();
       currency.setCmdtySpace(pCmdtySpace);
       currency.setCmdtyId(pCmdtyId);

       PriceCurrencyType baseCurrency = getObjectFactory().createGncPricedbTypePriceTypePriceCurrencyType();
       baseCurrency.setCmdtySpace("ISO4217");
       baseCurrency.setCmdtyId(getDefaultCurrencyID());

       GncPricedbType.PriceType newQuote = getObjectFactory().createGncPricedbTypePriceType();
       newQuote.setPriceSource("JGnucashLib");
       newQuote.setPriceId(getObjectFactory().createGncPricedbTypePriceTypePriceIdType());
       newQuote.getPriceId().setType("guid");
       newQuote.getPriceId().setValue(createGUID());
       newQuote.setPriceCommodity(currency);
       newQuote.setPriceCurrency(baseCurrency);
       newQuote.setPriceTime(getObjectFactory().createGncPricedbTypePriceTypePriceTimeType());
       newQuote.getPriceTime().setTsDate(PRICEQUOTEDATEFORMAT.format(new Date()));
       newQuote.setPriceType("last");
       newQuote.setPriceValue(conversionFactor.toGnucashString());

       List bookElements = getRootElement().getGncBook().getBookElements();
       for (Object element : bookElements) {
           if (element instanceof BookElementsGncPricedb) {
               BookElementsGncPricedb prices = (BookElementsGncPricedb) element;
               prices.getPrice().add(newQuote);
               getCurrencyTable().setConversionFactor(pCmdtySpace, pCmdtyId, conversionFactor);
               return;
           }
       }
       throw new IllegalStateException("No priceDB in Book in Gnucash-file");
    }
    /**
     * {@inheritDoc}
     */
    public GnucashWritableTransaction createWritableTransaction() throws JAXBException {
        return new GnucashTransactionWritingImpl(this, createGUID());
    }

    /**
     * {@inheritDoc}
     */
    public GnucashWritableTransaction createWritableTransaction(final String id) throws JAXBException {
        return new GnucashTransactionWritingImpl(this, id);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#createWritableTransaction()
     */
    public GnucashWritableInvoice createWritableInvoice(final String invoiceNumber,
                                                        final GnucashJob job,
                                                        final GnucashAccount accountToTransferMoneyTo,
                                                        final java.util.Date dueDate) throws JAXBException {
        return createWritableInvoice(createGUID(),
                invoiceNumber,
                job,
                accountToTransferMoneyTo,
                dueDate);
    }

    /**
     * FOR USE BY EXTENSIONS ONLY!
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#createWritableTransaction()
     */
    public GnucashWritableInvoice createWritableInvoice(final String internalID,
                                                        final String invoiceNumber,
                                                        final GnucashJob job,
                                                        final GnucashAccount accountToTransferMoneyTo,
                                                        final java.util.Date dueDate) throws JAXBException {
        GnucashInvoiceWritingImpl retval = new GnucashInvoiceWritingImpl(this,
                                             internalID,
                                             invoiceNumber,
                                             job,
                                             (GnucashAccountImpl) accountToTransferMoneyTo,
                                             dueDate);

        invoiceid2invoice.put(retval.getId(), retval);
        return retval;
    }


    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#createWritableCustomer()
     */
    public GnucashWritableCustomer createWritableCustomer()
            throws JAXBException {
        return createWritableCustomer(createGUID());
    }

    /**
     * THIS METHOD IS ONLY TO BE USED BY EXTENSIONS TO THIS LIBRARY!<br/>
     * @throws JAXBException on XML-backend-errors
     * @param id the internal id the customer shall have
     * @return the new customer. (already added to this file)
     */
    public GnucashWritableCustomer createWritableCustomer(final String id) throws JAXBException {
        if (id == null) {
            throw new IllegalArgumentException("null id given!");
        }
        GnucashCustomerWritingImpl w = new GnucashCustomerWritingImpl(this, id);
        super.customerid2customer.put(w.getId(), w);
        return w;
    }


    /**
     * @param impl what to remove
     */
    public void removeCustomer(final GnucashWritableCustomer impl) {
        customerid2customer.remove(impl.getId());
        getRootElement().getGncBook().getBookElements().remove(((GnucashCustomerWritingImpl) impl).getJwsdpPeer());
        setModified(true);
    }


    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#createWritableJob(biz.wolschon.fileformats.gnucash.GnucashCustomer)
     */
    public GnucashWritableJob createWritableJob(final GnucashCustomer customer) throws JAXBException {
        if (customer == null) {
            throw new IllegalArgumentException("null customer given");
        }
        return this.createWritableJob(this.createGUID(), customer);
     }

    /**
    *
    * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#createWritableJob(biz.wolschon.fileformats.gnucash.GnucashCustomer)
    */
   public GnucashWritableJob createWritableJob(final String id, final GnucashCustomer customer) throws JAXBException {
       if (customer == null) {
        throw new IllegalArgumentException("null customer given");
    }
       GnucashJobWritingImpl w = new GnucashJobWritingImpl(this, id, customer);
       super.jobid2job.put(w.getId(), w);
       return w;
    }


    /**
     * @param impl what to remove
     */
    public void removeJob(final GnucashWritableJob impl) {
        jobid2job.remove(impl.getId());
        getRootElement().getGncBook().getBookElements().remove(((GnucashJobWritingImpl) impl).getJwsdpPeer());
        setModified(true);
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#createWritableAccount()
     */
    public GnucashWritableAccount createWritableAccount() throws JAXBException {
        GnucashWritableAccount w = new GnucashAccountWritingImpl(this);
        super.accountid2account.put(w.getId(), w);
        return w;
     }

    /**
    *
    * @see biz.wolschon.fileformats.gnucash.GnucashWritableFile#createWritableAccount()
    */
   public GnucashWritableAccount createWritableAccount(final String newID) throws JAXBException {
       GnucashWritableAccount w = new GnucashAccountWritingImpl(this, newID);
       super.accountid2account.put(w.getId(), w);
       return w;
    }


    /**
     * @param impl  what to remove
     */
    public void removeAccount(final GnucashWritableAccount impl) {
        if (impl.getTransactionSplits().size() > 0) {
            throw new IllegalStateException("cannot remove account while it contains transaction-splits!");
        }

         getRootElement().getGncBook().getBookElements().remove(((GnucashAccountWritingImpl) impl).getJwsdpPeer());
         setModified(true);
         super.accountid2account.remove(impl.getId());
    }



    /**
     *
     * @return a read-only collection of all accounts that have no parent
     */
    @SuppressWarnings("unchecked")
    public Collection<? extends GnucashWritableAccount> getWritableRootAccounts() {
     return (Collection<? extends GnucashWritableAccount>) getRootAccounts();
    }

    /**
     *
     * @return a read-only collection of all accounts
     */
    @SuppressWarnings("unchecked")
    public Collection<GnucashWritableAccount> getWritableAccounts() {
        TreeSet<GnucashWritableAccount> retval = new TreeSet();
        for (GnucashAccount account : getAccounts()) {
            retval.add((GnucashWritableAccount) account);
        }
        return retval;
    }

    /**
    * @param jnr the job-number to look for.
    * @return the (first) jobs that have this number or null if not found
    */
    public GnucashWritableJob getJobByNumber(final String jnr) {
        if (jobid2job == null) {
            throw new IllegalStateException("no root-element loaded");
        }

        for (GnucashJob gnucashJob : jobid2job.values()) {
            GnucashWritableJob job = (GnucashWritableJob) gnucashJob;
            if (job.getJobNumber().equals(jnr)) {
                return job;
            }

        }
        return null;

    }

    /**
     * @param impl an invoice to remove
     * @throws JAXBException if we have issues with the XML-backend.
     */
    public void removeInvoice(final GnucashInvoiceWritingImpl impl) throws JAXBException {

        if (impl.getPayingTransactions().size() > 0) {
            throw new IllegalArgumentException("cannot remove this invoice! It has payments!");
        }

        GnucashTransaction postTransaction = impl.getPostTransaction();
        if (postTransaction != null) {
            ((GnucashWritableTransaction) postTransaction).remove();
        }

        invoiceid2invoice.remove(impl.getId());
        getRootElement().getGncBook().getBookElements().remove(impl.getJwsdpPeer());
        this.decrementCountDataFor("gnc:GncInvoice");
        setModified(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GnucashWritableFile getWritableGnucashFile() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUserDefinedAttribute(final String aName, final String aValue)
                                                                    throws JAXBException {
        List<Slot> slots = getRootElement().getGncBook().getBookSlots().getSlot();
        for (Slot slot : slots) {
            if (slot.getSlotKey().equals(aName)) {
                slot.getSlotValue().getContent().clear();
                slot.getSlotValue().getContent().add(aValue);
                return;
            }
        }
        // create new slot
        Slot newSlot = getObjectFactory().createSlot();
        newSlot.setSlotKey(aName);
        newSlot.setSlotValue(getObjectFactory().createSlotValueType());
        newSlot.getSlotValue().getContent().add(aValue);
        newSlot.getSlotValue().setType("string");
        getRootElement().getGncBook().getBookSlots().getSlot().add(newSlot);
    }

    /* (non-Javadoc)
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileImpl#getRootAccounts()
     */
    @Override
    public Collection<? extends GnucashAccount> getRootAccounts() {
        // TODO Auto-generated method stub
        Collection<? extends GnucashAccount> rootAccounts = super.getRootAccounts();
        if (rootAccounts.size() > 1) {
            GnucashAccount root = null;
            StringBuilder roots = new StringBuilder();
            for (GnucashAccount gnucashAccount : rootAccounts) {
                if (gnucashAccount == null) {
                    continue;
                }
                if (gnucashAccount.getType() != null && gnucashAccount.getType().equals("ROOT")) {
                    root = gnucashAccount;
                    continue;
                }
                roots.append(gnucashAccount.getId()).append("=\"").append(gnucashAccount.getName()).append("\" ");
            }
            LOGGER.warn("file has more then one root-account! Attaching excess accounts to root-account: "
                     + roots.toString());
            LinkedList<GnucashAccount> rootAccounts2 = new LinkedList<GnucashAccount>();
            rootAccounts2.add(root);
            for (GnucashAccount gnucashAccount : rootAccounts) {
                if (gnucashAccount == null) {
                    continue;
                }
                if (gnucashAccount == root) {
                    continue;
                }
                try {
                    ((GnucashWritableAccount) gnucashAccount).setParentAccount(root);
                } catch (JAXBException e) {
                    LOGGER.warn("Canno re-parent account " + gnucashAccount.getId() + " \"" + gnucashAccount.getName() + "\"", e);
                    rootAccounts2.add(gnucashAccount);
                }
            }
            rootAccounts = rootAccounts2;
        }
        return rootAccounts;
    }
}
