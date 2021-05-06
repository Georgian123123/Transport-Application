package utilites;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.routes.DashboardActivity;
import com.example.routes.MainActivity;

public class LoginPage {
    EditText email;
    EditText password;
    Button login, register;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND";
    String EmailHolder, PasswordHolder, UserName, PhoneHolder;
    Boolean EditTextEmptyHolder;
    public static final String userDatas="";
    private static Context instance;

    public LoginPage(EditText email, EditText password, Button login, Button register) {
        // The constructor for the login page
        this.email = email;
        this.password = password;
        this.login = login;
        this.register = register;
        instance = MainActivity.getInstance().getApplicationContext();
        user_login();
    }

    public void user_login() {
        // take the database
        sqLiteHelper = new SQLiteHelper(instance);
        this.password.setTransformationMethod(new PasswordTransformationMethod());

        login.setOnClickListener(v -> {
            // Calling EditText is empty or no method.
            CheckEditTextStatus();

            // Calling Login Method
            LoginFunction();
        });
        register.setOnClickListener(v -> {
            // Start the registration page
            Intent intent = new Intent(instance, Register.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            instance.startActivity(intent);
        });
    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = email.getText().toString();
        PasswordHolder = password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        EditTextEmptyHolder = !TextUtils.isEmpty(EmailHolder) && !TextUtils.isEmpty(PasswordHolder);
    }

    // Login function starts from here.
    public void LoginFunction(){
        if(EditTextEmptyHolder) {
            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));
                    UserName=cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name));
                    PhoneHolder = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Number));
                    // Closing cursor.
                    cursor.close();
                }
            }
            // Calling method to check final result ..
            CheckFinalResult();
        }
        else {
            //If any of login EditText empty then this block will be executed.
            Toast.makeText(instance, Constants.EMPTY_FLAG,Toast.LENGTH_LONG).show();
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            Toast.makeText(instance, Constants.SUCCES_FLAG,Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(instance, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(userDatas, new String[] {UserName, PasswordHolder, EmailHolder, PhoneHolder});
            instance.startActivity(intent);
        }
        else {
            Toast.makeText(instance, Constants.WRONG_FLAG,Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND" ;
    }
}
