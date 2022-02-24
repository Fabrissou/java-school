package com.shulga.daniil;

import com.digdes.school.DatesToCronConvertException;
import java.util.List;

public class DatesToCron implements com.digdes.school.DatesToCronConverter {

    @Override
    public String convert(List<String> dates) throws DatesToCronConvertException {
        return null;
    }

    @Override
    public String getImplementationInfo() {
        return "ФИО: Шульга Даниил Александрович\n" +
                "Имя класса реализации: DatesToCron\n" +
                "Пакет: com.shulga.daniil\n" +
                "Cсылка на github: https://github.com/Fabrissou/";
    }
}
