import java.net.URL;

/**
 * Created by siphokazi on 2017/05/04.
 */
public class Data {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        WebClient webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);  //bypass certificate validation
        HtmlPage page1 = webClient.getPage("https://137.215.6.208");
        HtmlForm form = page1.getHtmlElementById("login-form");
        //logging in
        HtmlSubmitInput submitButton = form.getInputByValue("Log In");
        HtmlTextInput textFieldEmail = form.getInputByName("j_username");
        textFieldEmail.setValueAttribute("admin");
        HtmlPasswordInput textFieldPass = form.getInputByName("j_password");
        textFieldPass.setValueAttribute("Aruba123!");
        HtmlPage dashboard = submitButton.click();

        //Creating request to query aruba for location
        URL url = new URL("https://137.215.6.208/api/v1/location?sta_eth_mac=" + macAddr);
        WebRequest locationReq = new WebRequest(url);
        WebResponse response = webClient.loadWebResponse(locationReq);

        System.out.println("=========================================================");
        System.out.println(response.getContentAsString());
        webClient.close();
        //return filterArubaReturn(response.getContentAsString());
        System.out.println("=========================================================");
    }
}
