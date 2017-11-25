package com.facebook;

import javax.swing.JOptionPane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;

import com.fasterxml.jackson.annotation.JsonProperty;

@SpringBootApplication
@ComponentScan("com.facebook")
public class SpringBootMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcApplication.class, args);
		/*String appId=promptForInput("Enter your app Id");
		String appSecret=promptForInput("Enter your app Secret");
		String appToken=fetchAppAccessToken(appId, appSecret);
		AppData appdetails=fetchAppDetail(appId,appToken);
		
		System.out.println("---------Application Details-----------");
		System.out.println("ID "+appdetails.getId());
		System.out.println("Name: "+appdetails.getName());
		System.out.println("Namespace: "+appdetails.getNamespace());
		System.out.println("Contact Email: "+appdetails.getContactEmail());
		System.out.println("Website Url: "+appdetails.getWebsiteURL());*/
	}

	private static AppData fetchAppDetail(String appId, String appToken) {
		Facebook facebook = new FacebookTemplate(appToken);
		return facebook.restOperations().getForObject(
				"https://graph.facebook.com/{appId}?fields=name,namespace,contact_email,website_url", AppData.class,
				appId);
	}
	
	private static String fetchAppAccessToken(String appId,String appSecret){
		OAuth2Operations oauth=new FacebookConnectionFactory(appId, appSecret).getOAuthOperations();
		return oauth.authenticateClient().getAccessToken();
	}
	
	private static String promptForInput(String promptText){
		return JOptionPane.showInputDialog(promptText+" ");
	}

	private static final class AppData {
		private long id;
		private String name;
		private String namespace;

		@JsonProperty("contact_email")
		private String contactEmail;

		@JsonProperty("website_url")
		private String websiteURL;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNamespace() {
			return namespace;
		}

		public void setNamespace(String namespace) {
			this.namespace = namespace;
		}

		public String getContactEmail() {
			return contactEmail;
		}

		public void setContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
		}

		public String getWebsiteURL() {
			return websiteURL;
		}

		public void setWebsiteURL(String websiteURL) {
			this.websiteURL = websiteURL;
		}

	}
}
