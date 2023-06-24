package com.ortega.tshombo.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.ortega.tshombo.FragmentActivity;
import com.ortega.tshombo.R;

public class SecondFragment extends Fragment {
    MaterialButton materialButton;
    public SecondFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        materialButton = view.findViewById(R.id.normalClick);
        materialButton.setOnClickListener(v -> changeFragment());
    }
    void changeFragment() {
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // replace current fragment by another
        transaction.replace(R.id.fragment_container, FirstFragment.class, null);
        // Commit the transaction
        transaction.commit();

    }
}