package com.example.kptest.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.kptest.CreateAppointmentActivity;
import com.example.kptest.MainActivity;
import com.example.kptest.databinding.FragmentAppointmentBinding;

public class DashboardFragment extends Fragment {

    private FragmentAppointmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAppointmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button initialAppointmentButton = binding.initialAppointmentButton;
        initialAppointmentButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), CreateAppointmentActivity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}