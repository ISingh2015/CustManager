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
import com.cust.domain.vo.ElegantCountry;
import com.cust.domain.webservice.CountryWebService;
import java.util.ArrayList;

/**
 *
 * @author Inderjit
 */
public class CountryWebServiceTest {

    public static void main(String[] args) {
        String webServiceUrl = "http://localhost:8888/CustAppWebService/remoting/CountryService";
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        try {
            CountryWebService countryWebService = (CountryWebService) proxyFactory.create(CountryWebService.class, webServiceUrl);
            
            ServicePayload servicePayload;
            ServiceControl serviceControl = new ServiceControl();

            DBInfo dbInfo = new DBInfo();
            dbInfo.setDbName("custService");

            SessionInfo sessionInfo = new SessionInfo();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("sa");
            sessionInfo.setUserInfo(userInfo);
            serviceControl.setDbInfo(dbInfo);
            serviceControl.setSessionInfo(sessionInfo);
            
            Pagination pagination = new Pagination ();
            pagination.setMaxPageSize(50);
            pagination.setCurrrentPageNumber(1);
            
            serviceControl.setPagination(pagination);
            servicePayload = countryWebService.getAllCountries(serviceControl);
            ArrayList<ElegantCountry> countryList= (ArrayList<ElegantCountry>) servicePayload.getResponseValue().get(0);
            
            System.out.println("Got Data From HTTP Server " + countryList.size());
            for (ElegantCountry country : countryList) {
                System.out.println("Country Name : " + country.getCountryName());
            }
            servicePayload = countryWebService.saveCountryList(serviceControl, countryList);
            countryList= (ArrayList<ElegantCountry>) servicePayload.getResponseValue().get(0);
            System.out.println("Got Data From HTTP Server " + countryList.size());
            for (ElegantCountry country : countryList) {
                System.out.println("Saved Country Name : " + country.getCountryName());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!!!!");
    }
}
