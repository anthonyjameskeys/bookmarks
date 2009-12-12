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
  
  private String returnUrl; // = "http://76.24.64.156:8080/bookmarks/b/login/return";
  private String discoveryTarget; // = "https://www.google.com/accounts/o8/id";

  @RequestMapping(method=RequestMethod.GET)
  public String discoverProvider(HttpSession session) throws Exception {
    ConsumerManager manager = new ConsumerManager();
    List discoveries = manager.discover(discoveryTarget);
    DiscoveryInformation discovered = manager.associate(discoveries);
    session.setAttribute("discovered", discovered);
    AuthRequest authRequest = manager.authenticate(discovered, returnUrl);
    
    return "redirect:" + authRequest.getDestinationUrl(true);
  }
  
  @RequestMapping(value="/return")
  @ResponseBody
  public String openIdReturn(HttpServletRequest request, HttpSession session) {
    
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