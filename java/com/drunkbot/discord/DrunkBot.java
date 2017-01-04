package com.drunkbot.discord;



import com.drunkbot.discord.events.EventListener;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.util.DiscordException;

import java.util.Random;

public class DrunkBot {
    public static Random randomGenerator = new Random();
    public static final Logger logger = LogManager.getLogger(DrunkBot.class);
    private EventListener listener;
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


}
