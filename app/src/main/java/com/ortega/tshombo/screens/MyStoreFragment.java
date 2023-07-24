package com.ortega.tshombo.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ortega.tshombo.DetailPhone;
import com.ortega.tshombo.R;
import com.ortega.tshombo.SaveActivity;
import com.ortega.tshombo.adapters.PhoneAdapter;
import com.ortega.tshombo.models.MagasinModel;
import com.ortega.tshombo.models.TelephoneModel;

import java.util.ArrayList;
import java.util.Objects;


public class MyStoreFragment extends Fragment {

    TextView magasinName, magasinAddress, nbrPhone;

    RelativeLayout screen_not;
    ScrollView see_data;

    ExtendedFloatingActionButton btnAddPhone;

    DatabaseReference databaseReference;

    PhoneAdapter phoneAdapter;

    ArrayList<TelephoneModel> telephoneArrayList;

    RecyclerView recyclerView;

    String idMagasin;

    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_store, container, false);
    }

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        magasinAddress = view.findViewById(R.id.magasinAddress);
        magasinName = view.findViewById(R.id.magasinName);
        nbrPhone = view.findViewById(R.id.nbrPhone);

        btnAddPhone = view.findViewById(R.id.btnAddPhone);

        recyclerView = view.findViewById(R.id.recyclerViewPhone);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        screen_not = view.findViewById(R.id.screen_not);
        see_data = view.findViewById(R.id.see_data);

        pref = requireActivity().getApplicationContext().getSharedPreferences("userData", 0);
        this.searchMagasin(pref.getString("uuid", null));


    }

    private void dataInitialize(String magasinId) {

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

                    if (telephoneModel.getIdMagasin().equals(magasinId)) {
                        telephoneArrayList.add(telephoneModel);
                    }
                }
                nbrPhone.setText(String.valueOf(telephoneArrayList.size()));
                phoneAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    protected void dataInitializeMagasin(String magasinId) {

        databaseReference = FirebaseDatabase.getInstance().getReference("magasins");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    MagasinModel magasinModel = itemSnapshot.getValue(MagasinModel.class);
                    assert magasinModel != null;
                    magasinModel.setId(itemSnapshot.getKey());

                    if (magasinModel.getId().equals(magasinId)) {
                        magasinName.setText(magasinModel.getNom());
                        magasinAddress.setText(magasinModel.getAdresse());

                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    protected void searchMagasin(String uuid) {
        databaseReference = FirebaseDatabase.getInstance().getReference("magasins");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    MagasinModel magasinModel = itemSnapshot.getValue(MagasinModel.class);
                    assert magasinModel != null;
                    magasinModel.setId(itemSnapshot.getKey());

                    if (magasinModel.getIdUser().equals(uuid)) {
                        idMagasin = magasinModel.getId();
                        if (idMagasin != null) {

                            screen_not.setVisibility(View.INVISIBLE);
                            see_data.setVisibility(View.VISIBLE);
                            dataInitialize(idMagasin);
                            dataInitializeMagasin(idMagasin);


                            phoneAdapter = new PhoneAdapter(getContext(), telephoneArrayList, telephoneModel -> {
                                Intent intent = new Intent(getContext(), SaveActivity.class);
                                intent.putExtra("id", telephoneModel.getId());
                                intent.putExtra("idMagasin", telephoneModel.getIdMagasin());
                                intent.putExtra("marque", telephoneModel.getMarque());
                                intent.putExtra("nom", telephoneModel.getNom());
                                intent.putExtra("prix", telephoneModel.getPrix());
                                intent.putExtra("description", telephoneModel.getDescription());
                                intent.putExtra("photo", telephoneModel.getPhoto());
                                startActivity(intent);
                            });

                            recyclerView.setAdapter(phoneAdapter);
                            recyclerView.setClickable(true);

                            phoneAdapter.notifyDataSetChanged();

                            btnAddPhone.setOnClickListener(v -> {
                                Intent intent = new Intent(getContext(), SaveActivity.class);
                                intent.putExtra("id", "");
                                intent.putExtra("nom", "");
                                intent.putExtra("prix", "");
                                intent.putExtra("description", "");
                                intent.putExtra("photo", "");
                                startActivity(intent);
                            });
                        } else {

                        }
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}