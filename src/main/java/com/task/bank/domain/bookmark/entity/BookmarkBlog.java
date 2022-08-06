package com.task.bank.domain.bookmark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.task.bank.domain.bookmark.controller.request.BookmarkBlogInsertRequest;
import com.task.bank.global.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@IdClass(BookmarkBlogPK.class)
@Table(name = "BOOKMARK_BLOG")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkBlog extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
    /* 로그인 ID */
	@Id
    @Column(name = "LOGIN_ID", nullable = false)
    private String loginId;

    /* 북마크 제목 */
    @Column(name = "BOOKMARK_TITLE", nullable = false)
    private String bookmarkTitle;
    
    /* 북마크 URL */
    @Column(name = "BOOKMARK_URL", nullable = false)
    private String bookmarkUrl;

    @Builder(builderClassName = "Insert", builderMethodName = "Insert")
    public BookmarkBlog(BookmarkBlogInsertRequest bookmarkBlogInsertRequest) {
    	this.loginId = bookmarkBlogInsertRequest.getLoginId();
    	this.bookmarkTitle = bookmarkBlogInsertRequest.getBookmarkTitle();
    	this.bookmarkUrl = bookmarkBlogInsertRequest.getBookmarkUrl();
    }
    
}
