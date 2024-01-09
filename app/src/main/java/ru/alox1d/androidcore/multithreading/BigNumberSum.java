package ru.alox1d.androidcore.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BigNumberSum {
    private final static long VALUE = 1_000_000_000L;
    private static long sum = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Long>> results = new ArrayList<>();
        long valueDividedBy10 = VALUE / 10;
        for (int i = 0; i < 10; i++) {
            long from = valueDividedBy10 * i + 1;
            long to = valueDividedBy10 * (i + 1);
            PartitioningSum partitioningSum = new PartitioningSum(from, to);
            Future<Long> future = executorService.submit(partitioningSum);
            results.add(future);
        }
        executorService.shutdown();
        for (Future<Long> future : results) {
            long partSum = future.get();
            sum += partSum;
        }
        System.out.println("Сумма чисел от 1 до 1.000.000.000 = " + sum);
    }
}

class PartitioningSum implements Callable<Long> {
    private long from;
    private long to;
    private long partSum;

    public PartitioningSum(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() {
        for (long i = from; i <= to; i++) {
            partSum += i;
        }
        System.out.println("Сумма чисел от " + from + " до " + to + " = " + partSum);
        return partSum;
    }
}
