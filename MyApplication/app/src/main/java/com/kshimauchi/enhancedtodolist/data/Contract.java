package com.kshimauchi.enhancedtodolist.data;

import android.provider.BaseColumns;

/**
 * Created by kshim on 7/17/2017.
 */

public class Contract {
    //Uses an interface to define the most common names to reduce repitition
    public static class TABLE_TODO implements BaseColumns {
        public static final String TABLE_NAME = "todoitems";

        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DUE_DATE = "duedate";
        //new column for the spinner
        public static final String COLUMN_CHOICE= "choice";
    }
}
