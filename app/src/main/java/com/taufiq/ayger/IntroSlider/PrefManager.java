package com.taufiq.ayger.IntroSlider;
import android.content.Context;
import android.content.SharedPreferences;
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_ID = "spID";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "codeplayon.com";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void saveNama(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }
    public String getNama(){
        return pref.getString(SP_NAMA,"s");
    }
    public void saveId(String keySP, String value){
        editor.putString(keySP, value);
        editor.commit();
    }
    public  String  getId(){
        return pref.getString(SP_ID,"1");
    }
    public void saveSPString(String keySP, String value){
        editor.putString(keySP, value);
        editor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value){
        editor.putBoolean(keySP, value);
        editor.commit();
    }
    public Boolean getSPSudahLogin(){
        return pref.getBoolean(SP_SUDAH_LOGIN, false);
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
