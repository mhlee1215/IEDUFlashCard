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

import java.util.List;

import edu.iedu.flashcard.dao.domain.WordBook;

/**
 * Created by Yujin on 5/27/2016.
 */
public class SearchAdapter extends BaseAdapter{

    Context context = null;
    List<WordBook> wordbookList;
    private static LayoutInflater inflater=null;

    public SearchAdapter(Context context, List<WordBook> wordbookList){
        this.context = context;
        this.wordbookList = wordbookList;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView;
        rowView = inflater.inflate(R.layout.activity_menu_list_item, null);
        TextView viewMain =(TextView) rowView.findViewById(R.id.textViewMain);
        viewMain.setText(this.wordbookList.get(position).getName());

        TextView viewCount =(TextView) rowView.findViewById(R.id.textViewCount);
        viewCount.setText(this.wordbookList.get(position).getSize()+"");


        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                TextView viewMain =(TextView) v.findViewById(R.id.textViewMain);
//                Toast.makeText(context, "You Clicked " + viewMain.getText(), Toast.LENGTH_LONG).show();

                Intent i = new Intent(context, WordBookInfoActivity.class);
                TextView tv = (TextView)v.findViewById(R.id.textViewMain);

                i.putExtra("WORDBOOK_NAME",tv.getText());
                //wordbookList.get(v.get)
                //wordbookList.get(position);
                i.putExtra("WORDBOOK_ID", wordbookList.get(position).getId());
                v.getContext().startActivity(i);

            }
        });
        return rowView;
    }
}

