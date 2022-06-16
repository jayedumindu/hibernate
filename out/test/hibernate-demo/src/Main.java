import entity.Customer;
import entity.Laptop;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer("002","Kasun","Mathara",30000.00);

        Laptop laptop1 = new Laptop("L001","Asus",customer);
        Laptop laptop2 = new Laptop("L002","Dell",customer);

        ArrayList<Laptop> lapList = new ArrayList();
        lapList.add(laptop1);
        lapList.add(laptop2);

        customer.setLaptops(lapList);

        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.save(laptop1);
        session.save(laptop2);
        session.save(customer);

        transaction.commit();

        session.close();

    }
}
