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
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("FROM  User", User.class).getResultList();
   }

   @Override
   public User getUserByModelAndSeries(String brand, int series) {
      return sessionFactory.getCurrentSession().createQuery("SELECT user FROM User user WHERE user.car.brand = :brand and user.car.series = :series", User.class)
              .setParameter("brand", brand)
              .setParameter("series", series)
              .uniqueResult();
   }
}
