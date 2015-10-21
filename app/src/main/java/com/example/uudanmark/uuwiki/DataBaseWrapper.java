package com.example.uudanmark.uuwiki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Locale;

import static android.database.sqlite.SQLiteDatabase.*;
import static android.database.sqlite.SQLiteDatabase.CREATE_IF_NECESSARY;
import static android.database.sqlite.SQLiteDatabase.OPEN_READWRITE;
import static android.database.sqlite.SQLiteDatabase.openDatabase;

/**
 * Created by soren on 11/08/15.
 */
public class DataBaseWrapper extends SQLiteOpenHelper
{
    private static String TAG = DataBaseWrapper.class.getName();
    private  String DB_PATH = " ";
    private  static String DB_NAME = "data.db";
    private SQLiteDatabase myDataBase = null;
    private final Context myContext;
    public static final int SCHEMA_VERSION = 1;



    public DataBaseWrapper(Context context)
    {
        super(context,DB_NAME,null, 1);

        this.myContext = context;
        DB_PATH="/data/data/" + context.getPackageName() + "/" + "databases/";
        switch (Log.v("log_tag", "DBPath: " + DB_PATH)) {
        }

    }



    public void createDataBase() throws IOException, SQLException {
        boolean dbExist = checkDataBase();
        if(dbExist){
            Log.v("log_tag", "database does exist");
            openDataBase();

        }else{
            Log.v("log_tag", "database does not exist");
            getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException{
        Log.v("copy", "her");
        InputStream myInput;
        myInput = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

   /* private boolean checkDataBase(){

        SQLiteDatabase db;

        File dbFile = new File(DB_PATH + DB_NAME);
        Log.v("CheckdataBase","her");
        db = openDatabase(DB_PATH + DB_NAME, null, OPEN_READWRITE);
        Log.v("log openDataBaseStart:", DB_PATH + DB_NAME);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
        db.setVersion(1);
        if(db != null) db.close();


            return dbFile.exists();


    }
    */

    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }


    public SQLiteDatabase openDataBase()

    {

        String mPath = DB_PATH + DB_NAME;
        Log.v("mPath", mPath);
        myDataBase = openDatabase(mPath, null, CREATE_IF_NECESSARY);
         //myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        if(myDataBase != null) return myDataBase;


        return null;
    }


    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.v(TAG, "Upgrading database, this will drop database and recreate.");
    }
}