/**
  Copyright 2010 Anthony Chaves
  
  This file is part of Bookmarks.

  Bookmarks is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  Bookmarks is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with Bookmarks.  If not, see <http://www.gnu.org/licenses/>.
*/

package net.anthonychaves.bookmarks.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.beans.factory.annotation.*;

import org.openid4java.consumer.*;
import org.openid4java.message.*;
import org.openid4java.discovery.*;
import org.openid4java.message.ax.*;

import javax.servlet.http.*;

import java.util.List;

import net.anthonychaves.bookmarks.models.*;
import net.anthonychaves.bookmarks.service.*;

@Controller
@RequestMapping("/login")
public class OpenIdController {
  
  private String returnUrl;
  private String discoveryTarget;
  
  @Autowired
  private ConsumerManager manager;
  
  @Autowired
  private UserService userService;

  @RequestMapping(method=RequestMethod.GET)
  public String discoverProvider(HttpSession session) throws Exception {
    List discoveries = manager.discover(discoveryTarget);
    DiscoveryInformation discovered = manager.associate(discoveries);
    session.setAttribute("discovered", discovered);
    AuthRequest authRequest = manager.authenticate(discovered, returnUrl);
    
    FetchRequest fetch = FetchRequest.createFetchRequest();
    fetch.addAttribute("email", "http://axschema.org/contact/email", true);
    fetch.addAttribute("firstName", "http://axschema.org/namePerson/first", true);
    fetch.addAttribute("lastName", "http://axschema.org/namePerson/last", true);
    authRequest.addExtension(fetch);
    
    return "redirect:" + authRequest.getDestinationUrl(true);
  }
  
  @RequestMapping(value="/return")
  public String openIdReturn(HttpServletRequest request, HttpSession session) {
    // extract the parameters from the authentication response
    // (which comes in as a HTTP request from the OpenID provider)
    ParameterList openidResp = new ParameterList(request.getParameterMap());

    // retrieve the previously stored discovery information
    DiscoveryInformation discovered = (DiscoveryInformation) session.getAttribute("discovered");

    // extract the receiving URL from the HTTP request
    StringBuffer receivingURL = request.getRequestURL();
    String queryString = request.getQueryString();
    if (queryString != null && queryString.length() > 0) {
      receivingURL.append("?").append(request.getQueryString());
    }
    
    // verify the response
    VerificationResult verification = null;
    try {
      verification = manager.verify(receivingURL.toString(), openidResp, discovered);

      // examine the verification result and extract the verified identifier
      Identifier verified = verification.getVerifiedId();

      if (verified != null) {
        // success, use the verified identifier to identify the user
        String[] attributes = inspectOpenIdMessage(verification);
        User user = userService.findUser(attributes[0]);

        if (user == null) {
          user = new User();
          
          user.setEmailAddress(attributes[0]);
          user.setFirstName(attributes[1]);
          user.setLastName(attributes[2]);
          
          user = userService.createUser(user);
        }

        session.setAttribute("user", user);
        return "redirect:/b/user";
      } else {
        // OpenID authentication failed
        return "redirect:user_new";
      }
    } catch (Exception e) {
      throw new RuntimeException(e);      
    }
  }
  
  private String[] inspectOpenIdMessage(VerificationResult verification) throws MessageException {
    AuthSuccess success = (AuthSuccess) verification.getAuthResponse();
    FetchResponse response = (FetchResponse) success.getExtension(AxMessage.OPENID_NS_AX);
    String emailAddress = response.getAttributeValue("email");
    String firstName = response.getAttributeValue("firstName");
    String lastName = response.getAttributeValue("lastName");
    
    return new String[] {emailAddress, firstName, lastName};
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
  
  public void setConsumerManager(ConsumerManager manager) {
    this.manager = manager;
  }
  
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}