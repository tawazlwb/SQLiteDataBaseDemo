package easyway2fourt.com.sqlitedatabasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMD on 23/01/2016.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();

    static class LayoutHandler{
        TextView name;
        TextView mobile;
        TextView email;
    }


    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        this.list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name = (TextView) row.findViewById(R.id.text_user_name);
            layoutHandler.mobile = (TextView) row.findViewById(R.id.text_user_mobile);
            layoutHandler.email = (TextView) row.findViewById(R.id.text_user_email);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler = (LayoutHandler) row.getTag();
        }

        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.name.setText(dataProvider.getName());
        layoutHandler.mobile.setText(dataProvider.getMobile());
        layoutHandler.email.setText(dataProvider.getEmail());

        return row;
    }
}
