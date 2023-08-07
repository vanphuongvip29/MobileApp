package com.sinhvien.tourapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Set;


public class HomeFragment extends Fragment {


    Spinner spinnerCate;

    ArrayList<Category> dsCategory = new ArrayList<Category>();
    ArrayAdapter myArrayAdapterCategory;


    ArrayList<Tour> allTours;
    ArrayList<Tour> filteredTours;

    ArrayAdapterTourHome myArrayAdapterTour;
    ListView listViewTour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Ánh xạ TextView từ layout vào biến textViewSample
        spinnerCate = rootView.findViewById(R.id.spinnerCategory);

        CategoryDatabaseHandler categoryDatabaseHandler = new CategoryDatabaseHandler(requireContext());


        dsCategory = categoryDatabaseHandler.getAllCategory();
        dsCategory.add(0,new Category("0","Chọn tất cả"));
//        Log.i("danh sách", "ds: "+ dsCategory);



        myArrayAdapterCategory = new ArrayAdapter<Category>(requireContext(), android.R.layout.simple_dropdown_item_1line, dsCategory);

        spinnerCate.setAdapter(myArrayAdapterCategory);



        //Tour
        listViewTour = rootView.findViewById(R.id.listViewTour);
        TourDatabaseHandler tourDatabaseHandler = new TourDatabaseHandler(requireContext());



        //
        allTours = tourDatabaseHandler.getAllTour();// Phương thức này trả về danh sách toàn bộ tour.
        filteredTours = new ArrayList<>(allTours);


        Log.i("ds", "ds: "+ allTours);

        myArrayAdapterTour = new ArrayAdapterTourHome(getActivity(), R.layout.item_list_tour_home,filteredTours);

        listViewTour.setAdapter(myArrayAdapterTour);

        // lọc theo category
        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String idCate = dsCategory.get(i).getId();
                if (idCate.equals("0")) {
                    // Nếu người dùng chọn "Chọn tất cả", hiển thị tất cả các tour
                    showAllTours();
                }
                else{
//                    Log.i("category", "onItemSelected: " + idCate);
                    filterToursByCategory(idCate);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;


    }
    // Phương thức này sẽ hiển thị tất cả các tour
    private void showAllTours() {
        filteredTours.clear();
        filteredTours.addAll(allTours);
        myArrayAdapterTour.notifyDataSetChanged(); // Cập nhật danh sách tour
    }
    // Phương thức này sẽ lọc danh sách các tour dựa trên danh mục đã chọn
    private void filterToursByCategory(String selectedCategory) {
        filteredTours.clear();
        for (Tour tour : allTours) {
            if (tour.getCategory_id().equals(selectedCategory)) {
                filteredTours.add(tour);
            }
        }
        myArrayAdapterTour.notifyDataSetChanged(); //
    }



}