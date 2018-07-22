package ru.sberbank.android.school.lessons.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.storage.DbManager;
import ru.sberbank.android.school.lessons.adapter.NoteListAdapter;

public class NoteListActivity extends AppCompatActivity {
    public static final String TITLE_EXTRA = "TITLE_EXTRA";
    public static final int ADD_EDIT_NOTE_ACTIVITY = 0;

    private RecyclerView noteRecyclerView;
    private NoteListAdapter adapter;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        init();
        setListeners();
    }

    private void init() {
        noteRecyclerView = findViewById(R.id.note_recycler_view);
        adapter = new NoteListAdapter();
        noteRecyclerView.setAdapter(adapter);
        dbManager = new DbManager(this);
        adapter.setNotes(dbManager.getAllNotes());
    }

    private void setListeners() {
        adapter.setOnItemClickListener(new NoteListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NoteListAdapter.NoteViewHolder holder) {
                startActivity(NoteDetailActivity.newIntent(NoteListActivity.this,
                        holder.getNote().getId()));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setNotes(dbManager.getAllNotes());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_note_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note:
                startActivityForResult(AddEditNoteActivity.newIntent(this), ADD_EDIT_NOTE_ACTIVITY);
                return true;
            case R.id.settings:
                startActivity(SettingsActivity.newIntent(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_EDIT_NOTE_ACTIVITY && resultCode == RESULT_OK) {
            adapter.addNote(dbManager.getLastNote());
        }
    }
}
