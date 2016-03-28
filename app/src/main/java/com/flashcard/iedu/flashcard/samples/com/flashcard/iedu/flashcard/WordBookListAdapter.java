package com.flashcard.iedu.flashcard.samples.com.flashcard.iedu.flashcard;

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

/**
 * Created by mhlee on 3/13/16.
 */
public class WordBookListAdapter extends BaseAdapter {

    Context context = null;
    List<String> mainColumn;
    List<String> subColumn1;
    private static LayoutInflater inflater=null;

    public WordBookListAdapter(Context context, List<String> main, List<String> sub1){
        this.context = context;
        this.mainColumn = main;
        this.subColumn1 = sub1;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mainColumn.size();
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
        rowView = inflater.inflate(R.layout.activity_wordbook_list_item, null);
        TextView viewMain =(TextView) rowView.findViewById(R.id.textViewMain);
        TextView viewSub1 =(TextView) rowView.findViewById(R.id.textViewSub1);
        viewMain.setText(this.mainColumn.get(position));
        viewSub1.setText(this.subColumn1.get(position));


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
