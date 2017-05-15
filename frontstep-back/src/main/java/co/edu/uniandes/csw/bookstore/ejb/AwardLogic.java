/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.ejb;


import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.AwardEntity;
import co.edu.uniandes.csw.bookstore.persistence.AwardPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author rcasalla
 */
public class AwardLogic  {

    private static final Logger logger = Logger.getLogger(AwardLogic.class.getName());
    @Inject
    private AuthorLogic authorLogic;

    @Inject
    private AwardPersistence awardPersistence;

    
    public List<AwardEntity> getAwards(Long authorId) {
        AuthorEntity author = authorLogic.getAuthor(authorId);
        return author.getAwards();
    }

   
    public AwardEntity getAward(Long authorId, Long awardId) {
        try {
            return awardPersistence.find(authorId, awardId);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El premio no existe");
        }
    }

  
    public AwardEntity createAward(Long authorId, AwardEntity award) {
        AuthorEntity author = authorLogic.getAuthor(authorId);
        award.setAuthor(author);
        award = awardPersistence.create(award);
        return award;
    }



    public void deleteAward(Long authorId, Long awardId) {
        AwardEntity old = getAward(authorId, awardId);
        awardPersistence.delete(old.getId());
    }
}