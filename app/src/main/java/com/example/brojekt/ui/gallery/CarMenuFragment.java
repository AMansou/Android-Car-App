package com.example.brojekt.ui.gallery;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.brojekt.CarFrag;
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.Messagebox;
import com.example.brojekt.R;
import com.example.brojekt.SignUp_1172631_1171821;


import java.util.ArrayList;

import static com.example.brojekt.Login_1172631_1171821.customer;

public class CarMenuFragment extends Fragment {

    TextView cText;
    private FragmentActivity myContext;
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_carsmenu,container,false);
        LinearLayout lol=(LinearLayout) root.findViewById(R.id.lol);
        final Button addButton =new Button(container.getContext());
        DataBaseHelper dataBaseHelper =new DataBaseHelper(container.getContext(),"CARS",null,1);
        Cursor c=dataBaseHelper.getAllCars();
        /****************************************************************/
        Messagebox f=new Messagebox();
        f.show("bye",customer.getCars(),container.getContext());


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
                    PopupMenu popupMenu = new PopupMenu(container.getContext(), v);
                    MenuInflater menuInflater = getActivity().getMenuInflater();
                    menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
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
        c=dataBaseHelper.getAllCars();
        i=0;
        while (c.moveToNext()){
            String message="Model: "+c.getString(1)+" Make: "+c.getString(0)+" Year: "+c.getString(2)+" Price: "+c.getString(3)+"$Distance in Km: "+c.getString(4)+" Accidents: "+ c.getString(5)+" Offers: "+c.getString(6)+"\n";
            final String finalMessage=message;
            buttons.get(i).setOnLongClickListener(new View.OnLongClickListener(){
                Messagebox m=new Messagebox();
                @Override
                public boolean onLongClick(View view) {
                    final PopupMenu popupMenu = new PopupMenu(container.getContext(), view);
                    MenuInflater menuInflater = getActivity().getMenuInflater();
                    menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item){

                            int id = item.getItemId();
                            if(id == R.id.reserve) {

                                m.show("hello",finalMessage, container.getContext());

                                return true;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();


                    return false;
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
