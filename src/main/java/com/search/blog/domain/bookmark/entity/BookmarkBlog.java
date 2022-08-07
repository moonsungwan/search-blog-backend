package com.search.blog.domain.bookmark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.search.blog.global.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "BOOKMARK_BLOG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkBlog extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
    /* 로그인 ID */
    @Column(name = "LOGIN_ID", nullable = false)
    private String loginId;

    /* 북마크 제목 */
    @Column(name = "BOOKMARK_TITLE", nullable = false)
    private String bookmarkTitle;
    
    /* 북마크 URL */
    @Column(name = "BOOKMARK_URL", nullable = false)
    private String bookmarkUrl;

    @Builder(builderClassName = "Insert", builderMethodName = "Insert")
    public BookmarkBlog(String loginId, String bookmarkTitle, String bookmarkUrl) {
    	this.loginId = loginId;
    	this.bookmarkTitle = bookmarkTitle;
    	this.bookmarkUrl = bookmarkUrl;
    }
    
}
