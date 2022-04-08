package com.reyidev.todoapp.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createDate; // Fecha de inicio
    private LocalDateTime eta;        // Fecha estimada de terminación
    private boolean finished;         // Finalizado (Sí/No)
    private TaskStatus taskStatus;    // Estado (ON TIME, LATE)
}
