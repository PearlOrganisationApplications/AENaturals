package com.aenatural.aenaturals.baseframework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.pearl.aenaturals.R;

public abstract class BaseClass extends AppCompatActivity {
    protected String versionNew;
    protected String versionName;
   // public IsAppUpdated mIsUpdateAppTask = null;
    protected Context baseApcContext;
    public static boolean isInternetReceiver;
    protected AppCompatActivity activityIn;
    protected String LogTag, CAId,LogString;
    public int STORAGE_PERMISSION_CODE = 1;
    public Session session;
    public String classname="Login";


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

*/    @Override
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


    protected abstract void setLayoutXml();
    protected abstract void initializeViews();
    protected abstract void initializeClickListners();
    protected abstract void initializeInputs();
    protected abstract void initializeLabels();



}
