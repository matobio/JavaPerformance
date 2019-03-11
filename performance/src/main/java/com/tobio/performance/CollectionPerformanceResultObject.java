package com.tobio.performance;

import java.util.concurrent.TimeUnit;

public class CollectionPerformanceResultObject {

    public static final String COLLECTION_CLASS_UNDEFINED      = "COLLECTION_CLASS_UNDEFINED";

    public static final String COLLECTION_CLASS_ARRAYLIST      = "ArrayList";
    public static final String COLLECTION_CLASS_VECTOR         = "Vector";
    public static final String COLLECTION_CLASS_LINKED_LIST    = "LinkedList";

    public static final String COLLECTION_CLASS_HASHSET        = "HashSet";
    public static final String COLLECTION_CLASS_LINKED_HASHSET = "LinkedHashSet";
    public static final String COLLECTION_CLASS_TREESET        = "TreeSet";

    public static final String COLLECTION_CLASS_PRIORITY_QUEUE = "PriorityQueue";

    public static final String OPERATION_TYPE_ADD_AT_BEGGINING = "ADD_AT_BEGGINING";
    public static final String OPERATION_TYPE_ADD_AT_END       = "ADD_AT_END";
    public static final String OPERATION_TYPE_ADD_AT_MIDDLE    = "ADD_AT_MIDDLE";
    public static final String OPERATION_TYPE_GET              = "GET";
    public static final String OPERATION_TYPE_REMOVE           = "REMOVE";

    protected long             operationDuration               = 0;
    protected int              numerOfElements                 = 0;
    protected String           collectionType                  = null;
    protected String           operationType                   = null;
    protected Class<?>         collectionClass                 = null;


    public CollectionPerformanceResultObject() {

    }


    public CollectionPerformanceResultObject(long operationDuration, int numerOfElements, String operationType, Class<?> collectionClass) {
        this.operationDuration = operationDuration;
        this.numerOfElements = numerOfElements;
        this.operationType = operationType;
        this.collectionClass = collectionClass;
    }


    public String getCollectionType() {
        return this.collectionType;
    }


    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }


    public String getOperationType() {
        return this.operationType;
    }


    public void setOperationType(String operationType) {
        this.operationType = operationType;
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


    public Class<?> getCollectionClass() {
        return this.collectionClass;
    }


    public void setCollectionClass(Class<?> collectionClass) {
        this.collectionClass = collectionClass;
    }


    @Override
    public String toString() {

        double elapsedTime = TimeUnit.MILLISECONDS.convert(this.operationDuration, TimeUnit.NANOSECONDS) / 1000.0;

        return "Operation: " + this.operationType + ", " + this.collectionClass.getSimpleName() + ":  " + this.operationDuration + "ns, " + elapsedTime + "ms";
    }

}
