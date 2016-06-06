package com.delicious.delicious.fresco;

import com.facebook.common.util.ByteConstants;

/**
 * Created by POWERUSER on 2015-03-31.
 */
public class ConfigConstants {
    public static final String CACHE_DIRECTORY_NAME = "delicious";
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_DISK_CACHE_SIZE = 80 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
}
