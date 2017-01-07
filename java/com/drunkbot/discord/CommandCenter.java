package com.drunkbot.discord;
import com.drunkbot.discord.Users.Security;
import com.drunkbot.discord.Users.Watcher;
import com.drunkbot.discord.audio.AudioHandler;
import com.drunkbot.discord.events.AdminListener;
import com.drunkbot.discord.events.JoinListener;
import com.drunkbot.discord.events.LFGListener;
import com.drunkbot.discord.events.MessageListener;
import sx.blah.discord.api.IDiscordClient;

import  javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//import
public class CommandCenter extends JFrame{
    private DrunkBot drunkBot;
    public CommandCenter () {
        setTitle("Bot Command Center");
        setSize(500,300);
        setLocation(10,200);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            } //windowClosing
        } );



    }

    private void start () {
        drunkBot = new DrunkBot("MjUwODY4OTE3NzkzMzI1MDU2.Cximgw.cjs8MF6_htTOE5IvVI_DMRAz84Y");
        IDiscordClient client = drunkBot.getClient();

        client.getDispatcher().registerListener(drunkBot.getListener());
        client.getDispatcher().registerListener(new MessageListener());
        client.getDispatcher().registerListener(new AudioHandler());
        client.getDispatcher().registerListener(new Watcher());
        client.getDispatcher().registerListener(new Security());
        client.getDispatcher().registerListener(new JoinListener());
        client.getDispatcher().registerListener(new LFGListener());
        client.getDispatcher().registerListener(new AdminListener());
    }

    private void stop () {
        try {
//            drunkBot.getClient();
            drunkBot.getClient().logout();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }


}
