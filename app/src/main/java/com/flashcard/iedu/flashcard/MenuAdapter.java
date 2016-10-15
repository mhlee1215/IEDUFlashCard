package com.flashcard.iedu.flashcard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import edu.iedu.flashcard.dao.domain.WordBook;
import edu.iedu.flashcard.service.WordBookService;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;


public class MenuAdapter extends BaseSwipeAdapter {

    int userId;
    MenuActivity context = null;

    static List<WordBook> wordbookList;
    private static LayoutInflater inflater=null;

    public MenuAdapter(MenuActivity context, List<WordBook> wordbookList, int userId){
        this.context = context;
        this.wordbookList = wordbookList;
        this.userId = userId;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    @Override
    public int getCount() {
        return wordbookList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<WordBook> getData(){
        return this.wordbookList;
    }



    @Override
    public void fillValues(int position, View convertView) {
        TextView viewMain =(TextView) convertView.findViewById(R.id.textViewMain);
        viewMain.setText(this.wordbookList.get(position).getName());
        TextView viewCount =(TextView) convertView.findViewById(R.id.textViewCount);
        viewCount.setText(this.wordbookList.get(position).getSize()+"");
      //  convertView.setId(this.wordbookList.get(position).getId());

        final Button btnFavorite = (Button) convertView.findViewById(R.id.button_favorite);

        //System.out.println("isfavorite:"+wordbookList.get(position).getIsfavorite());
        if("Y".equalsIgnoreCase(wordbookList.get(position).getIsfavorite())){
            btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_black_48dp));
        }
    }

    static View rowView2;
    @Override
    public View generateView(final int position, ViewGroup parent) {

        View rowView;
        rowView = inflater.inflate(R.layout.menu_list_item, null);
        rowView2 = rowView;

        View v = rowView.findViewById(getSwipeLayoutResourceId(position));
        System.out.println(":::"+v);

        SwipeLayout swipeLayout = (SwipeLayout)v;

        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                //YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.id));
            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(context, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });

//        rowView.findViewById(R.id.delete).setOnClickListener(new MyDeleteClickListener(act, swipeLayout, position));
//        rowView.findViewById(R.id.reset).setOnClickListener(new MyResetClickListener(act, swipeLayout, position));
//        TextView viewMain =(TextView) rowView.findViewById(R.id.textViewMain);
//        viewMain.setText(this.wordbookList.get(position).getName());
//        TextView viewCount =(TextView) rowView.findViewById(R.id.textViewCount);
//        viewCount.setText(this.wordbookList.get(position).getSize()+"");
//        rowView.setId(this.wordbookList.get(position).getId());
//
//        final Button btnFavorite = (Button) rowView.findViewById(R.id.button_favorite);
//
//        System.out.println("isfavorite:"+wordbookList.get(position).getIsfavorite());
//        if("Y".equalsIgnoreCase(wordbookList.get(position).getIsfavorite())){
//            btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_black_48dp));
//        }

        final Button btnFavorite = (Button) rowView.findViewById(R.id.button_favorite);
        OnClickListener btnListener = new OnClickListener(){
            int wordbookId;
            int wordId;

            public void setProperty(int wordBookId, int wordId){
                this.wordbookId = wordBookId;
                this.wordId = wordId;
            }

            @Override
            public void onClick(View v) {
                System.out.println("Click! Favorite");
                if("Y".equalsIgnoreCase(wordbookList.get(position).getIsfavorite())){
                    wordbookList.get(position).setIsfavorite("N");
                    btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.button_heart));
                }else{
                    wordbookList.get(position).setIsfavorite("Y");
                    btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_black_48dp));
                }

                WordBookService.updateWordBook(wordbookList.get(position));
            }

        };

        btnFavorite.setOnClickListener(btnListener);

        rowView.findViewById(R.id.delete).setOnClickListener(new MyDeleteClickListener(context, swipeLayout, position));
//
//        btnFavorite.setOnClickListener(new OnClickListener(){
//            int wordbookId;
//            int wordId;
//
//            public void setProperty(int wordBookId, int wordId){
//                this.wordbookId = wordBookId;
//                this.wordId = wordId;
//            }
//
//            @Override
//            public void onClick(View v) {
//                System.out.println("Click! Favorite");
//            }
//        }.setProperty(10, 10));


        LinearLayout ll = (LinearLayout)rowView.findViewById(R.id.menu_item_layout);
        ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(context, WordBookListActivity.class);
                TextView tv = (TextView)v.findViewById(R.id.textViewMain);



                i.putExtra("WORDBOOK_NAME",tv.getText());
                //wordbookList.get(v.get)
                //wordbookList.get(position);
                i.putExtra("WORDBOOK_ID", wordbookList.get(position).getId());
                v.getContext().startActivity(i);

            }
        });

        TextView viewMain =(TextView) rowView.findViewById(R.id.textViewMain);
        viewMain.setText(this.wordbookList.get(position).getName());
        TextView viewCount =(TextView) rowView.findViewById(R.id.textViewCount);
        viewCount.setText(this.wordbookList.get(position).getSize()+"");
      //  rowView.setId(this.wordbookList.get(position).getId());

//        final Button btnFavorite = (Button) rowView.findViewById(R.id.button_favorite);

       // System.out.println("isfavorite:"+wordbookList.get(position).getIsfavorite());
        if("Y".equalsIgnoreCase(wordbookList.get(position).getIsfavorite())){
            btnFavorite.setBackground(context.getResources().getDrawable(R.drawable.ic_favorite_black_48dp));
        }

        return rowView;
    }

    public class MyDeleteClickListener implements View.OnClickListener {
        MenuActivity act;
        SwipeLayout swipeLayout;
        int position;

        public MyDeleteClickListener(MenuActivity act, SwipeLayout swipeLayout, int position){
            this.act = act;
            this.position = position;
            this.swipeLayout = swipeLayout;
        }

        @Override
        public void onClick(View v) {
            wordbookList.remove(position);
            swipeLayout.close(false);
//            act.selectUpperRow();
//            act.updateNumbers();
            act.adapter.notifyDataSetChanged();
        }
    }
}
