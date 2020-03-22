package com.trantan.sqliteqltoanha.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.trantan.sqliteqltoanha.model.Building;
import com.trantan.sqliteqltoanha.model.Resident;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "building_manager";

    private static final String TABLE_BUILDING_NAME = "buildings";
    private static final String ID_BUILDING = "id";
    private static final String NAME_OF_BUILDING = "name";
    private static final String CODE_OF_BUILDING = "code_of_building";

    private static final String TABLE_RESIDENT_NAME = "residents";
    private static final String ID_RESIDENT = "id";
    private static final String NAME_OF_HOUSEHOLD = "name";
    private static final String ROOM_NUMBER = "room_number";
    private static final String NAME_OF_BUILDING_HOUSEHOLE = "name_of_buiding";
    private static final String PHONE_NUMBER = "phone_number";

    public static final String TAG = "DBManager";

    private String SQLQueryBuilding = "CREATE TABLE IF NOT EXISTS " + TABLE_BUILDING_NAME + " ("
            + ID_BUILDING + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CODE_OF_BUILDING + " TEXT, "
            + NAME_OF_BUILDING + " TEXT)";

    private String SQLQueryResident = "CREATE TABLE IF NOT EXISTS " + TABLE_RESIDENT_NAME + " ("
            + ID_RESIDENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_OF_HOUSEHOLD + " TEXT, "
            + ROOM_NUMBER + " INTEGER, "
            + NAME_OF_BUILDING_HOUSEHOLE + " TEXT, "
            + PHONE_NUMBER + " INTEGER)";

    private Context context;

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQueryBuilding);
        db.execSQL(SQLQueryResident);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDING_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESIDENT_NAME);

        switch (oldVersion){
            case 1:
                db.execSQL(SQLQueryBuilding);
                db.execSQL(SQLQueryResident);
            case 2:

        }
        onCreate(db);
        Log.d(TAG, "onUpgrade: ");
    }

    public void addBuilding(Building building) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CODE_OF_BUILDING, building.getmCode());
        contentValues.put(NAME_OF_BUILDING, building.getmName());

        database.insert(TABLE_BUILDING_NAME, null, contentValues);
        database.close();

        Log.d(TAG, "addBuilding: ");
    }

    public void addResident(Resident resident) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_OF_HOUSEHOLD, resident.getmNameOfHouseHold());
        contentValues.put(ROOM_NUMBER, resident.getmRoomNumber());
        contentValues.put(NAME_OF_BUILDING_HOUSEHOLE, resident.getmNameOfBuilding());
        contentValues.put(PHONE_NUMBER, resident.getmPhoneNumber());

        database.insert(TABLE_RESIDENT_NAME, null, contentValues);
        database.close();

        Log.d(TAG, "addResident: ");
    }

    public List<Building> getAllBuilding() {
        List<Building> listBuilding = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_BUILDING_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String name = cursor.getString(2);

                Building building = new Building(id, code, name);
                listBuilding.add(building);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return listBuilding;
    }

    public List<Resident> getAllResident() {
        List<Resident> listResident = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_RESIDENT_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nameOfHousehold = cursor.getString(1);
                String roomNumber = cursor.getString(2);
                String nameOfBuilding = cursor.getString(3);
                int phoneNumber = cursor.getInt(4);

                Resident resident = new Resident(id, nameOfHousehold, roomNumber, nameOfBuilding, phoneNumber);
                listResident.add(resident);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return listResident;
    }

    public int deleteBuilding(Building building) {
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(TABLE_BUILDING_NAME, ID_BUILDING + "=?", new String[]{String.valueOf(building.getmID())});
    }

    public int deleteResident(Resident resident) {
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(TABLE_RESIDENT_NAME, ID_RESIDENT + "=?", new String[]{String.valueOf(resident.getmID())});
    }

    public int updateBuilding(Building building){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CODE_OF_BUILDING, building.getmCode());
        contentValues.put(NAME_OF_BUILDING, building.getmName());

        return database.update(TABLE_BUILDING_NAME,contentValues,ID_BUILDING + "=?", new String[]{String.valueOf(building.getmID())});
    }

    public int updateResident(Resident resident){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_OF_HOUSEHOLD, resident.getmNameOfHouseHold());
        contentValues.put(ROOM_NUMBER, resident.getmRoomNumber());
        contentValues.put(NAME_OF_BUILDING_HOUSEHOLE, resident.getmNameOfBuilding());
        contentValues.put(PHONE_NUMBER, resident.getmPhoneNumber());

        return database.update(TABLE_RESIDENT_NAME,contentValues,ID_RESIDENT + "=?", new String[]{String.valueOf(resident.getmID())});
    }
}
