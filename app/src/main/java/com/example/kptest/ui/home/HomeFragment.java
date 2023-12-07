package com.example.kptest.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.kptest.AnalysisListActivity;
import com.example.kptest.AnalysisReferralListActivity;
import com.example.kptest.DoctorReferralListActivity;
import com.example.kptest.ExaminationListActivity;
import com.example.kptest.ExaminationReferralListActivity;
import com.example.kptest.InspectionListActivity;
import com.example.kptest.LoginRegisterActivity;
import com.example.kptest.MainActivity;
import com.example.kptest.PatientCardActivity;
import com.example.kptest.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        TextView userEmailTextView = binding.userEmail;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TOKEN", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");
        userEmailTextView.setText(userEmail);

        Button logoutButton = binding.logoutButton;
        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("JWT");
            editor.remove("email");
            editor.apply();
            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        Button patientCardButton = binding.patientCardButton;
        patientCardButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), PatientCardActivity.class);
            startActivity(intent);
        });

        Button patientInspectionButton = binding.patientInspectionButton;
        patientInspectionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), InspectionListActivity.class);
            startActivity(intent);
        });

        Button patientExaminationButton = binding.patientExaminationButton;
        patientExaminationButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ExaminationListActivity.class);
            startActivity(intent);
        });
        Button patientAnalysisButton = binding.patientAnalysisButton;
        patientAnalysisButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), AnalysisListActivity.class);
            startActivity(intent);
        });
        Button patientDoctorReferralButton = binding.patientDoctorReferralsButton;
        patientDoctorReferralButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), DoctorReferralListActivity.class);
            startActivity(intent);
        });
        Button patientAnalysisReferralsButton = binding.patientAnalysisReferralsButton;
        patientAnalysisReferralsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), AnalysisReferralListActivity.class);
            startActivity(intent);
        });
        Button patientExaminationReferralsButton = binding.patientExaminationReferralsButton;
        patientExaminationReferralsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), ExaminationReferralListActivity.class);
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