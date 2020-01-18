package ru.indorm1992.tony.springbootvuedemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usr")
public class User {
	@Id
	private String id;
	private String name;
	private String userpic;
	private String email;
	private String gender;
	private String locale;
	private LocalDateTime lastVisit;
}
