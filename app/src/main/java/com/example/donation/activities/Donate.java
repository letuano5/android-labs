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
    amountTotal.setText("$0");
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
      newDonation(new Donation(donatedAmount, method));
      progressBar.setProgress(totalDonated);
      String totalDonatedStr = "$" + totalDonated;
      amountTotal.setText(totalDonatedStr);
    }
  }
}

//package com.example.donation.activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.example.donation.R;
//import com.google.android.material.snackbar.Snackbar;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.util.Log;
//import android.view.View;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//import com.example.donation.databinding.ActivityMainBinding;
//
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Button;
//import android.widget.NumberPicker;
//import android.widget.ProgressBar;
//import android.widget.RadioGroup;
//
//public class Donate extends Base {
//
//  private AppBarConfiguration appBarConfiguration;
//  private ActivityMainBinding binding;
//
//  private Button donateButton;
//  private RadioGroup paymentMethod;
//  private ProgressBar progressBar;
//  private NumberPicker amountPicker;
//  private int totalMoney = 0;
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//
//    binding = ActivityMainBinding.inflate(getLayoutInflater());
//    setContentView(binding.getRoot());
//
//    setSupportActionBar(binding.toolbar);
//
//    NavController navController = Navigation.findNavController(this,
//        R.id.nav_host_fragment_content_main);
//    appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//    binding.fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAnchorView(R.id.fab)
//            .setAction("Action", null).show();
//      }
//    });
//
//    donateButton = (Button) findViewById(R.id.donateButton);
//    if (donateButton != null) {
//      Log.v("Donate", "Init successfully");
//    }
//
//    paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
//    amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
//    progressBar = (ProgressBar) findViewById(R.id.progressBar);
//
//    amountPicker.setMaxValue(1000);
//    amountPicker.setMinValue(0);
//
//    progressBar.setMax(10000);
//  }
//
//  public void buttonPressed(View view) {
//    int amount = amountPicker.getValue();
//    int paymentId = paymentMethod.getCheckedRadioButtonId();
//    String method = "N/A";
//    if (paymentId == R.id.cash) {
//      method = "cash";
//    } else if (paymentId == R.id.banking) {
//      method = "online banking";
//    }
//    totalMoney += amount;
//    progressBar.setProgress(totalMoney);
//    Log.v("Donate",
//        "Donated " + amount + "$ through " + method + ", current balance = " + totalMoney);
//  }
//
//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.menu_main, menu);
//    return true;
//  }
//
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//    if (id == R.id.menuReport) {
//      startActivity(new Intent(this, Report.class));
//    }
//
//    if (id == R.id.menuDonate) {
//      startActivity(new Intent(this, Donate.class));
//    }
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }
//
//  @Override
//  public boolean onSupportNavigateUp() {
//    NavController navController = Navigation.findNavController(this,
//        R.id.nav_host_fragment_content_main);
//    return NavigationUI.navigateUp(navController, appBarConfiguration)
//        || super.onSupportNavigateUp();
//  }
//}