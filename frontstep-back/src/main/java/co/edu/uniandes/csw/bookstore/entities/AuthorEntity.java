package co.edu.uniandes.csw.bookstore.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.annotations.PodamExclude;

@Entity
public class AuthorEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String description;
    private String image;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @PodamExclude
    private List<AwardEntity> awards = new ArrayList<>();

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the awards
     */
    public List<AwardEntity> getAwards() {
        return awards;
    }

    /**
     * @param awards the awards to set
     */
    public void setAwards(List<AwardEntity> awards) {
        this.awards = awards;
    }
}
