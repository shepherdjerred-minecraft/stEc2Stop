package com.shepherdjerred.minecraft.ec2stop.configuration.flatfile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shepherdjerred.minecraft.ec2stop.configuration.Configuration;

public class JsonSerializer implements Serializer {
  private final Gson gson;

  public JsonSerializer() {
    var builder = new GsonBuilder();
    builder.setPrettyPrinting();
    gson = builder.create();
  }

  @Override
  public String serialize(Configuration configuration) {
    return gson.toJson(configuration);
  }

  @Override
  public Configuration deserialize(String string) {
    return gson.fromJson(string, Configuration.class);
  }
}
