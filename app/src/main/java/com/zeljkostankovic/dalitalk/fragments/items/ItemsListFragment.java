package com.zeljkostankovic.dalitalk.fragments.items;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zeljkostankovic.dalitalk.R;
import com.zeljkostankovic.dalitalk.adapters.ItemAdapter;
import com.zeljkostankovic.dalitalk.viewModels.ItemViewModel;

public class ItemsListFragment extends Fragment {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    ItemViewModel itemViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items_list, container, false);

        fab = view.findViewById(R.id.fabItemsList);
        recyclerView = view.findViewById(R.id.recyclerItemsList);
        itemAdapter = new ItemAdapter();
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(itemAdapter);

        itemViewModel.getmAllItems().observe( getViewLifecycleOwner(), items -> {
            itemAdapter.setData(items);
        });

        fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_itemsListFragment_to_itemsAddFragment));

        return view;
    }
}