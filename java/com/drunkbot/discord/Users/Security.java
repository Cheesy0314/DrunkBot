package com.drunkbot.discord.Users;

import com.drunkbot.discord.connectors.Connector;
import com.drunkbot.discord.connectors.WebRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Security implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        try {
            IMessage message = messageReceivedEvent.getMessage();
            IUser user = message.getAuthor();
            IGuild guild = message.getGuild();
            IRole arole = guild.getRoleByID("221379836776546304");
            IRole brole = guild.getRoleByID("231254104545034240");
            IRole crole = guild.getRolesForUser(guild.getUserByID("221377585215438848")).get(0);
            if (message.getContent().toLowerCase().contains("discord.gg") || message.getContent().contains("ts3server") || message.getContent().contains("ts3.hoster.com")){
                checkForDiscord(message.getContent(), message, guild, user, arole, brole, crole);


            } else if (message.getContent().matches("((http://|https://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:?=&;,]*)?)?)") ||
                    message.getContent().matches("(http://|https://)")){
                Pattern pattern = Pattern.compile("((http://|https://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:?=&;,]*)?)?)");
                Matcher matcher = pattern.matcher(message.getContent());
                List<String> urls = new ArrayList<>();
                while (matcher.find())
                {
                   urls.add(matcher.group(1));
                }


                //Send to google

                ObjectMapper mapper = new ObjectMapper();
                Map<String,String> headers = new HashMap<>();
                headers.put("content-type","application/json");
                Map<String,Object> JSON = new HashMap();
                Map client = new HashMap();
                client.put("clientId","drunkbot");
                client.put("clientVersion","1.0.1");

                String[] tarray = {"MALWARE","SOCIAL_ENGINEERING","UNWANTED_SOFTWARE","POTENTIALLY_HARMFUL_APPLICATION"};
                String[] parray = {"ALL_PLATFORMS"};
                String[] uarray = {"URL"};
                Map[] earray = new Map[urls.size()];
                int i = 0;
                for (String urlToCheck : urls) {
                    Map dp = new HashMap();
                    dp.put("url",urlToCheck);
                    earray[i] = dp;
                    i++;
                }

                Map threats = new HashMap();
                threats.put("threatTypes",tarray);
                threats.put("platformTypes",parray);
                threats.put("threatEntryTypes",uarray);
                threats.put("threatEntries",earray);
                JSON.put("threatInfo",threats);
                JSON.put("client",client);
                Connector connector = new Connector("https://safebrowsing.googleapis.com/v4/threatMatches:find?key=AIzaSyCoy0YeWjXi_RDGEUAghrhoKFQT7VUc9wY",mapper.writeValueAsString(JSON),headers,"POST");
                String response = connector.connect();
                Map<String,Object> responseData = mapper.readValue(response,Map.class);

                if (responseData!= null && responseData.containsKey("matches") && responseData.get("matches") != null) {

                    boolean shouldBan = false;
                    boolean isBad = false;
                    List matches = (ArrayList)responseData.get("matches");
                    for (Object match : matches) {
                        Map matchData = (Map) match;

                        if (matchData.get("threatType").equals("MALWARE") || matchData.get("threatType").equals("POTENTIALLY_HARMFUL_APPLICATION")) {
                            shouldBan = true;
                            isBad = true;
                        } else {
                            isBad = true;
                        }
                    }


                    if (isBad) {
                        if (shouldBan) {
                            if (user.getRolesForGuild(guild).contains(guild.getRolesByName("Shit List").get(0))){
                                message.reply("DANGEROUS URL DETECTED! You were warned asshole... Goodbye");
                                guild.banUser(user);
                            } else {
                                message.reply("DANGEROUS URL DETECTED! Please do not attempt to harm users with dangerous links, this is your only warning.");
                                message.delete();
                                user.addRole(guild.getRolesByName("Shit List").get(0));
                            }
                        } else {

                            message.reply("URL you posted was red flagged by DB-Security");
                            message.delete();
                        }
                    }
                } else {

                    for (String urlToCheck: urls) {
                        String Expanded = WebRequest.expander(urlToCheck);

                        checkForDiscord(Expanded,message,guild,user,arole,brole,crole);
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkForDiscord (String URL,IMessage message, IGuild guild, IUser user, IRole arole, IRole brole, IRole crole) throws Exception{
            if (URL.contains("discord.gg") || URL.contains("ts3server") || URL.contains("ts3.hoster.com")) {

                if (!user.getRolesForGuild(guild).contains(arole) && !user.getRolesForGuild(guild).contains(brole) && !user.getRolesForGuild(guild).contains(crole)) {
                    if (user.getRolesForGuild(guild).contains(guild.getRolesByName("Shit List").get(0))) {
                        message.reply("You were warned asshole... Goodbye");
                        guild.banUser(user);
                    } else {
                        message.reply("RULE INFRACTION: No linking discord or teamspeak channels, this is your warning buddy.");
                        message.delete();
                        user.addRole(guild.getRolesByName("Shit List").get(0));
                    }
                }
            }

    }
}
