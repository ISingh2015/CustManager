package com.custmanager.reports;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.DJGroupVariable;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import com.custmanager.util.CustUtil;
import com.custmanager.vo.BuySellReportData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Inderjit
 */
public class BuySellBillReport extends BaseReport {

    private Collection<BuySellReportData> list = new ArrayList<>();

    public BuySellBillReport(Collection<BuySellReportData> c, boolean createBorder, int rowHeight, int topMargin, int leftMargin, int bottomMargin, int rightMargin) {
        list.addAll(c);
        super.setCreateBorder(createBorder);
        super.setRowHeight(rowHeight);
        super.setTopMargin(topMargin);
        super.setBottomMargin(bottomMargin);
        super.setLeftMargin(leftMargin);
        super.setRightMargin(rightMargin);

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

        CustomExpression customExpression1 = new CustomExpression() {
            public Object evaluate(Map fields, Map variables, Map parameters) {
                String supName = (String) fields.get("buyerSellerName");
                return supName;
            }

            public String getClassName() {
                return String.class.getName();
            }
        };

//        CustomExpression customExpression = new CustomExpression() {
//            public Object evaluate(Map fields, Map variables, Map parameters) {
//                Long billNo = (Long) fields.get("billId");
//                return "Bill No : " + billNo;
//            }
//
//            public String getClassName() {
//                return String.class.getName();
//            }
//        };

        AbstractColumn billId = createColumn("billId", Long.class, "ID Code", 5, getHeaderTextStyle(), detailTextStyle, null);
        AbstractColumn srl = createColumn("srl", Long.class, "Srl.", 5, getHeaderNumberStyle(), detailNumStyle, null);
        AbstractColumn prodName = createColumn("prodName", String.class, "Prod Namae", 20, getHeaderTextStyle(), detailTextStyle, null);
        AbstractColumn buySellerName = createColumn("buyerSellerName", String.class, "Buyer/Seller", 20, getHeaderTextStyle(), detailTextStyle, null);
        AbstractColumn purchQty = createColumn("purchQty", Double.class, "Qty", 5, getHeaderNumberStyle(), detailNumStyle, null);
        AbstractColumn purchRate = createColumn("purchRate", Double.class, "Rate", 5, getHeaderNumberStyle(), detailNumStyle, null);
        AbstractColumn unitPack = createColumn("unitPack", String.class, "Pack", 5, getHeaderNumberStyle(), detailNumStyle, null);
        AbstractColumn unitDisc = createColumn("unitDiscount", Double.class, "Disc", 10, getHeaderNumberStyle(), detailNumStyle, null);
        AbstractColumn unitAmt = createColumn("unitAmt", Double.class, "Amt", 10, getHeaderNumberStyle(), detailNumStyle, null);
        report.addColumn(buySellerName).addColumn(billId).addColumn(srl).addColumn(prodName).addColumn(purchQty).addColumn(purchRate).addColumn(unitPack).addColumn(unitDisc).addColumn(unitAmt);

        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(14, Font._FONT_GEORGIA, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
        report.addAutoText(CustUtil.APPNAME + " " + CustUtil.getReportDate(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 500, CustUtil.elegantStyle().build());
        report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT, 60, 60);
        report.setTitleStyle(titleStyle.build());
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true);
        purchQty.setPattern(CustUtil.NUMBERFORMAT);
        purchRate.setPattern(CustUtil.NUMBERFORMAT);
        unitDisc.setPattern(CustUtil.NUMBERFORMAT);
        unitAmt.setPattern(CustUtil.NUMBERFORMAT);

        report.addGlobalFooterVariable(unitAmt, DJCalculation.SUM);

        report.setPageSizeAndOrientation(Page.Page_A4_Portrait());
//        GroupBuilder groupBuilder = new GroupBuilder();

//        DJGroupVariable billNo = new DJGroupVariable(billId, customExpression, detailTextStyle);

//        DJGroup dJGroup0 = groupBuilder.setCriteriaColumn((PropertyColumn) buySellerName)
//                .addHeaderVariable(sName)
//                .build();
//        report.addGroup(dJGroup0);

        GroupBuilder groupBuilder = new GroupBuilder();
        DJGroupVariable sName = new DJGroupVariable(buySellerName, customExpression1, detailTextStyle);        
        DJGroup dJGroup1 = groupBuilder.setCriteriaColumn((PropertyColumn) billId)
                .addFooterVariable(purchQty, DJCalculation.SUM)
                .addFooterVariable(unitAmt, DJCalculation.SUM)
                .addHeaderVariable(sName)
                .build();
        report.addGroup(dJGroup1);
        dJGroup1.setStartInNewPage(Boolean.TRUE);

        report.setAllowDetailSplit(true);
        report.setPrintColumnNames(false);
        report.setUseFullPageWidth(true);
        report.setDetailHeight(getRowHeight());
        report.setMargins(getTopMargin(), getBottomMargin(), getLeftMargin(), getRightMargin());

        return report.build();
    }
}
