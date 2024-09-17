//package dev.f04c.pokemonapi.model.storage;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class SecureStorageHelper extends SQLiteOpenHelper {
//
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "app.db";
//
//    private static SecureStorageHelper instance;
//
//    // Singleton initialization
//    public static void initialize(Context context) {
//        if (instance == null) {
//            instance = new SecureStorageHelper(context.getApplicationContext());
//        }
//    }
//
//    public static SecureStorageHelper getInstance() {
//        if (instance == null) {
//            throw new IllegalStateException("SecureStorageHelper not initialized");
//        }
//        return instance;
//    }
//
//    private SecureStorageHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Create our tables
//        db.execSQL(UserContract.SQL_CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Migrate our tables
//        db.execSQL(UserContract.SQL_DROP_TABLE);
//        onCreate(db);
//    }
//
//    @Override
//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onUpgrade(db, oldVersion, newVersion);
//    }
//
//
//
//    public void saveAccessToken(String accessToken) {
//        SQLiteDatabase db = null;
//        try {
//            db = getWritableDatabase();
//
//            ContentValues values = new ContentValues();
//            values.put(SecureStorageContract.AccessTokenEntry.COLUMN_ACCESS_TOKEN, accessToken);
//
//            // Check if the token already exists
//            long count = db.insertWithOnConflict(
//                    SecureStorageContract.AccessTokenEntry.TABLE_NAME,
//                    null,
//                    values,
//                    SQLiteDatabase.CONFLICT_REPLACE
//            );
//
//            if (count == -1) {
//                throw new Exception("Failed to insert or update access token");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (db != null) db.close();
//        }
//    }
//}
