package com.custmanager.reports;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.cust.domain.vo.ElegantSupplier;
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
public class SupListReport extends BaseReport{

    private Collection<ElegantSupplier> list = new ArrayList<>();
    private int crDays;
    private double crLimit;

    public SupListReport(Collection<ElegantSupplier> c) {
        list.addAll(c);
    }

    public SupListReport(ArrayList<ElegantSupplier> c, Date frmDt, Date toDt, boolean active, boolean createBorder, int crDays, double crLimit, int rowHeight, int top, int bottom, int left, int right) {
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
       
        this.crDays = crDays;
        this.crLimit = crLimit;
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
        AbstractColumn columnCustId = createColumn("supID", Long.class, "ID Code", 5, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnName = createColumn("supName", String.class, "Supplier Name", 35, getHeaderTextStyle(), detailTextStyle);
        AbstractColumn columnCrLimit = createColumn("creditLimit", Double.class, "CR.Limit", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnPaymentTerms = createColumn("paymentTerms", Integer.class, "CR.Days", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnCreateDate = createColumn("createDate", Date.class, "Updated", 10, getHeaderTextStyle(), detailTextStyle);
        report.addColumn(columnCustId).addColumn(columnName).addColumn(columnCrLimit).addColumn(columnPaymentTerms).addColumn(columnCreateDate);

        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(14, Font._FONT_GEORGIA, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));

        String subTitle = "Created Between " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getFrmDate()) + " & " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getToDate() );
        subTitle += (isActive() ? " Active Suppliers" : "");
        subTitle += (crDays > 0 ? " Cr-Days>=" + Integer.toString(crDays) : "");
        subTitle += (crLimit > 0 ? " Cr-Limit>=" + Double.toString(crLimit) : "");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle(subTitle);
        report.setSubtitleStyle(subTitleStyle.build());

        report.addAutoText(CustUtil.APPNAME + " " + CustUtil.getReportDate(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 500, CustUtil.elegantStyle().build());
        report.addAutoText("Suppliers Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
        report.addAutoText(subTitle, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, subTitleStyle.build());

        columnCreateDate.setPattern(CustUtil.DISPLAYDATEFORMAT);
        columnPaymentTerms.setPattern(CustUtil.NUMBERFORMAT);
        columnCrLimit.setPattern(CustUtil.NUMBERFORMAT);

        report.setDetailHeight(getRowHeight());
        report.setMargins(getTopMargin(), getBottomMargin(), getLeftMargin(), getRightMargin());

        report.setUseFullPageWidth(true);

        return report.build();
    }
}
