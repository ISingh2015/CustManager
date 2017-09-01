/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager.reports;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.awt.Color;
import java.util.Date;

/**
 *
 * @author Inderjit Singh Sanhotra
 */
public class BaseReport {

    private Date frmDate, toDate;
    private boolean active, createBorder;
    private int rowHeight, topMargin, bottomMargin, leftMargin, rightMargin;
    private Style headerTextStyle, headerNumberStyle;

    public BaseReport() {
        headerNumberStyle = createHeaderNumberStyle();
        headerTextStyle = createHeaderTextStyle();
    }

    private Style createHeaderTextStyle() {
        StyleBuilder sb = new StyleBuilder(true);
        sb.setFont(Font.ARIAL_SMALL);
        if (this.isCreateBorder()) {
            sb.setBorder(Border.THIN());
            sb.setBorderColor(Color.BLACK);
        }
        sb.setBorderBottom(Border.PEN_2_POINT());

        sb.setBackgroundColor(Color.lightGray);
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.LEFT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setTransparency(Transparency.OPAQUE);
        sb.setPaddingRight(5);
        sb.setPaddingLeft(5);

        return sb.build();
    }

    private Style createHeaderNumberStyle() {
        StyleBuilder sb = new StyleBuilder(true);
        sb.setFont(Font.ARIAL_SMALL);
        if (this.isCreateBorder()) {
            sb.setBorder(Border.THIN());
            sb.setBorderColor(Color.BLACK);
        }
        sb.setBorderBottom(Border.PEN_2_POINT());

        sb.setBackgroundColor(Color.lightGray);
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.RIGHT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setTransparency(Transparency.OPAQUE);
        sb.setPaddingRight(5);
        sb.setPaddingLeft(5);

        return sb.build();
    }

    public Style createDetailTextStyle() {
        StyleBuilder sb = new StyleBuilder(true);
        sb.setFont(Font.ARIAL_SMALL);
        if (isCreateBorder()) {
            sb.setBorder(Border.DOTTED());
            sb.setBorderColor(Color.BLACK);
        }
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.LEFT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingRight(5);
        sb.setPaddingLeft(5);
        sb.setStretchWithOverflow(true);
        return sb.build();
    }

    public Style createDetailNumberStyle() {
        StyleBuilder sb = new StyleBuilder(true);
        sb.setFont(Font.ARIAL_SMALL);
        if (isCreateBorder()) {
            sb.setBorder(Border.DOTTED());
            sb.setBorderColor(Color.BLACK);
        }
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.RIGHT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingLeft(5);
        sb.setPaddingRight(5);
        return sb.build();
    }

    public AbstractColumn createColumn(String property, Class type,
            String title, int width, Style headerStyle, Style detailStyle)
            throws ColumnBuilderException {
        AbstractColumn columnState = ColumnBuilder.getNew()
                .setColumnProperty(property, type.getName()).setTitle(
                        title).setWidth(Integer.valueOf(width))
                .setStyle(detailStyle).setHeaderStyle(headerStyle).build();
        return columnState;
    }

    public AbstractColumn createColumn(String property, Class type,
            String title, int width, Style headerStyle, Style detailStyle, CustomExpression customExpression)
            throws ColumnBuilderException {
        AbstractColumn columnState = ColumnBuilder.getNew()
                .setColumnProperty(property, type.getName()).setTitle(
                        title).setWidth(Integer.valueOf(width))
                .setStyle(detailStyle).setHeaderStyle(headerStyle).setCustomExpression(customExpression).build();

        return columnState;
    }

    /**
     * @return the frmDate
     */
    public Date getFrmDate() {
        return frmDate;
    }

    /**
     * @param frmDate the frmDate to set
     */
    public void setFrmDate(Date frmDate) {
        this.frmDate = frmDate;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the createBorder
     */
    public boolean isCreateBorder() {
        return createBorder;
    }

    /**
     * @param createBorder the createBorder to set
     */
    public void setCreateBorder(boolean createBorder) {
        this.createBorder = createBorder;
    }

    /**
     * @return the rowHeight
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * @param rowHeight the rowHeight to set
     */
    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    /**
     * @return the topMargin
     */
    public int getTopMargin() {
        return topMargin;
    }

    /**
     * @param topMargin the topMargin to set
     */
    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    /**
     * @return the bottomMargin
     */
    public int getBottomMargin() {
        return bottomMargin;
    }

    /**
     * @param bottomMargin the bottomMargin to set
     */
    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    /**
     * @return the leftMargin
     */
    public int getLeftMargin() {
        return leftMargin;
    }

    /**
     * @param leftMargin the leftMargin to set
     */
    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    /**
     * @return the rightMargin
     */
    public int getRightMargin() {
        return rightMargin;
    }

    /**
     * @param rightMargin the rightMargin to set
     */
    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    /**
     * @return the headerTextStyle
     */
    public Style getHeaderTextStyle() {
        return headerTextStyle;
    }

    /**
     * @param headerTextStyle the headerTextStyle to set
     */
    public void setHeaderTextStyle(Style headerTextStyle) {
        this.headerTextStyle = headerTextStyle;
    }

    /**
     * @return the headerNumberStyle
     */
    public Style getHeaderNumberStyle() {
        return headerNumberStyle;
    }

    /**
     * @param headerNumberStyle the headerNumberStyle to set
     */
    public void setHeaderNumberStyle(Style headerNumberStyle) {
        this.headerNumberStyle = headerNumberStyle;
    }

}
