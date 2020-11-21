package co.com.poli.backlog.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tbl_backlogs")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El id lo genera automaticamente dependiendo del motor de base de datos
    private Long id;

    @NotBlank(message = "El project identifier es requerido")
    @Column(name = "project_identifier")
    private String projectIdentifier;


    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL) // el cascade es para cuando se modifica se modifica en todas las tablas , como es una relacion el fetch LAZY  es perezoso hasta que no obtenga el project no viene
    @JoinColumn(name = "project_task_id") // Para relacionar con la otra tabla
    private Project project;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "project_task_id")
    private List<ProjectTask> projectTask;


    public Backlog() {
    }

    public Backlog(Long id, @NotEmpty(message = "El project identifier es requerido") String projectIdentifier, Project project, List<ProjectTask> projectTask) {
        this.id = id;
        this.projectIdentifier = projectIdentifier;
        this.project = project;
        this.projectTask = projectTask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProjectTask> getProjectTask() {
        return projectTask;
    }

    public void setProjectTask(List<ProjectTask> projectTask) {
        this.projectTask = projectTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Backlog backlog = (Backlog) o;
        return id.equals(backlog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
