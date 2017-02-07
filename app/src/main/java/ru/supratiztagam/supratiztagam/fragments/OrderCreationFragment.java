package ru.supratiztagam.supratiztagam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;
import ru.supratiztagam.supratiztagam.R;
import ru.supratiztagam.supratiztagam.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muxammed on 20.01.2017.
 */
public class OrderCreationFragment extends Fragment {


    //int[] testColors = {0xFF00796B,0xFF5B4947,0xFF607D8B,0xFFF57C00,0xFFF57C00,0xFF00796B};

    int[] testColors = {0xFF3CA0B4,0xFF7846BE,0xFF78287D,0xFFD76E4B,0xFF3CB271,0xFFD89B39};

    Controller controller;

    List<Fragment> mFragments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_create, container, false);

        initFragment();

        BottomTabTest(v);




        return v;
    }


    private void initFragment()
    {
        mFragments = new ArrayList<>();

        mFragments.add(createFragment("1"));
        mFragments.add(createFragment("2"));
        mFragments.add(createFragment("3"));
        mFragments.add(createFragment("4"));
        mFragments.add(createFragment("5"));
        mFragments.add(createFragment("6"));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
        transaction.add(R.id.frameLayout,mFragments.get(0));
        transaction.commit();
    }

    private void BottomTabTest(final View view)
    {
        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) view.findViewById(R.id.tab);

        //TabItemBuilder
        TabItemBuilder tabItemBuilder = new TabItemBuilder(getActivity()).create()
                .setDefaultIcon(android.R.drawable.ic_menu_send)
                .setText("Уля ля")
                .setSelectedColor(testColors[0])
                .setTag("A")
                .build();

        //Controller
        controller = pagerBottomTabLayout.builder()
                .addTabItem(tabItemBuilder)
                .addTabItem(android.R.drawable.ic_menu_compass, "Уля ля",testColors[1])
                .addTabItem(android.R.drawable.ic_menu_search, "Уля ля",testColors[2])
                .addTabItem(android.R.drawable.ic_menu_help, "Уля ля",testColors[3])
                .addTabItem(android.R.drawable.ic_menu_help, "Уля ля",testColors[4])
                .addTabItem(android.R.drawable.ic_menu_help, "Уля ля",testColors[5])
//                .setMode(TabLayoutMode.HIDE_TEXT)
                .setMode(TabLayoutMode.CHANGE_BACKGROUND_COLOR)
    //            .setMode(TabLayoutMode.HIDE_TEXT| TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();


        int sel = ((MainActivity) getActivity()).selectedTab;
        controller.setSelect(sel);


//        controller.setMessageNumber("A",2);
//        controller.setDisplayOval(0,true);

        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag)
            {
                Log.i("asd","onSelected:"+index+"   TAG: "+tag.toString());

//                FrameLayout frlm = (FrameLayout) view.findViewById(R.id.frameLayout);
//                frlm.setBackgroundColor(testColors[index]);


                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.push_up_in,R.anim.push_up_out);
                transaction.replace(R.id.frameLayout,mFragments.get(index));
                transaction.commit();

                FrameLayout frlm = (FrameLayout) view.findViewById(R.id.frameLayout);
                frlm.setBackgroundColor(testColors[index]);


            }

            @Override
            public void onRepeatClick(int index, Object tag) {
                Log.i("asd","onRepeatClick:"+index+"   TAG: "+tag.toString());
            }
        });
    }



    ///OnTabItemSelectListener listener =

    private Fragment createFragment(String content)
    {
        MFragment fragment = new MFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        fragment.setArguments(bundle);

        return fragment;
    }
}
