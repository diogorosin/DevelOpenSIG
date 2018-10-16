package br.com.developen.sig.database;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    static DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    @TypeConverter
    public static Date fromTimestamp(String value) {

        if (value != null) {

            try {

                return df.parse(value);

            } catch (ParseException e) {

                e.printStackTrace();

            }

            return null;

        } else {

            return null;

        }

    }

    @TypeConverter
    public static String dateToTimestamp(Date value) {

        return value == null ? null : df.format(value);

    }

}