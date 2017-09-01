package com.custmanager.reports;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.cust.domain.vo.ElegantBuySellConsolidation;
import com.custmanager.util.CustUtil;
import java.awt.Color;
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
public class PendingOrderListReport {

    private Collection<ElegantBuySellConsolidation> list = new ArrayList<>();
    private Date frmDate, toDate;
    private String supCode, salesMan, prodCode;
    private Style headerStyle, detailTextStyle,detailNumberStyle;
    private int rowHeight, topMargin, bottomMargin, leftMargin, rightMargin;
    private String billType;

    public PendingOrderListReport(Collection<ElegantBuySellConsolidation> c) {
        list.addAll(c);
    }

    public PendingOrderListReport(ArrayList<ElegantBuySellConsolidation> c, Date frmDt, Date toDt, String supCode, String salesMan, String prodCode, String billType, int rowHeight, int top, int bottom, int left, int right) {
        this(c);
        this.frmDate = frmDt;
        this.toDate = toDt;
        this.supCode = supCode;
        this.salesMan = salesMan;
        this.prodCode = prodCode;
        this.rowHeight = rowHeight;
        this.topMargin = top;
        this.bottomMargin = bottom;
        this.leftMargin = left;
        this.rightMargin = right;
        this.billType = billType;
    }

    public JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException {
        headerStyle = createHeaderStyle();
        detailTextStyle = createDetailTextStyle();
        detailNumberStyle = createDetailNumberStyle();
        DynamicReport dynaReport = getReport(headerStyle, detailTextStyle, detailNumberStyle);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(), new JRBeanCollectionDataSource(list));
        return jp;
    }

    private Style createHeaderStyle() {
        StyleBuilder sb = new StyleBuilder(true);
        sb.setFont(Font.VERDANA_SMALL_BOLD);
        sb.setBorder(Border.THIN());
        sb.setBorderBottom(Border.PEN_2_POINT());
        sb.setBorderColor(Color.BLACK);
        sb.setBackgroundColor(Color.magenta);
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.LEFT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setTransparency(Transparency.OPAQUE);
        return sb.build();
    }

    private Style createDetailTextStyle() {
        StyleBuilder sb = new StyleBuilder(true);
        sb.setFont(Font.VERDANA_SMALL);
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.LEFT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingLeft(5);
        return sb.build();
    }

    private Style createDetailNumberStyle() {
        StyleBuilder sb = new StyleBuilder(true);
        sb.setFont(Font.VERDANA_SMALL);
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.RIGHT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingRight(5);
        return sb.build();
    }

    private AbstractColumn createColumn(String property, Class type,
            String title, int width, Style headerStyle, Style detailStyle)
            throws ColumnBuilderException {
        AbstractColumn columnState = ColumnBuilder.getNew()
                .setColumnProperty(property, type.getName()).setTitle(
                        title).setWidth(Integer.valueOf(width))
                .setStyle(detailStyle).setHeaderStyle(headerStyle).build();

        return columnState;
    }

    private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle) throws ColumnBuilderException, ClassNotFoundException {
        Style colHeaderStyleNumber = headerStyle;
        colHeaderStyleNumber.setHorizontalAlign(HorizontalAlign.RIGHT);
        DynamicReportBuilder report = new DynamicReportBuilder();
        AbstractColumn billNo = createColumn("billno", String.class, "No", 10, headerStyle, detailTextStyle);
        AbstractColumn billDt = createColumn("billDate", Date.class, "Date", 10, headerStyle, detailTextStyle);
        AbstractColumn supName = createColumn("buyersellerName", String.class, ((this.billType.equals(CustUtil.PENDINGPURCHASEORDERS) || (this.billType.equals(CustUtil.ORDERVSSALES))) ? "Supplier" : "Customer"), 30, headerStyle, detailTextStyle);
        AbstractColumn supBillNo = createColumn("buyersellerBillno", String.class, ((this.billType.equals(CustUtil.PENDINGPURCHASEORDERS) || (this.billType.equals(CustUtil.ORDERVSSALES)))? "Supp Inv No" : "Cust Inv No"), 10, headerStyle, detailTextStyle);
        AbstractColumn purQty = createColumn("purchQty", Double.class, "Ord Qty", 10, colHeaderStyleNumber, detailNumStyle);
        AbstractColumn invQty = createColumn("billedQty", Double.class, "Inv Qty", 10, colHeaderStyleNumber, detailNumStyle);
        AbstractColumn purRtnQty = createColumn("purchRtnqty", Double.class, "Ord Rtn", 10, colHeaderStyleNumber, detailNumStyle);
        AbstractColumn invRtnQty = createColumn("billedRtnqty", Double.class, "Inv Qty", 10, colHeaderStyleNumber, detailNumStyle);
        AbstractColumn pendPurQty = createColumn("pendingPurQty", Double.class, "Pending Ord", 10, colHeaderStyleNumber, detailNumStyle);
        AbstractColumn pendInvQty = createColumn("pendingInvQty", Double.class, "Pending Inv", 10, colHeaderStyleNumber, detailNumStyle);
        AbstractColumn unitAmt = createColumn("unitAmt", Double.class, "Amt", 10, colHeaderStyleNumber, detailNumStyle);

        if (this.billType.equals(CustUtil.PENDINGPURCHASEORDERS)) {
            report.addColumn(billNo).addColumn(billDt).addColumn(supBillNo).addColumn(supName).addColumn(purQty).addColumn(purRtnQty).addColumn(pendPurQty).addColumn(unitAmt);
        } else if (this.billType.equals(CustUtil.PENDINGSALESORDERS)) {
            report.addColumn(billNo).addColumn(billDt).addColumn(supBillNo).addColumn(supName).addColumn(invQty).addColumn(invRtnQty).addColumn(pendInvQty).addColumn(unitAmt);
        } else if (this.billType.equals(CustUtil.ORDERVSSALES)) {
            report.addColumn(billNo).addColumn(billDt).addColumn(supBillNo).addColumn(supName).addColumn(purQty).addColumn(invQty).addColumn(unitAmt);
        }
        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(14, Font._FONT_GEORGIA, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(Font.VERDANA_SMALL_BOLD);

        String subTitle = "From : " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(frmDate) + " To: " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(toDate);
        if (this.billType.equals(CustUtil.PENDINGPURCHASEORDERS)) {
            subTitle += (supCode != null && !supCode.isEmpty() ? " SupCode : " + supCode : "");
        } else if (this.billType.equals(CustUtil.PENDINGSALESORDERS)) {
            subTitle += (supCode != null && !supCode.isEmpty() ? " CustCode : " + supCode : "");
        }
        subTitle += (salesMan != null && !salesMan.isEmpty() ? " SalesMan : " + salesMan : "");
        subTitle += (prodCode != null && !prodCode.isEmpty() ? " Product : " + prodCode : "");

        report.addAutoText(CustUtil.APPNAME + " " + CustUtil.getReportDate(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 500, CustUtil.elegantStyle().build());
        if (this.billType.equals(CustUtil.PENDINGPURCHASEORDERS)) {
            report.addAutoText("Pending Orders Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
            report.setGrandTotalLegend("Total");
        } else if (this.billType.equals(CustUtil.PENDINGSALESORDERS)) {
            report.addAutoText("Pending Sales Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
            report.setGrandTotalLegend("Total ");
        } else if (this.billType.equals(CustUtil.ORDERVSSALES)) {
            report.addAutoText("Order Vs Sale Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
            report.setGrandTotalLegend("Total");
        }
        report.addAutoText(subTitle, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, subTitleStyle.build());        
        report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_CENTER, 60, 60);
        report.setTitleStyle(titleStyle.build());
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true);
        report.setDetailHeight(rowHeight);
        report.setMargins(topMargin, bottomMargin, leftMargin, rightMargin);

        billDt.setPattern(CustUtil.DISPLAYDATEFORMAT);
        purQty.setPattern(CustUtil.NUMBERFORMAT);
        purRtnQty.setPattern(CustUtil.NUMBERFORMAT);
        invQty.setPattern(CustUtil.NUMBERFORMAT);        
        invRtnQty.setPattern(CustUtil.NUMBERFORMAT);                
        unitAmt.setPattern(CustUtil.NUMBERFORMAT);
        if (this.billType.equals(CustUtil.PENDINGPURCHASEORDERS)) {
            report.addGlobalFooterVariable(purQty, DJCalculation.SUM);
            report.addGlobalFooterVariable(purRtnQty, DJCalculation.SUM);
            report.addGlobalFooterVariable(pendPurQty, DJCalculation.SUM);
        } else if (this.billType.equals(CustUtil.PENDINGSALESORDERS)) {
            report.addGlobalFooterVariable(invRtnQty, DJCalculation.SUM);
            report.addGlobalFooterVariable(invQty, DJCalculation.SUM);
            report.addGlobalFooterVariable(pendInvQty, DJCalculation.SUM);
        } else if (this.billType.equals(CustUtil.ORDERVSSALES)) {
            report.addGlobalFooterVariable(purQty, DJCalculation.SUM);
            report.addGlobalFooterVariable(invQty, DJCalculation.SUM);
            report.addGlobalFooterVariable(unitAmt, DJCalculation.SUM);            
        }
        return report.build();
    }
}
