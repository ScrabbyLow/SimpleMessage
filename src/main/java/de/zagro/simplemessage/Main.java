package de.zagro.simplemessage;

import de.zagro.simplemessage.commands.MessageCmd;
import de.zagro.simplemessage.commands.ReplyCmd;
import de.zagro.simplemessage.utils.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    MessageManager mM;
    MessageCmd mC;

    @Override
    public void onEnable() {
        mM = new MessageManager(this);

        this.getCommand("message").setExecutor(new MessageCmd(this, mM));
        this.getCommand("reply").setExecutor(new ReplyCmd(this, mM, mC));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
