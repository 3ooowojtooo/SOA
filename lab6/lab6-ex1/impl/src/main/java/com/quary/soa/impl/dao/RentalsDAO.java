package com.quary.soa.impl.dao;

import com.quary.soa.api.dao.IRentalsDAO;
import com.quary.soa.api.dao.IRentalsDAORemote;
import com.quary.soa.api.entity.Rental;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Singleton
@Remote(IRentalsDAORemote.class)
public class RentalsDAO extends BaseDAO<Rental> implements IRentalsDAO {

    @PersistenceContext(unitName = "lab6ex1")
    private EntityManager entityManager;

    public RentalsDAO() {
        super(Rental.class);
    }

    @Override
    protected int getId(Rental entity) {
        return entity.getId();
    }

    @Override
    public List<Rental> findWithFilters(Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Rental> criteriaQuery = criteriaBuilder.createQuery(Rental.class);
        Root<Rental> root = criteriaQuery.from(Rental.class);
        if (filters != null && !filters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach((key, value) -> {
                Predicate predicate = buildPredicate(root, criteriaBuilder, key, value);
                if (predicate != null) {
                    predicates.add(predicate);
                }
            });
            criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        }

        TypedQuery<Rental> query = getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Predicate buildPredicate(Root<Rental> root, CriteriaBuilder builder, String key, Object value) {
        if (key.equals("rentalId")) {
            return builder.equal(root.get("id"), value);
        } else if(key.equals("bookId")) {
            return builder.equal(root.get("book").get("id"), value);
        } else if(key.equals("bookTitle")) {
            return builder.equal(root.get("book").get("title"), value.toString());
        } else if(key.equals("authorFirstName")) {
            return builder.equal(root.get("book").get("author").get("firstName"), value.toString());
        } else if(key.equals("authorLastName")) {
            return builder.equal(root.get("book").get("author").get("lastName"), value.toString());
        } else if(key.equals("readerId")) {
            return builder.equal(root.get("reader").get("id"), value);
        } else if(key.equals("readerFirstName")) {
            return builder.equal(root.get("reader").get("firstName"), value.toString());
        } else if(key.equals("readerLastName")) {
            return builder.equal(root.get("reader").get("lastName"), value.toString());
        } else if(key.equals("rentalStartDate")) {
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
            } catch (ParseException ignored) {
                return null;
            }
            return builder.equal(root.get("startDate"), date);
        } else if(key.equals("rentalEndDate")) {
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
            } catch (ParseException ignored) {
                return null;
            }
            return builder.equal(root.get("startDate"), date);
        } else {
            return builder.like(root.get(key), "%" + value.toString() + "%");
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
