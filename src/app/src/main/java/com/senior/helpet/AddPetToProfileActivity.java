package com.senior.helpet;

import android.content.ClipData;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddPetToProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView image1, image2, image3;

    private EditText pet_name;
    private EditText pet_breed;
    private EditText pet_age;
    private EditText pet_description;

    private Spinner pet_animalType;
    private Spinner pet_gender;

    private Switch isMate;
    private Switch isAdoptable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet_to_profile);

        image1 = (ImageView) findViewById(R.id.imageView1);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);

        pet_name = (EditText) findViewById(R.id.pet_name);
        pet_animalType = (Spinner) findViewById(R.id.pet_animalType);
        setSpinner(pet_animalType, false);
        pet_breed = (EditText) findViewById(R.id.pet_breed);
        pet_age = (EditText) findViewById(R.id.pet_age);
        isMate = (Switch) findViewById(R.id.pet_mate);
        isAdoptable = (Switch) findViewById(R.id.pet_adoption);
        pet_gender = (Spinner) findViewById(R.id.pet_gender);
        setSpinner(pet_gender, true);
    }

    private void setSpinner(Spinner spinner, boolean t) {
        List<String> list = new ArrayList<String>();
        if(t) {
            list.add("Male");
            list.add("Female");
            list.add("[Select gender]");
        } else {
            list.add("Dog");
            list.add("Cat");
            list.add("Bird");
            list.add("[Select Animal Type]");
        }
        final int listsize = list.size() - 1;
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list) {
            @Override
            public int getCount() {
                return(listsize); // Truncate the list
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(listsize); // Hidden item to appear in the spinner
    }

    public void openGallery(View v) {
        Intent intent= new Intent(Intent.EXTRA_ALLOW_MULTIPLE, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    if (i == 0) {
                        image1.setImageURI(uri);
                    } else if (i==1) {
                        image2.setImageURI(uri);
                    } else {
                        image3.setImageURI(uri);
                    }
                }
            }
        }
    }

    public void sendToMain(View v) {
        Intent mainIntent = new Intent(AddPetToProfileActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}