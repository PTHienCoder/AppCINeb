package com.example.app_project;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {

    TextView tvlogin,birthday;
    EditText email, username, passs, cf_pass, phone_user;
    Button signup;
    RelativeLayout jjjj;
    DatePicker datePicker;
    TextView buttonDate;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        pd = new ProgressDialog(this);

        email = findViewById(R.id.Email);
        username = findViewById(R.id.username);
        passs = findViewById(R.id.password);
        passs = findViewById(R.id.password);
        cf_pass = findViewById(R.id.cf_password);
//        phone_user = findViewById(R.id.phone_user);
//        birthday = findViewById(R.id.tvcontentdate);
//
//        ////////// choose date birthday /////////////
//        jjjj = findViewById(R.id.datechoo);
//        datePicker = (DatePicker) this.findViewById(R.id.datePicker);
//        buttonDate = findViewById(R.id.tvcontentdate);
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        Button cok = findViewById(R.id.btn_data);
//        cok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jjjj.setVisibility(View.GONE);
//            }
//        });
//        this.datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
//                datePickerChange(datePicker, year, month, dayOfMonth);
//            }
//        });

//
//        this.birthday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDate();
//            }
//        });

        ///////////////////////////////////////

         signup = findViewById(R.id.sinnupbtn);
         tvlogin = findViewById(R.id.signin);
         tvlogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                onBackPressed();
             }
         });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register_user();
            }
        });



    }

    public void Register_user(){
        String emails = email.getText().toString().trim();
        String usernames = username.getText().toString().trim();
        String password = passs.getText().toString().trim();
        String cf_password = cf_pass.getText().toString().trim();
//        String phone = phone_user.getText().toString().trim();
//        String birthday = buttonDate.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            Toast.makeText(Register_Activity.this, "Empty Email...", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(password.length()<6){
            Toast.makeText(Register_Activity.this, "Password lenght at least 6 chara...", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!password.equals(cf_password) ){
            Toast.makeText(Register_Activity.this, "Comfirpassword does not match...", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(emails) ){
            Toast.makeText(Register_Activity.this, "Enter Password...", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(usernames)){
            Toast.makeText(Register_Activity.this, "Enter Username...", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(password) ){
            Toast.makeText(Register_Activity.this, "Enter Password...", Toast.LENGTH_SHORT).show();
            return;
        }else{
            pd.show();
            pd.setMessage("Registering.....");
            RetrofitInterface dataService = APIService.getService();
            Call<Void> callback = dataService.PostSignupUser(emails, usernames, password);
            callback.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
//                    Toast.makeText(Register_Activity.this, "Signup success", Toast.LENGTH_SHORT).show();
                        if(response.code() == 404){
                            pd.dismiss();
                            Toast.makeText(Register_Activity.this, "Signup faill", Toast.LENGTH_SHORT).show();
                        }else if (response.code() == 200 ){
                            pd.dismiss();
                            Toast.makeText(Register_Activity.this, "Signup success", Toast.LENGTH_SHORT).show();
                             onBackPressed();
                        }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(Register_Activity.this, "Rest api faill"+t, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }



//    private void datePickerChange(DatePicker datePicker, int year, int month, int dayOfMonth) {
//        this.buttonDate.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
//    }
//
//    private void showDate()  {
//        jjjj.setVisibility(View.VISIBLE);
//    }
}