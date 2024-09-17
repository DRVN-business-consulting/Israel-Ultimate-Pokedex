//package dev.f04c.pokemonapi.model.storage;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.provider.BaseColumns;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dev.f04c.pokemonapi.model.dto.response.UserDto;
//
//public class UserDao {
//
//    private UserDao() {}
//
//    @Nullable
//    public static UserDto getUser(int id) {
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//        UserDto userDto = null;
//
//        try {
//            db = SecureStorageHelper.getInstance().getReadableDatabase();
//
//            String[] projection = {
//                    BaseColumns._ID,
//                    UserContract.UserEntry.COLUMN_FIRST_NAME,
//                    UserContract.UserEntry.COLUMN_LAST_NAME,
//                    UserContract.UserEntry.COLUMN_AGE,
//                    UserContract.UserEntry.COLUMN_EMAIL,
//                    UserContract.UserEntry.COLUMN_PASSWORD,
//                    UserContract.UserEntry.COLUMN_CREATED_AT,
//                    UserContract.UserEntry.COLUMN_UPDATED_AT
//            };
//
//            String selection = UserContract.UserEntry._ID + " = ?";
//            String[] selectionArgs = { String.valueOf(id) };
//            String sortingOrder = UserContract.UserEntry.COLUMN_FIRST_NAME + " DESC";
//
//            cursor = db.query(
//                    UserContract.UserEntry.TABLE_NAME,
//                    projection,
//                    selection,
//                    selectionArgs,
//                    null,
//                    null,
//                    sortingOrder
//            );
//
//            if (cursor.moveToFirst()) {
//                userDto = UserDto.fromCursor(cursor);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) cursor.close();
//            if (db != null) db.close();
//        }
//
//        return userDto;
//    }
//
//    @NonNull
//    public static List<UserDto> getUsers() {
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//        List<UserDto> userDtos = new ArrayList<>();
//
//        try {
//            db = SecureStorageHelper.getInstance().getReadableDatabase();
//
//            String[] projection = {
//                    BaseColumns._ID,
//                    UserContract.UserEntry.COLUMN_FIRST_NAME,
//                    UserContract.UserEntry.COLUMN_LAST_NAME,
//                    UserContract.UserEntry.COLUMN_AGE,
//                    UserContract.UserEntry.COLUMN_EMAIL,
//                    UserContract.UserEntry.COLUMN_PASSWORD,
//                    UserContract.UserEntry.COLUMN_CREATED_AT,
//                    UserContract.UserEntry.COLUMN_UPDATED_AT
//            };
//
//            String sortingOrder = UserContract.UserEntry.COLUMN_FIRST_NAME + " DESC";
//
//            cursor = db.query(
//                    UserContract.UserEntry.TABLE_NAME,
//                    projection,
//                    null,
//                    null,
//                    null,
//                    null,
//                    sortingOrder
//            );
//
//            while (cursor.moveToNext()) {
//                UserDto userDto = UserDto.fromCursor(cursor);
//                userDtos.add(userDto);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) cursor.close();
//            if (db != null) db.close();
//        }
//
//        return userDtos;
//    }
//
//    public static boolean insertUser(String firstName,
//                                     String lastName,
//                                     int age,
//                                     String email,
//                                     String password) {
//        SQLiteDatabase db = null;
//        boolean success = false;
//
//        try {
//            db = SecureStorageHelper.getInstance().getWritableDatabase();
//
//            long creationTime = System.currentTimeMillis();
//            ContentValues values = new ContentValues();
//            values.put(UserContract.UserEntry.COLUMN_FIRST_NAME, firstName);
//            values.put(UserContract.UserEntry.COLUMN_LAST_NAME, lastName);
//            values.put(UserContract.UserEntry.COLUMN_AGE, age);
//            values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
//            values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);
//            values.put(UserContract.UserEntry.COLUMN_CREATED_AT, creationTime);
//            values.put(UserContract.UserEntry.COLUMN_UPDATED_AT, creationTime);
//
//            long id = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
//            success = id != -1;
//
//            // Log the access token stored
//            Log.d("UserDao", "User inserted with email: " + email + " and password (token): " + password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (db != null) db.close();
//        }
//
//        return success;
//    }
//
//    public static boolean updateUser(int id,
//                                     String firstName,
//                                     String lastName,
//                                     int age,
//                                     String email,
//                                     String password) {
//        SQLiteDatabase db = null;
//        boolean success = false;
//
//        try {
//            db = SecureStorageHelper.getInstance().getWritableDatabase();
//
//            long updateTime = System.currentTimeMillis();
//            ContentValues values = new ContentValues();
//            values.put(UserContract.UserEntry.COLUMN_FIRST_NAME, firstName);
//            values.put(UserContract.UserEntry.COLUMN_LAST_NAME, lastName);
//            values.put(UserContract.UserEntry.COLUMN_AGE, age);
//            values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
//            values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);
//            values.put(UserContract.UserEntry.COLUMN_UPDATED_AT, updateTime);
//
//            String selection = UserContract.UserEntry._ID + " = ?";
//            String[] selectionArgs = { String.valueOf(id) };
//
//            int count = db.update(
//                    UserContract.UserEntry.TABLE_NAME,
//                    values,
//                    selection,
//                    selectionArgs
//            );
//
//            success = count > 0;
//
//            // Log the access token updated
//            Log.d("UserDao", "User updated with ID: " + id + " and new password (token): " + password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (db != null) db.close();
//        }
//
//        return success;
//    }
//
//    public static boolean deleteUser(int id) {
//        SQLiteDatabase db = null;
//        boolean success = false;
//
//        try {
//            db = SecureStorageHelper.getInstance().getWritableDatabase();
//
//            String selection = UserContract.UserEntry._ID + " = ?";
//            String[] selectionArgs = { String.valueOf(id) };
//
//            int count = db.delete(
//                    UserContract.UserEntry.TABLE_NAME,
//                    selection,
//                    selectionArgs
//            );
//
//            success = count > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (db != null) db.close();
//        }
//
//        return success;
//    }
//
//    public static int getFirstUserId() {
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//        int id = -1;
//
//        try {
//            db = SecureStorageHelper.getInstance().getReadableDatabase();
//
//            String[] projection = {
//                    BaseColumns._ID
//            };
//
//            String sortingOrder = UserContract.UserEntry._ID + " ASC LIMIT 1";
//
//            cursor = db.query(
//                    UserContract.UserEntry.TABLE_NAME,
//                    projection,
//                    null,
//                    null,
//                    null,
//                    null,
//                    sortingOrder
//            );
//
//            if (cursor.moveToFirst()) {
//                id = cursor.getInt(cursor.getColumnIndexOrThrow(UserContract.UserEntry._ID));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) cursor.close();
//            if (db != null) db.close();
//        }
//
//        return id;
//    }
//}
