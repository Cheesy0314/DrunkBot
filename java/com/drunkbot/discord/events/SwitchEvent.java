package com.drunkbot.discord.events;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.UserVoiceChannelJoinEvent;
import sx.blah.discord.handle.impl.events.UserVoiceChannelMoveEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * Created by Dylan on 1/7/2017.
 */


public class SwitchEvent implements IListener<UserVoiceChannelMoveEvent> {
    @Override
    public void handle(UserVoiceChannelMoveEvent userVoiceChannelJoinEvent) {
        IUser user = userVoiceChannelJoinEvent.getUser();
        IGuild guild = userVoiceChannelJoinEvent.getNewChannel().getGuild();
        IVoiceChannel voiceChannel = guild.getVoiceChannelByID("265994889676718082");
        try {


            if (userVoiceChannelJoinEvent.getNewChannel().equals(voiceChannel)) {
                guild.getChannelByID("221334865155457025").sendMessage("@everyone, " + user.getName() + " is looking for a group to play R6 with.");

            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
