package com.tobio.performance.collections.lists.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.tobio.performance.objects.Person;

public class ListsGetElementsTestPerformance {

    protected static final boolean BASIC_LOOP          = false;
    protected static final boolean BASIC_LOOP_ITERATOR = true;
    protected static final boolean JAVA_8_STREAM       = false;


    public static void main(String[] args) {

        int size = 100000;

        // test(size);
        // test(size * 5);
        // test(size * 10);
        // test(size * 100);
        test(size * 300);

        System.exit(0);
    }


    private static void test(int size) {

        System.out.println(">>>> Size:  " + size);

        List<Person> list = new ArrayList<>();

        operationAdd(size, list);

        operationFindElements(size, list);

        System.out.println();
    }


    private static void operationFindElements(int size, List<Person> arrayList) {

        if (BASIC_LOOP) {
            getElementsBasicLooping(arrayList);
        }
        if (BASIC_LOOP_ITERATOR) {
            getElementsBasicLoopingWithIterator(arrayList);
        }
        if (JAVA_8_STREAM) {
            getElementsWithStreams(arrayList);
        }

    }


    private static List<Person> getElementsBasicLooping(List<Person> list) {

        List<Person> result = new ArrayList<>();

        long endTime = 0;
        long startTime = System.nanoTime();

        for (Person person : list) {
            if (person.getPersonId().equals(5)) {
                result.add(person);
            }
        }

        endTime = System.nanoTime();

        printResults("BasicLooping ", startTime, endTime);

        return result;
    }


    private static List<Person> getElementsBasicLoopingWithIterator(List<Person> list) {

        List<Person> result = new ArrayList<>();

        long endTime = 0;
        Iterator<Person> iterator = list.iterator();

        long startTime = System.nanoTime();

        Person person = null;
        while (iterator.hasNext()) {
            person = iterator.next();
            if (person.getPersonId().equals(5)) {
                result.add(person);
            }
        }

        endTime = System.nanoTime();

        printResults("BasicLoopingWithIterator ", startTime, endTime);

        return result;

    }


    private static List<Person> getElementsWithStreams(List<Person> list) {

        long endTime = 0;
        long startTime = System.nanoTime();

        List<Person> result = list.stream().filter(obj -> obj.getPersonId().equals(5)).collect(Collectors.toList());

        endTime = System.nanoTime();
        printResults("STREAMS ", startTime, endTime);

        return result;
    }


    private static void operationAdd(int size, List<Person> arrayList) {

        listAdd(size, arrayList);

    }


    private static void listAdd(int size, List<Person> list) {

        for (int i = 0; i < size; i++) {
            list.add(new Person(i % 20));
        }

    }


    private static void printResults(String header, long startTime, long endTime) {

        long duration = endTime - startTime;
        double elapsedTime = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS) / 1000.0;
        System.out.println(header + ": " + duration + " ns, " + elapsedTime + " ms");
    }

}
