package com.tobio.performance.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.tobio.performance.prop.AppProperties;
import com.tobio.performance.utils.AppUtils;

public class Logger {

    protected static final String PROPERTY_LOGFILE     = "logfile";

    public static final String    MESSAGE_TYPE_ERROR   = "ERROR";
    public static final String    MESSAGE_TYPE_WARNING = "WARNING";
    public static final String    MESSAGE_TYPE_INFO    = "INFO";

    public static final String    DEFAULT_MESSAGE_TYPE = Logger.MESSAGE_TYPE_INFO;

    protected static Logger       instance;

    protected static File         logFile              = null;


    protected Logger() {}


    public static Logger getInstance() {
        if (Logger.instance == null) {
            Logger.instance = new Logger();
        }
        return Logger.instance;
    }


    public void init() {

        try {
            Properties prop = AppProperties.getInstance().getProperties();

            String logfile = prop.getProperty(Logger.PROPERTY_LOGFILE);
            if (logfile != null) {
                Logger.createLogFile(new File(AppUtils.getClassPath() + logfile));
            }
        } catch (Exception e) {
            Logger.message(e.getMessage(), Logger.MESSAGE_TYPE_ERROR);
        }
    }


    public static void createLogFile(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        Logger.setLogFile(file);
    }


    public static File getLogFile() {
        return Logger.logFile;
    }


    public static void setLogFile(File logFile) {
        Logger.logFile = logFile;
    }


    protected static String getDefaultMessageType() {
        return Logger.DEFAULT_MESSAGE_TYPE;
    }


    public static void message(String message, String messageType) {

        if (message == null) {
            message = "";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        StringBuilder finalMessage = new StringBuilder();
        finalMessage.append(simpleDateFormat.format(new Date()));
        finalMessage.append(" :: ").append(messageType).append(" --> ");
        finalMessage.append(message);

        File file;
        try {
            if (Logger.logFile != null && Logger.logFile.exists()) {
                file = Logger.logFile;
            }
            else {
                file = new File("log.txt");
            }

            Path path = Paths.get(file.toURI());

            Files.write(path, System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
            Files.write(path, finalMessage.toString().getBytes(), StandardOpenOption.APPEND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void message(String message) {
        Logger.message(message, Logger.DEFAULT_MESSAGE_TYPE);
    }


    public static void error(String message) {
        Logger.message(message, Logger.MESSAGE_TYPE_ERROR);
    }


    public static void info(String message) {
        Logger.message(message, Logger.MESSAGE_TYPE_INFO);
    }


    public static void warning(String message) {
        Logger.message(message, Logger.MESSAGE_TYPE_WARNING);
    }

}
