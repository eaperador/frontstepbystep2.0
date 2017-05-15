/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bookstore.entities;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.annotations.PodamExclude;


@Entity
public class AwardEntity extends BaseEntity implements Serializable {
    
    private String description;
    
    @Temporal(TemporalType.DATE)

    private Date awardyear;

    @ManyToOne
    @PodamExclude
    private AuthorEntity author;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the awardyear
     */
    public Date getAwardyear() {
        return awardyear;
    }

    /**
     * @param awardyear the awardyear to set
     */
    public void setAwardyear(Date awardyear) {
        this.awardyear = awardyear;
    }

    /**
     * @return the author
     */
    public AuthorEntity getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

} 