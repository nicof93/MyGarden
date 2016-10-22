package blackrobot.mygarden.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by niko_ on 23/07/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "bestgarden.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PLANTA = "CREATE TABLE " + DBContract.PlantaEntry.TABLE_NAME + "( " +
                DBContract.PlantaEntry.COLUMN_NAME_PLANTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBContract.PlantaEntry.COLUMN_NAME_NOMBRE + " TEXT NOT NULL," +
                DBContract.PlantaEntry.COLUMN_NAME_DESCRPCION + " TEXT NOT NULL," +
                DBContract.PlantaEntry.COLUMN_NAME_IMAGEN + " TEXT NOT NULL )";

        db.execSQL(SQL_CREATE_PLANTA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.PlantaEntry.TABLE_NAME);

        onCreate(db);
    }
}
