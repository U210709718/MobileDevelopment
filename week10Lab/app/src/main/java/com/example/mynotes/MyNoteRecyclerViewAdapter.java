package com.example.mynotes;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynotes.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.mynotes.databinding.FragmentNoteBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.example.mynotes.NoteFragment;
import com.example.mynotes.MyNoteRecyclerViewAdapter.ViewHolder; // Import ViewHolder

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> notes;
    private final NoteFragment.OnNoteListInteractionListener listener; // Fixed the typo here

    public MyNoteRecyclerViewAdapter(ArrayList<Note> notes, NoteFragment.OnNoteListInteractionListener listener) {
        this.notes = notes;
        this.listener= listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);

        return new ViewHolder(view); // Add 'new' keyword
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = notes.get(position);
        holder.mHeaderview.setText(holder.mItem.getHeader());
        holder.mDateView.setText(new SimpleDateFormat("yyyy-MM-dd").format(holder.mItem.getDate()));

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNoteSelected(holder.mItem);
                }
            }
        });
        if(position %2 == 1) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }
        else{
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

    }



    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mHeaderview;
        public final TextView mDateView;
        public final View mview;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mview = view;
            mHeaderview = view.findViewById(R.id.note_header);
            mDateView = view.findViewById(R.id.note_date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mHeaderview.getText() + "'";
        }
    }
}