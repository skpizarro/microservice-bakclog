package co.com.poli.backlog.domain;

import co.com.poli.backlog.model.ProjectTaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_project_tasks")
public class ProjectTask {

    @Id
    @NotBlank(message = "El Id es requerido")
    private Long id;

    @NotBlank(message = "El name es requerido")
    private String name;

    @NotBlank(message = "El summary es requerido")
    private String summary;

    @Column(name = "acceptence_criteria")
    private String acceptanceCriteria;

    // Validar enum
    @Enumerated(EnumType.STRING)
    private ProjectTaskStatus status;

    @Size(min = 1,max = 5,message = "La prioridad debe ser de 1 a 5")
    private int priority;

    @Positive(message = "Las horas deben de ser positivas")
    @Size(min = 1,max = 8,message = "Las horas deben ser de 1 a 8")
    private Double hours;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endtDate;

    @Column(name = "project_identifier", updatable = false)
    private ProjectTaskStatus projectIdentifier;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;

    public ProjectTask() {
    }

    public ProjectTask(@NotBlank(message = "El Id es requerido") Long id, @NotBlank(message = "El name es requerido") String name, @NotBlank(message = "El summary es requerido") String summary, String acceptanceCriteria, ProjectTaskStatus status, @Size(min = 1, max = 5, message = "La prioridad debe ser de 1 a 5") int priority, @Positive(message = "Las horas deben de ser positivas") @Size(min = 1, max = 8, message = "Las horas deben ser de 1 a 8") Double hours, Date startDate, Date endtDate, ProjectTaskStatus projectIdentifier, Backlog backlog) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = status;
        this.priority = priority;
        this.hours = hours;
        this.startDate = startDate;
        this.endtDate = endtDate;
        this.projectIdentifier = projectIdentifier;
        this.backlog = backlog;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTask that = (ProjectTask) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
