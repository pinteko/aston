package org.trainee.hw_hibernate.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.trainee.hw_hibernate.entities.AbstractCorporation;

import java.util.List;

@RequiredArgsConstructor
public class CorporationRepositoryImpl implements CorporationRepository {

    private final SessionFactory sessionFactory;

    @Override
    public AbstractCorporation getCorporationById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(AbstractCorporation.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AbstractCorporation getCorporationByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "Select c FROM AbstractCorporation c WHERE c.name = :name";
            Query<AbstractCorporation> query = session.createQuery(hql, AbstractCorporation.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AbstractCorporation> getAllCorporations() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM AbstractCorporation";
            Query<AbstractCorporation> query = session.createQuery(hql, AbstractCorporation.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveCorporation(AbstractCorporation corporation) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.saveOrUpdate(corporation);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCorporation(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            AbstractCorporation corporation = session.get(AbstractCorporation.class, id);

            if (corporation != null) {
                session.delete(corporation);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
