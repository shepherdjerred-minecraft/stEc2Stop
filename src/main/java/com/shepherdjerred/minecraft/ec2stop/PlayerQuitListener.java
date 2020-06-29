package com.shepherdjerred.minecraft.ec2stop;

import com.shepherdjerred.minecraft.ec2stop.stopper.Ec2Stopper;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@Log4j2
@AllArgsConstructor
public class PlayerQuitListener implements Listener {
  private final BukkitPlayerCountStopperDecider decider;
  private final Ec2Stopper stopper;

  @EventHandler
  public void onQuit(PlayerQuitEvent event) throws IOException {
    if (decider.shouldStop()) {
      log.info("Stopping server");
      stopper.stop();
    }
  }
}
