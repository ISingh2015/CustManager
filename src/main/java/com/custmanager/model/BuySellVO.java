/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantBuySell;
import com.cust.domain.vo.ElegantBuySellConsolidation;
import com.cust.domain.vo.ElegantBuySellDetails;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class BuySellVO extends Observable {

    private ElegantBuySell elegantBuy;
    private ArrayList<ElegantBuySell> buyList;
    private ArrayList<ElegantBuySellDetails> buyDetailsList,buyDetailsListOld;
    private ArrayList<ElegantBuySell> buyRepList;
    
    private TableRowSorter<TableModel> tableRowsorter;
    private ArrayList<ElegantBuySellConsolidation> pendingBuyRepList;
    
    private String[] colNamesModelBuy = {"Bill ID", "Bill No", "Bill Date", "Supplier", "Bill Amount", "Status"};
    private String[] colNamesModelSell = {"Bill ID", "Bill No", "Bill Date", "Supplier", "Bill Amount"};    
    
    private String[] colNamesModelPurchRtn = {"Bill ID", "Bill No", "Bill Date", "Supplier", "Bill Amount"};        
    private String[] colNamesModelSaleRtn = {"Bill ID", "Bill No", "Bill Date", "Supplier", "Bill Amount"};            

//    private String[] colNamesDetailsModel = {"Srl.", "Item Code", "Product Description", "Instock","Qty", "Qty", "Rate", "Units", "Discount", "Value"};

    private String[] colNamesPurDetailsModel = {"Srl.", "Item Code", "Product Description", "Instock","Qty", "Qty", "Rate", "Units", "Discount", "Value"};
    private String[] colNamesSaleDetailsModel = {"Srl.", "Item Code", "Product Description", "Instock","Qty", "Qty", "Rate", "Units", "Discount", "Value"};
    
    private String[] colNamesPurRtnDetailsModel = {"Srl.", "Item Code", "Product Description", "Instock","Qty", "Qty", "Rate", "Units", "Discount", "Value"};
    private String[] colNamesSaleRtnDetailsModel = {"Srl.", "Item Code", "Product Description", "Instock","Qty", "Qty", "Rate", "Units", "Discount", "Value"};
    
    private ElegantBuySell elegantSell;
    private ArrayList<ElegantBuySell> sellList;
    private ArrayList<ElegantBuySellDetails> sellDetailsList;
    private ArrayList<ElegantBuySell> sellRepList;

    private ElegantBuySell elegantPurRtn;
    private ArrayList<ElegantBuySell> purRtnList;
    private ArrayList<ElegantBuySellDetails> purRtnDetailsList;
    private ArrayList<ElegantBuySell> purRtnRepList;

    private ElegantBuySell elegantSaleRtn;
    private ArrayList<ElegantBuySell> saleRtnList;
    private ArrayList<ElegantBuySellDetails> saleRtnDetailsList;
    private ArrayList<ElegantBuySell> saleRtnRepList;
    
    private PurchaseTableModel buyModel;
    private PurchaseDetailsTableModel buyDetailsModel;
    
    private SalesTableModel sellModel;
    private SalesDetailsTableModel sellDetailsModel;

    private PurchaseRtnTableModel purchaseRtnModel;
    private PurchaseRtnDetailsTableModel purchaseRtnDetailsModel;

    private SaleRtnTableModel salesRtnModel;
    private SaleRtnDetailsTableModel salesRtnDetailsModel;

    /**
     * @return the elegantBuy
     */
    public ElegantBuySell getElegantBuy() {
        return elegantBuy;
    }

    /**
     * @param elegantBuy the elegantBuy to set
     */
    public void setElegantBuy(ElegantBuySell elegantBuy) {
        this.elegantBuy = elegantBuy;
        setChanged();
        notifyObservers(this.elegantBuy);

    }

    /**
     * @return the buyList
     */
    public ArrayList<ElegantBuySell> getBuyList() {
        return buyList;
    }

    /**
     * @param buyList the buyList to set
     */
    public void setBuyList(ArrayList<ElegantBuySell> buyList) {
        this.buyList = buyList;
        setBuyModel(new PurchaseTableModel(colNamesModelBuy, this.buyList));
    }

    /**
     * @return the buySellRepList
     */
    public ArrayList<ElegantBuySell> getBuySellRepList() {
        return buyRepList;
    }

    /**
     * @param buySellRepList the buySellRepList to set
     */
    public void setBuySellRepList(ArrayList<ElegantBuySell> buySellRepList) {
        this.buyRepList = buySellRepList;
        setChanged();
        notifyObservers(this.buyRepList);

    }

    /**
     * @return the tableRowsorter
     */
    public TableRowSorter<TableModel> getTableRowsorter() {
        return tableRowsorter;
    }

    /**
     * @param tableRowsorter the tableRowsorter to set
     */
    public void setTableRowsorter(TableRowSorter<TableModel> tableRowsorter) {
        this.tableRowsorter = tableRowsorter;
    }

    /**
     * @return the buyDetailsModel
     */
    public PurchaseDetailsTableModel getBuyDetailsModel() {
        return buyDetailsModel;
    }

    /**
     * @param buyModel the buySellDetailsModel to set
     */
    public void setBuyDetailsModel(PurchaseDetailsTableModel buyDetailsModel) {
        this.buyDetailsModel = buyDetailsModel;
        setChanged();
        notifyObservers(this.buyDetailsModel);

    }

    /**
     * @return the buyDetailsList
     */
    public ArrayList<ElegantBuySellDetails> getBuyDetailsList() {
        return buyDetailsList;
    }

    /**
     * @param buyDetailsList the buyDetailsList to set
     */
    public void setBuyDetailsList(ArrayList<ElegantBuySellDetails> buyDetailsList) {
        this.buyDetailsList = buyDetailsList;
        ElegantBuySell buy = getElegantBuy();
        buy.setBuySellDetailsList(this.buyDetailsList);
        setBuyDetailsModel(new PurchaseDetailsTableModel(colNamesPurDetailsModel, this.buyDetailsList));
        double total = getBillTotal() - buy.getFreighTranspDedAmt() - buy.getTaxDedAmt();
        buy.setFinalBillAmt(total);
        setElegantBuy(buy);
    }

    public double getBillTotal() {
        double tot = 0d;
        if (this.getBuyDetailsList()==null || this.getBuyDetailsList().isEmpty()) return tot;
        for (ElegantBuySellDetails elegantBuyDetails : this.getBuyDetailsList()) {
            tot += elegantBuyDetails.getUnitAmt();
        }
        return tot;
    }

    public double getInvTotal() {
        double tot = 0d;
        if (this.getBuyDetailsList()==null || this.getSellDetailsList().isEmpty()) return tot;        
        for (ElegantBuySellDetails elegantSellDetails : this.getSellDetailsList()) {
            tot += elegantSellDetails.getUnitAmt();
        }
        return tot;
    }

    public double getPurchRtnTotal() {
        double tot = 0d;
        if (this.getPurRtnDetailsList()==null || this.getPurRtnDetailsList().isEmpty()) return tot;        
        for (ElegantBuySellDetails elegantSellDetails : this.getPurRtnDetailsList()) {
            tot += elegantSellDetails.getUnitAmt();
        }
        return tot;
    }
    
    public double getSaleRtnTotal() {
        double tot = 0d;
        if (this.getSaleRtnDetailsList()==null || this.getSaleRtnDetailsList().isEmpty()) return tot;        
        for (ElegantBuySellDetails elegantSellDetails : this.getSaleRtnDetailsList()) {
            tot += elegantSellDetails.getUnitAmt();
        }
        return tot;
    }

//    /**
//     * @return the colNamesDetailsModel
//     */
//    public String[] getColNamesDetailsModel() {
//        return colNamesDetailsModel;
//    }
//
//    /**
//     * @param colNamesDetailsModel the colNamesDetailsModel to set
//     */
//    public void setColNamesDetailsModel(String[] colNamesDetailsModel) {
//        this.colNamesDetailsModel = colNamesDetailsModel;
//    }

    /**
     * @return the buyModel
     */
    public PurchaseTableModel getBuyModel() {
        return buyModel;
    }

    /**
     * @param buyModel the buyModel to set
     */
    public void setBuyModel(PurchaseTableModel buyModel) {
        this.buyModel = buyModel;
        setChanged();
        notifyObservers(this.buyModel);
    }

    /**
     * @return the elegantSell
     */
    public ElegantBuySell getElegantSell() {
        return elegantSell;
    }

    /**
     * @param elegantSell the elegantSell to set
     */
    public void setElegantSell(ElegantBuySell elegantSell) {
        this.elegantSell = elegantSell;
        setChanged();
        notifyObservers(this.elegantSell);
    }

    /**
     * @return the sellList
     */
    public ArrayList<ElegantBuySell> getSellList() {
        return sellList;
    }

    /**
     * @param sellList the sellList to set
     */
    public void setSellList(ArrayList<ElegantBuySell> sellList) {
        this.sellList = sellList;
        setSellModel(new SalesTableModel(colNamesModelSell, this.sellList));
    }

    /**
     * @return the sellDetailsList
     */
    public ArrayList<ElegantBuySellDetails> getSellDetailsList() {
        return sellDetailsList;
    }

    /**
     * @param sellDetailsList the sellDetailsList to set
     */
    public void setSellDetailsList(ArrayList<ElegantBuySellDetails> sellDetailsList) {
        this.sellDetailsList = sellDetailsList;
        ElegantBuySell sell = getElegantSell();
        sell.setBuySellDetailsList(this.sellDetailsList);
        setSellDetailsModel(new SalesDetailsTableModel(colNamesSaleDetailsModel, this.sellDetailsList));
        double total = getInvTotal() - sell.getFreighTranspDedAmt() - sell.getTaxDedAmt();
        sell.setFinalBillAmt(total);
        setElegantSell(sell);
    }

    /**
     * @return the sellModel
     */
    public SalesTableModel getSellModel() {
        return sellModel;
    }

    /**
     * @param sellModel the sellModel to set
     */
    public void setSellModel(SalesTableModel sellModel) {
        this.sellModel = sellModel;
        setChanged();
        notifyObservers(this.sellModel);
    }

    /**
     * @return the sellDetailsModel
     */
    public SalesDetailsTableModel getSellDetailsModel() {
        return sellDetailsModel;
    }

    /**
     * @param sellDetailsModel the sellDetailsModel to set
     */
    public void setSellDetailsModel(SalesDetailsTableModel sellDetailsModel) {
        this.sellDetailsModel = sellDetailsModel;
        setChanged();
        notifyObservers(this.sellDetailsModel);
    }

    /**
     * @return the elegantPurRtn
     */
    public ElegantBuySell getElegantPurRtn() {
        return elegantPurRtn;
    }

    /**
     * @param elegantPurRtn the elegantPurRtn to set
     */
    public void setElegantPurRtn(ElegantBuySell elegantPurRtn) {
        this.elegantPurRtn = elegantPurRtn;
        setChanged();
        notifyObservers(this.elegantPurRtn);
        
    }

    /**
     * @return the purRtnList
     */
    public ArrayList<ElegantBuySell> getPurRtnList() {
        return purRtnList;
    }

    /**
     * @param purRtnList the purRtnList to set
     */
    public void setPurRtnList(ArrayList<ElegantBuySell> purRtnList) {
        this.purRtnList = purRtnList;
        setPurchaseRtnModel(new PurchaseRtnTableModel(colNamesModelPurchRtn, this.purRtnList));
    }

    /**
     * @return the purRtnDetailsList
     */
    public ArrayList<ElegantBuySellDetails> getPurRtnDetailsList() {
        return purRtnDetailsList;
    }

    /**
     * @param purRtnDetailsList the purRtnDetailsList to set
     */
    public void setPurRtnDetailsList(ArrayList<ElegantBuySellDetails> purRtnDetailsList) {
        this.purRtnDetailsList = purRtnDetailsList;
        ElegantBuySell purchaseRtn = getElegantPurRtn();
        purchaseRtn.setBuySellDetailsList(this.purRtnDetailsList);
        setPurchaseRtnDetailsModel(new PurchaseRtnDetailsTableModel(colNamesPurRtnDetailsModel, this.purRtnDetailsList));
        double total = getPurchRtnTotal()- purchaseRtn.getFreighTranspDedAmt() - purchaseRtn.getTaxDedAmt();
        purchaseRtn.setFinalBillAmt(total);
        setElegantPurRtn(purchaseRtn);
    }

    /**
     * @return the purRtnRepList
     */
    public ArrayList<ElegantBuySell> getPurRtnRepList() {
        return purRtnRepList;
    }

    /**
     * @param purRtnRepList the purRtnRepList to set
     */
    public void setPurRtnRepList(ArrayList<ElegantBuySell> purRtnRepList) {
        this.purRtnRepList = purRtnRepList;
        setChanged();
        notifyObservers(this.purRtnRepList);
        
    }

    /**
     * @return the purchaseRtnModel
     */
    public PurchaseRtnTableModel getPurchaseRtnModel() {
        return purchaseRtnModel;
    }

    /**
     * @param purchaseRtnModel the purchaseRtnModel to set
     */
    public void setPurchaseRtnModel(PurchaseRtnTableModel purchaseRtnModel) {
        this.purchaseRtnModel = purchaseRtnModel;
        setChanged();
        notifyObservers(this.purchaseRtnModel);

    }

    /**
     * @return the purchaseRtnDetailsModel
     */
    public PurchaseRtnDetailsTableModel getPurchaseRtnDetailsModel() {
        return purchaseRtnDetailsModel;
    }

    /**
     * @param purchaseRtnDetailsModel the purchaseRtnDetailsModel to set
     */
    public void setPurchaseRtnDetailsModel(PurchaseRtnDetailsTableModel purchaseRtnDetailsModel) {
        this.purchaseRtnDetailsModel = purchaseRtnDetailsModel;
        setChanged();
        notifyObservers(this.purchaseRtnDetailsModel);

    }

    /**
     * @return the elegantSaleRtn
     */
    public ElegantBuySell getElegantSaleRtn() {
        return elegantSaleRtn;
    }

    /**
     * @param elegantSaleRtn the elegantSaleRtn to set
     */
    public void setElegantSaleRtn(ElegantBuySell elegantSaleRtn) {
        this.elegantSaleRtn = elegantSaleRtn;
        setChanged();
        notifyObservers(this.elegantSaleRtn);

    }

    /**
     * @return the saleRtnList
     */
    public ArrayList<ElegantBuySell> getSaleRtnList() {
        return saleRtnList;
    }

    /**
     * @param saleRtnList the saleRtnList to set
     */
    public void setSaleRtnList(ArrayList<ElegantBuySell> saleRtnList) {
        this.saleRtnList = saleRtnList;
        setSalesRtnModel(new SaleRtnTableModel(colNamesModelSaleRtn, this.saleRtnList));        
    }

    /**
     * @return the saleRtnDetailsList
     */
    public ArrayList<ElegantBuySellDetails> getSaleRtnDetailsList() {
        return saleRtnDetailsList;
    }

    /**
     * @param saleRtnDetailsList the saleRtnDetailsList to set
     */
    public void setSaleRtnDetailsList(ArrayList<ElegantBuySellDetails> saleRtnDetailsList) {
        this.saleRtnDetailsList = saleRtnDetailsList;
        ElegantBuySell saleRtn = getElegantSaleRtn();
        saleRtn.setBuySellDetailsList(this.saleRtnDetailsList);
        setSalesRtnDetailsModel(new SaleRtnDetailsTableModel(colNamesSaleRtnDetailsModel, this.saleRtnDetailsList));
        double total = getSaleRtnTotal()- saleRtn.getFreighTranspDedAmt() - saleRtn.getTaxDedAmt();
        saleRtn.setFinalBillAmt(total);
        setElegantSaleRtn(saleRtn);

    }

    /**
     * @return the saleRtnRepList
     */
    public ArrayList<ElegantBuySell> getSaleRtnRepList() {
        return saleRtnRepList;
    }

    /**
     * @param saleRtnRepList the saleRtnRepList to set
     */
    public void setSaleRtnRepList(ArrayList<ElegantBuySell> saleRtnRepList) {
        this.saleRtnRepList = saleRtnRepList;
        
    }

    /**
     * @return the salesRtnModel
     */
    public SaleRtnTableModel getSalesRtnModel() {
        return salesRtnModel;
    }

    /**
     * @param salesRtnModel the salesRtnModel to set
     */
    public void setSalesRtnModel(SaleRtnTableModel salesRtnModel) {
        this.salesRtnModel = salesRtnModel;
        setChanged();
        notifyObservers(this.salesRtnModel);
    }

    /**
     * @return the salesRtnDetailsModel
     */
    public SaleRtnDetailsTableModel getSalesRtnDetailsModel() {
        return salesRtnDetailsModel;
    }

    /**
     * @param salesRtnDetailsModel the salesRtnDetailsModel to set
     */
    public void setSalesRtnDetailsModel(SaleRtnDetailsTableModel salesRtnDetailsModel) {
        this.salesRtnDetailsModel = salesRtnDetailsModel;
        setChanged();
        notifyObservers(this.salesRtnDetailsModel);

    }

    /**
     * @return the pendingBuyRepList
     */
    public ArrayList<ElegantBuySellConsolidation> getPendingBuyRepList() {
        return pendingBuyRepList;
    }

    /**
     * @param pendingBuyRepList the pendingBuyRepList to set
     */
    public void setPendingBuyRepList(ArrayList<ElegantBuySellConsolidation> pendingBuyRepList) {
        this.pendingBuyRepList = pendingBuyRepList;
        setChanged();
        notifyObservers(this.pendingBuyRepList);

    }

    /**
     * @return the buyDetailsListOld
     */
    public ArrayList<ElegantBuySellDetails> getBuyDetailsListOld() {
        return buyDetailsListOld;
    }

    /**
     * @param buyDetailsListOld the buyDetailsListOld to set
     */
    public void setBuyDetailsListOld(ArrayList<ElegantBuySellDetails> buyDetailsListOld) {
        this.buyDetailsListOld = buyDetailsListOld;
    }

}
