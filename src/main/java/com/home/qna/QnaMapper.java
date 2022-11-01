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

}
