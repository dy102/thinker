package com.example.thinker.util;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Convertor {
    private Convertor() {
    }

    public static LocalDate stringToLocalDate(int year, int month, int day) throws DateTimeException {
        return LocalDate.of(year, month, day);
    }
}
