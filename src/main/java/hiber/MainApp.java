package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

       userService.add(new User("User1", "LastName1", "user1@mail.ru" , new Car(24324, "Toyota")));
       userService.add(new User("User2", "LastName2", "user2@mail.ru" , new Car(243248, "Ferrari")));
       userService.add(new User("User3", "LastName3", "user3@mail.ru" , new Car(2432654, "Заположец")));
       userService.add(new User("User4", "LastName4", "user4@mail.ru" , new Car(864243265, "Москвич")));
       userService.add(new User("User5", "LastName5", "user5@mail.ru" , new Car(264432654, "Лада")));
       List<User> users = userService.listUsers();
       for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
       }
       System.out.println(userService.getUserByModelAndSeries("Toyota", 24324));
       context.close();
  }

}
