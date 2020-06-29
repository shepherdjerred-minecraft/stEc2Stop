package com.shepherdjerred.minecraft.ec2stop;

import com.shepherdjerred.minecraft.ec2stop.configuration.Configuration;
import com.shepherdjerred.minecraft.ec2stop.configuration.flatfile.FlatfileConfigurationGetter;
import com.shepherdjerred.minecraft.ec2stop.configuration.flatfile.JsonSerializer;
import com.shepherdjerred.minecraft.ec2stop.stopper.HttpEc2Stopper;
import com.shepherdjerred.minecraft.ec2stop.stopper.ScheduledStopper;
import java.io.IOException;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {

  @SneakyThrows
  @Override
  public void onEnable() {
    var configuration = getConfiguration();
    var stopperDecider = new BukkitPlayerCountStopperDecider();
    var httpEc2Stopper = new HttpEc2Stopper(configuration.getUrl(), configuration.getSecret());
    var scheduledStopper = new ScheduledStopper(httpEc2Stopper, stopperDecider, configuration.getDelayInSeconds() * 1000);
    getServer().getPluginManager().registerEvents(new PlayerQuitListener(stopperDecider, scheduledStopper), this);
  }

  private Configuration getConfiguration() throws IOException {
    var path = getDataFolder() + "/configuration.json";
    var serializer = new JsonSerializer();
    var configurationGetter = new FlatfileConfigurationGetter(serializer, path);
    return configurationGetter.getConfiguration();
  }
}
