package com.daviddevelops.templates.Commands.SubCommands.TemplateCommand;

import com.daviddevelops.templates.Commands.SubCommand;
import org.bukkit.entity.Player;

public class TemplateSubCommand extends SubCommand {
    @Override
    public String getName() {
        return "Template";
    }

    @Override
    public String getDescription() {
        return "This is a template command";
    }

    @Override
    public String getSyntax() {
        return "/Mastercommand template <user>";
    }

    @Override
    public void perform(Player player, String[] args) {

    }
}
