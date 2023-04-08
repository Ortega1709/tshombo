package com.ortega.tshombo.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.ortega.tshombo.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageSlider imageSlider = view.findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://i0.wp.com/dz-mobiles.com/wp-content/uploads/2017/02/ooredoo_condor_promotion.jpg?resize=800%2C444&ssl=1", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://www.lesmobiles.com/img/actus/Promo-smartphone-Ce-smartphone-Samsung-pas-cher-est-prix-cass-chez-ce-marchand-1677763723-large.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://www.edcom.fr/img/actus/Black-Friday-iPhone-13-et-Iphone-13-mini-en-promo-chez-RED-by-SFR-1637847923-large.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://s.alicdn.com/@sc04/kf/Hd59205ed49144d74aa2be89466263390j.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://pic.clubic.com/v1/images/2045745/raw?fit=max&width=1200&hash=c2505eaadac79e2c5eb36637e893356732b4f650", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://d1fmx1rbmqrxrr.cloudfront.net/cnet/optim/i/edit/2022/11/iphone-14-1200__w570.jpg", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://www.android-mt.com/wp-content/uploads/2021/06/RSminote10.jpg.webp", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://fimgkzkm.filerobot.com/cms/pages+app/APP/LANDING_APP_01-FR-fr.png?vh=adc648", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://cdn-uploads.gameblog.fr/img/good_deal/416764_63eb83659475f.jpg?ver=1", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://pic.clubic.com/v1/images/1968105/raw?fit=max&width=1200&hash=4d7b6cbfcbc89369872fe88939871058c8c71f8f", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        return view;
    }
}