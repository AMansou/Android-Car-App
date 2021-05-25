package com.example.brojekt.ui.profile;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.brojekt.DataBaseHelper;
import com.example.brojekt.Messagebox;
import com.example.brojekt.R;
import com.example.brojekt.SignUp_1172631_1171821;

import static com.example.brojekt.Login_1172631_1171821.customer;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_profile,container, false);
        /*********************************Defining text a views*******************************************************/
        final TextView textEmail=(TextView) root.findViewById(R.id.textEmail);
        final TextView textFname=(TextView) root.findViewById(R.id.textFname);
        final TextView textLname=(TextView) root.findViewById(R.id.textLname);
        final TextView textPass=(TextView) root.findViewById(R.id.textPass);
        final TextView textPhone=(TextView) root.findViewById(R.id.textPhone);
        final TextView textGender=(TextView) root.findViewById(R.id.textGender);
        final TextView textCountry=(TextView) root.findViewById(R.id.textCountry);
        final TextView textCity=(TextView) root.findViewById(R.id.textCity);
        /*****************************Defining the buttons**********************************************************/
        final Button changeEmail=(Button) root.findViewById(R.id.changeEmail);
        final Button changeFname=(Button) root.findViewById(R.id.changeFname);
        final Button changeLname=(Button) root.findViewById(R.id.changeLname);
        final Button changePass=(Button) root.findViewById(R.id.changePass);
        final Button changePhone=(Button) root.findViewById(R.id.changePhone);
        final Button changeGender=(Button) root.findViewById(R.id.changeGender);
        final Button changeCountry=(Button) root.findViewById(R.id.changeCountry);
        final Button changeCity=(Button) root.findViewById(R.id.changeCity);
        /*****************************Defining layouts **********************************************************/
        final LinearLayout lEmail=(LinearLayout) root.findViewById(R.id.lEmail);
        final LinearLayout lFname=(LinearLayout) root.findViewById(R.id.lFname);
        final LinearLayout lLname=(LinearLayout) root.findViewById(R.id.lLname);
        final LinearLayout vPass=(LinearLayout) root.findViewById(R.id.vPass);
        final LinearLayout hPass=(LinearLayout) root.findViewById(R.id.hPass);
        final LinearLayout lPhone=(LinearLayout) root.findViewById(R.id.lPhone);
        final LinearLayout lGender=(LinearLayout) root.findViewById(R.id.lGender);
        final LinearLayout lCountry=(LinearLayout) root.findViewById(R.id.lCountry);
        final LinearLayout lCity=(LinearLayout) root.findViewById(R.id.lCity);
        /**********************Displaying customer info********************************************************/
        textEmail.setText(customer.getEmail());
        textFname.setText(customer.getFirstName());
        textLname.setText(customer.getLastName());
        textPass.setText(customer.getPassword());
        textPhone.setText(customer.getPhone());
        textGender.setText(customer.getGender());
        textCountry.setText(customer.getCountry());
        textCity.setText(customer.getCity());
        /*****************************New Edit Texts**************************************************************/
        final EditText newEmail=new EditText(container.getContext());
        newEmail.setHint("Please enter new Email");
        /**************************************New Buttons******************************************************/
        final Button saveEmail=new Button(container.getContext());
        saveEmail.setText("Save Email");
        /************************************************Program*********************************************/
        changeEmail.setOnClickListener(new View.OnClickListener() {
            Messagebox m=new Messagebox();
            @Override
            public void onClick(View view) {
                if (lEmail.getChildCount()==0)
                {
                    lEmail.addView(newEmail);
                    lEmail.addView(saveEmail);
                }
                else
                    lEmail.removeAllViews();
            }
        });
        final String email=customer.getEmail();
        saveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
                Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
                while (allCustomersCursor.moveToNext()){

                    if(newEmail.getText().toString().equals(allCustomersCursor.getString(0))) {
                        Toast.makeText(container.getContext(), " E-mail Address taken",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                //allCustomersCursor.moveToFirst();
                //fname.setText(allCustomersCursor.getString(0)+"\n");
                if(newEmail.getText().toString().isEmpty()) {
                    Toast.makeText(container.getContext(), "Invalid E-mail Address",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    customer.setEmail(newEmail.getText().toString());
                    dataBaseHelper.updateCustomer(email,customer);
                    textEmail.setText(customer.getEmail());
                    Toast.makeText(container.getContext(), "Email Changed successfully",Toast.LENGTH_SHORT).show();
                    lEmail.removeAllViews();
                }

            }
        });


        return root;
    }
}
