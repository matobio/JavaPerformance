package com.tobio.performance.database;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.tobio.performance.objects.CollectionPerformanceResultObject;

public class DataBaseUtils {

    public static void printResults(ObjectSet<?> result) {

        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }


    public static void saveResult(List<?> list) {
        ObjectContainer dbManager = DBManager.getInstance().getDatabaseManager();
        list.forEach(el -> dbManager.store(el));
    }


    public static <T> ObjectSet<T> getCollectionResults(Class<T> anyClass) {
        return DBManager.getInstance().getDatabaseManager().query(anyClass);
    }


    public static ObjectSet<CollectionPerformanceResultObject> getCollectionResults() {
        return DataBaseUtils.getCollectionResults(CollectionPerformanceResultObject.class);
    }

}
