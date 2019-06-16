package com.custmanager;

import com.cust.common.ApplicationException;
import com.cust.domain.vo.ElegantUserAccess;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.LoginManager;
import com.custmanager.controller.ElegantMenuController;
import com.custmanager.images.ImagesDir;
import com.custmanager.util.CustUtil;
import com.custmanager.view.CountryView;
import com.custmanager.view.CustomerView;
import com.custmanager.view.LoadStatus;
import com.inder.customcomponents.ITitlePanel;
import com.custmanager.view.SupplierView;
import com.custmanager.view.LoginView;
import com.custmanager.view.ProductView;
import com.custmanager.view.PurchaseRtnView;
import com.custmanager.view.PurchaseView;
import com.custmanager.view.SalesManView;
import com.custmanager.view.SalesRtnView;
import com.custmanager.view.SalesView;
import com.custmanager.view.UserAdminView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class ElegantInventory extends CustFrame {

    private static final long serialVersionUID = 1L;
    private ImageIcon icon = null;
    private WindowAdapter windowAdapter = null;
    private Timer timerDate = null;

    private ElegantInventory() {
        initComponents();
    }

    public static ElegantInventory getInstance() {
        return ElegantInventoryInstance.INSTANCE;
    }

    void loadData() throws Exception {

        try {
            super.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
            LoadStatus displayStatus = new LoadStatus();
            displayStatus.setMaxCount(11);
            displayStatus.setVisible(true);
            displayStatus.setMessage("Loading Please Wait ...");
            CountryView countryView = AppManager.getCountryView();
            displayStatus.updateCount();
            Thread.sleep(50);
            ProductView productView = AppManager.getProductView();
            displayStatus.updateCount();
            Thread.sleep(50);
            SalesManView salesManView = AppManager.getSalesManView();
            displayStatus.updateCount();
            Thread.sleep(50);
            SupplierView supplierView = AppManager.getSupplierView();
            displayStatus.updateCount();
            Thread.sleep(50);
            CustomerView custView = AppManager.getCustomerView();
            displayStatus.updateCount();
            Thread.sleep(50);
            PurchaseView purchaseView = AppManager.getPurchaseView();
            displayStatus.updateCount();
            Thread.sleep(50);
            SalesView salesView = AppManager.getSalesView();
            displayStatus.updateCount();
            Thread.sleep(50);
            PurchaseRtnView purchaseRtnView = AppManager.getPurchaseRtnView();
            displayStatus.updateCount();
            Thread.sleep(50);
            SalesRtnView salesRtnView = AppManager.getSalesRtnView();
            displayStatus.updateCount();
            Thread.sleep(50);
            UserAdminView userAdminView = AppManager.getUserAdminView();
            displayStatus.updateCount();
            Thread.sleep(50);
            displayStatus.updateCount();
            Thread.sleep(50);
            setUserAccess();
            for (ElegantUserAccess elegantUserAccess : PersistanceManager.getInstance().getUserAccessList()) {
                icon = ImagesDir.getImage("country.png");
                if (elegantUserAccess.getUserAccessId() == 1001 && elegantUserAccess.getMenuAllowed()) {
                    countryView.initButtons();
                    ITitlePanel countryPanel = new ITitlePanel("Country Management", icon, countryView, null);
                    countryPanel.setName("1");
                    this.elegantTabsPanel.add("Country", countryPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1002 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("product.png");
                    productView.initButtons();
                    ITitlePanel custProductPanel = new ITitlePanel("Product Management", icon, productView, null);
                    custProductPanel.setName("2");
                    this.elegantTabsPanel.add("Product", custProductPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1003 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("salesman.png");
                    salesManView.initButtons();
                    ITitlePanel salesManPanel = new ITitlePanel("SalesPerson Management", icon, salesManView, null);
                    salesManPanel.setName("3");
                    this.elegantTabsPanel.add("SalesPerson", salesManPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1004 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("supplier.png");
                    supplierView.initButtons();
                    ITitlePanel custSupPanel = new ITitlePanel("Supplier Management", icon, supplierView, null);
                    custSupPanel.setName("4");
                    this.elegantTabsPanel.add("Supplier", custSupPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1005 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("customer.png");
                    custView.initButtons();
                    ITitlePanel custPanel = new ITitlePanel("Customer Management", icon, custView, null);
                    custPanel.setName("5");
                    this.elegantTabsPanel.add("Customer", custPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1007 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("purchase.png");
                    purchaseView.initButtons();
                    ITitlePanel custPurchasePanel = new ITitlePanel("Order Management", icon, purchaseView, null);
                    custPurchasePanel.setName("7");
                    this.elegantTabsPanel.add("Purchase Orders", custPurchasePanel);
                } else if (elegantUserAccess.getUserAccessId() == 1009 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("sales.png");
                    salesView.initButtons();
                    ITitlePanel salesPanel = new ITitlePanel("Invoice Management", icon, salesView, null);
                    salesPanel.setName("9");
                    this.elegantTabsPanel.add("Sales Invoice", salesPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1010 && elegantUserAccess.getMenuAllowed()) {
                    purchaseRtnView.initButtons();
                    icon = ImagesDir.getImage("purchase.png");
                    ITitlePanel purchaseRtnPanel = new ITitlePanel("Order RTN Management", icon, purchaseRtnView, null);
                    purchaseRtnPanel.setName("10");
                    this.elegantTabsPanel.add("Purchase RTN", purchaseRtnPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1011 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("sales.png");
                    salesRtnView.initButtons();
                    ITitlePanel saleRtnPanel = new ITitlePanel("Invoice RTN Management", icon, salesRtnView, null);
                    saleRtnPanel.setName("11");
                    this.elegantTabsPanel.add("Sale RTN", saleRtnPanel);
                } else if (elegantUserAccess.getUserAccessId() == 1012 && elegantUserAccess.getMenuAllowed()) {
                    icon = ImagesDir.getImage("admin.png");
                    userAdminView.initButtons();
                    ITitlePanel userAdminPanel = new ITitlePanel("User Management", icon, userAdminView, null);
                    userAdminPanel.setName("12");
                    this.elegantTabsPanel.add("User", userAdminPanel);

                }
            }
            for (int x=0;x< this.elegantTabsPanel.getTabCount(); x++) {
                this.elegantTabsPanel.setForegroundAt(x, Color.orange);
            }
            displayStatus.dispose();
// poll data from server depending on tab selected
            this.elegantTabsPanel.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                }
            });
        } catch (Exception e) {
            CustUtil.showErrorDialogue(e.getMessage());
        }
    }

    void init() {

        addMenuListeners();
        try {
            this.addWindowListener(getWindowAdapter());
// added as JTabbedPane does not have methods to set INSETS            
            UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
            UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);
            loadData();
            this.createTimerToUpdateDate();

        } catch (Exception e) {
            CustUtil.showErrorDialogue(e.getMessage());
            System.exit(0);
        }
        if (AppManager.getInstance().getElegantInventory().elegantTabsPanel.getTabCount() > 0 && AppManager.getInstance().getElegantInventory().elegantTabsPanel.isEnabledAt(0)) {
            AppManager.getInstance().getElegantInventory().elegantTabsPanel.setSelectedIndex(0);
        } else {
            AppManager.getInstance().getElegantInventory().custStatus.requestFocus();
        }

    }

    void addMenuListeners() {
        ElegantMenuController elegantMenuController = new ElegantMenuController();
        this.menuCountryMas.addActionListener(elegantMenuController);
        this.menuCustMas.addActionListener(elegantMenuController);
        this.menuExitMas.addActionListener(elegantMenuController);
        this.menuProductMas.addActionListener(elegantMenuController);
        this.menuSalesManMas.addActionListener(elegantMenuController);
        this.menuSupMas.addActionListener(elegantMenuController);

        this.menuPurchaseOrder.addActionListener(elegantMenuController);
        this.menuPurchaseRTN.addActionListener(elegantMenuController);
        this.menuSalesInvoice.addActionListener(elegantMenuController);
        this.menuSalesRTN.addActionListener(elegantMenuController);

        this.menuCountryRep.addActionListener(elegantMenuController);
        this.menuPurRep.addActionListener(elegantMenuController);
        this.menuPurRtnRep.addActionListener(elegantMenuController);
        this.menuSalesManRep.addActionListener(elegantMenuController);
        this.menuProdRep.addActionListener(elegantMenuController);
        this.menuSupRep.addActionListener(elegantMenuController);
        this.menuCustomersRep.addActionListener(elegantMenuController);
        this.menuSalesRep.addActionListener(elegantMenuController);
        this.menuSalesRtnRep.addActionListener(elegantMenuController);

        this.orderVsSalesRep.addActionListener(elegantMenuController);
        this.pendingOrderRep.addActionListener(elegantMenuController);
        this.pendingSaleRep.addActionListener(elegantMenuController);
        this.menuUserListing.addActionListener(elegantMenuController);

        this.menuAdmin.addActionListener(elegantMenuController);
        this.menuDoc.addActionListener(elegantMenuController);
        this.menuChat.addActionListener(elegantMenuController);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        elegantTabsPanel = new javax.swing.JTabbedPane();
        custStatus = new org.jdesktop.swingx.JXStatusBar();
        dateLabel = new javax.swing.JLabel();
        custMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCountryMas = new javax.swing.JMenuItem();
        menuProductMas = new javax.swing.JMenuItem();
        menuSalesManMas = new javax.swing.JMenuItem();
        menuSupMas = new javax.swing.JMenuItem();
        menuCustMas = new javax.swing.JMenuItem();
        menuExitMas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuPurchaseOrder = new javax.swing.JMenuItem();
        menuPurchaseRTN = new javax.swing.JMenuItem();
        menuSalesInvoice = new javax.swing.JMenuItem();
        menuSalesRTN = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuCountryRep = new javax.swing.JMenuItem();
        menuSalesManRep = new javax.swing.JMenuItem();
        menuProdRep = new javax.swing.JMenuItem();
        menuSupRep = new javax.swing.JMenuItem();
        menuCustomersRep = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuPurRep = new javax.swing.JMenuItem();
        menuPurRtnRep = new javax.swing.JMenuItem();
        menuSalesRep = new javax.swing.JMenuItem();
        menuSalesRtnRep = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        orderVsSalesRep = new javax.swing.JMenuItem();
        pendingOrderRep = new javax.swing.JMenuItem();
        pendingSaleRep = new javax.swing.JMenuItem();
        menuUserListing = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuAdmin = new javax.swing.JMenuItem();
        menuDoc = new javax.swing.JMenuItem();
        menuChat = new javax.swing.JMenuItem();

        jLabel3.setText("jLabel3");

        jTextField2.setText("jTextField2");

        jLabel7.setText("jLabel7");

        jMenuItem16.setText("jMenuItem16");

        jMenuItem19.setText("jMenuItem19");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Elegant Inventory ");

        elegantTabsPanel.setMaximumSize(new java.awt.Dimension(800, 700));
        elegantTabsPanel.setName(""); // NOI18N
        elegantTabsPanel.setRequestFocusEnabled(false);
        getContentPane().add(elegantTabsPanel, java.awt.BorderLayout.CENTER);
        elegantTabsPanel.getAccessibleContext().setAccessibleName("jTab1");

        custStatus.setLayout(new java.awt.BorderLayout());

        dateLabel.setBackground(new java.awt.Color(251, 243, 243));
        dateLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText(".");
        custStatus.add(dateLabel, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(custStatus, java.awt.BorderLayout.PAGE_START);

        custMenuBar.setBackground(new java.awt.Color(251, 243, 243));

        jMenu1.setMnemonic('m');
        jMenu1.setText("Master ");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenu1.setName("1"); // NOI18N

        menuCountryMas.setBackground(new java.awt.Color(195, 223, 168));
        menuCountryMas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuCountryMas.setIcon(ImagesDir.getImage("country16.png"));
        menuCountryMas.setMnemonic('c');
        menuCountryMas.setText("Country");
        menuCountryMas.setName("1"); // NOI18N
        jMenu1.add(menuCountryMas);

        menuProductMas.setBackground(new java.awt.Color(195, 223, 168));
        menuProductMas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuProductMas.setIcon(ImagesDir.getImage("product16.png"));
        menuProductMas.setMnemonic('p');
        menuProductMas.setText("Product");
        menuProductMas.setName("2"); // NOI18N
        jMenu1.add(menuProductMas);

        menuSalesManMas.setBackground(new java.awt.Color(195, 223, 168));
        menuSalesManMas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSalesManMas.setIcon(ImagesDir.getImage("salesman16.png"));
        menuSalesManMas.setMnemonic('s');
        menuSalesManMas.setText("SalesMan");
        menuSalesManMas.setName("3"); // NOI18N
        jMenu1.add(menuSalesManMas);

        menuSupMas.setBackground(new java.awt.Color(195, 223, 168));
        menuSupMas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSupMas.setIcon(ImagesDir.getImage("supplier16.png"));
        menuSupMas.setMnemonic('u');
        menuSupMas.setText("Supplier");
        menuSupMas.setName("4"); // NOI18N
        jMenu1.add(menuSupMas);

        menuCustMas.setBackground(new java.awt.Color(195, 223, 168));
        menuCustMas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuCustMas.setIcon(ImagesDir.getImage("customer16.png"));
        menuCustMas.setMnemonic('c');
        menuCustMas.setText("Customer");
        menuCustMas.setName("5"); // NOI18N
        jMenu1.add(menuCustMas);

        menuExitMas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuExitMas.setBackground(new java.awt.Color(195, 223, 168));
        menuExitMas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuExitMas.setIcon(ImagesDir.getImage("exit16.png"));
        menuExitMas.setMnemonic('x');
        menuExitMas.setText("Exit");
        menuExitMas.setName("6"); // NOI18N
        jMenu1.add(menuExitMas);

        custMenuBar.add(jMenu1);

        jMenu2.setMnemonic('y');
        jMenu2.setText("Inventory");
        jMenu2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenu2.setName("2"); // NOI18N

        menuPurchaseOrder.setBackground(new java.awt.Color(195, 223, 168));
        menuPurchaseOrder.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuPurchaseOrder.setIcon(ImagesDir.getImage("purchase16.png"));
        menuPurchaseOrder.setMnemonic('p');
        menuPurchaseOrder.setText("Order");
        menuPurchaseOrder.setActionCommand("PurchaseOrder");
        menuPurchaseOrder.setName("8"); // NOI18N
        jMenu2.add(menuPurchaseOrder);

        menuPurchaseRTN.setBackground(new java.awt.Color(195, 223, 168));
        menuPurchaseRTN.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuPurchaseRTN.setIcon(ImagesDir.getImage("goodsrtn16.png"));
        menuPurchaseRTN.setMnemonic('r');
        menuPurchaseRTN.setText("Invoice");
        menuPurchaseRTN.setActionCommand("PurchaseRTN");
        menuPurchaseRTN.setName("9"); // NOI18N
        jMenu2.add(menuPurchaseRTN);

        menuSalesInvoice.setBackground(new java.awt.Color(195, 223, 168));
        menuSalesInvoice.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSalesInvoice.setIcon(ImagesDir.getImage("sales16.png"));
        menuSalesInvoice.setMnemonic('s');
        menuSalesInvoice.setText("Order Rtn");
        menuSalesInvoice.setActionCommand("SalesInvoice");
        menuSalesInvoice.setName("10"); // NOI18N
        jMenu2.add(menuSalesInvoice);

        menuSalesRTN.setBackground(new java.awt.Color(195, 223, 168));
        menuSalesRTN.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSalesRTN.setIcon(ImagesDir.getImage("goodsrtn16.png"));
        menuSalesRTN.setMnemonic('e');
        menuSalesRTN.setText("Invoice RTN");
        menuSalesRTN.setActionCommand("SalesRTN");
        menuSalesRTN.setName("11"); // NOI18N
        jMenu2.add(menuSalesRTN);

        custMenuBar.add(jMenu2);

        jMenu4.setMnemonic('E');
        jMenu4.setText("Reports");
        jMenu4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenu4.setName("4"); // NOI18N

        menuCountryRep.setBackground(new java.awt.Color(195, 223, 168));
        menuCountryRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuCountryRep.setIcon(ImagesDir.getImage("countrylist16.png"));
        menuCountryRep.setMnemonic('c');
        menuCountryRep.setText("Countries");
        menuCountryRep.setName("15"); // NOI18N
        jMenu4.add(menuCountryRep);

        menuSalesManRep.setBackground(new java.awt.Color(195, 223, 168));
        menuSalesManRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSalesManRep.setIcon(ImagesDir.getImage("salesmanlist16.png"));
        menuSalesManRep.setMnemonic('s');
        menuSalesManRep.setText("SalesMan");
        menuSalesManRep.setActionCommand("SalesManRep");
        menuSalesManRep.setName("16"); // NOI18N
        jMenu4.add(menuSalesManRep);

        menuProdRep.setBackground(new java.awt.Color(195, 223, 168));
        menuProdRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuProdRep.setIcon(ImagesDir.getImage("productlist16.png"));
        menuProdRep.setMnemonic('p');
        menuProdRep.setText("Products");
        menuProdRep.setActionCommand("ProductsRep");
        menuProdRep.setName("17"); // NOI18N
        jMenu4.add(menuProdRep);

        menuSupRep.setBackground(new java.awt.Color(195, 223, 168));
        menuSupRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSupRep.setIcon(ImagesDir.getImage("supplierlist16.png"));
        menuSupRep.setMnemonic('u');
        menuSupRep.setText("Suppliers");
        menuSupRep.setActionCommand("SuppliersRep");
        menuSupRep.setName("18"); // NOI18N
        jMenu4.add(menuSupRep);

        menuCustomersRep.setBackground(new java.awt.Color(195, 223, 168));
        menuCustomersRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuCustomersRep.setIcon(ImagesDir.getImage("customerlist16.png"));
        menuCustomersRep.setMnemonic('s');
        menuCustomersRep.setText("Customers");
        menuCustomersRep.setActionCommand("CustomersRep");
        menuCustomersRep.setName("19"); // NOI18N
        jMenu4.add(menuCustomersRep);

        jSeparator2.setName("20"); // NOI18N
        jMenu4.add(jSeparator2);

        menuPurRep.setBackground(new java.awt.Color(195, 223, 168));
        menuPurRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuPurRep.setIcon(ImagesDir.getImage("purchasereport16.png"));
        menuPurRep.setMnemonic('p');
        menuPurRep.setText("Purchases");
        menuPurRep.setToolTipText("a");
        menuPurRep.setActionCommand("PurchaseRep");
        menuPurRep.setName("22"); // NOI18N
        jMenu4.add(menuPurRep);

        menuPurRtnRep.setBackground(new java.awt.Color(195, 223, 168));
        menuPurRtnRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuPurRtnRep.setMnemonic('e');
        menuPurRtnRep.setText("Purchase RTNs");
        menuPurRtnRep.setActionCommand("PurchaseRTNRep");
        menuPurRtnRep.setName("23"); // NOI18N
        jMenu4.add(menuPurRtnRep);

        menuSalesRep.setBackground(new java.awt.Color(195, 223, 168));
        menuSalesRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSalesRep.setMnemonic('l');
        menuSalesRep.setText("Sales");
        menuSalesRep.setActionCommand("SalesRep");
        menuSalesRep.setName("24"); // NOI18N
        jMenu4.add(menuSalesRep);

        menuSalesRtnRep.setBackground(new java.awt.Color(195, 223, 168));
        menuSalesRtnRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuSalesRtnRep.setMnemonic('n');
        menuSalesRtnRep.setText("Sales RTNs");
        menuSalesRtnRep.setActionCommand("SalesRTNRep");
        menuSalesRtnRep.setName("25"); // NOI18N
        jMenu4.add(menuSalesRtnRep);

        jSeparator1.setName("26"); // NOI18N
        jMenu4.add(jSeparator1);
        jSeparator1.getAccessibleContext().setAccessibleDescription("");

        custMenuBar.add(jMenu4);

        jMenu5.setMnemonic('i');
        jMenu5.setText("M.I.S");
        jMenu5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jMenu5.setName("5"); // NOI18N

        orderVsSalesRep.setBackground(new java.awt.Color(195, 223, 168));
        orderVsSalesRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        orderVsSalesRep.setMnemonic('o');
        orderVsSalesRep.setText("Orders Vs Sales");
        orderVsSalesRep.setActionCommand("OrderVsSale");
        orderVsSalesRep.setName("30"); // NOI18N
        jMenu5.add(orderVsSalesRep);

        pendingOrderRep.setBackground(new java.awt.Color(195, 223, 168));
        pendingOrderRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pendingOrderRep.setMnemonic('e');
        pendingOrderRep.setText("Pending Orders");
        pendingOrderRep.setActionCommand("PendingOrders");
        pendingOrderRep.setName("31"); // NOI18N
        jMenu5.add(pendingOrderRep);

        pendingSaleRep.setBackground(new java.awt.Color(195, 223, 168));
        pendingSaleRep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pendingSaleRep.setMnemonic('s');
        pendingSaleRep.setText("Pending Sales");
        pendingSaleRep.setActionCommand("PendingSales");
        pendingSaleRep.setName("32"); // NOI18N
        jMenu5.add(pendingSaleRep);

        menuUserListing.setBackground(new java.awt.Color(195, 223, 168));
        menuUserListing.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuUserListing.setText("User Listing");
        menuUserListing.setActionCommand("userlisting");
        menuUserListing.setName("33"); // NOI18N
        jMenu5.add(menuUserListing);

        custMenuBar.add(jMenu5);

        jMenu6.setMnemonic('o');
        jMenu6.setText("Tools");
        jMenu6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        menuAdmin.setBackground(new java.awt.Color(195, 223, 168));
        menuAdmin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuAdmin.setMnemonic('u');
        menuAdmin.setText("User Administration");
        menuAdmin.setActionCommand("UserAdmin");
        menuAdmin.setName("37"); // NOI18N
        jMenu6.add(menuAdmin);

        menuDoc.setBackground(new java.awt.Color(195, 223, 168));
        menuDoc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuDoc.setMnemonic('d');
        menuDoc.setText("Online Documentation");
        menuDoc.setActionCommand("docs");
        menuDoc.setName("39"); // NOI18N
        jMenu6.add(menuDoc);

        menuChat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuChat.setBackground(new java.awt.Color(195, 223, 168));
        menuChat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuChat.setMnemonic('g');
        menuChat.setText("Global Chat");
        menuChat.setActionCommand("chat");
        menuChat.setName("40"); // NOI18N
        jMenu6.add(menuChat);

        custMenuBar.add(jMenu6);

        setJMenuBar(custMenuBar);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws Exception {
        UIManager.setLookAndFeel(new MetalLookAndFeel());

        ElegantInventory elegantInstance = ElegantInventory.getInstance();
        AppManager.getInstance().setElegantInventory(elegantInstance);
        while (true) {
            LoginView loginForm = AppManager.getLoginView();
            loginForm.setModal(true);
            loginForm.setVisible(true);
            if (AppManager.getInstance().isLoggedIn()) {
                break;
            }
        }
        SwingUtilities.updateComponentTreeUI(elegantInstance);
        displayMainPanel(elegantInstance);

    }

    static void displayMainPanel(ElegantInventory elegantInstance) {
        elegantInstance.init();

//        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
//        elegantInstance.setPreferredSize(new Dimension((int) screenDim.getWidth() - 400, (int) screenDim.getHeight() - 400));
        elegantInstance.setLocation(10, 0);
        if (AppManager.getInstance().getElegantInventory().elegantTabsPanel.getTabCount() == 0) {
            JLabel noDataLabel = new JLabel("No Tab Data Found");
            noDataLabel.setSize(400, 400);
            elegantInstance.elegantTabsPanel.add(noDataLabel);
            elegantInstance.setLocationRelativeTo(null);
        }
        elegantInstance.pack();
        String title = CustUtil.APPNAME + " - " + elegantInstance.getTitle();
        elegantInstance.setTitle(title);
        elegantInstance.setVisible(true);
    }

    private void setUserAccess() {
//        createTimerToUpdateMenus();
        ArrayList<ElegantUserAccess> userAccessList = PersistanceManager.getInstance().getUserAccessList();
        if (!userAccessList.equals(null) && !userAccessList.isEmpty()) {
            for (ElegantUserAccess elegantUserAccess : userAccessList) {
                doApplyMenuItemAccess(elegantUserAccess);
            }
        }
    }

    private void doApplyMenuItemAccess(ElegantUserAccess elegantUserAccess) {
        if (elegantUserAccess == null) {
            return;
        }
        int tabs = this.elegantTabsPanel.getTabCount();
        Component[] compFieldsListMenu = this.custMenuBar.getComponents();
        int menuId;
        boolean accessAllowed;
        for (int intCount = 0; intCount < compFieldsListMenu.length; intCount++) {
            if (compFieldsListMenu[intCount] instanceof JMenu) {
                JMenu jMenu = (JMenu) compFieldsListMenu[intCount];
                Component[] compFieldsListMenuItems = jMenu.getMenuComponents();
                for (int intCount1 = 0; intCount1 < compFieldsListMenuItems.length; intCount1++) {

                    if (compFieldsListMenuItems[intCount1] instanceof JMenuItem) {
                        JMenuItem jMenuItem = (JMenuItem) compFieldsListMenuItems[intCount1];
                        menuId = Integer.parseInt(jMenuItem.getName());
                        accessAllowed = (elegantUserAccess.getMenuAllowed());
                        if (menuId == elegantUserAccess.getMenuId()) {

                            jMenuItem.setEnabled(accessAllowed);
//                            if (menuId < tabs) {
//                                this.elegantTabsPanel.setEnabledAt(menuId, accessAllowed);
//                            }
                            break;

                        }
                    }
                }
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar custMenuBar;
    private org.jdesktop.swingx.JXStatusBar custStatus;
    private javax.swing.JLabel dateLabel;
    public javax.swing.JTabbedPane elegantTabsPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JMenuItem menuAdmin;
    public javax.swing.JMenuItem menuChat;
    public javax.swing.JMenuItem menuCountryMas;
    public javax.swing.JMenuItem menuCountryRep;
    public javax.swing.JMenuItem menuCustMas;
    public javax.swing.JMenuItem menuCustomersRep;
    public javax.swing.JMenuItem menuDoc;
    public javax.swing.JMenuItem menuExitMas;
    public javax.swing.JMenuItem menuProdRep;
    public javax.swing.JMenuItem menuProductMas;
    public javax.swing.JMenuItem menuPurRep;
    public javax.swing.JMenuItem menuPurRtnRep;
    public javax.swing.JMenuItem menuPurchaseOrder;
    public javax.swing.JMenuItem menuPurchaseRTN;
    public javax.swing.JMenuItem menuSalesInvoice;
    public javax.swing.JMenuItem menuSalesManMas;
    public javax.swing.JMenuItem menuSalesManRep;
    public javax.swing.JMenuItem menuSalesRTN;
    public javax.swing.JMenuItem menuSalesRep;
    public javax.swing.JMenuItem menuSalesRtnRep;
    public javax.swing.JMenuItem menuSupMas;
    public javax.swing.JMenuItem menuSupRep;
    public javax.swing.JMenuItem menuUserListing;
    public javax.swing.JMenuItem orderVsSalesRep;
    public javax.swing.JMenuItem pendingOrderRep;
    public javax.swing.JMenuItem pendingSaleRep;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the windowAdapter
     */
    protected WindowAdapter getWindowAdapter() throws ApplicationException {
        if (windowAdapter == null) {
            windowAdapter = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    int optionSelected = confirmExit();
                    if (optionSelected == JOptionPane.YES_OPTION) {
                        try {
                            LoginManager loginManager = (LoginManager) AppManager.getInstance().getManagerList().get("loginManager");
                            if (loginManager.doLogOffUser()) {
                                if (ElegantInventory.this.timerDate != null) {
                                    timerDate.cancel();
                                }
                                CustUtil.showMessageDialogue("Loged Off Successfully. \nclick OK button to Exit the Application");
                                AppManager appManager = AppManager.getInstance();
                                appManager.getViewManagerList().clear();
                                appManager = null;
                                System.exit(0);
                            } else {
                                CustUtil.showErrorDialogue("Loged Off UN-Successfully. \nPlease Re-try exit again...");
                            }
                        } catch (Exception e) {
                            CustUtil.showErrorDialogue("Error Processing the request.. Please retry");
                        }
                    }
                }
            };
        }
        return windowAdapter;
    }

    /**
     * @param windowAdapter the windowAdapter to set
     */
    public void setWindowAdapter(WindowAdapter windowAdapter) {
        this.windowAdapter = windowAdapter;
    }

    public static int confirmExit() {
        int optionSelected;
        String options[] = new String[]{"Yes Exit", "Cancel Exit"};
        optionSelected = JOptionPane.showOptionDialog(RootFrame.getRootFrame(), "Are You Sure to Log Off & Exit?", CustUtil.APPNAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                ImagesDir.getImage(CustUtil.APPIMAGE),
                options,
                options[1]);
        return optionSelected;
    }

    private class CustDateTimerTask extends TimerTask {

        @Override
        public void run() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int numOfDays = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
            SimpleDateFormat sdf = new SimpleDateFormat(CustUtil.STATUSDATEFORMAT);
            int balDays = (numOfDays - PersistanceManager.getInstance().getElegantUser().getGracePeriod()) ;
//            System.out.println(numOfDays + "-" + PersistanceManager.getInstance().getElegantUser().getGracePeriod()+ "-" + balDays);
            dateLabel.setText("<html>Welcome : " + PersistanceManager.getInstance().getElegantUser().getUserName() + " Today is : " + sdf.format(new Date()) + (balDays>7 ? " <font color='green'>MemberShip is Active</font>": " <font color='white'>MemberShip Expires in : " + balDays + " Days.</font>") + "</html>");
        }

    }

    private void createTimerToUpdateDate() {
        if (timerDate == null) {
            timerDate = new Timer();
            timerDate.scheduleAtFixedRate(new CustDateTimerTask(), 1000, 1000);

        }
    }

    private static class ElegantInventoryInstance {

        private static final ElegantInventory INSTANCE = new ElegantInventory();
    }
}
