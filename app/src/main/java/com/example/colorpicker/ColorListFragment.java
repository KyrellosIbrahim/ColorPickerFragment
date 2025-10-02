package com.example.colorpicker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;


public class ColorListFragment extends Fragment {
    private ColorViewModel colorsModel;
    ListView listViewWidget;

    public ColorListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_color_list, container, false);
        listViewWidget = view.findViewById(R.id.elements);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorsModel = new ViewModelProvider(getActivity()).get(ColorViewModel.class);
        colorsModel.getColors().observe(getViewLifecycleOwner(), new Observer<LinkedList<String>>() {
            @Override
            public void onChanged(LinkedList<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, colorsModel.getColors().getValue());
                listViewWidget.setAdapter(adapter);
            }
        });
    }
}