package com.ellen.basequickandroid.save.key_value;

/**
 * SharedPreference或者MMKV的抽象层
 */
public abstract class BaseKeyValueHelper {

    private String name;

    public BaseKeyValueHelper(String name){
        this.name = name;
    }

    //存储获取非加密数据
    public abstract void save(String key,Object value);
    public abstract Object getValue(String key,Object defaultValue);

    //存储获取加密数据
    public abstract void safeSave(String encryptionString,String key,String value);
    public abstract Object safeGetValue(String encryptionString,String key,Object defaultValue);

}
