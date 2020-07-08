package jdk;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zscome
 * DateTime: 2020-07-08 14:15
 */
public class ParseFile {
    @Test
    public void parseFile() {
        try {
            String select = "select * from etl.T_CPS_STAT_PROPERTY where MNO in (%s) ;\n";
            String update = "update  etl.T_CPS_STAT_PROPERTY set PROTOCOL_FLAG='%s' ,INS_SILENCE_FLAG='%s' ,ACT_SILENCE_FLAG='%s' ,INSTALL_FLAG='%s' ,UPDATE_DATE=sysdate  where MNO='%s' ;\n";
            List<String> list = FileUtils.readLines(new File("/huyue/workspace/workspace/test/src/main/resources/data.log"), "UTF-8");
            final AtomicInteger count = new AtomicInteger();
            List<String> updateSqls = new ArrayList<>(list.size());
            List<String> batchMonIds = new ArrayList<>(list.size());
            StringBuilder batchMonId = new StringBuilder();

            list.forEach(l -> {
                if (null != l && !"".equals(l)) {
                    int currentIndex = count.incrementAndGet();

                    //MNO	CREATE_DATE	UPDATE_DATE	PROTOCOL_FLAG	INS_SILENCE_FLAG	ACT_SILENCE_FLAG	INSTALL_FLAG
                    //700000008936181	2020/7/8 10:54:38	2020/7/8 10:54:38	03	01	01	01
                    String[] words = l.split("[\\s+]");

                    updateSqls.add(String.format(update, words[5], words[6], words[7], words[8], words[0]));
                    batchMonId.append("'").append(words[0]).append("',");

                    if (currentIndex % 500 == 0) {
                        batchMonId.deleteCharAt(batchMonId.length() - 1);
                        batchMonIds.add(batchMonId.toString());
                        batchMonId.delete(0, batchMonId.length());
                    }
                }
            });

            //bak sql
            File file = new File("/zm/sql.sql");
            file.createNewFile();

            batchMonIds.forEach(bs -> writeSql(file, String.format(select, bs)));

            if (batchMonId.length() > 0 && batchMonId.toString().endsWith(",")) {
                writeSql(file, String.format(select, batchMonId.deleteCharAt(batchMonId.length() - 1)));
            }

            //update sql
            updateSqls.forEach(sql -> writeSql(file, sql));

            System.out.println("all sql count:" + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSql(File file, String sql) {
        try {
            System.out.println(sql);
            FileUtils.write(file, sql, "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
