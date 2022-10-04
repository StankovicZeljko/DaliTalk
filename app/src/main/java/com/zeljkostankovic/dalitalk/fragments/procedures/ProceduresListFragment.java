package com.zeljkostankovic.dalitalk.fragments.procedures;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zeljkostankovic.dalitalk.R;


public class ProceduresListFragment extends Fragment {

    FloatingActionButton fab;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_procedures_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerProceduresList);
        fab = view.findViewById(R.id.fabProceduresList);

        fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_proceduresListFragment_to_proceduresAddFragment));


        return view;
    }
}