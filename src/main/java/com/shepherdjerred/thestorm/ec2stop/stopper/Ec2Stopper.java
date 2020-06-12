package com.shepherdjerred.thestorm.ec2stop.stopper;

import java.io.IOException;

public interface Ec2Stopper {
  void stop() throws IOException;
}
