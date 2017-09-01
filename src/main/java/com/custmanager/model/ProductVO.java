/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.model;

import com.cust.domain.vo.ElegantProduct;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Inderjit
 */
public class ProductVO extends Observable {

    private ElegantProduct product;
    private ArrayList<ElegantProduct> prodList;
    private ArrayList<ElegantProduct> prodRepList;
    private TableRowSorter<TableModel> tableRowsorter;
    private String[] colNames = {"Product ID", "Product Name", "Min In-Stock","Re-Order Point","Standard Cost","List Price","Color"};
    private ProdTableModel prodModel;

    public ProductVO() {
        initAll();
    }

    public void initAll() {
        setProduct(new ElegantProduct());
        setProdList(new ArrayList<>());
        setProdRepList(new ArrayList<>());
    }

    /**
     * @return the product
     */
    public ElegantProduct getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ElegantProduct product) {
        this.product = product;
        setChanged();
        notifyObservers(this.product);
    }

    /**
     * @return the prodList
     */
    public ArrayList<ElegantProduct> getProdList() {
        return prodList;
    }

    /**
     * @param prodList the prodList to set
     */
    public void setProdList(ArrayList<ElegantProduct> prodList) {
        this.prodList = prodList;
        setProdModel(new ProdTableModel(getColNames(), getProdList()));

    }

    /**
     * @return the prodRepList
     */
    public ArrayList<ElegantProduct> getProdRepList() {
        return prodRepList;
    }

    /**
     * @param prodRepList the prodRepList to set
     */
    public void setProdRepList(ArrayList<ElegantProduct> prodRepList) {
        this.prodRepList = prodRepList;
    }

    /**
     * @return the tableRowsorter
     */
    public TableRowSorter<TableModel> getTableRowsorter() {
        if (tableRowsorter==null)     setTableRowsorter(new TableRowSorter(getProdModel()));        
        return tableRowsorter;
    }

    /**
     * @param tableRowsorter the tableRowsorter to set
     */
    public void setTableRowsorter(TableRowSorter<TableModel> tableRowsorter) {
        this.tableRowsorter = tableRowsorter;
    }

    /**
     * @return the colNames
     */
    public String[] getColNames() {
        return colNames;
    }

    /**
     * @param colNames the colNames to set
     */
    public void setColNames(String[] colNames) {
        this.colNames = colNames;
    }

    /**
     * @return the prodModel
     */
    public ProdTableModel getProdModel() {
        return prodModel;
    }

    /**
     * @param prodModel the prodModel to set
     */
    public void setProdModel(ProdTableModel prodModel) {
        this.prodModel = prodModel;
        setChanged();
        notifyObservers(this.prodModel);

    }
}

