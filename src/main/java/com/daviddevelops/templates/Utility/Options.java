package com.daviddevelops.templates.Utility;

import com.daviddevelops.templates.Primary.Registry;
import org.bukkit.*;
import org.bukkit.configuration.file.*;

public class Options {

    // Developer added Options

    public String SLOT_LIMIT_REACHED;
    public String STACK_SIZE_REACHED;
    public String ADDED_TO_STACK;
    public String UPGRADE_FAILURE;




    // Requested Options
    public Sound GENERATOR_PLACE_SOUND;
    public Sound GENERATOR_BREAKING_SOUND;
    public Sound GENERATOR_UPGRADING_SOUND;
    public String GENERATOR_PLACE_MESSAGE;
    public String GENERATOR_BREAKING_MESSAGE;
    public String GENERATOR_UPGRADING_MESSAGE;

    public Options(){
        ConfigManager ch = Registry.getInstance().getConfigManager();
        FileConfiguration fc = ch.getConfig("messages.yml");


        SLOT_LIMIT_REACHED = ch.getStringRaw(fc, "Generator.Messages.SLOT_LIMIT_REACHED");
        STACK_SIZE_REACHED = ch.getStringRaw(fc, "Generator.Messages.STACK_SIZE_REACHED");
        ADDED_TO_STACK = ch.getStringRaw(fc, "Generator.Messages.ADDED_TO_STACK");
        UPGRADE_FAILURE = ch.getStringRaw(fc, "Generator.Messages.UPGRADE_FAILURE");

        GENERATOR_PLACE_MESSAGE = ch.getStringRaw(fc, "Generator.Messages.PLACE_MESSAGE");
        GENERATOR_BREAKING_MESSAGE = ch.getStringRaw(fc, "Generator.Messages.BREAK_MESSAGE");
        GENERATOR_UPGRADING_MESSAGE = ch.getStringRaw(fc, "Generator.Messages.UPGRADE_MESSAGE");

        GENERATOR_PLACE_SOUND = Sound.valueOf(ch.getStringRaw(fc, "Generator.Sounds.PLACE_SOUND"));
        GENERATOR_BREAKING_SOUND = Sound.valueOf(ch.getStringRaw(fc, "Generator.Sounds.BREAK_SOUND"));
        GENERATOR_UPGRADING_SOUND = Sound.valueOf(ch.getStringRaw(fc, "Generator.Sounds.UPGRADE_SOUND"));
    }

}
