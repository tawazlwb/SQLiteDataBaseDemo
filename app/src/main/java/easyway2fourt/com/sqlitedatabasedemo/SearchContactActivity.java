package easyway2fourt.com.sqlitedatabasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchContactActivity extends AppCompatActivity {

    TextView displayMobile, displayEmail;
    EditText searchName;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String userSearchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact_layout);

        displayMobile = (TextView) findViewById(R.id.display_mobile);
        displayEmail = (TextView) findViewById(R.id.display_email);
        searchName = (EditText) findViewById(R.id.search_name);

        displayMobile.setVisibility(View.GONE);
        displayEmail.setVisibility(View.GONE);

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
            displayMobile.setText(cursor.getString(0));
            displayEmail.setText(cursor.getString(1));
            displayMobile.setVisibility(View.VISIBLE);
            displayEmail.setVisibility(View.VISIBLE);
        }

    }

}
