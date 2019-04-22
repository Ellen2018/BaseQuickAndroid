package com.ellen.basequickandroid.save;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceHelper extends BaseKeyValueHelper {

    private SharedPreferences sharedPreferences;
    /*
     * 保存手机里面的名字
     */private SharedPreferences.Editor editor;


    public SharePreferenceHelper(Context context, String fileName) {
        super(fileName);
        sharedPreferences = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void save(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
        editor.commit();
    }

    @Override
    public Object getValue(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    @Override
    public void safeSave(String encryptionString, String key, String value) {
        save(encryptionString + key, value);
    }

    @Override
    public Object safeGetValue(String encryptionString, String key, Object defaultObject) {
        String newKey = encryptionString + key;
        return getValue(newKey, defaultObject);
    }
}
