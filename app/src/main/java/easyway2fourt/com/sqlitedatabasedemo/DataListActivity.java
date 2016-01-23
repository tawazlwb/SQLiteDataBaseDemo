package easyway2fourt.com.sqlitedatabasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    UserDBHelper userDBHelper;
    Cursor cursor;
    ListView listView;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_layout);

        listView = (ListView) findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDBHelper = new UserDBHelper(this);
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        cursor = userDBHelper.getInformations(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{
                String name, mobile, email;
                name = cursor.getString(0);
                mobile = cursor.getString(1);
                email = cursor.getString(2);
                DataProvider dataProvider = new DataProvider(name, mobile, email);
                listDataAdapter.add(dataProvider);
            }while (cursor.moveToNext());
        }

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

}
