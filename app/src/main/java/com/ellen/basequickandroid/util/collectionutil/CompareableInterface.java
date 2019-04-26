package com.ellen.basequickandroid.util.collectionutil;

//用于比较器
public interface CompareableInterface<T> {
    //比较二者的大小 0 相同，正值代表外面大于里面，负值代表里面大于外面
    int compareTo(T t);
}
