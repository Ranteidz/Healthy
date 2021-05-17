package com.example.healthy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthy.R;
import com.example.healthy.adapter.HistoryItemAdapter;
import com.example.healthy.models.Item;
import com.example.healthy.viewmodels.HomeViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;
    TextView minutedMeditated;
    TextView exercisesDone;
    TextView daysHydrated;
    TextView fastCompleted;
    TextView minuteMeditatedValue;
    RecyclerView recyclerView;
    HistoryItemAdapter historyItemAdapter;
    HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        minutedMeditated = view.findViewById(R.id.home_minutes_meditated);
        exercisesDone = view.findViewById(R.id.home_excercises_completed);
        daysHydrated = view.findViewById(R.id.home_hydrated_days);
        fastCompleted = view.findViewById(R.id.home_fasts_completed);
        minuteMeditatedValue = view.findViewById(R.id.home_minutes_meditated_value);

        recyclerView = view.findViewById(R.id.historyRv);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        ArrayList<Item> itemListTest = new ArrayList<>();
        itemListTest.add(new Item("baseball", "blablabla", "davinki"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));
        itemListTest.add(new Item("baseball", "blablabla", "no completed"));

        historyItemAdapter = new HistoryItemAdapter(itemListTest);
        recyclerView.setAdapter(historyItemAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getMinutesMeditated().observe(this, minutes->{
            minuteMeditatedValue.setText(String.valueOf(minutes));
        });
        super.onCreate(savedInstanceState);
    }
}
