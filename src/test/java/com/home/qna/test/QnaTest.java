package com.home.qna.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.qna.QnaMapper;
import com.home.qna.QnaVO;
@SpringBootTest
class QnaTest {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Test
	void test() throws Exception{
		QnaVO qnaVO = new QnaVO();
		for(int i=0; i<50; i++) {
			qnaVO.setWriter("jw"+i);
			qnaVO.setTitle("title");
			qnaVO.setContents("contents");
			int result = qnaMapper.setList(qnaVO);
			String rs = String.valueOf(result);
		}
	}

}
