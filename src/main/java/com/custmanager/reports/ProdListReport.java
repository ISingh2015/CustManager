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
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.cust.domain.vo.ElegantProduct;
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
public class ProdListReport extends BaseReport{

    private Collection<ElegantProduct> list = new ArrayList<>();

    public ProdListReport(Collection<ElegantProduct> c) {
        list.addAll(c);
    }

    public ProdListReport(ArrayList<ElegantProduct> c, Date frmDt, Date toDt, boolean active, boolean createBorder, int rowHeight, int top, int bottom, int left, int right) {
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
        AbstractColumn columnCustId = createColumn("prodID", Long.class, "ID Code", 5, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnName = createColumn("prodName", String.class, "Product Name", 35, getHeaderTextStyle(), detailTextStyle);
        AbstractColumn columnMinStock = createColumn("minInStock", Double.class, "Min Stock", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnReorderPoint = createColumn("reOrderPoint", Double.class, "Re Order", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnStandardCost = createColumn("standardCost", Double.class, "St.Cost.", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnListPrice = createColumn("listPrice", Double.class, "List.Price", 10, getHeaderNumberStyle(), detailNumStyle);
        report.addColumn(columnCustId).addColumn(columnName).addColumn(columnMinStock).addColumn(columnReorderPoint).addColumn(columnStandardCost).addColumn(columnListPrice);

        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(14, Font._FONT_GEORGIA, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));

        String subTitle = "Created Between " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getFrmDate()) + " & " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getToDate());
        subTitle += (isActive() ? " Active Products " : "");
        report.setUseFullPageWidth(true);

        report.addAutoText(CustUtil.APPNAME + " " + CustUtil.getReportDate(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 500, CustUtil.elegantStyle().build());
        report.addAutoText("Product Listing Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
        report.addAutoText(subTitle, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, subTitleStyle.build());
        report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
        report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_CENTER, 50, 50);
        report.addGlobalFooterVariable(columnStandardCost, DJCalculation.SUM);

        columnMinStock.setPattern(CustUtil.NUMBERFORMAT);
        columnReorderPoint.setPattern(CustUtil.NUMBERFORMAT);
        columnStandardCost.setPattern(CustUtil.NUMBERFORMAT);
        columnListPrice.setPattern(CustUtil.NUMBERFORMAT);

        report.setGrandTotalLegend("Totals ");
        report.addGlobalFooterVariable(columnListPrice, DJCalculation.SUM);
        report.setUseFullPageWidth(true);
        report.setDetailHeight(getRowHeight());
        report.setMargins(getTopMargin(), getBottomMargin(), getLeftMargin(), getRightMargin());

        return report.build();
    }
}
