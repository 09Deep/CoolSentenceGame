package com.deeppatel.coolsentencegame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.deeppatel.coolsentencegame.databinding.FragmentLevelsecondBinding;
import com.deeppatel.coolsentencegame.databinding.FragmentSecondBinding;

public class LevelSecondFragment extends Fragment {

    private FragmentLevelsecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLevelsecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
