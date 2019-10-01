package com.codio.feature_usage_mod.support;

import java.io.File;

public interface FileHandler {
  void handle(int level, String path, File file);
}
