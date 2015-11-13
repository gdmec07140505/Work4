package s07140505.gdmec.edu.cn.work4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chdy on 2015/11/6.
 */
public class MyDB extends SQLiteOpenHelper{
    private static String DB_NAME = "My_DB.db";
    private static int DB_VERSION = 2;
    private SQLiteDatabase db;

    public MyDB(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
        db=getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public SQLiteDatabase openConnection(){
        if (!db.isOpen()){
            db=getWritableDatabase();
        }
        return db;
    }
    public void closeConnection(){
        try {
            if (db!=null&&db.isOpen()) {
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean createTable(String createTableSql){
        try{
            openConnection();
            db.execSQL(createTableSql);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    public boolean save(String tableName,ContentValues values){
        try{
            openConnection();
            db.insert(tableName, null, values);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //更新数据
    public boolean update(String table,ContentValues values,String whereClause,String whereArgs[]){
        try{
            openConnection();
            db.update(table, values, whereClause, whereArgs);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //删除数据
    public boolean delete(String table,String deleteSql,String obj[]){
        try{
            openConnection();
            db.delete(table, deleteSql, obj);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //查找数据
    public Cursor find(String findSql,String obj[]){
        try{
            openConnection();
            Cursor cursor=db.rawQuery(findSql,obj);
            return cursor;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    //检查数据表是否存在
    public boolean isTableExists(String tableName){
        try{
            openConnection();
           String str="select count(*)xcount from "+tableName;
            db.rawQuery(str,null).close();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }

}
