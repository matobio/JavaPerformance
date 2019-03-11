package com.tobio.performance.collections.lists.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.tobio.performance.objects.Person;

public class ListsFindElementTestPerformance {

    protected static final boolean OPERATION_ADD       = true;
    protected static final boolean OPERATION_FIND      = true;
    protected static final boolean BASIC_LOOP          = true;
    protected static final boolean BASIC_LOOP_ITERATOR = true;
    protected static final boolean JAVA_8_STREAM       = true;


    public static void main(String[] args) {

        int size = 100000;

        performanceTest(size);
        performanceTest(size * 5);
        performanceTest(size * 10);
        // performanceTest(size * 100);

        System.exit(0);
    }


    private static void performanceTest(int size) {

        System.out.println(">>>> Size:  " + size);

        List<Person> arrayList = new ArrayList<>();

        if (OPERATION_ADD) {
            operationAdd(size, arrayList);
        }
        if (BASIC_LOOP) {
            operationFind(size, arrayList);
        }

        System.out.println();
    }


    private static void operationFind(int size, List<Person> arrayList) {

        if (BASIC_LOOP) {
            findBasicLooping(size, arrayList);
        }
        if (BASIC_LOOP_ITERATOR) {
            findBasicLoopingWithIterator(size, arrayList);
        }
        if (JAVA_8_STREAM) {
            findWithStreams(size, arrayList);
        }

    }


    private static Person findBasicLooping(int size, List<Person> list) {

        int element = size - 1;

        Person result = null;
        long endTime = 0;
        long startTime = System.nanoTime();

        for (Person person : list) {
            if (person.getPersonId().equals(element)) {
                endTime = System.nanoTime();
                result = person;
                break;
            }
        }
        if (result == null) {
            endTime = System.nanoTime();
        }

        printResults("BasicLooping ", startTime, endTime);

        return result;

    }


    private static Person findBasicLoopingWithIterator(int size, List<Person> list) {

        Person result = null;
        long endTime = 0;

        int element = size - 1;

        Iterator<Person> iterator = list.iterator();

        long startTime = System.nanoTime();

        while (iterator.hasNext()) {

            Person person = iterator.next();

            if (person.getPersonId().equals(element)) {
                endTime = System.nanoTime();
                result = person;
                break;
            }
        }
        if (result == null) {
            endTime = System.nanoTime();
        }

        printResults("BasicLoopingWithIterator ", startTime, endTime);

        return result;

    }


    private static Person findWithStreams(int size, List<Person> list) {

        int element = size - 1;

        Person result = null;
        long endTime = 0;
        long startTime = System.nanoTime();

        result = list.stream().filter(obj -> obj.getPersonId().equals(element)).findFirst().orElse(null);

        endTime = System.nanoTime();
        printResults("STREAMS ", startTime, endTime);

        return result;
    }


    private static void operationAdd(int size, List<Person> arrayList) {

        listAdd(size, arrayList);

    }


    private static void listAdd(int size, List<Person> list) {

        for (int i = 0; i < size; i++) {
            list.add(new Person(i));
        }

    }


    private static void printResults(String header, long startTime, long endTime) {

        long duration = endTime - startTime;
        double elapsedTime = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS) / 1000.0;
        System.out.println(header + ": " + duration + " ns, " + elapsedTime + " ms");
    }

}
