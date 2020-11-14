package staslibs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LimitedQueueTest {
    LimitedQueue<String> photoQueue;

    @Before
    public void setUp() throws Exception {
        photoQueue = new LimitedQueue<String>(4);
    }

    @Test
    public void getLimit() {
        int expectedValue = 4;
        int actualValue = photoQueue.getLimit();
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void setLimit() {
        photoQueue.setLimit(5);
        int expectedValue = 5;
        int actualValue = photoQueue.getLimit();
        Assert.assertEquals(expectedValue, actualValue);

        // add more than limit
        photoQueue.add("4");
        photoQueue.add("5"); // <-5th element
        photoQueue.add("6");
        photoQueue.add("7");
        photoQueue.add("8");
        photoQueue.add("9");
        String expectedStrValue = "5";
        String actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);

        photoQueue.setLimit(2);
        expectedStrValue = "8";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
        expectedValue = 2;
        actualValue = photoQueue.getLimit();
        Assert.assertEquals(expectedValue, actualValue);

        photoQueue.setLimit(6);
        expectedStrValue = "9";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
        expectedValue = 6;
        actualValue = photoQueue.getLimit();
        Assert.assertEquals(expectedValue, actualValue);
        photoQueue.add("4");
        photoQueue.add("5");
        photoQueue.add("6");
        photoQueue.add("7");
        photoQueue.add("8");
        photoQueue.add("9");
        expectedStrValue = "4";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
    }

    @Test
    public void add() {
        photoQueue.setLimit(5);

        // add one element and get it
        photoQueue.add("1");
        String expectedStrValue = "1";
        String actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);

        // add few element
        photoQueue.add("1");
        photoQueue.add("2");
        photoQueue.add("3");
        expectedStrValue = "1";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);

        // more than limit
        photoQueue.add("4");
        photoQueue.add("5");
        photoQueue.add("6");
        photoQueue.add("7");
        photoQueue.add("8");
        photoQueue.add("9");
        expectedStrValue = "5";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);

    }

    @Test
    public void get() {
        photoQueue.clear();
        photoQueue.setLimit(5);
        // more than limit
        photoQueue.add("4");
        photoQueue.add("5");
        photoQueue.add("6");
        photoQueue.add("7");
        photoQueue.add("8");
        photoQueue.add("9");
        String expectedStrValue = "5";
        String actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);

        //get all elements

        expectedStrValue = "6";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
        expectedStrValue = "7";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
        expectedStrValue = "8";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
        expectedStrValue = "9";
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
        expectedStrValue = null;
        actualStringValue = photoQueue.get();
        Assert.assertEquals(expectedStrValue, actualStringValue);
    }

    @Test
    public void getSize() {
        photoQueue.clear();
        photoQueue.setLimit(5);
        //empty queue
        int expectedValue = 0;
        int actualValue = photoQueue.getSize();
        Assert.assertEquals(expectedValue, actualValue);

        // add one element and get it
        photoQueue.add("1");
        expectedValue = 1;
        actualValue = photoQueue.getSize();
        Assert.assertEquals(expectedValue, actualValue);
        String dummy = photoQueue.get();
        expectedValue = 0;
        actualValue = photoQueue.getSize();
        Assert.assertEquals(expectedValue, actualValue);

        // add few element
        photoQueue.add("1");
        photoQueue.add("2");
        photoQueue.add("3");
        expectedValue = 3;
        actualValue = photoQueue.getSize();
        Assert.assertEquals(expectedValue, actualValue);

        // more than limit
        photoQueue.add("4");
        photoQueue.add("5");
        photoQueue.add("6");
        photoQueue.add("7");
        photoQueue.add("8");
        photoQueue.add("9");
        expectedValue = photoQueue.getLimit();
        actualValue = photoQueue.getSize();
        Assert.assertEquals(expectedValue, actualValue);

        dummy = photoQueue.get();
        dummy = photoQueue.get();
        expectedValue = photoQueue.getLimit()-2;
        actualValue = photoQueue.getSize();
        Assert.assertEquals(expectedValue, actualValue);

    }
}