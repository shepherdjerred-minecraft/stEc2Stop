package com.shepherdjerred.minecraft.ec2stop.stopper;

import com.shepherdjerred.minecraft.ec2stop.StopperDecider;
import java.util.Timer;
import java.util.TimerTask;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class ScheduledStopper implements Ec2Stopper {
  private final Ec2Stopper delegate;
  private final StopperDecider decider;
  private final int delay;

  @Override
  public void stop() {
    var task = new TimerTask() {

      @SneakyThrows
      @Override
      public void run() {
        if (decider.shouldStop()) {
          delegate.stop();
        }
      }
    };

    var timer = new Timer("Timer");
    timer.schedule(task, delay);
  }
}
