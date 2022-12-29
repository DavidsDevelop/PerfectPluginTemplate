package com.daviddevelops.templates.Commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

public class TemplateCommand implements CommandExecutor {

    public TemplateCommand(){

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            return true;
        }

        return false;
    }

}
