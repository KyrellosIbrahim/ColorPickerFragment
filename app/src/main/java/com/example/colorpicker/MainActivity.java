package com.example.colorpicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnMessageSendListener {

    FragmentManager fg;
    ColorViewModel colorsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            colorsModel = new ViewModelProvider(this).get(ColorViewModel.class);
            fg = getSupportFragmentManager();
            FragmentTransaction trans = fg.beginTransaction();
            ColorPickFragment cf = new ColorPickFragment();
            trans.add(R.id.colorFragment, cf, "colorFrag");
            ColorListFragment cl = new ColorListFragment();
            trans.add(R.id.listFragment, cl, "listFrag");
            trans.commit();
        }
    }

    @Override
    public void onMessageSend(String message) {

    }
}