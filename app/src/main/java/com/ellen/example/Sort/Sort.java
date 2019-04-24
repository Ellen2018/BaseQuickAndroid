package com.ellen.example.Sort;

//排序算法归类
public class Sort {

    //冒泡排序范例
    public static void maoPaoSort(int[] array){
        for(int i=0;i<array.length;i++) {
            for(int j=i+1;j<array.length;j++) {
                if(array[i]>array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    //选择排序范例
    public static void chooseSort(int[] array){
        for(int i=0;i<array.length;i++) {
            int tem = i;
            for(int j=i;j<array.length;j++) {
                if(array[j] < array[tem]) {
                    tem = j;
                }
            }
            int temp1 = array[i];
            array[i] = array[tem];
            array[tem] = temp1;
        }
    }

}
