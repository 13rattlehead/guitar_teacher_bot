package denis_ryindin.repo;

import denis_ryindin.model.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LessonRepository {
    public Lesson saveOrUpdate(Lesson l) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Lesson merged = (Lesson) session.merge(l);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Lesson> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Lesson", Lesson.class).list();
        }
    }


    public Lesson findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Lesson.class, id);
        }
    }
    public void delete(Lesson l) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(session.contains(l) ? l : session.merge(l));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
