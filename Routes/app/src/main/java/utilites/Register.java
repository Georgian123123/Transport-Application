package utilites;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.routes.MainActivity;
import com.example.routes.R;


public class Register extends AppCompatActivity {
    EditText Email, Password, Name, phone;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    String PhoneHolder;
    Cursor cursor;
    String F_Result = "Not_Found";
    CheckBox check, check2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register = findViewById(R.id.register);
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        Name = findViewById(R.id.editName);
        phone = findViewById(R.id.editPhone);
        check = findViewById(R.id.checkBox);
        check2 = findViewById(R.id.checkBox2);
        check.setText("Politica de confidentialitate");
        check2.setText("Termeni si conditii");
        sqLiteHelper = new SQLiteHelper(this);

        // Adding click listener to register button.
        Register.setOnClickListener(view -> {

            if (valid_Email(Email.getText().toString())) {
                // Creating SQLite database if dose n't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();

                //Sending confirmation email.
                Confirm();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();
            } else {
                Toast.makeText(Register.this, Constants.WRONG_FORMAT_EMAIL, Toast.LENGTH_LONG).show();
            }
        });
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setTitle("Politici de confidentialitate");
                StringBuilder message  = new StringBuilder();
                message.append("Datele furnizate de către clienți sunt strict confidențiale. MAIA se angajează în fața utilizatorilor/clienților săi să nu furnizeze aceste date unor terțe persoane neautorizate și să le utilizeze strict pentru desfășurarea relației de afaceri dintre client și MAIA.\n" +
                        "Conform cerințelor Legii nr. 677/2001 pentru protecția persoanelor cu privire la prelucrarea datelor cu caracter personal și libera circulație a acestor date, modificată și completată, MAIA are obligația de a administra în condiții de siguranță și numai pentru scopurile specificate, datele personale pe care ni le furnizați despre dumneavoastră.\n" +
                        "Tipurile de informații colectate vor varia în funcție de activitate și scopul colectării acestora, respectiv:\n" +
                        "    • prin înregistrarea ca utilizator al serviciului de cumpărarea a biletului de călătorie, informarea utilizatorilor/clienților privind situația contului lor din aplicația MAIA, confirmarea, evoluția și starea tranzacțiile online efectuate și întreaga corespondență legată de acestea, respectiv prin e-mail (datele necesare efectuării călătoriei/biletul),\n" +
                        "    •  emiterea facturii electronice pentru biletele achiziționate online din contul utilizatorului care a cumpărat biletul, în baza introducerii de către acesta, pe proprie răspundere, a datelor obligatorii de facturare,\n" +
                        "Prin oferirea acestor date vă dați consimțământul liber exprimat și în mod neechivoc ca toate datele dvs. cu caracter personal să fie stocate în baza noastră de date, utilizate și prelucrate nelimitat teritorial și/sau temporar de către MAIA, afiliații și colaboratorii acestora pentru desfășurarea și/sau derularea de activități cum ar fi, dar fără a se limita la, activități comerciale, de dezvoltare, de cercetare de piață, de statistică, de urmărire și monitorizare a vânzărilor și comportamentul consumatorului (acest site folosește cookie-uri), cu respectarea strictă a prevederilor legale referitoare la protecția persoanelor cu privire la prelucrarea datelor cu caracter personal și libera circulație a acestor date.\n" +
                        "Informațiile pe care ni le furnizați vor fi utilizate în scopul pentru care le-ați furnizat, pentru a administra, a sprijini și a evalua serviciile noastre și a preîntâmpina încălcarea securității, a legii și a termenilor noștri contractuali.\n" +
                        "Datele dumneavoastră cu caracter personal colectate de MAIA nu vor fi divulgate niciodată unei companii terțe care intenționează să le folosească în scopuri de marketing direct, dacă dumneavoastră nu v-ați dat în mod expres consimțământul în acest sens.\n" +
                        "MAIA poate furniza datele dumneavoastră cu caracter personal altor companii cu care se află în relații de parteneriat, dar numai în temeiul unui angajament de confidențialitate din partea acestora, prin care garantează că aceste date sunt păstrate în siguranță și că furnizarea acestor informații personale se face conform legislației în vigoare, după cum urmează: furnizori de servicii de curierat, servicii de marketing, servicii bancare/de plăți electronice (MAIA nu are acces și nu stochează datele cardurilor sau ale conturilor bancare), furnizorii de servicii informatice care se ocupă de aplicațiile online de vânzare a legitimațiilor de călătorie, platforma pe care funcționează site-ul și de aplicația mobilă etc.,\n" +
                        "Informațiile dumneavoastră cu caracter personal pot fi furnizate și către autoritățile și instituțiile publice abilitate (Parchet, Poliție, Instanțele judecătorești etc.), în baza și în limitele prevederilor legale și ca urmare a unor cereri expres formulate .\n" +
                        "MAIA se obligă să aplice, în legătură cu datele personale ale utilizatorilor/clienților săi măsuri tehnice și organizatorice adecvate pentru protejarea datelor cu caracter personal împotriva distrugerii accidentale sau ilegale, pierderii, modificării, dezvăluirii sau accesului neautorizat, în special dacă prelucrarea respectivă comportă transmisii de date în cadrul unei rețele, precum și împotriva oricărei alte forme de prelucrare ilegală. \n" +
                        "Prin citirea prezentelor termene și condiții ați luat la cunoștință faptul că vă sunt garantate drepturile prevăzute de lege, respectiv dreptul de informare, dreptul de acces la date, dreptul de intervenție, dreptul de opoziție, dreptul de a nu fi supus unei decizii individuale, dreptul de a vă adresa justiției în caz de încălcare a drepturilor sale garantate de Legea 677/2001 pentru protecția persoanelor cu privire la prelucrarea datelor cu caracter personal și libera circulație a acestor date.\n" +
                        "Dacă aveți orice întrebare despre modul în care datele dumneavoastră sunt utilizate, sau dacă unele din datele dumneavoastră sunt incorecte, puteți înainta către MAIA o cerere scrisă, datată și semnată la adresa Bd. Nicolae Titulescu nr. 40, Etaj 1, Sector 1, București, Romania, Cod 010456 sau pe e-mail către maia@office.ro. În cerere se va preciza dacă se dorește ca informațiile să fie comunicate la o anumită adresă (chiar și e-mail) sau printr-un serviciu de corespondență care să asigure predarea numai personal.\n" +
                        "MAIA va comunica măsurile adoptate/informațiile solicitate în termen de 30 zile de la data primirii cererii.\n");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setMessage(message);
                builder.create().show();
                // Save to shared preferences
            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setTitle("Termenis si conditii");
                StringBuilder message  = new StringBuilder();
                message.append("Utilizând serviciile oferite de MAIA nu mai este necesară prezentarea la ghișeele de vânzare din stații/agenții. Datele necesare efectuării călătoriei vor fi transmise pe email și vor fi accesibile oricând din contul de utilizator.\n" +
                        "Atenție la declararea datelor de contact . Pe adresa de e-mail și numărul de telefon declarate inițial la înregistrare se vor transmite:\n" +
                        "    • biletele cumpărate precum și întreaga corespondență legată de tranzacțiile online efectuate prin accesarea acestui serviciu;\n" +
                        "    • link-ul de regenerare al parolei (când s-a uitat parola).\n" +
                        "Atragem atenția că înscrierea greșită a adresei de e-mail, conduce la imposibilitatea utilizării serviciului de achiziționare a biletelor de călătorie.\n" +
                        "Un utilizator al serviciului MAIA este necesar să cunoască, să respecte și să fie de acord cu următoarele aspecte legate de utilizarea biletelor de călătorie:\n" +
                        "1. Biletele sunt NOMINALE și permit efectuarea călătoriei de către persoanele nominalizate în cerere. \n" +
                        "2. Este recomandat călătorilor să tipărească și să aibă asupra lor biletul de călătorie în formatul standard pentru acest tip de bilet, dar nu este obligatoriu. Biletul poate fi descărcat și în telefonul mobil. Pasagerii trebuie să aibă la ei cartea de identitate sau/și pașaportul valabilă/valabil. Persoanele care beneficiază de reduceri ale tarifelor de călătorie trebuie să se vor identifica în baza unui act oficial de identitate (carte de identitate, carnet de elev/student vizat pentru anul în curs, talon de pensie).\n" +
                        "3 . MAIA va aviza prin e-mail orice modificare care este legată de călătoria clienților care și-au achiziționat bilete de călătorie, dacă acestea sunt cunoscute (dispuse) cu mai mult de 24 de ore înainte de data și ora plecării. Un utilizator înregistrat al serviciului MAIA este obligat și rămâne direct răspunzător de verificarea mail-urilor transmise, pentru a lua cunoștință de eventualele modificări apărute în desfășurarea călătoriei sale. \n" +
                        "4 . Tranzacțiile bancare de tip e-Commerce (prin care se efectuează plata aferentă biletelor) sunt asigurate, conform legislației în vigoare, de furnizori acreditați în acest sens. ");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setMessage(message);
                builder.create().show();
                // Save to shared preferences
            }
        });
    }

    public boolean valid_Email(String email) {
        return email.contains("@gmail.com") || email.contains("@yahoo.com");
    }
    public void Confirm(){

    }

    // SQLite database build method.
    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    // SQLite table build method.
    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR, " + SQLiteHelper.Table_Column_3_Password + " VARCHAR, " + SQLiteHelper.Table_Column_4_Number + " VARCHAR);");
    }

    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase(){

        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolder)
        {
            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,email,password,phone) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"', '"+PhoneHolder+"');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.
            Toast.makeText(Register.this, Constants.RESITER_FLAG, Toast.LENGTH_LONG).show();

        }
        // This block will execute if any of the registration EditText is empty.
        else {
            // Printing toast message if any of EditText is empty.
            Toast.makeText(Register.this, Constants.FILL_FLAG, Toast.LENGTH_LONG).show();
        }
    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
        finish();
    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){
        // Getting value from All EditText and storing into String Variables.
        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        PhoneHolder = phone.getText().toString();
        EditTextEmptyHolder = !TextUtils.isEmpty(NameHolder) && !TextUtils.isEmpty(EmailHolder) && !TextUtils.isEmpty(PasswordHolder);
    }

    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";

                // Closing cursor.
                cursor.close();
            }
        }
        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();
    }

    // Checking result
    public void CheckFinalResult(){
        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase(Constants.EMAIL_FOUND_FLAG))
        {
            // If email is exists then toast msg will display.
            Toast.makeText(Register.this,Constants.EMAIL_EXISTS_FLAG,Toast.LENGTH_LONG).show();
        }
        else {
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
        F_Result = "Not_Found" ;
    }

}
