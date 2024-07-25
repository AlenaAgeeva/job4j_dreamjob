package dreamjob.model;

import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private String description;
    private String creationDate;

    public Candidate(int id, String name, String description, String creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && name.equals(candidate.name)
                && description.equals(candidate.description)
                && creationDate.equals(candidate.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, creationDate);
    }
}
