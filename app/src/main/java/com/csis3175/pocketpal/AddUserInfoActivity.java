package com.csis3175.pocketpal;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class AddUserInfoActivity extends AppCompatActivity {
    private static final int SELECT_PHOTO = 1;
    private String selectedImagePath = "";

    private void OpenGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_info);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        final LinearLayout defineProfile = (LinearLayout) findViewById(R.id.user_data_layout);
        final LinearLayout defineBudget = (LinearLayout) findViewById(R.id.budget_define_layout);

        Log.d("MainActivity", "onCreate");
        MyDB db = new MyDB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();

        final ImageButton img = (ImageButton) findViewById(R.id.imageButton2);
        img.setImageResource(R.drawable.ic_add_item);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                        OpenGallery();
                        img.setImageResource(R.drawable.ic_check);
                }
                else
                {
                    img.setImageResource(R.drawable.ic_check);
                    String[] permissionRequested = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissionRequested,1);
                }

            }
        });

        final Button bAdd = (Button) findViewById(R.id.gobtn);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(defineProfile.getVisibility()==View.VISIBLE){
                        EditText eNM = (EditText) findViewById(R.id.name);
                        EditText eEM = (EditText) findViewById(R.id.email);
                        EditText eIN = (EditText) findViewById(R.id.income);

                        ContentValues values = new ContentValues();
                        values.put(UserEntry.COLUMN_NAME_USRNAME, eNM.getText().toString());
                        values.put(UserEntry.COLUMN_NAME_EMAIL, eEM.getText().toString());
                        values.put(UserEntry.COLUMN_NAME_INCOME, eIN.getText().toString());
                        values.put(UserEntry.IMAGE, selectedImagePath.toString());
                        wdb.insert(UserEntry.TABLE_NAME, null, values);
                        bAdd.setText("FINISH");
                        defineProfile.setVisibility(View.INVISIBLE);
                        defineBudget.setVisibility(View.VISIBLE);
                }
                else{
                    EditText groceries = (EditText) findViewById(R.id.groceries);
                    EditText insurances = (EditText) findViewById(R.id.insurances);
                    EditText phone = (EditText) findViewById(R.id.phone);
                    EditText rent = (EditText) findViewById(R.id.rent);
                    EditText eat = (EditText) findViewById(R.id.eat);
                    EditText shop = (EditText) findViewById(R.id.shop);
                    EditText misc = (EditText) findViewById(R.id.misc);

                    ContentValues values = new ContentValues();
                    values.put(UserEntry.COLUMN_NAME_GROCERIES, groceries.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_INSURANCES, insurances.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_BILLS, phone.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_RENT, rent.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_EAT, eat.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_SHOP, shop.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_MISC, misc.getText().toString());
                    wdb.insert(UserEntry.TABLE_NAME_2, null, values);

                    Intent intent = new Intent(AddUserInfoActivity.this, MonthViewActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                OpenGallery();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PHOTO) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return uri.getPath();
    }

}