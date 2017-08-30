package com.example.ahmed.whatsapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ahmed.whatsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 22/08/17.
 */

public class FragmentChats extends Fragment {

    Context context = null;
//    @BindView(R.id.srlExistingChats)
//    SwipeRefreshLayout srlRefreshChatsListView;// = null gives an error
//    @BindView(R.id.lvExistingChats)
//    ListView lvExistingChats;
    View viewRoot = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewRoot = inflater.inflate(R.layout.frag_chats, container, false);
        //init();
        return viewRoot;

    }

    private void init() {
//        viewRoot = getView();
        context = getActivity();
        ButterKnife.bind(this, viewRoot);

        //this is classic way as leads to non flexible/clean code
//        srlRefreshChatsListView = viewRoot.findViewById(R.id.srlExistingChats);
//        lvExistingChats =


    }
}
