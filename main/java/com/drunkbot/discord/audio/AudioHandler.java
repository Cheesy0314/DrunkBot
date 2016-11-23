package com.drunkbot.discord.audio;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.audio.AudioPlayer;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by dylan on 11/23/16.
 */
public class AudioHandler {
    private static final String botprefix = "";
    @EventSubscriber
    public void OnMesageEvent(MessageReceivedEvent event) throws IOException, UnsupportedAudioFileException, RateLimitException, MissingPermissionsException, DiscordException {
        IMessage message = event.getMessage(); // Get message from event

        if(message.getContent().startsWith(botprefix)){
            String command = message.getContent().replaceFirst(botprefix, ""); // Remove prefix
            String[] args = command.split(" "); // Split command into arguments

            if(args[0].equalsIgnoreCase("summon")){
                // Get the user's voice channel
                IVoiceChannel voicechannel = message.getAuthor().getConnectedVoiceChannels().get(0);
                // Join the channel
                voicechannel.join();
                // Send message
                message.getChannel().sendMessage("Joined `" + voicechannel.getName() + "`.");
            } else if(args[0].equalsIgnoreCase("playfile")){
                // Queue up test file from local path
                playAudioFromFile("jc.mp3", message.getGuild());
                // Send message
                message.getChannel().sendMessage("Queued test file.");

            // Check for "playurl" as command
            } else if(args[0].equalsIgnoreCase("playurl")){
                // Queue up test file from URL
                playAudioFromUrl("http://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3", message.getGuild());
                // Send message
                message.getChannel().sendMessage("Queued test URL.");
            } else if(args[0].equalsIgnoreCase("setvol")){
                // Read first argument as float value
                float vol = Float.parseFloat(args[1]);
                // Set volume for guild
                setVolume(vol, message.getGuild());
                // Send message
                message.getChannel().sendMessage("Set volume to " + vol + ".");
            }
        }
    }


    private static void playAudioFromUrl(String s_url, IGuild guild) throws IOException, UnsupportedAudioFileException {
        URL url = new URL(s_url); // Get URL
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild); // Get AudioPlayer for guild
        player.queue(url); // Queue URL stream
    }

    // Queue audio from specified file for guild
    private static void playAudioFromFile(String s_file, IGuild guild) throws IOException, UnsupportedAudioFileException {
        File file = new File(s_file); // Get file
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild); // Get AudioPlayer for guild
        player.queue(file); // Queue file
    }

    private static void setVolume(float vol, IGuild guild){
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild);
        player.setVolume(vol);
    }
}
