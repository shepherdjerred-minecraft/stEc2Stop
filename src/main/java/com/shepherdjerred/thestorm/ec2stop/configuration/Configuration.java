package com.shepherdjerred.thestorm.ec2stop.configuration;

import lombok.Value;

@Value
public class Configuration {
  String url;
  String secret;
  int delay;
}
