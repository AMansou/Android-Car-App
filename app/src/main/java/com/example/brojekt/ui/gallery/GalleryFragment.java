package com.example.brojekt.ui.gallery;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.brojekt.Car;
import com.example.brojekt.CarFrag;
import com.example.brojekt.CustomerHome_1172631_1171821;
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.R;
import com.example.brojekt.SignUp_1172631_1171821;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    TextView cText;
    private FragmentActivity myContext;
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_gallery,container,false);
        LinearLayout lol=(LinearLayout) root.findViewById(R.id.lol);
        final Button addButton =new Button(container.getContext());
        DataBaseHelper dataBaseHelper =new DataBaseHelper(container.getContext(),"CARS",null,1);
        Cursor c=dataBaseHelper.getAllCars();

        Button  button;
        ArrayList<Button> buttons = new ArrayList<Button>();
        linearLayout=new LinearLayout(container.getContext());
        int i = 0;
        while(c.moveToNext()){

            button=new Button(container.getContext());
            buttons.add(button);
            buttons.get(i).setText(c.getString(1));//String.valueOf(i));
            if(i%4==0){
                lol.addView(linearLayout);
                linearLayout=new LinearLayout(container.getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            }
            linearLayout.addView(buttons.get(i));
            i++;


        }
        if(i%4!=0)
            lol.addView(linearLayout);
        final FragmentManager fragmentManager = myContext.getSupportFragmentManager();
        String str;
        final CarFrag firstFragment = new CarFrag();
        c=dataBaseHelper.getAllCars();
        i=0;
        while ( c.moveToNext()) {
            str="Model: "+c.getString(1)+"\nMake: "+c.getString(0)+"\nYear: "+c.getString(2)+"\nPrice: "+c.getString(3)+"$\nDistance in Km: "+c.getString(4)+"\n"+ c.getString(5)+"\n"+c.getString(6)+"\n";
            final String finalStr = str;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    if (!firstFragment.isAdded()) {
                        firstFragment.setText(finalStr);
                        fragmentTransaction.add(R.id.drawer_layout, firstFragment, "CarFrag");
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        fragmentTransaction.remove( firstFragment);
                        fragmentTransaction.commit();
                    }
                }
            });
            i++;
        }
        return root;

    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


}
