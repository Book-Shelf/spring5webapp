package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author malcolm = new Author("Malcolm", "Rolski");
        Book dad = new Book("Dad all Dead", "123321");
        Publisher cant = new Publisher();
        cant.setName("Cantin");
        cant.setAddressLine("Lolita");
        cant.setCity("krakow");
        cant.setState("Malopolska");
        cant.setZip("3444");

        publisherRepository.save(cant);

        malcolm.getBooks().add(dad);
        dad.getAuthors().add(malcolm);
        dad.setPublisher(cant);
        cant.getBooks().add(dad);

        authorRepository.save(malcolm);
        bookRepository.save(dad);
        publisherRepository.save(cant);

        Author rod = new Author("Rodrigo", "Marines");
        Book gog = new Book("Go Oranges Gradle", "753");

        rod.getBooks().add(gog);
        gog.getAuthors().add(rod);
        gog.setPublisher(cant);
        cant.getBooks().add(gog);

        authorRepository.save(rod);
        bookRepository.save(gog);
        publisherRepository.save(cant);

        System.out.println("Bootstrap started!");
        System.out.println("Numbers of books assign to a cant publisher: " + cant.getBooks().size());
    }
}
