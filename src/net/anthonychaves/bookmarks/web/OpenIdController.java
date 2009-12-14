package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.beans.factory.annotation.*;

import org.openid4java.consumer.*;
import org.openid4java.message.*;
import org.openid4java.discovery.*;

import javax.servlet.http.*;

import java.util.List;

@Controller
@RequestMapping("/login")
public class OpenIdController {
  
  private String returnUrl;
  private String discoveryTarget;
  private ConsumerManager manager;

  @RequestMapping(method=RequestMethod.GET)
  public String discoverProvider(HttpSession session) throws Exception {
    List discoveries = manager.discover(discoveryTarget);
    DiscoveryInformation discovered = manager.associate(discoveries);
    session.setAttribute("discovered", discovered);
    AuthRequest authRequest = manager.authenticate(discovered, returnUrl);
    
    return "redirect:" + authRequest.getDestinationUrl(true);
  }
  
  @RequestMapping(value="/return")
  @ResponseBody
  public String openIdReturn(HttpServletRequest request, HttpSession session) {
    /**
    // extract the parameters from the authentication response
       // (which comes in as a HTTP request from the OpenID provider)
       ParameterList openidResp = new ParameterList(request.getParameterMap());

       // retrieve the previously stored discovery information
       DiscoveryInformation discovered = (DiscoveryInformation) session.getAttribute("discovered");

       // extract the receiving URL from the HTTP request
       StringBuffer receivingURL = request.getRequestURL();
       String queryString = request.getQueryString();
       if (queryString != null && queryString.length() > 0)
           receivingURL.append("?").append(request.getQueryString());

       // verify the response
       VerificationResult verification = _consumerManager.verify(receivingURL.toString(), openidResp, discovered);

       // examine the verification result and extract the verified identifier
       Identifier verified = verification.getVerifiedId();

       if (verified != null)
           // success, use the verified identifier to identify the user
       else
           // OpenID authentication failed
    */
    
    
    
    return "at least we got back here";
  }
  
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }
  
  public String getReturnUrl() {
    return returnUrl;
  }

  public void setDiscoveryTarget(String discoveryTarget) {
    this.discoveryTarget = discoveryTarget;
  }
  
  public String getDiscoveryTarget() {
    return discoveryTarget;
  }
}