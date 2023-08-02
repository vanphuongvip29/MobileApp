package com.sinhvien.tourapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    Spinner spinnerCate;

    ArrayList<Category> dsCategory = new ArrayList<Category>();
    ArrayAdapter myArrayAdapterCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Ánh xạ TextView từ layout vào biến textViewSample
        spinnerCate = rootView.findViewById(R.id.spinnerCategory);

        CategoryDatabaseHandler categoryDatabaseHandler = new CategoryDatabaseHandler(requireContext());


        dsCategory = categoryDatabaseHandler.getAllCategory();
        Log.i("danh sách", "ds: "+ dsCategory);

        myArrayAdapterCategory = new ArrayAdapter<Category>(requireContext(), android.R.layout.simple_dropdown_item_1line, dsCategory);

        spinnerCate.setAdapter(myArrayAdapterCategory);




        return rootView;


    }
}