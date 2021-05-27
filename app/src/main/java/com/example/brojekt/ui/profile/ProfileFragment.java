package com.example.brojekt.ui.profile;

import android.database.Cursor;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.text.Layout;
import android.text.method.PasswordTransformationMethod;
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
import com.example.brojekt.Encryption;
import com.example.brojekt.Messagebox;
import com.example.brojekt.R;
import com.example.brojekt.SignUp_1172631_1171821;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);
        String decrypted = encryption.decryptOrNull(customer.getPassword());
        textPass.setText(decrypted);
        //textPass.setText(customer.getPassword());
        textPhone.setText(customer.getPhone());
        textGender.setText(customer.getGender());
        textCountry.setText(customer.getCountry());
        textCity.setText(customer.getCity());
        /*****************************New Edit Texts**************************************************************/
        final EditText newEmail=new EditText(container.getContext());
        newEmail.setHint("Please enter new Email");

        final EditText newFname=new EditText(container.getContext());
        newFname.setHint("Please Enter New First Name");

        final EditText newLname=new EditText(container.getContext());
        newLname.setHint("Please Enter New Last Name");

        final EditText newVPass=new EditText(container.getContext());
        newVPass.setHint("Please Enter New Password");
        newVPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        newVPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        final EditText newHPass=new EditText(container.getContext());
        newHPass.setHint("Please Confirm New Password");
        newHPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        newHPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        final EditText newPhone=new EditText(container.getContext());
        newPhone.setHint("Please Enter New Phone Number");
        newPhone.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_NUMBER);

        /**************************************New Buttons******************************************************/
        final Button saveEmail=new Button(container.getContext());
        saveEmail.setText("Save New Email");

        final Button saveFname=new Button(container.getContext());
        saveFname.setText("Save New First Name");

        final Button saveLname=new Button(container.getContext());
        saveLname.setText("Save New Last Name");

        final Button savePass=new Button(container.getContext());
        savePass.setText("Save New Password");

        final Button savePhone=new Button(container.getContext());
        savePhone.setText("Save New Phone Number");
        /************************************************Changing Email*********************************************/
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
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
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
                if(!newEmail.getText().toString().trim().matches(emailPattern)) {
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
        /************************changing First Name***********************************/
        changeFname.setOnClickListener(new View.OnClickListener() {
            Messagebox m=new Messagebox();
            @Override
            public void onClick(View view) {
                if (lFname.getChildCount()==0)
                {
                    lFname.addView(newFname);
                    lFname.addView(saveFname);
                }
                else
                    lFname.removeAllViews();
            }
        });
        saveFname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
                //allCustomersCursor.moveToFirst();
                //fname.setText(allCustomersCursor.getString(0)+"\n");
                if(newFname.getText().toString().length()<3) {
                    Toast.makeText(container.getContext(), "Invalid First Name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    customer.setFirstName(newFname.getText().toString());
                    dataBaseHelper.updateCustomer(customer.getEmail(),customer);
                    textFname.setText(customer.getFirstName());
                    Toast.makeText(container.getContext(), "First Name Changed successfully",Toast.LENGTH_SHORT).show();
                    lFname.removeAllViews();
                }

            }
        });
        /****************************************chaning Last Name*****************************************/
        changeLname.setOnClickListener(new View.OnClickListener() {
            Messagebox m=new Messagebox();
            @Override
            public void onClick(View view) {
                if (lLname.getChildCount()==0)
                {
                    lLname.addView(newLname);
                    lLname.addView(saveLname);
                }
                else
                    lLname.removeAllViews();
            }
        });
        saveLname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
                //allCustomersCursor.moveToFirst();
                //fname.setText(allCustomersCursor.getString(0)+"\n");
                if(newLname.getText().toString().length()<3) {
                    Toast.makeText(container.getContext(), "Invalid Last Name ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    customer.setLastName(newLname.getText().toString());
                    dataBaseHelper.updateCustomer(customer.getEmail(),customer);
                    textLname.setText(customer.getLastName());
                    Toast.makeText(container.getContext(), "Last Name Changed successfully",Toast.LENGTH_SHORT).show();
                    lLname.removeAllViews();
                }

            }
        });
        /*********************changing Password*****************************************************/
        changePass.setOnClickListener(new View.OnClickListener() {
            Messagebox m=new Messagebox();
            @Override
            public void onClick(View view) {
                if (hPass.getChildCount()==0)
                {
                    hPass.addView(newVPass);
                    hPass.addView(savePass);
                    vPass.addView(newHPass);

                }
                else
                {
                    hPass.removeAllViews();
                    vPass.removeAllViews();
                    vPass.addView(hPass);
                }
            }
        });
        savePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordRegex="^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
                Pattern p = Pattern.compile(passwordRegex);
                Matcher m = p.matcher(newVPass.getText().toString().trim());
                DataBaseHelper dataBaseHelper=new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
                //allCustomersCursor.moveToFirst();
                //fname.setText(allCustomersCursor.getString(0)+"\n");
                if(newVPass.getText().toString().trim().isEmpty()|| !m.matches()) {
                    Toast.makeText(container.getContext(), "Password must be between 8-20 characters, have one upper case and one lower case letter and one special character",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!newHPass.getText().toString().equals(newVPass.getText().toString()))
                {
                    Toast.makeText(container.getContext(), "Passwords don't match! ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);
                    String encrypted = encryption.encryptOrNull(newVPass.getText().toString());
                    customer.setPassword(encrypted);
                    dataBaseHelper.updateCustomer(customer.getEmail(),customer);
                    textPass.setText(newVPass.getText().toString());
                    Toast.makeText(container.getContext(), "Password Changed successfully",Toast.LENGTH_SHORT).show();
                    hPass.removeAllViews();
                    vPass.removeAllViews();
                    vPass.addView(hPass);
                }

            }
        });
        /******************************************Changing Phone NUmber************************************/
        changePhone.setOnClickListener(new View.OnClickListener() {
            Messagebox m=new Messagebox();
            @Override
            public void onClick(View view) {
                if (lPhone.getChildCount()==0)
                {
                    lPhone.addView(newPhone);
                    lPhone.addView(savePhone);
                }
                else
                    lPhone.removeAllViews();
            }
        });
        savePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(container.getContext(),"CUSTOMER",null,1);
                //allCustomersCursor.moveToFirst();
                //fname.setText(allCustomersCursor.getString(0)+"\n");
                if(newPhone.getText().toString().length()!=10) {
                    Toast.makeText(container.getContext(), "Invalid Phone ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                {
                    if(customer.getCountry().equals("Japan"))
                        customer.setPhone("00970"+newPhone.getText().toString());
                    else if(customer.getCountry().equals("France"))
                        customer.setPhone("00971"+newPhone.getText().toString());
                    else if(customer.getCountry().equals("Russia"))
                        customer.setPhone("00972"+newPhone.getText().toString());
                    else if(customer.getCountry().equals("Tunisia"))
                        customer.setPhone("00973"+newPhone.getText().toString());
                    dataBaseHelper.updateCustomer(customer.getEmail(),customer);
                    textPhone.setText(customer.getPhone());
                    Toast.makeText(container.getContext(), "Phone Number Changed successfully",Toast.LENGTH_SHORT).show();
                    lPhone.removeAllViews();
                }

            }
        });


        return root;
    }
}
