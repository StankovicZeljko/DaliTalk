package com.zeljkostankovic.dalitalk.fragments.procedures;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zeljkostankovic.dalitalk.R;


public class ProceduresAddFragment extends Fragment {

    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_procedures_add, container, false);

        fab = view.findViewById(R.id.fabProcedAdd);

        fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_proceduresAddFragment_to_proceduresAddChooseItemFragment));



        return view;
    }
}