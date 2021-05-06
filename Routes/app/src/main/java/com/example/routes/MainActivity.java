package com.example.routes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import available_routes.Routes;
import available_routes.RoutesDetails;
import utilites.LoginPage;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    private static String userName;
    private static String password;
    private static String email;
    private static String phoneNumber;
    public static Routes allRoutes = Routes.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        setContentView(R.layout.activity_main);
        instance = this;
        LoginPage loginPage = new LoginPage(findViewById(R.id.Email), findViewById(R.id.password),
                                            findViewById(R.id.buttonLogin), findViewById(R.id.buttonRegister));

    }

    public static MainActivity getInstance() {
        return instance;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setUserName(String userName) {
        MainActivity.userName = userName;
    }

    public static void setPassword(String password) {
        MainActivity.password = password;
    }

    public static void setEmail(String email) {
        MainActivity.email = email;
    }

    public static void setPhoneNumber(String phoneNumber) {
        MainActivity.phoneNumber = phoneNumber;
    }
}