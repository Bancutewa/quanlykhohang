package inventory.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BaseDAOImpl<E> implements BaseDAO<E> {
    final static Logger log = Logger.getLogger(BaseDAOImpl.class);
    @Autowired
    SessionFactory sessionFactory;
    public List<E> findAll(String queryStr, Map<String, Object> mapParams)  {
        log.info("find all records from db");
        StringBuilder queryString = new StringBuilder();
        queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag=1");
        if (queryStr!=null && !queryStr.isEmpty()) {
            queryString.append(queryStr);
        }
        Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
        if (mapParams != null && !mapParams.isEmpty()) {
            for(String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));
            }
        }

        log.info("Query find all -->" + queryString.toString());
        return query.list();
    }

    public E findById(Class<E> e, Serializable id) {
        log.info("find record by id " + id);
        return sessionFactory.getCurrentSession().get(e, id);

    }


    public List<E> findByProperty(String property, Object value) {
        log.info("find record by property " + property + " value " + value);
        StringBuilder queryString = new StringBuilder();
        queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag=1 and model.").append(property).append("=?");
        log.info("Query find by property -->" + queryString.toString());
        Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
        query.setParameter(0, value);
        return query.getResultList();
    }


    public void save(E instance) {
        log.info("save instance" + instance);
        sessionFactory.getCurrentSession().persist(instance);
    }


    public void update(E instance) {
        log.info("update instance" + instance);
        sessionFactory.getCurrentSession().merge(instance);
    }


    public void delete(E instance) {
        log.info("delete instance" + instance);
        sessionFactory.getCurrentSession().delete(instance);
    }

    //
    public String getGenericName() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> genericClass = (Class<?>) genericSuperclass.getActualTypeArguments()[0];
        return genericClass.getSimpleName();
    }

}
