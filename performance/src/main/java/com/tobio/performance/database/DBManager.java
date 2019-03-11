package com.tobio.performance.database;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;

public class DBManager {

    protected static DBManager instance;

    protected ObjectContainer  databaseManager = null;


    protected DBManager() {

    }


    public static DBManager getInstance() {
        if (DBManager.instance == null) {
            DBManager.instance = new DBManager();
        }
        return DBManager.instance;
    }


    public void initDatabase() {

        Configuration config = this.getDatabaseConfiguration();

        this.databaseManager = Db4o.openFile(config, DBProperties.getInstance().getDatabaseFile());

    }


    protected Configuration getDatabaseConfiguration() {

        Configuration config = Db4o.newConfiguration();

        return config;
    }


    public void closeDatabase() {
        if (this.databaseManager != null) {
            this.databaseManager.close();
        }
    }


    public ObjectContainer getDatabaseManager() {
        return this.databaseManager;
    }


    public void store(Object ob) {
        this.databaseManager.store(ob);
    }


    public void printResults(ObjectSet<?> result) {

        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }
}
