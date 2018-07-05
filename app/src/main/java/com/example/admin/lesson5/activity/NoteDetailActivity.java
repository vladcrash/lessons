package com.example.admin.lesson5.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.lesson5.storage.DataStorage;
import com.example.admin.lesson5.storage.DbManager;
import com.example.admin.lesson5.model.Note;
import com.example.admin.lesson5.R;

public class NoteDetailActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "ID";
    public static final int EDIT_NOTE = 0;

    private TextView title;
    private TextView content;
    private DbManager dbManager;
    private Note note;
    private DataStorage dataStorage;

    public static final Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        init();
    }

    private void init() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        dbManager = new DbManager(this);
        dataStorage = new DataStorage(this);
        updateUI();
    }

    private void updateUI() {
        note = dbManager.findNoteById(getIntent().getLongExtra(EXTRA_ID, 0));
        title.setText(note.getTitle());
        content.setText(note.getContent());
        setTextViewParams(title);
        setTextViewParams(content);
    }

    private void setTextViewParams(TextView textView) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, dataStorage.getTextSize());
        textView.setTextColor(dataStorage.getColor());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_note_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                startActivityForResult(AddEditNoteActivity.newIntent(this, note.getId()), EDIT_NOTE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_NOTE && resultCode == RESULT_OK) {
            updateUI();
        }
    }
}
