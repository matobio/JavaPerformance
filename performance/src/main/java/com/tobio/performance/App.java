package com.tobio.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.db4o.ObjectSet;
import com.tobio.performance.collections.lists.tests.ListsTestPerformance;
import com.tobio.performance.database.DBManager;
import com.tobio.performance.database.DBProperties;
import com.tobio.performance.database.DataBaseUtils;
import com.tobio.performance.log.Logger;
import com.tobio.performance.objects.CollectionPerformanceResultObject;
import com.tobio.performance.prop.AppProperties;

public class App {

    public static void main(String[] args) {

        try {
            App.initApplication();

            App.doSomething();

        } catch (Exception ex) {
            Logger.error(ex.getMessage());
        } finally {
            App.closeApp();
        }
    }


    protected static void doSomething() {

        List<?> listResults = ListsTestPerformance.runTestOperationAdd(10, 100000, new Vector<>(), ListsTestPerformance.ADD_AT_THE_END);
        DataBaseUtils.saveResult(listResults);
        listResults.clear();

        listResults = ListsTestPerformance.runTestOperationAdd(10, 1000000, new Vector<>(), ListsTestPerformance.ADD_AT_THE_END);
        DataBaseUtils.saveResult(listResults);
        listResults.clear();

        listResults = ListsTestPerformance.runTestOperationAdd(10, 10000000, new Vector<>(), ListsTestPerformance.ADD_AT_THE_END);
        DataBaseUtils.saveResult(listResults);
        listResults.clear();

        listResults = ListsTestPerformance.runTestOperationAdd(10, 100000, new ArrayList<>(), ListsTestPerformance.ADD_AT_THE_END);
        DataBaseUtils.saveResult(listResults);
        listResults.clear();

        listResults = ListsTestPerformance.runTestOperationAdd(10, 1000000, new ArrayList<>(), ListsTestPerformance.ADD_AT_THE_END);
        DataBaseUtils.saveResult(listResults);
        listResults.clear();

        listResults = ListsTestPerformance.runTestOperationAdd(10, 10000000, new ArrayList<>(), ListsTestPerformance.ADD_AT_THE_END);
        DataBaseUtils.saveResult(listResults);
        listResults.clear();

        ObjectSet<CollectionPerformanceResultObject> result = DataBaseUtils.getCollectionResults();
        DataBaseUtils.printResults(result);
    }


    public static void initApplication() {

        Logger.getInstance().init();

        AppProperties.getInstance().init();

        DBProperties.getInstance().init();

        DBManager.getInstance().initDatabase();

    }


    public static void closeApp() {
        DBManager.getInstance().closeDatabase();
    }
}
