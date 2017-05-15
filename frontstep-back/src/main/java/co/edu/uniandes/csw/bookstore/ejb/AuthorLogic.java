package co.edu.uniandes.csw.bookstore.ejb;


import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthorLogic {

    private static final Logger logger = Logger.getLogger(AuthorLogic.class.getName());

    @Inject
    private AuthorPersistence persistence;

    @Inject
    BookLogic bookLogic;

   
    public List<AuthorEntity> getAuthors() {
        logger.info("Inicia proceso de consultar todos los autores");
        List<AuthorEntity> authors = persistence.findAll();
        logger.info("Termina proceso de consultar todos los autores");
        return authors;
    }

 
    public AuthorEntity getAuthor(Long id) {
        logger.log(Level.INFO, "Inicia proceso de consultar autor con id={0}", id);
        AuthorEntity author = persistence.find(id);
        if (author == null) {
            logger.log(Level.SEVERE, "El autor con el id {0} no existe", id);
            throw new IllegalArgumentException("El autor solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar autor con id={0}", id);
        return author;
    }


    public AuthorEntity createAuthor(AuthorEntity entity) {
        logger.info("Inicia proceso de creación de autor");
        persistence.create(entity);
        logger.info("Termina proceso de creación de autor");
        return entity;
    }

  
    public AuthorEntity updateAuthor(Long id, AuthorEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar autor con id={0}", id);
        AuthorEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar autor con id={0}", entity.getId());
        return newEntity;
    }


    public void deleteAuthor(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar autor con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar autor con id={0}", id);
    }

   
    public BookEntity addBook(Long bookId, Long authorId) throws BusinessLogicException {
        bookLogic.addAuthor(authorId, bookId);
        return bookLogic.getBook(bookId);
    }


    public void removeBook(Long bookId, Long authorId) {
        bookLogic.deleteAuthor(authorId, bookId);
    }

   
    public List<BookEntity> replaceBooks(List<BookEntity> books, Long authorId) throws BusinessLogicException {
        AuthorEntity author = getAuthor(authorId);
        for (BookEntity book : author.getBooks()) {
            if (!books.contains(book)) {
                bookLogic.deleteAuthor(book.getId(), authorId);
            }
        }
        for (BookEntity book : books) {
            if (!book.getAuthors().contains(author)) {
                bookLogic.addAuthor(authorId, book.getId());
            }
        }
        author.setBooks(books);
        return author.getBooks();
    }


    public List<BookEntity> getBooks(Long authorId) {
        return getAuthor(authorId).getBooks();
    }

  
    public BookEntity getBook(Long authorId, Long bookId) {
        List<BookEntity> books = getAuthor(authorId).getBooks();
        BookEntity book = bookLogic.getBook(bookId);
        int index = books.indexOf(book);
        if (index >= 0) {
            return books.get(index);
        }
        throw new IllegalArgumentException("El libro no está asociado al autor");
    }
}
