package com.redbee.challenge.socialnetworks.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.redbee.challenge.socialnetworks.SocialNetworkListener;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamListener;
import twitter4j.conf.ConfigurationBuilder;

@Service("socialNetworkListener")
public class TwitterListenerImpl implements SocialNetworkListener {
	
	@Value( "${socialnetwork.twitter.consumer.key}" )
	private String consumerKey;
	
	@Value( "${socialnetwork.twitter.consumer.secret}" )
	private String consumerSecret;
	
	@Value( "${socialnetwork.twitter.access.token}" )
	private String accessToken;
	
	@Value( "${socialnetwork.twitter.access.token.secret}" )
	private String accessTokenSecret;
	
	@Autowired
	private UserStreamListener listener;
	
	private TwitterStream twitterStream = null;
	
	private void init() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(consumerKey)
		.setOAuthConsumerSecret(consumerSecret)
		.setOAuthAccessToken(accessToken)
		.setOAuthAccessTokenSecret(accessTokenSecret);

		twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		twitterStream.addListener(listener);
	}
	
	/**
	 * Start listening a specific user
	 * 
	 * @param user
	 */
	public void listeningUser (String user) {
		if (twitterStream == null) {
			init();
		}
		
		twitterStream.user(user);
	}

}
