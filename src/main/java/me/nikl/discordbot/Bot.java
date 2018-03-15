package me.nikl.discordbot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.managers.GuildController;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EventListener;
import java.util.Properties;

/**
 * @author Niklas Eicker
 */
public class Bot implements EventListener {
    private static Bot instance;
    private static String token;
    private static JDA api;

    public static void main(String [] arguments) {
        loadConfig();
        instance = new Bot();
        try {
            api = new JDABuilder(AccountType.BOT)
                    .setToken(token)
                    .addEventListener(new ReadyListener(instance))
                    .setGame(Game.playing("Serving Nikl"))
                    .buildAsync();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void loadConfig() {
        try {
            Properties properties = new Properties();
            File config = new File("config.properties");
            if (!config.exists()) {
                config.createNewFile();
                FileOutputStream fos = new FileOutputStream("config.properties");
                properties.put("token", "");
                properties.store(fos, " Your bot token");
                fos.flush();
                fos.close();
            }
            FileInputStream input = new FileInputStream("config.properties");
            properties.load(input);
            token = properties.getProperty("token");
            if (token == null || token.isEmpty()) {
                System.out.println("Please set a token in config.properties");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void enable() {
        System.out.println("enable bot now");
        //api.addEventListener(new MessageListerner());
        api.addEventListener(new JoinListener());
    }
}