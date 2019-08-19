package com.bfbm.collections.hashmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A performance tuning aggregation HashMap.
 *
 * @author 巴分巴秒-Eric老师  QQ:1580716954
 * @Date 2019/08/15
 * @since v1.1
 **/
public class InstrumentedMap<K,V> implements Map<K,V> {

    private final Map<K,V> m = new HashMap<K,V>();

    private int addCount = 0;

    public InstrumentedMap(Map<K,V> m) {
        m = m;
    }

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V put(K key, V value) {
        addCount++;
        return m.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        addCount += map.size();
        m.putAll(map);
    }



    public int getAddCount(){
        return addCount;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return m.remove(key);
    }


    @Override
    public void clear() {
        m.clear();
    }

    @Override
    public Set<K> keySet() {
        return m.keySet();
    }

    @Override
    public Collection<V> values() {
        return m.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return m.entrySet();
    }

    @Override
    public String toString() {
        return "InstrumentedMap{" +
                "m=" + m +
                ", addCount=" + addCount +
                '}';
    }

    public static void main(String[] args) {

        InstrumentedMap instrumentedMap = new InstrumentedMap(new HashMap(16, 0.75f));

        instrumentedMap.put("key1", "value1");
        instrumentedMap.put("key2", "value2");
        instrumentedMap.put("key3", "value3");

        instrumentedMap.putAll(instrumentedMap);

        System.out.println(instrumentedMap.toString());

        System.out.println(" getAddCount: "+ instrumentedMap.getAddCount());

    }
}
