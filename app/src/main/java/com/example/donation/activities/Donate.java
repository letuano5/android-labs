package com.example.donation.activities;

import android.content.Intent;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.donation.R;
import com.example.donation.models.Donation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

public class Donate extends Base {

  private Button donateButton;
  private RadioGroup paymentMethod;
  private ProgressBar progressBar;
  private NumberPicker amountPicker;
  private EditText amountText;
  private TextView amountTotal;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action",
                Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    donateButton = findViewById(R.id.donateButton);
    paymentMethod = findViewById(R.id.paymentMethod);
    progressBar = findViewById(R.id.progressBar);
    amountPicker = findViewById(R.id.amountPicker);
    amountText = findViewById(R.id.paymentAmount);
    amountTotal = findViewById(R.id.totalSoFar);
    amountPicker.setMinValue(0);
    amountPicker.setMaxValue(1000);
    progressBar.setMax(10000);
    progressBar.setProgress(app.totalDonated);
    String totalDonatedStr = "$" + app.totalDonated;
    amountTotal.setText(totalDonatedStr);

    // Avoid N/A transaction
    donateButton.setEnabled(false);
    paymentMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        donateButton.setEnabled(checkedId != -1);
      }
    });
  }

  public void buttonPressed(View view) {
    String method = "N/A";
    int idMethod = paymentMethod.getCheckedRadioButtonId();
    if (idMethod == R.id.banking) {
      method = "Online Banking";
    } else if (idMethod == R.id.cash) {
      method = "Cash";
    }
    int donatedAmount = amountPicker.getValue();
    if (donatedAmount == 0) {
      String text = amountText.getText().toString();
      if (!text.isEmpty()) {
        donatedAmount = Integer.parseInt(text);
      }
    }
    if (donatedAmount > 0) {
      app.newDonation(new Donation(donatedAmount, method));
      progressBar.setProgress(app.totalDonated);
      String totalDonatedStr = "$" + app.totalDonated;
      amountTotal.setText(totalDonatedStr);
    }
  }

  @Override
  public void reset(MenuItem item) {
    app.totalDonated = 0;
    app.dbManager.reset();
    progressBar.setProgress(app.totalDonated);
    amountTotal.setText("$0");
  }
}