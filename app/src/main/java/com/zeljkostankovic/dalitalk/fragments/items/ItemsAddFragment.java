package com.zeljkostankovic.dalitalk.fragments.items;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.storage.FirebaseStorage;
import com.zeljkostankovic.dalitalk.R;


public class ItemsAddFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items_add, container, false);

        FirebaseStorage storage = FirebaseStorage.getInstance();

        return view;
    }
}