package com.daviddevelops.templates.Primary;

import org.bukkit.plugin.java.JavaPlugin;

public class TemplatePlugin extends JavaPlugin {

    private Registry registry;

    @Override
    public void onEnable(){
        super.onEnable();
    }

    @Override
    public void onDisable() {
        registry.getPlayerHandler().saveAllPlayers();
        super.onDisable();
    }

    @Override
    public void onLoad() {
        registry = Registry.getInstance(this);
        super.onLoad();
    }

    public Registry getRegistry() {
        return registry;
    }
}
