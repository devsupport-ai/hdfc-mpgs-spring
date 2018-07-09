package {{=it.packageName}}.payment;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping ("payment")
public class PaymentController {
  @ResponseBody
  @RequestMapping (path = "process", method = RequestMethod.POST)
  public String processPayment(HttpServletRequest request) throws Exception {
    Merchant merchant = new Merchant(Configuration.configuration);
    Parser parser = new Parser(merchant);
    String method = request.getParameter("method");

    String requestUrl = parser.formRequestUrl(request, (String) Configuration.configuration.get("version"));
    if (merchant.debugMode()) {
      System.out.println("Request url: " + requestUrl);
    }
    Connection connection = new Connection(merchant);

    String data = "";
    String apiOperation = request.getParameter("apiOperation");

    if (apiOperation == null) {
      return "invalid api operation";
    }

    data = parser.parse(request);
    if (merchant.debugMode()) {
      System.out.println("Data: " + data);
    }

    String resp = "";

    if ("RETRIEVE_TRANSACTION".equalsIgnoreCase(apiOperation)) {
      resp = connection.getTransaction();
    } else {
      resp = connection.sendTransaction(data);
    }
    return resp;
  }

  @RequestMapping (path = "get_payment", method = RequestMethod.POST)
  public String getPaymentDetails(HttpServletRequest request) throws Exception {
    Merchant merchant = new Merchant(Configuration.configuration);
    Parser parser = new Parser(merchant);
    String method = request.getParameter("method");

    String requestUrl = parser.formRequestUrl(request, (String) Configuration.configuration.get("version"));
    if (merchant.debugMode()) {
      System.out.println("Request url: " + requestUrl);
    }
    Connection connection = new Connection(merchant);

    String data = "";
    String apiOperation = request.getParameter("apiOperation");

    if (apiOperation == null) {
      return "invalid api operation";
    }

    data = parser.parse(request);
    if (merchant.debugMode()) {
      System.out.println("Data: " + data);
    }

    String resp = "";

    resp = connection.getTransaction();

    return resp;
  }

}