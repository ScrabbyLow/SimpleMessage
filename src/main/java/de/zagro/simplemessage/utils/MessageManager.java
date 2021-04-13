package de.zagro.simplemessage.utils;

import de.zagro.simplemessage.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageManager {

    private final Main plugin;

    public HashMap<Player, Player> msgPlayers = new HashMap<Player,Player>();

    public MessageManager(Main plugin) {
        this.plugin = plugin;
    }

    public void setReplyTarget(Player messager, Player reciever) {
        msgPlayers.put(messager, reciever);
        msgPlayers.put(reciever, messager);
    }

    public Player getReplyTarget(Player messager) {
        return msgPlayers.get(messager);
    }
}
