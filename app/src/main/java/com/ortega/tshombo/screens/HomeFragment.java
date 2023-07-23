package com.ortega.tshombo.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ortega.tshombo.DetailPhone;
import com.ortega.tshombo.MapsActivity;
import com.ortega.tshombo.R;
import com.ortega.tshombo.SearchActivity;
import com.ortega.tshombo.adapters.PhoneAdapter;
import com.ortega.tshombo.models.PromotionModel;
import com.ortega.tshombo.models.TelephoneModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {

    TextView showMore;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceSlide;
    List<SlideModel> slideModels;
    ImageSlider imageSlider;
    PhoneAdapter phoneAdapter;
    ArrayList<TelephoneModel> telephoneArrayList;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();
        sliderInitialize();

        recyclerView = view.findViewById(R.id.recyclerViewPhone);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        showMore = view.findViewById(R.id.showMore);

        phoneAdapter = new PhoneAdapter(getContext(), telephoneArrayList, telephoneModel -> {
            Intent intent = new Intent(getContext(), DetailPhone.class);
            intent.putExtra("marque", telephoneModel.getMarque());
            intent.putExtra("uuid", telephoneModel.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(phoneAdapter);
        recyclerView.setClickable(true);

        phoneAdapter.notifyDataSetChanged();
        showMore = view.findViewById(R.id.showMore);
        imageSlider = view.findViewById(R.id.slider);

        showMore.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

    }

    private void sliderInitialize() {
        slideModels = new ArrayList<>();

        databaseReferenceSlide = FirebaseDatabase.getInstance().getReference("promotions");
        databaseReferenceSlide.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slideModels.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    PromotionModel promotionModel = itemSnapshot.getValue(PromotionModel.class);
                    assert promotionModel != null;
                    slideModels.add(new SlideModel(promotionModel.getPromotion(), ScaleTypes.CENTER_CROP));

                }
                imageSlider.setImageList(slideModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void dataInitialize() {

        telephoneArrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("telephones");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                telephoneArrayList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    TelephoneModel telephoneModel = itemSnapshot.getValue(TelephoneModel.class);
                    assert telephoneModel != null;
                    telephoneModel.setId(itemSnapshot.getKey());
                    System.out.println("[id] " + itemSnapshot.getKey());
                    telephoneArrayList.add(telephoneModel);
                }
                phoneAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}