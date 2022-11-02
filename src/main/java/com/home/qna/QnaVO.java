package com.home.qna;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class QnaVO {
	
	private Long num;
	
	@NotBlank
	private String title;
	@NotBlank
	private String writer;
	@NotBlank
	private String contents;
	private Long hit;
	private Long ref;
	private Long step;
	private Long depth;
	private List<QnaFileVO> fileVOs;
	private MultipartFile [] files;
}
