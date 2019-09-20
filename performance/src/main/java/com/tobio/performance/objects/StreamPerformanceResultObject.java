package com.tobio.performance.objects;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StreamPerformanceResultObject {

    protected long operationDuration  = 0;
    protected int  numerOfElements    = 0;
    protected long foundElementsCount = 0;


    public StreamPerformanceResultObject() {

    }


    public StreamPerformanceResultObject(long operationDuration, int numerOfElements, long foundElementsCount) {
        this.operationDuration = operationDuration;
        this.numerOfElements = numerOfElements;
        this.foundElementsCount = foundElementsCount;
    }


    public static long getAverageTime(List<StreamPerformanceResultObject> list) {
        if (list.isEmpty()) {
            return 0;
        }
        return list.stream().map(StreamPerformanceResultObject::getOperationDuration).reduce((a, b) -> a + b).get() / list.size();
    }


    public int getNumerOfElements() {
        return this.numerOfElements;
    }


    public void setNumerOfElements(int numerOfElements) {
        this.numerOfElements = numerOfElements;
    }


    public long getOperationDuration() {
        return this.operationDuration;
    }


    public void setOperationDuration(long operationDuration) {
        this.operationDuration = operationDuration;
    }


    @Override
    public String toString() {

        double elapsedTime = StreamPerformanceResultObject.nanosecondsToMiliseconds(this.operationDuration);

        return "Size: " + this.numerOfElements + ", Count: " + this.foundElementsCount + ", Average time: " + this.operationDuration + " ns, " + elapsedTime + " ms";

    }


    public static String printResult(List<StreamPerformanceResultObject> list) {

        long averageTime = StreamPerformanceResultObject.getAverageTime(list);
        double elapsedTime = StreamPerformanceResultObject.nanosecondsToMiliseconds(averageTime);

        return "Size: " + list.get(0).getNumerOfElements() + ", Average time: " + averageTime + " ns, " + elapsedTime + " ms";
    }


    protected static double nanosecondsToMiliseconds(long ns) {
        return TimeUnit.MILLISECONDS.convert(ns, TimeUnit.NANOSECONDS);
    }


    public long getFoundElementsCount() {
        return this.foundElementsCount;
    }


    public void setFoundElementsCount(long foundElementsCount) {
        this.foundElementsCount = foundElementsCount;
    }
}
