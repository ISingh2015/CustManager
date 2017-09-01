/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cust.common.FilterCriteria;
import com.cust.common.QueryCriteria;
import com.cust.common.ServiceControl;
import com.cust.common.ServicePayload;
import com.cust.common.DBInfo;
import com.cust.common.SessionInfo;
import com.cust.common.UserInfo;
import com.cust.domain.vo.PreferenceVO;
import com.cust.domain.webservice.PreferenceWebService;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Inderjit
 */
public class CustomerWebServiceTest {

    public static void main(String[] args) {
        String webServiceUrl = "http://localhost:80/CustServiceWeb/remoting/PreferenceService";
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        try {
            PreferenceWebService customerWebService = (PreferenceWebService) proxyFactory.create(PreferenceWebService.class, webServiceUrl);

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

            QueryCriteria queryCriteria = new QueryCriteria();
            ArrayList<FilterCriteria> filterCriteriaList = new ArrayList<FilterCriteria>();
            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria = new FilterCriteria();
            filterCriteria.setFilterFieldName("webSite");
            filterCriteria.setFilterCondition("=");
            filterCriteria.setFilterFieldValue("Test Website190");
            filterCriteriaList.add(filterCriteria);
            queryCriteria.setFilterCriteria(filterCriteriaList);

            serviceControl.setQueryCriteria(queryCriteria);

            servicePayload = customerWebService.getServiceNames(serviceControl, 6000);
            ArrayList<PreferenceVO> prefList = (ArrayList<PreferenceVO>) servicePayload.getResponseValue().get(0);

            System.out.println("Got Data From HTTP Server " + prefList.size());
            for (PreferenceVO preferenceVO : prefList) {

                System.out.println("Peference Found : " + preferenceVO.getPreference().size());

                Iterator iterator = preferenceVO.getPreference().keySet().iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    String[] value = preferenceVO.getPreference().get(key);
                    System.out.println("Key " + key + " value " + value[0]);

                }

                System.out.println("Constants Found : " + preferenceVO.getConstants().size());

                iterator = preferenceVO.getConstants().keySet().iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    String[] value = preferenceVO.getConstants().get(key);
                    System.out.println("Key " + key + " value " + value[0]);
                }

            }
//            if (customerList != null) Collections.sort(customerList);
//            servicePayload = customerWebService.saveCustomerList(serviceControl, customerList);
//            customerList= (ArrayList<ElegantCustomer>) servicePayload.getResponseValue().get(0);
//            System.out.println("Got Data From HTTP Server " + customerList.size());
//            for (ElegantCustomer customer : customerList) {
//                System.out.println("Saved Customer Name : " + customer.getCustName());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!!!!");
    }
}
