package com.ellen.basequickandroid.util.collectionutil;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    //对某个集合进行排序
    public static <E> List<E> sort(List<E> eList) {
        List<E> copyList = new ArrayList<>();
        for(E e:eList){
            copyList.add(e);
        }
        E e = eList.get(0);
        if (!(e instanceof CompareableInterface)) {
            //抛出异常 -> 说明它没有实现比较器接口
            throw new CompareableException("", "your class Not Implemented CompareableInterface");
        }
        //使用冒泡排序进行排序
        for (int i = 0; i < copyList.size(); i++) {
            for (int j = i + 1; j < copyList.size(); j++) {
                CompareableInterface iCompareable = (CompareableInterface) copyList.get(i);
                if (iCompareable.compareTo(copyList.get(j)) >= 0) {
                    E e1 = copyList.get(i);
                    copyList.set(i, copyList.get(j));
                    copyList.set(j, e1);
                }
            }
        }
        return copyList;
    }

    //对某个集合进行整理算法
    public static <E> List<List<E>> arrange(List<E> eList){
        if(eList == null || eList.size() == 0){
            //执行这里说明无法进行整理归类
            return null;
        }
        List<E> copyList = new ArrayList<>();
        List<List<E>> listList = new ArrayList<>();
        for(E e:eList){
            copyList.add(e);
        }
        E e = eList.get(0);
        if (!(e instanceof ArrangeInterface)) {
            //抛出异常 -> 说明它没有实现归类整理器接口
            throw new CompareableException("", "your class Not Implemented ArrangeInterface");
        }
        for(int i = 0;i < eList.size();i++){
            boolean isAddList = true;
            for(List<E> list:listList){
                E eCompare = list.get(0);
                ArrangeInterface arrangeInterface = (ArrangeInterface) eList.get(i);
                boolean falg = arrangeInterface.identical(eCompare);
                if(falg){
                    //是相同的
                    list.add(eList.get(i));
                    isAddList = false;
                    break;
                }
            }
            if(isAddList){
                List<E> eList1 = new ArrayList<>();
                eList1.add(eList.get(i));
                listList.add(eList1);
            }
        }
        return listList;
    }

}
