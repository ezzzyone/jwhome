package com.home.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.home.util.Pager;
import com.home.qna.QnaVO;

@Mapper
public interface QnaMapper {
	
	public int setList(QnaVO qnaVO)throws Exception;
	
	public List<QnaVO> getList(Pager pager)throws Exception;
	
	public Long getListCount(Pager pager)throws Exception;
	
	public int setFileAdd(QnaFileVO fileVO)throws Exception;
	
	public QnaFileVO getFile(QnaFileVO fileVO)throws Exception;
	
	public QnaVO getDetail (QnaVO qnaVO)throws Exception;

}
