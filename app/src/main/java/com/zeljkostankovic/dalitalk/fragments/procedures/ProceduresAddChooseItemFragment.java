package com.zeljkostankovic.dalitalk.fragments.procedures;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeljkostankovic.dalitalk.R;
import com.zeljkostankovic.dalitalk.adapters.ChooseItemAdapter;
import com.zeljkostankovic.dalitalk.viewModels.ItemViewModel;


public class ProceduresAddChooseItemFragment extends Fragment {

    RecyclerView recyclerView;
    ChooseItemAdapter chooseItemAdapter;
    ItemViewModel itemViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_procedures_add_choose_item, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewExistingItems);
        chooseItemAdapter = new ChooseItemAdapter();
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(chooseItemAdapter);

        itemViewModel.getmAllItems().observe( getViewLifecycleOwner(), items -> {
            chooseItemAdapter.setData(items);
        });


        return view;
    }
}