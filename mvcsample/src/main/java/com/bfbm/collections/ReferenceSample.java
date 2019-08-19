package com.bfbm.collections;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * A customized ReferenceSample.
 *
 * @author 巴分巴秒-Eric老师
 * @Date 2019/08/15
 * @since v1.1
 **/
public class ReferenceSample {

    static class BigClass{
        private static final int SIZE=10000;
        private long[] longArray = new long[SIZE];
        private String ident;
        public BigClass(String id){
            ident = id;
        }
        public String toString(){
            return ident;
        }

        protected void finalize(){
            System.out.println("Finalizing " +ident);
        }

    }

    static class References{
        private  static ReferenceQueue<BigClass> referenceQueue = new ReferenceQueue<BigClass>();

        private static void checkQueue(){
            Reference<? extends BigClass> inQueue = referenceQueue.poll();
            if(inQueue != null){
                System.out.println("in queue:  " +inQueue.get());
            }
        }

        public static void main(String[] args) {
            int size = 16;

            LinkedList<BigClass> bigClasses = new LinkedList<BigClass>(); // Strong Reference


            LinkedList<SoftReference<BigClass>> so = new LinkedList<SoftReference<BigClass>>();
            for (int i=0; i<size; i++){
                so.add(new SoftReference<BigClass>(new BigClass("Soft"+i), referenceQueue));
                System.out.println("Just created:  " + so.getLast());
                checkQueue();
            }


            LinkedList<WeakReference<BigClass>> wo = new LinkedList<WeakReference<BigClass>>();
            for (int i=0; i<size; i++){
                wo.add(new WeakReference<BigClass>(new BigClass("Weak"+i), referenceQueue));
                System.out.println("Just created:  " + wo.getLast());
                checkQueue();
            }

            SoftReference<BigClass> softReference = new SoftReference<BigClass>(new BigClass("Soft"));
            WeakReference<BigClass> weakReference = new WeakReference<BigClass>(new BigClass("Weak"));

            System.gc(); // by JVM 的调度


            LinkedList<PhantomReference<BigClass>> po = new LinkedList<PhantomReference<BigClass>>();
            for (int i=0; i<size; i++){
                po.add(new PhantomReference<BigClass>(new BigClass("Phantom"+i), referenceQueue));
                System.out.println("Just created:  " + po.getLast());
                checkQueue();
            }

        }

    }


}
