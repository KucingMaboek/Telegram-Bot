package utils;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {

    private Twitter twitter;
    private ConfigurationBuilder cb;
    private ConfigurationBuilder config;
    private long[] followedUser;

    public void configTwitter() throws TwitterException {
        String apiKey = "ZryKkpHFnXHPSg5tYaWO3yLe7";
        String apiSecretKey = "6zf4m6S6wrWNj3pU1WPxTnqUZ8z5Tus20909SOGUBZT7jP9uso";
        String accessToken = "1254690739306430464-DiEsYPeHxXuIEZlu6yqYkDXIllLVop";
        String accessTokenSecret = "EGkHiBFXCmogtAEt0prhfxvj2HuGpkAMG8np4Tsq9UHk4";

        cb = new ConfigurationBuilder();
        config = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(apiKey)
                .setOAuthConsumerSecret(apiSecretKey)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        config.setDebugEnabled(true)
                .setOAuthConsumerKey(apiKey)
                .setOAuthConsumerSecret(apiSecretKey)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

        followedUser = new long[]{
                twitter.showUser("@bambangsupra2").getId(),
                twitter.showUser("@_pln_id").getId(),
                twitter.showUser("@pln_123").getId()
        };
        streamTwitter();
    }

    private void streamTwitter() {
        Bot bot = new Bot();
        TwitterStream twitterStream = new TwitterStreamFactory(config.build())
                .getInstance();

        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                try {
                    bot.broadcast(status.getText());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        FilterQuery filtre = new FilterQuery();
        filtre.follow(followedUser);
        twitterStream.filter(filtre);
    }
}
