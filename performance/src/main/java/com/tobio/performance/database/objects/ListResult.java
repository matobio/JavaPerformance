package com.tobio.performance.database.objects;

import java.io.Serializable;

public class ListResult implements Serializable {

    private static final long serialVersionUID  = 1L;

    protected long            operationDuration = 0;
    protected int             numerOfElements   = 0;
    protected String          collectionType    = null;
    protected String          operationType     = null;
    protected Class<?>        collectionClass   = null;


    public long getOperationDuration() {
        return this.operationDuration;
    }


    public void setOperationDuration(long operationDuration) {
        this.operationDuration = operationDuration;
    }


    public int getNumerOfElements() {
        return this.numerOfElements;
    }


    public void setNumerOfElements(int numerOfElements) {
        this.numerOfElements = numerOfElements;
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


    public Class<?> getCollectionClass() {
        return this.collectionClass;
    }


    public void setCollectionClass(Class<?> collectionClass) {
        this.collectionClass = collectionClass;
    }

}
