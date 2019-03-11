package com.tobio.performance.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.tobio.performance.utils.AppUtils;
import com.tobio.performance.utils.Constants;

public class AppProperties {

    private static final String    APPLICATION_PROPERTIES_FILE = "com/tobio/performance/prop/app.properties";

    protected static AppProperties instance;


    protected AppProperties() {}


    public static AppProperties getInstance() {
        if (AppProperties.instance == null) {
            AppProperties.instance = new AppProperties();
        }
        return AppProperties.instance;
    }


    public void init() {

        try {

            this.loadProperties();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadProperties() throws Exception {

    }


    public Properties getProperties() throws Exception {

        Properties properties = null;

        InputStream inputStream = null;

        try {

            properties = new Properties();

            String classPath = AppUtils.getClassPath();

            String finalPropFilePath = classPath + AppProperties.APPLICATION_PROPERTIES_FILE;
            File file = new File(finalPropFilePath);
            if (!file.exists()) {
                throw new FileNotFoundException("Error: Application property file '" + AppProperties.APPLICATION_PROPERTIES_FILE + "' not found in the classpath");
            }

            inputStream = new FileInputStream(finalPropFilePath);

            properties.load(inputStream);

        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Exception(Constants.ERROR_TRYING_TO_GET_APPLICATION_PROPERTIES);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }

}
