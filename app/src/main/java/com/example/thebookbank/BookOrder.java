package com.example.thebookbank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookOrder extends RecyclerView.Adapter<BookOrder.ViewHolder> {



    List<Books> bookList;
    Context context;



    public BookOrder(Context context, List<Books> modelList) {
        this.context = context;
        this.bookList = modelList;
    }



    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {



        View view = LayoutInflater.from(context).inflate(R.layout.book_shop_list, parent, false);
        return new ViewHolder(view);



    }



    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {



// here we will find the position and start setting the output on our views



        String nameofBook = bookList.get(position).getBookName();
        String descriptionofBook = bookList.get(position).getBookDetail();
        int images = bookList.get(position).getBookPhoto();



        holder.BookName.setText(nameofBook);
        holder.BookDescription.setText(descriptionofBook);
        holder.imageView.setImageResource(images);



    }



    @Override
    public int getItemCount() {
        return bookList.size();
    }



// in order to make our views responsive we can implement onclicklistener on our recyclerview



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



// here we will find the views on which we will inflate our data



        TextView BookName, BookDescription;
        ImageView imageView;



        public ViewHolder(View itemView) {
            super(itemView);



            BookName = itemView.findViewById(R.id.BookName);
            BookDescription = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.bookimg);
            itemView.setOnClickListener(this);




        }



        @Override
        public void onClick(View v) {

// lets get the position of the view in list and then work on it

            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent = new Intent(context, Book_info.class);
                context.startActivity(intent);
            }

            if (position == 1) {
                Intent intent2 = new Intent(context, LatteActivity.class);
                context.startActivity(intent2);
            }
        }
    }
}
