package com.daviddevelops.templates.Commands;

import com.daviddevelops.templates.Primary.Registry;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

    JavaPlugin plugin = Registry.getInstance().getTemplatePlugin();

    public CommandManager(){
        plugin.getCommand("Mastercommand").setExecutor(new MasterCommand());
        plugin.getCommand("TemplateCommand").setExecutor(new MasterCommand());
    }

}
