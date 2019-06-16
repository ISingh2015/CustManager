package com.custmanager.vo;

import java.util.Date;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class BuySellReportData {
    
//  create as Value object for dynamic report     
    private long billId,srl,prodId,buyerSellCode;
    private double purchQty,purchQtyRtn,billedQty,billedQtyRtn,purchRate,billRate,unitDiscount,unitAmt,freight,tax,finalBillAmt;
    private String billNo, buyerSellerName, buyerSellerBillNo,prodName,unitPack;
    private String buyerSellerBillDt;
    private Date billDt;

    /**
     * @return the billId
     */
    public long getBillId() {
        return billId;
    }

    /**
     * @param billId the billId to set
     */
    public void setBillId(long billId) {
        this.billId = billId;
    }

    /**
     * @return the srl
     */
    public long getSrl() {
        return srl;
    }

    /**
     * @param srl the srl to set
     */
    public void setSrl(long srl) {
        this.srl = srl;
    }

    /**
     * @return the prodId
     */
    public long getProdId() {
        return prodId;
    }

    /**
     * @param prodId the prodId to set
     */
    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    /**
     * @return the buyerSellCode
     */
    public long getBuyerSellCode() {
        return buyerSellCode;
    }

    /**
     * @param buyerSellCode the buyerSellCode to set
     */
    public void setBuyerSellCode(long buyerSellCode) {
        this.buyerSellCode = buyerSellCode;
    }

    /**
     * @return the purchQty
     */
    public double getPurchQty() {
        return purchQty;
    }

    /**
     * @param purchQty the purchQty to set
     */
    public void setPurchQty(double purchQty) {
        this.purchQty = purchQty;
    }

    /**
     * @return the purchQtyRtn
     */
    public double getPurchQtyRtn() {
        return purchQtyRtn;
    }

    /**
     * @param purchQtyRtn the purchQtyRtn to set
     */
    public void setPurchQtyRtn(double purchQtyRtn) {
        this.purchQtyRtn = purchQtyRtn;
    }

    /**
     * @return the billedQty
     */
    public double getBilledQty() {
        return billedQty;
    }

    /**
     * @param billedQty the billedQty to set
     */
    public void setBilledQty(double billedQty) {
        this.billedQty = billedQty;
    }

    /**
     * @return the billedQtyRtn
     */
    public double getBilledQtyRtn() {
        return billedQtyRtn;
    }

    /**
     * @param billedQtyRtn the billedQtyRtn to set
     */
    public void setBilledQtyRtn(double billedQtyRtn) {
        this.billedQtyRtn = billedQtyRtn;
    }

    /**
     * @return the purchRate
     */
    public double getPurchRate() {
        return purchRate;
    }

    /**
     * @param purchRate the purchRate to set
     */
    public void setPurchRate(double purchRate) {
        this.purchRate = purchRate;
    }

    /**
     * @return the billRate
     */
    public double getBillRate() {
        return billRate;
    }

    /**
     * @param billRate the billRate to set
     */
    public void setBillRate(double billRate) {
        this.billRate = billRate;
    }

    /**
     * @return the unitDiscount
     */
    public double getUnitDiscount() {
        return unitDiscount;
    }

    /**
     * @param unitDiscount the unitDiscount to set
     */
    public void setUnitDiscount(double unitDiscount) {
        this.unitDiscount = unitDiscount;
    }

    /**
     * @return the unitAmt
     */
    public double getUnitAmt() {
        return unitAmt;
    }

    /**
     * @param unitAmt the unitAmt to set
     */
    public void setUnitAmt(double unitAmt) {
        this.unitAmt = unitAmt;
    }

    /**
     * @return the billNo
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * @param billNo the billNo to set
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * @return the buyerSellerName
     */
    public String getBuyerSellerName() {
        return buyerSellerName;
    }

    /**
     * @param buyerSellerName the buyerSellerName to set
     */
    public void setBuyerSellerName(String buyerSellerName) {
        this.buyerSellerName = buyerSellerName;
    }

    /**
     * @return the buyerSellerBillNo
     */
    public String getBuyerSellerBillNo() {
        return buyerSellerBillNo;
    }

    /**
     * @param buyerSellerBillNo the buyerSellerBillNo to set
     */
    public void setBuyerSellerBillNo(String buyerSellerBillNo) {
        this.buyerSellerBillNo = buyerSellerBillNo;
    }

    /**
     * @return the prodName
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * @param prodName the prodName to set
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * @return the unitPack
     */
    public String getUnitPack() {
        return unitPack;
    }

    /**
     * @param unitPack the unitPack to set
     */
    public void setUnitPack(String unitPack) {
        this.unitPack = unitPack;
    }


    /**
     * @return the billDt
     */
    public Date  getBillDt() {
        return billDt;
    }

    /**
     * @param billDt the billDt to set
     */
    public void setBillDt(Date billDt) {
        this.billDt = billDt;
    }

    /**
     * @return the buyerSellerBillDt
     */
    public String getBuyerSellerBillDt() {
        return buyerSellerBillDt;
    }

    /**
     * @param buyerSellerBillDt the buyerSellerBillDt to set
     */
    public void setBuyerSellerBillDt(String buyerSellerBillDt) {
        this.buyerSellerBillDt = buyerSellerBillDt;
    }

    /**
     * @return the freight
     */
    public double getFreight() {
        return freight;
    }

    /**
     * @param freight the freight to set
     */
    public void setFreight(double freight) {
        this.freight = freight;
    }

    /**
     * @return the tax
     */
    public double getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(double tax) {
        this.tax = tax;
    }

    /**
     * @return the finalBillAmt
     */
    public double getFinalBillAmt() {
        return finalBillAmt;
    }

    /**
     * @param finalBillAmt the finalBillAmt to set
     */
    public void setFinalBillAmt(double finalBillAmt) {
        this.finalBillAmt = finalBillAmt;
    }
}
