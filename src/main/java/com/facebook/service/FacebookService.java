package com.facebook.service;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Service;

import com.facebook.model.AppDetails;
/**
 * @author viks
 * @version 1.0.0
 */
@Service
public class FacebookService {
	
	/**
	 * 
	 * @param appId
	 * @param appToken
	 * @return App details object
	 */
	public static AppDetails fetchAppDetail(String appId, String appToken) {
		Facebook facebook = new FacebookTemplate(appToken);
		return facebook.restOperations().getForObject(
				"https://graph.facebook.com/{appId}?fields=name,namespace,contact_email,website_url", AppDetails.class,
				appId);
	}
	/**
	 * 
	 * @param appId
	 * @param appSecret
	 * @return access token for oauth
	 */
	public static String fetchAppAccessToken(String appId,String appSecret){
		OAuth2Operations oauth=new FacebookConnectionFactory(appId, appSecret).getOAuthOperations();
		return oauth.authenticateClient().getAccessToken();
	}
}
