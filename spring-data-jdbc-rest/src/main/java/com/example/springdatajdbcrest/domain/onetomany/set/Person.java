package com.example.springdatajdbcrest.domain.onetomany.set;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;

import lombok.Value;
import lombok.With;

@Value
@With
public class Person { // One to Many Parent

    private final @Id Long id;
    private final String name;
    private final Set<Skill> skills;

    public static Person of(String name, Set<Skill> skills) {
        return new Person(null, name, unmodifiableSet(skills));
    }

    public static Person of(String name) {
        return new Person(null, name, unmodifiableSet(new HashSet<Skill>()));
    }

    public Person addSkill(String skillName) {
        // clone the Set to make the new instance immutable
        Set<Skill> skills_ = new HashSet<Skill>();
        skills_.addAll(skills);

        skills_.add(Skill.of(skillName));
        return this.withSkills(skills_);
    }

    public Person withIdNull() {
        return this.withId(null).withSkills(getSkillsWithNullId());
    }

    private Set<Skill> getSkillsWithNullId() {
        return skills.stream().map(skill -> skill.withId(null))
            .collect(Collectors.toSet());
    }

}
