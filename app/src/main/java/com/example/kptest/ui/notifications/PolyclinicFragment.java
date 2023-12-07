package com.example.kptest.ui.notifications;

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

//import com.example.kptest.databinding.FragmentNotificationsBinding;
import com.example.kptest.DoctorListActivity;
import com.example.kptest.databinding.FragmentPolyclinicBinding;

public class PolyclinicFragment extends Fragment {

//    private FragmentNotificationsBinding binding;
    private FragmentPolyclinicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PolyclinicViewModel polyclinicViewModel =
                new ViewModelProvider(this).get(PolyclinicViewModel.class);

        binding = FragmentPolyclinicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        polyclinicViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final Button doctorListButton = binding.doctorListButton;
        doctorListButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DoctorListActivity.class);
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