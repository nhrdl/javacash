/**
 * TransactionsPanel.java
 * created: 21.10.2006 17:17:17
 */
package biz.wolschon.finance.jgnucash.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.wolschon.fileformats.gnucash.GnucashAccount;
import biz.wolschon.fileformats.gnucash.GnucashTransaction;
import biz.wolschon.fileformats.gnucash.GnucashTransactionSplit;
import biz.wolschon.finance.jgnucash.actions.TransactionSplitAction;
import biz.wolschon.finance.jgnucash.swingModels.GnucashSimpleAccountTransactionsTableModel;
import biz.wolschon.finance.jgnucash.swingModels.GnucashTransactionsSplitsTableModel;
import biz.wolschon.finance.jgnucash.widgets.MultiLineToolTip;
import biz.wolschon.numbers.FixedPointNumber;

/**
 * (c) 2006 by Wolschon Softwaredesign und Beratung.<br/>
 * Project: gnucashReader<br/>
 * TransactionsPanel.java<br/>
 * created: 21.10.2006 17:17:17 <br/>
 *<br/><br/>
 * <b>This Panel shows a list of transactions.
 * These transactions currently can only be "all transactions
 * of a single account"
 * but later other transaction-lists like search-results will be possible.</b>
 * @author <a href="Marcus@Wolschon.biz">Marcus Wolschon</a>
 */
public class TransactionsPanel extends JPanel {


    /**
     * Automatically created logger for debug and error-output.
     */
    private static final Log LOGGER = LogFactory.getLog(TransactionsPanel.class);

    /**
     * for serializing.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A scrollpane for ${@link #transactionTable}}.
     */
    private JScrollPane transactionTableScrollPane = null;

    /**
     * A table showing the transactions.
     */
    private JTable transactionTable;

    /**
     * A panel holding ${@link #selectionSummaryLabel}}.
     */
    private JPanel selectionSummaryPanel = null;  //  @jve:decl-index=0:visual-constraint="159,213"

    /**
     * A label showing summary-information about the selected transactions.
     */
    private JLabel selectionSummaryLabel = null;

    /**
     * A drop-down List to select the account the numbers
     * in ${@link #selectionSummaryLabel}} shall refer to.
     */
    private JComboBox selectionSummaryAccountComboBox = null;

    /**
     * The model of our ${@link #transactionTable}.
     */
    private GnucashTransactionsSplitsTableModel model;

    /**
     * The panel to show a single transaction.
     */
    private ShowTransactionPanel mySingleTransactionPanel;

    /**
     * A JPanel showing either {@link #getSelectionSummaryPanel()} or {@link #getSingleTransactionPanel()}.
     */
    private JPanel mySummaryPanel;

    /**
     * The actions we have on Splits.
     */
    private Collection<TransactionSplitAction> mySplitActions;

    /**
     * @return Returns the model.
     * @see #model
     */
    public GnucashTransactionsSplitsTableModel getModel() {
        return model;
    }

    /**
     * @param aModel The model to set.
     * @see #model
     */
    protected void setModel(final GnucashTransactionsSplitsTableModel aModel) {
        if (aModel == null) {
            throw new IllegalArgumentException("null 'aModel' given!");
        }

        Object old = model;
        if (old == aModel) {
            return; // nothing has changed
        }
        model = aModel;

        getTransactionTable().setModel(model);
        getTransactionTable().setAutoCreateRowSorter(true);
        // set column-width
        FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(transactionTable.getFont());
        getTransactionTable().getColumn("date").setPreferredWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.dateFormat.format(new Date())) + 5);
        getTransactionTable().getColumn("+").setPreferredWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.defaultCurrencyFormat.format(10000)));
        getTransactionTable().getColumn("-").setPreferredWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.defaultCurrencyFormat.format(-10000)));
        TableColumn balanceColumn = null;
        try {
            balanceColumn = getTransactionTable().getColumn("balance");
        } catch (Exception e) {
            // column is allowed to not exist
        }
        if (balanceColumn != null) {
            balanceColumn.setPreferredWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.defaultCurrencyFormat.format(-10000)));
        }

        getTransactionTable().getColumn("date").setMaxWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.dateFormat.format(new Date())) + 5);
        getTransactionTable().getColumn("+").setMaxWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.defaultCurrencyFormat.format(1000000)));
        getTransactionTable().getColumn("-").setMaxWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.defaultCurrencyFormat.format(-1000000)));
        if (balanceColumn != null) {
            balanceColumn.setMaxWidth(SwingUtilities.computeStringWidth(metrics, GnucashSimpleAccountTransactionsTableModel.defaultCurrencyFormat.format(-1000000)));
        }

        getTransactionTable().getColumn("transaction").setCellRenderer(new DesriptionCellRenderer());
        getTransactionTable().getColumn("description").setCellRenderer(new DesriptionCellRenderer());

        updateSelectionSummaryAccountList();
        updateSelectionSummary();
        getSingleTransactionPanel().setTransaction(null);

    }

    /**
     * This is the default constructor.
     */
    public TransactionsPanel() {
        super();
        initialize();
    }

    /**
     * Give an account who's transactions to display.
     * @param account if null, an empty table will be shown.
     */
    public void setAccount(final GnucashAccount account) {


        if (account == null) {
            setModel(new GnucashSimpleAccountTransactionsTableModel());
        } else {
            setModel(new GnucashSimpleAccountTransactionsTableModel(account));
        }
    }

    /**
     * This method initializes this panel.
     */
    private void initialize() {
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        this.add(getTransactionTableScrollPane(), BorderLayout.CENTER);
        this.add(getSummaryPanel(), BorderLayout.SOUTH);
        }

    /**
    *
    * @return a JPanel showing either {@link #getSelectionSummaryPanel()} or {@link #getSingleTransactionPanel()}.
    */
   private JPanel getSummaryPanel() {
       if (mySummaryPanel == null) {
           mySummaryPanel = new JPanel();
           mySummaryPanel.setLayout(new CardLayout());
           mySummaryPanel.add(getSelectionSummaryPanel(), "multi");
           getSingleTransactionPanel().setVisible(false);
           mySummaryPanel.add(getSingleTransactionPanel(), "single");
           getSummaryPanel().setPreferredSize(getSelectionSummaryPanel().getPreferredSize());
       }

       return mySummaryPanel;
   }

   /**
    * This method initializes transactionTableScrollPane
    *
    * @return javax.swing.JScrollPane
    */
   private JScrollPane getTransactionTableScrollPane() {
       if (transactionTableScrollPane == null) {
           transactionTableScrollPane = new JScrollPane();
           transactionTableScrollPane.setViewportView(getTransactionTable());
       }
       return transactionTableScrollPane;
   }

   /**
    * This method initializes transactionTable.
    *
    * @return javax.swing.JTable
    */
   protected JTable getTransactionTable() {
       if (transactionTable == null) {
           transactionTable = new JTable() {

               /**
                * Our TransactionsPanel.java.
                * @see long
                */
               private static final long serialVersionUID = 1L;

               /**
                * @see javax.swing.JTable#getToolTipText(java.awt.event.MouseEvent)
                */

               @Override
               public String getToolTipText(final MouseEvent event) {
                   java.awt.Point p = event.getPoint();
                   int rowIndex = rowAtPoint(p);
                   // convertColumnIndexToModel is needed,
                   // because the user may reorder columns
                   //int realColumnIndex = convertColumnIndexToModel(columnAtPoint(p));
                   if (rowIndex >= 0) {

                       GnucashSimpleAccountTransactionsTableModel model = (GnucashSimpleAccountTransactionsTableModel)getModel();
                       GnucashTransactionSplit localSplit = model.getTransactionSplit(rowIndex);
                       GnucashTransaction transaction = localSplit.getTransaction();
                       StringBuilder output = new StringBuilder();
                       output.append("\"")
                       .append(transaction.getTransactionNumber())
                       .append("\t \"")
                       .append(transaction.getDescription())
                       .append("\"\t [")
                       .append(localSplit.getAccount().getQualifiedName())
                       .append("]\t ")
                       .append(localSplit.getQuantity())
                       .append(localSplit.getAccount().getCurrencyNameSpace().equals(GnucashAccount.CURRENCYNAMESPACE_CURRENCY) ? " " : " x ")
                       .append(localSplit.getAccount().getCurrencyID())
                       .append("\n");

                       try {
                           for (GnucashTransactionSplit split : transaction.getSplits()) {
                               output.append("\"")
                               .append(split.getSplitAction())
                               .append("\"\t \"")
                               .append(split.getDescription())
                               .append("\"\t [")
                               .append(split.getAccount().getQualifiedName())
                               .append("]\t ")
                               .append(split.getQuantity())
                               .append(localSplit.getAccount().getCurrencyNameSpace().equals(GnucashAccount.CURRENCYNAMESPACE_CURRENCY) ? " " : " x ")
                               .append(split.getAccount().getCurrencyID())
                               .append("\n");
                           }
                           if (!transaction.isBalanced()) {
                               output.append("TRANSACTION IS NOT BALANACED! missung=" + transaction.getBalanceFormatet());
                           }
                       } catch (JAXBException e) {

                           TransactionsPanel.LOGGER.error("[JAXBException] Problem in " + getClass().getName(), e);
                           output.append("\nERROR: [" + e.getClass().getName() + "] " + e.getMessage());
                       }
                       return output.toString();
                   }

                   // show default-tooltip
                   return super.getToolTipText(event);
               }

               @Override
               public JToolTip createToolTip() {
                   MultiLineToolTip tip = new MultiLineToolTip();
                   tip.setComponent(this);
                   return tip;
               }

           };

           // add a listener to call updateSelectionSummary() every time
           // the user changes the selected rows.
           transactionTable.getSelectionModel().addListSelectionListener(
                   new ListSelectionListener() {
                       public void valueChanged(
                               final javax.swing.event.ListSelectionEvent e) {
                           try {
                            updateSelectionSummaryAccountList();
                               updateSelectionSummary();
                               if (getTransactionTable().getSelectedRowCount() == 1) {
                                   GnucashTransactionSplit transactionSplit = model.getTransactionSplit(getTransactionTable().getSelectedRow());
//                               setTransaction(transactionSplit.getTransaction());

                                   getSingleTransactionPanel().setTransaction(transactionSplit.getTransaction());
                                   getSingleTransactionPanel().setVisible(true);
                                   getSelectionSummaryPanel().setVisible(false);
                                   getSummaryPanel().setPreferredSize(getSingleTransactionPanel().getPreferredSize());
                                   //((CardLayout) getSelectionSummaryPanel().getLayout()).next(getSummaryPanel());
                               } else {
                                   getSingleTransactionPanel().setVisible(false);
                                   getSingleTransactionPanel().setTransaction(null);
                                   getSelectionSummaryPanel().setVisible(true);
                                   getSummaryPanel().setPreferredSize(getSelectionSummaryPanel().getPreferredSize());
                                   //((CardLayout) getSelectionSummaryPanel().getLayout()).next(getSummaryPanel());
                               }
                        } catch (Exception e1) {
                            LOGGER.error("", e1);
                        }
                       };
                   });
           setModel(new GnucashSimpleAccountTransactionsTableModel());
       }
       return transactionTable;
   }

   /**
    * This method initializes selectionSummaryLabel.
    *
    * @return JLabel
    */
   private JLabel getSelectionSummaryLabel() {
       if (selectionSummaryLabel == null) {
           selectionSummaryLabel = new JLabel();
           selectionSummaryLabel.setText("");
       }
       return selectionSummaryLabel;
   }

   /**
    * This method initializes selectionSummaryLabel.
    *
    * @return JLabel
    */
   private JComboBox getSelectionSummaryAccountComboBox() {
       if (selectionSummaryAccountComboBox == null) {
           selectionSummaryAccountComboBox = new JComboBox();
           selectionSummaryAccountComboBox.setEditable(false);
           selectionSummaryAccountComboBox.addItemListener(new ItemListener() {
               public void itemStateChanged(final java.awt.event.ItemEvent e) {
                   updateSelectionSummary();
               };
           });
       }
       return selectionSummaryAccountComboBox;
   }

   /**
    * This method initializes selectionSummaryPanel.
    *
    * @return javax.swing.JPanel
    */
   private JPanel getSelectionSummaryPanel() {
       if (selectionSummaryPanel == null) {
           selectionSummaryPanel = new JPanel();
           selectionSummaryPanel.setLayout(new BorderLayout());
           selectionSummaryPanel.add(getSelectionSummaryLabel(),
                   BorderLayout.CENTER);
           selectionSummaryPanel.add(getSelectionSummaryAccountComboBox(),
                   BorderLayout.SOUTH);
       }
       return selectionSummaryPanel;
   }

    /**
     * This method initializes ShowTransactionPanel.
     *
     * @return javax.swing.JPanel
     */
   protected ShowTransactionPanel getSingleTransactionPanel() {
       if (mySingleTransactionPanel == null) {
           mySingleTransactionPanel = new ShowTransactionPanel();
           mySingleTransactionPanel.setSplitActions(getSplitActions());
       }
       return mySingleTransactionPanel;
   }


   /**
    * Put all accounts into the model of
    * the selectionSummaryAccountComboBox.
    */
   private void updateSelectionSummaryAccountList() {

       Set<GnucashAccount> accounts = new TreeSet<GnucashAccount>();

       int selectedCount = getTransactionTable().getSelectedRowCount();
       if (selectedCount < 1) {
           // get all accounts of all splits
           int count = model.getRowCount();

           for (int i = 0; i<count; i++) {
               GnucashTransactionSplit transactionSplit =
                   model.getTransactionSplit(i);

               try {
                   GnucashTransaction transaction = transactionSplit.getTransaction();
                   if (transaction == null) {
                       LOGGER.error("Split has no transaction");
                   } else {
                       Collection<? extends GnucashTransactionSplit> splits =
                           transaction.getSplits();
                       for (GnucashTransactionSplit split : splits) {
                           try {
                               GnucashAccount account = split.getAccount();
                               if (account != null) {
                                   if (!accounts.contains(account)) {
                                       accounts.add(account);
                                   }
                               }
                           } catch (Exception x) {
                               System.err.println("Ignoring account in "
                                       + "TransactionPanel::updateSelectionSummary"
                                       + "AccountList() because of:");
                               x.printStackTrace(System.err);
                           }
                       }
                   }
               } catch (Exception e) {
                   LOGGER.error("Problem in "
                              + getClass().getName(),
                                e);
               }
           }
       } else {
           // show a summary only for the selected transactions
           int[] selectedRows = getTransactionTable().getSelectedRows();

           for (int selectedRow : selectedRows) {
               GnucashTransactionSplit             transactionSplit = model.getTransactionSplit(selectedRow);
               try {
                   Collection<? extends GnucashTransactionSplit> splits = transactionSplit.getTransaction().getSplits();
                   for (GnucashTransactionSplit split : splits) {
                       if (!accounts.contains(split.getAccount())) {
                           accounts.add(split.getAccount());
                       }
                   }
               } catch (JAXBException e) {
                   LOGGER.error("[JAXBException] Problem in "
                              + getClass().getName(),
                                e);
               }
           }
       }


       DefaultComboBoxModel aModel = new DefaultComboBoxModel();
       for (GnucashAccount account : accounts) {
           aModel.addElement(account);
       }

       JComboBox list = getSelectionSummaryAccountComboBox();
       list.setModel(aModel);
       list.setSelectedIndex(-1);
   }


   /**
    * If the user has selected an account to display a summary for,
    * we add all splits of the given split's transaction that have
    * that account. If nothing is selected, we add the given split.
    * @param retval the list of splits to add to
    * @param split the split who's transaction to look at
    * @throws JAXBException if we have issues accessing the XML-Backend.
    */
   private void replaceSplitsWithSelectedAccountsSplits(
           final Set<GnucashTransactionSplit> retval,
           final GnucashTransactionSplit split) throws JAXBException {

       JComboBox combo = getSelectionSummaryAccountComboBox();
       GnucashAccount selectedAccount =
           (GnucashAccount) combo.getSelectedItem();
       if (selectedAccount == null) {
           retval.add(split);
       } else {
           GnucashTransaction transaction = split.getTransaction();
           for (GnucashTransactionSplit split2 : transaction.getSplits()) {
               if (split2 == null) {
                   continue;
               }
               GnucashAccount account = split2.getAccount();
               if (account != null && account.equals(selectedAccount)) {
                   retval.add(split2);
               }
           }
       }

   }

   /**
    * If the user has selected an account to display a summary for,
    * we return All splits of the selected transactions that are
    * for that account. Else we return all selected Splits.
    * If no Splits are selected, we use all splits as the selected
    * ones.
    * @return the splits of the selected/all transactions featuring this/the
    *         selected account.
    * @throws JAXBException if we have issues accessing the XML-Backend.
    */
   private Collection<GnucashTransactionSplit> getSplitsForSummary() throws JAXBException {
       int[] selectedRows = getTransactionTable().getSelectedRows();
       Set<GnucashTransactionSplit> retval
       = new HashSet<GnucashTransactionSplit>();

       if (selectedRows == null || selectedRows.length == 0) {
           int count = model.getRowCount();
           for (int i = 0; i < count; i++) {
               GnucashTransactionSplit transactionSplit
               = model.getTransactionSplit(i);
               replaceSplitsWithSelectedAccountsSplits(retval,
                       transactionSplit);
           }
       } else {
           for (int selectedRow : selectedRows) {
               GnucashTransactionSplit transactionSplit
               = model.getTransactionSplit(selectedRow);
               replaceSplitsWithSelectedAccountsSplits(retval,
                       transactionSplit);
           }
       }

       return retval;
   }

   /**
    * Update the text in the selectionSummaryLabel
    * to show summary-information about the currently
    * selected transactions.
    */
   private void updateSelectionSummary() {

       int selectedCount = getTransactionTable().getSelectedRowCount();
       FixedPointNumber valueSumPlus = new FixedPointNumber(0);
       FixedPointNumber valueSumMinus = new FixedPointNumber(0);
       FixedPointNumber valueSumBalance = new FixedPointNumber(0);
       NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

       Collection<GnucashTransactionSplit> splits;
       try {
           splits = getSplitsForSummary();
       } catch (JAXBException e) {
           LOGGER.error("[JAXBException] Problem in "
                      + getClass().getName(),
                        e);
           getSelectionSummaryLabel().setText("ERROR [JAXBException]: " + e.getMessage());
           return;
       }

       for (GnucashTransactionSplit transactionSplit : splits) {
           FixedPointNumber value = transactionSplit.getValue();
           valueSumBalance.add(value);
           if (value.isPositive()) {
               valueSumPlus.add(value);
           } else {
               valueSumMinus.add(value);
           }
       }


       if (selectedCount < 1) {
           // show a summary for all transactions displayed
           int count = model.getRowCount();
           getSelectionSummaryLabel().setText(count + " transactions "
                   + currencyFormat.format(valueSumPlus)
                   + currencyFormat.format(valueSumMinus)
                   + "=" + currencyFormat.format(valueSumBalance));
       } else {
           // show a summary only for the selected transactions
           getSelectionSummaryLabel().setText(selectedCount
                   + " transactions selected "
                   + currencyFormat.format(valueSumPlus)
                   + currencyFormat.format(valueSumMinus)
                   + "=" + currencyFormat.format(valueSumBalance));
       }

   }

   /**
    * @param aTransaction the transactions to show in detail
    */
   public void setTransaction(final GnucashTransaction aTransaction) {
       TableModel temp = getTransactionTable().getModel();
       if (temp != null && temp instanceof GnucashTransactionsSplitsTableModel) {
           GnucashTransactionsSplitsTableModel tblModel = (GnucashTransactionsSplitsTableModel) temp;
           int max = tblModel.getRowCount();
           for (int i = 0; i < max; i++) {
               if (tblModel.getTransactionSplit(i).getTransaction().getId() == aTransaction.getId()) {
                   getTransactionTable().getSelectionModel().setSelectionInterval(i, i);
                   return;
               }
           }
       }
       getSingleTransactionPanel().setTransaction(aTransaction);
       getSingleTransactionPanel().setVisible(true);
       getSelectionSummaryPanel().setVisible(false);
       getSummaryPanel().setPreferredSize(getSingleTransactionPanel().getPreferredSize());
   }

   /**
    * @param aSplitActions the actions we shall offer on splits.
    */
   public void setSplitActions(final Collection<TransactionSplitAction> aSplitActions) {
       LOGGER.info("TransactionsPanel is given " + (mySplitActions == null ? "no" : mySplitActions.size()) + " split-actions");
       mySplitActions = aSplitActions;
       getSingleTransactionPanel().setSplitActions(mySplitActions);
   }

   /**
    * @return the actions we shall offer on splits.
    */
   protected Collection<TransactionSplitAction> getSplitActions() {
       return mySplitActions;
   }
}
