package com.example.foolishfan.user_v10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foolishfan.user_v10.dialog.DeleteDialog;
import com.example.foolishfan.user_v10.dialog.InsertDialog;
import com.example.foolishfan.user_v10.dialog.UpdateDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private SQLiteHelper db;
    private ListAdapter listAdapter;

    /**
     * 插入请求代码
     */
    private static final int INSERT_REQUESTCODE = 1;
    /**
     * 插入结果代码
     */
    private static final int INSERT_RESULTCODE = 1;

    /**
     * 删除请求代码
     */
    private static final int DELETE_REQUESTCODE = 2;
    /**
     * 删除结果代码
     */
    private static final int DELETE_RESULTCODE = 2;

    /**
     * 修改请求代码
     */
    private static final int UPDATE_REQUESTCODE = 3;
    /**
     * 修改结果代码
     */
    private static final int UPDATE_RESULTCODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLiteHelper(this, "person.db", null, 1);
        init();
    }

    private void init() {
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        listAdapter = new ListAdapter(new ArrayList<Person>(), this);
        findViewById(R.id.list_data, ListView.class).setAdapter(listAdapter);
    }

    private <T> T findViewById(int id, Class<T> c) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert: // 插入
                insert();
                break;
            case R.id.btn_delete: // 删除
                delete();
                break;
            case R.id.btn_update: // 修改
                update();
                break;
            case R.id.btn_query: // 查询
                query();
                break;
        }
    }

    /**
     * 添加
     */
    private void insert() {
        Intent intent = new Intent(this, InsertDialog.class);
        startActivityForResult(intent, INSERT_REQUESTCODE);
    }

    /**
     * 删除
     */
    private void delete() {
        Intent intent = new Intent(this, DeleteDialog.class);
        startActivityForResult(intent, DELETE_REQUESTCODE);
    }

    /**
     * 更新
     */
    private void update() {
        Intent intent = new Intent(this, UpdateDialog.class);
        startActivityForResult(intent, UPDATE_REQUESTCODE);
    }

    /**
     * 查询
     */
    private void query() {
        List<Person> list = db.queryAllPerson(); // 查询所有的Person
        loadData(list); // 加载数据到ListView上面
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case INSERT_REQUESTCODE:
                if (resultCode == INSERT_RESULTCODE) { // 插入请求
                    if (data != null) {
                        db.addPerson((Person) data.getSerializableExtra("person"));
                        showMessage("插入成功!");
                        query();
                    } else {
                        showMessage("取消插入!");
                    }
                }
                break;
            case DELETE_REQUESTCODE:
                if (resultCode == DELETE_RESULTCODE) { // 删除请求
                    if (data != null) {
                        int _id = data.getIntExtra("_id", -1);
                        if (_id != -1) {
                            if (db.queryPersonById(_id) != null) {
                                db.deletePerson(_id);
                                showMessage("删除成功!");
                                query();
                            } else {
                                showMessage("删除失败\t_id:" + _id + "不存在!");
                            }
                        } else {
                            showMessage("删除失败!");
                        }
                    } else {
                        showMessage("取消删除!");
                    }
                }
                break;
            case UPDATE_REQUESTCODE:
                if (resultCode == UPDATE_RESULTCODE) { // 修改请求
                    if (data != null) {
                        Person person = (Person) data
                                .getSerializableExtra("person");
                        int _id = person.get_id();
                        if (db.queryPersonById(person.get_id()) != null) {
                            db.updatePerson(person);
                            showMessage("修改成功!");
                            query();
                        } else {
                            showMessage("修改失败\t_id:" + _id + "不存在!");
                        }
                    } else {
                        showMessage("取消修改!");
                    }
                }
                break;
        }

    }

    /**
     * 显示消息
     *
     * @param msg
     *            消息
     */
    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载数据到ListView
     *
     * @param list
     *            Person集合
     */
    private void loadData(List<Person> list) {
        listAdapter.setList(list);
        listAdapter.notifyDataSetChanged(); // 刷新数据
    }
}


