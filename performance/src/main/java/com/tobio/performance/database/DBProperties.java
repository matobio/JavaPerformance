package com.tobio.performance.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.tobio.performance.log.Logger;
import com.tobio.performance.utils.AppUtils;
import com.tobio.performance.utils.Constants;

public class DBProperties {

    protected static final String DATABASE_PROPERTIES_FILE = "com/tobio/performance/prop/database.properties";

    protected static DBProperties instance;

    protected String              databaseFile             = null;


    protected DBProperties() {}


    public static DBProperties getInstance() {
        if (DBProperties.instance == null) {
            DBProperties.instance = new DBProperties();
        }
        return DBProperties.instance;
    }


    public void init() {

        try {

            this.loadProperties();

        } catch (Exception ex) {
            Logger.error(ex.getMessage());
        }
    }


    public void loadProperties() throws Exception {

        Properties prop = this.getProperties();

        String tempDatabaseFilePath = prop.getProperty("server.database");
        if (tempDatabaseFilePath != null) {
            this.databaseFile = tempDatabaseFilePath;
        }
    }


    /**
     * Read the database properties file and get the properties. Return an object Properties with the database properties.
     *
     * @return
     *      properties
     * @throws Exception
     */
    public Properties getProperties() throws Exception {

        Properties properties = null;

        InputStream inputStream = null;

        try {

            properties = new Properties();

            String classPath = AppUtils.getClassPath();

            if (classPath.startsWith("/")) {
                classPath = classPath.replaceFirst("/", "");
            }
            else if (classPath.startsWith("\\")) {
                classPath = classPath.replaceFirst("\\", "");
            }

            String finalPropFilePath = classPath + DBProperties.DATABASE_PROPERTIES_FILE;
            File file = new File(finalPropFilePath);
            if (!file.exists()) {
                throw new FileNotFoundException("Error: property file '" + DBProperties.DATABASE_PROPERTIES_FILE + "' not found in the classpath");
            }

            inputStream = new FileInputStream(finalPropFilePath);

            properties.load(inputStream);

        } catch (Exception e) {
            throw new Exception(Constants.ERROR_TRYING_TO_GET_DATABASE_PROPERTIES);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }


    public String getDatabaseFile() {
        return this.databaseFile;
    }

}
