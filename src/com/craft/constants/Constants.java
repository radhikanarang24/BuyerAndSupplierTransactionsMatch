package com.craft.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {
    public static final Integer INTEGER_THRESHOLD = 2;
    public static final Integer BIGDECIMAL_THRESHOLD = 2;
    public static final Integer STRING_THRESHOLD = 2;
    public static final Integer DATE_THRESHOLD = 2;
    public static final Integer PARTIAL_MATCH_THRESHOLD = 15;
    public static final Integer STRING_MULTIPLICATION_FACTOR = 10;
    public static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
}
