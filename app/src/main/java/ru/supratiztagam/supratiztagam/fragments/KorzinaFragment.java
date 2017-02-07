package ru.supratiztagam.supratiztagam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ru.supratiztagam.supratiztagam.R;

/**
 * Created by muxammed on 20.01.2017.
 */
public class KorzinaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_korzina, container, false);

        return v;
    }
}
