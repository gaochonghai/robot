package com.movie.springboot.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "result" )
public class Result {
    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
    private int id;

    /** input data */
    @Column(name="input")
    private String input;

    /** output data */
    @Column(name="output")
    private String output;


    public Result() {
    }

    public Result(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
