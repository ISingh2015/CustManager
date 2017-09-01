/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import com.cust.domain.vo.AddressXML;
import com.custmanager.util.CustUtil;

/**
 *
 * @author Inderjit
 */
public class XStreamTest {
    public static void main (String [] args ) {
        AddressXML addressXML = new AddressXML ("","","",0l,0l,"","",0l,0l,0l);
        String xml = CustUtil.getXStreamInstance().toXML(addressXML);
        System.out.println(xml);
    }
}
