package com.example.foolishfan.user_v10.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.foolishfan.user_v10.R;

/**
 * 删除对话框
 */
public class DeleteDialog extends Activity implements View.OnClickListener {
    /**
     * 插入请求代码
     */
    private static final int DELETE_REQUESTCODE = 2;
    /**
     * id控件
     */
    private EditText idView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);	//去掉标题栏
        setContentView(R.layout.delete_dialog);
        init();
    }

    private void init() {
        findViewById(R.id.delete_confirm).setOnClickListener(this);
        findViewById(R.id.delete_cancel).setOnClickListener(this);
        idView = (EditText) findViewById(R.id.delete_edit_id);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.delete_confirm:
                confirm(); // 确认删除
                break;
            case R.id.delete_cancel:
                cancel(); // 取消删除
                break;
        }
    }

    /**
     * 确认删除
     */
    private void confirm() {
        int _id = Integer.valueOf(String.valueOf(idView.getText()));
        Intent intent = new Intent();
        intent.putExtra("_id", _id);
        setResult(DELETE_REQUESTCODE, intent);
        finish();
    }

    /**
     * 取消删除
     */
    private void cancel() {
        setResult(DELETE_REQUESTCODE);
        finish();
    }
}
