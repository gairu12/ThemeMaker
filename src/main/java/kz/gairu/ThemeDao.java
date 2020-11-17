package kz.gairu;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ThemeDao {
    public void initBase() { //todo: проверка не заполнена ли база уже
        try {
            List<String> words = Files.readAllLines(new File("Word.txt").toPath());

            for (String w : words) {
                Theme theme = new Theme();
                theme.setValue(w);
                save(theme);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Theme findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Theme.class, id);
    }

    public void save(Theme theme) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(theme);
        tx1.commit();
        session.close();
    }

    public void update(Theme theme) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(theme);
        tx1.commit();
        session.close();
    }

    public void delete(Theme theme) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(theme);
        tx1.commit();
        session.close();
    }

    public List<Theme> findAll() {
        List<Theme> users = (List<Theme>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Theme").list();
        return users;
    }
}
