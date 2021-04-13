package de.zagro.simplemessage.commands;

import de.zagro.simplemessage.Main;
import de.zagro.simplemessage.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCmd implements CommandExecutor {

    private final Main plugin;
    private final MessageManager mM;
    public MessageCmd(Main plugin, MessageManager mM) {
        this.plugin = plugin;
        this.mM = mM;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player messager = (Player) sender;
            if (messager.hasPermission("simplemessage.message")) {
                if (args.length < 2) {
                    messager.sendMessage(ChatColor.RED + "Usage: /msg <player> <message>");
                } else {
                    if (command.getName().equalsIgnoreCase("message")) {
                        Player reciever = Bukkit.getPlayerExact(args[0]);
                        if (!(reciever == null)) {
                            if (!(reciever.getName().equals(messager.getName()))) {
                                mM.setReplyTarget(messager, reciever);
                                String msg = "";
                                for (int i = 1; i < args.length; i++) {
                                    msg += " " + args[i];
                                }
                                reciever.sendMessage(ChatColor.LIGHT_PURPLE + "From " + ChatColor.GOLD + messager.getName() + ChatColor.GRAY + " >>" + ChatColor.WHITE + msg);
                                messager.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.GOLD + reciever.getName() + ChatColor.GRAY + " >> " + ChatColor.WHITE + msg);

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
