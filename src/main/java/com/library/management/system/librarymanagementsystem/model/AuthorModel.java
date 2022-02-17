package com.library.management.system.librarymanagementsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class AuthorModel {
    
    @Override
	public String toString() {
		return "AuthorModel [authorId=" + authorId + ", authorName=" + authorName + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private Long authorId;

	@Column(name = "author_name", unique = true, nullable = false)
	private String authorName;

    public AuthorModel() {
    }

    public AuthorModel(Long authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    
}
