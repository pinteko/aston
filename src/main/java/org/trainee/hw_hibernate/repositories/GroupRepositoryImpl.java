package org.trainee.hw_hibernate.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.trainee.hw_hibernate.entities.Group;

import java.util.List;

@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Group getGroupById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            String hql = "select g from Group g join fetch g.students where g.id = :id";
            Query<Group> query = session.createQuery(hql, Group.class);
            query.setCacheable(true);
            query.setParameter("id", id);
            Group group = query.uniqueResult();
            tx.commit();
            return group;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Group getGroupByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select g from Group g join fetch g.students where g.title = :title";
            Query<Group> query = session.createQuery(hql, Group.class);
            query.setParameter("title", title);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Group> getAllGroups() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Group g left join fetch g.teacher";
            Query<Group> query = session.createQuery(hql, Group.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveGroup(Group group) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.saveOrUpdate(group);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGroup(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Group group = session.get(Group.class, id);

            if (group != null) {
                session.delete(group);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
