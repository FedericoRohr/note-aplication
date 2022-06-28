package com.example.demo.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter @Setter

public class NoteEntity {
@Id
@GeneratedValue(strategy= GenerationType.SEQUENCE)
private Long id;

private String title;

private String content;

@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
@JoinTable(name = "note_category", joinColumns = @JoinColumn(name = "note_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
private List<CategoryEntity>categories=new ArrayList<>();

@Column(name = "last_edited")
@DateTimeFormat(pattern = "yyyy/MM/dd")
private LocalDate lastEdited;


@ManyToOne(fetch = FetchType.EAGER,cascade= CascadeType.PERSIST) /// buscar mejor tipo de cascada
@JoinColumn(name = "user_id", insertable = false, updatable = false)
private UserExpampleEntity user;

@Column(name = "user_id", nullable = false)
private Long userId;

private boolean archived = Boolean.FALSE;



public void addCategories(CategoryEntity category) {
	categories.add(category);
}

public void removeCategories(CategoryEntity category) {
	categories.remove(category);
}
}
