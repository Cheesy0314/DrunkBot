package com.drunkbot.discord;
//import
/**
 * Created by Dylan on 11/23/2016.
 */


import com.drunkbot.discord.events.EventListener;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.util.Random;

public class DrunkBot {
    public static Random randomGenerator = new Random();
    public static final Logger logger = LogManager.getLogger(DrunkBot.class);
    private  EventListener listener;
    private IDiscordClient client;
    public DrunkBot (String token) {
        try {
            listener = new EventListener();
           client = makeClient(token, true);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getMessage(e));
        }
    }

    public IDiscordClient makeClient(String token, boolean login) throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);

        if (login) {
            return clientBuilder.login();
        } else {
            return clientBuilder.build();
        }
    }

    public EventListener getListener () {
        return listener;
    }

    public IDiscordClient getClient () {
        return client;
    }




    //TODO: Need to add in

//
//                        if (message.getContent().contains("SodiumCh1oride") || message.getMentions().get(0).getName().equalsIgnoreCase("SodiumCh1oride")) {
//                            message.reply("That dude is a fag");
//                        }
//
//                        if (message.getAuthor().getName().equalsIgnoreCase("sodiumch1oride")){
////                            int ran = randomGenerator.nextInt(100);
//
//                                int ran = randomGenerator.nextInt(100);
//                            switch (ran % 4) {
//                                case 0:
//                                    message.reply("Shut the fuck up gaylord!");
//                                    break;
//                                case 1:
//                                    message.reply("Yep yep yep... no one gives a shit Anthony");
//                                    break;
//                                case 2:
//                                    message.reply("Dude... really?");
//                                    break;
//                                case 3:
//                                    message.reply("DrunkBot needs you to take the dick out of your mouth dude");
//                                    break;
//                                    default:
//                                        message.reply("Fuck off butt pirate");

}
