package com.example.thebookbank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.ViewHolder> {

    List<libBook> modelList;
    Context context;

    public BorrowAdapter(Context context, List<libBook> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String nameofBook = modelList.get(position).getbName();
        String descriptionofBook = modelList.get(position).getbDetail();
        int images = modelList.get(position).getbPhoto();

        holder.bName.setText(nameofBook);
        holder.bDescription.setText(descriptionofBook);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount()
    {

        return modelList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView bName, bDescription;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            bName = itemView.findViewById(R.id.BName);
            bDescription = itemView.findViewById(R.id.Bdescription);
            imageView = itemView.findViewById(R.id.BOOKImage);
            itemView.setOnClickListener(this);


        }




        @Override
        public void onClick(View v) {

            // lets get the position of the view in list and then work on it

            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent = new Intent(context, infoact.class);
                context.startActivity(intent);
            }


        }
    }

}
