package com.kshimauchi.enhancedtodolist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kshimauchi.enhancedtodolist.data.Contract;

/**
 * Created by kshim on 7/17/2017.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ItemHolder> {

    private Cursor cursor;
    private ItemClickListener listener;
    private String TAG = "todolistadapter";

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public interface ItemClickListener {
        void onItemClick(int pos, String description, String duedate,String spinnerResult, long id);
    }

    public TodoListAdapter(Cursor cursor, ItemClickListener listener) {
        this.cursor = cursor;
        this.listener = listener;
    }

    public void swapCursor(Cursor newCursor){
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView descr;
        TextView due;
        String duedate;
        String description;
        String spinnerResult; //Had to add it here for the itemClickListener to be used in Main
        long id;
        ItemHolder(View view) {
            super(view);
            descr = (TextView) view.findViewById(R.id.description);
            due = (TextView) view.findViewById(R.id.dueDate);
            view.setOnClickListener(this);
        }

        public void bind(ItemHolder holder, final int pos) {
            cursor.moveToPosition(pos);
            id = cursor.getLong(cursor.getColumnIndex(Contract.TABLE_TODO._ID));
            Log.d(TAG, "deleting id: " + id);

            duedate = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DUE_DATE));
            description = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DESCRIPTION));
           //adding to the database
            spinnerResult = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_CHOICE));
            descr.setText(description);
            due.setText(duedate);

            holder.itemView.setTag(id);
        } //bind method end

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos, description, duedate,spinnerResult,id);  //added spinnerResult here also
        }

    }

}
