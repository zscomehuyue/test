package jdk;

import org.junit.Test;

/**
 * @author: zscome
 * DateTime: 2020-07-08 16:28
 */
public class SplitTest {

    @Test
    public void split() {
        //MNO	CREATE_DATE	UPDATE_DATE	PROTOCOL_FLAG	INS_SILENCE_FLAG	ACT_SILENCE_FLAG	INSTALL_FLAG
        String l = "700000008936181	2020/7/8 10:54:38	2020/7/8 10:54:38	03	01	01	01";
        splitLine(l);

        System.out.println("\n\n\n");

        l = "MNO	CREATE_DATE	CREATE_TIME	UPDATE_DATE	UPDATE_TIME	PROTOCOL_FLAG	INS_SILENCE_FLAG ACT_SILENCE_FLAG INSTALL_FLAG";
        splitLine(l);
    }

    public void splitLine(String l) {
        String[] words = l.split("[\\s+]");
        for (int i = 0; i < words.length; i++) {
            System.out.println("words[" + i + "]=" + words[i]);
        }
    }
}
