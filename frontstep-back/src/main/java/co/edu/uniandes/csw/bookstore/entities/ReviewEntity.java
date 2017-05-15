package co.edu.uniandes.csw.bookstore.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import uk.co.jemos.podam.annotations.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

/**
 * @generated
 */
@Entity
public class ReviewEntity extends BaseEntity implements Serializable {

    private String source;

    private String description;
    
    @PodamExclude
    
    @ManyToOne
    private BookEntity book;

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

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
     * @return the book
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(BookEntity book) {
        this.book = book;
    }

    
}
