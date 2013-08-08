/**
 * GnucashAccountImpl.java
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.wolschon.fileformats.gnucash.GnucashAccount;
import biz.wolschon.fileformats.gnucash.GnucashTransactionSplit;
import biz.wolschon.fileformats.gnucash.GnucashWritableAccount;
import biz.wolschon.fileformats.gnucash.GnucashWritableFile;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.ObjectFactory;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.Slot;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotValueType;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.SlotsType;
import biz.wolschon.numbers.FixedPointNumber;

/**
 * created: 16.05.2005 <br/>
 *
 * Extension of GnucashAccountImpl to allow writing instead of
 * read-only access.<br/>
 * Supported properties for the propertyChangeListeners:
 * <ul>
 *  <li>name</li>
 *  <li>code</li>
 *  <li>currencyID</li>
 *  <li>currencyNameSpace</li>
 *  <li>description</li>
 *  <li>type</li>
 *  <li>parentAccount</li>
 *  <li>transactionSplits (not giving the old value of the list)</li>
 * </ul>
 * @author <a href="mailto:Marcus@Wolschon.biz">Marcus Wolschon</a>
 */
public class GnucashAccountWritingImpl extends GnucashAccountImpl implements GnucashWritableAccount {


    /**
     * Our logger for debug- and error-ourput.
     */
    private static final Log LOGGER = LogFactory.getLog(GnucashAccountWritingImpl.class);

    /**
     * Our helper to implement the GnucashWritableObject-interface.
     */
    private GnucashWritableObjectHelper helper;

    /**
     * {@inheritDoc}
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableObject#setUserDefinedAttribute(java.lang.String, java.lang.String)
     */
    public void setUserDefinedAttribute(final String name, final String value) throws JAXBException {
        if (helper == null) {
            helper = new GnucashWritableObjectHelper(super.helper);
        }
        LOGGER.debug("GnucashAccountWritingImpl[account-id="
                + getId() + " name="
                + getName() + "].setUserDefinedAttribute(name="
                + name + ", value="
                + value + ")");
        helper.setUserDefinedAttribute(name, value);
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashAccountImpl#GnucashAccountImpl(GncAccountType, GnucashFileImpl)
     */
    public GnucashAccountWritingImpl(final GncAccountType jwsdpPeer,
                                     final GnucashFileImpl file) throws JAXBException {
        super(jwsdpPeer, file);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashAccountImpl#GnucashAccountImpl(GncAccountType, GnucashFileImpl)
     */
    public GnucashAccountWritingImpl(final GnucashFileWritingImpl file) throws JAXBException {
        super(createAccount(file, file.createGUID()), file);
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashAccountImpl#GnucashAccountImpl(GncAccountType, GnucashFileImpl)
     */
    public GnucashAccountWritingImpl(final GnucashFileWritingImpl file, final String id) throws JAXBException {
        super(createAccount(file, id), file);
    }


    /**
     * @param file
     * @return
     * @throws JAXBException
     */
    private static GncAccountType createAccount(final GnucashFileWritingImpl file, final String accountguid) throws JAXBException {
        ObjectFactory factory = file.getObjectFactory();

        GncAccountType account = factory.createGncAccount();
        //left unset account.setActCode();
        account.setActCommodityScu((short) 100); // x,yz
        account.setActDescription("no description yet");
//      left unset account.setActLots();
        account.setActName("UNNAMED");
//      left unset account.setActNonStandardScu();
        //left unset account.setActParent())
        account.setActType("BANK");

        account.setVersion("2.0.0");


        {
            GncAccountType.ActCommodityType currency = factory.createGncAccountTypeActCommodityType();
            currency.setCmdtyId(file.getDefaultCurrencyID());
            currency.setCmdtySpace("ISO4217");
            account.setActCommodity(currency);
        }

        {
            GncAccountType.ActIdType guid = factory.createGncAccountTypeActIdType();
            guid.setType("guid");
            guid.setValue(accountguid);
            account.setActId(guid);
        }

        {
            SlotsType slots = factory.createSlotsType();
            account.setActSlots(slots);
        }

        {
            Slot slot = factory.createSlot();
            slot.setSlotKey("placeholder");
            SlotValueType slottype = factory.createSlotValueType();
            slottype.setType("string");
            slottype.getContent().add("false");
            slot.setSlotValue(slottype);
            account.getActSlots().getSlot().add(slot);
        }

        {
            Slot slot = factory.createSlot();
            slot.setSlotKey("notes");
            SlotValueType slottype = factory.createSlotValueType();
            slottype.setType("string");
            slottype.getContent().add("");
            slot.setSlotValue(slottype);
            account.getActSlots().getSlot().add(slot);
        }


        file.getRootElement().getGncBook().getBookElements().add(account);
        file.setModified(true);
        return account;
    }

    /**
     * Remove this account from the sytem.<br/>
     * Throws IllegalStateException if this account has splits or childres.
     */
    public void remove() {
     if (getTransactionSplits().size() > 0) {
        throw new IllegalStateException("cannot remove account while it contains transaction-splits!");
    }
     if (this.getChildren().size() > 0) {
        throw new IllegalStateException("cannot remove account while it contains child-accounts!");
    }

     getWritableGnucashFile().getRootElement().getGncBook().getBookElements().remove(getJwsdpPeer());
     getWritableGnucashFile().removeAccount(this);
    }


    /**
     * The gnucash-file is the top-level class to contain everything.
     * @return the file we are associated with
     */
    public GnucashWritableFile getWritableGnucashFile() {
     return (GnucashFileWritingImpl) getGnucashFile();
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#addTransactionSplit(biz.wolschon.fileformats.gnucash.GnucashTransactionSplit)
     */
    @Override
    public void addTransactionSplit(final GnucashTransactionSplit split) {
        super.addTransactionSplit(split);

        setIsModified();
        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("transactionSplits", null, getTransactionSplits());
        }
    }


    /**
     * @param impl the split to remove
     */
    protected void removeTransactionSplit(final GnucashWritableTransactionSplit impl) {
        List transactionSplits = getTransactionSplits();
        transactionSplits.remove(impl);

        setIsModified();
        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("transactionSplits", null, transactionSplits);
        }
    }

    /**
     *
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableAccount#setName(java.lang.String)
     */
    public void setName(final String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("null or empty name given!");
        }


        Object old = getJwsdpPeer().getActName();
        if (old == name) {
            return; // nothing has changed
        }
        this.getJwsdpPeer().setActName(name);
        setIsModified();
        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("name", old, name);
        }
    }
    /**
    *
    *
    * @see biz.wolschon.fileformats.gnucash.GnucashWritableAccount#setAccountCode(java.lang.String)
    */
   public void setAccountCode(final String code) {
       if (code == null || code.trim().length() == 0) {
           throw new IllegalArgumentException("null or empty code given!");
       }


       Object old = getJwsdpPeer().getActCode();
       if (old == code) {
           return; // nothing has changed
       }
       this.getJwsdpPeer().setActCode(code);
       setIsModified();
       // <<insert code to react further to this change here
       PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
       if (propertyChangeFirer != null) {
           propertyChangeFirer.firePropertyChange("code", old, code);
       }
   }
    /**
     * @param currencyID the new currency
     * @see #setCurrencyNameSpace(String)
     * @see ${@link GnucashAccount#getCurrencyID()}
     */
    public void setCurrencyID(final String currencyID) {
        if (currencyID == null) {
            throw new IllegalArgumentException("null or empty currencyID given!");
        }


        Object old = getJwsdpPeer().getActCommodity().getCmdtyId();
        if (old == currencyID) {
            return; // nothing has changed
        }
        this.getJwsdpPeer().getActCommodity().setCmdtyId(currencyID);
        setIsModified();
        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("currencyID", old, currencyID);
        }
    }
    /**
     * @param currencyNameSpace the new namespace
     * @see ${@link GnucashAccount#getCurrencyNameSpace()}
     */
    public void setCurrencyNameSpace(final String currencyNameSpace) {
        if (currencyNameSpace == null) {
            throw new IllegalArgumentException("null or empty currencyNameSpace given!");
        }


        Object old = getJwsdpPeer().getActCommodity().getCmdtySpace();
        if (old == currencyNameSpace) {
            return; // nothing has changed
        }
        this.getJwsdpPeer().getActCommodity().setCmdtySpace(currencyNameSpace);
        setIsModified();
        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("currencyNameSpace", old, currencyNameSpace);
        }
    }

    /**
     * set getWritableFile().setModified(true).
     */
    protected void setIsModified() {
        GnucashWritableFile writableFile = getWritableGnucashFile();
        writableFile.setModified(true);
    }

    /**
     * Used by ${@link #getBalance()} to cache the result.
     */
    private FixedPointNumber myBalanceCached = null;

    /**
     * Used by ${@link #getBalance()} to cache the result.
     */
    private PropertyChangeListener myBalanceCachedInvalidtor = null;

    /**
     * same as getBalance(new Date()).<br/>
     * ignores transactions after the current date+time<br/>
     * This implementation caches the result.<br/>
     * We assume that time does never move backwards
     * @see #getBalance(Date)
     */
    @Override
    public FixedPointNumber getBalance() {

        if (myBalanceCached != null) {
            return myBalanceCached;
        }

        Collection<GnucashTransactionSplit> after = new LinkedList<GnucashTransactionSplit>();
        FixedPointNumber balance = getBalance(new Date(), after);

        if (after.isEmpty()) {
            myBalanceCached = balance;

            // add a listener to keep the cache up to date
            if (myBalanceCachedInvalidtor != null) {
                myBalanceCachedInvalidtor = new PropertyChangeListener() {
                    private final Collection<GnucashTransactionSplit> splitsWeAreAddedTo = new HashSet<GnucashTransactionSplit>();
                    public void propertyChange(final PropertyChangeEvent evt) {
                        myBalanceCached = null;

                        // we don't handle the case of removing an account
                        // because that happenes seldomly enough

                        if (evt.getPropertyName().equals("account")
                                &&
                            evt.getSource() instanceof GnucashWritableTransactionSplit) {
                            GnucashWritableTransactionSplit splitw = (GnucashWritableTransactionSplit) evt.getSource();
                            if (splitw.getAccount() != GnucashAccountWritingImpl.this) {
                                splitw.removePropertyChangeListener("account", this);
                                splitw.removePropertyChangeListener("quantity", this);
                                splitw.getTransaction().removePropertyChangeListener("datePosted", this);
                                splitsWeAreAddedTo.remove(splitw);

                            }

                        }
                        if (evt.getPropertyName().equals("transactionSplits")) {
                            Collection<GnucashTransactionSplit> splits = (Collection<GnucashTransactionSplit>) evt.getNewValue();
                            for (GnucashTransactionSplit split : splits) {
                                if (!(split instanceof GnucashWritableTransactionSplit)
                                        ||
                                        splitsWeAreAddedTo.contains(split)
                                    ) {
                                    continue;
                                }
                                GnucashWritableTransactionSplit splitw = (GnucashWritableTransactionSplit) split;
                                splitw.addPropertyChangeListener("account", this);
                                splitw.addPropertyChangeListener("quantity", this);
                                splitw.getTransaction().addPropertyChangeListener("datePosted", this);
                                splitsWeAreAddedTo.add(splitw);
                            }
                        }
                    }
                };
                addPropertyChangeListener("currencyID", myBalanceCachedInvalidtor);
                addPropertyChangeListener("currencyNameSpace", myBalanceCachedInvalidtor);
                addPropertyChangeListener("transactionSplits", myBalanceCachedInvalidtor);
            }
        }

        return balance;
    }


    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableAccount#setDescription(java.lang.String)
     */
    public void setDescription(final String description) {
        if (description == null) {
            throw new IllegalArgumentException("null or empty description given!");
        }


        Object old = getJwsdpPeer().getActDescription();
        if (old == description) {
            return; // nothing has changed
        }
        getJwsdpPeer().setActDescription(description);
        setIsModified();
        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("description", old, description);
        }
    }

    /**
     *
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableAccount#setType(java.lang.String)
     */
    public void setType(final String type) {
        if (type == null) {
            throw new IllegalArgumentException("null type given!");
        }


        Object old = getJwsdpPeer().getActDescription();
        if (old == type) {
            return; // nothing has changed
        }
        getJwsdpPeer().setActType(type);
        setIsModified();
        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("type", old, type);
        }
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableAccount#setParentAccount(biz.wolschon.fileformats.gnucash.GnucashAccount)
     */
    public void setParentAccountId(final String newParent) throws JAXBException {
        if (newParent == null || newParent.trim().length() == 0) {
            setParentAccount(null);
        } else {
            setParentAccount(getGnucashFile().getAccountByID(newParent));
        }
    }

    /**
     * @see biz.wolschon.fileformats.gnucash.GnucashWritableAccount#setParentAccount(biz.wolschon.fileformats.gnucash.GnucashAccount)
     */
    public void setParentAccount(final GnucashAccount parentAccount) throws JAXBException {

        if (parentAccount == null) {
            this.getJwsdpPeer().setActParent(null);
            return;
        }

        if (parentAccount == this) {
            throw new IllegalArgumentException("I cannot be my own parent!");
        }

        // check if newparent is a child-account recusively
        if (isChildAccountRecursive(parentAccount)) {
            throw new IllegalArgumentException("I cannot be my own (grand-)parent!");
        }


        Object old = null;
        biz.wolschon.fileformats.gnucash.jwsdpimpl.generated.GncAccountType.ActParentType parent = getJwsdpPeer().getActParent();
        if (parent == null) {
            parent = ((GnucashFileWritingImpl) getWritableGnucashFile())
                        .getObjectFactory().createGncAccountTypeActParentType();
            parent.setType("guid");
            parent.setValue(parentAccount.getId());
            getJwsdpPeer().setActParent(parent);

        } else {
            old = getParentAccount();
            parent.setValue(parentAccount.getId());
        }
        setIsModified();

        // <<insert code to react further to this change here
        PropertyChangeSupport propertyChangeFirer = getPropertyChangeSupport();
        if (propertyChangeFirer != null) {
            propertyChangeFirer.firePropertyChange("parentAccount", old, parentAccount);
        }
    }


    /**
     * Get the sum of all transaction-splits
     * affecting this account in the given time-frame.
     * @param from when to start, inclusive
     * @param to when to stop, exlusive.
     * @return the sum of all transaction-splits
     *         affecting this account in the given time-frame.
     */
    public FixedPointNumber getBalanceChange(final Date from, final Date to) {
        FixedPointNumber retval = new FixedPointNumber();

        for (Object element : getTransactionSplits()) {
            GnucashTransactionSplit split = (GnucashTransactionSplit) element;
            Date whenHappened = split.getTransaction().getDatePosted();
            if (!whenHappened.before(to)) {
                continue;
            }
            if (whenHappened.before(from)) {
                continue;
            }
            retval = retval.add(split.getQuantity());
        }
        return retval;
    }


//  -------------------------------------------------------


}
