package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   @Autowired
   private HibernateTransactionManager hibernateTransactionManager;

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM  User");
      return query.getResultList();
   }

   @Override
   public User getUserByModelAndSeries(String brand, int series) {
      User user = null;

      try {
         Session session = sessionFactory.getCurrentSession();
         String HQL = "SELECT user FROM User user WHERE user.car.brand = :brand and user.car.series = :series";
         user = session.createQuery(HQL, User.class)
                 .setParameter("brand", brand)
                 .setParameter("series", series)
                 .uniqueResult();
         if (user == null) {
            System.out.println("User don't exist");
         }
      } catch (NonUniqueResultException e) {
         System.err.println("It is impossible to get user data, as there are several users with a machine of this model and series.");
      } catch (HibernateException e) {
         System.err.println("Error in getting the user by the series and model of the machine.");
         e.printStackTrace();
      }
      return user;
   }
}
