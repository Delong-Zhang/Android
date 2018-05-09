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
 * 修改对话框
 */
public class UpdateDialog extends Activity implements View.OnClickListener {
    /**
     * 修改请求代码
     */
    private static final int UPDATE_REQUESTCODE = 3;

    /**
     * id控件
     */
    private EditText idView;
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
        setContentView(R.layout.update_dialog);
        init();
    }

    private void init() {
        findViewById(R.id.update_confirm).setOnClickListener(this);
        findViewById(R.id.update_cancel).setOnClickListener(this);
        idView = (EditText) findViewById(R.id.update_edit_id);
        nameView = (EditText) findViewById(R.id.update_edit_name);
        ageView = (EditText) findViewById(R.id.update_edit_age);
        sexView = (EditText) findViewById(R.id.update_edit_sex);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.update_confirm:
                confirm(); // 确认修改
                break;
            case R.id.update_cancel:
                cancel(); // 取消修改
                break;
        }
    }

    /**
     * 确认修改
     */
    private void confirm() {
        int _id = Integer.valueOf(String.valueOf(idView.getText()));
        String name = String.valueOf(nameView.getText());
        int age = Integer.valueOf(String.valueOf(ageView.getText()));
        String sex = String.valueOf(sexView.getText());
        Person person = new Person();
        person.set_id(_id);
        person.setAge(age);
        person.setName(name);
        person.setSex(sex);
        Intent intent = new Intent();
        intent.putExtra("person", person);
        setResult(UPDATE_REQUESTCODE, intent);
        finish();
    }

    /**
     * 取消修改
     */
    private void cancel() {
        setResult(UPDATE_REQUESTCODE);
        finish();
    }
}
