package com.tobio.performance.utils;

public class AppUtils {

    public static String getClassPath() {
        String classPath = AppUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (classPath.startsWith("/")) {
            classPath = classPath.replaceFirst("/", "");
        }
        else if (classPath.startsWith("\\")) {
            classPath = classPath.replaceFirst("\\", "");
        }

        return classPath;
    }
}
