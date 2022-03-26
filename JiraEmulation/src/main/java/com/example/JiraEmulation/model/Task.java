package com.example.JiraEmulation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "task_list")
//@SequenceGenerator(name = "userIdGenerator", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "task_category")
    private String taskCategory;

    @Column(name = "priority")
    private int priority;

    @Column(name = "start_task")
    private Date startTask;

    @Column(name = "deadline")
    private Date deadline ;
}