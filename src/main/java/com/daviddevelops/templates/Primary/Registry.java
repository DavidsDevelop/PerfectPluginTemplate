package com.daviddevelops.templates.Primary;

import com.daviddevelops.templates.Commands.CommandManager;
import com.daviddevelops.templates.Events.EventManager;
import com.daviddevelops.templates.Inventories.API.*;
import com.daviddevelops.templates.Items.ItemCreator;
import com.daviddevelops.templates.Items.ItemManager;
import com.daviddevelops.templates.Player.PlayerDataHandler;
import com.daviddevelops.templates.Player.PlayerHandler;
import com.daviddevelops.templates.Utility.ConfigManager;
import com.daviddevelops.templates.Utility.GenericUtility;
import com.daviddevelops.templates.Utility.LocationAdapter;
import com.daviddevelops.templates.Utility.Options;

public class Registry {

    private static Registry instance = null;
    private final TemplatePlugin templatePlugin;
    private EventManager eventManager;
    private InventoryManager inventoryManager;
    private ItemCreator itemCreator;
    private ItemManager itemManager;
    private PlayerDataHandler playerDataHandler;
    private PlayerHandler playerHandler;
    private ConfigManager configManager;
    private GenericUtility genericUtility;
    private LocationAdapter locationAdapter;
    private CommandManager commandManager;
    private Options options;

    public Registry(TemplatePlugin plugin) {
        this.templatePlugin = plugin;
        registerGenericUtility();
        registerLocationAdapter();
        registerConfigManager();
        registerCommandManager();
        registerOptions();
        registerEventManager();
        registerInventoryManager();
        registerItemCreator();
        registerItemManager();
        registerPlayerDataHandler();
        registerPlayerHandler();
    }

    /**
     * @desc Returns instance of registry class (Registers if does not exist)
     */
    public static Registry getInstance(TemplatePlugin plugin)
    {
        if (instance == null) {
            instance = new Registry(plugin);
        }
        return instance;
    }

    public static Registry getInstance()
    {
        return instance;
    }

    public TemplatePlugin getTemplatePlugin() {
        return templatePlugin;
    }
    public CommandManager getCommandManager() {
        return commandManager;
    }
    public EventManager getEventManager() {
        return eventManager;
    }
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
    public ItemCreator getItemCreator() {
        return itemCreator;
    }
    public ItemManager getItemManager() {
        return itemManager;
    }
    public PlayerDataHandler getPlayerDataHandler() {
        return playerDataHandler;
    }
    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }
    public ConfigManager getConfigManager() {
        return configManager;
    }
    public GenericUtility getGenericUtility() {
        return genericUtility;
    }
    public LocationAdapter getLocationAdapter() {
        return locationAdapter;
    }

    public Options getOptions() {
        return options;
    }

    /**
     * @desc Registers class ( EventManager ) into private/private registry
     */
    private void registerEventManager() {
        eventManager = new EventManager();
    }

    /**
     * @desc Registers class ( InventoryManager ) into private/private registry
     */
    private void registerInventoryManager() {
        inventoryManager = new InventoryManager(templatePlugin);
        inventoryManager.init();
    }

    /**
     * @desc Registers class ( ItemCreator ) into private/private registry
     */
    private void registerItemCreator() {
        itemCreator = new ItemCreator();
    }

    /**
     * @desc Registers class ( ItemManager ) into private/private registry
     */
    private void registerItemManager() {
        itemManager = new ItemManager();
    }

    /**
     * @desc Registers class ( PlayerDataHandler ) into private/private registry
     */
    private void registerPlayerDataHandler() {
        playerDataHandler = new PlayerDataHandler();
    }

    /**
     * @desc Registers class ( PlayerHandler ) into private/private registry
     */
    private void registerPlayerHandler() {
        playerHandler = new PlayerHandler();
    }

    /**
     * @desc Registers class ( ConfigManager ) into private/private registry
     */
    private void registerConfigManager() {
        configManager = new ConfigManager();
    }

    /**
     * @desc Registers class ( GenericUtility ) into private/private registry
     */
    private void registerGenericUtility() {
        genericUtility = new GenericUtility();
    }

    /**
     * @desc Registers class ( LocationAdapter ) into private/private registry
     */
    private void registerLocationAdapter() {
        locationAdapter = new LocationAdapter();
    }

    /**
     * @desc Registers class ( CommandManager ) into public/private registry
     */
    public void registerCommandManager() {
        commandManager = new CommandManager();
    }

    /**
     * @desc Registers class ( Options ) into private/private registry
     */
    private void registerOptions() {
        options = new Options();
    }
}
