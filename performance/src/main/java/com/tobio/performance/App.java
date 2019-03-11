package com.tobio.performance;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.tobio.performance.database.DBManager;
import com.tobio.performance.database.DBProperties;
import com.tobio.performance.log.Logger;
import com.tobio.performance.prop.AppProperties;

public class App {

    public static void main(String[] args) {

        try {
            App.initApplication();

            // List<CollectionPerformanceResultObject> listResults = ListsTestPerformance.runTestOperationAdd(5, 100000, new ArrayList<>(),
            // ListsTestPerformance.ADD_AT_THE_END);
            // List<CollectionPerformanceResultObject> listResults2 = ListsTestPerformance.runTestOperationAdd(5, 100000, new Vector<>(),
            // ListsTestPerformance.ADD_AT_THE_END);
            //
            ObjectContainer dbManager = DBManager.getInstance().getDatabaseManager();
            //
            // listResults.forEach(el -> dbManager.store(el));
            // listResults2.forEach(el -> dbManager.store(el));

            ObjectSet<CollectionPerformanceResultObject> result = dbManager.query(new Predicate<CollectionPerformanceResultObject>() {

                private static final long serialVersionUID = 1L;


                @Override
                public boolean match(CollectionPerformanceResultObject obj) {
                    return true;
                }

            });

            DBManager.getInstance().printResults(result);

        } catch (Exception ex) {
            Logger.error(ex.getMessage());
        } finally {
            App.closeApp();
        }
    }


    protected static void initApplication() {

        Logger.getInstance().init();

        AppProperties.getInstance().init();

        DBProperties.getInstance().init();

        DBManager.getInstance().initDatabase();

    }


    public static void closeApp() {
        DBManager.getInstance().closeDatabase();
    }
}
