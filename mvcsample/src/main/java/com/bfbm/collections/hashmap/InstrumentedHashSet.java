package com.bfbm.collections.hashmap;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * A performance tuning inherited HashSet.
 *
 * @author 巴分巴秒-Eric老师  QQ:1580716954
 * @Date 2019/08/15
 * @since v1.1
 **/
public class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(Collection<? extends E> c) {
        super(c);
    }

    public InstrumentedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }


    public  boolean add(E e){
        addCount++;
        return super.add(e);
    }

    public boolean addAll(Collection<? extends E> c){
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount(){
        return this.addCount;
    }


    public static void main(String[] args) {

        List<String> list = Arrays.asList(new String[]{"add","remove", "size"});
        System.out.println(" List size is: "+ list.size());

        InstrumentedHashSet set = new InstrumentedHashSet();
        set.addAll(list);

        System.out.println("After addAll: " + set.getAddCount());


    }

}
