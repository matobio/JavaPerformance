package com.tobio.performance.database;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
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
        return Db4o.newConfiguration();
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

}
