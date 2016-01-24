package easyway2fourt.com.sqlitedatabasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateContactActivity extends AppCompatActivity {

    TextView updateConTitle;
    EditText searchName, newName, newMoible, newEmail;
    Button updateCont;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String userSearchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact_layout);

        searchName = (EditText) findViewById(R.id.search_name);
        newName = (EditText) findViewById(R.id.new_name);
        newMoible = (EditText) findViewById(R.id.new_mobile);
        newEmail = (EditText) findViewById(R.id.new_email);
        updateConTitle = (TextView) findViewById(R.id.update_title);
        updateCont = (Button) findViewById(R.id.update_contact_button);

        newName.setVisibility(View.INVISIBLE);
        newMoible.setVisibility(View.INVISIBLE);
        newEmail.setVisibility(View.INVISIBLE);
        updateConTitle.setVisibility(View.INVISIBLE);
        updateCont.setVisibility(View.INVISIBLE);

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

    public void searchContact(View view){
        userSearchName = searchName.getText().toString();
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor = userDBHelper.getContact(userSearchName,sqLiteDatabase);
        if(cursor.moveToFirst()){
            newName.setText(userSearchName);
            newMoible.setText(cursor.getString(0));
            newEmail.setText(cursor.getString(1));

            newName.setVisibility(View.VISIBLE);
            newMoible.setVisibility(View.VISIBLE);
            newEmail.setVisibility(View.VISIBLE);
            updateConTitle.setVisibility(View.VISIBLE);
            updateCont.setVisibility(View.VISIBLE);

        }
        else {
            newName.setVisibility(View.INVISIBLE);
            newMoible.setVisibility(View.INVISIBLE);
            newEmail.setVisibility(View.INVISIBLE);
            updateConTitle.setVisibility(View.INVISIBLE);
            updateCont.setVisibility(View.INVISIBLE);
        }

    }

    public void updateContact(View view){
        userSearchName = searchName.getText().toString();
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        String name, mobile, email;
        name = newName.getText().toString();
        mobile = newMoible.getText().toString();
        email = newEmail.getText().toString();

        int count = userDBHelper.updateInformations(userSearchName, name, mobile, email, sqLiteDatabase);
        userDBHelper.close();
        Toast.makeText(getApplicationContext(), count + " row(s) updated", Toast.LENGTH_LONG).show();
        finish();
    }

}
