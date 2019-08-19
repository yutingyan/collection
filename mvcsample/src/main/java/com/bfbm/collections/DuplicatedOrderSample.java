package com.bfbm.collections;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * A customized duplicated and order sample.
 *
 * @author 巴分巴秒-Eric老师
 * @Date 2019/08/15
 * @since v1.1
 **/
public class DuplicatedOrderSample {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList<String>();
        linkedList.add("111");
        linkedList.add("333");
        linkedList.add("222");
        linkedList.add("444");
        linkedList.add("555");
        linkedList.add("111");
        System.out.println(linkedList);

        TreeSet treeSet = new TreeSet();
        treeSet.add("111");
        treeSet.add("333");
        treeSet.add("222");
        treeSet.add("444");
        treeSet.add("555");
        treeSet.add("111");
        System.out.println(treeSet);


        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("111");
        linkedHashSet.add("333");
        linkedHashSet.add("222");
        linkedHashSet.add("444");
        linkedHashSet.add("555");
        linkedHashSet.add("111");
        System.out.println(linkedHashSet);

    }



}
