package io.github.truongbn.mariadb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "celebrity")
public class Celebrity {
    @Id
    private int celebrityId;
    @Min(18)
    @Max(65)
    private int age;
    private String name;
}
