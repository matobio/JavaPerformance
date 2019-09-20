package com.tobio.performance.collections.lists.tests;

import java.util.ArrayList;
import java.util.List;

import com.tobio.performance.collections.lists.utils.ListUtils;
import com.tobio.performance.objects.CollectionPerformanceResultObject;

public class ListsTestPerformance {

    public static final int ADD_AT_THE_BEGINNING = 1;
    public static final int ADD_AT_THE_MIDDLE    = 2;
    public static final int ADD_AT_THE_END       = 3;


    public static List<CollectionPerformanceResultObject> runTestOperationAdd(int numOfTries, int numerOfElementsToAdd, List<Integer> list, int operationAddType) {

        List<CollectionPerformanceResultObject> listResults = new ArrayList<>();

        for (int i = 0; i < numOfTries; i++) {
            list.clear();
            listResults.add(ListsTestPerformance.operationAdd(numerOfElementsToAdd, list, operationAddType));
        }

        return listResults;
    }


    public static List<CollectionPerformanceResultObject> runTestOperationGet(int numOfTries, List<?> list) {

        List<CollectionPerformanceResultObject> listResults = new ArrayList<>();

        for (int i = 0; i < numOfTries; i++) {
            listResults.add(ListsTestPerformance.operationGet(list));
        }

        return listResults;
    }


    public static List<CollectionPerformanceResultObject> runTestOperationRemove(int numOfTries, List<?> list) {

        List<CollectionPerformanceResultObject> listResults = new ArrayList<>();

        for (int i = 0; i < numOfTries; i++) {
            listResults.add(ListsTestPerformance.operationRemove(list));
        }

        return listResults;
    }


    protected static CollectionPerformanceResultObject operationRemove(List<?> list) {
        return ListUtils.removeElements(list);
    }


    protected static CollectionPerformanceResultObject operationGet(List<?> list) {
        return ListUtils.getElements(list);
    }


    protected static CollectionPerformanceResultObject operationAdd(int numerOfElementsToAdd, List<Integer> list, int operationAddType) {

        CollectionPerformanceResultObject result = new CollectionPerformanceResultObject();

        if (operationAddType == ListsTestPerformance.ADD_AT_THE_BEGINNING) {
            result = ListUtils.addElementsAtTheBeginning(numerOfElementsToAdd, list);
        } else if (operationAddType == ListsTestPerformance.ADD_AT_THE_MIDDLE) {
            result = ListUtils.addElementsAtTheMiddle(numerOfElementsToAdd, list);
        } else if (operationAddType == ListsTestPerformance.ADD_AT_THE_END) {
            result = ListUtils.addElementsAtTheEnd(numerOfElementsToAdd, list);
        }

        return result;
    }

}
