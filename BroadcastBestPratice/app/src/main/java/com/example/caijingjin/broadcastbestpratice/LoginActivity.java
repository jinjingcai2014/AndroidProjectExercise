package com.example.caijingjin.broadcastbestpratice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText)findViewById(R.id.account);
        passwordEdit = (EditText)findViewById(R.id.password);
        rememberBox  = (CheckBox)findViewById(R.id.remember_pass);
        login = (Button)findViewById(R.id.login);
        boolean isRemember =pref.getBoolean("remember_password",false);
        if(isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberBox.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if(account.equals("admin") && password.equals("123456")){
                    //记录下账号和密码
                    editor = pref.edit();
                    if(rememberBox.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();//提交数据
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Account or password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
