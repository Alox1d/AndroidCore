package ru.alox1d.androidcore.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Дима", "Саша", "Алёша", "Трофим", "Ира", "Алёша");
        System.out.println(names);

        List<Integer> lengths = names.stream().map(name -> name.length()).collect(Collectors.toList());
        System.out.println("Мапинг строк в длины: " + lengths);

        List<String> shortNames = names.stream().filter(name -> name.length() < 5).collect(Collectors.toList());
        System.out.println("Только короткие имена: " + shortNames);

        System.out.print("Результат метода forEach(): ");
        names.stream().forEach(name -> System.out.print(name + " - красавичк \uD83D\uDC4A "));
        System.out.println();

        String firstLetters = names.stream().reduce("", (accumulator, name) -> accumulator += name.charAt(0));
        System.out.println("Результат метода reduce(): " + firstLetters);

        System.out.print("Сортировка имен в алфавитном порядке: ");
        names.stream().sorted().forEach(name -> System.out.print(name + " "));
        System.out.println();

        System.out.print("Сортировка имен по длине: ");
        names.stream().sorted((name1, name2) -> name1.length() - name2.length())
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        long uniqNameCount = names.stream().distinct().count();
        System.out.println("Количество уникальных имен в списке: " + uniqNameCount);

        List<String> surnames = List.of("Иванов", "Петров", "Сидоров", "Тапочкин");
        List<List<String>> listOfLists = List.of(names, surnames);
        System.out.print("Объединение листов методом flatMap(): ");
        listOfLists.stream().flatMap(element -> element.stream()).forEach(element -> System.out.print(element + " "));
        System.out.println();

        System.out.println("------------------------------------------------------");
        Map<Integer, List<String>> groupedByLength = names.stream().collect(Collectors.groupingBy(e -> e.length()));
        System.out.println("Имена сгруппированные по длине: ");
        for (Map.Entry<Integer, List<String>> entry : groupedByLength.entrySet()) {
            System.out.println("Длина - " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("------------------------------------------------------");

        System.out.println("Имена сгруппированные по первой букве: ");
        Map<Boolean, List<String>> partitionedByA = names.stream()
                .collect(Collectors.partitioningBy(name -> name.startsWith("А")));
        for (Map.Entry<Boolean, List<String>> entry : partitionedByA.entrySet()) {
            System.out.println(entry.getValue() + " - эти имена начинаются на 'А'? - " + entry.getKey());
        }
        System.out.println("------------------------------------------------------");

        System.out.println("Первое имя: " + names.stream().findFirst().get());
        System.out.println("Самое \"маленькое\" имя (лексикографически): " + names.stream()
                .min((name1, name2) -> name1.compareTo(name2)).get());
        System.out.println("Самое \"большое\" имя по длине: " + names.stream()
                .max((name1, name2) -> name1.length() - name2.length()).get());

        System.out.print("Результат работы метода limit(2): ");
        System.out.println(names.stream().limit(2).collect(Collectors.toList()));
        System.out.print("Результат работы метода skip(2): ");
        System.out.println(names.stream().skip(2).collect(Collectors.toList()));
    }
}
