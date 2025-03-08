package com.example.donation.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.donation.R;
import com.example.donation.databinding.ActivityMainBinding;
import com.example.donation.models.Donation;
import java.util.List;


public class Report extends Base {

  ListView listView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);
    Toolbar toolbar = findViewById(R.id.toolbar2);
    setSupportActionBar(toolbar);
    listView = findViewById(R.id.reportList);
    DonationAdapter adapter = new DonationAdapter(this, app.dbManager.getAll());
    listView.setAdapter(adapter);
  }
}

class DonationAdapter extends ArrayAdapter<Donation> {

  private Context context;
  public List<Donation> donations;

  public DonationAdapter(Context context, List<Donation> donations) {
    super(context, R.layout.row_donate, donations);
    this.context = context;
    this.donations = donations;
  }

  @SuppressLint("SetTextI18n")
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.row_donate, parent, false);
    Donation donation = donations.get(position);
    TextView amountView = view.findViewById(R.id.row_amount);
    TextView methodView = view.findViewById(R.id.row_method);
    amountView.setText("$" + donation.amount);
    methodView.setText(donation.method);
    return view;
  }

  @Override
  public int getCount() {
    return donations.size();
  }
}
