package com.aenatural.aenaturals.distributors;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aenatural.aenaturals.R;
import com.aenatural.aenaturals.baseframework.BaseClass;
import com.aenatural.aenaturals.baseframework.Session;
import com.aenatural.aenaturals.common.Login;
import com.yalantis.ucrop.UCrop;

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
    Session pref;
    LinearLayout dis_nameLL;
    LinearLayout dis_emailLL;
    LinearLayout dist_phoneTVLL;
    LinearLayout dist_phoneEdtLL;
    LinearLayout dist_addressLL;
    LinearLayout dist_cityTVLL;
    LinearLayout dist_cityEdtLL;
    LinearLayout dist_gstNoLL;
    LinearLayout dist_bankNameLL;
    LinearLayout dist_accountNoLL;
    LinearLayout dist_ifscLL;
    LinearLayout dist_upiLL;
    LinearLayout dist_adharNoLL;
    LinearLayout dist_panLL;
    LinearLayout dis_adharPic;
    LinearLayout dis_panPic;
    ImageView dis_edtProfile;

    EditText dis_nameEdt;
    EditText dis_emailEdt;
    EditText dist_phoneEdt;
    EditText dis_pincodeEdt;
    EditText dist_addressEdt;
    EditText dist_cityEdt;
    EditText dist_stateEdt;
    EditText dist_gstNoEdt;
    EditText dist_bankNameEdt;
    EditText dist_accountNoEdt;
    EditText dist_ifscEdt;
    EditText dist_upiEdt;
    EditText dist_adharNoEdt;
    EditText dist_panEdt;
    ImageView dist_aadharIV;
    ImageView dist_panIV;

    Button dist_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref=new Session(this);
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

        dis_nameLL = findViewById(R.id.dis_nameLL);
        dis_emailLL = findViewById(R.id.dis_emailLL);
        dist_phoneTVLL = findViewById(R.id.dist_phoneTVLL);
        dist_phoneEdtLL = findViewById(R.id.dist_phoneEdtLL);
        dist_addressLL = findViewById(R.id.dist_addressLL);
        dist_cityTVLL = findViewById(R.id.dist_cityTVLL);   // Text view
        dist_cityEdtLL = findViewById(R.id.dist_cityEdtLL);  // Edit text
        dist_gstNoLL = findViewById(R.id.dist_gstNoLL);
        dist_bankNameLL = findViewById(R.id.dist_bankNameLL);
        dist_accountNoLL = findViewById(R.id.dist_accountNoLL);
        dist_ifscLL = findViewById(R.id.dist_ifscLL);
        dist_upiLL = findViewById(R.id.dist_upiLL);
        dist_adharNoLL = findViewById(R.id.dist_adharNoLL);
        dist_panLL = findViewById(R.id.dist_panLL);
        dis_edtProfile = findViewById(R.id.dis_edtProfile);
        dis_adharPic = findViewById(R.id.dis_adharPic);
        dis_panPic = findViewById(R.id.dis_panPic);

        dis_nameEdt = findViewById(R.id.dis_nameEdt);
        dis_emailEdt = findViewById(R.id.dis_emailEdt);
        dist_phoneEdt = findViewById(R.id.dist_phoneEdt);
        dis_pincodeEdt = findViewById(R.id.dis_pincodeEdt);
        dist_addressEdt = findViewById(R.id.dist_addressEdt);
        dist_cityEdt = findViewById(R.id.dist_cityEdt);
        dist_stateEdt = findViewById(R.id.dist_stateEdt);
        dist_gstNoEdt = findViewById(R.id.dist_gstNoEdt);
        dist_bankNameEdt = findViewById(R.id.dist_bankNameEdt);
        dist_accountNoEdt = findViewById(R.id.dist_accountNoEdt);
        dist_ifscEdt = findViewById(R.id.dist_ifscEdt);
        dist_upiEdt = findViewById(R.id.dist_upiEdt);
        dist_adharNoEdt = findViewById(R.id.dist_adharNoEdt);
        dist_panEdt = findViewById(R.id.dist_panEdt);

        dist_button = findViewById(R.id.dist_button);
        dist_aadharIV = findViewById(R.id.dist_aadharIV);
        dist_panIV = findViewById(R.id.dist_panIV);

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Do you want to Logout?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pref.clearSession();
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
        dis_edtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dis_nameLL.setVisibility(View.GONE);
                dis_emailLL.setVisibility(View.GONE);
                dist_phoneTVLL.setVisibility(View.GONE);
                dist_addressLL.setVisibility(View.GONE);
                dist_cityTVLL.setVisibility(View.GONE);
                dist_gstNoLL.setVisibility(View.GONE);
                dist_bankNameLL.setVisibility(View.GONE);
                dist_accountNoLL.setVisibility(View.GONE);
                dist_ifscLL.setVisibility(View.GONE);
                dist_upiLL.setVisibility(View.GONE);
                dist_adharNoLL.setVisibility(View.GONE);
                dist_panLL.setVisibility(View.GONE);

                dis_nameEdt.setVisibility(View.VISIBLE);
                dis_emailEdt.setVisibility(View.VISIBLE);
                dist_phoneEdt.setVisibility(View.VISIBLE);
                dis_pincodeEdt.setVisibility(View.VISIBLE);
                dist_addressEdt.setVisibility(View.VISIBLE);
                dist_cityEdt.setVisibility(View.VISIBLE);
                dist_stateEdt.setVisibility(View.VISIBLE);
                dist_phoneEdtLL.setVisibility(View.VISIBLE);
                dist_cityEdtLL.setVisibility(View.VISIBLE);
                dist_gstNoEdt.setVisibility(View.VISIBLE);
                dist_bankNameEdt.setVisibility(View.VISIBLE);
                dist_accountNoEdt.setVisibility(View.VISIBLE);
                dist_ifscEdt.setVisibility(View.VISIBLE);
                dist_upiEdt.setVisibility(View.VISIBLE);
                dist_adharNoEdt.setVisibility(View.VISIBLE);
                dist_panEdt.setVisibility(View.VISIBLE);

                dist_button.setVisibility(View.VISIBLE);

                dis_adharPic.setEnabled(true);
                dis_panPic.setEnabled(true);
                dis_adharPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        IMAGE_TYPE = 2;
                        setImageType(2);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestCameraPermission();
                        }else
                            Toast.makeText(DistributorProfileActivity.this, "aadhar pic", Toast.LENGTH_SHORT).show();
                    }
                });

                dis_panPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        IMAGE_TYPE = 1;
                        setImageType(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestCameraPermission();
                        }else
                        Toast.makeText(DistributorProfileActivity.this, "pan card pic", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dis_nameLL.setVisibility(View.VISIBLE);
                dis_emailLL.setVisibility(View.VISIBLE);
                dist_phoneTVLL.setVisibility(View.VISIBLE);
                dist_addressLL.setVisibility(View.VISIBLE);
                dist_cityTVLL.setVisibility(View.VISIBLE);
                dist_gstNoLL.setVisibility(View.VISIBLE);
                dist_bankNameLL.setVisibility(View.VISIBLE);
                dist_accountNoLL.setVisibility(View.VISIBLE);
                dist_ifscLL.setVisibility(View.VISIBLE);
                dist_upiLL.setVisibility(View.VISIBLE);
                dist_adharNoLL.setVisibility(View.VISIBLE);
                dist_panLL.setVisibility(View.VISIBLE);

                dis_nameEdt.setVisibility(View.GONE);
                dis_emailEdt.setVisibility(View.GONE);
                dist_phoneEdt.setVisibility(View.GONE);
                dis_pincodeEdt.setVisibility(View.GONE);
                dist_addressEdt.setVisibility(View.GONE);
                dist_cityEdt.setVisibility(View.GONE);
                dist_stateEdt.setVisibility(View.GONE);
                dist_phoneEdtLL.setVisibility(View.GONE);
                dist_cityEdtLL.setVisibility(View.GONE);
                dist_gstNoEdt.setVisibility(View.GONE);
                dist_bankNameEdt.setVisibility(View.GONE);
                dist_accountNoEdt.setVisibility(View.GONE);
                dist_ifscEdt.setVisibility(View.GONE);
                dist_upiEdt.setVisibility(View.GONE);
                dist_adharNoEdt.setVisibility(View.GONE);
                dist_panEdt.setVisibility(View.GONE);

                dist_button.setVisibility(View.GONE);

                dis_adharPic.setEnabled(false);
                dis_panPic.setEnabled(false);
            }
        });
    }

    @Override
    protected void initializeInputs() {

    }

    @Override
    protected void initializeLabels() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                    Uri imageUri = saveImageToFile(imageBitmap); // Save the image and get its Uri
                    if (imageUri != null) {
                        cropImage(imageUri);
                    }
                    break;

                case REQUEST_IMAGE_GALLERY:
                     imageUri = data.getData();
                    if (imageUri != null) {
                        cropImage(imageUri);
                    }
                    break;
                case UCrop.REQUEST_CROP:
                    final Uri resultUri = UCrop.getOutput(data);
                    // Process the cropped image (resultUri)
                    if (resultUri != null) {
                        if (getImageType() == 1) {
                            dist_panIV.setImageURI(resultUri);
                        } else if (getImageType() == 2) {
                            dist_aadharIV.setImageURI(resultUri);
                        }
                    }
                    break;
            }
        }else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable error = UCrop.getError(data);
            // Handle the cropping error
            Log.d("errorImage", error != null ? error.toString() : "" + "line 404 salesmanprofileActivity page");
        }
    }
}