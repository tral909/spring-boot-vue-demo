package ru.indorm1992.tony.springbootvuedemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Id.class)
	private Long id;

	@JsonView(Views.IdName.class)
	private String text;

	@Column(updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonView(Views.FullMessage.class)
	private LocalDateTime creationDate;
}
