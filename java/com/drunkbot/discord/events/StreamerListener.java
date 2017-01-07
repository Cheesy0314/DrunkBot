package com.drunkbot.discord.events;

import com.drunkbot.discord.connectors.WebRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.Map;

/**
 * Created by Dylan on 1/4/2017.
 */
public class StreamerListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        IGuild guild = messageReceivedEvent.getMessage().getGuild();
        IMessage message = messageReceivedEvent.getMessage();
        IChannel tc = message.getChannel();
        IUser author = message.getAuthor();

        if (message.getContent().toLowerCase().contains("--twitch")) {
            String user = message.getContent().substring(10);
            WebRequest request = new WebRequest();

            request.setHeader("Accept","application/vnd.twitchtv.v5+json");
            request.setHeader("Client-ID","xeqnbvxqztl3xihqnmfc4qkzjcmydc");
            try {
                String raw = request.doRequest("https://api.twitch.tv/kraken/channels/sodiumch1oride");
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> response = mapper.readValue(raw, Map.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
