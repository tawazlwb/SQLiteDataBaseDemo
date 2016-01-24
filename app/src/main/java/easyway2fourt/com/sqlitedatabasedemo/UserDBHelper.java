package easyway2fourt.com.sqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MMD on 23/01/2016.
 */
public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSIONS = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+UserContract.newUserInfo.TABLE_NAME+"("+UserContract.newUserInfo.USER_NAME+" TEXT,"
            +UserContract.newUserInfo.USER_MOB+" TEXT,"+UserContract.newUserInfo.USER_EMAIL+" TEXT);";

    public UserDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSIONS);
        Log.e("DATABASE CREATIONS", "Database created / opened ...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE CREATIONS", "Table created ...");
    }

    public void addInformations(String name,String mobile, String email, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.newUserInfo.USER_NAME, name);
        contentValues.put(UserContract.newUserInfo.USER_MOB, mobile);
        contentValues.put(UserContract.newUserInfo.USER_EMAIL, email);
        db.insert(UserContract.newUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE CREATIONS", "One row is inserted ...");
    }

    public Cursor getInformations(SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {UserContract.newUserInfo.USER_NAME, UserContract.newUserInfo.USER_MOB, UserContract.newUserInfo.USER_EMAIL};
        cursor = db.query(UserContract.newUserInfo.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public Cursor getContact(String userName, SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {UserContract.newUserInfo.USER_MOB, UserContract.newUserInfo.USER_EMAIL};
        String selection = UserContract.newUserInfo.USER_NAME + " LIKE ?";
        String[] selection_args = {userName};

        cursor = db.query(UserContract.newUserInfo.TABLE_NAME, projections, selection, selection_args, null, null, null);
        return cursor;
    }

    public void deleteInformations(String userName, SQLiteDatabase db){
        String selection = UserContract.newUserInfo.USER_NAME + " LIKE ?";
        String[] selection_args = {userName};
        db.delete(UserContract.newUserInfo.TABLE_NAME, selection, selection_args);
    }

    public int updateInformations(String oldName, String newName, String newMobile, String newEmail, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.newUserInfo.USER_NAME, newName);
        contentValues.put(UserContract.newUserInfo.USER_MOB, newMobile);
        contentValues.put(UserContract.newUserInfo.USER_EMAIL, newEmail);

        String selection = UserContract.newUserInfo.USER_NAME + " LIKE ?";
        String[] selection_args = {oldName};

        int count = db.update(UserContract.newUserInfo.TABLE_NAME, contentValues, selection, selection_args);
        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
