//package dev.f04c.pokemonapi.model.storage;
//
//import android.provider.BaseColumns;
//
//public class UserContract {
//
//    private UserContract() {}
//
//    public static final String SQL_CREATE_TABLE =
//            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
//                    UserEntry._ID + " INTEGER PRIMARY KEY," +
//                    UserEntry.COLUMN_FIRST_NAME + " TEXT," +
//                    UserEntry.COLUMN_LAST_NAME + " TEXT," +
//                    UserEntry.COLUMN_AGE + " INTEGER," +
//                    UserEntry.COLUMN_EMAIL + " TEXT," +
//                    UserEntry.COLUMN_PASSWORD + " TEXT," +
//                    UserEntry.COLUMN_CREATED_AT + " INTEGER," +
//                    UserEntry.COLUMN_UPDATED_AT + " INTEGER" +
//                    ")";
//
//    public static final String SQL_DROP_TABLE =
//            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
//
//    public static class UserEntry implements BaseColumns {
//        public static final String TABLE_NAME = "user";
//        public static final String COLUMN_FIRST_NAME = "first_name";
//        public static final String COLUMN_LAST_NAME = "last_name";
//        public static final String COLUMN_AGE = "age";
//        public static final String COLUMN_EMAIL = "email";
//        public static final String COLUMN_PASSWORD = "password";
//        public static final String COLUMN_CREATED_AT = "created_at";
//        public static final String COLUMN_UPDATED_AT = "updated_at";
//    }
//}