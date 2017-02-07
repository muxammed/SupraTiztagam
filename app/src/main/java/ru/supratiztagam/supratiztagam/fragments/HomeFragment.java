package ru.supratiztagam.supratiztagam.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

//import jp.wasabeef.blurry.Blurry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Response;
import ru.supratiztagam.supratiztagam.R;
import ru.supratiztagam.supratiztagam.activities.MainActivity;
import ru.supratiztagam.supratiztagam.models.Image;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Handler mHandler;

    private OnFragmentInteractionListener mListener;


    private ArrayList<Image> images;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView lblCount, lblTitle, lblDate;
    private int selectedPosition = 0;
    private Button corbalar, pidemide, donermoner, pizzalar, others, drinks;

    private static final String endpoint = "http://api.androidhive.info/json/glide.json";




    public HomeFragment() {
        // Required empty public constructor
    }

    private void fetchImages() {

        images.clear();

        Image image = new Image();
        image.setName("Birinji");

        image.setSmall("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya2.jpg");
        image.setMedium("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya2.jpg");
        image.setLarge("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya2.jpg");
        image.setTimestamp("19/01/2017");

        images.add(image);

        image = new Image();
        image.setName("Ikinji");

        image.setSmall("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya2.jpg");
        image.setMedium("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya2.jpg");
        image.setLarge("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya2.jpg");
        image.setTimestamp("19/01/2017");

        images.add(image);

        image = new Image();
        image.setName("Ucunji");

        image.setSmall("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya3.jpg");
        image.setMedium("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya3.jpg");
        image.setLarge("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya3.jpg");
        image.setTimestamp("19/01/2017");

        images.add(image);

        image = new Image();
        image.setName("Dordunji");

        image.setSmall("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya4.jpg");
        image.setMedium("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya4.jpg");
        image.setLarge("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya4.jpg");
        image.setTimestamp("19/01/2017");

        images.add(image);

        image = new Image();
        image.setName("Bashinji");

        image.setSmall("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya5.jpg");
        image.setMedium("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya5.jpg");
        image.setLarge("http://supratiztagam.ru/supra_connect/aksiya_picz/aksiya5.jpg");
        image.setTimestamp("19/01/2017");

        images.add(image);


    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mHandler = new Handler();

        corbalar = (Button) v.findViewById(R.id.corbalar);
        corbalar.setOnClickListener(this);

        pidemide = (Button) v.findViewById(R.id.pidemide);
        pidemide.setOnClickListener(this);

        donermoner = (Button) v.findViewById(R.id.donermoner);
        donermoner.setOnClickListener(this);

        pizzalar = (Button) v.findViewById(R.id.pizzalar);
        pizzalar.setOnClickListener(this);

        others = (Button) v.findViewById(R.id.others);
        others.setOnClickListener(this);

        drinks = (Button) v.findViewById(R.id.drinks);
        drinks.setOnClickListener(this);







        images = new ArrayList<>();
        selectedPosition = 0;

        fetchImages();

        Log.e(TAG, "position: " + selectedPosition);
        Log.e(TAG, "images size: " + images.size());


        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        lblCount = (TextView) v.findViewById(R.id.lbl_count);
        lblDate = (TextView) v.findViewById(R.id.date);
        lblTitle = (TextView) v.findViewById(R.id.title);

        Log.e(TAG, "viewpager height*width size: " + viewPager.getHeight() + "x" + viewPager.getWidth());


        lblCount.setBackgroundResource(R.drawable.rounded_corner);
        //lblCount.setTextColor(R.color.colorPrimary);
        lblCount.setPadding(10,0,10,0);

        lblTitle.setBackgroundResource(R.drawable.gapdal_corners);
        lblTitle.setPadding(20,0,15,0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        setCurrentItem(selectedPosition);

        return v;
    }


    private void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
        displayMetaInfo(selectedPosition);
    }

    //	page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void displayMetaInfo(int position) {
        lblCount.setText((position + 1) + " / " + images.size());

        Image image = images.get(position);
        lblTitle.setText(image.getName());
        lblDate.setText(image.getTimestamp());

        Log.e(TAG, "viewpager height*width size: " + viewPager.getHeight() + "x" + viewPager.getWidth());

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        Runnable mPendingRunnable;

        switch (view.getId())
        {
            case R.id.corbalar:
                ((MainActivity) getActivity()).selectedTab = 0;
                ((MainActivity) getActivity()).shouldLoadHomeFragOnBackPress = true;

                mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // update the main content by replacing fragments
                        OrderCreationFragment fragment = new OrderCreationFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        //getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commitAllowingStateLoss();


                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler.post(mPendingRunnable);
                }

                break;
            case R.id.pidemide:

                ((MainActivity) getActivity()).selectedTab = 1;

                mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // update the main content by replacing fragments
                        OrderCreationFragment fragment = new OrderCreationFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        //getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler.post(mPendingRunnable);
                }

                break;
            case R.id.donermoner:

                ((MainActivity) getActivity()).selectedTab = 2;

                mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // update the main content by replacing fragments
                        OrderCreationFragment fragment = new OrderCreationFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        //getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler.post(mPendingRunnable);
                }
                break;
            case R.id.pizzalar:

                ((MainActivity) getActivity()).selectedTab = 3;

                mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // update the main content by replacing fragments
                        OrderCreationFragment fragment = new OrderCreationFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        //getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.push_up_in,
                                R.anim.push_up_out);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler.post(mPendingRunnable);
                }
                break;
            case R.id.others:

                ((MainActivity) getActivity()).selectedTab = 4;

                mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // update the main content by replacing fragments
                        OrderCreationFragment fragment = new OrderCreationFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        //getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.push_up_in,
                                R.anim.push_up_out);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler.post(mPendingRunnable);
                }
                break;

            case R.id.drinks:

                ((MainActivity) getActivity()).selectedTab = 5;

                mPendingRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // update the main content by replacing fragments
                        OrderCreationFragment fragment = new OrderCreationFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        //getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.push_up_in,
                                R.anim.push_up_out);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commitAllowingStateLoss();
                    }
                };

                // If mPendingRunnable is not null, then add to the message queue
                if (mPendingRunnable != null) {
                    mHandler.post(mPendingRunnable);
                }
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    //	adapter
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

            ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);

            Image image = images.get(position);

            Glide.with(getActivity()).load(image.getMedium())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewPreview);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
