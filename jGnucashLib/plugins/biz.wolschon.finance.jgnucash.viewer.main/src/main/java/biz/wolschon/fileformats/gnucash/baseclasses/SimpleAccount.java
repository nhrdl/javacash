/**
 * SimpleAccount.java
 * License: GPLv3 or later
 * created: 22.05.2006 17:56:15
 */
package biz.wolschon.fileformats.gnucash.baseclasses;

//other imports
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.wolschon.fileformats.gnucash.GnucashAccount;
import biz.wolschon.fileformats.gnucash.GnucashFile;
import biz.wolschon.fileformats.gnucash.GnucashTransaction;
import biz.wolschon.fileformats.gnucash.GnucashTransactionSplit;
import biz.wolschon.finance.ComplexCurrencyTable;
import biz.wolschon.numbers.FixedPointNumber;


/**
 * (c) 2006 by Wolschon Softwaredesign und Beratung.<br/>
 * Project: gnucashReader<br/>
 * SimpleAccount.java<br/>
 * created: 22.05.2006 17:56:15 <br/>
 *<br/><br/>
 * This is a base-class that helps implementing the GnucashAccount
 * -interface with it's extenive number of convenience-methods.<br/>
 * @author <a href="Marcus@Wolschon.biz">Marcus Wolschon</a>
 */
public abstract class SimpleAccount  implements GnucashAccount {



	/**
     * Our logger for debug- and error-ourput.
     */
    private static final Log LOGGER = LogFactory.getLog(SimpleAccount.class);


    /**
     * The file we belong to.
     */
    private final GnucashFile myFile;

    /**
     * @param slots ${@link #mySlots}
     * @param myFile The file we belong to
     */
    public SimpleAccount(final GnucashFile myFile) {
        super();
        this.myFile = myFile;
    }



    /**
     * The returned list ist sorted by the natural order of the Transaction-Splits.
     * @return all splits
     * @see ${@link GnucashTransaction}
     */
     public List<GnucashTransaction> getTransactions() {
         List<? extends GnucashTransactionSplit> splits = getTransactionSplits();
         List<GnucashTransaction> retval = new ArrayList<GnucashTransaction>(splits.size());

         for (Object element : splits) {
             GnucashTransactionSplit split = (GnucashTransactionSplit) element;
             retval.add(split.getTransaction());
         }

         return retval;
     }



     /**
      * @return Returns the file.
      * @see ${@link #myFile}
      */
     public GnucashFile getGnucashFile() {
         return myFile;
     }

    /**
     * @param account the account to test
     * @return true if this is a child of us or any child's or us.
     */
    public boolean isChildAccountRecursive(final GnucashAccount account) {

        if (this == account) {
            return true;
        }


        for (Object element : getChildren()) {
            GnucashAccount child = (GnucashAccount) element;
            if (this == child) {
                return true;
            }
            if (child.isChildAccountRecursive(account)) {
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getQualifiedName();
    }

    /**
     * same as getBalance(new Date()).<br/>
     * ignores transactions after the current date+time
     * @see #getBalance(Date)
     */
    public FixedPointNumber getBalance() {
     return getBalance(new Date());
    }

    /**
     * get name including the name of the parent.accounts.
     * @return e.g. "Aktiva::test::test2"
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getQualifiedName()
     */
    public String getQualifiedName() {
        GnucashAccount acc = getParentAccount();
        if (acc == null || acc.getId() == getId()) {
            if (getParentAccountId() == null) {
                return getName();
            }

            return "UNKNOWN::" + getName();
        }
        return acc.getQualifiedName() + "::" + getName();
    }


    /**
     *
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getParentAccount()
     */
    public GnucashAccount getParentAccount() {
        String id = getParentAccountId();
        if (id == null) {
            return null;
        }

        return getGnucashFile().getAccountByID(id);
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getSubAccounts()
     */
    public Collection getSubAccounts() {
        return getChildren();
    }


   /**
    * @param date ignores transactions after the given date
    * @param currency the currency the result shall be in (use account-currency if null)
    * @return null if the conversion is not possible
    * @see #getBalance(Date)
    */
   public FixedPointNumber getBalance(final Date date,
                                      final Currency currency) {


       FixedPointNumber retval = getBalance(date);

       if (retval == null) {
           LOGGER.warn("SimpleAccount.getBalance() - "
                     + "error creating balance!");
           return null;
       }

       if (currency == null || retval.equals(new FixedPointNumber())) {
        return retval;
    }

       // is conversion needed?
       if (getCurrencyNameSpace().equals(GnucashAccount.CURRENCYNAMESPACE_CURRENCY)
                &&
            getCurrencyID().equals(currency.getCurrencyCode())) {
        return retval;
    }

       ComplexCurrencyTable currencyTable = getGnucashFile().getCurrencyTable();

       if (currencyTable == null) {
           LOGGER.warn("SimpleAccount.getBalance() - cannot transfer "
                   + "to given currency because we have no currency-table!");
           return null;
       }


       if (!currencyTable.convertToBaseCurrency(getCurrencyNameSpace(),
                retval,
                getCurrencyID())) {
           LOGGER.warn("SimpleAccount.getBalance() - cannot transfer "
                   + "from our currency '"
                   + getCurrencyNameSpace() + "'-'"
                   + getCurrencyID()
                   + "' to the base-currency!");
           return null;
       }

       if (!currencyTable.convertFromBaseCurrency(retval, currency.getCurrencyCode())) {
           LOGGER.warn("SimpleAccount.getBalance() - cannot transfer "
                   + "from base-currenty to given currency '"
                   + currency
                   + "'!");
           return null;
       }

       return retval;
   }

   /**
   *
   * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalanceRecursiveFormated(java.util.Date)
   */
  public String getBalanceRecursiveFormated(final Date date) {
      return getCurrencyFormat().format(getBalanceRecursive(date));
  }

  /**
   *
   * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalanceRecursiveFormated()
   */
  public String getBalanceRecursiveFormated() {
      return getCurrencyFormat().format(getBalanceRecursive());
  }
  /**
   *
   *
   * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalanceRecursive()
   */
  public FixedPointNumber getBalanceRecursive() {

      return getBalanceRecursive(new Date());
  }

  /**
   *
   *
   * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalanceRecursive(java.util.Date)
   */
  public FixedPointNumber getBalanceRecursive(final Date date) {

      return getBalanceRecursive(date, this.getCurrencyNameSpace(), this.getCurrencyID());
  }

  /**
   * Gets the last transaction-split before the given date.
   * @param date if null, the last split of all time is returned
   * @return the last transaction-split before the given date
   */
   public GnucashTransactionSplit getLastSplitBeforeRecursive(final Date date) {

       GnucashTransactionSplit lastSplit = null;

       for (Object element : getTransactionSplits()) {
          GnucashTransactionSplit split = (GnucashTransactionSplit) element;
          if (date == null || split.getTransaction().getDatePosted().before(date)) {
            if (lastSplit == null || split.getTransaction().getDatePosted().after(lastSplit.getTransaction().getDatePosted())) {
                lastSplit = split;
            }
        }
      }

       for (Iterator iter = getSubAccounts().iterator(); iter.hasNext();) {
          GnucashAccount account = (GnucashAccount) iter.next();
          GnucashTransactionSplit split = account.getLastSplitBeforeRecursive(date);
          if (split != null && split.getTransaction() != null) {
            if (lastSplit == null || split.getTransaction().getDatePosted().after(lastSplit.getTransaction().getDatePosted())) {
                lastSplit = split;
            }
        }
      }


       return lastSplit;
   }

   /**
    * Ignores accounts for wich this conversion is not possible.
    * @param date ignores transactions after the given date
    * @param currencyNameSpace the currency the result shall be in
    * @param currencyName the currency the result shall be in
    * @return Gets the balance including all sub-accounts.
    * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalanceRecursive(Date, Currency)
    */
   public FixedPointNumber getBalanceRecursive(final Date date,
                                               final String currencyNameSpace,
                                               final String currencyName) {

       FixedPointNumber retval = getBalance(date, currencyNameSpace, currencyName);

       if (retval == null) {
        retval = new FixedPointNumber();
    }



       for (Object element : getChildren()) {
           GnucashAccount child = (GnucashAccount) element;
           retval.add(child.getBalanceRecursive(date, currencyNameSpace, currencyName));
       }

       return retval;
   }

 /**
  * Ignores accounts for wich this conversion is not possible.
  * @param date ignores transactions after the given date
  * @param currency the currency the result shall be in
  * @return Gets the balance including all sub-accounts.
  * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalanceRecursive(Date, Currency)
  */
 public FixedPointNumber getBalanceRecursive(final Date date,
                                             final Currency currency) {

     FixedPointNumber retval = getBalance(date, currency);

     if (retval == null) {
        retval = new FixedPointNumber();
    }



     for (Object element : getChildren()) {
         GnucashAccount child = (GnucashAccount) element;
         retval.add(child.getBalanceRecursive(date, currency));
     }

     return retval;
 }

 /**
  * @return true if ${@link #hasTransactions()} is true for this
  *         or any sub-accounts
  */
 public boolean hasTransactionsRecursive() {
     if (this.hasTransactions()) {
        return true;
    }

     for (Object element : getChildren()) {
         GnucashAccount child = (GnucashAccount) element;
         if (child.hasTransactionsRecursive()) {
            return true;
        }
     }

     return false;
 }
 /**
  * @return true if ${@link #getTransactionSplits()}.size()>0
  */
 public boolean hasTransactions() {
     return this.getTransactionSplits().size() > 0;
 }



   /**
    * @param date ignores transactions after the given date
    * @param currencyNameSpace the currency the result shall be in
    * @param currencyName the currency the result shall be in
    * @return null if the conversion is not possible
    * @see #getBalance(Date)
    */
   public FixedPointNumber getBalance(final Date date,
                                      final String currencyNameSpace,
                                      final String currencyName) {
       FixedPointNumber retval = getBalance(date);

       if (retval == null) {
           LOGGER.warn("SimpleAccount.getBalance() - "
                     + "error creating balance!");
           return null;
       }

       // is conversion needed?
       if (getCurrencyNameSpace().equals(currencyNameSpace)
                &&
           getCurrencyID().equals(currencyName)) {
        return retval;
    }



       ComplexCurrencyTable currencyTable = getGnucashFile().getCurrencyTable();

       if (currencyTable == null) {
           LOGGER.warn("SimpleAccount.getBalance() - cannot transfer "
                   + "to given currency because we have no currency-table!");
           return null;
       }

       if (!currencyTable.convertToBaseCurrency(getCurrencyNameSpace(),
                retval,
                getCurrencyID())) {
           Collection<String> currencies = getGnucashFile().getCurrencyTable().getCurrencies(getCurrencyNameSpace());
		LOGGER.warn("SimpleAccount.getBalance() - cannot transfer "
                   + "from our currency '"
                   + getCurrencyNameSpace() + "'-'"
                   + getCurrencyID()
                   + "' to the base-currency!"
                   + " \n(we know " + getGnucashFile().getCurrencyTable().getNameSpaces().size()
                   + " currency-namespaces and "
                   + (currencies==null?"no":""+currencies.size())
                   + " currencies in our namespace)");
           return null;
       }

       if (!currencyTable.convertFromBaseCurrency(currencyNameSpace, retval, currencyName)) {
           LOGGER.warn("SimpleAccount.getBalance() - cannot transfer "
                   + "from base-currenty to given currency '"
                   + currencyNameSpace
                   + "-"
                   + currencyName
                   + "'!");
           return null;
       }

       return retval;
   }



    /**
     * The currency-format to use for formating.<br/>
     * Plase access this only via {@link #getCurrencyFormat()}.
     */
    private static NumberFormat currencyFormat = null;

    /**
     * @return null if we are no currency but e.g. a fund
     */
    public Currency getCurrency() {

        if (!getCurrencyNameSpace().equals(GnucashAccount.CURRENCYNAMESPACE_CURRENCY)) {
            return null;
        }

        String gnucashCurrencyID = getCurrencyID();
        return Currency.getInstance(gnucashCurrencyID);
    }

    /**
     *
     * @return The currency-format to use for formating.
     */
    public NumberFormat getCurrencyFormat() {
        if (currencyFormat == null) {
            currencyFormat = NumberFormat.getCurrencyInstance();
        }

        // the currency may have changed
        if (this.getCurrencyNameSpace().equals(GnucashAccount.CURRENCYNAMESPACE_CURRENCY)) {
            Currency currency = getCurrency();
            currencyFormat.setCurrency(currency);
        } else {
                currencyFormat = NumberFormat.getNumberInstance();
        }

        return currencyFormat;
    }


    /**
     * same as {@link #getBalance(Date)}. <br/>
     * ignores transactions after the current date+time.
     * @see #getBalance(Date)
     */
    public String getBalanceFormated() {
     return getCurrencyFormat().format(getBalance());
    }

    /**
     * same as {@link #getBalance(Date)}. <br/>
     * ignores transactions after the current date+time.
     * @see #getBalance(Date)
     */
    public String getBalanceFormated(final Locale loc) {

        NumberFormat cf = NumberFormat.getCurrencyInstance(loc);
        cf.setCurrency(getCurrency());
        return cf.format(getBalance());
    }

    /**
     * The currency will be the one of this account.
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalance(java.util.Date)
     */
    public FixedPointNumber getBalance(final Date date) {
        return getBalance(date, (Collection<GnucashTransactionSplit>) null);
    }

    /**
     * The currency will be the one of this account.
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalance(Date, Collection)
     */
    public FixedPointNumber getBalance(final Date date, final Collection<GnucashTransactionSplit> after) {

        FixedPointNumber balance = new FixedPointNumber();

        for (Object element : getTransactionSplits()) {
            GnucashTransactionSplit split = (GnucashTransactionSplit) element;

            if (date != null
                  &&
                split.getTransaction().getDatePosted().after(date)) {
                if (after != null) {
                    after.add(split);
                }
                continue;
            }


            // the currency of the quantity is the one of the account
            balance.add(split.getQuantity());
        }
        return balance;
    }

    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getBalance(GnucashTransactionSplit)
     */
    public FixedPointNumber getBalance(
            final GnucashTransactionSplit lastIncludesSplit) {

        FixedPointNumber balance = new FixedPointNumber();
        for (Object element : getTransactionSplits()) {
            GnucashTransactionSplit split = (GnucashTransactionSplit) element;

             balance.add(split.getQuantity());

             if (split == lastIncludesSplit) {
                 break;
             }

        }
        return balance;
    }


    /**
     *
     * @see biz.wolschon.fileformats.gnucash.GnucashAccount#getTransactionSplitByID(java.lang.String)
     */
    public GnucashTransactionSplit getTransactionSplitByID(final String id) {
    	if (id == null) {
			throw new IllegalArgumentException("null id given!");
		}

        for (Object element2 : getTransactionSplits()) {
            GnucashTransactionSplit element = (GnucashTransactionSplit) element2;
            if (id.equals(element.getId())) {
                return element;
            }

        }
        return null;
    }


    /**
     * This is an extension to ${@link #compareNamesTo(Object)}
     * that makes shure that NEVER 2 accounts with different
     * IDs compare to 0.
     *
     * Compares our name to o.toString() .<br/>
     * If both starts with some digits the resulting
     * ${@link java.lang.Integer} are compared.<br/>
     * If one starts with a number and the other does not,
     * the one starting with a number is "bigger"<br/>
     * else and if both integers are equals a normals comparison of the
     * ${@link java.lang.String} is done.     *
     * @param   o the Object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the specified object.
     *
     * @throws ClassCastException if the specified object's type prevents it
     *         from being compared to this Object.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(final Object o) {

        int i = compareNamesTo(o);
        if (i != 0) {
            return i;
        }

        if (o instanceof GnucashAccount) {
            GnucashAccount other = (GnucashAccount) o;
            i = other.getId().compareTo(getId());
            if (i != 0) {
                return i;
            }
        }

        return ("" + hashCode()).compareTo("" + o.hashCode());

	}

    /**
     * Compares our name to o.toString() .<br/>
     * If both starts with some digits the resulting
     * ${@link java.lang.Integer} are compared.<br/>
     * If one starts with a number and the other does not,
     * the one starting with a number is "bigger"<br/>
     * else and if both integers are equals a normals comparison of the
     * �{@link java.lang.String} is done.     *
     * @param   o the Object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the specified object.
     *
     * @throws ClassCastException if the specified object's type prevents it
     *         from being compared to this Object.
     */
    public int compareNamesTo(final Object o) throws ClassCastException {




        // usually compare the qualified name
        String other = o.toString();
        String me = getQualifiedName();

        // if we have the same parent,
        // compare the unqualified name.
        // This enshures that the exception
        // for numbers is used within our parent-
        // account too and not just in the top-
        // level accounts
        if (o instanceof GnucashAccount
                &&
            ((GnucashAccount) o).getParentAccountId() != null
                &&
            getParentAccountId() != null
                &&
            ((GnucashAccount) o).getParentAccountId().equalsIgnoreCase(getParentAccountId())) {
            other = ((GnucashAccount) o).getName();
            me = getName();
        }

        // compare

        Long i0 = startsWithNumber(other);
        Long i1 = startsWithNumber(me);
        if (i0 == null && i1 != null) {
            return 1;
        }
        if (i1 == null && i0 != null) {
            return -1;
        }
        if (i0 == null) {
            return me.compareTo(other);
        }
        if (i1 == null) {
            return me.compareTo(other);
        }

        if (i1.equals(i0)) {
            return me.compareTo(other);
        }

        return i1.compareTo(i0);
    }


    /**
     * Helper used in ${@link #compareTo(Object)} to
     * compare names starting with a number.
     * @param s the name
     * @return the Integer build from the digits the name starts with or null
     */
    private Long startsWithNumber(final String s) {
        int digitCount = 0;
        for (int i = 0; i < s.length()
                          &&
                        Character.isDigit(s.charAt(i)); i++) {
            digitCount++;
        }
        if (digitCount == 0) {
            return null;
        }
        return new Long(s.substring(0, digitCount));
    }

//  ------------------------ support for propertyChangeListeners ------------------

    /**
     * support for firing PropertyChangeEvents.
     * (gets initialized only if we really have listeners)
     */
    private volatile PropertyChangeSupport myPropertyChange = null;

    /**
     * Returned value may be null if we never had listeners.
     * @return Our support for firing PropertyChangeEvents
     */
    protected PropertyChangeSupport getPropertyChangeSupport() {
        return myPropertyChange;
    }

    /**
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for all properties.
     *
     * @param listener  The PropertyChangeListener to be added
     */
    public final void addPropertyChangeListener(
                                                final PropertyChangeListener listener) {
        if (myPropertyChange == null) {
            myPropertyChange = new PropertyChangeSupport(this);
        }
        myPropertyChange.addPropertyChangeListener(listener);
    }

    /**
     * Add a PropertyChangeListener for a specific property.  The listener
     * will be invoked only when a call on firePropertyChange names that
     * specific property.
     *
     * @param propertyName  The name of the property to listen on.
     * @param listener  The PropertyChangeListener to be added
     */
    public final void addPropertyChangeListener(
                                                final String propertyName,
                                                final PropertyChangeListener listener) {
        if (myPropertyChange == null) {
            myPropertyChange = new PropertyChangeSupport(this);
        }
        myPropertyChange.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Remove a PropertyChangeListener for a specific property.
     *
     * @param propertyName  The name of the property that was listened on.
     * @param listener  The PropertyChangeListener to be removed
     */
    public final void removePropertyChangeListener(
                                                   final String propertyName,
                                                   final PropertyChangeListener listener) {
        if (myPropertyChange != null) {
            myPropertyChange.removePropertyChangeListener(propertyName, listener);
        }
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     * This removes a PropertyChangeListener that was registered
     * for all properties.
     *
     * @param listener  The PropertyChangeListener to be removed
     */
    public synchronized void removePropertyChangeListener(
                                                          final PropertyChangeListener listener) {
        if (myPropertyChange != null) {
            myPropertyChange.removePropertyChangeListener(listener);
        }
    }

//  -------------------------------------------------------


}
