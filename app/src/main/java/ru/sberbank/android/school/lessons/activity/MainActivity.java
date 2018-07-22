package ru.sberbank.android.school.lessons.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.fragment.DisplayFragment;
import ru.sberbank.android.school.lessons.fragment.EditFragment;

public class MainActivity extends AppCompatActivity implements DisplayFragment.Callback,
        EditFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayFragment(DisplayFragment.newInstance());
    }

    @Override
    public void edit() {
        displayFragment(EditFragment.newInstance());
    }

    @Override
    public void done() {
        displayFragment(DisplayFragment.newInstance());
    }

    private void displayFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
