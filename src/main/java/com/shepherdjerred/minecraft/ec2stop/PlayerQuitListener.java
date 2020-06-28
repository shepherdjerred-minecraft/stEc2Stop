package com.shepherdjerred.minecraft.ec2stop;

import com.shepherdjerred.minecraft.ec2stop.stopper.Ec2Stopper;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class PlayerQuitListener implements Listener {
  private final BukkitPlayerCountStopperDecider decider;
  private final Ec2Stopper stopper;

  @EventHandler
  public void onQuit(PlayerQuitEvent event) throws IOException {
    if (decider.shouldStop()) {
      stopper.stop();
    }
  }
}
