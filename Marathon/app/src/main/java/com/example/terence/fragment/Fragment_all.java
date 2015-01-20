package com.example.terence.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.terence.marathon.MainActivity;
import com.example.terence.marathon.R;
import com.example.terence.photo.PhotoWallAdapter;
import com.example.terence.photo.photo_url;


public class Fragment_all extends Fragment {

    private GridView mPhotoWall;
    private PhotoWallAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context;
        context = getActivity().getApplicationContext();

        View rootView = inflater.inflate(R.layout.fragment_all, container, false);

        /*
        TextView tv_tabName = (TextView) rootView.findViewById(R.id.tv_tabName);
        Bundle bundle = getArguments();
        tv_tabName.setText(bundle.getString(MainActivity.ARGUMENTS_NAME, ""));
        */

        mPhotoWall = (GridView)rootView.findViewById(R.id.photo_wall);
        adapter = new PhotoWallAdapter(context, 0, photo_url.imageThumbUrls, mPhotoWall);
        mPhotoWall.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

}
