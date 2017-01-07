package com.drunkbot.discord.events;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.UserJoinEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Dylan on 1/6/2017.
 */
public class JoinListener implements IListener<UserJoinEvent> {
    @Override
    public void handle(UserJoinEvent userJoinEvent) {
        IUser newUser = userJoinEvent.getUser();
        IGuild guild = userJoinEvent.getGuild();

        try {
                    guild.getChannelByID("221334865155457025").sendMessage(newUser.getName() + ", welcome to " + guild.getName() + " discord server. Please take a few moments to read the rules.").addReaction(guild.getEmojiByName("gitgud"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
