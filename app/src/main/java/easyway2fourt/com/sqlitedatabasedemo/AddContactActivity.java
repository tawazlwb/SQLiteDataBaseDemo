package easyway2fourt.com.sqlitedatabasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    EditText contactName, contactMobile, contactEmail;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);

        contactName = (EditText) findViewById(R.id.id_contact_name);
        contactMobile = (EditText) findViewById(R.id.id_contact_number);
        contactEmail = (EditText) findViewById(R.id.id_contact_email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addContact(View view){
        String name = contactName.getText().toString();
        String mobile = contactMobile.getText().toString();
        String email = contactEmail.getText().toString();

        userDBHelper = new UserDBHelper(context);
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        userDBHelper.addInformations(name, mobile, email, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG).show();
        userDBHelper.close();
    }


}
