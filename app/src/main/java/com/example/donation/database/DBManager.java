package com.example.donation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import com.example.donation.activities.Base;
import com.example.donation.models.Donation;

public class DBManager {

  private SQLiteDatabase database;
  private DBDesigner dbHelper;

  public DBManager(Context context) {
    dbHelper = new DBDesigner(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    database.close();
  }

  public void add(Donation d) {
    ContentValues values = new ContentValues();
    values.put("amount", d.amount);
    values.put("method", d.method);

    database.insert("donations", null, values);
  }

  public List<Donation> getAll() {
    List<Donation> donations = new ArrayList<Donation>();
    Cursor cursor = database.rawQuery("SELECT * FROM donations", null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Donation d = toDonation(cursor);
      donations.add(d);
      cursor.moveToNext();
    }
    cursor.close();
    return donations;
  }

  private Donation toDonation(Cursor cursor) {
    Donation pojo = new Donation();
    pojo.id = cursor.getInt(0);
    pojo.amount = cursor.getInt(1);
    pojo.method = cursor.getString(2);
    return pojo;
  }

  public void setTotalDonated(Base base) {
//    Log.v("Donate", "Setting total donate");
    Cursor c = database.rawQuery("SELECT SUM(amount) FROM donations", null);
    c.moveToFirst();
    if (!c.isAfterLast()) {
//      Log.v("Donate", "Here setting to " + c.getInt(0));
      base.app.totalDonated = c.getInt(0);
    }
  }

  public void reset() {
    database.delete("donations", null, null);
  }
}
