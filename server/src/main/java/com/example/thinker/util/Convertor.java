package com.example.thinker.util;

import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;

@Component
public class Convertor {
    private Convertor() {
    }

    public static LocalDate stringToLocalDate(int year, int month, int day) throws DateTimeException {
        return LocalDate.of(year, month, day);
    }
}
