package com.example.thebookbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "bookbank.db";
    public static final String TABLE_NAME = "BOOKREQ";


    public static final String TABLE_NAME4 = "payment_table";
    public static final String COL_1 = "MemberID";
    public static final String COL_2 = "ContactNo";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Amount";

    public static final String TABLE_NAME3 = "carddetails_table";
    public static final String COL_5 = "CardNo";
    public static final String COL_6 = "ExpDate";
    public static final String COL_7 = "Code";
    public static final String COL_8 = "Amount";

    public static final String TABLE_NAME2 = "feedback_table";
    public static final String COL_9 = "Name";
    public static final String COL_10 = "Feedback";

    public static final String TABLE_NAME5 = "users";
    public static final String TABLE_NAME6 = "admin";

    public static final String TABLE_NAME11 = "delivery";
    public static final String TABLE_NAME12 = "Dstates";
    public static final String TABLE_NAME13 = "Feedback";


    private String TABLE_CREATE_NAME4 = "create table " + TABLE_NAME4 + " (" +
            COL_1 + " TEXT PRIMARY KEY," +
            COL_2 + " TEXT," +
            COL_3 + " TEXT," +
            COL_4 + " REAL)";

    private String TABLE_CREATE_NAME3 = "create table " + TABLE_NAME3 + " (" +
            COL_5 + " TEXT PRIMARY KEY," +
            COL_6 + " TEXT," +
            COL_7 + " TEXT," +
            COL_8 + " REAL)";

    private String TABLE_CREATE_NAME2 = "create table " + TABLE_NAME2 + " (" +
            COL_9 + " TEXT," +
            COL_10 + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = "CREATE TABLE " + OrderContract.OrderEntry.TABLE_NAME1 + " ("
                + OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  OrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_QUANTITY + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_PRICE + " TEXT NOT NULL);";

        db.execSQL(SQL_TABLE);

        db.execSQL("create Table BOOKREQ(Username TEXT, Bookname TEXT, Author TEXT)");

        db.execSQL(TABLE_CREATE_NAME4);
        db.execSQL(TABLE_CREATE_NAME3);
        db.execSQL(TABLE_CREATE_NAME2);

        db.execSQL("create Table users(username TEXT primary key, password TEXT, quantity INTEGER, Regfee INTEGER)");
        db.execSQL("create Table admin(Uname TEXT primary key, Pword TEXT, email TEXT, phone TEXT)");

        db.execSQL("create Table delivery(username TEXT primary key, Location TEXT, ContactNumber TEXT)");
        db.execSQL("create Table Dstates(username TEXT primary key, States TEXT)");
        db.execSQL("create Table Feedback(Feeaback TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists BOOKREQ");

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);

        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists admin");

        db.execSQL("drop Table if exists delivery");
        db.execSQL("drop Table if exists Dstates");
    }

    public Boolean insertUserData(String username, String password, String quantity, String Regfee){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("quantity", quantity);
        contentValues.put("Regfee", Regfee);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertadminData(String Uname, String Pword, String email, String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Uname", Uname);
        contentValues.put("Pword", Pword);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        long result = MyDB.insert("admin", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkadminusername(String Uname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where Uname = ?", new String[]{Uname});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkadminusernamepassword(String Uname, String Pword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where Uname = ? and Pword = ?", new String[] {Uname,Pword});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean UpdateUserdata(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("password",password);
        //db.rawQuery("UPDATE users SET password=? where username = ?", new String[] {password,username});
        db.update(TABLE_NAME5, contentValues, "username = ?",new String[] {username});

        //db.update(TABLE_NAME,contentValues,username+"= ?",new String[]{username});
        return true;


    }

    public boolean Updateadmindata(String Uname,String Pword,String email,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Uname",Uname);
        contentValues.put("Pword",Pword);
        contentValues.put("email",email);
        contentValues.put("phone",phone);
        db.update(TABLE_NAME6, contentValues, "Uname = ?",new String[] {Uname});


        return true;


    }

    public Cursor getUserdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME5+"  ",  null);
        return res;
    }

    public Integer deleteUserData (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME5, "username == ?",new String[] {username});
    }

    public Cursor getadmindata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME6+"  ",  null);
        return res;
    }

    public Integer deleteadminData (String Uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME6, "Uname == ?",new String[] {Uname});
    }

    public Boolean insertData(String Username, String Bookname, String Author){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("Bookname", Bookname);
        contentValues.put("Author", Author);
        long result = MyDB.insert("BOOKREQ", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+"  ",  null);
        return res;
    }

    public Integer deleteData (String Username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Username = ?",new String[] {Username});
    }

    public boolean Updatedata(String Username,String bName,String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username",Username);
        contentValues.put("Bookname",bName);
        contentValues.put("Author",author);
        db.update(TABLE_NAME, contentValues, "Username = ?",new String[] {Username});

        return true;
    }

    public boolean insertPaymentData(String memberid, String contactno, String email, String amount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,memberid);
        contentValues.put(COL_2,contactno);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,amount);
        long result =  db.insert(TABLE_NAME4, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public boolean insertFeedbackdata(String name, String feedback){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,name);
        contentValues.put(COL_10,feedback);
        long result =  db.insert(TABLE_NAME2, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getPaymentdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME4+"  ",  null);
        return res;
    }

    public Cursor getFeedbackData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME2+"  ",  null);
        return res;
    }


    public boolean InsertCardData(String cardno, String expdate, String code, String amountt){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5,cardno);
        contentValues.put(COL_6,expdate);
        contentValues.put(COL_7,code);
        contentValues.put(COL_8,amountt);
        long result =  db.insert(TABLE_NAME3, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public boolean updatePaymentData(String memberid,String contactno,String email,String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,memberid);
        contentValues.put(COL_2,contactno);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,amount);
        db.update(TABLE_NAME4, contentValues, "MemberID = ?",new String[] { memberid});
        return true;
    }
    public boolean UpdateHandlingdata(String memberid,String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,memberid);
        contentValues.put(COL_4,amount);
        db.update(TABLE_NAME4, contentValues, "MemberID = ?",new String[] { memberid});
        return true;
    }
    public boolean updateFeedbackData(String name,String feedback) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,name);
        contentValues.put(COL_10,feedback);
        db.update(TABLE_NAME2, contentValues, "Name = ?",new String[] { name});
        return true;
    }
    public boolean UpdateCardData(String cardno, String expdate, String code, String amountt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5,cardno);
        contentValues.put(COL_6,expdate);
        contentValues.put(COL_7,code);
        contentValues.put(COL_8, amountt);
        db.update(TABLE_NAME3, contentValues, "CardNo = ?",new String[] { cardno});
        return true;
    }

    public Integer deletePaymentData (String memberid) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME4, "MemberID = ?",new String[] {memberid});
    }
    public Integer deleteCarddata (String cardno) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME3, "CardNo  = ?",new String[] {cardno});
    }
    public Integer DeleteFeedData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "Name = ?",new String[] {name});
    }

    boolean isEmail(EditText text) { //check valid Email
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isPhone(EditText text) { //check valid phone
        CharSequence phone = text.getText().toString();
        Pattern ptrn = Pattern.compile("(0/91)?[0-9][0-9]{9}");
//the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(phone);
//returns a boolean value
        return (match.find() && match.group().equals(phone));
    }

    public Boolean Adddelivery(String username, String Location, String ContactNumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("Location", Location);
        contentValues.put("ContactNumber", ContactNumber);
        long result = MyDB.insert("delivery", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean AddStates(String username, String States){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("States", States);
        long result = MyDB.insert("Dstates", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean Feedback(String Feeaback){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Feeaback", Feeaback);
        long result = MyDB.insert("Feedback", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor viewDelivery() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME12+"  ",  null);
        return res;
    }

    public Integer DeleteStates (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME12, "username == ?",new String[] {username});
    }

    public boolean StatesUpdate(String username,String States) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("States",States);
        db.update(TABLE_NAME12, contentValues, "username = ?",new String[] {username});


        return true;


    }

    public Cursor ViewDeliveryList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME11+"  ",  null);
        return res;
    }

    boolean checkPhone(EditText text) { //check valid phone
        CharSequence phone = text.getText().toString();
        Pattern ptrn = Pattern.compile("(0/91)?[0-9][0-9]{9}");
//the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(phone);
//returns a boolean value
        return (match.find() && match.group().equals(phone));
    }


}

