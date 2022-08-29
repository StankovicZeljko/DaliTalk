package com.zeljkostankovic.dalitalk.fragments.config;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.zeljkostankovic.dalitalk.R;


public class ConfigFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        ImageButton itemsButtom = (ImageButton) view.findViewById(R.id.imageButtonItems);
        itemsButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_configFragment_to_itemsListFragment);
            }
        });



        return view;
    }
}