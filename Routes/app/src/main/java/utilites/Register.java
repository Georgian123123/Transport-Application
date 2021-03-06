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
                message.append("Datele furnizate de c??tre clien??i sunt strict confiden??iale. MAIA se angajeaz?? ??n fa??a utilizatorilor/clien??ilor s??i s?? nu furnizeze aceste date unor ter??e persoane neautorizate ??i s?? le utilizeze strict pentru desf????urarea rela??iei de afaceri dintre client ??i MAIA.\n" +
                        "Conform cerin??elor Legii nr. 677/2001 pentru protec??ia persoanelor cu privire la prelucrarea datelor cu caracter personal ??i libera circula??ie a acestor date, modificat?? ??i completat??, MAIA are obliga??ia de a administra ??n condi??ii de siguran???? ??i numai pentru scopurile specificate, datele personale pe care ni le furniza??i despre dumneavoastr??.\n" +
                        "Tipurile de informa??ii colectate vor varia ??n func??ie de activitate ??i scopul colect??rii acestora, respectiv:\n" +
                        "    ??? prin ??nregistrarea ca utilizator al serviciului de cump??rarea a biletului de c??l??torie, informarea utilizatorilor/clien??ilor privind situa??ia contului lor din aplica??ia??MAIA, confirmarea, evolu??ia ??i starea tranzac??iile online efectuate ??i ??ntreaga coresponden???? legat?? de acestea, respectiv prin e-mail (datele necesare efectu??rii c??l??toriei/biletul),\n" +
                        "    ???  emiterea facturii electronice pentru biletele achizi??ionate online din contul utilizatorului care a cump??rat biletul, ??n baza introducerii de c??tre acesta, pe proprie r??spundere, a datelor obligatorii de facturare,\n" +
                        "Prin oferirea acestor date??v?? da??i consim????m??ntul liber exprimat ??i ??n mod neechivoc ca toate datele dvs. cu caracter personal s?? fie stocate ??n baza noastr?? de date, utilizate ??i prelucrate nelimitat teritorial ??i/sau temporar de c??tre MAIA, afilia??ii ??i colaboratorii acestora pentru desf????urarea ??i/sau derularea de activit????i??cum ar fi, dar f??r?? a se limita la, activit????i comerciale, de dezvoltare, de cercetare de pia????, de statistic??, de urm??rire ??i monitorizare a v??nz??rilor ??i comportamentul consumatorului (acest site folose??te cookie-uri), cu respectarea strict?? a prevederilor legale referitoare la protec??ia persoanelor cu privire la prelucrarea datelor cu caracter personal ??i libera circula??ie a acestor date.\n" +
                        "Informa??iile pe care ni le furniza??i vor fi utilizate ??n scopul pentru care le-a??i furnizat, pentru a administra, a sprijini ??i a evalua serviciile noastre ??i a pre??nt??mpina ??nc??lcarea securit????ii, a legii ??i a termenilor no??tri contractuali.\n" +
                        "Datele dumneavoastr?? cu caracter personal colectate de MAIA nu vor fi divulgate niciodat?? unei companii ter??e care inten??ioneaz?? s?? le foloseasc?? ??n scopuri de marketing direct, dac?? dumneavoastr?? nu v-a??i dat ??n mod expres consim????m??ntul ??n acest sens.\n" +
                        "MAIA poate furniza datele dumneavoastr?? cu caracter personal altor companii cu care se afl?? ??n rela??ii de parteneriat, dar numai ??n temeiul unui angajament de confiden??ialitate din partea acestora, prin care garanteaz?? c?? aceste date sunt p??strate ??n siguran???? ??i c?? furnizarea acestor informa??ii personale se face conform legisla??iei ??n vigoare, dup?? cum urmeaz??: furnizori de servicii de curierat, servicii de marketing, servicii bancare/de pl????i electronice (MAIA nu are acces ??i nu stocheaz?? datele cardurilor sau ale conturilor bancare), furnizorii de servicii informatice care se ocup?? de aplica??iile online de v??nzare a legitima??iilor de c??l??torie, platforma pe care func??ioneaz?? site-ul ??i de aplica??ia mobil?? etc.,\n" +
                        "Informa??iile dumneavoastr?? cu caracter personal pot fi furnizate ??i c??tre??autorit????ile ??i institu??iile publice abilitate??(Parchet, Poli??ie, Instan??ele judec??tore??ti etc.), ??n baza ??i ??n limitele prevederilor legale ??i ca urmare a unor cereri expres formulate??.\n" +
                        "MAIA se oblig?? s?? aplice, ??n leg??tur?? cu datele personale ale utilizatorilor/clien??ilor s??i m??suri tehnice ??i organizatorice adecvate pentru protejarea datelor cu caracter personal ??mpotriva distrugerii accidentale sau ilegale, pierderii, modific??rii, dezv??luirii sau accesului neautorizat, ??n special dac?? prelucrarea respectiv?? comport?? transmisii de date ??n cadrul unei re??ele, precum ??i ??mpotriva oric??rei alte forme de prelucrare ilegal??. \n" +
                        "Prin citirea prezentelor termene ??i condi??ii a??i luat la cuno??tin???? faptul c?? v?? sunt garantate drepturile prev??zute de lege, respectiv dreptul de informare, dreptul de acces la date, dreptul de interven??ie, dreptul de opozi??ie, dreptul de a nu fi supus unei decizii individuale, dreptul de a v?? adresa justi??iei ??n caz de ??nc??lcare a drepturilor sale garantate de Legea 677/2001 pentru protec??ia persoanelor cu privire la prelucrarea datelor cu caracter personal ??i libera circula??ie a acestor date.\n" +
                        "Dac?? ave??i orice ??ntrebare despre modul ??n care datele dumneavoastr?? sunt utilizate, sau dac?? unele din datele dumneavoastr?? sunt incorecte, pute??i ??nainta c??tre MAIA o cerere scris??, datat?? ??i semnat?? la adresa Bd. Nicolae Titulescu nr. 40, Etaj 1, Sector 1, Bucure??ti, Romania, Cod 010456 sau pe e-mail c??tre??maia@office.ro. ??n cerere se va preciza dac?? se dore??te ca informa??iile s?? fie comunicate la o anumit?? adres?? (chiar ??i e-mail) sau printr-un serviciu de coresponden???? care s?? asigure predarea numai personal.\n" +
                        "MAIA va comunica m??surile adoptate/informa??iile solicitate ??n termen de 30 zile de la data primirii cererii.\n");
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
                message.append("Utiliz??nd serviciile oferite de??MAIA??nu mai este necesar?? prezentarea la ghi??eele de v??nzare din sta??ii/agen??ii. Datele necesare efectu??rii c??l??toriei vor fi transmise pe email ??i vor fi accesibile oric??nd din contul de utilizator.\n" +
                        "Aten??ie la declararea datelor de contact??. Pe adresa de e-mail ??i num??rul de telefon declarate ini??ial la ??nregistrare se vor transmite:\n" +
                        "    ??? biletele cump??rate precum ??i ??ntreaga coresponden???? legat?? de tranzac??iile online efectuate prin accesarea acestui serviciu;\n" +
                        "    ??? link-ul de regenerare al parolei (c??nd s-a uitat parola).\n" +
                        "Atragem aten??ia c?? ??nscrierea gre??it?? a adresei de e-mail, conduce la imposibilitatea utiliz??rii serviciului de achizi??ionare a biletelor de c??l??torie.\n" +
                        "Un utilizator al serviciului??MAIA??este necesar s?? cunoasc??, s?? respecte ??i s?? fie de acord cu urm??toarele aspecte legate de utilizarea biletelor de c??l??torie:\n" +
                        "1. Biletele sunt NOMINALE ??i permit efectuarea c??l??toriei??de c??tre persoanele nominalizate ??n cerere. \n" +
                        "2. Este recomandat c??l??torilor s?? tip??reasc?? ??i s?? aib?? asupra lor biletul de c??l??torie ??n formatul standard pentru acest tip de bilet, dar nu este obligatoriu. Biletul poate fi desc??rcat ??i ??n telefonul mobil. Pasagerii trebuie s?? aib?? la ei cartea de identitate sau/??i pa??aportul valabil??/valabil. Persoanele care beneficiaz?? de reduceri ale tarifelor de c??l??torie trebuie s?? se vor identifica ??n baza unui act oficial de identitate (carte de identitate, carnet de elev/student vizat pentru anul ??n curs, talon de pensie).\n" +
                        "3??. MAIA va aviza prin e-mail orice modificare care este legat?? de c??l??toria clien??ilor care ??i-au achizi??ionat bilete de c??l??torie,??dac?? acestea sunt cunoscute??(dispuse)??cu mai mult de 24 de ore ??nainte de data ??i ora plec??rii. Un utilizator ??nregistrat al serviciului??MAIA este obligat ??i r??m??ne direct??r??spunz??tor de verificarea mail-urilor transmise,??pentru a lua cuno??tin???? de eventualele modific??ri ap??rute ??n desf????urarea c??l??toriei sale.??\n" +
                        "4??. Tranzac??iile bancare de tip e-Commerce (prin care se efectueaz?? plata aferent?? biletelor) sunt asigurate, conform legisla??iei ??n vigoare, de furnizori acredita??i ??n acest sens.??");
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
