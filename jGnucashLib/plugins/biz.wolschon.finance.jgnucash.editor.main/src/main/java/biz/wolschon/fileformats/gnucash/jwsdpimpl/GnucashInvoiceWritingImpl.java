/**
 * GnucashInvoiceWritingImpl.java
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

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.xml.bind.JAXBException;

import biz.wolschon.fileformats.gnucash.GnucashAccount;
import biz.wolschon.fileformats.gnucash.GnucashFile;
import biz.wolschon.fileformats.gnucash.GnucashInvoiceEntry;
import biz.wolschon.fileformats.gnucash.GnucashJob;
import biz.wolschon.fileformats.gnucash.GnucashTaxTable;
import biz.wolschon.fileformats.gnucash.GnucashWritableFile;
import biz.wolschon.fileformats.gnucash.GnucashWritableInvoice;
import biz.wolschon.fileformats.gnucash.GnucashWritableInvoiceEntry;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransaction;
import biz.wolschon.fileformats.gnucash.GnucashTaxTable.TaxTableEntry;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncInvoiceType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransactionType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncV2Type;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.ObjectFactory;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotsType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType.ActLotsType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType.ActLotsType.GncLotType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncGncInvoiceType.InvoicePosttxnType;
import biz.wolschon.numbers.FixedPointNumber;

/**
 * @author Marcus@Wolschon.biz
 * created: 16.05.2005
 * TODO write a comment what this type does here
 *
 */
public class GnucashInvoiceWritingImpl extends GnucashInvoiceImpl implements GnucashWritableInvoice {

    /**
     * Create an editable invoice facading an existing JWSDP-peer.
     * @param jwsdpPeer the JWSDP-object we are facading.
     * @param file the file to register under
     * @see GnucashInvoiceImpl#GnucashInvoiceImpl(GncV2Type.GncBookType.GncGncInvoiceType, GnucashFile)
     */
    public GnucashInvoiceWritingImpl(final GncGncInvoiceType jwsdpPeer,
                                     final GnucashFile file) {
        super(jwsdpPeer, file);
    }



    /**
     * The gnucash-file is the top-level class to contain everything.
     * @return the file we are associated with
     */
    @Override
    public GnucashWritableFile getFile() {
        return (GnucashWritableFile) super.getFile();
    }


    /**
     *
     * @param file the file we are associated with.
     * @param invoiceNumber
     * @param job
     * @param accountToTransferMoneyTo
     * @param dueDate
     * @throws JAXBException
     */
    protected GnucashInvoiceWritingImpl(final GnucashFileWritingImpl file,
                                        final String internalID,
                                     final String invoiceNumber,
                                     final GnucashJob job,
                                     final GnucashAccountImpl accountToTransferMoneyTo,
                                     final Date dueDate) throws JAXBException {
        super(createInvoice(file, internalID, invoiceNumber, job, accountToTransferMoneyTo, dueDate), file);
    }

    /**
     * create and add a new entry.
     * @throws JAXBException
     */
    public GnucashWritableInvoiceEntry createEntry(final FixedPointNumber singleUnitPrice, final FixedPointNumber quantity) throws JAXBException {
        GnucashInvoiceEntryWritingImpl entry = new GnucashInvoiceEntryWritingImpl(this, quantity, singleUnitPrice);
        return entry;
    }

    /**
     * create and add a new entry.<br/>
     * @throws JAXBException
     * @return an entry using the given Tax-Table
     */
    public GnucashWritableInvoiceEntry createEntry(final FixedPointNumber singleUnitPrice,
                                                   final FixedPointNumber quantity,
                                                   final GnucashTaxTable tax) throws JAXBException {

        GnucashInvoiceEntryWritingImpl entry = new GnucashInvoiceEntryWritingImpl(this, quantity, singleUnitPrice);
        if (tax == null
                 ||
            tax.getEntries().isEmpty()
                 ||
            (tax.getEntries().iterator().next())
            .getAmount().equals(new FixedPointNumber())) {
            // no taxes
            entry.setTaxable(false);
        } else {
            entry.setTaxTable(tax);
        }
        return entry;
    }

    /**
     * create and add a new entry.<br/>
     * The entry will use the accounts of the
     * SKR03.
     * @throws JAXBException
     */
    public GnucashWritableInvoiceEntry createEntry(final FixedPointNumber singleUnitPrice, final FixedPointNumber quantity, final FixedPointNumber tax) throws JAXBException {

        GnucashInvoiceEntryWritingImpl entry = new GnucashInvoiceEntryWritingImpl(this, quantity, singleUnitPrice);
        if (tax.equals(new FixedPointNumber())) {
            // no taxes
            entry.setTaxable(false);
        } else {
//TODO: find taxtable to use for given percentage
        }
        return entry;
    }

    /**
     * Use ${@link GnucashWritableFile#createWritableInvoice(String, GnucashJob, GnucashAccount, java.util.Date)}
     * instead of calling this method!
     * @param file
     * @param invoiceNumber
     * @param job
     * @param accountToTransferMoneyTo e.g. "Forderungen aus Lieferungen und Leistungen "
     * @param dueDate
     * @return
     * @throws JAXBException
     */
    protected static GncGncInvoiceType createInvoice(final GnucashFileWritingImpl file,
                                                                        final String internalID,
                                                                        final String invoiceNumber,
                                                                        final GnucashJob job,
                                                                        final GnucashAccountImpl accountToTransferMoneyTo,
                                                                        final Date dueDate) throws JAXBException {



        ObjectFactory factory = file.getObjectFactory();
        String invoiceguid = (internalID==null?file.createGUID():internalID);

        GncGncInvoiceType invoice = file.createGncGncInvoiceType();
        invoice.setInvoiceActive(1); //TODO: is this correct?
        invoice.setInvoiceBillingId(invoiceNumber);
        {
            GncGncInvoiceType.InvoiceCurrencyType currency = factory.createGncGncInvoiceTypeInvoiceCurrencyType();
            currency.setCmdtyId(file.getDefaultCurrencyID());
            currency.setCmdtySpace("ISO4217");
            invoice.setInvoiceCurrency(currency);
        }
        {
            GncGncInvoiceType.InvoiceGuidType invoiceref = factory.createGncGncInvoiceTypeInvoiceGuidType();
            invoiceref.setType("guid");
            invoiceref.setValue(invoiceguid);
            invoice.setInvoiceGuid(invoiceref);
        }
        invoice.setInvoiceId(invoiceNumber);
        {
            GncGncInvoiceType.InvoiceOpenedType opened = factory.createGncGncInvoiceTypeInvoiceOpenedType();
            opened.setTsDate(OPENEDDATEFORMAT.format(new Date()));
            invoice.setInvoiceOpened(opened);
        }
        {

            GncGncInvoiceType.InvoiceOwnerType jobref = factory.createGncGncInvoiceTypeInvoiceOwnerType();
            jobref.setOwnerType("gncJob");
            jobref.setVersion("2.0.0");
            {
             biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.OwnerIdType ownerIdRef = factory.createOwnerIdType();
             ownerIdRef.setType("guid");
             ownerIdRef.setValue(job.getId());
             jobref.setOwnerId(ownerIdRef);
            }
            invoice.setInvoiceOwner(jobref);
        }
        {
            GncGncInvoiceType.InvoicePostaccType postacc = factory.createGncGncInvoiceTypeInvoicePostaccType();
            postacc.setType("guid");
            postacc.setValue(accountToTransferMoneyTo.getId());
            invoice.setInvoicePostacc(postacc);
        }
        {
            GncGncInvoiceType.InvoicePostedType posted = factory.createGncGncInvoiceTypeInvoicePostedType();
            posted.setTsDate(OPENEDDATEFORMAT.format(new Date()));
            invoice.setInvoicePosted(posted);
        }
        {
            GncGncInvoiceType.InvoicePostlotType lotref = factory.createGncGncInvoiceTypeInvoicePostlotType();
            lotref.setType("guid");

            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType.ActLotsType.GncLotType newlot =
                createlot(file, factory, invoiceguid, accountToTransferMoneyTo, job);


            lotref.setValue(newlot.getLotId().getValue());
            invoice.setInvoicePostlot(lotref);
        }
        {
            GncGncInvoiceType.InvoicePosttxnType transactionref = factory.createGncGncInvoiceTypeInvoicePosttxnType();
            transactionref.setType("guid");
            transactionref.setValue(createPostTransaction(file, factory, invoiceguid, dueDate).getId());

            invoice.setInvoicePosttxn(transactionref);
        }
        invoice.setVersion("2.0.0");

        file.getRootElement().getGncBook().getBookElements().add(invoice);
        file.setModified(true);
        return invoice;
    }

    /**
     * @see #GnucashInvoiceWritingImpl(GnucashFileWritingImpl, String, String, GnucashJob, GnucashAccountImpl, Date)
     */
    private static GnucashTransactionImpl createPostTransaction(final GnucashFileWritingImpl file,
                                                                final ObjectFactory factory,
                                                                final String invoiceID,
                                                                final Date dueDate) throws JAXBException {
        GnucashTransactionImpl postTransaction = new GnucashTransactionWritingImpl(file, file.createGUID());

        SlotsType slots = postTransaction.getJwsdpPeer().getTrnSlots();

        if (slots == null)
        {
             slots = factory.createSlotsType();
             postTransaction.getJwsdpPeer().setTrnSlots(slots);
        }


//      add trans-txn-type -slot

        {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot slot = factory.createSlot();
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType value = factory.createSlotValueType();
            slot.setSlotKey("trans-txn-type");
            value.setType("string");
            value.getContent().add("I"); // I like invoice, P like payment

            slot.setSlotValue(value);
            slots.getSlot().add(slot);
        }

//      add trans-date-due -slot

        {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot slot = factory.createSlot();
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType value = factory.createSlotValueType();
            slot.setSlotKey("trans-date-due");
            value.setType("timespec");
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.TsDate dueDateRef = factory.createTsDate();
            dueDateRef.setValue(OPENEDDATEFORMAT.format(dueDate));
            value.getContent().add(dueDateRef);

            slot.setSlotValue(value);
            slots.getSlot().add(slot);
        }

//add invoice-slot

{
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot slot = factory.createSlot();
    biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType value = factory.createSlotValueType();
    slot.setSlotKey("gncInvoice");
    value.setType("frame");
    {
        biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot subslot = factory.createSlot();
        biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType subvalue = factory.createSlotValueType();

        subslot.setSlotKey("invoice-guid");
        subvalue.setType("guid");
        subvalue.getContent().add(invoiceID);
        subslot.setSlotValue(subvalue);

        value.getContent().add(subslot);
    }

    slot.setSlotValue(value);
    slots.getSlot().add(slot);
}


        return postTransaction;
    }

    private static GncLotType createlot(final GnucashFileWritingImpl file,
                                        final ObjectFactory factory,
                                        final String invoiceID,
                                        final GnucashAccountImpl accountToTransferMoneyTo,
                                        final GnucashJob job) throws JAXBException {

        ActLotsType lots = accountToTransferMoneyTo.getJwsdpPeer().getActLots();
        if (lots == null) {
            lots = factory.createGncAccountTypeActLotsType();
            accountToTransferMoneyTo.getJwsdpPeer().setActLots(lots);
        }



        biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType.ActLotsType.GncLotType newlot = factory.createGncAccountTypeActLotsTypeGncLotType();
        {
            GncAccountType.ActLotsType.GncLotType.LotIdType id = factory.createGncAccountTypeActLotsTypeGncLotTypeLotIdType();
            id.setType("guid");
            id.setValue(file.createGUID());
            newlot.setLotId(id);
        }
        newlot.setVersion("2.0.0");

        {
            SlotsType slots = factory.createSlotsType();
            newlot.setLotSlots(slots);
        }

        //add owner-slot (job)
        {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot slot = factory.createSlot();
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType value = factory.createSlotValueType();
            slot.setSlotKey("gncOwner");
            value.setType("frame");
            {
                biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot subslot = factory.createSlot();
                biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType subvalue = factory.createSlotValueType();

                subslot.setSlotKey("owner-type");
                subvalue.setType("integer");
                subvalue.getContent().add("3");
                subslot.setSlotValue(subvalue);

                value.getContent().add(subslot);
            }

            {
                biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot subslot = factory.createSlot();
                biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType subvalue = factory.createSlotValueType();

                subslot.setSlotKey("owner-guid");
                subvalue.setType("guid");
                subvalue.getContent().add(job.getId());
                subslot.setSlotValue(subvalue);

                value.getContent().add(subslot);
            }

            slot.setSlotValue(value);
            newlot.getLotSlots().getSlot().add(slot);
        }
        //add invoice-slot

        {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot slot = factory.createSlot();
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType value = factory.createSlotValueType();
            slot.setSlotKey("gncInvoice");
            value.setType("frame");
            {
                biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot subslot = factory.createSlot();
                biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType subvalue = factory.createSlotValueType();

                subslot.setSlotKey("invoice-guid");
                subvalue.setType("guid");
                subvalue.getContent().add(invoiceID);
                subslot.setSlotValue(subvalue);

                value.getContent().add(subslot);
            }

            slot.setSlotValue(value);
            newlot.getLotSlots().getSlot().add(slot);
        }


        lots.getGncLot().add(newlot);

        return newlot;
    }


    /**
     * @throws JAXBException
     * @see #addEntry(GnucashInvoiceEntryImpl)
     */
    protected void removeEntry(final GnucashInvoiceEntryWritingImpl impl) throws JAXBException {



        if (!isModifiable()) {
            throw new IllegalStateException("This Invoice has payments and is not modifiable!");
        }

        this.subtractEntry(impl);
        entries.remove(impl);
    }


    /**
     * Called by ${@link GnucashInvoiceEntryWritingImpl#createInvoiceEntry(GnucashInvoiceWritingImpl, GnucashAccount, FixedPointNumber, FixedPointNumber)}.
     * @param entry the entry to add to our internal list of invoice-entries
     * @throws JAXBException of errors in the xml-backend
     */
    protected void addEntry(final GnucashInvoiceEntryImpl entry) throws JAXBException {

System.err.println("GnucashInvoiceWritingImpl.addEntry " + entry.toString());


        if (!isModifiable()) {
            throw new IllegalArgumentException("This invoice has payments and is"
                    + " thus not modifiable");
        }

        super.addEntry(entry);

        //==============================================================
        // update or add split in PostTransaction
        // that transferes the money from the tax-account

        boolean isTaxable = entry.isTaxable();
        FixedPointNumber sumExclTaxes = entry.getSumExclTaxes();
        FixedPointNumber sumInclTaxes = entry.getSumInclTaxes();
        String accountToTransferMoneyFrom = getAccountIDToTransferMoneyFrom(entry);


        biz.wolschon.fileformats.gnucash.GnucashTaxTable taxtable = null;

        if (entry.isTaxable()) {
            taxtable = entry.getTaxTable();
            if (taxtable == null) {
                throw new IllegalArgumentException("The given entry has no tax-table (it's taxtable-id is '"
                        + entry.getJwsdpPeer().getEntryITaxtable().getValue()
                        + "')");
            }
        }


        updateEntry(taxtable, isTaxable, sumExclTaxes, sumInclTaxes, accountToTransferMoneyFrom);
        getFile().setModified(true);
    }



	/**
	 * @param entry
	 * @return the AccountID of the Account to transfer the money from
	 */
	private String getAccountIDToTransferMoneyFrom(final GnucashInvoiceEntryImpl entry) {
		return entry.getJwsdpPeer().getEntryIAcct().getValue();
	}

protected void subtractEntry(final GnucashInvoiceEntryImpl entry) throws JAXBException {
    System.err.println("GnucashInvoiceWritingImpl.subtractEntry " + entry.toString());
        //==============================================================
        // update or add split in PostTransaction
        // that transferes the money from the tax-account

        boolean isTaxable = entry.isTaxable();
        FixedPointNumber sumExclTaxes = entry.getSumExclTaxes().negate();
        FixedPointNumber sumInclTaxes = entry.getSumInclTaxes().negate();
        String accountToTransferMoneyFrom = entry.getJwsdpPeer().getEntryIAcct().getValue();


        GnucashTaxTable taxtable = null;

        if (entry.isTaxable()) {
            taxtable = entry.getTaxTable();
            if (taxtable == null) {
                throw new IllegalArgumentException("The given entry has no tax-table (it's taxtable-id is '"
                        + entry.getJwsdpPeer().getEntryITaxtable().getValue() + "')");
            }
        }


        updateEntry(taxtable, isTaxable, sumExclTaxes, sumInclTaxes, accountToTransferMoneyFrom);
        getFile().setModified(true);
    }

    /**
     * @param entry
     * @param isTaxable
     * @param sumExclTaxes
     * @param sumInclTaxes
     * @param accountToTransferMoneyFrom
     * @throws JAXBException
     *
     * Update the post-transaction by the given values of an added Invoice-Entry.
     */
    private void updateEntry(final biz.wolschon.fileformats.gnucash.GnucashTaxTable taxtable,
                             final boolean isTaxable,
                             final FixedPointNumber sumExclTaxes,
                             final FixedPointNumber sumInclTaxes,
                             final String accountToTransferMoneyFrom) throws JAXBException {
        System.err.println("GnucashInvoiceWritingImpl.updateEntry "
                + "isTaxable=" + isTaxable + " "
                + "accountToTransferMoneyFrom=" + accountToTransferMoneyFrom + " ");

        GnucashTransactionWritingImpl postTransaction = (GnucashTransactionWritingImpl) getPostTransaction();
        if (postTransaction == null) {
        	return; //invoice may not be posted
        }
        if (isTaxable) {

            // get the first account of the taxTable
            TaxTableEntry taxTableEntry = taxtable.getEntries().iterator().next();
            GnucashAccount accountToTransferTaxTo =  taxTableEntry.getAccount();
            FixedPointNumber entryTaxAmount = ((FixedPointNumber) sumInclTaxes.clone()).subtract(sumExclTaxes);


            System.err.println("GnucashInvoiceWritingImpl.updateEntry "
                    + "isTaxable=" + isTaxable + " "
                    + "accountToTransferMoneyFrom=" + accountToTransferMoneyFrom + " "
                    + "accountToTransferTaxTo=" + accountToTransferTaxTo.getQualifiedName() + " "
                    + "entryTaxAmount=" + entryTaxAmount + " "
                    + "#splits=" + postTransaction.getSplits().size());

            //failed for subtractEntry assert entryTaxAmount.isPositive() || entryTaxAmount.equals(new FixedPointNumber());

            boolean postTransactionTaxUpdated = false;
            for (Object element : postTransaction.getSplits()) {
                GnucashTransactionSplitWritingImpl split = (GnucashTransactionSplitWritingImpl) element;
                if (split.getAccountID().equals(accountToTransferTaxTo.getId())) {

//quantity gets updated automagically                 split.setQuantity(split.getQuantity().subtract(entryTaxAmount));
                 split.setValue(split.getValue().subtract(entryTaxAmount));

//               failed for subtractEntry                  assert !split.getValue().isPositive();
//               failed for subtractEntry                  assert !split.getQuantity().isPositive();

                 System.err.println("GnucashInvoiceWritingImpl.updateEntry "
                         + "updated tax-split=" + split.getId() + " "
                         + " of account " + split.getAccount().getQualifiedName()
                         + " to value " + split.getValue());

                 postTransactionTaxUpdated = true;
                 break;
                }
                System.err.println("GnucashInvoiceWritingImpl.updateEntry "
                        + "ignoring non-tax-split=" + split.getId() + " "
                        + " of value " + split.getValue()
                        + " and account " + split.getAccount().getQualifiedName());
            }
            if (!postTransactionTaxUpdated)  {
                GnucashTransactionSplitWritingImpl split = (GnucashTransactionSplitWritingImpl) postTransaction.createWritingSplit(accountToTransferTaxTo);
                split.setQuantity(((FixedPointNumber) entryTaxAmount.clone()).negate());
                split.setValue(((FixedPointNumber) entryTaxAmount.clone()).negate());

                //assert !split.getValue().isPositive();
                //assert !split.getQuantity().isPositive();

                split.setSplitAction("Rechnung");

                System.err.println("GnucashInvoiceWritingImpl.updateEntry "
                        + "created new tax-split=" + split.getId() + " "
                        + " of value " + split.getValue()
                        + " and account " + split.getAccount().getQualifiedName());
            }


        }


        updateNonTaxableEntry(sumExclTaxes, sumInclTaxes, accountToTransferMoneyFrom);
        getFile().setModified(true);
    }

    /**
     * @param sumExclTaxes
     * @param sumInclTaxes
     * @param accountToTransferMoneyFrom
     * @throws JAXBException
     *
     * Update the post-transaction by the given values of an added Invoice-Entry.
     *
     */
    private void updateNonTaxableEntry(final FixedPointNumber sumExclTaxes,
                                       final FixedPointNumber sumInclTaxes,
                                       final String accountToTransferMoneyFrom) throws JAXBException {

        System.err.println("GnucashInvoiceWritingImpl.updateNonTaxableEntry "
                + "accountToTransferMoneyFrom=" + accountToTransferMoneyFrom);

        GnucashTransactionWritingImpl postTransaction = (GnucashTransactionWritingImpl) getPostTransaction();
        if (postTransaction == null) {
        	return; //invoice may not be posted
        }

        //==============================================================
        // update transaction-split that transferes the sum incl. taxes from the incomeAccount
        // (e.g. "Umsatzerl�se 19%")
        String accountToTransferMoneyTo = getAccountIDToTransferMoneyTo();
        boolean postTransactionSumUpdated = false;

        System.err.println("GnucashInvoiceWritingImpl.updateNonTaxableEntry "
                + " #slits=" + postTransaction.getSplits().size());

        for (Object element : postTransaction.getSplits()) {
            GnucashTransactionSplitWritingImpl split = (GnucashTransactionSplitWritingImpl) element;
            if (split.getAccountID().equals(accountToTransferMoneyTo)) {


             FixedPointNumber value = split.getValue();
             split.setQuantity(split.getQuantity().add(sumInclTaxes));
             split.setValue(value.add(sumInclTaxes));
             postTransactionSumUpdated = true;


             System.err.println("GnucashInvoiceWritingImpl.updateNonTaxableEntry "
                     + " updated split " + split.getId());
             break;
            }
        }
        if (!postTransactionSumUpdated)  {
            GnucashTransactionSplitWritingImpl split = (GnucashTransactionSplitWritingImpl) postTransaction.createWritingSplit(getFile().getAccountByID(accountToTransferMoneyTo));
            split.setQuantity(sumInclTaxes);
            split.setValue(sumInclTaxes);
            split.setSplitAction("Rechnung");

            // this split must have a reference to the lot
            // as has the transaction-split of the whole sum in the
            // transaction when the invoice is payed
            GncTransactionType.TrnSplitsType.TrnSplitType.SplitLotType lotref
                 = ((GnucashFileImpl) getFile()).getObjectFactory()
                 .createGncTransactionTypeTrnSplitsTypeTrnSplitTypeSplitLotType();
            lotref.setType(getJwsdpPeer().getInvoicePostlot().getType());
            lotref.setValue(getJwsdpPeer().getInvoicePostlot().getValue());
            split.getJwsdpPeer().setSplitLot(lotref);

            System.err.println("GnucashInvoiceWritingImpl.updateNonTaxableEntry "
                    + " created split " + split.getId());
        }

//      ==============================================================
        // update transaction-split that transferes the sum incl. taxes to the postAccount
        // (e.g. "Forderungen aus Lieferungen und Leistungen")

        boolean postTransactionNetSumUpdated = false;
        for (Object element : postTransaction.getSplits()) {
            GnucashTransactionSplitWritingImpl split = (GnucashTransactionSplitWritingImpl) element;
            if (split.getAccountID().equals(accountToTransferMoneyFrom)) {


             FixedPointNumber value = split.getValue();
             split.setQuantity(split.getQuantity().subtract(sumExclTaxes));
             split.setValue(value.subtract(sumExclTaxes));
             split.getJwsdpPeer().setSplitAction("Rechnung");
             postTransactionNetSumUpdated = true;
             break;
            }
        }
        if (!postTransactionNetSumUpdated)  {
            GnucashTransactionSplitWritingImpl split = new GnucashTransactionSplitWritingImpl(postTransaction, getFile().getAccountByID(accountToTransferMoneyFrom));
            split.setQuantity(((FixedPointNumber) sumExclTaxes.clone()).negate());
            split.setValue(((FixedPointNumber) sumExclTaxes.clone()).negate());
        }


       assert postTransaction.isBalanced();
       getFile().setModified(true);
    }



    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#isModifiable()
     */
    public boolean isModifiable() {
        return getPayingTransactions().size() == 0;
    }

    /**
     * Throw an IllegalStateException if we are not modifiable.
     * @see #isModifiable()
     */
    protected void atemptChange() {
        if (!isModifiable()) {
            throw new IllegalStateException("this invoice is NOT changable because there are already payment for it made!");
        }
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#setJob(biz.wolschon.fileformats.gnucash.GnucashJob)
     */
    public void setJob(final GnucashJob job) {
        atemptChange();
        getJwsdpPeer().getInvoiceOwner().getOwnerId().setValue(job.getId());
        getFile().setModified(true);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#setDateOpened(java.util.Date)
     */
    public void setDateOpened(final Date d) {
        atemptChange();
        super.dateOpened = d;
        getJwsdpPeer().getInvoiceOpened().setTsDate(DATEOPENEDFORMAT.format(d));
        getFile().setModified(true);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#setDateOpened(java.lang.String)
     */
    public void setDateOpened(final String d) throws java.text.ParseException {
        atemptChange();
        setDateOpened(DATEOPENEDFORMAT.parse(d));
        getFile().setModified(true);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#setDatePosted(java.util.Date)
     */
    public void setDatePosted(final Date d) {

        atemptChange();

        super.datePosted = d;
        getJwsdpPeer().getInvoicePosted().setTsDate(DATEOPENEDFORMAT.format(d));
        getFile().setModified(true);

        // change the date of the transaction too
        GnucashWritableTransaction postTr = getWritingPostTransaction();
        if (postTr != null) {
            postTr.setDatePosted(d);
        }
    }

    /**
     *      * @see ${@link #setDatePosted(Date)};
     *           */
    public void setDatePostedFormatet(final String datePosted) {
        try {
            this.setDatePosted(java.text.DateFormat.getDateInstance().parse(datePosted));
        } catch (ParseException e) {
            IllegalArgumentException x = new IllegalArgumentException("cannot parse datePosted '"
                    + datePosted
                    + "'");
            x.initCause(e);
            throw x;
        }
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#setDatePosted(java.lang.String)
     */
    public void setDatePosted(final String d) throws java.text.ParseException {
        setDatePosted(DATEOPENEDFORMAT.parse(d));
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashInvoice#getPayingTransaction()
     */
    public Collection getWritingPayingTransactions() {
        return getPayingTransactions();
    }

    /**
     * @return get a modifiable version of {@link GnucashInvoiceImpl#getPostTransaction()}
     */
    public GnucashWritableTransaction getWritingPostTransaction() {
     InvoicePosttxnType invoicePosttxn = jwsdpPeer.getInvoicePosttxn();
     if (invoicePosttxn == null) {
    	 return null; //invoice may not be posted
     }
	return getFile().getTransactionByID(invoicePosttxn.getValue());
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#getWritableEntryById(java.lang.String)
     */
    public GnucashWritableInvoiceEntry getWritableEntryById(final String id) {
        return (GnucashWritableInvoiceEntry) super.getEntryById(id);
    }



    /**
     * @throws JAXBException if we have issues accessing the XML-Backend.
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableInvoice#remove()
     */
    public void remove() throws JAXBException {

        if (!isModifiable()) {
            throw new IllegalStateException("Invoice has payments and cannot be deleted!");
        }

        // we copy the list because element.remove() modifies it
        Collection<GnucashInvoiceEntry> entries2 = new LinkedList<GnucashInvoiceEntry>();
        entries2.addAll(this.getEntries());
        for (GnucashInvoiceEntry element : entries2) {
            ((GnucashWritableInvoiceEntry) element).remove();

        }


        GnucashWritableTransaction post = (GnucashWritableTransaction) getPostTransaction();
        if (post != null) {
            post.remove();
        }

        ((GnucashFileWritingImpl) getFile()).removeInvoice(this);

    }


}


