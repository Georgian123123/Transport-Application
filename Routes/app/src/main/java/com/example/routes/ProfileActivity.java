package com.example.routes;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import utilites.Constants;
import utilites.LoginPage;
import utilites.Register;
import utilites.SQLiteHelper;

public class ProfileActivity extends AppCompatActivity {
    EditText currentEmail;
    EditText currentName;
    EditText currentPassword;
    EditText phoneNumber;
    private static String[] userDatas;
    public static final String userDatasToSend = "";
    private static Context instance;
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.putExtra(userDatasToSend, new String[] {currentName.getText().toString(), currentPassword.getText().toString(), currentEmail.getText().toString(), phoneNumber.getText().toString()});

            instance = MainActivity.getInstance().getApplicationContext();
            sqLiteHelper = new SQLiteHelper(instance);
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(SQLiteHelper.Table_Column_1_Name, currentName.getText().toString());
            values.put(SQLiteHelper.Table_Column_3_Password, currentPassword.getText().toString());
            values.put(SQLiteHelper.Table_Column_4_Number, phoneNumber.getText().toString());

            int rowsUpdated = sqLiteDatabaseObj.update(SQLiteHelper.TABLE_NAME, values, "email='" + currentEmail.getText().toString() + "'", null);
            if (rowsUpdated < 0) {
                Toast.makeText(this, Constants.UPDATE_FALSE, Toast.LENGTH_LONG).show();
            }
            sqLiteDatabaseObj.close();
            startActivity(intent);
        });
        Intent intent = getIntent();
        userDatas = intent.getStringArrayExtra(DashboardActivity.userDatasToSend);
        currentEmail = findViewById(R.id.email_profile);
        currentEmail.setText(userDatas[2]);
        currentEmail.setEnabled(false);

        currentName = findViewById(R.id.name);
        currentName.setText(userDatas[0]);

        currentPassword = findViewById(R.id.password);
        currentPassword.setText(userDatas[1]);

        phoneNumber = findViewById(R.id.phone_number);
        phoneNumber.setText(userDatas[3]);

        this.currentPassword.setTransformationMethod(new PasswordTransformationMethod());
    }
}
