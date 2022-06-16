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

        c1.addToCart(item1,2);
        c1.addToCart(item2,5);




        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        // crud

        transaction.commit();

        session.close();

    }
}
