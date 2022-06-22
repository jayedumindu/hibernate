import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        // creation


        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        // crud


        transaction.commit();

        session.close();

    }
}
