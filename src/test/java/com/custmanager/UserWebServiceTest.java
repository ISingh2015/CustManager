/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.client.HessianRuntimeException;
import com.cust.common.ApplicationException;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.vo.ElegantUser;
import com.cust.domain.vo.ElegantUserLogin;
import com.cust.domain.webservice.UserWebService;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Inderjit
 */
public class UserWebServiceTest {

    public static void main(String[] args) {
        String webServiceUrl = "http://localhost:80/CustServiceWeb/remoting/UserService";
        ArrayList<ElegantUserLogin> elegantUserLoginList = new ArrayList<ElegantUserLogin>();
        ArrayList<ElegantUser> elegantUserList = new ArrayList<ElegantUser>();        
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        try {
            UserWebService userWebService = (UserWebService) proxyFactory.create(UserWebService.class, webServiceUrl);

            ServicePayload servicePayload;
            ServiceControl serviceControl = new ServiceControl();

            DBInfo dbInfo = new DBInfo();
            dbInfo.setDbName("custService");

            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.setSessionId(UUID.randomUUID().toString());
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("sa");
            userInfo.setLoginId("ISingh");
            sessionInfo.setUserInfo(userInfo);
            serviceControl.setDbInfo(dbInfo);
            serviceControl.setSessionInfo(sessionInfo);
            long compId = 6000, userId = 64002;
            ElegantUserLogin user = new ElegantUserLogin();
            user.setCompID(compId);
            user.setUserID(userId);
            user.setLoginIP("192.168.0.10");
            user.setLoginDate(new Date());
            elegantUserLoginList.add(user);
            elegantUserLoginList = (ArrayList<ElegantUserLogin>) userWebService.saveUserLoginList(serviceControl, elegantUserLoginList).getResponseValue().get(0);
//            elegantUserList = (ArrayList<ElegantUser>) userWebService.getAllUsers(serviceControl, compId).getResponseValue().get(0);            
            System.out.println("User Logins found in List" + elegantUserLoginList.get(0).getUserID());
            System.out.println("User found in List" + elegantUserList.size());
        } catch (MalformedURLException e) {
            System.out.println("URL  Execption :" + e.getMessage());
        } catch (HessianRuntimeException e) {
            System.out.println("Connection Execption :" + e.getMessage());
        } catch (ApplicationException e) {
            System.out.println("Application Execption :" + e.getMessage());
        }

        System.out.println("Done!!!!");
    }
}
