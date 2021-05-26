package com.example.brojekt.ui.favorites;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.brojekt.CarFrag;
import com.example.brojekt.Customer;
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.Messagebox;
import com.example.brojekt.R;
import com.example.brojekt.SignUp_1172631_1171821;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.example.brojekt.Login_1172631_1171821.customer;

public class FavoritesFragment extends Fragment {

    TextView cText;
    private FragmentActivity myContext;
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root=inflater.inflate(R.layout.fragment_favorites,container,false);
        final LinearLayout lol=(LinearLayout) root.findViewById(R.id.lol);
        final Button addButton =new Button(container.getContext());
        //lol.addView(addButton);
        final DataBaseHelper dataBaseHelper =new DataBaseHelper(container.getContext(),"CARS",null,1);
        Cursor c=dataBaseHelper.getAllCars();
        /****************************************************************/
        if (customer.getFavorites().isEmpty())
            return root;
        final String[] splitString = customer.getFavorites().split("%");
        ScrollView scroll = new ScrollView(container.getContext());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());

        final Button[] button = new Button[1];
        final ArrayList<Button>[] buttons = new ArrayList[]{new ArrayList<Button>()};
        linearLayout=new LinearLayout(container.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //String[] splitString = Arrays.copyOfRange(split, 1, split.length);
        int i = 0;
        for(i=0;i<splitString.length;i++){

            button[0] =new Button(container.getContext());
            buttons[0].add(button[0]);
            buttons[0].get(i).setText(splitString[i].split("#")[0]+" "+splitString[i].split("#")[1]);//String.valueOf(i));
            buttons[0].get(i).setTag(String.valueOf(i));
            linearLayout.addView(buttons[0].get(i));
            buttons[0].get(i).setLayoutParams(new LinearLayout.LayoutParams(width,height));

        }
        //if(i%4!=0)
        final TextView t=new TextView(container.getContext());
        scroll.addView(linearLayout);
            lol.addView(scroll);
       // Messagebox f=new Messagebox();
        //f.show("bye", String.valueOf(splitString.length),container.getContext());

        final FragmentManager fragmentManager = myContext.getSupportFragmentManager();
        String str;
        final CarFrag firstFragment = new CarFrag();
        c=dataBaseHelper.getAllCars();
        //i=0;
        for (i=0;i<splitString.length;i++ ) {
            str="Make: "+splitString[i].split("#")[0]+"\nModel: " +
                    ""+splitString[i].split("#")[1]+"\nYear: "+splitString[i].split("#")[2]+"\nPrice: "+splitString[i].split("#")[3]+"$\nDistance in Km: "+splitString[i].split("#")[4]+"\nAccidents: "+ splitString[i].split("#")[5]+"\nOffers:"+splitString[i].split("#")[6]+"\n";
            //str=splitString[i].split("#")[1];
            final String finalStr = str;
            root.findViewWithTag(String.valueOf(i)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //PopupMenu popupMenu = new PopupMenu(container.getContext(), v);
                    //MenuInflater menuInflater = getActivity().getMenuInflater();
                    //menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                   /* FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    if (!firstFragment.isAdded()) {
                        firstFragment.setText(finalStr);
                        fragmentTransaction.add(R.id.drawer_layout, firstFragment, "CarFrag");
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        fragmentTransaction.remove( firstFragment);
                        fragmentTransaction.commit();
                    }*/
                    if(lol.getChildCount()>1) {
                        lol.removeView(t);
                        t.setText(finalStr);
                        lol.addView(t);
                    }
                    else{
                        t.setText(finalStr);
                        lol.addView(t);
                    }
                }
            });
        }

        c=dataBaseHelper.getAllCars();
        i=0;
        for (i=0;i<splitString.length;i++ ){
            String message="Model: "+splitString[i].split("#")[0]+" Make: " +
                                ""+splitString[i].split("#")[1]+" Year: "+splitString[i].split("#")[2]+" Price: "+splitString[i].split("#")[3]+"$ Distance in Km: "+splitString[i].split("#")[4]+" Accidents: "+ splitString[i].split("#")[5]+" Offers: "+splitString[i].split("#")[6]+"\n";
            //String message=splitString[i].split("#")[1];
            final String finalMessage=message;
            String message2=splitString[i].split("#")[0]+"#"+splitString[i].split("#")[1]+"#"+splitString[i].split("#")[2]+"#"+splitString[i].split("#")[3]+"#"+splitString[i].split("#")[4]+"#"+ splitString[i].split("#")[5]+"#"+splitString[i].split("#")[6]+"%";
            //String message2="#"+splitString[i].split("#")[1];
            final String finalMessage2=message2;
            buttons[0].get(i).setOnLongClickListener(new View.OnLongClickListener(){
                Messagebox m=new Messagebox();
                @Override
                public boolean onLongClick(View view) {
                    final PopupMenu popupMenu = new PopupMenu(container.getContext(), view);
                    MenuInflater menuInflater = getActivity().getMenuInflater();
                    menuInflater.inflate(R.menu.popup2, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item){

                            int id = item.getItemId();
                            if(id == R.id.reserve2) {
                                if (!customer.getCars().contains(finalMessage))
                                    m.show("hello",finalMessage, container.getContext());
                                else
                                    m.show2("hello",finalMessage,container.getContext());

                                return true;
                            }

                            return true;
                        }
                    });
                    popupMenu.show();


                    return false;
                }
            });


        }

        SearchView search=(SearchView) root.findViewById(R.id.search);
        //c=dataBaseHelper.getAllCars();
        final Cursor finalC = c;
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //Toast.makeText(container.getContext(), query,Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(container.getContext(), newText,Toast.LENGTH_LONG).show();
                //lol.removeAllViews();
                //buttons[0].clear();
                ///linearLayout=new LinearLayout(container.getContext());
                int i = 0;
                finalC.moveToFirst();
                RadioButton name= (RadioButton) root.findViewById(R.id.name);
                RadioButton model= (RadioButton) root.findViewById(R.id.model);
                RadioButton price= (RadioButton) root.findViewById(R.id.price);
                for(i=0;i<splitString.length;i++) {
                    if(price.isChecked())
                    {
                        String s= splitString[i].split("#")[1];
                        if (!splitString[i].split("#")[3].equals(newText) && buttons[0].get(i).getVisibility()==View.VISIBLE)
                            buttons[0].get(i).setVisibility(View.INVISIBLE);
                        else if(splitString[i].split("#")[3].equals(newText) && buttons[0].get(i).getVisibility()==View.INVISIBLE)
                            buttons[0].get(i).setVisibility(View.VISIBLE);
                    }
                    else if(model.isChecked())
                    {
                        if (!splitString[i].split("#")[1].toLowerCase().contains(newText.toLowerCase()) && buttons[0].get(i).getVisibility()==View.VISIBLE)
                            buttons[0].get(i).setVisibility(View.INVISIBLE);
                        else if(splitString[i].split("#")[1].toLowerCase().contains(newText.toLowerCase()) && buttons[0].get(i).getVisibility()==View.INVISIBLE)
                            buttons[0].get(i).setVisibility(View.VISIBLE);
                    }
                    else if(name.isChecked())
                    {
                        if (!(splitString[i].split("#")[0]+" "+splitString[i].split("#")[1]).toLowerCase().contains(newText.toLowerCase()) && buttons[0].get(i).getVisibility()==View.VISIBLE)
                            buttons[0].get(i).setVisibility(View.INVISIBLE);
                        else if((splitString[i].split("#")[0]+" "+splitString[i].split("#")[1]).toLowerCase().contains(newText.toLowerCase()) && buttons[0].get(i).getVisibility()==View.INVISIBLE)
                            buttons[0].get(i).setVisibility(View.VISIBLE);
                    }



                }
                return false;
            }
        });
        return root;

    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


}
