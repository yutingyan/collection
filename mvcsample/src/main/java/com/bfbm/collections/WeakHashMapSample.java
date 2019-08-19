package com.bfbm.collections;

import java.util.Arrays;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * A customized WeakHashMapSample.
 *
 * @author 巴分巴秒-Eric老师
 * @Date 2019/08/15
 * @since v1.1
 **/
public class WeakHashMapSample {


    static class Element{
        private static final int SIZE=10000;
        private long[] longArray = new long[SIZE];
        private String ident;
        public Element(String id){
            ident = id;
        }
        public String toString(){
            return ident;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Element)) return false;
            Element element = (Element) o;
            return Arrays.equals(longArray, element.longArray) &&
                    ident.equals(element.ident);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(ident);
            result = 31 * result + Arrays.hashCode(longArray);
            return result;
        }

        protected void finalize(){
            System.out.println("Finalizing " +getClass().getSimpleName()+ident);
        }

    }

    static class Key extends  Element{
        public Key(String id){
            super(id);
        }
    }

    static class Value extends  Element{
        public Value(String id){
            super(id);
        }
    }

    public static void main(String[] args) {
        int size = 10000;
        Key[] keys = new Key[size];
        WeakHashMap<Key,Value> map = new WeakHashMap<Key,Value>();

        for (int i =0; i<size;i++){
            Key k = new Key("" +i);
            Value v= new Value(("" +i));
            if((i % 3) == 0) keys[i] = k;
            map.put(k,v);
        }

        System.gc();
    }

}
