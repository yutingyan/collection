package com.bfbm.collections.hashmap;

import java.util.*;

/**
 * A performance tuning aggregation HashSet.
 *
 * @author 巴分巴秒-Eric老师
 * @Date 2019/08/15
 * @since v1.1
 **/
public class InstrumentedSet<E>  implements Set<E> {
    //FIXME: 作业2
    public InstrumentedSet(Set<E> e) {
        e=e;
    }

    private int addCount = 0;
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount=c.size();
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
    public int getAddCount(){
        return addCount;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList(new String[]{"add","remove", "size"});
        System.out.println(" List size is: "+ list.size());

        InstrumentedSet set = new InstrumentedSet(new HashSet<>());

        set.addAll(list);

        System.out.println("After addAll: " + set.getAddCount());


    }



}
