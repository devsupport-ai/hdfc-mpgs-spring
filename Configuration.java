package nl.bldn.example.springboot.helloworld.payment;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
  static Map<String, Object> configuration;

  static {
    configuration = new HashMap<String, Object>();

    // The host of the Payment Gateway
    configuration.put("gatewayHost", "https://hdfcbank.test.gateway.mastercard.com");

    // Base URL of the Payment Gateway. Do not include the version.
    configuration.put("gatewayUrl", "https://hdfcbank.test.gateway.mastercard.com/api/rest");

    // If no authentication is required, only uncomment proxyServer
    // Server name or IP address and port number of your proxy server
    configuration.put("proxyServer", null);
    configuration.put("proxyPort", null);

    // Username and password for proxy server authentication
    configuration.put("proxyUsername", null);
    configuration.put("proxyPassword", null);

    // Proxy authentication type e.g. (NTLM, BASIC)
    configuration.put("proxyAuthType", null);

    //NT domain to authenticate in
    configuration.put("ntDomain", null);

    // The debug setting controls displaying the raw content of the request and
    // response for a transaction.
    // In production you should ensure this is set to FALSE as to not display/use
    // this debugging information
    configuration.put("debug", true);

    // Version number of the API being used for your integration
    // this is the default value if it isn't being specified in process.jsp
    configuration.put("version", "13");

    // Merchant ID supplied by your payments provider
    configuration.put("merchantId", "{{=it.merchantName}}");

    // API username in the format below where Merchant ID is the same as above
    configuration.put("apiUsername", "merchant.{{=it.merchantName}}");

    // API password which can be configured in Merchant Administration
    configuration.put("password", "{{=it.apiPassword}}");

    // [Snippet] howToConfigureSslCert - start
    // If using certificate validation, modify the following configuration settings

    // alternate trust store file
    // leave as null if you use default java trust store
    String trustStore = null;
    // trust store password
    String trustStorePassword = null;

    if (trustStore != null) {
      System.setProperty("javax.net.ssl.trustStore", trustStore);
      System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    }
    // [Snippet] howToConfigureSslCert - end

  }
}
