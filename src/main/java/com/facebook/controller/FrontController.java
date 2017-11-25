package com.facebook.controller;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.facebook.model.AppDetails;
import com.facebook.service.FacebookService;

@Controller
public class FrontController {
	
	@Inject
	FacebookService appDetailService;
	
	private static final Logger logger=Logger.getLogger(FrontController.class);
	
	@RequestMapping(value="/facebookInfo", method=RequestMethod.POST)
	public String getFacebookData(Model  model,@RequestParam(value="appId")String appId,@RequestParam(value="secret") String secret){
		logger.info("In getFacebookData function");
		String appToken=FacebookService.fetchAppAccessToken(appId,secret);
		AppDetails appdetails=FacebookService.fetchAppDetail(appId, appToken);
		logger.info("@@@App ID"+appId);
		logger.info("@@@Secret key "+secret);
		model.addAttribute("Id",appdetails.getId());
		model.addAttribute("Name",appdetails.getName());
		model.addAttribute("NameSpace",appdetails.getNamespace());
		model.addAttribute("WebSite URL",appdetails.getWebsiteURL());
		return "facebookInfo";
	}
}