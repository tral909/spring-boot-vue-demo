package ru.indorm1992.tony.springbootvuedemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usr")
public class User implements Serializable {
	@Id
	private String id;
	private String name;
	private String userpic;
	private String email;
	private String gender;
	private String locale;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastVisit;
}
