package com.drunkbot.discord;
import com.drunkbot.discord.Users.Security;
import com.drunkbot.discord.Users.Watcher;
import com.drunkbot.discord.audio.AudioHandler;
import com.drunkbot.discord.events.*;
import com.sun.deploy.uitoolkit.ui.ConsoleTraceListener;
import sx.blah.discord.api.IDiscordClient;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import
public class CommandCenter{
    private static DrunkBot drunkBot;
    public JFrame frame;
    public CommandCenter () {
        JFrame frame = new JFrame("DrunkBot Command Center");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JButton component = new JButton();
        component.setSize(15,10);
        component.setText("Stop");
        component.setLocation(0,20);
        component.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandCenter.stop();
            }
        });


        JButton startB = new JButton();
        startB.setSize(15,10);
        startB.setText("Start");
        startB.setLocation(0,20);
        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandCenter.start();
            }
        });


        JPanel panel1 = new JPanel(new GridLayout(1,1));
        JPanel panel2 = new JPanel(new GridLayout(1,1));
        JPanel panel3 = new JPanel(new GridLayout(2,3));
        JTextField field = new JTextField();
        panel1.add(startB);
        panel2.add(component);
        panel.add(panel1);
        panel.add(panel2);
        frame.add(panel);
        frame.setSize(200,100);
        frame.setVisible(true);
    }

    private static void start () {
        drunkBot = new DrunkBot("MjUwODY4OTE3NzkzMzI1MDU2.Cximgw.cjs8MF6_htTOE5IvVI_DMRAz84Y");
        IDiscordClient client = drunkBot.getClient();

        client.getDispatcher().registerListener(drunkBot.getListener());
        client.getDispatcher().registerListener(new MessageListener());
        client.getDispatcher().registerListener(new AudioHandler());
        client.getDispatcher().registerListener(new Watcher());
        client.getDispatcher().registerListener(new Security());
        client.getDispatcher().registerListener(new JoinListener());
        client.getDispatcher().registerListener(new LFGListener());
        client.getDispatcher().registerListener(new SwitchEvent());
        client.getDispatcher().registerListener(new AdminListener());
    }

    private static void stop () {
        try {
            drunkBot.getClient().logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JFrame getFrame () {
        return frame;
    }
}
