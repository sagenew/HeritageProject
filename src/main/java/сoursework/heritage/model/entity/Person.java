package сoursework.heritage.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "people")
public class Person {

    public Person() { }
    public Person(Builder builder) {
        Preconditions.checkNotNull(builder.name, "name");
        Preconditions.checkNotEmpty(builder.name, "name");
        this.name = builder.name;
        this.sex = builder.sex;
        this.birthDate = builder.birthDate;
        this.deathDate = builder.deathDate;
        this.biography = builder.biography;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String name;

    public enum Sex {male, female}

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Sex sex;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "deathdate")
    private Date deathDate;

    @Column(name = "biography")
    private String biography;

    @JoinTable(name = "parent_child",
            joinColumns = {
            @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "child_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Person> parents;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "parents")
    @JsonBackReference
    private List<Person> children;

    @JoinTable(name = "spouses",
            joinColumns = {
            @JoinColumn(name = "spouse1_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "spouse2_id", referencedColumnName = "id", nullable = false)
    })
    @OneToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Person spouse;

    @JoinTable(name = "cousins",
            joinColumns = {
                    @JoinColumn(name = "cousin1_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "cousin2_id", referencedColumnName = "id", nullable = false)
            })
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Person> cousins;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public List<Person> getCousins() {
        return cousins;
    }

    public void setCousins(List<Person> cousins) {
        this.cousins = cousins;
    }

    public static class Builder {
        private String name;
        private Sex sex;
        private Date birthDate;
        private Date deathDate;
        private String biography;

        public Builder setName(String name) { this.name = name; return this; }

        public Builder setSex(Sex sex) { this.sex = sex; return this; }

        public Builder setBirthDate(Date birthDate) { this.birthDate = birthDate; return this; }

        public Builder setDeathDate(Date deathDate) { this.deathDate = deathDate; return this; }

        public Builder setBiography(String biography) { this.biography = biography; return this; }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return id + " " + name + " " + sex + " " + birthDate + " " + deathDate + " " + biography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) &&
                name.equals(person.name) &&
                sex == person.sex &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(deathDate, person.deathDate) &&
                Objects.equals(biography, person.biography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, birthDate);
    }
}
