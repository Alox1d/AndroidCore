package ru.alox1d.androidcore.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @see BigNumberSum
 */
@Deprecated(since = "Я не заметил, что был)")
public class SumNumbers {
    private static long sum = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long value = 1_000_000_000L;
        long dividedValue = value / 10;
        List<Future<Long>> featureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long from = dividedValue * i + 1;
            long to = dividedValue * (i + 1);
            var future = executorService.submit(new PartialSum(from, to));
            featureList.add(future);
        }
        featureList.forEach(future -> {
            try {
                sum += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        System.out.println(sum);
    }
}

class PartialSum implements Callable<Long> {
    long from;
    long to;
    long localSum;

    public PartialSum(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        for (long i = from; i <= to; i++) {
            localSum += i;
        }
        return localSum;
    }
}