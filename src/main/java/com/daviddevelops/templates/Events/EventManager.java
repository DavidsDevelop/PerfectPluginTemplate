package com.daviddevelops.templates.Events;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;

public class EventManager implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Action actionType = event.getAction();
        Player player = event.getPlayer();
        switch(actionType){
            case RIGHT_CLICK_BLOCK:
                if(player.isSneaking()){
                    playerRightClickBlockShift();
                    break;
                }
                playerRightClickBlock();
                break;
            case RIGHT_CLICK_AIR:
                if(player.isSneaking()){
                    playerRightClickAirShift();
                    break;
                }
                playerRightClickAir();
                break;
            case LEFT_CLICK_BLOCK:
                if(player.isSneaking()){
                    playerLeftClickBlockShift();
                    break;
                }
                playerLeftClickBlock();
                break;
            case LEFT_CLICK_AIR:
                if(player.isSneaking()){
                    playerLeftClickAirShift();
                    break;
                }
                playerLeftClickAir();
                break;
        }
    }


    public void playerRightClickAir(){

    }

    public void playerRightClickBlock(){

    }

    public void playerRightClickBlockShift(){

    }

    public void playerRightClickAirShift(){

    }

    public void playerLeftClickAir(){

    }

    public void playerLeftClickBlock(){

    }

    public void playerLeftClickBlockShift(){

    }

    public void playerLeftClickAirShift(){

    }
    
}
