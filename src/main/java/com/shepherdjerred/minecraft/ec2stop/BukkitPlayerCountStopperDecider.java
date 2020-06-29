package com.shepherdjerred.minecraft.ec2stop;

import org.bukkit.Bukkit;

public class BukkitPlayerCountStopperDecider {

  public boolean shouldStop() {
    return Bukkit.getOnlinePlayers().size() == 1;
  }
}
