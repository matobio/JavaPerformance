package com.tobio.performance.collections.lists.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

class ListUtilsTest {

    @Test
    void testAddElementsAtTheEnd() {

        List<Integer> list = new ArrayList<>();
        ListUtils.addElementsAtTheEnd(5, list);

        Assert.assertEquals(5, list.size());
        Assert.assertEquals(Arrays.asList(0, 1, 2, 3, 4), list);

        list = new ArrayList<>();
        ListUtils.addElementsAtTheEnd(1000, list);

        Assert.assertEquals(1000, list.size());

        list = new ArrayList<>();
        ListUtils.addElementsAtTheEnd(12345, list);

        Assert.assertEquals(12345, list.size());
    }


    @Test
    void testAddElementsAtTheBeginning() {

        List<Integer> list = new ArrayList<>();
        ListUtils.addElementsAtTheBeginning(5, list);

        Assert.assertEquals(5, list.size());
        Assert.assertEquals(Arrays.asList(4, 3, 2, 1, 0), list);

        list = new ArrayList<>();
        ListUtils.addElementsAtTheBeginning(1000, list);

        Assert.assertEquals(1000, list.size());

        list = new ArrayList<>();
        ListUtils.addElementsAtTheBeginning(12345, list);

        Assert.assertEquals(12345, list.size());

    }


    @Test
    void testAddElementsAtInTheMiddle() {

        List<Integer> list = new ArrayList<>();
        ListUtils.addElementsAtTheMiddle(5, list);

        Assert.assertEquals(5, list.size());
        Assert.assertEquals(Arrays.asList(1, 3, 4, 2, 0), list);

        list = new ArrayList<>();
        ListUtils.addElementsAtTheMiddle(1000, list);

        Assert.assertEquals(1000, list.size());

        list = new ArrayList<>();
        ListUtils.addElementsAtTheMiddle(12345, list);

        Assert.assertEquals(12345, list.size());

    }


    @Test
    void testRemoveElements() {

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));

        ListUtils.removeElements(list);

        Assert.assertEquals(0, list.size());
    }

}
