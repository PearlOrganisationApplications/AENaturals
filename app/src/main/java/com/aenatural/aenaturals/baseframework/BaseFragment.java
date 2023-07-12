package com.aenatural.aenaturals.baseframework;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment{
    public final int REQUEST_IMAGE_CAPTURE = 101;
    public final int REQUEST_IMAGE_GALLERY = 102;
    public boolean cameraPermissionDenied = false;
    public boolean galleryPermissionDenied = false;
    protected Context baseApcContext;
    private int IMAGE_TYPE;

    public void openCameraOrGallery() {
        String[] options = {"Take Photo", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
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

    public int getImageType() {
        return IMAGE_TYPE;
    }

    public void setImageType(int imageType) {
        IMAGE_TYPE = imageType;
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

        if (ContextCompat.checkSelfPermission(requireContext(), cameraPermission) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(cameraPermission);
        }

        if (ContextCompat.checkSelfPermission(requireContext(), galleryPermission) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(galleryPermission);
        }

        if (!permissionsToRequest.isEmpty()) {
            String[] permissionsArray = permissionsToRequest.toArray(new String[0]);
            if (cameraPermissionDenied && !shouldShowRequestPermissionRationale(cameraPermission) &&
                    galleryPermissionDenied && !shouldShowRequestPermissionRationale(galleryPermission)) {
                // User denied both camera and gallery permissions and checked "Never ask again"
                // Show a dialog explaining why the permissions are necessary
                // You can customize the dialog message based on your app's requirements
                new AlertDialog.Builder(requireContext())
                        .setTitle("Permissions Required")
                        .setMessage("Please grant camera and gallery permissions to use this feature.")
                        .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Open the app settings page
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
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
                ActivityCompat.requestPermissions(requireActivity(), permissionsArray, REQUEST_IMAGE_CAPTURE);
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

    public void cropImage(Uri imageUri) {
        File destinationUri = new File(requireActivity().getCacheDir(), "cropped_image.jpg");

        UCrop.of(imageUri, Uri.fromFile(destinationUri))
                //.withAspectRatio(1f, 1f)
                .start(requireActivity());
    }

    public Uri saveImageToFile(Bitmap bitmap) {
        File filesDir = requireActivity().getFilesDir();
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
                return FileProvider.getUriForFile(requireContext(), "com.aenatural.aenaturals.salesmans.fileprovider", imageFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*public Uri saveImageToFile(Bitmap bitmap) throws IOException {
        File imagesDir = new  File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "my_images");
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }
        File imageFile = new  File(imagesDir, "image.jpg");

        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            outputStream.flush();
            outputStream.close();

            if (imageFile.exists()) {
                return FileProvider.getUriForFile(requireContext(), "com.aenatural.aenaturals.salesmans.fileprovider", imageFile);
            }
        } catch (Throwable e:  ) {

            e.printStackTrace();
        }

        return null;
    }*/

}
