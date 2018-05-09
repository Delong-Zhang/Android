package com.example.foolishfan.user_v10;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库创建、更新
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    /**
     * @param context
     *            上下文
     * @param name
     *            数据库名称
     * @param factory
     *            游标工厂
     * @param version
     *            数据库版本
     */
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    // 创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.e("SqliteHelper", "数据库创建");
        String sql = "create table person(_id integer Primary Key autoincrement,name varchar(20), age integer,sex varchar(20))";
        db.execSQL(sql);
    }

    // 数据库更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.e("SqliteHelper", "数据库更新");
    }

    /**
     * 添加Person到数据库
     *
     * @param person
     *            Person
     */
    public void addPerson(Person person) {
//        Log.e("SqliteHelper", "插入");
        SQLiteDatabase db = getWritableDatabase(); // 以读写的形式打开数据库
//		db.execSQL("insert into person(name,age) values("
//				+ String.format("'%s'", person.getName()) + ","
//				+ person.getAge() + ");"); // 插入数据库

        // insert into person(name,age,sex) values('liudehua',50,'man')
//		db.execSQL(
//				"insert into person(name,age,sex) values("
//				+ String.format("'%s'", person.getName()) + ","
//				+ person.getAge() + ","
//				+ String.format("'%s'", person.getSex()) +
//				");"
//		); // 插入数据库

        db.execSQL(
                "insert into person(name,age,sex) values("
                        + String.format("'%s'", person.getName()) + ","
                        + person.getAge() + ","
                        + String.format("'%s'", person.getSex()) +
                        ");"
        ); // 插入数据库

        db.close(); // 关闭数据库连接
    }

    /**
     * 更新Person
     *
     * @param person
     *            Person
     */
    public void updatePerson(Person person) {
//        Log.e("SqliteHelper", "更新");
        SQLiteDatabase db = getWritableDatabase(); // 以读写的形式打开数据库
//		String sql = "update person set name="
//				+ String.format("'%s'", person.getName()) + ",age="
//				+ person.getAge() + " where _id=" + person.get_id();

        String sql = "update person set name="
                + String.format("'%s'", person.getName())
                + ",age=" + person.getAge()
                + ",sex=" + String.format("'%s'", person.getSex())
                + " where _id=" + person.get_id();

//        Log.e("updatePerson", sql);
        db.execSQL(sql); // 更新数据库
        db.close(); // 关闭数据库连接
    }

    /**
     * 删除Person
     *
     * @param _id
     *            Person的id
     */
    public void deletePerson(int _id) {
        Log.e("SqliteHelper", "删除");
        SQLiteDatabase db = getWritableDatabase(); // 以读写的形式打开数据库
        String sql = "_id = ?";
        String wheres[] = { String.valueOf(_id) };
        db.delete("person", sql, wheres); // 数据库删除
        db.close(); // 关闭数据库
    }

    /**
     * 查询所有的Person
     *
     * @return 所有Person集合
     */
    public List<Person> queryAllPerson() {
        List<Person> list = new ArrayList<Person>();
        SQLiteDatabase db = getReadableDatabase(); // 以只读的方式打开数据库
        String sql = "select * from person;";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            Person person = new Person();
            person.set_id(_id);
            person.setName(name);
            person.setAge(age);
            person.setSex(sex);
            System.out.println(" ---- sex = " + sex);
            list.add(person); // 添加到数组
        }
        cursor.close(); // 关闭游标
        db.close(); // 关闭数据库
        return list;
    }

    /**
     * 根据id查询Person
     *
     * @param _id
     *            id
     * @return Person
     */
    public Person queryPersonById(int _id) {
        Person person = null;
        SQLiteDatabase db = getReadableDatabase(); // 以只读方式打开数据库
//		String[] columns = { "_id", "name", "age" };
        String[] columns = { "_id", "name", "age", "sex" };
        String selection = "_id=?";
        String[] selectionArgs = { String.valueOf(_id) };
        Cursor cursor = db.query("person", columns, selection, selectionArgs,
                null, null, null);
        if (cursor.moveToNext()) {
            person = new Person();
            person.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            person.setName(cursor.getString(cursor.getColumnIndex("name")));
            person.setName(cursor.getString(cursor.getColumnIndex("sex")));
        }
        return person;
    }
}
