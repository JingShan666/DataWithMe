package com.example.administrator.myapplication1.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu on 2017/7/10 0010.
 */

public class DBServer {

    private DBHelper dbhelper;
    public DBServer(Context context)
    {
        this.dbhelper = new DBHelper(context);
    }
    /**
     * 添加相册
     * @param entity
     */
    public void addPhotoFile(Photo entity)
    {

        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = entity.getPhotoUrl();
        arrayOfObject[1] = entity.getPhotoName();
       // arrayOfObject[2] = entity.getPhotoName();
        localSQLiteDatabase.execSQL("insert into photoFile(photoUrl,photoNum) values(?,?)", arrayOfObject);
        localSQLiteDatabase.close();

        copyDBToSDcrad();
    }


    /**
     * 	取得所有相册
     * @return
     */
    public List<Photo> findAllPhotoFiles()
    {
        List<Photo> localArrayList=new ArrayList ();
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select * from photoFile " +
                "where 1=1", null);
        while (localCursor.moveToNext())
        {
            Photo temp=new Photo();
            temp.setPhotoId(localCursor.getString(localCursor.getColumnIndex("photo_id")));
            temp.setPhotoUrl(localCursor.getString(localCursor.getColumnIndex("photoUrl")));
            temp.setPhotoName(localCursor.getString(localCursor.getColumnIndex("photoNum")));
            localArrayList.add(temp);
        }
        localSQLiteDatabase.close();

        copyDBToSDcrad();
        return localArrayList;
    }


    /**
     * 确认该相册是否存在
     *  classId
     * @return
     */
    public boolean isClassExists(String s)
    {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select count(*)  from photoFile  " +
                "where photoUrl=? or photoNum=?", new String[]{s,s});
        localCursor.moveToFirst();
        if(localCursor.getLong(0)>0)
            return true;
        else
            return false;

    }


    /**
     * 删除一个相册
     * 同时会相册中所有图片
     * @param photo_id
     */
    public void deletePhotoFile(String photo_id)
    {
        SQLiteDatabase localSQLiteDatabase = this.dbhelper.getWritableDatabase();
        //设置了级联删除和级联更新
        //在执行有级联关系的语句的时候必须先设置“PRAGMA foreign_keys=ON”
        //否则级联关系默认失效
        localSQLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] =photo_id;
        localSQLiteDatabase.execSQL("delete from photoFile where photo_id=?", arrayOfObject);
        localSQLiteDatabase.close();

        copyDBToSDcrad();
    }


    private void copyDBToSDcrad()
    {
        String DATABASE_NAME = "info.db";

        String oldPath = "data/data/com.example.administrator.myapplication1/databases/" + DATABASE_NAME;
        String newPath = Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME;

        copyFile(oldPath, newPath);
    }

    /**
     * 复制单个文件
     *
     * @param oldPath
     *            String 原文件路径
     * @param newPath
     *            String 复制后路径
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath)
    {
        try
        {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath);
            if (!newfile.exists())
            {
                newfile.createNewFile();
            }
            if (oldfile.exists())
            { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1)
                {
                    bytesum += byteread; // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                    Log.e("复制单个文件操作成功","66666");

                    Log.e("复制单个文件信路径",newPath);
                }
                inStream.close();
            }
        }
        catch (Exception e)
        {
           Log.e("复制单个文件操作出错","66666");
            e.printStackTrace();

        }

    }
}
