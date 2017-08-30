package com.example.ahmed.whatsapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ahmed.whatsapp.R;

/**
 * Created by ahmed on 22/08/17.
 */

public class FragmentContactsGroups extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "Groups", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.frag_contacts_groups, container, false);

    }
}
