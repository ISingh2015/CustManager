/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import java.text.DecimalFormat;

/**
 *
 * @author Inderjit
 */
public class CustManagerUtil {

    public static String DECIMALFORMAT =  "###,###,###,##0.00";
    
    public static DecimalFormat getCustFormater () {
        return new DecimalFormat(DECIMALFORMAT);
    }
}
