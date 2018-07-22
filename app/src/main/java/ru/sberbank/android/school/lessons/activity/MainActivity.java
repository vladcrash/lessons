package ru.sberbank.android.school.lessons.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.lesson5.R;
import ru.sberbank.android.school.lessons.fragment.FragmentOne;
import ru.sberbank.android.school.lessons.fragment.FragmentThree;
import ru.sberbank.android.school.lessons.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.horizontal_container, FragmentOne.newInstance())
                .replace(R.id.vertical_container_one, FragmentTwo.newInstance())
                .replace(R.id.vertical_container_two, FragmentThree.newInstance())
                .commit();
    }
}
