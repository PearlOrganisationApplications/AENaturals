package com.aenatural.aenaturals.baseframework;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import com.aenatural.aenaturals.R;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class BaseClass extends AppCompatActivity {
    protected String versionNew;
    protected String versionName;
   // public IsAppUpdated mIsUpdateAppTask = null;
    protected Context baseApcContext;
    public static boolean isInternetReceiver;
    protected AppCompatActivity activityIn;
    protected String LogTag, CAId,LogString;
    public int STORAGE_PERMISSION_CODE = 1;
    public String classname="Login";

    public final int REQUEST_IMAGE_CAPTURE = 101;
    public final int REQUEST_IMAGE_GALLERY = 102;
    public boolean cameraPermissionDenied = false;
    public boolean galleryPermissionDenied = false;
    private int IMAGE_TYPE;

    public int getImageType() {
        return IMAGE_TYPE;
    }

    public void setImageType(int imageType) {
        IMAGE_TYPE = imageType;
    }

    public void printLogs(String tag, String funcs, String msg){
        Log.i("OSG-"+tag+"__"+funcs,msg);
        LogString = LogString+"TAG - "+tag+"<br/> FUNCTION - "+funcs+"<br/> DATA - "+msg+"<br/><br/><br/><br/>";
    }



    @SuppressLint("ObsoleteSdkInt")
    public void getgreenTheme(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.darkgreen));
        }
    }
    public void getLightGreentheme(){
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.lightgreen));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    public void getMidGreentheme(){
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.midgreen));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    @SuppressLint("ObsoleteSdkInt")
    public void getwhiteTheme(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
    }
    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(Color.parseColor("#044935"), PorterDuff.Mode.SRC_OVER);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    public void openCameraOrGallery() {
        String[] options = {"Take Photo", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        openCamera();
                        break;
                    case 1:
                        openGallery();
                        break;
                }
            }
        });
        builder.show();
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestCameraPermission() {
        String cameraPermission = android.Manifest.permission.CAMERA;
        String galleryPermission = android.Manifest.permission.READ_EXTERNAL_STORAGE;

        List<String> permissionsToRequest = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, cameraPermission) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(cameraPermission);
        }

        if (ContextCompat.checkSelfPermission(this, galleryPermission) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(galleryPermission);
        }

        if (!permissionsToRequest.isEmpty()) {
            String[] permissionsArray = permissionsToRequest.toArray(new String[0]);
            if (cameraPermissionDenied && !shouldShowRequestPermissionRationale(cameraPermission) &&
                    galleryPermissionDenied && !shouldShowRequestPermissionRationale(galleryPermission)) {
                // User denied both camera and gallery permissions and checked "Never ask again"
                // Show a dialog explaining why the permissions are necessary
                // You can customize the dialog message based on your app's requirements
                new AlertDialog.Builder(this)
                        .setTitle("Permissions Required")
                        .setMessage("Please grant camera and gallery permissions to use this feature.")
                        .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Open the app settings page
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            } else {
                // Show the permission request dialog
                ActivityCompat.requestPermissions(this, permissionsArray, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            // Permissions already granted
            // Proceed to open the camera or choose from gallery
            openCameraOrGallery();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            boolean cameraPermissionGranted = false;
            boolean galleryPermissionGranted = false;

            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        cameraPermissionGranted = true;
                    } else {
                        cameraPermissionDenied = true;
                    }
                }

                if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        galleryPermissionGranted = true;
                    } else {
                        galleryPermissionDenied = true;
                    }
                }
            }

            if (cameraPermissionGranted && galleryPermissionGranted) {
                // Both camera and gallery permissions granted
                // Proceed to open the camera or choose from gallery
                openCameraOrGallery();
            } else {
                // Request the permissions again
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestCameraPermission();
                }
            }
        }
    }

    public Uri saveImageToFile(Bitmap bitmap) {
        File filesDir = getFilesDir();
        File imageFile = new File(filesDir, "image.jpg");

        // Delete the existing file if it exists
        if (imageFile.exists()) {
            imageFile.delete();
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            outputStream.flush();
            outputStream.close();

            if (imageFile.exists()) {
                return FileProvider.getUriForFile(this, "com.aenatural.aenaturals.salesmans.fileprovider", imageFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void cropImage(Uri imageUri) {
        File destinationUri = new File(getCacheDir(), "cropped_image.jpg");

        UCrop.of(imageUri, Uri.fromFile(destinationUri))
                //.withAspectRatio(1f, 1f)
                .start(this);
    }


    /*public void birdTheme(){
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.birdcolor));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }*/
    public void birdTheme() {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.birdcolor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }



      /*public Uri saveImageToFile(Bitmap bitmap) {
        File filesDir = getFilesDir();
        File imageFile = new File(filesDir, "image.jpg");
        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            outputStream.flush();
            outputStream.close();

            // Check if the file exists
            if (!imageFile.exists()) {
                return null;
            }

            return FileProvider.getUriForFile(this, "com.aenatural.aenaturals.salesmans.fileprovider", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }*/

       /*  public void setBaseApcContextParent(Context cnt, AppCompatActivity ain, String lt,String classname){
            baseApcContext = cnt;
            activityIn = ain;
            LogTag = lt;
            classname = classname;
            printLogs(lt, "setBaseApcContextParent", "weAreIn");
        }


        protected void internetChangeBroadCast() {
            printLogs("Logs", "initializeViews", "init");
            registerBroadcast();
        }




        public boolean isNetworkConnected(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null;
        }


        public void printLogs(String tag, String funcs, String msg){
                Log.i("OSG-"+tag+"__"+funcs,msg);
                LogString = LogString+"TAG - "+tag+"<br/> FUNCTION - "+funcs+"<br/> DATA - "+msg+"<br/><br/><br/><br/>";
        }

        public BroadcastReceiver IChangeReceiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onReceive(Context pContext, Intent pIntent) {
                ConnectivityManager cm = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                View no_connection = findViewById(R.id.no_internet);
               // TextView try_again = findViewById(R.id.try_again);
                if (cm.getActiveNetwork()!= null) {
                    no_connection.setVisibility(View.GONE);
                    printLogs(LogTag, "BroadcastReceiver", "func1"+IChangeReceiver);
                } else {
                    no_connection.setVisibility(View.VISIBLE);
                }
            }
        };




        public void registerBroadcast() {
            try {
                printLogs(LogTag, "registerBroadcast", "init");
                IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                registerReceiver(IChangeReceiver, filter);
                isInternetReceiver = true;
                printLogs(LogTag, "registerBroadcast", "exit");

            }
            catch (Exception e){
                printLogs(LogTag, "registerBroadcast", "Exception "+e.getMessage());
            }
        }

        public void unregisterBroadcast() {
            printLogs(LogTag, "unregisterBroadcast", "init");
            try {
                if (isInternetReceiver) {
                    printLogs(LogTag, "unregisterBroadcast", "isInternetReceiver");
                    isInternetReceiver = false;
                    unregisterReceiver(IChangeReceiver);
                }
            } catch (Exception e) {
                printLogs(LogTag, "unregisterBroadcast", "Exception " + e.getMessage());
            }
        }




        protected void showProgress(final boolean show) {
            View ll_main = findViewById(R.id.ll_main);
            View loader = findViewById(R.id.loader);
            if (show){
                ll_main.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);
            }else {
                ll_main.setVisibility(View.VISIBLE);
                loader.setVisibility(View.GONE);
            }
        }

        public void syncUpdates(Context baseApcContext,AppCompatActivity activityIn)    {
            int versionCode=1;
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                versionName = packageInfo.versionName;
                versionCode = packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            versionNew = String.valueOf(versionCode);
    //        versionNew = versionName;
            printLogs(LogTag,"syncUpdates","versionName "+versionName+" VersionCode "+versionCode + " NewVersion "+versionNew);
            mIsUpdateAppTask = new IsAppUpdated(versionNew, baseApcContext);
            mIsUpdateAppTask.execute((Void) null);
        }

        public void verifyVersion(){
           *//* syncUpdates(baseApcContext, activityIn);
        printLogs(LogTag, "verifyVersion", "init");
        session = new Session(baseApcContext);
        Boolean isUpdate = session.getIsUpdateRequired();
        printLogs(LogTag, "verifyVersion", "isUpdate " + isUpdate);
        if (isUpdate) {
            Intent intent = new Intent(baseApcContext, AppUpdateA.class);
            startActivity(intent);
            finish();
        }*//*
    }

    public void openFileExplorer() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), STORAGE_PERMISSION_CODE);
    }

    protected void setCustomError(String msg, EditText mEditView) {
        mEditView.setError(msg, null);
        mEditView.setBackgroundResource(R.drawable.input_error_profile);
        mEditView.requestFocus();
    }

    protected void setCustomErrorDisabled(EditText mEditView) {
        mEditView.setError(null);
        mEditView.setBackgroundResource(R.drawable.input_boder_profile);
    }

    public boolean validateName(EditText inputUser) {
        String name = inputUser.getText().toString().trim();
        setCustomError(null, inputUser);
        if (name.isEmpty()) {
            String sMessage ="Please enter name..!!";
            setCustomError(sMessage, inputUser);
            return false;
        }
        else if (!isValidName(name)){
            String sMessage ="Name must be at least 3 character and at most 50 character..!!";
            setCustomError(sMessage, inputUser);
            return false;
        }
        else {
            setCustomErrorDisabled(inputUser);
            return true;
        }
    }

    public boolean validateAddress1(EditText inputUser) {
        String address = inputUser.getText().toString().trim();
        setCustomError(null, inputUser);
        if (address.isEmpty()) {
            String sMessage ="Please enter Address..!!";
            setCustomError(sMessage, inputUser);
            return false;
        }
        else if (!isValidAddress10(address)){
            String sMessage ="Address must be at least 10 character and should have House no / Flat no / Road no.";
            setCustomError(sMessage, inputUser);
            return false;
        }
        else {
            setCustomErrorDisabled(inputUser);
            return true;
        }
    }


    public boolean validateAddress(EditText inputUser) {
        String address = inputUser.getText().toString().trim();
        setCustomError(null, inputUser);
        if (address.isEmpty()) {
            String sMessage ="Please enter Address..!!";
            setCustomError(sMessage, inputUser);
            return false;
        }
        else if (!isValidAddress(address)){
            String sMessage ="Address must be at least 3 character";
            setCustomError(sMessage, inputUser);
            return false;
        }
        else {
            setCustomErrorDisabled(inputUser);
            return true;
        }
    }

    public boolean validateEmail(EditText email) {
        String email_id = email.getText().toString().trim();
        setCustomError(null, email);
        if (email_id.isEmpty()) {
            String sMessage ="Please enter email..!!";
            setCustomError(sMessage, email);
            return false;
        }
        else {
            setCustomErrorDisabled(email);
            return true;
        }
    }

    public boolean validateDob(EditText dob) {
        String dob_id = dob.getText().toString().trim();
        setCustomError(null, dob);
        if (dob_id.isEmpty()) {
            String sMessage ="Please enter valid dob..!!";
            setCustomError(sMessage, dob);
            return false;
        }
        else {
            setCustomErrorDisabled(dob);
            return true;
        }
    }


    public boolean validateNumber(EditText number) {
        String num = number.getText().toString().trim();
        setCustomError(null, number);
        if (num.isEmpty()) {
            String sMessage ="Please enter number..!!";
            setCustomError(sMessage, number);
            return false;
        }
        else if (!isValidNumber(num)){
            String sMessage ="Number must be 10 character..!!";
            setCustomError(sMessage, number);
            return false;
        }
        else {
            setCustomErrorDisabled(number);
            return true;
        }
    }

    public static boolean isValidNumber(String number) {
        Boolean result;
        result = true;
        if((number.length()<10)){
            result = false;
        } if ((number.length()>10)){
            result = false;
        }
        return result;
    }

    public static boolean isValidName(String name) {
        Boolean result;
        result = true;
        if(name.length()<3){
            result = false;
        }
        if(name.length()>50){
            result = false;
        }
        return result;
    }

    public static boolean isValidAddress10(String name) {
        Boolean result;
        result = true;
        if(name.length()<10){
            result = false;
        }
        return result;
    }

    public static boolean isValidAddress(String name) {
        Boolean result;
        result = true;
        if(name.length()<3){
            result = false;
        }
        return result;
    }

    public String getExtension(Uri uri){
        String mimeType = getContentResolver().getType(uri);
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
    }

    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openFileExplorer();
            return;
        }
        ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        Base64.encodeToString(b, Base64.DEFAULT);
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

*/



    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    protected abstract void setLayoutXml();
    protected abstract void initializeViews();
    protected abstract void initializeClickListners();
    protected abstract void initializeInputs();
    protected abstract void initializeLabels();

public void errorHandler(String msg, TextView tv, Boolean mode){
    tv.setText(msg);
    if(mode){
        tv.setVisibility(View.VISIBLE);
    }else{
        tv.setVisibility(View.GONE);
    }
}

}
