package com.example.brojekt.ui.gallery;

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
import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.Messagebox;
import com.example.brojekt.R;
import com.example.brojekt.SignUp_1172631_1171821;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.brojekt.Login_1172631_1171821.customer;

public class CarMenuFragment extends Fragment {

    TextView cText;
    private FragmentActivity myContext;
    LinearLayout linearLayout;
    ScrollView scroll;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root=inflater.inflate(R.layout.fragment_carsmenu,container,false);
        final LinearLayout lol=(LinearLayout) root.findViewById(R.id.lol);
        final Button addButton =new Button(container.getContext());
        final DataBaseHelper dataBaseHelper =new DataBaseHelper(container.getContext(),"CARS",null,1);
        Cursor c=dataBaseHelper.getAllCars();
        /****************************************************************/
        //Messagebox f=new Messagebox();
        //f.show("bye",customer.getCars(),container.getContext());


        final Button[] button = new Button[1];
        final ArrayList<Button>[] buttons = new ArrayList[]{new ArrayList<Button>()};
        linearLayout=new LinearLayout(container.getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scroll=new ScrollView(container.getContext());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        int i = 0;
        while(c.moveToNext()){

            button[0] =new Button(container.getContext());
            buttons[0].add(button[0]);
            buttons[0].get(i).setText(c.getString(1)+" "+ c.getString(0));//String.valueOf(i));
            buttons[0].get(i).setTag(String.valueOf(i));
            linearLayout.addView(buttons[0].get(i));
            buttons[0].get(i).setLayoutParams(new LinearLayout.LayoutParams(width,height));
            i++;


        }
        scroll.addView(linearLayout);
        lol.addView(scroll);
        final FragmentManager fragmentManager = myContext.getSupportFragmentManager();
        String str;
        final CarFrag firstFragment = new CarFrag();
        c=dataBaseHelper.getAllCars();
        i=0;
        while ( c.moveToNext()) {
            str="Model: "+c.getString(1)+"\nMake: "+c.getString(0)+"\nYear: "+c.getString(2)+"\nPrice: "+c.getString(3)+"$\nDistance in Km: "+c.getString(4)+"\nAccidents: "+Boolean.parseBoolean( c.getString(5))+"\nOffers: "+Boolean.parseBoolean(c.getString(6))+"\n";
            final String finalStr = str;
            root.findViewWithTag(String.valueOf(i)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //PopupMenu popupMenu = new PopupMenu(container.getContext(), v);
                    //MenuInflater menuInflater = getActivity().getMenuInflater();
                    //menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
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
            String message="Model: "+c.getString(1)+" Make: "+c.getString(0)+" Year: "+c.getString(2)+" Price: "+c.getString(3)+"$ Distance in Km: "+c.getString(4)+" Accidents: "+ c.getString(5)+" Offers: "+c.getString(6)+"\n";
            final String finalMessage=message;
            String message2=c.getString(1)+"#"+c.getString(0)+"#"+c.getString(2)+"#"+c.getString(3)+"#"+c.getString(4)+"#"+ c.getString(5)+"#"+c.getString(6)+"%";
            final String finalMessage2=message2;
            buttons[0].get(i).setOnLongClickListener(new View.OnLongClickListener(){
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
                                if (!customer.getCars().contains(finalMessage))
                                m.show("hello",finalMessage, container.getContext());
                                else
                                    m.show2("hello",finalMessage,container.getContext());

                                return true;
                            }
                            else if(id==R.id.favorite)
                            {
                                MenuItem fav=(MenuItem)root.findViewById(R.id.favorite);
                                if (!customer.getFavorites().contains(finalMessage2)){
                                    DataBaseHelper dataBaseHelper=new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
                                    customer.setFavorites(customer.getFavorites()+"\n"+finalMessage2);
                                    dataBaseHelper.updateCustomer(customer.getEmail(),customer);
                                    Toast.makeText(container.getContext(), "Car added to favorites successfully",
                                            Toast.LENGTH_SHORT).show();}
                                else{
                                    DataBaseHelper dataBaseHelper=new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
                                    customer.setFavorites(customer.getFavorites().replace( "\n"+finalMessage2,"") );
                                    //customer.setFavorites("");
                                    dataBaseHelper.updateCustomer(customer.getEmail(),customer);
                                    Toast.makeText(container.getContext(), "Car removed from favorites successfully",Toast.LENGTH_SHORT).show();
                                }

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
                do {
                    if(price.isChecked())
                    {
                        if (!finalC.getString(4).equals(newText) && buttons[0].get(i).getVisibility()==View.VISIBLE)
                            buttons[0].get(i).setVisibility(View.INVISIBLE);
                        else if(finalC.getString(4).equals(newText) && buttons[0].get(i).getVisibility()==View.INVISIBLE)
                            buttons[0].get(i).setVisibility(View.VISIBLE);
                    }
                    else if(model.isChecked())
                    {
                        if (!finalC.getString(0).toLowerCase().contains(newText.toLowerCase()) && buttons[0].get(i).getVisibility()==View.VISIBLE)
                            buttons[0].get(i).setVisibility(View.INVISIBLE);
                        else if(finalC.getString(0).toLowerCase().contains(newText.toLowerCase()) && buttons[0].get(i).getVisibility()==View.INVISIBLE)
                            buttons[0].get(i).setVisibility(View.VISIBLE);
                    }
                    else if(name.isChecked())
                    {
                        if (!(finalC.getString(1)+" "+finalC.getString(0)).toLowerCase().contains(newText.toLowerCase()) && buttons[0].get(i).getVisibility()==View.VISIBLE)
                            buttons[0].get(i).setVisibility(View.INVISIBLE);
                        else if((finalC.getString(1)+" "+finalC.getString(0)).toLowerCase().contains(newText.toLowerCase())&& buttons[0].get(i).getVisibility()==View.INVISIBLE)
                            buttons[0].get(i).setVisibility(View.VISIBLE);
                    }
                    i++;


                }while(finalC.moveToNext());
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
