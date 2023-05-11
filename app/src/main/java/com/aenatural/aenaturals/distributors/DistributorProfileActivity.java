package com.aenatural.aenaturals.distributors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aenatural.aenaturals.R;
import com.aenatural.aenaturals.baseframework.BaseClass;
import com.aenatural.aenaturals.common.Login;

import java.util.Base64;

public class DistributorProfileActivity extends BaseClass {
    TextView backTV;
    TextView distLogout;
    CardView customercarebutton;
    CardView privacypolicyButton;
    CardView callus;
    CardView mailus;
    CardView whatsapp;
    ScrollView customercareLayout;
    ScrollView privacypolicylayout;
    AlertDialog.Builder alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutXml();
        initializeViews();
        initializeClickListners();
        initializeInputs();
        initializeLabels();
    }

    @Override
    protected void setLayoutXml() {
        setContentView(R.layout.activity_distributor_profile);
        getMidGreentheme();
    }

    @Override
    protected void initializeViews() {

        backTV = findViewById(R.id.dist_backTV);
        distLogout = findViewById(R.id.dist_Logout);
        privacypolicyButton = findViewById(R.id.dist_privacypolicyButton);
        customercarebutton = findViewById(R.id.dist_customercarebutton);
        privacypolicylayout = findViewById(R.id.dist_privacypolicylayout);
        customercareLayout = findViewById(R.id.dist_customercareLayout);

        callus = findViewById(R.id.callus);
        mailus = findViewById(R.id.mailus);
        whatsapp = findViewById(R.id.whatsapp);

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Do you want to Logout?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(DistributorProfileActivity.this, Login.class));
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     
            }
        });

    }

    @Override
    protected void initializeClickListners() {
        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        distLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });

        privacypolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (privacypolicylayout.getVisibility() == View.GONE) {
                    privacypolicylayout.setVisibility(View.VISIBLE);
                } else {
                    privacypolicylayout.setVisibility(View.GONE);
                }
                customercareLayout.setVisibility(View.GONE);
            }
        });

        customercarebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customercareLayout.getVisibility() == View.GONE) {
                    customercareLayout.setVisibility(View.VISIBLE);
                } else {
                    customercareLayout.setVisibility(View.GONE);
                }
                privacypolicylayout.setVisibility(View.GONE);
            }
        });

        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918046444999"));
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(DistributorProfileActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    }
                }
            }
        });

        mailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + "care@aenaturals.in"));
                startActivity(emailIntent);
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "8553463261";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + number;
                intent.setData(Uri.parse(url));

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(DistributorProfileActivity.this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    @Override
    protected void initializeInputs() {

    }

    @Override
    protected void initializeLabels() {

    }
}