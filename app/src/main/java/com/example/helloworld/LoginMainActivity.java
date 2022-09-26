package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginMainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Spinner spinner;
    EditText editText1;
    EditText editText2;
    Button button1;
    Button button2;
    String password;

    TextView textcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        password ="000000";

        spinner = (Spinner) findViewById(R.id.spinner1);
        String[] user = getResources().getStringArray(R.array.user);
        ArrayAdapter<String> users = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,user);
        spinner.setAdapter(users);
        spinner.setSelection(0);
        spinner.setPrompt("请选择用户");

        editText1 = (EditText) findViewById(R.id.label1);
        editText2 = (EditText) findViewById(R.id.label2);
        button1 =(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        textcode =(TextView) findViewById(R.id.textcode);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId()==R.id.btn1){
                    textcode.setText("登陆密码：");
                    button1.setText("忘记密码");
                }
                if (radioGroup.getCheckedRadioButtonId()==R.id.btn2){
                    textcode.setText("   验证码：");
                    button1.setText("获取验证码");
                }
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editText1.getText().toString();
                if (phone==null||phone.length()!=11){
                    Toast.makeText(LoginMainActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (radioGroup.getCheckedRadioButtonId()==R.id.btn1){
                    Intent intent = new Intent(LoginMainActivity.this,LoginForgetActivity.class);
                    intent.putExtra("phone",phone);
                    startActivityForResult(intent,1);
                }
                if(radioGroup.getCheckedRadioButtonId()==R.id.btn2){
                    int random =(int)(Math.random()*1000000);
                    password = Integer.toString(random);
                    AlertDialog.Builder builder= new AlertDialog.Builder(LoginMainActivity.this);
                    builder.setTitle("请记住密码");
                    builder.setMessage("手机号"+phone+"，本次验证码是"+password);
                    builder.setPositiveButton("好的",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editText1.getText().toString();
                String passwordIn =editText2.getText().toString();
                if (phone==null||phone.length()!=11){
                    Toast.makeText(LoginMainActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordIn.equals(password)==false){
                    Toast.makeText(LoginMainActivity.this,"请输入密码或验证吗",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    AlertDialog.Builder builder= new AlertDialog.Builder(LoginMainActivity.this);
                    builder.setTitle("登录成功");
                    builder.setMessage("手机号"+phone+"，用户类型"+spinner.getSelectedItem().toString()+"，恭喜你通过验证");
                    builder.setPositiveButton("好的",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            password=data.getStringExtra("password");
    }

    @Override
    protected void onRestart() {
        editText2.setText("");
        super.onRestart();
    }
}
