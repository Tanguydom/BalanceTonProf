package fr.efrei.balancetonprof.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class EcoleSessionBean {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projet_java_avance");
    EntityManager em = entityManagerFactory.createEntityManager();
    public EcoleEntity getSchoolById(int id){
        return em.find(EcoleEntity.class, id);
    }
    public List<EcoleEntity> getListSchool(){
        Query q = em.createQuery("select e from EcoleEntity e");
        return q.getResultList();
    }
}
