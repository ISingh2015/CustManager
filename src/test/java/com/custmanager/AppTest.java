package com.custmanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Base64;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String [] args) {
        try {
            String str = "Soccer2015";
            byte[] encoded = Base64.getEncoder().encode(str.getBytes());
            System.out.println(new String(encoded));
            String s = new String(encoded);
            System.out.println(new String(Base64.getDecoder().decode(s.getBytes())));
//            long no = System.currentTimeMillis();
//            str = Long.toString(no);
//            encoded = Base64.getEncoder().encode(str.substring(7).getBytes());
//            System.out.println(no + " - " + new String(encoded));            
//            s = new String(encoded);            
//            System.out.println(new String(Base64.getDecoder().decode(s.getBytes())));            
//            String testString = "abcdefgh@..";
//            System.out.println(testString + "" + testString.toUpperCase());
//            System.out.println(testString + "" + testString.replaceAll("@",""));
//            InetAddress addr = InetAddress.getLocalHost();
//            System.out.println(addr.getHostAddress()+ "-" +addr.getHostName());
            String passwd = "aazEa4d9";
            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,10}";
            System.out.println(passwd.matches(pattern));
            String[] listToAddress = "inderjitsanhotra@gmail.com,isanhot@ra.rockwell.com,testemail".split(",[ ]*");
            System.out.println(listToAddress.length + " - " + listToAddress[0] + " - " + listToAddress[1]);
//            new AppTest().checkHosts("10.112.19");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void checkHosts(String subnet) throws Exception {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("arp -a");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
