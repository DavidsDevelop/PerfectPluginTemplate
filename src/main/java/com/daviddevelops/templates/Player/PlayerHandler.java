package com.daviddevelops.templates.Player;

import com.daviddevelops.templates.Player.Data.*;
import com.daviddevelops.templates.Primary.Registry;
import com.daviddevelops.templates.Utility.*;
import com.google.gson.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PlayerHandler {

    Registry reg;

    List<PlayerData> playerDataList = new ArrayList<>();

    String playerDataFilePath;

    public PlayerHandler(){
        this.reg = com.daviddevelops.templates.Primary.Registry.getInstance();
        playerDataFilePath = reg.getTemplatePlugin().getDataFolder().getAbsolutePath() + "/players";
        onServerLoad();
    }

    public void onServerLoad(){
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            loadPlayerData(player);
        }
    }

    public boolean savePlayerData(PlayerData data){
        try{
            GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
            builder.registerTypeAdapter(Location.class, new LocationAdapter());
            Gson gson = builder.create();
            File file = new File(reg.getTemplatePlugin().getDataFolder() + "/players", data.getPlayer().getUniqueId() + ".json");
            file.getParentFile().mkdirs();
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            gson.toJson(data, writer);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException exp){
            exp.printStackTrace();
        }
        return false;
    }

    public boolean loadPlayerData(Player player){
        try {
            GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
            builder.registerTypeAdapter(Location.class, new LocationAdapter());
            Gson gson = builder.create();

            File file = new File(reg.getTemplatePlugin().getDataFolder().getAbsolutePath() + "/players", player.getUniqueId() + ".json");
            if(!(file.exists())){
                // Create new player data
                registerNewPlayer(player);
                return true;
            }
            Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));
            PlayerData data = gson.fromJson(reader, PlayerData.class);
            if(data ==null){
                registerNewPlayer(player);
            }
            data.setPlayer(player);
            playerDataList.add(data);
        } catch (IOException exp){
            exp.printStackTrace();
        }
        return false;
    }

    public boolean saveAllPlayers(){
        for(PlayerData data : playerDataList){
            savePlayerData(data);
        }
        return false;
    }

    private void registerNewPlayer(Player player){
        PlayerData data = new PlayerData();
        data.setPlayer(player);
        playerDataList.add(data);
    }


}
