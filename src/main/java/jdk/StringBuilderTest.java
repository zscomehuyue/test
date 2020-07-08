package jdk;

import org.junit.Test;

/**
 * @author: zscome
 * DateTime: 2020-07-08 16:12
 */
public class StringBuilderTest {

    @Test
    public void delete() {
        StringBuilder batchMonId = new StringBuilder();
        batchMonId.append("b");
        System.out.println(batchMonId.length());
        batchMonId.delete(0, batchMonId.length());
        System.out.println(batchMonId.length());
    }
}
