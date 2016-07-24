package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import java.util.List;

import edu.iedu.flashcard.dao.domain.WordBook;


public class MenuAdapter extends BaseAdapter {

    int userId;
    Context context = null;

    static List<WordBook> wordbookList;
    private static LayoutInflater inflater=null;

    public MenuAdapter(Context context, List<WordBook> wordbookList, int userId){
        this.context = context;
        this.wordbookList = wordbookList;
        this.userId = userId;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {return wordbookList.size();
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
        viewMain.setText(this.wordbookList.get(position).getName());
        TextView viewCount =(TextView) rowView.findViewById(R.id.textViewCount);
        viewCount.setText(this.wordbookList.get(position).getSize()+"");
        rowView.setId(this.wordbookList.get(position).getId());



        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(context, WordBookListActivity.class);
                TextView tv = (TextView)v.findViewById(R.id.textViewMain);

                i.putExtra("WORDBOOK_NAME",tv.getText());
                //wordbookList.get(v.get)
                //wordbookList.get(position);
                i.putExtra("WORDBOOK_ID", v.getId());
                v.getContext().startActivity(i);

            }
        });
        return rowView;

    }
}
