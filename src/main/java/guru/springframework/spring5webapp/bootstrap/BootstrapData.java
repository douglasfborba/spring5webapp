package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        Publisher addisonWesley = new Publisher("Addison-Wesley Professional");
        Address addisonWesleyAddress = new Address("Boston", "MA", "02116");

        publisherRepository.save(addisonWesley);

        addisonWesley.setAddress(addisonWesleyAddress);
        addisonWesley.getBooks().add(ddd);
        ddd.setPublisher(addisonWesley);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(addisonWesley);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "3939459459");
        Book profJavDev = new Book("Professional Java Development with Spring", "25369874");

        rod.getBooks().add(noEjb);
        rod.getBooks().add(profJavDev);
        noEjb.getAuthors().add(rod);
        profJavDev.getAuthors().add(rod);

        Publisher wroxPress = new Publisher("Wrox Press");
        Address wroxPressAddress = new Address("Springfield", "IL", "62703");

        publisherRepository.save(wroxPress);

        wroxPress.setAddress(wroxPressAddress);
        wroxPress.getBooks().add(noEjb);
        wroxPress.getBooks().add(profJavDev);
        noEjb.setPublisher(wroxPress);
        profJavDev.setPublisher(wroxPress);

        authorRepository.save(rod);
        bookRepository.save(noEjb);
        bookRepository.save(profJavDev);
        publisherRepository.save(wroxPress);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
    }
}
