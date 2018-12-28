package br.com.developen.sig.util;

import android.text.format.DateFormat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

public class StringUtils {

    private static final DecimalFormatSymbols symbols;

    private static final DecimalFormat decimalFormatWithSymbol;

    private static final DecimalFormat decimalFormatOfQuantity;

    private static final DecimalFormat decimalFormat;

    private static final DateFormat dateFormat;

    static{

        symbols = new DecimalFormatSymbols();

        symbols.setDecimalSeparator(',');

        symbols.setGroupingSeparator('.');

        symbols.setCurrencySymbol("R$");

        decimalFormat = new DecimalFormat("###,###,###,###.##", symbols);

        decimalFormat.setMaximumFractionDigits(2);

        decimalFormat.setMinimumFractionDigits(2);

        decimalFormatWithSymbol = new DecimalFormat("R$ ###,###,###,###.##", symbols);

        decimalFormatWithSymbol.setMaximumFractionDigits(2);

        decimalFormatWithSymbol.setMinimumFractionDigits(2);

        decimalFormatOfQuantity = new DecimalFormat("###,###,###,###.###", symbols);

        dateFormat = new DateFormat();

    }

    public static String formatCurrencyWithSymbol(Double currency){

        return decimalFormatWithSymbol.format(currency);

    }

    public static String formatCurrency(Double currency){

        return decimalFormat.format(currency);

    }

    public static String formatQuantity(Double currency){

        decimalFormatOfQuantity.setMinimumFractionDigits(0);

        decimalFormatOfQuantity.setMaximumFractionDigits(3);

        return decimalFormatOfQuantity.format(currency);

    }

    public static String formatQuantityWithMinimumFractionDigit(Double currency){

        decimalFormatOfQuantity.setMinimumFractionDigits(3);

        decimalFormatOfQuantity.setMaximumFractionDigits(3);

        return decimalFormatOfQuantity.format(currency);

    }

    public static String formatDate(Date dateTime){

        return dateFormat.format("dd/MM/yyyy", dateTime).toString();

    }

    public static String formatDateTime(Date dateTime){

        return dateFormat.format("dd/MM/yyyy HH:mm:ss", dateTime).toString();

    }

    public static String formatShortDateTime(Date dateTime){

        return dateFormat.format("dd/MM HH:mm", dateTime).toString();

    }

    public static String leftPad(String string, Integer size, Character character){

        String result = string;

        if (string.length() > size)

            result = string.substring(0, size - 1);

        else

            while (result.length() < size)

                result = character + result;

        return result;

    }

    public static String rightPad(String string, Integer size, Character character){

        String result = string;

        if (string.length() > size)

            result = string.substring(0, size - 1);

        else

            while (result.length() < size)

                result = result + character;

        return result;

    }

}