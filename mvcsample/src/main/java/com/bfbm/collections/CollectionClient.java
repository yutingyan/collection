package com.bfbm.collections;

import com.bfbm.collections.event.MyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * A customized CollectionClient.
 *
 * @author 巴分巴秒-Eric老师
 * @Date 2019/08/15
 * @since v1.1
 **/
public class CollectionClient {

    private static final String REGULAR_ORDER = "Regular";
    private static final String LEGACY_ORDER = "legacy";

    public static void main(String[] args) {

        BuyerService buyerService = new BuyerService();
        buyerService.selectAllOrders();

//        TreeSet treeSet = new TreeSet(new Comparator<Object>(){
//            public int compare(Object o1, Object o2){
//                return 0;
//            };
//
//        });
    }


    static class BuyerService{ //Client  caller  API User

        public Collection<Order> selectAllOrders(){
            List<Order> orders = new ArrayList<Order>();

            OrderService orderService = new OrderService();
            orders.addAll(orderService.getRegularOrders());

            // call legacy orders
            orders.addAll(orderService.getLegacyOrders());

            // call product hot sale
            orders.addAll(orderService.getHotSaleOrdersByProduct());  // Set


            // iterate elements and modify object status
            Iterator iter = orders.iterator();
            while(iter.hasNext()){
                //遍历的过程当中，修改集合里面的元素之后，为什么不能触发一个事件监听机制？ Event
                Order order =  (Order)iter.next();
                if (order.getId()==1){
                    iter.remove();
                    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                    MyEvent event = new MyEvent("remove event");
                    event.setText("remove"+order.getBuyer()+"@"+order.getId());
                    context.publishEvent(event);
                }else {
                    System.out.println(order.getBuyer()+":"+order.getId());
                }
            }
            // call service
            //StatictisService.calcute(orders);


            return orders;
        }
    }


    static class OrderService{

        public Collection<Order> getRegularOrders(){
            Collection<Order> orders = new ArrayList<Order>();
            orders = generateOrders(REGULAR_ORDER);
            return orders;
        }

        // legacy orders

        public Collection<Order> getLegacyOrders(){
            Collection<Order> orders = new ArrayList<Order>();
            orders = generateOrders(LEGACY_ORDER);
            return orders;
        }

        public Collection<Order> getHotSaleOrdersByProduct(){
            Collection<Order> orders = new HashSet<Order>();
            orders = generateOrders(LEGACY_ORDER);
            return orders;
        }

    }

    static Collection<Order> generateOrders(String orderType){
        List<Order> orders = new ArrayList<Order>();

        Order order;
        for(int i=0; i<10; i++){
            order = new Order(i,"item"+i+orderType,"buyer"+i,"address"+i);
            orders.add(order);
        }

        return orders;
    }



    static class Order{
        private long id;
        private String item;
        private String buyer;
        private String address;
        // ...

        public Order(long id, String item, String buyer, String address) {
            this.id = id;
            this.item = item;
            this.buyer = buyer;
            this.address = address;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getBuyer() {
            return buyer;
        }

        public void setBuyer(String buyer) {
            this.buyer = buyer;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
