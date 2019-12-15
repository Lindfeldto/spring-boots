package com.example.springdatajdbcrest.repository.onetomany.set;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.example.springdatajdbcrest.domain.onetomany.set.Person;
import com.example.springdatajdbcrest.domain.onetomany.set.Skill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

/**
 * Sample Tests from official blog
 * https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates
 * 
 * One-to-Many: One Person Has Many Skills,
 * a Skill is not reused between Person(s)
 * the many side will keep reference to the one side.
 * 
 * https://stackoverflow.com/questions/3113885
 */
@DataJdbcTest // or @SpringBootTest
class OneToManySetTests {

    @Autowired
    PersonRepository repository; // parent repository

    @Test
    void createUpdateDeletePerson() {
        // Create
        Person person = Person.of("Foo").addSkill("Java").addSkill("Python").addSkill("Java");
        Person saved = repository.save(person);

        System.out.println("SavedPerson: " + saved);

        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countSkills()).isEqualTo(2);

        // Read
        Optional<Person> found = repository.findById(saved.getId());

        System.out.println("FoundPerson: " + found);

        assertThat(found.get().getId()).isEqualTo(saved.getId());
        assertThat(found.get().getName()).isEqualTo("Foo");

        // Update person name
        Person updating = found.get().withName("FooNew");
        Person updated = repository.save(updating);

        // Note here actually run [DELETE FROM skill WHERE skill.person = ?]

        System.out.println("UpdatedPersonName: " + updated);

        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getName()).isEqualTo("FooNew");

        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countSkills()).isEqualTo(2);

        // Update person skill
        found = repository.findById(saved.getId());

        Set<Skill> skills = new HashSet<Skill>();
        skills.add(Skill.of("Java"));

        updating = found.get().withSkills(skills);
        updated = repository.save(updating);

        System.out.println("UpdatedPersonSkill: " + updated);

        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countSkills()).isEqualTo(1);

        // Delete skills
        found = repository.findById(saved.getId());

        skills = new HashSet<Skill>();

        updating = found.get().withSkills(skills);
        updated = repository.save(updating);

        System.out.println("UpdatedPersonSkill: " + updated);

        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countSkills()).isEqualTo(0);

        // Add skills back to see if it's deleted when person deleted
        found = repository.findById(saved.getId());
        updated = repository.save(found.get().addSkill("Java"));

        System.out.println("UpdatedPersonSkill2: " + updated);

        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countSkills()).isEqualTo(1);

        // Delete person
        repository.delete(saved);

        found = repository.findById(saved.getId());

        System.out.println("FoundPerson: " + found);

        assertThat(found).isEmpty();
        assertThat(repository.count()).isEqualTo(0);
        assertThat(repository.countSkills()).isEqualTo(0);
    }

    @Test
    void clonePerson() {
        // Create
        Person person = Person.of("Foo").addSkill("Java").addSkill("Python").addSkill("Java");
        Person saved = repository.save(person);

        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.countSkills()).isEqualTo(2);

        // Clone
        Person clone = saved.withIdNull();
        Person cloned = repository.save(clone);

        System.out.println("ClonedPerson: " + cloned);

        assertThat(repository.count()).isEqualTo(2);
        assertThat(repository.countSkills()).isEqualTo(4);
    }

}
