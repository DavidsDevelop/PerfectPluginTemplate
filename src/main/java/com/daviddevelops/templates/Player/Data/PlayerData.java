package com.daviddevelops.templates.Player.Data;

import org.bukkit.*;
import org.bukkit.entity.*;

public class PlayerData {

    private String username, UUID;


    public Player getPlayer(){
        return Bukkit.getPlayer(getUsername());
    }

    public void setPlayer(Player player){
        username = player.getName();
        UUID = player.getUniqueId().toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
