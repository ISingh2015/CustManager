/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.PreferenceVO;
import com.cust.persistance.PersistanceManager;
import com.cust.persistance.managers.AuditManager;
import com.cust.persistance.managers.BuySellManager;
import com.cust.persistance.managers.CountryManager;
import com.cust.persistance.managers.CustomerManager;
import com.cust.persistance.managers.LoginManager;
import com.cust.persistance.managers.ProductManager;
import com.cust.persistance.managers.SalesManManager;
import com.cust.persistance.managers.SupplierManager;
import com.cust.persistance.managers.UserAdminManager;
import com.custmanager.controller.CountryButtonController;
import com.custmanager.controller.CountryKeyController;
import com.custmanager.controller.CountryMouseController;
import com.custmanager.controller.CustomerButtonController;
import com.custmanager.controller.CustomerKeyController;
import com.custmanager.controller.CustomerMouseController;
import com.custmanager.controller.ProductButtonController;
import com.custmanager.controller.ProductKeyController;
import com.custmanager.controller.ProductMouseController;
import com.custmanager.controller.SalesManButtonController;
import com.custmanager.controller.SalesManKeyController;
import com.custmanager.controller.PurchaseButtonController;
import com.custmanager.controller.PurchaseKeyController;
import com.custmanager.controller.SalesManHelpKeyController;
import com.custmanager.controller.SalesManMouseController;
import com.custmanager.controller.SupplierButtonController;
import com.custmanager.controller.GeneralHelpKeyController;
import com.custmanager.controller.LoginButtonController;
import com.custmanager.controller.PurchaseRtnButtonController;
import com.custmanager.controller.PurchaseRtnKeyController;
import com.custmanager.controller.SaleButtonController;
import com.custmanager.controller.SalesKeyController;
import com.custmanager.controller.SalesRtnButtonController;
import com.custmanager.controller.SalesRtnKeyController;
import com.custmanager.controller.SupplierKeyController;
import com.custmanager.controller.SupplierMouseController;
import com.custmanager.controller.UserAdminButtonController;
import com.custmanager.controller.UserAdminKeyController;
import com.custmanager.controller.UserAdminMouseController;
import com.custmanager.images.ImagesDir;
import com.custmanager.images.ImagesDir;

import com.custmanager.model.AuditVO;
import com.custmanager.model.CountryVO;
import com.custmanager.model.CustomerVO;
import com.custmanager.model.LoginVO;
import com.custmanager.model.ProductVO;
import com.custmanager.model.SalesManVO;
import com.custmanager.model.SupplierVO;
import com.custmanager.view.CountryView;
import com.custmanager.view.CustomerView;
import com.custmanager.view.SupplierView;
import com.custmanager.view.LoginView;
import com.custmanager.view.ProductView;
import com.custmanager.view.SalesManView;
import com.custmanager.model.BuySellVO;
import com.custmanager.model.UserAdminVO;
import com.custmanager.renders.CustomerTableCellRenderer;
import com.custmanager.util.CustUtil;
import com.custmanager.view.AuditView;
import com.custmanager.view.GeneralBillHelp;
import com.custmanager.view.PurchaseRtnView;
import com.custmanager.view.PurchaseView;
import com.custmanager.view.RegistrationView;
import com.custmanager.view.ResetPasswordView;
import com.custmanager.view.SalesRtnView;
import com.custmanager.view.SalesView;
import com.custmanager.view.UserAdminView;
import com.inder.customcomponents.HelpTextField;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Inderjit
 */
public class AppManager {

    //TODO change to XML 
    public static final String[] viewNames = {"custView", "productView", "loginView", "countryView", "suppView", "salesManView", "registrationView", "resetPasswordView", "userAdmin", "", "", "estimation", "purchaseView", "salesView", "crnoteView", "drnoteView", "vouchersView", "purchaseRtnView", "salesRtnView", "auditView"};
    public static final String[] viewManagers = {"custManager", "productManager", "loginManager", "countryManager", "suppManager", "salesManManager", "regManager", "resetPasswordManaager", "userAdminManager", "", "", "estimationManager", "purchaseManager", "saleManager", "crnoteManager", "drnoteManager", "voucherManager", "purchaseRtnManager", "saleRtnManager", "auditManager"};
    public static final String[] viewModels = {"custModel", "productModel", "loginModel", "countryModel", "suppModel", "salesManModel", "regModel", "resetPasswordModel", "userAdminModel", "", "", "estimationModel", "purchaseModel", "saleModel", "crnoteModel", "drnoteModel", "voucherModel", "purchaseRtnModel", "saleRtnModel", "auditModel"};
    public static final String[] viewHelps = {"managerHelp", "purchaseBillHelp", "saleBillHelp", "custCodeHelp", "supplierCodeHelp", "salesManCodeHelp", "productHelp", "purchaseRtnBillHelp", "saleBillRtnHelp"};
    public static final String[] controllerHelp = {"custController", "productController", "loginController", "countryController", "suppController", "salesManController", "", "", "userAdminController", "", "", "estimationController", "purchaseController", "saleController", "crnoteController", "drnoteController", "voucherController", "purchaseRtnController", "saleRtnController", "auditController"};
    private ConcurrentHashMap<String, Object> viewList = null;
    private ConcurrentHashMap<String, Object> viewManagerList = null;
    private ConcurrentHashMap<String, Object> viewModelList = null;
    private ConcurrentHashMap<String, Object> viewHelpList = null;
    private ConcurrentHashMap<String, Object> controllerList = null;

    private boolean loggedIn = false;
    private ElegantInventory elegantInventory;
    private static Color controlColor;

    private JasperViewer reportViewer;
    private PreferenceVO preferenceVO;

    private AppManager() {
        controlColor = UIManager.getColor("control");
    }

    public static AppManager getInstance() {
        return AppManagerHolder.INSTANCE;
    }

    public static ConcurrentHashMap getViewList() {
        return AppManager.getInstance().getViewsList();
    }

    public static ConcurrentHashMap getManagerList() {
        return AppManager.getInstance().getViewManagerList();
    }

    public static ConcurrentHashMap getModelList() {
        return AppManager.getInstance().getViewModelList();
    }

    public static ConcurrentHashMap getHelpList() {
        return AppManager.getInstance().getViewHelpList();
    }

    public static ConcurrentHashMap getControllerList() {
        return AppManager.getInstance().getViewControllerList();
    }

    public static LoginView getLoginView() {
        if (AppManager.getInstance().getViewList().get(viewNames[2]) == null) {
            LoginView loginView = new LoginView();
            AppManager.getViewList().put(viewNames[2], loginView);
            AppManager.getManagerList().put(viewManagers[2], new LoginManager());
            AppManager.getModelList().put(viewModels[2], new LoginVO());

            LoginButtonController loginButtonController = new LoginButtonController();
            loginButtonController.setLoginView(loginView);
            loginView.addButtonController(loginButtonController);
            AppManager.getControllerList().put(controllerHelp[2], loginButtonController);

        }
//        System.out.println("Login View Created ...");
        return (LoginView) AppManager.getInstance().getViewList().get(viewNames[2]);
    }

    public static RegistrationView getRegistrationView() {
        if (AppManager.getInstance().getViewList().get(viewNames[6]) == null) {
            RegistrationView registrationView = new RegistrationView();
            AppManager.getViewList().put(viewNames[6], registrationView);
            AppManager.getManagerList().put(viewManagers[6], new LoginManager());
            AppManager.getModelList().put(viewModels[6], new LoginVO());
        }
//        System.out.println("Registration View Created ...");
        return (RegistrationView) AppManager.getInstance().getViewList().get(viewNames[6]);
    }

    public static ResetPasswordView getResetPasswordView() {
        if (AppManager.getInstance().getViewList().get(viewNames[7]) == null) {
            ResetPasswordView resetPasswordView = new ResetPasswordView();
            AppManager.getViewList().put(viewNames[7], resetPasswordView);
            AppManager.getManagerList().put(viewManagers[7], new LoginManager());
            AppManager.getModelList().put(viewModels[7], new LoginVO());
        }
//        System.out.println("Reset Password View Created ...");
        return (ResetPasswordView) AppManager.getInstance().getViewList().get(viewNames[7]);
    }

    public static SalesManView getSalesManView() {
        if (AppManager.getInstance().getViewList().get(viewNames[5]) == null) {
            SalesManView salesManView = new SalesManView();
            GeneralBillHelp generalBillCodeHelp = new GeneralBillHelp();
            GeneralBillHelp managerHelp = new GeneralBillHelp();
            SalesManVO salesManModel = new SalesManVO();
            salesManModel.addObserver(salesManView);
            salesManModel.addObserver(generalBillCodeHelp);
            salesManModel.addObserver(managerHelp);

            AppManager.getViewList().put(viewNames[5], salesManView);
            AppManager.getManagerList().put(viewManagers[5], new SalesManManager());
            AppManager.getModelList().put(viewModels[5], new SalesManVO());
            AppManager.getHelpList().put(viewHelps[0], managerHelp);
            AppManager.getHelpList().put(viewHelps[5], generalBillCodeHelp);

            SalesManButtonController buttonController = new SalesManButtonController();
            buttonController.setSalesManModel(salesManModel);
            buttonController.setSalesManView(salesManView);
            buttonController.initAllFields();
            salesManView.addButtonController(buttonController);
            AppManager.getControllerList().put(controllerHelp[5], buttonController);

            SalesManMouseController mouseController = new SalesManMouseController();
            mouseController.setSalesManModel(salesManModel);
            mouseController.setSalesManView(salesManView);
            salesManView.addMouseController(mouseController);

            SalesManKeyController salesManKeyController = new SalesManKeyController();
            salesManKeyController.setSalesManModel(salesManModel);
            salesManKeyController.setSalesManView(salesManView);
            salesManView.addKeyController(salesManKeyController);

//            ManagerKeyController managerKeyController = new ManagerKeyController();
//            managerKeyController.setSalesManModel(salesManModel);
//            managerKeyController.setManagerHelp(managerHelp);
//            managerHelp.addKeyController(managerKeyController);
            SalesManHelpKeyController salesManHelpKeyController = new SalesManHelpKeyController();
            salesManHelpKeyController.setSalesManModel(salesManModel);
            salesManHelpKeyController.setGeneralBillelp(generalBillCodeHelp);
            generalBillCodeHelp.addKeyController(salesManHelpKeyController);

            GeneralHelpKeyController managerHelpKeyController = new GeneralHelpKeyController();
            managerHelpKeyController.setGeneralBillCodeHelp(managerHelp);
            managerHelp.addKeyController(managerHelpKeyController);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            salesManView.setSalesManTableCellRenderer(customerTableCellRenderer);

        }
//        System.out.println("Sales Man View Created ...");
        return (SalesManView) AppManager.getInstance().getViewList().get(viewNames[5]);
    }

    public static CustomerView getCustomerView() {
        if (AppManager.getInstance().getViewList().get(viewNames[0]) == null) {
            CustomerView customerView = new CustomerView();
            GeneralBillHelp customerCodeHelp = new GeneralBillHelp();
            CustomerVO customerModel = new CustomerVO();
            customerModel.addObserver(customerView);
            customerModel.addObserver(customerCodeHelp);

            AppManager.getViewList().put(viewNames[0], customerView);
            AppManager.getManagerList().put(viewManagers[0], new CustomerManager());
            AppManager.getModelList().put(viewModels[0], new CustomerVO());
            AppManager.getHelpList().put(viewHelps[3], customerCodeHelp);

            CustomerButtonController buttonController = new CustomerButtonController();
            buttonController.setCustomerModel(customerModel);
            buttonController.setCustomerView(customerView);
            buttonController.initAllFields(true);
            customerView.addButtonController(buttonController);
            AppManager.getControllerList().put(controllerHelp[0], buttonController);

            CustomerMouseController mouseController = new CustomerMouseController();
            mouseController.setCustomerModel(customerModel);
            mouseController.setCustomerView(customerView);
            customerView.addMouseController(mouseController);

            CustomerKeyController customerKeyController = new CustomerKeyController();
            customerKeyController.setCustomerModel(customerModel);
            customerKeyController.setCustomerView(customerView);

            customerView.addKeyController(customerKeyController);
            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            customerView.setCustomerTableRenderer(customerTableCellRenderer);

            GeneralHelpKeyController generalHelpKeyController = new GeneralHelpKeyController();
            generalHelpKeyController.setGeneralBillCodeHelp(customerCodeHelp);
            customerCodeHelp.addKeyController(generalHelpKeyController);

        }
//        System.out.println("Customer View Created ...");
        return (CustomerView) AppManager.getInstance().getViewList().get(viewNames[0]);
    }

    public static ProductView getProductView() {
        if (AppManager.getInstance().getViewsList().get(viewNames[1]) == null) {
            ProductView productView = new ProductView();
            ProductVO productModel = new ProductVO();
            GeneralBillHelp productHelpView = new GeneralBillHelp();
            HelpTextField productIdHelpTextFieldPur = new HelpTextField();
            productIdHelpTextFieldPur.helpButton.setActionCommand("helpButtonPur");

            HelpTextField productIdHelpTextFieldSal = new HelpTextField();
            productIdHelpTextFieldSal.helpButton.setActionCommand("helpButtonSal");

            HelpTextField productIdHelpTextFieldPurRTN = new HelpTextField();
            productIdHelpTextFieldPurRTN.helpButton.setActionCommand("helpButtonPurRTN");

            HelpTextField productIdHelpTextFieldSalRTN = new HelpTextField();
            productIdHelpTextFieldSalRTN.helpButton.setActionCommand("helpButtonSalRTN");

            productModel.addObserver(productView);
            productModel.addObserver(productHelpView);

            AppManager.getViewList().put(viewNames[1], productView);
            AppManager.getManagerList().put(viewManagers[1], new ProductManager());
            AppManager.getModelList().put(viewModels[1], new ProductVO());

            ProductButtonController buttonController = new ProductButtonController();
            buttonController.setProductModel(productModel);
            buttonController.setProductView(productView);
            buttonController.initAllFields();
            productView.addButtonController(buttonController);

            buttonController.setHelpTextFieldPur(productIdHelpTextFieldPur);
            productIdHelpTextFieldPur.addButtonController(buttonController);
            buttonController.setHelpTextFieldSal(productIdHelpTextFieldSal);
            productIdHelpTextFieldSal.addButtonController(buttonController);
            buttonController.setHelpTextFieldPurRTN(productIdHelpTextFieldPurRTN);
            productIdHelpTextFieldPurRTN.addButtonController(buttonController);
            buttonController.setHelpTextFieldSalRTN(productIdHelpTextFieldSalRTN);
            productIdHelpTextFieldSalRTN.addButtonController(buttonController);

            AppManager.getControllerList().put(controllerHelp[1], buttonController);
            AppManager.getHelpList().put(viewHelps[6], productHelpView);

            ProductMouseController productMouseController = new ProductMouseController();
            productMouseController.setProductModel(productModel);
            productMouseController.setProductView(productView);
            productView.addMouseController(productMouseController);

            ProductKeyController productKeyController = new ProductKeyController();
            productKeyController.setProductModel(productModel);
            productKeyController.setProductView(productView);
            productView.addKeyController(productKeyController);

            GeneralHelpKeyController generalHelpKeyController = new GeneralHelpKeyController();
//            generalHelpKeyController.setProductModel(productModel);
            generalHelpKeyController.setGeneralBillCodeHelp(productHelpView);
            productHelpView.addKeyController(generalHelpKeyController);

            CustomerTableCellRenderer customerTableCellRenderer = new CustomerTableCellRenderer();
            productView.setCustomerTableCellRenderer(customerTableCellRenderer);

        }
//        System.out.println("Product View Created ...");
        return (ProductView) AppManager.getInstance().getViewsList().get(viewNames[1]);
    }

    public static SupplierView getSupplierView() {
        if (AppManager.getInstance().getViewsList().get(viewNames[4]) == null) {
            SupplierView supplierView = new SupplierView();
            GeneralBillHelp generalBillCodeHelp = new GeneralBillHelp();
            SupplierVO supplierModel = new SupplierVO();
            supplierModel.addObserver(supplierView);
            supplierModel.addObserver(generalBillCodeHelp);

            AppManager.getViewList().put(viewNames[4], supplierView);
            AppManager.getManagerList().put(viewManagers[4], new SupplierManager());
            AppManager.getModelList().put(viewModels[4], new SupplierVO());
            AppManager.getHelpList().put(viewHelps[4], generalBillCodeHelp);

            SupplierButtonController buttonController = new SupplierButtonController();
            buttonController.setSupplierModel(supplierModel);
            buttonController.setSupplierView(supplierView);
            buttonController.initAllFields(true);
            supplierView.addButtonController(buttonController);
            AppManager.getControllerList().put(controllerHelp[4], buttonController);

            SupplierMouseController supplierMouseController = new SupplierMouseController();
            supplierMouseController.setSupplierModel(supplierModel);
            supplierMouseController.setSupplierView(supplierView);
            supplierView.addMouseController(supplierMouseController);

            SupplierKeyController supplierKeyController = new SupplierKeyController();
            supplierKeyController.setSupplierModel(supplierModel);
            supplierKeyController.setSupplierView(supplierView);
            supplierView.addKeyController(supplierKeyController);

            GeneralHelpKeyController generalHelpKeyController = new GeneralHelpKeyController();
//            generalHelpKeyController.setSupplierModel(supplierModel);
            generalHelpKeyController.setGeneralBillCodeHelp(generalBillCodeHelp);

            generalBillCodeHelp.addKeyController(generalHelpKeyController);

            CustomerTableCellRenderer supplierTableCellRenderer = new CustomerTableCellRenderer();
            supplierView.setSupplierTableCellRenderer(supplierTableCellRenderer);

        }
//        System.out.println("Supplier View Created ...");
        return (SupplierView) AppManager.getInstance().getViewsList().get(viewNames[4]);
    }

    public static CountryView getCountryView() {
        if (AppManager.getInstance().getViewsList().get(viewNames[3]) == null) {
            CountryView countryView = new CountryView();
            CountryVO countryModel = new CountryVO();
            countryModel.addObserver(countryView);

            AppManager.getViewList().put(viewNames[3], countryView);
            AppManager.getManagerList().put(viewManagers[3], new CountryManager());
            AppManager.getModelList().put(viewModels[3], new CountryVO());

            CountryButtonController countryButtonController = new CountryButtonController();
            countryButtonController.setCountryView(countryView);
            countryButtonController.setCountryModel(countryModel);

            countryButtonController.initAllFields();
            countryView.addButtonController(countryButtonController);
            AppManager.getControllerList().put(controllerHelp[3], countryButtonController);

            CountryKeyController countryKeyController = new CountryKeyController();
            countryKeyController.setCountryModel(countryModel);
            countryKeyController.setCountryView(countryView);
            countryView.addKeyController(countryKeyController);

            CountryMouseController countryMouseController = new CountryMouseController();
            countryMouseController.setCountryModel(countryModel);
            countryMouseController.setCountryView(countryView);
            countryView.addMouseController(countryMouseController);

        }
//        System.out.println("Country View Created ...");
        return (CountryView) AppManager.getInstance().getViewsList().get(viewNames[3]);
    }

    public static UserAdminView getUserAdminView() {
        if (AppManager.getInstance().getViewsList().get(viewNames[8]) == null) {
            UserAdminView userAdminView = new UserAdminView(Color.GRAY);
            UserAdminVO userAdminModel = new UserAdminVO();
            userAdminModel.addObserver(userAdminView);

            AppManager.getViewList().put(viewNames[8], userAdminView);
            AppManager.getManagerList().put(viewManagers[8], new UserAdminManager());
            AppManager.getModelList().put(viewModels[8], userAdminModel);

            UserAdminButtonController userAdminButtonController = new UserAdminButtonController();
            userAdminButtonController.setUserAdminView(userAdminView);
            userAdminButtonController.setUserAdminModel(userAdminModel);
            AppManager.getControllerList().put(controllerHelp[8], userAdminButtonController);

            userAdminButtonController.initAllFields(true);
            userAdminView.addButtonController(userAdminButtonController);

            UserAdminKeyController userAdminKeyController = new UserAdminKeyController();
            userAdminKeyController.setUserAdminModel(userAdminModel);
            userAdminKeyController.setUserAdminView(userAdminView);
            userAdminView.addKeyController(userAdminKeyController);

            UserAdminMouseController userAdminMouseController = new UserAdminMouseController();
            userAdminMouseController.setUserAdminModel(userAdminModel);
            userAdminMouseController.setUserAdminView(userAdminView);
            userAdminView.addMouseController(userAdminMouseController);

        }
//        System.out.println("User Admin View Created ...");
        return (UserAdminView) AppManager.getInstance().getViewsList().get(viewNames[8]);
    }

    public static AuditView getAuditView() {
        if (AppManager.getInstance().getViewList().get(viewNames[19]) == null) {
            AuditView auditView = new AuditView(Color.GRAY);
            AppManager.getViewList().put(viewNames[19], auditView);
            AppManager.getManagerList().put(viewManagers[19], new AuditManager());
            AppManager.getModelList().put(viewModels[19], new AuditVO());
        }
//        System.out.println("Estimation View Created ...");
        return (AuditView) AppManager.getInstance().getViewList().get(viewNames[19]);
    }

    public static SalesView getSalesView() {
        if (AppManager.getInstance().getViewList().get(viewNames[13]) == null) {
            SalesView salesView = new SalesView(Color.GRAY);
            GeneralBillHelp saleBillCodeHelp = new GeneralBillHelp();

            BuySellVO saleModel = new BuySellVO();
            saleModel.addObserver(salesView);
            saleModel.addObserver(saleBillCodeHelp);

            AppManager.getViewList().put(viewNames[13], salesView);
            AppManager.getManagerList().put(viewManagers[13], new BuySellManager());
            AppManager.getModelList().put(viewModels[13], saleModel);
            AppManager.getHelpList().put(viewHelps[2], saleBillCodeHelp);

            SaleButtonController saleButtonController = new SaleButtonController();
            saleButtonController.setSaleModel(saleModel);
            saleButtonController.setSalesView(salesView);
            saleButtonController.initAllFields(true);

            salesView.addButtonController(saleButtonController);
            AppManager.getControllerList().put(controllerHelp[13], saleButtonController);

            SalesKeyController salesKeyController = new SalesKeyController();
            salesKeyController.setSalesModel(saleModel);
            salesKeyController.setSalesView(salesView);

            salesView.addKeyController(salesKeyController);

            GeneralHelpKeyController saleHelpKeyController = new GeneralHelpKeyController();
            saleHelpKeyController.setGeneralBillCodeHelp(saleBillCodeHelp);
            saleBillCodeHelp.addKeyController(saleHelpKeyController);

            ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get(controllerHelp[1]);
            HelpTextField productIdHelpTextField = bttnController.getHelpTextFieldSal();
            salesView.setHelpTextField(productIdHelpTextField);

        }
        return (SalesView) AppManager.getInstance().getViewList().get(viewNames[13]);
    }

    public static PurchaseView getPurchaseView() {
        if (AppManager.getInstance().getViewList().get(viewNames[12]) == null) {
            PurchaseView purchaseView = new PurchaseView(Color.GRAY);
            GeneralBillHelp generalBillCodeHelp = new GeneralBillHelp();

            BuySellVO purchaseModel = new BuySellVO();
            purchaseModel.addObserver(purchaseView);
            purchaseModel.addObserver(generalBillCodeHelp);

            AppManager.getViewList().put(viewNames[12], purchaseView);
            AppManager.getManagerList().put(viewManagers[12], new BuySellManager());
            AppManager.getModelList().put(viewModels[12], purchaseModel);
            AppManager.getHelpList().put(viewHelps[1], generalBillCodeHelp);

            PurchaseButtonController buttonController = new PurchaseButtonController();
            buttonController.setPurchaseModel(purchaseModel);
            buttonController.setPurchaseView(purchaseView);

            buttonController.initAllFields(true);
            purchaseView.addButtonController(buttonController);

            AppManager.getControllerList().put(controllerHelp[12], buttonController);

            PurchaseKeyController purchaseKeyController = new PurchaseKeyController();
            purchaseKeyController.setPurchaseModel(purchaseModel);
            purchaseKeyController.setPurchaseView(purchaseView);

            purchaseView.addKeyController(purchaseKeyController);

            GeneralHelpKeyController purchaseHelpKeyController = new GeneralHelpKeyController();
//            purchaseHelpKeyController.setPurchaseModel(purchaseModel);
            purchaseHelpKeyController.setGeneralBillCodeHelp(generalBillCodeHelp);
            generalBillCodeHelp.addKeyController(purchaseHelpKeyController);

            ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get(controllerHelp[1]);
            HelpTextField productIdHelpTextField = bttnController.getHelpTextFieldPur();
//            productIdHelpTextField.addButtonController(bttnController);
            purchaseView.setHelpTextField(productIdHelpTextField);

            generalBillCodeHelp.addKeyController(purchaseHelpKeyController);

        }
//        System.out.println("Purchase View Created ...");
        return (PurchaseView) AppManager.getInstance().getViewList().get(viewNames[12]);
    }

    public static PurchaseRtnView getPurchaseRtnView() {
        if (AppManager.getInstance().getViewList().get(viewNames[17]) == null) {
            PurchaseRtnView purchaseRtnView = new PurchaseRtnView(Color.GRAY);
            GeneralBillHelp generalRtnBillCodeHelp = new GeneralBillHelp();

            BuySellVO purchaseRtnModel = new BuySellVO();
            purchaseRtnModel.addObserver(purchaseRtnView);
            purchaseRtnModel.addObserver(generalRtnBillCodeHelp);

            AppManager.getViewList().put(viewNames[17], purchaseRtnView);
            AppManager.getManagerList().put(viewManagers[17], new BuySellManager());
            AppManager.getModelList().put(viewModels[17], purchaseRtnModel);
            AppManager.getHelpList().put(viewHelps[7], generalRtnBillCodeHelp);

            PurchaseRtnButtonController buttonController = new PurchaseRtnButtonController();
            buttonController.setPurchaseModel(purchaseRtnModel);
            buttonController.setPurchaseRtnView(purchaseRtnView);

            buttonController.initAllFields(true);
            purchaseRtnView.addButtonController(buttonController);

            AppManager.getControllerList().put(controllerHelp[17], buttonController);

            PurchaseRtnKeyController purchaseRtnKeyController = new PurchaseRtnKeyController();
            purchaseRtnKeyController.setPurchaseRtnModel(purchaseRtnModel);
            purchaseRtnKeyController.setPurchaseRtnView(purchaseRtnView);

            purchaseRtnView.addKeyController(purchaseRtnKeyController);

            GeneralHelpKeyController purchaseRtnHelpKeyController = new GeneralHelpKeyController();
//            purchaseRtnHelpKeyController.setPurchaseModel(purchaseRtnModel);
            purchaseRtnHelpKeyController.setGeneralBillCodeHelp(generalRtnBillCodeHelp);
            generalRtnBillCodeHelp.addKeyController(purchaseRtnHelpKeyController);

            ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get(controllerHelp[1]);
            HelpTextField productIdHelpTextField = bttnController.getHelpTextFieldPurRTN();
            purchaseRtnView.setHelpTextField(productIdHelpTextField);

            generalRtnBillCodeHelp.addKeyController(purchaseRtnHelpKeyController);

        }

        return (PurchaseRtnView) AppManager.getInstance().getViewList().get(viewNames[17]);
    }

    public static SalesRtnView getSalesRtnView() {
        if (AppManager.getInstance().getViewList().get(viewNames[18]) == null) {
            SalesRtnView salesRtnView = new SalesRtnView(Color.GRAY);
            GeneralBillHelp saleBillRtnCodeHelp = new GeneralBillHelp();

            BuySellVO saleRtnModel = new BuySellVO();
            saleRtnModel.addObserver(salesRtnView);
            saleRtnModel.addObserver(saleBillRtnCodeHelp);

            AppManager.getViewList().put(viewNames[18], salesRtnView);
            AppManager.getManagerList().put(viewManagers[18], new BuySellManager());
            AppManager.getModelList().put(viewModels[18], saleRtnModel);
            AppManager.getHelpList().put(viewHelps[8], saleBillRtnCodeHelp);

            SalesRtnButtonController saleRtnButtonController = new SalesRtnButtonController();
            saleRtnButtonController.setSaleModel(saleRtnModel);
            saleRtnButtonController.setSaleRtnView(salesRtnView);
            saleRtnButtonController.initAllFields(true);

            salesRtnView.addButtonController(saleRtnButtonController);
            AppManager.getControllerList().put(controllerHelp[18], saleRtnButtonController);

            SalesRtnKeyController salesRtnKeyController = new SalesRtnKeyController();
            salesRtnKeyController.setSalesRtnModel(saleRtnModel);
            salesRtnKeyController.setSalesRtnView(salesRtnView);

            salesRtnView.addKeyController(salesRtnKeyController);

            GeneralHelpKeyController saleRtnHelpKeyController = new GeneralHelpKeyController();
            saleRtnHelpKeyController.setGeneralBillCodeHelp(saleBillRtnCodeHelp);
            saleBillRtnCodeHelp.addKeyController(saleRtnHelpKeyController);

            ProductButtonController bttnController = (ProductButtonController) AppManager.getControllerList().get(controllerHelp[1]);
            HelpTextField productIdHelpTextField = bttnController.getHelpTextFieldSalRTN();
            salesRtnView.setHelpTextField(productIdHelpTextField);

        }
        return (SalesRtnView) AppManager.getInstance().getViewList().get(viewNames[18]);
    }

    /**
     * @return the viewManagerList
     */
    public ConcurrentHashMap<String, Object> getViewManagerList() {
        if (viewManagerList == null) {
            viewManagerList = new ConcurrentHashMap<String, Object>();
        }
        return viewManagerList;
    }

    /**
     * @return the viewModelList
     */
    public ConcurrentHashMap<String, Object> getViewModelList() {
        if (viewModelList == null) {
            viewModelList = new ConcurrentHashMap<String, Object>();
        }
        return viewModelList;
    }

    public ConcurrentHashMap<String, Object> getViewHelpList() {
        if (viewHelpList == null) {
            viewHelpList = new ConcurrentHashMap<String, Object>();
        }
        return viewHelpList;
    }

    public ConcurrentHashMap<String, Object> getViewControllerList() {
        if (controllerList == null) {
            controllerList = new ConcurrentHashMap<String, Object>();
        }
        return controllerList;
    }

    /**
     * @return the viewManagers
     */
    public String[] getViewManagers() {
        return viewManagers;
    }

    /**
     * @return the viewModels
     */
    public String[] getViewModels() {
        return viewModels;
    }

//    /**
//     * @return the userAccessList
//     */
//    public ArrayList<ElegantUserAccess> getUserAccessList() {
//        return userAccessList;
//    }
//
//    /**
//     * @param userAccessList the userAccessList to set
//     */
//    public void setUserAccessList(ArrayList<ElegantUserAccess> userAccessList) {
//        this.userAccessList = userAccessList;
//    }
    /**
     * @return the elegantInventory
     */
    public ElegantInventory getElegantInventory() {
        return elegantInventory;
    }

    /**
     * @param elegantInventory the elegantInventory to set
     */
    public void setElegantInventory(ElegantInventory elegantInventory) {
            this.elegantInventory = elegantInventory;
    }

//    /**
//     * @return the userWebService
//     */
//    public UserWebService getUserWebService() {
//        return userWebService;
//    }
//
//    /**
//     * @param userWebService the userWebService to set
//     */
//    public void setUserWebService(UserWebService userWebService) {
//        if (this.userWebService == null) {
//            this.userWebService = userWebService;
//        }
//        return;
//    }
//
//    /**
//     * @return the countryWebService
//     */
//    public CountryWebService getCountryWebService() {
//        return countryWebService;
//    }
//
//    /**
//     * @param countryWebService the countryWebService to set
//     */
//    public void setCountryWebService(CountryWebService countryWebService) {
//        if (this.countryWebService == null) {
//            this.countryWebService = countryWebService;
//        }
//    }
//
//    /**
//     * @return the customerWebService
//     */
//    public CustomerWebService getCustomerWebService() {
//        return customerWebService;
//    }
//
//    /**
//     * @param customerWebService the customerWebService to set
//     */
//    public void setCustomerWebService(CustomerWebService customerWebService) {
//        if (this.customerWebService == null) {
//            this.customerWebService = customerWebService;
//        }
//    }
//
//    /**
//     * @return the supplierWebService
//     */
//    public SupplierWebService getSupplierWebService() {
//        return supplierWebService;
//    }
//
//    /**
//     * @param supplierWebService the supplierWebService to set
//     */
//    public void setSupplierWebService(SupplierWebService supplierWebService) {
//        if (this.supplierWebService == null) {
//            this.supplierWebService = supplierWebService;
//        }
//    }
//
//    /**
//     * @return the buysellWebService
//     */
//    public BuySellWebService getBuysellWebService() {
//        return buysellWebService;
//    }
//
//    /**
//     * @param buysellWebService the buysellWebService to set
//     */
//    public void setBuysellWebService(BuySellWebService buysellWebService) {
//        if (this.buysellWebService == null) {
//            this.buysellWebService = buysellWebService;
//        }
//    }
//
//    /**
//     * @return the productWebService
//     */
//    public ProductWebService getProductWebService() {
//        return productWebService;
//    }
//
//    /**
//     * @param productWebService the productWebService to set
//     */
//    public void setProductWebService(ProductWebService productWebService) {
//        if (this.productWebService == null) {
//            this.productWebService = productWebService;
//        }
//    }
//
//    /**
//     * @return the salesManWebService
//     */
//    public SalesManWebService getSalesManWebService() {
//        return salesManWebService;
//    }
//
//    /**
//     * @param salesManWebService the salesManWebService to set
//     */
//    public void setSalesManWebService(SalesManWebService salesManWebService) {
//        if (this.salesManWebService == null) {
//            this.salesManWebService = salesManWebService;
//        }
//
//    }
    /**
     * @return the preferenceVO
     */
//    public PreferenceVO getPreferenceVO() {
//        return preferenceVO;
//    }
//
//    /**
//     * @param preferenceVO the preferenceVO to set
//     */
//    public void setPreferenceVO(PreferenceVO preferenceVO) {
//        this.preferenceVO = preferenceVO;
//    }
//    /**
//     * @return the preferenceWebService
//     */
//    public PreferenceWebService getPreferenceWebService() {
//        return preferenceWebService;
//    }
//
//    /**
//     * @param preferenceWebService the preferenceWebService to set
//     */
//    public void setPreferenceWebService(PreferenceWebService preferenceWebService) {
//        this.preferenceWebService = preferenceWebService;
//    }
    private static class AppManagerHolder {

        private static final AppManager INSTANCE = new AppManager();
    }

    /**
     * @return the viewList
     */
    public ConcurrentHashMap<String, Object> getViewsList() {
        if (viewList == null) {
            viewList = new ConcurrentHashMap<String, Object>();
        }
        return viewList;
    }

    public String[] getViewNames() {
        return viewNames;
    }

    /**
     * This method connects to the database server using a web Service
     *
     * @param serviceName
     * @return
     */
//    public boolean connectToService(String serviceName) {
//        boolean connected = false;
//        HessianProxyFactory factory = new HessianProxyFactory();
//        String webUrl = "http://" + AppManager.getInstance().getLoginServer() + ":" + CustServiceConstants.PORT + "/" + CustServiceConstants.APPURLNAME + "/";
//        try {
//            if (serviceName.equals(CustUtil.PERFSERVICE)) {
//                webUrl += serviceName;
//                PreferenceWebService webService = (PreferenceWebService) factory.create(PreferenceWebService.class, webUrl);
//                if (webService != null) {
//                    setPreferenceWebService(webService);
//                    connected = true;
//                }
//
//            } else if (serviceName.equals(AppManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.userservice")[0])) {
//                webUrl += serviceName;
//                UserWebService webService = (UserWebService) factory.create(UserWebService.class, webUrl);
//                if (webService != null) {
//                    setUserWebService(webService);
//                    connected = true;
//                }
//            } else if (serviceName.equals(AppManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.customerservice")[0])) {
//                webUrl += serviceName;
//                CustomerWebService webService = (CustomerWebService) factory.create(CustomerWebService.class, webUrl);
//                if (webService != null) {
//                    setCustomerWebService(webService);
//                    connected = true;
//                }
//            } else if (serviceName.equals(AppManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.countryservice")[0])) {
//                webUrl += serviceName;
//                CountryWebService webService = (CountryWebService) factory.create(CountryWebService.class, webUrl);
//                if (webService != null) {
//                    setCountryWebService(webService);
//                    connected = true;
//                }
//            } else if (serviceName.equals(AppManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.supplierservice")[0])) {
//                webUrl += serviceName;
//                SupplierWebService webService = (SupplierWebService) factory.create(SupplierWebService.class, webUrl);
//                if (webService != null) {
//                    setSupplierWebService(webService);
//                    connected = true;
//                }
//            } else if (serviceName.equals(AppManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.productservice")[0])) {
//                webUrl += serviceName;
//                ProductWebService webService = (ProductWebService) factory.create(ProductWebService.class, webUrl);
//                if (webService != null) {
//                    setProductWebService(webService);
//                    connected = true;
//                }
//            } else if (serviceName.equals(AppManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.salesmanservice")[0])) {
//                webUrl += serviceName;
//                SalesManWebService webService = (SalesManWebService) factory.create(SalesManWebService.class, webUrl);
//                if (webService != null) {
//                    setSalesManWebService(webService);
//                    connected = true;
//                }
//            } else if (serviceName.equals(AppManager.getInstance().getPreferenceVO().getPreference().get("com.elegant.inventory.buysellservice")[0])) {
//                webUrl += serviceName;
//                BuySellWebService webService = (BuySellWebService) factory.create(BuySellWebService.class, webUrl);
//                if (webService != null) {
//                    setBuysellWebService(webService);
//                    connected = true;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error while connecting, Please re-try in some time " + e.getMessage());
//        }
//        return connected;
//    }
//    /**
//     * @return the elegantUser
//     */
//    public ElegantUser getElegantUser() {
//        return elegantUser;
//    }
//
//    /**
//     * @param elegantUser the elegantUser to set
//     */
//    public void setElegantUser(ElegantUser elegantUser) {
//        this.elegantUser = elegantUser;
//    }
//    /**
//     * @return the loginServer
//     */
//    public String getLoginServer() {
//        return loginServer;
//    }
//
//    /**
//     * @param loginServer the loginServer to set
//     */
//    public void setLoginServer(String loginServer) {
//        this.loginServer = loginServer;
//    }
    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isUserLoggedIn() {
        boolean isExist = false;
        try {
            LoginManager loginManager = (LoginManager) AppManager.getInstance().getViewManagerList().get("loginManager");
            ArrayList<ElegantUser> userList = loginManager.getUserByName(PersistanceManager.getInstance().getElegantUser().getUserLoginName());
            ElegantUser elegantUser = (ElegantUser) userList.get(0);
            isExist = elegantUser.getLoggedIn() == 1;
            if (!isExist) {
                CustUtil.showErrorDialogue("Your Session on the Server is Invalid..  Please re-login to continue");
                System.exit(0);
            }
        } catch (Exception e) {
            CustUtil.showErrorDialogue(e.getMessage());
        }
        return isExist;
    }

    public JasperViewer showReport(JasperPrint jasperPrint) {
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
        jasperViewer.setAlwaysOnTop(false);
        String title = CustUtil.APPNAME + " - " + " - User : " + PersistanceManager.getInstance().getElegantUser().getUserName();
        jasperViewer.setIconImage(ImagesDir.getImage(CustUtil.APPIMAGE).getImage());
        jasperViewer.setTitle(title);
        return reportViewer;
    }

}
