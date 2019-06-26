package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;


    @Override
    @Transactional
    public List<Distillery> findDistilleriesByWhiskiesAged(int age) {
        List<Distillery> distilleries = null;
        Session session = entityManager.unwrap(Session.class);
        try{
            Criteria cr = session.createCriteria(Distillery.class);
            cr.createAlias("whiskies", "whiskiesAlias");
            cr.add(Restrictions.eq("whiskiesAlias.age", age));
            distilleries = cr.list();
        } catch (HibernateError ex){
            ex.printStackTrace();
        } finally {

        }
        return distilleries;
    }
}
