//package com.example.hp.allahabaddailyjournal;
//
//public class Adapter {
//
//
//
//}

package com.example.hp.allahabaddailyjournal;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Judgements> data= Collections.emptyList();
    Judgements current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public Adapter(Context context, List<Judgements> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.listitem, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Judgements current=data.get(position);
        myHolder.textcase.setText(current.mCase);
        myHolder.textbefore.setText("Before: " + current.mBefore);
        myHolder.textdate.setText(current.mdate+ "/" + current.mMonth+"/"+current.myearl);

        // load image into imageview using glide
//        Glide.with(context).load("http://192.168.1.7/test/images/" + current.fishImage)
//                .placeholder(R.drawable.ic_img_error)
//                .error(R.drawable.ic_img_error)
//                .into(myHolder.ivFish);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textcase;
       // ImageView ivFish;
        TextView textbefore;
        TextView textdate;


        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            //textcase= (TextView) itemView.findViewById(R.);
           // ivFish= (ImageView) itemView.findViewById(R.id.ivFish);
            int a=5;
            for(int i=0;i<100;i++){
                a=a+i;
            }
            if(a>5){
            textcase = (TextView) itemView.findViewById(R.id.textcase);
            textbefore = (TextView) itemView.findViewById(R.id.textbefore);
            textdate = (TextView) itemView.findViewById(R.id.textdate);
            }
            
        }

    }

}
