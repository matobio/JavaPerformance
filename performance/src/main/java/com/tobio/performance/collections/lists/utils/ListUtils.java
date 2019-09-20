package com.tobio.performance.collections.lists.utils;

import java.util.List;

import com.tobio.performance.objects.CollectionPerformanceResultObject;

public class ListUtils {

    private ListUtils() {
        // Empty
    }


    public static CollectionPerformanceResultObject addElementsAtTheEnd(int numerOfElements, List<Integer> list) {

        long startTime = System.nanoTime();

        for (int i = 0; i < numerOfElements; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();

        long time = endTime - startTime;

        return new CollectionPerformanceResultObject(time, numerOfElements, CollectionPerformanceResultObject.OPERATION_TYPE_ADD_AT_END, list.getClass());
    }


    public static CollectionPerformanceResultObject addElementsAtTheBeginning(int numerOfElements, List<Integer> list) {

        long startTime = System.nanoTime();

        for (int i = 0; i < numerOfElements; i++) {
            list.add(0, i);
        }
        long endTime = System.nanoTime();

        long time = endTime - startTime;

        return new CollectionPerformanceResultObject(time, numerOfElements, CollectionPerformanceResultObject.OPERATION_TYPE_ADD_AT_BEGGINING, list.getClass());
    }


    public static CollectionPerformanceResultObject addElementsAtTheMiddle(int numerOfElements, List<Integer> list) {

        long startTime = System.nanoTime();

        for (int i = 0; i < numerOfElements; i++) {
            list.add(i / 2, i);
        }
        long endTime = System.nanoTime();

        long time = endTime - startTime;

        return new CollectionPerformanceResultObject(time, numerOfElements, CollectionPerformanceResultObject.OPERATION_TYPE_ADD_AT_MIDDLE, list.getClass());
    }


    public static <T> CollectionPerformanceResultObject removeElements(List<T> list) {

        int numerOfElements = list.size();

        long startTime = System.nanoTime();

        for (int i = numerOfElements - 1; i >= 0; i--) {
            list.remove(i);
        }
        long endTime = System.nanoTime();

        long time = endTime - startTime;

        return new CollectionPerformanceResultObject(time, numerOfElements, CollectionPerformanceResultObject.OPERATION_TYPE_REMOVE, list.getClass());
    }


    public static <T> CollectionPerformanceResultObject getElements(List<T> list) {

        int numerOfElements = list.size();

        long startTime = System.nanoTime();

        for (int i = 0; i < numerOfElements; i++) {
            list.get(i);
        }
        long endTime = System.nanoTime();

        long time = endTime - startTime;

        return new CollectionPerformanceResultObject(time, numerOfElements, CollectionPerformanceResultObject.OPERATION_TYPE_GET, list.getClass());
    }

}
