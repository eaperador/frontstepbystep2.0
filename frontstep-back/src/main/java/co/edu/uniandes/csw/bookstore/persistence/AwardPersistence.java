/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AwardPersistence {
    
    private static final Logger logger = Logger.getLogger(AwardPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    public AwardEntity find(Long bookId, Long awardId) {
        TypedQuery<AwardEntity> q = em.createQuery("select p from AwardEntity p where (p.book.id = :bookId) and (p.id = :awardId)", AwardEntity.class);
        q.setParameter("bookId", bookId);
        q.setParameter("awardId", awardId);
        return q.getSingleResult();
    }


    public AwardEntity create(AwardEntity entity) {
        logger.info("Creando un premio nuevo");
        em.persist(entity);
        logger.info("Premio creado");
        return entity;
    }

    public AwardEntity update(AwardEntity entity) {
        logger.log(Level.INFO, "Actualizando premio con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando premio con id={0}", id);
        AwardEntity entity = em.find(AwardEntity.class, id);
        em.remove(entity);
    }
}
