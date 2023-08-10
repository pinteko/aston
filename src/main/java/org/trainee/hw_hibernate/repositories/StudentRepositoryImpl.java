package org.trainee.hw_hibernate.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.trainee.hw_hibernate.entities.Student;

import java.util.List;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Student getStudentById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            String hql = "Select s FROM Student s join fetch s.groups where s.id = :id";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setCacheable(true);
            query.setParameter("id", id);
            Student student = query.uniqueResult();
            tx.commit();
            sessionFactory.getStatistics();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Student> getAllAfter() {
        try (Session session = sessionFactory.openSession()) {
            long start = System.currentTimeMillis();
            Query<Student> query = session.createQuery("SELECT s FROM Student s WHERE s.age > :minAge", Student.class);
            query.setParameter("minAge", 30);
            List<Student> students = query.getResultList();
            System.out.println(System.currentTimeMillis() - start);
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student getStudentByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "Select s FROM Student s join fetch s.groups where s.name = :name";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student";
            Query<Student> query = session.createQuery(hql, Student.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.saveOrUpdate(student);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteStudent(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Student student = session.get(Student.class, id);

            if (student != null) {
                session.delete(student);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
