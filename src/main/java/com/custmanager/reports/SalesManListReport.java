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
import com.cust.domain.vo.ElegantSalesMan;
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
public class SalesManListReport extends BaseReport{

    private Collection<ElegantSalesMan> list = new ArrayList<>();
    private int displayQuota;

    public SalesManListReport(Collection<ElegantSalesMan> c) {
        list.addAll(c);
    }

    public SalesManListReport(ArrayList<ElegantSalesMan> c, Date frmDt, Date toDt, boolean active, boolean createBorder, int displayQuota, int rowHeight, int top, int bottom, int left, int right) {
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
        this.displayQuota = displayQuota;
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
        AbstractColumn columnCustId = createColumn("salesManID", Number.class, "ID Code", 5, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnName = createColumn("salesManName", String.class, "Name", 35, getHeaderTextStyle(), detailTextStyle);
        AbstractColumn columnManagerId = createColumn("managerId", Number.class, "Manager", 5, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnCreateDate = createColumn("createDate", Date.class, "Created", 5, getHeaderTextStyle() , detailTextStyle);
        AbstractColumn columnStatus = createColumn("frozen", Number.class, "Status", 5, getHeaderTextStyle(), detailNumStyle);
        AbstractColumn columnQuota1 = createColumn("firstQtr", Number.class, "1st QTR", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnQuota2 = createColumn("secondQtr", Double.class, "2nd QTR", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnQuota3 = createColumn("thirdQtr", Double.class, "3rd QTR", 10, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnQuota4 = createColumn("fourthQtr", Double.class, "4th QTR", 10, getHeaderNumberStyle(), detailNumStyle);
        columnCreateDate.setPattern(CustUtil.DISPLAYDATEFORMAT);
        if (this.displayQuota == 1) {
            report.addColumn(columnCustId).addColumn(columnName).addColumn(columnManagerId).addColumn(columnQuota1).addColumn(columnQuota2).addColumn(columnQuota3).addColumn(columnQuota4);
        } else {
            report.addColumn(columnCustId).addColumn(columnName).addColumn(columnManagerId).addColumn(columnCreateDate).addColumn(columnStatus);
        }
        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(14, Font._FONT_GEORGIA, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));

        String subTitle = "Created Between " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getFrmDate()) + " & " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getToDate());
        subTitle += (isActive() ? " Active Sales Man" : "");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitleStyle(subTitleStyle.build());

        report.addAutoText(CustUtil.APPNAME + " " + CustUtil.getReportDate(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 500, CustUtil.elegantStyle().build());
        report.addAutoText("Sales Man Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
        report.addAutoText(subTitle, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, subTitleStyle.build());
        report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_CENTER, 50, 50);
        report.setDetailHeight(getRowHeight());
        report.setMargins(getTopMargin(), getBottomMargin(), getLeftMargin(), getRightMargin());

        report.setUseFullPageWidth(true);

        return report.build();
    }
}
