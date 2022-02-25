package com.shulga.daniil;

import com.digdes.school.DatesToCronConvertException;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DatesToCron implements com.digdes.school.DatesToCronConverter {

    private String builder(Set<Integer> set) {

        StringBuilder stringBuilder = new StringBuilder();
        int[] array = new int[set.size() + 1];
        AtomicInteger i = new AtomicInteger();

        set.forEach(el -> {
            array[i.get()] = el;
            i.getAndIncrement();
        });

        for (int k = 0; k < array.length - 1; k++) {
            if (array[k] + 1 == array[k + 1]) {

                int g = k + 1;
                while ((array[g] + 1 == array[g + 1]) || (array[g] == array[g + 1])) {
                    g++;
                }

                stringBuilder.append(array[k]);
                stringBuilder.append("-");
                stringBuilder.append(array[g]);
                k = g;
            } else {
                stringBuilder.append(array[k]);
            }

            if (k + 2 != array.length) {
                stringBuilder.append(",");
            }
        }

        stringBuilder.append(" ");

        return stringBuilder.toString();
    }

    @Override
    public String convert(List<String> dates) throws DatesToCronConvertException {
        Set<Integer> seconds = new TreeSet<>();
        Set<Integer> minutes = new TreeSet<>();
        Set<Integer> hours = new TreeSet<>();
        Set<Integer> days = new TreeSet<>();
        Set<Integer> months = new TreeSet<>();
        Set<Integer> daysOfWeek = new TreeSet<>();

        try {
            dates.forEach(date -> {
                String[] data = date.split("T|-|:");
                if (data.length != 6) {
                    throw new NumberFormatException();
                }
                LocalDate date2 = LocalDate.parse(date.split("T")[0]);
                seconds.add(Integer.parseInt(data[5]));
                minutes.add(Integer.parseInt(data[4]));
                hours.add(Integer.parseInt(data[3]));
                days.add(Integer.parseInt(data[2]));
                months.add(Integer.parseInt(data[1]));
                daysOfWeek.add(date2.getDayOfWeek().getValue());
            });
        } catch (NumberFormatException e) {
            throw new DatesToCronConvertException();
        }

        return builder(seconds) + builder(minutes) + builder(hours) + builder(days) + builder(months) + builder(daysOfWeek);
    }

    @Override
    public String getImplementationInfo() {
        return "ФИО: Шульга Даниил Александрович\n" +
                "Имя класса реализации: DatesToCron\n" +
                "Пакет: com.shulga.daniil\n" +
                "Cсылка на github: https://github.com/Fabrissou/java-school/";
    }
}
