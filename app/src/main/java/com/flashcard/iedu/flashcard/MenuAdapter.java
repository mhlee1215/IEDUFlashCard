package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import java.util.List;


public class MenuAdapter extends BaseAdapter {

    Context context = null;
    List<Title> titleList;
    private static LayoutInflater inflater=null;

    public MenuAdapter(Context context, List<Title> titleList){
        this.context = context;
        this.titleList = titleList;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        rowView = inflater.inflate(R.layout.activity_menu_list_item, null);
        TextView viewMain =(TextView) rowView.findViewById(R.id.textViewMain);
        viewMain.setText(this.titleList.get(position).getTitle());


        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView viewMain =(TextView) v.findViewById(R.id.textViewMain);
                Toast.makeText(context, "You Clicked " + viewMain.getText(), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
