package com.custmanager.reports;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.DJVariable;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.cust.domain.vo.ElegantBuySell;
import com.custmanager.util.CustUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Inderjit
 */
public class BuySellListReport extends BaseReport{

    private Collection<ElegantBuySell> list = new ArrayList<>();
    private String supCode, salesMan, billAmt;
    private int billType;

    public BuySellListReport(Collection<ElegantBuySell> c) {
        list.addAll(c);
    }

    public BuySellListReport(ArrayList<ElegantBuySell> c, Date frmDt, Date toDt, String supCode, String salesMan, String billAmt, boolean active, int billType, boolean createBorder, int rowHeight, int top, int bottom, int left, int right) {
        this(c);
        super.setFrmDate(frmDt);
        super.setToDate(toDt);
        super.setActive(active);
        super.setCreateBorder(createBorder);
        super.setRowHeight(rowHeight);
        super.setTopMargin(top);
        super.setBottomMargin(bottom);
        super.setLeftMargin(left);
        super.setRightMargin(right);
        this.supCode = supCode;
        this.salesMan = salesMan;
        this.billAmt = billAmt;
        this.billType=billType;
    }

    public JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException {
        Style detailTextStyle = createDetailTextStyle();
        Style detailNumberStyle = createDetailNumberStyle();
        DynamicReport dynaReport = getReport(detailTextStyle, detailNumberStyle);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(), new JRBeanCollectionDataSource(list));
        return jp;
    }

    private DynamicReport getReport(Style detailTextStyle, Style detailNumStyle) throws ColumnBuilderException, ClassNotFoundException {


        DynamicReportBuilder report = new DynamicReportBuilder();
        AbstractColumn billId = createColumn("billID", Long.class, "ID Code", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn billNo = createColumn("billNo", String.class, "No", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn billDt = createColumn("billDt", Date.class, "Date", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn supName = createColumn("buyerSellerName", String.class, (billType== CustUtil.PURCHASEBILLTYPE || this.billType == CustUtil.PURCHASERTNTYPE ? "Supplier" : "Customer"), 35, getHeaderTextStyle(), detailTextStyle);
        AbstractColumn supBillNo = createColumn("buyerSellBillNo", String.class, (billType== CustUtil.PURCHASEBILLTYPE || this.billType == CustUtil.PURCHASERTNTYPE ? "Inv No" : "Inv No"), 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn fBillAmt = createColumn("finalBillAmt", Double.class, (billType== CustUtil.PURCHASEBILLTYPE || this.billType == CustUtil.PURCHASERTNTYPE ? "Order Amt" : "Inv Amt"), 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn taxAmt = createColumn("taxDedAmt", Double.class, "Tax Amt", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn supBillDt = createColumn("buyerSellerBillDt", Date.class, (this.billType == CustUtil.PURCHASEBILLTYPE || this.billType == CustUtil.PURCHASERTNTYPE ? "Inv Dt" : "Inv Dt"), 10, getHeaderTextStyle(), detailTextStyle);
        if (this.billType == CustUtil.PURCHASEBILLTYPE || this.billType == CustUtil.PURCHASERTNTYPE ) {
            report.addColumn(billId).addColumn(billNo).addColumn(billDt).addColumn(supBillNo).addColumn(supBillDt).addColumn(supName).addColumn(taxAmt).addColumn(fBillAmt);
        } else if (this.billType == CustUtil.SALESBILLTYPE || this.billType == CustUtil.SALESRTNBILLTYPE) {
            report.addColumn(billId).addColumn(billNo).addColumn(billDt).addColumn(supName).addColumn(taxAmt).addColumn(fBillAmt);
        }
        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(14, Font._FONT_GEORGIA, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(Font.VERDANA_SMALL_BOLD);

        StyleBuilder footerStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(8, Font._FONT_GEORGIA, true));

        String subTitle = "Bills From : " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getFrmDate()) + " To: " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getToDate());
        if (this.billType== CustUtil.PURCHASEBILLTYPE || this.billType == CustUtil.PURCHASERTNTYPE ) {
            subTitle += (supCode != null && !supCode.isEmpty() ? " SupCode : " + supCode : "");
        } else if (this.billType == CustUtil.SALESBILLTYPE || this.billType == CustUtil.SALESRTNBILLTYPE) {
            subTitle += (supCode != null && !supCode.isEmpty() ? " CustCode : " + supCode : "");
        }
        subTitle += (salesMan != null && !salesMan.isEmpty() ? " SalesMan : " + salesMan : "");
        subTitle += (billAmt != null && !billAmt.isEmpty() ? " BillAmt >= " + billAmt : "");
        subTitle += (isActive() ? " Active Bills" : "");

        report.addAutoText(CustUtil.APPNAME + " " + CustUtil.getReportDate(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 500, CustUtil.elegantStyle().build());
        if (this.billType == CustUtil.PURCHASEBILLTYPE) {
            report.addAutoText("Orders Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
            report.setGrandTotalLegend("Total Orders");
        } else if (this.billType == CustUtil.PURCHASERTNTYPE) {
            report.addAutoText("Orders Rtn Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
            report.setGrandTotalLegend("Total Orders Rtn");
            
        } else if (this.billType == CustUtil.SALESBILLTYPE) {
            report.addAutoText("Invoice Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
            report.setGrandTotalLegend("Total Invoice");
        } else if (this.billType == CustUtil.SALESRTNBILLTYPE) {
            report.addAutoText("Invoice RTN Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
            report.setGrandTotalLegend("Total Invoice RTN");
        }
        
        report.addAutoText(subTitle, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, subTitleStyle.build());
        report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_CENTER, 60, 60, footerStyle.build());

        report.setTitleStyle(titleStyle.build());
        report.setSubtitleStyle(subTitleStyle.build());

        billDt.setPattern(CustUtil.DISPLAYDATEFORMAT);
        supBillDt.setPattern(CustUtil.DISPLAYDATEFORMAT);
        fBillAmt.setPattern(CustUtil.NUMBERFORMAT);
        taxAmt.setPattern(CustUtil.NUMBERFORMAT);
        report.addGlobalFooterVariable(fBillAmt, DJCalculation.SUM);

        report.setUseFullPageWidth(true);
        report.setDetailHeight(getRowHeight());
        report.setMargins(getTopMargin(), getBottomMargin(), getLeftMargin(), getRightMargin());

        return report.build();
    }
}
