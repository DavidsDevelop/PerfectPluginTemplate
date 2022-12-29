package com.daviddevelops.templates.Commands;

import com.daviddevelops.templates.Primary.*;
import com.daviddevelops.templates.Utility.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

public class MasterCommand implements CommandExecutor {

    GenericUtility ut;
    private final ArrayList<SubCommand> subcommands = new ArrayList<>();

    public MasterCommand() {
        ut = Registry.getInstance().getGenericUtility();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubcommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                        if (p.hasPermission("PluginName." + getSubcommands().get(i).getName())) {
                            getSubcommands().get(i).perform(p, args);
                        } else {
                            ut.sendPlayerMessage(p, command.getPermissionMessage(), true);
                            ut.sendPlayerMessage(p, command.getPermissionMessage(), false);
                        }
                    }
                }
                return true;
            }
            sendHubMessage(p);
        } else {
            for (int i = 0; i < getSubcommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                    getSubcommands().get(i).perform((Player) sender, args);
                }
            }
        }
        return true;
    }

    private void sendHubMessage(Player p) {
        ut.sendPlayerMessage(p, "&8&m&l-------------------&f Genied Commands &8&m&l-------------------", false);
        for (int i = 0; i < getSubcommands().size(); i++) {
            ut.sendPlayerMessage(p, "&8» &f" + getSubcommands().get(i).getSyntax() + "&8 - &7" + getSubcommands().get(i).getDescription(), false);
        }
        ut.sendPlayerMessage(p, "&8&m&l-------------------&f Genied Commands &8&m&l-------------------", false);
    }

    public ArrayList<SubCommand> getSubcommands() {
        return subcommands;
    }
}
