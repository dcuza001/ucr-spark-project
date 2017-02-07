/**
 * Created by juhel on 25/01/2017.
 */
import static spark.Spark.*;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;



public class Main {
    public static void main(String[] args) {
        //get("/hello", (req, res) -> "Hello World");


        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("******************************")
                    .setOAuthConsumerSecret("**********************************************")
                    .setOAuthAccessToken("**************************************************")
                    .setOAuthAccessTokenSecret("*********************************************");
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            ResponseList<Location> locations;

            System.out.println("Obtaining available trends");
            locations = twitter.getAvailableTrends();
            for (Location location : locations) {
                System.out.println(location.getName() + " (woeid:" + location.getWoeid() + ")");
            }

            Trends trends = twitter.getPlaceTrends(1);
            System.out.println("Showing trends worldwide with WOEID = 1");
            System.out.println("As of : " + trends.getAsOf());
            for (Trend trend : trends.getTrends()) {
                System.out.println(" " + trend.getName());
            }
            System.out.println("done.");
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
            System.exit(-1);
        }
    }
}
