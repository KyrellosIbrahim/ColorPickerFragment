package com.example.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class ColorPickFragment extends Fragment {

    private String[] colors;
    private GridLayout grid;

    private OnMessageSendListener mListener;
    private ColorViewModel colorsModel;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int buttonIndex = grid.indexOfChild(v);
            String currentColor = colors[buttonIndex];
            colorsModel = new ViewModelProvider(getActivity()).get(ColorViewModel.class);
            colorsModel.addColor(currentColor);

        }
    };

    public ColorPickFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnMessageSendListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnMessageSendListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the resources String Array bellow
        colors = getResources().getStringArray(R.array.colors_array);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment here
        View view = inflater.inflate(R.layout.fragment_color_pick, container, false);
        grid = view.findViewById(R.id.lightGrid);

        // set the color of each button here
        for(int i = 0; i < grid.getChildCount(); i++) {
            Button currentButton = (Button) grid.getChildAt(i);
            int c = Color.parseColor(colors[i]);
            currentButton.setBackgroundColor(c);
        }

        // bind the listener to each button

        for(int i =0; i < grid.getChildCount(); i++){
            Button currentButton = (Button) grid.getChildAt(i);
            currentButton.setOnClickListener(buttonListener);
        }
        return view;
    }
}