package {{=it.packageName}}.payment;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;

final class Parser {
  private Merchant merchant;

  Parser(Merchant merchant) {
    this.merchant = merchant;
  }

  String formRequestUrl(HttpServletRequest request, String version) {
    String orderId = request.getParameter("orderId");
    String transactionId = request.getParameter("transactionId");
    StringBuilder url = new StringBuilder(merchant.getGatewayUrl());
    url.append("/version/");
    url.append(version);
    url.append("/merchant/");
    url.append(merchant.getMerchantId());
    url.append("/order/");
    url.append(orderId);
    url.append("/transaction/");
    url.append(transactionId);
    merchant.setGatewayUrl(url.toString());
    return merchant.getGatewayUrl();
  }
  // [Snippet] howToConfigureURL - end

  // [Snippet] howToConvertFormData - start
  // Convert parameter field value to JSON formmated value
  private String convertToJSonOrNull(String fieldValue) {
    if (fieldValue == null || fieldValue.trim().equals("")) {
      fieldValue = null;
    } else {
      fieldValue = "\"" + fieldValue + "\"";
    }
    return fieldValue;
  }

  // Creates the JSON encoded transaction body from http request parameters
  // Remember to make it check if the member is empty, assign null if it is
  String parse(HttpServletRequest request) {
    // Convert to JSON value if not empty or null.
    String cardSecurityCode = convertToJSonOrNull(request.getParameter("sourceOfFunds[provided][card][securityCode]"));
    String orderReference = convertToJSonOrNull(request.getParameter("order[reference]"));
    String sourceOfFundsType = convertToJSonOrNull(request.getParameter("sourceOfFunds[type]"));
    String cardNumber = convertToJSonOrNull(request.getParameter("sourceOfFunds[provided][card][number]"));
    String cardExpiryMonth = convertToJSonOrNull(request.getParameter("sourceOfFunds[provided][card][expiry][month]"));
    String cardExpiryYear = convertToJSonOrNull(request.getParameter("sourceOfFunds[provided][card][expiry][year]"));
    String transactionReference = convertToJSonOrNull(request.getParameter("transaction[reference]"));
    String customerIpAddress = convertToJSonOrNull(request.getParameter("customer[ipAddress]"));
    String transactionAmount = convertToJSonOrNull(request.getParameter("transaction[amount]"));
    String transactionCurrency = convertToJSonOrNull(request.getParameter("transaction[currency]"));
    String targetTransactionId = convertToJSonOrNull(request.getParameter("transaction[targetTransactionId]"));
    String apiOperation = convertToJSonOrNull(request.getParameter("apiOperation"));

    // Create JSON formatted data
    String data = MessageFormat
            .format("'{'\"apiOperation\":{0}," + "\"sourceOfFunds\":'{'\"type\":{1},\"provided\":'{'\"card\":'{'\"number\":{2}," + "\"expiry\":'{'\"month\":{3}, \"year\":{4}'}',\"securityCode\":{5}'}}}'," + "\"order\":'{'\"reference\":{6}'}'," + "\"transaction\":'{'\"amount\":{7},\"currency\":{8},\"reference\":{9},\"targetTransactionId\":{10}'}'," + "\"customer\":'{'\"ipAddress\":{11}'}}'",
                    apiOperation, sourceOfFundsType, cardNumber, cardExpiryMonth, cardExpiryYear, cardSecurityCode, orderReference, transactionAmount,
                    transactionCurrency, transactionReference, targetTransactionId, customerIpAddress);
    return data;
  }
  // [Snippet] howToConvertFormData - end

  String parseInitiate(HttpServletRequest request) {
    // Convert to JSON value if not empty or null.
    String orderReference = convertToJSonOrNull(request.getParameter("order[reference]"));
    String sourceOfFundsType = convertToJSonOrNull(request.getParameter("sourceOfFunds[type]"));
    String transactionReference = convertToJSonOrNull(request.getParameter("transaction[reference]"));
    String customerIpAddress = convertToJSonOrNull(request.getParameter("customer[ipAddress]"));
    String orderAmount = convertToJSonOrNull(request.getParameter("order[amount]"));
    String orderCurrency = convertToJSonOrNull(request.getParameter("order[currency]"));
    String apiOperation = convertToJSonOrNull(request.getParameter("apiOperation"));
    String returnUrl = convertToJSonOrNull(request.getParameter("browserPayment[returnUrl]"));
    String operation = convertToJSonOrNull(request.getParameter("browserPayment[paypal][operation]"));
    String paymentConfirmation = convertToJSonOrNull(request.getParameter("browserPayment[paypal][paymentConfirmation]"));
    String message = MessageFormat
            .format("'{'\"apiOperation\":{0},\"sourceOfFunds\":'{'\"type\":{1}'}'," + "\"browserPayment\":'{'\"returnUrl\":{2},\"paypal\":'{'\"operation\":{3},\"paymentConfirmation\":{4}'}}'" + ",\"order\":'{'\"reference\":{5},\"amount\":{6},\"currency\":{7}'}'" + ",\"transaction\":'{'\"reference\":{8}'}'" + ",\"customer\":'{'\"ipAddress\":{9}'}}'",
                    apiOperation, sourceOfFundsType, returnUrl, operation, paymentConfirmation, orderReference, orderAmount, orderCurrency,
                    transactionReference, customerIpAddress);
    return message;
  }

  String parseConfirm(HttpServletRequest request) {
    // Convert to JSON value if not empty or null.
    String orderReference = convertToJSonOrNull(request.getParameter("order[reference]"));
    String transactionReference = convertToJSonOrNull(request.getParameter("transaction[reference]"));
    String orderAmount = convertToJSonOrNull(request.getParameter("order[amount]"));
    String orderCurrency = convertToJSonOrNull(request.getParameter("order[currency]"));
    String apiOperation = convertToJSonOrNull(request.getParameter("apiOperation"));
    String message = MessageFormat
            .format("'{'\"apiOperation\":{0}" + ",\"order\":'{'\"reference\":{1},\"amount\":{2},\"currency\":{3}'}'" + ",\"transaction\":'{'\"reference\":{4}'}}'",
                    apiOperation, orderReference, orderAmount, orderCurrency, transactionReference);
    return message;
  }
}