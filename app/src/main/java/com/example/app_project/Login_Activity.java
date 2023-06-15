package com.example.app_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_project.Model.ModelUser;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {
    TextView tvsignup;
    EditText tv_email, tv_pass;
    Button login;
    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing id.
    public static final String IDUSER_KEY = "iduser_key";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    public static SharedPreferences sharedpreferences;
    String username, email_us, password;
    String img_user;
    int id_user;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        pd = new ProgressDialog(this);


        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

//        email_us = sharedpreferences.getString("EMAIL_KEY", null);
//        password = sharedpreferences.getString("PASSWORD_KEY", null);

        tv_email = findViewById(R.id.Email);
        tv_pass = findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);
        tvsignup = findViewById(R.id.signup);
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
             //   finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_user = tv_email.getText().toString().trim();
               String password_user = tv_pass.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(email_user).matches()){
                    Toast.makeText(Login_Activity.this, "Empty Email...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email_user)){
                    Toast.makeText(Login_Activity.this, "Enter email...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password_user)){
                    Toast.makeText(Login_Activity.this, "Enter Password...", Toast.LENGTH_SHORT).show();
                    return;
                }
                pd.show();
                pd.setMessage("Logining.....");
                RetrofitInterface dataService = APIService.getService();
                Call<ModelUser> callback = dataService.LoginUser(email_user, password_user);
                callback.enqueue(new Callback<ModelUser>() {
                    @Override
                    public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                        if(response.code() == 404 ){
                            pd.dismiss();
                            Toast.makeText(Login_Activity.this, "Login Faill " , Toast.LENGTH_SHORT).show();

                        }else if(response.code() == 200 ){
//                            ModelUser dataUserToLogin = response.body();
                            for(ModelUser item:response.body().getModelUser()){
                                id_user = item.getId();
//                                username = item.getName();
//                                img_user = item.getImage_user();
                            }

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt(IDUSER_KEY, id_user);
                            editor.putString(EMAIL_KEY, email_user);
                            editor.putString(PASSWORD_KEY, password_user);
                            // to save our data with key and value.
                            editor.commit();
                            pd.dismiss();
                            Toast.makeText(Login_Activity.this, "Login Success ", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Login_Activity.this, AppLayout.class);
//                            intent.putExtra("username", username);
//                            intent.putExtra("id_user", id_user);
//                            intent.putExtra("img_user", img_user);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelUser> call, Throwable t) {
                        Toast.makeText(Login_Activity.this, "Rest api faill"+t, Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });

            }
        });

    }


}