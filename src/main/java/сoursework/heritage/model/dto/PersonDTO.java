package сoursework.heritage.model.dto;

import org.springframework.format.annotation.DateTimeFormat;
import сoursework.heritage.model.entity.Person;

import java.util.Date;

public class PersonDTO {

    private String name;

    private Person.Sex sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deathDate;

    private String biography;

    public String getName() {
        return name;
    }

    public Person.Sex getSex() {
        return sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Person.Sex sex) {
        this.sex = sex;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
