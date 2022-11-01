package com.home.qna;

import java.util.List;

import lombok.Data;

@Data
public class QnaVO {
	
	private Long num;
	private String title;
	private String writer;
	private String contents;
	private Long hit;
	private Long ref;
	private Long step;
	private Long depth;

}
