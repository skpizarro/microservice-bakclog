package co.com.poli.backlog.domain;

import co.com.poli.backlog.model.ProjectTaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tbl_project_tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectTask {

    @Id
    @NotEmpty(message = "El Id es requerido")
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
    @Column(name = "start_date")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endtDate;

    @Column(name = "project_identifier", updatable = false)
    private ProjectTaskStatus projectIdentifier;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;

}
