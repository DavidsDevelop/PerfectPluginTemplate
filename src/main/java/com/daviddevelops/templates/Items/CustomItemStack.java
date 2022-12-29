package com.daviddevelops.templates.Items;

import com.daviddevelops.templates.Primary.Registry;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.*;

public class CustomItemStack extends ItemStack {

    Player player;
    Material material;
    ItemStack itemStack;
    NamespacedKey isPLUGINNAMEItem;

    public CustomItemStack(Player player, Material material){
        this.player = player;
        this.material = material;
        this.itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer PDC = meta.getPersistentDataContainer();
        isPLUGINNAMEItem = new NamespacedKey(Registry.getInstance().getTemplatePlugin(), "isCustomItemXXX");
        PDC.set(isPLUGINNAMEItem, PersistentDataType.STRING, "true");
    }



}
