//package dev.f04c.pokemonapi.model.storage;
//
//import android.provider.BaseColumns;
//
//public final class SecureStorageContract {
//
//    private SecureStorageContract() {}
//
//    public static class AccessTokenEntry implements BaseColumns {
//        public static final String TABLE_NAME = "access_token";
//        public static final String COLUMN_ACCESS_TOKEN = "access_token";
//    }
//
//    public static final String SQL_CREATE_ACCESS_TOKEN_TABLE =
//            "CREATE TABLE " + AccessTokenEntry.TABLE_NAME + " (" +
//                    AccessTokenEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    AccessTokenEntry.COLUMN_ACCESS_TOKEN + " TEXT NOT NULL" + ")";
//
//    public static final String SQL_DROP_ACCESS_TOKEN_TABLE =
//            "DROP TABLE IF EXISTS " + AccessTokenEntry.TABLE_NAME;
//}
