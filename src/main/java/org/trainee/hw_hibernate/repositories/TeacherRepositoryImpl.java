package org.trainee.hw_hibernate.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.trainee.hw_hibernate.entities.Teacher;

import java.util.List;

@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Teacher getTeacherById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Teacher.class, id);
//            String hql = "FROM Teacher t WHERE t.id = :id";
//            Query<Teacher> query = session.createQuery(hql, Teacher.class);
//            query.setParameter("id", id);
//            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Teacher getTeacherByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Teacher WHERE name = :name";
            Query<Teacher> query = session.createQuery(hql, Teacher.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Teacher t left join fetch t.groups";
            Query<Teacher> query = session.createQuery(hql, Teacher.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.saveOrUpdate(teacher);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Teacher teacher = session.get(Teacher.class, id);

            if (teacher != null) {
                session.delete(teacher);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
