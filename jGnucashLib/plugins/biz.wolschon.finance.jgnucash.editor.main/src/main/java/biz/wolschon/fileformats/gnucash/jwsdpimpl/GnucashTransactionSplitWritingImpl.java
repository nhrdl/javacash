/**
 * GnucashTransactionSplitWritingImpl.java
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

import javax.xml.bind.JAXBException;

import biz.wolschon.fileformats.gnucash.GnucashAccount;
import biz.wolschon.fileformats.gnucash.GnucashWritableFile;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransaction;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransactionType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.ObjectFactory;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotsType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransactionType.TrnSplitsType.TrnSplitType;
import biz.wolschon.numbers.FixedPointNumber;

/**
 * created: 16.05.2005 <br/>
 *
 * Transaction-Split that can be newly created or removed from it's transaction.
 * @author <a href="mailto:Marcus@Wolschon.biz">Marcus Wolschon</a>
 */
public class GnucashTransactionSplitWritingImpl extends
        GnucashTransactionSplitImpl  implements GnucashWritableTransactionSplit {

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
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashTransactionSplitImpl#getTransaction()
     */
    @Override
    public GnucashWritableTransaction getTransaction() {
        return (GnucashWritableTransaction) super.getTransaction();
    }

    /**
     * @param jwsdpPeer the JWSDP-object we are facading.
     * @param transaction the transaction we belong to
     * @throws JAXBException on issues with the XML-backend
     */
    public GnucashTransactionSplitWritingImpl(
            final TrnSplitType jwsdpPeer,
            final GnucashWritableTransaction transaction) throws JAXBException {
        super(jwsdpPeer, transaction);
    }

    /**
     * create a new split and and add it to the given transaction.
     * @param transaction transaction the transaction we will belong to
     * @param account the account we take money (or other things) from or give it to
     * @param pSplitID
     * @throws JAXBException
     */
    public GnucashTransactionSplitWritingImpl(final GnucashTransactionWritingImpl transaction,
                                              final GnucashAccount account) throws JAXBException {
        this(transaction, account, (transaction.getWritingFile()).createGUID());
    }

    /**
     * create a new split and and add it to the given transaction.
     * @param transaction transaction the transaction we will belong to
     * @param account the account we take money (or other things) from or give it to
     * @param pSplitID
     * @throws JAXBException
     */
    public GnucashTransactionSplitWritingImpl(final GnucashTransactionWritingImpl transaction,
                                              final GnucashAccount account, String pSplitID) throws JAXBException {
        super(createTransactionSplit(transaction, account, pSplitID), transaction);

        // this is a workaound.
        // if super does account.addSplit(this) it adds an instance on GnucashTransactionSplitImpl that is "!=(GnucashTransactionSplitWritingImpl)this";
        // thus we would get warnings about dublicate split-ids and can no longer compare splits by instance.
//        if(account!=null)
//            ((GnucashAccountImpl)account).replaceTransactionSplit(account.getTransactionSplitByID(getId()), GnucashTransactionSplitWritingImpl.this);

        transaction.addSplit(this);
    }

    /**
     * Creates a new Transaction and add's it to the given gnucash-file
     * Don't modify the ID of the new transaction!
     * @param pSplitID
     *
     * @param file
     * @return
     * @throws JAXBException
     */
    protected static GncTransactionType.TrnSplitsType.TrnSplitType createTransactionSplit(final GnucashTransactionWritingImpl transaction,
                                                                                          final GnucashAccount account,
                                                                                          final String pSplitID) throws JAXBException {

        if (transaction == null) {
            throw new IllegalArgumentException("null transaction given");
        }

        if (account == null) {
            throw new IllegalArgumentException("null account given");
        }

        if (pSplitID == null || pSplitID.trim().length() == 0) {
            throw new IllegalArgumentException("null or empty pSplitID given");
        }

        // this is needed because transaction.addSplit() later
        // must have an already build List of splits.
        // if not it will create the list from the JAXB-Data
        // thus 2 instances of this GnucashTransactionSplitWritingImpl
        // will exist. One created in getSplits() from this JAXB-Data
        // the other is this object.
        transaction.getSplits();

        GnucashFileWritingImpl gnucashFileImpl = transaction.getWritingFile();
        ObjectFactory factory = gnucashFileImpl.getObjectFactory();


        GncTransactionType.TrnSplitsType.TrnSplitType split = gnucashFileImpl.createGncTransactionTypeTrnSplitsTypeTrnSplitType();
        {
            GncTransactionType.TrnSplitsType.TrnSplitType.SplitIdType id = factory.createGncTransactionTypeTrnSplitsTypeTrnSplitTypeSplitIdType();
            id.setType("guid");
            id.setValue(pSplitID);
            split.setSplitId(id);
        }

        split.setSplitReconciledState("n");

        split.setSplitQuantity("0/100");
        split.setSplitValue("0/100");
        {
            GncTransactionType.TrnSplitsType.TrnSplitType.SplitAccountType splitaccount = factory.createGncTransactionTypeTrnSplitsTypeTrnSplitTypeSplitAccountType();
            splitaccount.setType("guid");
            splitaccount.setValue(account.getId());
            split.setSplitAccount(splitaccount);
        }




        return split;
    }


    /**
     * remove this split from it's transaction.
     * @throws JAXBException if we have issues accessing the XML-Backend.
     */
    public void remove() throws JAXBException {
        getTransaction().remove(this);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit#setAccount(biz.wolschon.fileformats.gnucash.GnucashAccount)
     */
    public void setAccountID(final String accountId) {
        setAccount(getTransaction().getGnucashFile().getAccountByID(accountId));
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit#setAccount(biz.wolschon.fileformats.gnucash.GnucashAccount)
     */
    public void setAccount(final GnucashAccount account) {
        if (account == null) {
            throw new NullPointerException("null account given");
        }
        String old = (getJwsdpPeer().getSplitAccount() == null ? null
                        :
                        getJwsdpPeer().getSplitAccount().getValue());
        getJwsdpPeer().getSplitAccount().setType("guid");
        getJwsdpPeer().getSplitAccount().setValue(account.getId());
        ((GnucashWritableFile) getGnucashFile()).setModified(true);

        if (old == null || !old.equals(account.getId())) {
            if (getPropertyChangeSupport() != null) {
                getPropertyChangeSupport().firePropertyChange("accountID", old, account.getId());
            }
        }

    }
    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit#setQuantity(biz.wolschon.numbers.FixedPointNumber)
     */
    public void setQuantity(final String n) {
        try {
            this.setQuantity(new FixedPointNumber(n.toLowerCase().replaceAll("&euro;", "").replaceAll("&pound;", "")));
        } catch (NumberFormatException e) {
            try {
                Number parsed = this.getQuantityCurrencyFormat().parse(n);
                this.setQuantity(new FixedPointNumber(parsed.toString()));
            } catch (NumberFormatException e1) {
                throw e;
            } catch (ParseException e1) {
                throw e;
            }
        }
    }


    /**
     * @return true if the currency of transaction and account match
     */
    private boolean isCurrencyMatching() {
        GnucashAccount account = getAccount();
        if (account == null) {
            return false;
        }
        GnucashWritableTransaction transaction = getTransaction();
        if (transaction == null) {
            return false;
        }
        String actCID = account.getCurrencyID();
        if (actCID == null) {
            return false;
        }
        String actCNS = account.getCurrencyNameSpace();
        if (actCNS == null) {
            return false;
        }
        return (actCID.equals(transaction.getCurrencyID())
                &&
            actCNS.equals(transaction.getCurrencyNameSpace())
                );
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit#setQuantity(biz.wolschon.numbers.FixedPointNumber)
     */
    public void setQuantity(final FixedPointNumber n) {
        if (n == null) {
            throw new NullPointerException("null quantity given");
        }

        String old = getJwsdpPeer().getSplitQuantity();
        getJwsdpPeer().setSplitQuantity(n.toGnucashString());
        ((GnucashWritableFile) getGnucashFile()).setModified(true);
        if (isCurrencyMatching()) {
            String oldvalue = getJwsdpPeer().getSplitValue();
            getJwsdpPeer().setSplitValue(n.toGnucashString());
            if (old == null || !old.equals(n.toGnucashString())) {
                if (getPropertyChangeSupport() != null) {
                    getPropertyChangeSupport().firePropertyChange("value", new FixedPointNumber(oldvalue), n);
                }
            }
        }


        if (old == null || !old.equals(n.toGnucashString())) {
            if (getPropertyChangeSupport() != null) {
                getPropertyChangeSupport().firePropertyChange("quantity", new FixedPointNumber(old), n);
            }
        }
    }

    /**
     * @see GnucashWritableTransactionSplit#setValue(FixedPointNumber)
     */
    public void setValue(final String n) {
        try {
            this.setValue(new FixedPointNumber(n.toLowerCase().replaceAll("&euro;", "").replaceAll("&pound;", "")));
        } catch (NumberFormatException e) {
            try {
                Number parsed = this.getValueCurrencyFormat().parse(n);
                this.setValue(new FixedPointNumber(parsed.toString()));
            } catch (NumberFormatException e1) {
                throw e;
            } catch (ParseException e1) {
                throw e;
            }
        }
    }

    /**
     * @see GnucashWritableTransactionSplit#setValue(FixedPointNumber)
     */
    public void setValue(final FixedPointNumber n) {
        if (n == null) {
            throw new NullPointerException("null value given");
        }
        String old = getJwsdpPeer().getSplitValue();
        getJwsdpPeer().setSplitValue(n.toGnucashString());
        ((GnucashWritableFile) getGnucashFile()).setModified(true);
        if (isCurrencyMatching()) {
            String oldquantity = getJwsdpPeer().getSplitQuantity();
            getJwsdpPeer().setSplitQuantity(n.toGnucashString());
            if (old == null || !old.equals(n.toGnucashString())) {
                if (getPropertyChangeSupport() != null) {
                    getPropertyChangeSupport().firePropertyChange("quantity", new FixedPointNumber(oldquantity), n);
                }
            }
        }

        if (old == null || !old.equals(n.toGnucashString())) {
            if (getPropertyChangeSupport() != null) {
                getPropertyChangeSupport().firePropertyChange("value", new FixedPointNumber(old), n);
            }
        }
    }

    /**
     * Set the description-text.
     * @param desc the new description
     */
    public void setDescription(final String desc) {
        if (desc == null) {
            throw new IllegalArgumentException("null description given! Please use the empty string instead of null for an empty description");
        }

        String old = getJwsdpPeer().getSplitMemo();
        getJwsdpPeer().setSplitMemo(desc);
        ((GnucashWritableFile) getGnucashFile()).setModified(true);

        if (old == null || !old.equals(desc)) {
            if (getPropertyChangeSupport() != null) {
                getPropertyChangeSupport().firePropertyChange("description", old, desc);
            }
        }
    }


    /**
     * Set the type of association this split has with
     * an invoice's lot.
     * @param action null, "Zahlung" or "Rechnung" or freeform-string
     */
    public void setSplitAction(final String action) {
        /*if (action != null
                &&
            !action.equals("Zahlung")
                &&
            !action.equals("Rechnung")) {
                throw new IllegalArgumentException("action may only be null, 'Zahlung' or 'Rechnung'");
            }*/


        String old = getJwsdpPeer().getSplitAction();
        getJwsdpPeer().setSplitAction(action);
        ((GnucashWritableFile) getGnucashFile()).setModified(true);

        if (old == null || !old.equals(action)) {
            if (getPropertyChangeSupport() != null) {
                getPropertyChangeSupport().firePropertyChange("splitAction", old, action);
            }
        }
    }




    @SuppressWarnings("unchecked")
    public void setLotID(final String lotID) throws JAXBException {

        GnucashTransactionWritingImpl transaction = (GnucashTransactionWritingImpl) getTransaction();
        GnucashFileWritingImpl writingFile = transaction.getWritingFile();
        ObjectFactory factory = writingFile.getObjectFactory();

        if (getJwsdpPeer().getSplitLot() == null) {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncTransactionType.TrnSplitsType.TrnSplitType.SplitLotType lot = factory.createGncTransactionTypeTrnSplitsTypeTrnSplitTypeSplitLotType();
            getJwsdpPeer().setSplitLot(lot);
        }
        getJwsdpPeer().getSplitLot().setValue(lotID);
        getJwsdpPeer().getSplitLot().setType("guid");

        // if we have a lot, we are a "P"aying transaction, check the slots
        SlotsType slots = getJwsdpPeer().getSplitSlots();
        if(slots==null) {
            slots = factory.createSlotsType();
            getJwsdpPeer().setSplitSlots(slots);
        }
        if (slots.getSlot()==null) {
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotType slot = factory.createSlotType();
            slot.setSlotKey("trans-txn-type");
            biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType value = factory.createSlotValueType();
            value.setType("string");
            value.getContent().add("P");
            slot.setSlotValue(value);
            slots.getSlot().add(slot);
        }

    }



// --------------------- support for propertyChangeListeners ---------------

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit#setQuantityFormatetForHTML(java.lang.String)
     */
    public void setQuantityFormatetForHTML(final String n) {
        this.setQuantity(n);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit#setValueFormatetForHTML(java.lang.String)
     */
    public void setValueFormatetForHTML(final String n) {
        this.setValue(n);
    }


    /**
     *
     * ${@inheritDoc}.
     */
    public GnucashWritableFile getWritableGnucashFile() {
        return (GnucashWritableFile) getGnucashFile();
    }
}
