/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cust.common.Pagination;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.vo.ElegantBuySell;
import com.cust.domain.webservice.BuySellWebService;
import java.util.ArrayList;

/**
 *
 * @author Inderjit
 */
public class BuySellWebServiceTest {

    public static void main(String[] args) {
        String webServiceUrl = "http://localhost/CustServiceWeb/remoting/BuySellService";
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        try {
            BuySellWebService buySellWebService = (BuySellWebService) proxyFactory.create(BuySellWebService.class, webServiceUrl);
            
            ServicePayload servicePayload;
            ServiceControl serviceControl = new ServiceControl();

            DBInfo dbInfo = new DBInfo();
            dbInfo.setDbName("custService");

            SessionInfo sessionInfo = new SessionInfo();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("Inderjit");
            sessionInfo.setUserInfo(userInfo);
            serviceControl.setDbInfo(dbInfo);
            serviceControl.setSessionInfo(sessionInfo);
            
            Pagination pagination = new Pagination ();
            pagination.setCurrrentPageNumber(1);
            pagination.setMaxPageSize(100);            
            serviceControl.setPagination(pagination);
            servicePayload = buySellWebService.getAllBuySell(serviceControl, 6000,true);
            ArrayList<ElegantBuySell> buySell= (ArrayList<ElegantBuySell>) servicePayload.getResponseValue().get(0);
            
            System.out.println("Got Data From HTTP Server " + buySell.get(0).getBillNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!!!!");
    }
}
