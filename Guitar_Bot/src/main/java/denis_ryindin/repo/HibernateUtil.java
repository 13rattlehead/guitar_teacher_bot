package denis_ryindin.repo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Явная регистрация драйвера PostgreSQL (важно!)
            Class.forName("org.postgresql.Driver");

            // Загружаем конфигурацию Hibernate
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // ищет в resources

            System.out.println("Hibernate configuration успешно загружена.");
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Ошибка инициализации Hibernate: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
