package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginForgetActivity extends AppCompatActivity {

    EditText password1;
    EditText password2;
    EditText confirm;
    String password;
    String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_forget);

        password1=findViewById(R.id.newlabel1);
        password2=findViewById(R.id.newlabel2);
        confirm=findViewById(R.id.newlabel3);
        phone = getIntent().getStringExtra("phone");
        findViewById(R.id.newbutton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone==null||phone.length()!=11){
                    Toast.makeText(LoginForgetActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    return;
                }else
                {
                    int random =(int)(Math.random()*1000000);
                    password = Integer.toString(random);
                    AlertDialog.Builder builder= new AlertDialog.Builder(LoginForgetActivity.this);
                    builder.setTitle("请记住验证码");
                    builder.setMessage("手机号"+phone+"，本次验证码是"+password);
                    builder.setPositiveButton("好的",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        findViewById(R.id.newbutton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass1=password1.getText().toString();
                String pass2=password2.getText().toString();
                if (!pass1.equals(pass2)){
                    Toast.makeText(LoginForgetActivity.this,"两次输入的密码不相同",Toast.LENGTH_SHORT).show();
                }else if (!confirm.getText().toString().equals(password)){
                    Toast.makeText(LoginForgetActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginForgetActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.putExtra("password",pass1);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}
