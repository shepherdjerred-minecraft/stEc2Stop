package com.shepherdjerred.thestorm.ec2stop.configuration.flatfile;

import com.shepherdjerred.thestorm.ec2stop.configuration.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FlatfileConfigurationGetter {
  private final Serializer serializer;
  private final File file;

  public FlatfileConfigurationGetter(Serializer serializer, String filePath) {
    this.serializer = serializer;
    this.file = new File(filePath);
  }

  public Configuration getConfiguration() throws IOException {
    try (var stream = new FileInputStream(file)) {
      var bytes = stream.readAllBytes();
      var jsonString = new String(bytes);
      return serializer.deserialize(jsonString);
    }
  }
}
