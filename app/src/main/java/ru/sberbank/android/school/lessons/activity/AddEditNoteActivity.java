package ru.sberbank.android.school.lessons.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.storage.DbManager;
import ru.sberbank.android.school.lessons.model.Note;


public class AddEditNoteActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "ID";
    private static final String ACTION_ADD = "com.example.admin.lesson5.add";
    private static final String ACTION_EDIT = "com.example.admin.lesson5.edit";

    private EditText title;
    private EditText content;
    private DbManager dbManager;
    private Note note;

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddEditNoteActivity.class);
        intent.setAction(ACTION_ADD);
        return intent;
    }

    public static final Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, AddEditNoteActivity.class);
        intent.setAction(ACTION_EDIT);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);
        init();
    }

    private void init() {
        title = findViewById(R.id.title_edit_text);
        content = findViewById(R.id.content_edit_text);
        dbManager = new DbManager(this);
        long id = getIntent().getLongExtra(EXTRA_ID, 0);
        note = dbManager.findNoteById(id);
        if (note != null) {
            title.setText(note.getTitle());
            content.setText(note.getContent());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_edit_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                sendData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendData() {
        String action = getIntent().getAction();

        if (action != null) {
            if (action.equals(ACTION_ADD)) {
                dbManager.addNote(getNote());
            } else if (action.equals(ACTION_EDIT)) {
                Note note = getNote();
                note.setId(this.note.getId());
                dbManager.update(note);
            }
        }

        setResult(RESULT_OK);
        finish();
    }

    private Note getNote() {
        String titleNote = title.getText().toString();
        String contentNote = content.getText().toString();

        if (titleNote.isEmpty()) {
            titleNote = "no title";
        }

        return new Note(titleNote,
                contentNote);
    }
}
