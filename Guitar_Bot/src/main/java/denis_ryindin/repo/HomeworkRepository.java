package denis_ryindin.repo;

import denis_ryindin.model.Homework;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HomeworkRepository {
    public Homework saveOrUpdate(Homework h) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Homework merged = (Homework) session.merge(h);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Homework> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Homework", Homework.class).list();
        }
    }


    public Homework findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Homework.class, id);
        }
    }
    public void delete(Homework h) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(session.contains(h) ? h : session.merge(h));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
