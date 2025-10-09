package com.example.colorpicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public abstract class MainActivity extends AppCompatActivity implements OnMessageSendListener {

    FragmentManager fg;
    ColorViewModel colorsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("TAG", String.valueOf(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)));
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            String[] perm = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this,perm,67);
        }
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
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("sms");
        Toast.makeText(this, "Activity: " + message, Toast.LENGTH_SHORT).show();
    }
}