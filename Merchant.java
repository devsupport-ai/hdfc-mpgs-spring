package nl.bldn.example.springboot.helloworld.payment;

import java.util.Map;

final class Merchant {
  private String gatewayHost;

  private String gatewayUrl;

  private String proxyServer;

  private Integer proxyPort;

  private String proxyUsername;

  private String proxyPassword;

  private String proxyAuthType;

  private String ntDomain;

  private boolean debug;

  private String version;

  private String merchantId;

  private String apiUsername;

  private String password;

  private String trustStorePath;

  private String trustStorePassword;

  Merchant(Map<String, Object> configuration) {
    gatewayHost = (String) configuration.get("gatewayHost");
    proxyServer = (String) configuration.get("proxyServer");
    proxyPort = (Integer) configuration.get("proxyPort");
    proxyUsername = (String) configuration.get("proxyUsername");
    proxyPassword = (String) configuration.get("proxyPassword");
    proxyAuthType = (String) configuration.get("proxyAuthType");
    ntDomain = (String) configuration.get("ntDomain");
    gatewayUrl = (String) configuration.get("gatewayUrl");
    debug = (Boolean) configuration.get("debug");
    version = (String) configuration.get("version");
    merchantId = (String) configuration.get("merchantId");
    password = (String) configuration.get("password");
    apiUsername = (String) configuration.get("apiUsername");
  }

  String getGatewayHost() {
    return gatewayHost;
  }

  String getNtDomain() {
    return ntDomain;
  }

  String getProxyServer() {
    return proxyServer;
  }

  String getProxyUsername() {
    return proxyUsername;
  }

  String getProxyPassword() {
    return proxyPassword;
  }

  String getProxyAuthType() {
    return proxyAuthType;
  }

  Boolean debugMode() {
    return debug;
  }

  Integer getProxyPort() {
    return proxyPort;
  }

  String getGatewayUrl() {
    return gatewayUrl;
  }

  void setGatewayUrl(String gatewayUrl) {
    this.gatewayUrl = gatewayUrl;
  }

  String getMerchantId() {
    return merchantId;
  }

  String getPassword() {
    return password;
  }

  String getVersion() {
    return version;
  }

  void setVersion(String version) {
    this.version = version;
  }

  String getApiUsername() {
    return apiUsername;
  }

}