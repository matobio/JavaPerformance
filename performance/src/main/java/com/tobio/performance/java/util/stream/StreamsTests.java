package com.tobio.performance.java.util.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tobio.performance.App;
import com.tobio.performance.objects.StreamPerformanceResultObject;

public class StreamsTests {

    private static final String COLUMN1 = "column1";
    private static final String COLUMN2 = "column2";


    @Before
    public void setUp() {
        App.initApplication();
    }


    @After
    public void setDown() {
        App.closeApp();
    }


    @Test
    public void testIntStreamLoop() {

        int size = 100000000;
        int samples = 50;
        Map<String, Object> map = this.initData(size);

        List<StreamPerformanceResultObject> results = new ArrayList<>();
        for (int i = 0; i < samples; i++) {
            results.add(this.searchWithStream(map));
        }

        System.out.println(StreamPerformanceResultObject.printResult(results));
        System.out.println(results.get(0));

        results.clear();
        for (int i = 0; i < samples; i++) {
            results.add(this.searchWithFor(map));
        }

        System.out.println(StreamPerformanceResultObject.printResult(results));
        System.out.println(results.get(0));
    }


    protected Map<String, Object> initData(int numberOfElements) {

        List<Integer> column1Values = new ArrayList<>();
        List<Integer> column2Values = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            column1Values.add(i % 10);
            column2Values.add(i % 10);
        }
        Map<String, Object> map = new HashMap<>();
        map.put(StreamsTests.COLUMN1, column1Values);
        map.put(StreamsTests.COLUMN2, column2Values);
        return map;
    }


    @SuppressWarnings("unused")
    protected StreamPerformanceResultObject searchWithStream(Map<String, Object> map) {

        List<Integer> column1 = (List<Integer>) map.get(StreamsTests.COLUMN1);
        List<Object> column2 = (List<Object>) map.get(StreamsTests.COLUMN2);

        IntPredicate predicate = index -> column1.get(index).equals(0);

        long startTime = System.nanoTime();
        List<Object> matches = IntStream.range(0, column1.size()).filter(predicate).boxed().map(column2::get).collect(Collectors.toList());
        long endTime = System.nanoTime();

        long time = endTime - startTime;
        return new StreamPerformanceResultObject(time, column1.size(), matches.size());
    }


    @SuppressWarnings("unused")
    protected StreamPerformanceResultObject searchWithFor(Map<String, Object> map) {

        List<Integer> column1 = (List<Integer>) map.get(StreamsTests.COLUMN1);
        List<Object> column2 = (List<Object>) map.get(StreamsTests.COLUMN2);
        List<Object> matches = new ArrayList<>();

        int size = column1.size();
        IntPredicate predicate = index -> column1.get(index).equals(0);

        long startTime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            if (column1.get(i).equals(0)) {
                matches.add(column2.get(i));
            }
        }

        long endTime = System.nanoTime();

        long time = endTime - startTime;
        return new StreamPerformanceResultObject(time, column1.size(), matches.size());
    }

}
