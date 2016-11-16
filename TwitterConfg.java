package com.twitterBg;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfg {
	
//
//
//	ConfigurationBuilder cb = new ConfigurationBuilder();
//		cb.setDebugEnabled(true).setOAuthConsumerKey("I5f0jrKUTvwaXKku4OfpIsStj")
//			.setOAuthConsumerSecret("9iq7c5cmhnGxxXkxLUibFaFzNoj7zLsfmE7ljfFw4YuNut6tlP")
//			.setOAuthAccessToken("3298416996-0lnL5bxn80PcnvfEa0G4Xj5vxIMwhGmZns3UvrB")
//			.setOAuthAccessTokenSecret("ra56T1n8TNO2dLHL7DwCnvghkg87KdJMSBvhkUljEC7Mb");
//	TwitterFactory tf = new TwitterFactory(cb.build());
//	Twitter twitter = tf.getInstance();
//	try {
//		int pageno = 1;
//		Paging page = new Paging(pageno++, 100);
//		List<Status> statuses1 = twitter.getUserTimeline("abhiroopgoswami", page);
//		for (Status status1 : statuses1) {
//			// if(status1.getText().toLowerCase().contains("money")){
//			// System.out.println( status1.getText());
//			String tweet = status1.getText();
//			String pure = tweet.replaceAll("@\\p{L}+", "");
//			System.out.println(pure);
//			// }
//		}
//	} catch (Exception e) {
//		// TODO: handle exception
//	}
//
//
//
//}

}
