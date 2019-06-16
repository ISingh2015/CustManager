package com.custmanager;

import java.text.DecimalFormat;

/**
 *
 * @author Inderjit SS
 * @version 1.0.0
 * @since 01.10.2016
 */
public class CustManagerUtil {

    public static String DECIMALFORMAT =  "###,###,###,##0.00";
    
    public static DecimalFormat getCustFormater () {
        return new DecimalFormat(DECIMALFORMAT);
    }
}
