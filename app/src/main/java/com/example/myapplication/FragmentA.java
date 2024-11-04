package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FragmentA extends Fragment {

    private View viewSection1;
    private View viewSection2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewSection1 = view.findViewById(R.id.view_section_1);
        viewSection2 = view.findViewById(R.id.view_section_2);





        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSection2();
            }
        });

        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSection1();
            }
        });
    }


    private void navigateToSection2() {

        Animation fadeOut = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out);
        Animation slideIn = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in);


        viewSection1.startAnimation(fadeOut);
        viewSection1.setVisibility(View.GONE); ///first animation then visibility

        viewSection2.setVisibility(View.VISIBLE);  ///first visibility then animation
        viewSection2.startAnimation(slideIn);
    }

    private void navigateToSection1() {

        Animation slideOut = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out);
        Animation fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);

        viewSection2.startAnimation(slideOut);
        viewSection2.setVisibility(View.GONE);


        viewSection1.setVisibility(View.VISIBLE);
        viewSection1.startAnimation(fadeIn);
    }
}
