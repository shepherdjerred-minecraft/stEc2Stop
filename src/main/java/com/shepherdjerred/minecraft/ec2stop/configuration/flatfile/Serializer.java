package com.shepherdjerred.minecraft.ec2stop.configuration.flatfile;

import com.shepherdjerred.minecraft.ec2stop.configuration.Configuration;

public interface Serializer {
  String serialize(Configuration configuration);

  Configuration deserialize(String string);
}
