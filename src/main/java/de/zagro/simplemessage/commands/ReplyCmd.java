package de.zagro.simplemessage.commands;

import de.zagro.simplemessage.Main;
import de.zagro.simplemessage.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCmd implements CommandExecutor {

    private final Main plugin;
    private final MessageManager mM;
    private final MessageCmd mC;
    public ReplyCmd(Main plugin, MessageManager mM, MessageCmd mC) {
        this.plugin = plugin;
        this.mM = mM;
        this.mC = mC;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player messager = (Player) sender;
            if (messager.hasPermission("simplemessage.message")) {
                if (args.length < 1) {
                    messager.sendMessage(ChatColor.RED + "Usage: /r <message>");
                } else {
                    if (mM.getReplyTarget(messager) == null) {
                        messager.sendMessage(ChatColor.RED + "You haven't messaged anyone recently!");
                        return true;
                    }
                    Player reciever = mM.getReplyTarget(messager);
                    if (command.getName().equalsIgnoreCase("reply")) {
                        if (!(reciever == null)) {
                            if (!(reciever.getName().equals(messager.getName()))) {
                                String msg = "";
                                for (int i = 0; i < args.length; i++) {
                                    msg += " " + args[i];
                                }
                                if (!(reciever.isOnline())) {
                                    messager.sendMessage(ChatColor.RED + "You cannot send a message to this player because they're not online!");
                                } else {
                                    reciever.sendMessage(ChatColor.LIGHT_PURPLE + "From " + ChatColor.GOLD + messager.getName() + ChatColor.GRAY + " >>" + ChatColor.WHITE + msg);
                                    messager.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.GOLD + reciever.getName() + ChatColor.GRAY + " >> " + ChatColor.WHITE + msg);
                                }
                            } else {
                                messager.sendMessage(ChatColor.RED + "You cannot send a message to yourself!");
                            }
                        } else {
                            messager.sendMessage(ChatColor.RED + "You cannot send a message to this player because they're not online!");
                        }
                    }
                }
            } else {
                messager.sendMessage(ChatColor.RED + "You do not have the required permission!");
            }
        }
        return false;
    }
}
