package com.shepherdjerred.minecraft.ec2stop.configuration;

import lombok.Value;

@Value
public class Configuration {
  String url;
  String secret;
  int delayInSeconds;
}
