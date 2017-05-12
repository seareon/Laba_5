package com.seareon.util;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Misha Ro on 17.03.2017.
 */
public class Util {
    public static String getSelect(Object[] params) {
        String selectParams = "";

        if(params.length > 0) {
            selectParams = " where ";
            selectParams += getGenericParams(params, " and ");
        }

        return selectParams;
    }

    public  static  String getUpdate(Object[] params) {
        return " " + getGenericParams(params, ", ") + " where id = ?";
    }

    private static String getGenericParams(Object[] params, String separate) {
        String genericParams = "";

        for(int indexParams = 0; indexParams < params.length; indexParams++) {
            EnumWhereParams param = EnumWhereParams.valueOf(((String)params[indexParams]).toUpperCase());
            genericParams += param.getParam() +  " = ?";

            if(params.length - 1 > indexParams) {
                genericParams += separate;
            }
        }

        return genericParams;
    }

    public static String getInsert(Map<String, String> params) {
        String insertParamsNames = " (";
        String insertParams = ") values (";
        Iterator<String> it = params.keySet().iterator();
        String paramName;

        while (it.hasNext()) {
            paramName = it.next();
            EnumWhereParams param = EnumWhereParams.valueOf(paramName.toUpperCase());
            insertParamsNames += param.getParam();

            if(params.get(paramName).length() > 0) {
                insertParams += "?";
            } else {
                insertParams += "null";
                it.remove();
            }

            if(it.hasNext()) {
                insertParams += ", ";
                insertParamsNames += ", ";
            }
        }

        return  insertParamsNames + insertParams + ")";
    }

    public static boolean isInteger(String value) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    public static boolean isDouble(String value) {
        Pattern p = Pattern.compile("\\d+\\.\\d");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
