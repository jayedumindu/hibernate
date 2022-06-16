import entity.Customer;
import entity.Item;
import entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;


public class Main {

    public static void main(String[] args) {

        // creation

        Item item1 = new Item("I001","Rice",200.00,100);
        Item item2 = new Item("I002","Dhal",450.00,50);

        Customer c1 = new Customer("C001","Pasan","Galle","0771939234");


        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        // crud
//        session.save(item1);
//        session.save(item2);
//        session.save(c1);

        c1.addToCart(item1,2);
        c1.addToCart(item2,5);

        Order order = c1.placeOrder(session);
        c1.getOrders().add(order);

        transaction.commit();

        session.close();

    }
}
