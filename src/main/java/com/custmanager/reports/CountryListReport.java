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
import com.cust.domain.vo.ElegantCountry;
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
public class CountryListReport extends BaseReport{

    private Collection<ElegantCountry> list = new ArrayList<>();

    public CountryListReport(Collection<ElegantCountry> c) {
        list.addAll(c);
    }

    public CountryListReport(ArrayList<ElegantCountry> c, Date frmDt, Date toDt, boolean active, boolean createBorder, int  rowHeight, int top, int bottom, int left, int right) {
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
        AbstractColumn columnCountryId = createColumn("countryID", Long.class, "ID Code", 5, getHeaderNumberStyle(), detailNumStyle);
        AbstractColumn columnName = createColumn("countryName", String.class, "Name", 45, getHeaderTextStyle(), detailTextStyle);
        AbstractColumn columnCurrency = createColumn("currency", String.class, "Currency", 5, getHeaderTextStyle(), detailTextStyle);
        AbstractColumn columnCountryCd = createColumn("countryCd", String.class, "Country CD", 5, getHeaderTextStyle(), detailTextStyle);
        AbstractColumn columnExchRate = createColumn("exchangeRate", Double.class, "Exch.Rate", 5, getHeaderNumberStyle(), detailNumStyle);
        report.addColumn(columnCountryId).addColumn(columnName).addColumn(columnCountryCd).addColumn(columnCurrency).addColumn(columnExchRate);

        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(14, Font._FONT_GEORGIA, true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));

        String subTitle = "Created Between " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getFrmDate()) + " & " + new SimpleDateFormat(CustUtil.DISPLAYDATEFORMAT).format(getToDate());
        subTitle += (isActive() ? " Active Users" : "");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitleStyle(subTitleStyle.build());

        report.addAutoText(CustUtil.APPNAME + " " + CustUtil.getReportDate(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 500, CustUtil.elegantStyle().build());
        report.addAutoText("Country Listing Report", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, titleStyle.build());
        report.addAutoText(subTitle, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER, 500, subTitleStyle.build());
        report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_CENTER, 50, 50);
        report.setUseFullPageWidth(true);
        report.setDetailHeight(getRowHeight());
        report.setMargins(getTopMargin(), getBottomMargin(), getLeftMargin(), getRightMargin());

//        Page page = new Page(800,600,false);
//        report.setPageSizeAndOrientation(page);
        return report.build();
    }
}
