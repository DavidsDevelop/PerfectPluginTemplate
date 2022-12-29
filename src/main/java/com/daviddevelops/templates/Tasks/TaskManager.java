package com.daviddevelops.templates.Tasks;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TaskManager {


    public TaskManager(){

    }

    private final ArrayList<BukkitRunnable> taskQueue = new ArrayList<>();
    private final ArrayList<BukkitRunnable> runningTasks = new ArrayList<>();

    public ArrayList<BukkitRunnable> getTaskQueue() {
        return taskQueue;
    }

    public ArrayList<BukkitRunnable> getRunningTasks() {
        return runningTasks;
    }

    public void getTaskFromQueue(BukkitRunnable runnable){

    }

    public void getTask(BukkitRunnable runnable){

    }

}
