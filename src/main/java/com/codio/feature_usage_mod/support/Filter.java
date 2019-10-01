package com.codio.feature_usage_mod.support;

import java.io.File;

public interface Filter {
  boolean interested(int level, String path, File file);
}
