package com.daviddevelops.templates.Tasks;

import org.bukkit.scheduler.BukkitRunnable;

public class AsyncTask {

    BukkitRunnable runnable;

    public AsyncTask(BukkitRunnable runnable){
        this.runnable = runnable;
    }


    public void runTask(){
        runnable.run();
    }

}
