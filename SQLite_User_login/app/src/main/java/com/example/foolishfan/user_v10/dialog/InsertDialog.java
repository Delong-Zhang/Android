package com.example.foolishfan.user_v10.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.foolishfan.user_v10.Person;
import com.example.foolishfan.user_v10.R;

/**
 * 插入对话框
 */
public class InsertDialog extends Activity implements View.OnClickListener {
    /**
     * 插入请求代码 大于0
     */
    private static final int INSERT_REQUESTCODE = 1;
    /**
     * 姓名控件
     */
    private EditText nameView;
    /**
     * 年龄控件
     */
    private EditText ageView;
    /**
     * 性别控件
     */
    private EditText sexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);	//去掉标题栏
        setContentView(R.layout.insert_dialog);
        init();
    }

    private void init() {
        findViewById(R.id.insert_confirm).setOnClickListener(this);
        findViewById(R.id.insert_cancel).setOnClickListener(this);
        nameView = (EditText) findViewById(R.id.insert_edit_name);
        ageView = (EditText) findViewById(R.id.insert_edit_age);
        sexView = (EditText) findViewById(R.id.insert_edit_sex);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.insert_confirm:
                confirm(); // 确认插入
                break;
            case R.id.insert_cancel:
                cancel(); // 取消插入
                break;
        }
    }

    /**
     * 确认插入
     */
    private void confirm() {
        String name = String.valueOf(nameView.getText());
        int age = Integer.valueOf(String.valueOf(ageView.getText()));
        String sex = String.valueOf(sexView.getText());
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setSex(sex);
        Intent intent = new Intent();
        intent.putExtra("person", person);
        setResult(INSERT_REQUESTCODE, intent);
        finish();
    }

    /**
     * 取消插入
     */
    private void cancel() {
        setResult(INSERT_REQUESTCODE);
        finish();
    }
}
