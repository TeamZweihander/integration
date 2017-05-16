package com.zweihander.navup.data.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.apache.log4j.BasicConfigurator;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.ws.Response;
import java.net.URL;



@Service
public class DataService {

	private String uname = "admin";
    private String pass = "Aruba123!";
    private WebClient webClient = null;
    private URL strURL;
    public Boolean isLoggedin = false ;
    
    public Boolean getLogIn(){
        return isLoggedin ;
    }
    
    public void login() throws Exception{
        BasicConfigurator.configure();
        webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        HtmlPage logPage = webClient.getPage("https://137.215.6.208");

        HtmlForm logForm = logPage.getHtmlElementById("login-form");
        HtmlSubmitInput log = logForm.getInputByValue("Log In");
        HtmlTextInput inUser = logForm.getInputByName("j_username");
        HtmlPasswordInput inPass = logForm.getInputByName("j_password");
        inUser.setValueAttribute(uname);
        inPass.setValueAttribute(pass);
        HtmlPage homePage = log.click();
        isLoggedin = true ;
    }
    
    public String getLocation(String Mac) throws Exception{
        //String address = "https://137.215.6.208/api/v1/building" ;

        String address = "https://137.215.6.208/api/v1/location?sta_eth_mac=" + Mac ;
    	strURL = new URL(address);
    	WebRequest _address = new WebRequest(strURL);
    	WebResponse location = webClient.loadWebResponse(_address);

    	System.out.println("-----------------------------");
    	System.out.println(location.getContentAsString());
        //System.out.println(sanitization(location.getContentAsString()));
    	return location.getContentAsString() ;
     }
    
    public void close(){
    	webClient.close();
    }

    public String sanitization(String json) {

        org.json.JSONObject cleanData = null;
        org.json.JSONObject dirtyData = null;
        String data = "";

        try {
            dirtyData = new org.json.JSONObject(json);

            double longitude = 0;
            double latitude = 0;
            double sta_x = 0 ;
            double sta_y = 0 ;

            //get the address
            JSONArray mainArray = dirtyData.getJSONArray("Location_result");
            org.json.JSONObject position = mainArray.getJSONObject(0);
            org.json.JSONObject location = position.getJSONObject("msg");
            org.json.JSONObject address = location.getJSONObject("sta_eth_mac");

            //getting the longitude,latitude and altitude of the device
            //longitude = location.getLong("longitude");
            //latitude = location.getLong("latitude");
            //altitude = location.getLong("altitude");
            sta_x = location.getDouble("sta_location_x");
            sta_y = location.getDouble("sta_location_y");


            //data += "{\n  \"Location\": {\n";
            //data += "      \"longitude\": \"" + longitude + "\",\n";
            //data += "  }\n}" ;

        } catch (Exception e) {
            System.out.println("JSON object error");
        }
        return json;
    }
     
}
