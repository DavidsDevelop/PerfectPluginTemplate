package com.daviddevelops.templates.Utility;

import com.daviddevelops.templates.Primary.Registry;
import org.bukkit.*;
import org.bukkit.configuration.*;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;


public class ConfigManager {
    private final ArrayList<FileConfiguration> customConfigs = new ArrayList<FileConfiguration>();
    private final ArrayList<String> configNames = new ArrayList<String>();
    private Plugin plugin = null;

    public ConfigManager(){
        plugin = Registry.getInstance().getTemplatePlugin();
    }

    public FileConfiguration getConfig(String name){
        if (customConfigs.size() > 0) {
            for (FileConfiguration conf : customConfigs) {
                if (conf.getName().equalsIgnoreCase(name)) {
                    return conf;
                }
            }
        }

        return createNewCustomConfig(name);
    }

    public FileConfiguration getConfig(String name, Plugin plugin){
        if (customConfigs.size() > 0) {
            for (FileConfiguration conf : customConfigs) {
                if (conf.getName().equalsIgnoreCase(name)) {
                    return conf;
                }
            }
        }

        return createNewCustomConfig(name, plugin);
    }

    public void reloadConfigs(){
        customConfigs.clear();
        configNames.clear();
    }

    private FileConfiguration createNewCustomConfig(String name, Plugin plugin) {
        return getFileConfiguration(name, plugin);
    }

    @Nullable
    private FileConfiguration getFileConfiguration(String name, Plugin plugin) {
        FileConfiguration fileConfiguration;
        File configFile = new File(plugin.getDataFolder(), name);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            plugin.saveResource(name, false);
        }

        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(configFile);
            customConfigs.add(fileConfiguration);
            configNames.add(name);
            return fileConfiguration;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FileConfiguration createNewCustomConfig(String name) {
        return getFileConfiguration(name, plugin);
    }

    //Returns true if saved successfully, returns false in case of error and prints error to console
    public boolean setData(FileConfiguration conf, String path, Object data){
        conf.set(path, data);
        return saveData(conf);
    }

    private boolean saveData(FileConfiguration conf){
        try {
            conf.save(new File(plugin.getDataFolder(), configNames.get(customConfigs.indexOf(conf))));
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public String getStringRaw(FileConfiguration conf, String path){
        //Create dummy if not available
        if (!conf.contains(path)) {
            setData(conf, path, "dummy string");
        }
        return conf.getString(path);
    }

    public int getInt(FileConfiguration conf, String path){
        //Create dummy if not available
        if (!conf.contains(path)) {
            setData(conf, path, 1);
        }
        return conf.getInt(path);
    }

    public double getDouble(FileConfiguration conf, String path){
        //Create dummy if not available
        if (!conf.contains(path)) {
            setData(conf, path, 1.0);
        }
        return conf.getDouble(path);
    }

    public boolean getBoolean(FileConfiguration conf, String path){
        //Create dummy if not available
        if (!conf.contains(path)) {
            setData(conf, path, false);
        }
        return conf.getBoolean(path);
    }

    public List<?> getList(FileConfiguration conf, String path){
        //Create dummy list if not available
        if (!conf.contains(path)) {
            setData(conf, path, new ArrayList<Location>().add(new Location(Bukkit.getWorld("world"), 10, 10, 10)));
        }
        return conf.getList(path);
    }

    public boolean addLocation(FileConfiguration conf, Location location, String path){
        conf.set(String.format("%s.world", path), location.getWorld().getName());
        conf.set(String.format("%s.x", path), location.getX());
        conf.set(String.format("%s.y", path), location.getY());
        conf.set(String.format("%s.z", path), location.getZ());

        return saveData(conf);
    }

    public Location getLocation(FileConfiguration conf, String path){
        String worldName = getStringRaw(conf, String.format("%s.world", path));
        Bukkit.getServer().createWorld(new WorldCreator(worldName));

        World world = Bukkit.getWorld(worldName);
        int x = getInt(conf, String.format("%s.x", path));
        int y = getInt(conf, String.format("%s.y", path));
        int z = getInt(conf, String.format("%s.z", path));

        return new Location(world, x, y, z);
    }

}