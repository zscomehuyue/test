package jdk;


import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

/**
 * @author: zscome
 * DateTime: 2020-02-08 10:27
 */
public class CompletableFutureTest {
    private static String name = "ddd";
    private static DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @org.junit.Test
    public void exception() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("1111111111111");
            if (true) {
                // throw new RuntimeException("test1");
            }
            return name;
        });
        f1.join();
        if (f1.isCompletedExceptionally()) {
            System.err.println("okokokokokokokokokok");
        }
    }

    public void test(String[] args) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("1111111111111");
            if (true) {
                throw new RuntimeException("test1");
            }
            return name;
        }).exceptionally(e -> {
            System.err.println("11111==" + e.getMessage());
            if (null != e) {
                //throw new RuntimeException(e);
            }
            return name;
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("2222222222222");
            if (true) {
                throw new RuntimeException("test1");
            }
            return name;
        });
        f1.join();
        if (f1.isCompletedExceptionally()) {
            System.err.println("okokokokokokokokokok");
        }
        CompletableFuture<Void> f = CompletableFuture.allOf(f1, f2);
        f.join();
        System.out.println(f);
        if (f.isCompletedExceptionally()) {
            System.err.println("okokokokokokokokokok");
        }
        System.out.println("test111111");
    }

}
