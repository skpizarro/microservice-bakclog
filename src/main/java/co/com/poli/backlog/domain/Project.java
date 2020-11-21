package co.com.poli.backlog.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name" , unique = true)
    @NotEmpty(message = "El project name es requerido")
    private String projectName;

    @Column(name = "project_identifier", unique = true, updatable = false)
    @NotBlank(message = "El project identifier es requerido")
    @Size(min = 5,max = 7,message = "El project identifer debe estar entre 5 y 7 caracteres")
    private String projectIdentifier;

    @Column(name = "description")
    @NotBlank(message = "La descripción es requerida")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endtDate;

    @JoinColumn(name = "backlog_id") //
    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    private Backlog backlog;


    public Project() {
    }

    public Project(Long id, @NotEmpty(message = "El project name es requerido") String projectName, @NotBlank(message = "El project identifier es requerido") @Size(min = 5, max = 7, message = "El project identifer debe estar entre 5 y 7 caracteres") String projectIdentifier, @NotBlank(message = "La descripción es requerida") String description, Date startDate, Date endtDate, Backlog backlog) {
        this.id = id;
        this.projectName = projectName;
        this.projectIdentifier = projectIdentifier;
        this.description = description;
        this.startDate = startDate;
        this.endtDate = endtDate;
        this.backlog = backlog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndtDate() {
        return endtDate;
    }

    public void setEndtDate(Date endtDate) {
        this.endtDate = endtDate;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id.equals(project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
