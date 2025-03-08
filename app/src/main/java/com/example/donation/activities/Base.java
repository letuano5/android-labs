package com.example.donation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.donation.R;
import com.example.donation.main.DonationApp;
import com.example.donation.models.Donation;
import java.util.ArrayList;
import java.util.List;

public class Base extends AppCompatActivity {

  public DonationApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    app = (DonationApp) getApplication();
    app.dbManager.open();
    app.dbManager.setTotalDonated(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    app.dbManager.close();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    super.onPrepareOptionsMenu(menu);
    MenuItem report = menu.findItem(R.id.menuReport);
    MenuItem donate = menu.findItem(R.id.menuDonate);
    MenuItem reset = menu.findItem(R.id.menuReset);
    boolean hasDonation = !app.dbManager.getAll().isEmpty();
    report.setEnabled(hasDonation);
    reset.setEnabled(hasDonation);
    donate.setVisible(!(this instanceof Donate));
    report.setVisible(!(this instanceof Report));
    reset.setVisible(this instanceof Donate);
    return true;
  }

  public void settings(MenuItem item) {
    Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show();
  }

  public void report(MenuItem item) {
    startActivity(new Intent(this, Report.class));
  }

  public void donate(MenuItem item) {
    startActivity(new Intent(this, Donate.class));
  }

  public void reset(MenuItem item) {
    Toast.makeText(this, "Resetting...", Toast.LENGTH_SHORT).show();
  }
}
