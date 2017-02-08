package ru.supratiztagam.supratiztagam.fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ru.supratiztagam.supratiztagam.DatabaseHandler;
import ru.supratiztagam.supratiztagam.R;
import ru.supratiztagam.supratiztagam.activities.MainActivity;

public class MFragment extends Fragment
{
    private Context mContext;
    private Button artyBir;
    TextView textView;
    DatabaseHandler db;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);


        ((MainActivity) getActivity()).navItemIndex = -1;




        artyBir = (Button) v.findViewById(R.id.menubut);
        textView = (TextView) v.findViewById(R.id.menutxt);

        //artyBir = new Button(mContext);
        artyBir.setText("artty Bir");
        artyBir.setTextSize(30);
        artyBir.setGravity(Gravity.CENTER);
        artyBir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ssany = ((MainActivity) getActivity()).kcount.getText().toString();
                System.out.println("sanyyyyyy - " + ssany);
                int sany = Integer.valueOf(ssany);
                if (sany == 0)
                {
                    ((MainActivity) getActivity()).kcount.setVisibility(View.VISIBLE);
                    ((MainActivity) getActivity()).kcount.setText("1");
                }
                else
                {
                    sany = sany + 1;
                    ((MainActivity) getActivity()).kcount.setVisibility(View.VISIBLE);
                    ((MainActivity) getActivity()).kcount.setText(String.valueOf(sany));
                }
            }
        });


        String content = getArguments().getString("content");
        //textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setText("Gör hany\n\n" +content);

        ((MainActivity) getActivity()).toolbartitle.setText("Saylanan " + content);

        db = new DatabaseHandler(getActivity().getApplicationContext());



        Toast.makeText(getActivity().getApplicationContext(), "menu sany - " + db.getMenuCount(), Toast.LENGTH_LONG).show();

        Cursor mmenu = db.getMenuByGrpid(Integer.valueOf(content));

        Toast.makeText(getActivity().getApplicationContext(), content + " grp ozu we  sany - " + mmenu.getCount(), Toast.LENGTH_LONG).show();



        //textView.setBackgroundColor(0xFFececec);
        return v;
    }
}
