package com.example.test2.ui.chatting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test2.databinding.FragmentChattingBinding;
import com.example.test2.databinding.FragmentChattingBinding;

public class ChattingFragment extends Fragment {

    private FragmentChattingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChattingViewModel chattingViewModel =
                new ViewModelProvider(this).get(ChattingViewModel.class);

        binding = FragmentChattingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textChatting;
        chattingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}