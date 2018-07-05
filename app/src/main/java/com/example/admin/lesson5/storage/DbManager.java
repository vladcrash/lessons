package com.example.admin.lesson5.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.admin.lesson5.model.Note;

import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private DbHelper dbHelper;

    public DbManager(Context context) {
        dbHelper = DbHelper.getInstance(context);
    }

    public long addNote(Note note) {
        SQLiteDatabase db = null;
        long id = 0;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            id = addNote(db, note);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }

        return id;
    }

    @Nullable
    public Note findNoteById(long id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Note note = null;

        try {
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            cursor = db.query(DbHelper.TABLE_NAME, null, null, null,
                    null, null, null);
            note = findNoteById(id, cursor);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }

        return note;
    }

    @Nullable
    public Note getLastNote() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Note note = null;

        try {
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            cursor = db.query(DbHelper.TABLE_NAME, null, null, null,
                    null, null, null);
            note = getLastNote(cursor);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }

        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            cursor = db.query(DbHelper.TABLE_NAME, null, null, null,
                    null, null, null);
            notes = getAllNotes(cursor);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }

        return notes;
    }

    public void update(Note note) {
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            db.update(DbHelper.TABLE_NAME, getContentValues(note), DbHelper.COLUMN_ID + " = ?",
                    new String[] {String.valueOf(note.getId())});
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
    }

    private long addNote(SQLiteDatabase db, Note note) {
        return db.insert(DbHelper.TABLE_NAME, null, getContentValues(note));
    }

    @NonNull
    private ContentValues getContentValues(Note note) {
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.COLUMN_TITLE, note.getTitle());
        cv.put(DbHelper.COLUMN_CONTENT, note.getContent());
        return cv;
    }

    private Note findNoteById(long id, Cursor cursor) {
        Note note = null;
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int titleIndex = cursor.getColumnIndex(DbHelper.COLUMN_TITLE);
        int contentIndex = cursor.getColumnIndex(DbHelper.COLUMN_CONTENT);

        while (cursor.moveToNext()) {
            if (cursor.getLong(idIndex) == id) {
                note = new Note(cursor.getLong(idIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(contentIndex));
            }
        }

        return note;
    }

    @Nullable
    private Note getLastNote(Cursor cursor) {
        Note note = null;
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int titleIndex = cursor.getColumnIndex(DbHelper.COLUMN_TITLE);
        int contentIndex = cursor.getColumnIndex(DbHelper.COLUMN_CONTENT);

        if (cursor.moveToLast()) {
            note = new Note(cursor.getLong(idIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(contentIndex));
        }

        return note;
    }

    @NonNull
    private List<Note> getAllNotes(Cursor cursor) {
        List<Note> notes = new ArrayList<>();
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int titleIndex = cursor.getColumnIndex(DbHelper.COLUMN_TITLE);
        int contentIndex = cursor.getColumnIndex(DbHelper.COLUMN_CONTENT);

        while (cursor.moveToNext()) {
            Note note = new Note(cursor.getLong(idIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(contentIndex));
            notes.add(note);
        }

        return notes;
    }
}


