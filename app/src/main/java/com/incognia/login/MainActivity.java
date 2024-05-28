package com.incognia.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.widget.Toast;

import com.google.common.hash.Hashing;
import com.incognia.IncogniaTrial;
import com.incognia.TransactionAddress;
import com.incognia.login.database.Database;
import com.sirvar.robin.RobinActivity;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class MainActivity extends RobinActivity {
    private AppCompatActivity thisActivity = this;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoginTitle("Sign in to Incognia");
        setSignupTitle("Welcome to Incognia");
        setForgotPasswordTitle("Forgot Password");
        setImage(getResources().getDrawable(R.mipmap.ic_launcher));
        disableSocialLogin();
        requestPermission();
        String loggedUser = Database.getLoggedUser(getApplicationContext());
        if(!loggedUser.isEmpty()){
            Intent goToNextActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(goToNextActivity);
        }
    }

    @Override
    protected void onLogin(final String email, final String password) {
        if(Database.login(getApplicationContext(), email, password)){
            Intent goToNextActivity = new Intent(getApplicationContext(), HomeActivity.class);

            startActivity(goToNextActivity);
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSignup(final String name, final String email, final String password) {
        if(validCredentials(email, password))
        {
            if(Database.userExists(getApplicationContext(),email)){
                Toast.makeText(getApplicationContext(), "This email is already in use", Toast.LENGTH_LONG).show();
            }
            else{
                Database.addUser(getApplicationContext(), email, password);
                Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_LONG).show();
                String myId = email;
                String signUpId = Hashing.sha256()
                        .hashString(myId, StandardCharsets.UTF_8)
                        .toString();
                // Creating an Android Address
                Address address = new Address(new Locale("pt-br"));
                address.setCountryName("Brazil"); //E.g: United States
                address.setCountryCode("BR"); //E.g: US
                address.setAdminArea("Rio Grande do Sul"); //E.g: New York (REQUIRED)
                address.setSubAdminArea("RS"); //E.g: New York
                address.setLocality("Porto Alegre"); //E.g: New York City (REQUIRED)
                address.setSubLocality("Petropolis"); // E.g: Manhattan
                address.setThoroughfare("Dona Oti"); //E.g: Fifth Avenue (REQUIRED)
                address.setSubThoroughfare("225");//E.g: 350 (REQUIRED)
                address.setPostalCode("90680060"); //E.g: 10118 (REQUIRED)

// Creating a Transaction Address
                TransactionAddress transactionAddress1 = TransactionAddress.homeAddress(address);
                TransactionAddress transactionAddress2 = TransactionAddress.billingAddress(address);
                TransactionAddress transactionAddress3 = TransactionAddress.shippingAddress(address);
                IncogniaTrial.trackSignupSent(signUpId, address);
                startLoginFragment();
            }
        }
    }

    @Override
    protected void onForgotPassword(final String email) {
        Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED YET.", Toast.LENGTH_LONG).show();
        startLoginFragment();
    }

    @Override
    protected void onGoogleLogin() {

    }

    @Override
    protected void onFacebookLogin() {

    }

    public void requestPermission(){
        if (ContextCompat.checkSelfPermission(thisActivity,
                                              Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                                                                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(thisActivity,
                                                  new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                                  MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }
    }

    public boolean validCredentials(String email, String password){
        if(email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "You must insert a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.isEmpty()){
            Toast.makeText(getApplicationContext(), "You must insert a valid password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}