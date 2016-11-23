package com.drunkbot.discord.audio;
import com.fasterxml.jackson.databind.ObjectMapper;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.audio.AudioPlayer;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Created by dylan on 11/23/16.
 */
public class AudioHandler {
    private static final String botprefix = "";
    @EventSubscriber
    public void OnMesageEvent(MessageReceivedEvent event) throws IOException, UnsupportedAudioFileException, RateLimitException, MissingPermissionsException, DiscordException {
        IMessage message = event.getMessage(); // Get message from event

//        if(message.getContent().startsWith(botprefix)){
//            String command = message.getContent().replaceFirst(botprefix, ""); // Remove prefix
//            String[] args = command.split(" "); // Split command into arguments

            if(message.getContent().toLowerCase().contains("summon")) {
                try {
//                    DiscordVoiceWS socket =

                    IVoiceChannel voicechannel = message.getAuthor().getConnectedVoiceChannels().get(0);
//                    voicechannel
                    voicechannel.join();
//                    voicechannel.
                    message.getChannel().sendMessage("Joined `" + voicechannel.getName() + "`.");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (message.getContent().toLowerCase().contains("dismiss")) {
                IVoiceChannel voiceChannel = message.getAuthor().getConnectedVoiceChannels().get(0);
                voiceChannel.leave();
                message.getChannel().sendMessage("Left channel `" + voiceChannel.getName() + "`.");


            }else if(message.getContent().toLowerCase().contains("volume")) {
                float vol = Float.parseFloat(message.getContent().split(" ")[1]);
                setVolume(vol, message.getGuild());
                message.getChannel().sendMessage("Set volume to " + vol + ".");


            }  else if (message.getContent().toLowerCase().contains("play")) {
                try {
                    AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                    player.queue(new File("main/resources/jc.mp3"));
                    player.queue(new File("/Users/dylan/IdeaProjects/DrunkBot/main/resources/jc.mp3"));
//                    player.skipTo(0);
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(mapper.writeValueAsString(player.getCurrentTrack().getMetadata()));
                    message.getChannel().sendMessage("Queueing files status: " + (player.getCurrentTrack().isReady() ? "Ready" : "Not Ready"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (message.getContent().toLowerCase().contains("pause")) {
                AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                player.setPaused(true);
                message.getChannel().sendMessage("Pausing.");


            } else if (message.getContent().toLowerCase().contains("resume")) {
                AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                player.setPaused(false);
                message.getChannel().sendMessage("Resuming Audio.");

            }

    }


    // Queue audio from specified file for guild
    private static void playAudioFromFile(String s_file, IGuild guild) throws IOException, UnsupportedAudioFileException {
//        File file = new File("main/resources/jc.mp3"); // Get file
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild);
//        player.
        player.setPaused(false);
        player.setVolume(80.0f);
        player.queue(new File("main/resources/jc.mp3"));
        AudioPlayer.Track track = player.getCurrentTrack();
        track.getStream().read(player.provide());
    }

    private static void setVolume(float vol, IGuild guild){
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild);
        player.setVolume(vol);
    }
}
