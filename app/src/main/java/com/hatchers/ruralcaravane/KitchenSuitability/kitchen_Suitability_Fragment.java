package com.hatchers.ruralcaravane.KitchenSuitability;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hatchers.ruralcaravane.KitchenSuitability.Databases.Kitchen_Table;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.Kitchen_Table_Helper;
import com.hatchers.ruralcaravane.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class kitchen_Suitability_Fragment extends Fragment implements AdapterView.OnItemSelectedListener{


    private static final String IMAGE_DIRECTORY = "/RuraralCaravane";
    private int CAMERA = 1;

    private Toolbar kitchen_toolbar;
    private ImageButton kitchen_btnBack;
    private Spinner house_survey,roof_type;
    private TextInputEditText kitchen_height;
    private ImageView take_picture;
    private int RESULT_CANCELED;
    private Button upload;
    Kitchen_Table kitchen_table;
    public kitchen_Suitability_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_kitchen__suitability, container, false);

        ((AppCompatActivity)getActivity()).setSupportActionBar(kitchen_toolbar);

        initializations(view);
        onclicklisteners();


        house_survey.setOnItemSelectedListener(this);
        roof_type.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> house_survey_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.House_Type, android.R.layout.simple_spinner_item);
        house_survey_adapter.setDropDownViewResource(R.layout.spinner_item);
        house_survey.setAdapter(house_survey_adapter);

        ArrayAdapter<CharSequence> roof_type_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Roof_Type, android.R.layout.simple_spinner_item);
       roof_type_adapter.setDropDownViewResource(R.layout.spinner_item);
        roof_type.setAdapter(roof_type_adapter);




        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        File profile = new File(wallpaperDirectory, "Area_Picture.jpg");
        if(profile.exists())
        {
            Bitmap bitmap = BitmapFactory.decodeFile(profile.getAbsolutePath());
            take_picture.setImageBitmap(bitmap);


            // to set the background color (color should have some alpha val)
            //  diagonalView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            // to make the solid color diagonal
            //  diagonalView.setSolidColor(getResources().getColor(R.color.colorPrimaryDark));

        }
        else
        {
            take_picture.setImageResource(R.mipmap.chullha);

        }
        return view;
    }

    private void initializations(View view)
    {
        kitchen_toolbar=(Toolbar)view.findViewById(R.id.kitchen_toolbar);
        kitchen_btnBack=(ImageButton)view.findViewById(R.id.kitchen_btnBack);
        house_survey=(Spinner)view.findViewById(R.id.house_type_survey);
        roof_type=(Spinner)view.findViewById(R.id.roof_type);
        kitchen_height=(TextInputEditText)view.findViewById(R.id.kitchen_height);
        take_picture=(ImageView)view.findViewById(R.id.take_picture);
        upload=(Button)view.findViewById(R.id.upload);
    }

    private  void onclicklisteners()
    {
        kitchen_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKitchenData();
                Kitchen_Table_Helper.insertKitchenData(getContext(),kitchen_table );

            }
        });
    }


    private void setKitchenData() {

        kitchen_table = new Kitchen_Table();

        kitchen_table.setHouse_typeValue(house_survey.getSelectedItem().toString());
        kitchen_table.setRoof_typeValue(roof_type.getSelectedItem().toString());
        kitchen_table.setKitchen_heightValue(kitchen_height.getText().toString());

    }

    private void showPictureDialog()
    {
        final CharSequence[] options = {"Take Photo", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(options,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhotoFromCamera();
                                break;

                            case 2:
                                dialog.dismiss();
                        }
                    }
                });
        AlertDialog alert=builder.create();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    private void takePhotoFromCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            take_picture.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, "profile.jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getContext(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
