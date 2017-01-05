package com.drunkbot.discord.audio;

import sx.blah.discord.handle.audio.IAudioProvider;
import sx.blah.discord.util.audio.providers.FileProvider;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class YoutubeProvider implements IAudioProvider {

    private IAudioProvider provider;
    private boolean providerSet = false;

    public YoutubeProvider(String videoID) {
        new Thread(() -> {
            Thread downloadThread = new Thread(() -> {
                try {
                    Process youtubeDl = new ProcessBuilder("youtube-dl", "-x", "--audio-format", "wav", "--id", "--", videoID).inheritIO().start();
                    youtubeDl.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            downloadThread.start();

            try {
                downloadThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                provider = new FileProvider(new File(videoID + ".wav"));
                providerSet = true;
            } catch (IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }


            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

            service.scheduleAtFixedRate(() -> {
                if (!this.isReady()) {
                    new File(videoID + ".wav").delete();
                }
            }, 0, 1, TimeUnit.SECONDS);
        }).start();
    }

    @Override
    public boolean isReady() {
        return providerSet && provider.isReady();
    }

    @Override
    public byte[] provide() {
        return provider.provide();
    }
}