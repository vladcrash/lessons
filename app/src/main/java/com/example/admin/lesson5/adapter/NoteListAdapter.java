package com.example.admin.lesson5.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.lesson5.model.Note;
import com.example.admin.lesson5.R;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NoteViewHolder(inflater.inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public void addNote(Note note) {
        notes.add(note);
        notifyDataSetChanged();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(NoteListAdapter.NoteViewHolder holder);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView noteTitle;
        private Note note;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteTitle = (TextView) itemView;
            itemView.setOnClickListener(this);
        }

        public void bind(Note note) {
            this.note = note;
            noteTitle.setText(note.getTitle());
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(this);
        }

        public Note getNote() {
            return note;
        }
    }
}
