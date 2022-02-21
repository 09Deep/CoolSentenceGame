package com.deeppatel.coolsentencegame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.deeppatel.coolsentencegame.databinding.FragmentLevelfourBinding;

public class LevelFourFragment extends Fragment {
    private FragmentLevelfourBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLevelfourBinding.inflate(inflater, container, false);
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
