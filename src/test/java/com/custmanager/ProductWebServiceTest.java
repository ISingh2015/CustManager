/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.webservice.ProductWebService;

/**
 *
 * @author Inderjit
 */
public class ProductWebServiceTest {

    public static void main(String[] args) {
        String webServiceUrl = "http://localhost/CustServiceWeb/remoting/ProductService";
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        try {
            ProductWebService productWebService = (ProductWebService) proxyFactory.create(ProductWebService.class, webServiceUrl);
            
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
            
            servicePayload = productWebService.getProductStockById(serviceControl, 6000,36,7);
            Double buySellStock= (Double) servicePayload.getResponseValue().get(0);
            
            System.out.println("Got Data From HTTP Server " + buySellStock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!!!!");
    }
}
