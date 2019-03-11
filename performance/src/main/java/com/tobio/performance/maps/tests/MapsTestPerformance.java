package com.tobio.performance.maps.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class MapsTestPerformance {

    protected static final boolean OPERATION_PUT      = true;
    protected static final boolean OPERATION_GET      = true;
    protected static final boolean OPERATION_REMOVE   = true;

    protected static final boolean TYPE_HASHMAP       = false;
    protected static final boolean TYPE_HASHTABLE     = true;
    protected static final boolean TYPE_LINKEDHASHMAP = false;
    protected static final boolean TYPE_TREEMAP       = false;


    public static void main(String[] args) {

        int size = 100000;

        performanceTest(size);
        performanceTest(size * 5);
        performanceTest(size * 10);
        // performanceTest(size * 100);

        System.exit(0);
    }


    private static void performanceTest(int size) {

        System.out.println();
        System.out.println(">>>> Size:  " + size);
        System.out.println();

        HashMap<String, Object> hashMap = new HashMap<>();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        Hashtable<String, Object> hashtable = new Hashtable<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(Integer.valueOf(i).toString());
        }
        Object[] array = (Object[]) list.toArray();

        if (OPERATION_PUT) {
            operationPut(array, hashMap, linkedHashMap, hashtable, treeMap);
        }
        if (OPERATION_GET) {
            operationGet(array, hashMap, linkedHashMap, hashtable, treeMap);
        }
        if (OPERATION_REMOVE) {
            operationRemove(array, hashMap, linkedHashMap, hashtable, treeMap);
        }

    }


    private static void operationPut(Object[] array, HashMap<String, Object> hashMap, LinkedHashMap<String, Object> linkedHashMap, Hashtable<String, Object> hashtable,
            TreeMap<String, Object> treeMap) {

        System.out.println(">> Operation <put>:  ");

        if (TYPE_HASHMAP) {
            mapPut("HashMap put", hashMap, array);
        }
        if (TYPE_HASHTABLE) {
            mapPut("Hashtable put", hashtable, array);
        }
        if (TYPE_LINKEDHASHMAP) {
            mapPut("LinkedHashMap put", linkedHashMap, array);
        }
        if (TYPE_TREEMAP) {
            mapPut("TreeMap put", treeMap, array);
        }
    }


    private static void operationGet(Object[] array, HashMap<String, Object> hashMap, LinkedHashMap<String, Object> linkedHashMap, Hashtable<String, Object> hashtable,
            TreeMap<String, Object> treeMap) {

        System.out.println(">> Operation <get>:  ");

        if (TYPE_HASHMAP) {
            mapGet("HashMap get", hashMap, array);
        }
        if (TYPE_HASHTABLE) {
            mapGet("Hashtable get", hashtable, array);
        }
        if (TYPE_LINKEDHASHMAP) {
            mapGet("LinkedHashMap get", linkedHashMap, array);
        }
        if (TYPE_TREEMAP) {
            mapGet("TreeMap get", treeMap, array);
        }
    }


    private static void operationRemove(Object[] array, HashMap<String, Object> hashMap, LinkedHashMap<String, Object> linkedHashMap, Hashtable<String, Object> hashtable,
            TreeMap<String, Object> treeMap) {

        System.out.println(">> Operation <remove>:  ");

        if (TYPE_HASHMAP) {
            mapRemove("HashMap remove", hashMap, array);
        }
        if (TYPE_HASHTABLE) {
            mapRemove("Hashtable remove", hashtable, array);
        }
        if (TYPE_LINKEDHASHMAP) {
            mapRemove("LinkedHashMap remove", linkedHashMap, array);
        }
        if (TYPE_TREEMAP) {
            mapRemove("TreeMap remove", treeMap, array);
        }

    }


    private static void mapPut(String type, Map<String, Object> map, Object[] array) {

        long myTotalMemoryBefore = Runtime.getRuntime().totalMemory();
        long startTime = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            map.put(Integer.valueOf(i).toString(), array[i]);
        }

        long endTime = System.nanoTime();
        long myTotalMemoryAfter = Runtime.getRuntime().totalMemory();
        printResults(type, startTime, endTime, myTotalMemoryBefore, myTotalMemoryAfter);
    }


    private static void mapGet(String type, Map<String, Object> map, Object[] array) {

        long startTime = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            map.get(array[i]);
        }

        long endTime = System.nanoTime();
        printResult(type, startTime, endTime);
    }


    private static void mapRemove(String type, Map<String, Object> map, Object[] array) {

        long startTime = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            map.remove(array[i]);
        }

        long endTime = System.nanoTime();
        printResult(type, startTime, endTime);
    }


    private static void printResults(String type, long startTime, long endTime, long myTotalMemoryBefore, long myTotalMemoryAfter) {

        long duration = endTime - startTime;
        long myMapMemory = myTotalMemoryAfter - myTotalMemoryBefore;

        double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS) / 1000.0;
        System.out.println(type + ":  " + duration + " ns, " + elapsedTimeInSeconds + " ms" + ". Memory -> " + myMapMemory + " bytes");
    }


    public static void printResult(String type, long startTime, long endTime) {

        long duration = endTime - startTime;
        double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS) / 1000.0;
        System.out.println(type + ": " + duration + "ns , " + elapsedTimeInSeconds + " ms");
    }

}
