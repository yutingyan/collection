package com.bfbm.collections;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * A customized BlockingQueueSample.
 *
 * @author 巴分巴秒-Eric老师
 * @Date 2019/08/15
 * @since v1.1
 **/
public class BlockingQueueSample {

    public static void main(String[] args) {
        BlockingQueue q = new ArrayBlockingQueue(16);

        Producer p = new Producer(q);
        new Thread(p).start();

        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        Consumer c3 = new Consumer(q);
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();

    }

    static class Producer implements  Runnable{
        private final BlockingQueue queue;

        Producer(BlockingQueue q) {
            queue = q; }

        public void run() {
            try {
                while (true) {
                    queue.put(produce());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        public Object produce() {
            // FIXME: get from database or generate the UUID
            FixedBond fixedBond = new FixedBond(1000l, "fixedBond", new Date(), new Date());
            return fixedBond;
        }
    }


    static class Consumer implements  Runnable{
        private final BlockingQueue queue;

        Consumer(BlockingQueue q) { queue = q; }

        public void run() {
            try {
                while (true) {
                    consume(queue.take());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        public void consume(Object o) {
            System.out.println(o.toString());
        }

    }



    // BaseBankObject
    static class BaseBankObject{
        private Long id;
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }


    // FixedBondKey Definition
    static class FixedBondKey{

        private Long id;
        private String name;

        // 4~10

        public FixedBondKey(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FixedBondKey)) return false;
            FixedBondKey that = (FixedBondKey) o;
            return id.equals(that.id) &&
                    name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }


    // FixedBond
    static class FixedBond extends BaseBankObject{
        private Long id;
        private String name;
        private Date startDate;
        private Date   endDate;

        // ... > 100 attributes


        public FixedBond(Long id, String name, Date startDate, Date endDate) {
            this.id = id;
            this.name = name;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FixedBond)) return false;
            FixedBond fixedBond = (FixedBond) o;
            return id.equals(fixedBond.id) &&
                    name.equals(fixedBond.name) &&
                    startDate.equals(fixedBond.startDate) &&
                    endDate.equals(fixedBond.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, startDate, endDate);
        }

        @Override
        public String toString() {
            return "FixedBond{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }

}
