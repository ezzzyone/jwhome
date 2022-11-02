package com.home.qna;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.home.util.FileManager;
import com.home.util.Pager;
import com.home.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	
	@Value("${app.upload.qna}")
	private String path;
	
	@Autowired
	private FileManager fileManager;
	
	public int setList(QnaVO qnaVO)throws Exception{
		
	    int result = qnaMapper.setList(qnaVO);

		//String realPath = session.getServletContext().getRealPath("/static/upload/qna");
		
		log.info("Path : {} "+path);
		
		File file = new File(path);
		if(!file.exists()){
			boolean check = file.mkdirs();
			log.info("check : {}"+check);
		}
		

		for(MultipartFile mf : qnaVO.getFiles()) {
//			if(mf.isEmpty()) {
//				log.info("------------- Exception 발생-----------");
//				throw new Exception();
//			}
		      
		      
	
		         if(!mf.isEmpty()) {
		            log.info("FileName : {} ", mf.getOriginalFilename());
		            String fileName = fileManager.saveFile(mf, path);
		            QnaFileVO qnaFileVO = new QnaFileVO();
		            qnaFileVO.setFileName(fileName);
		            qnaFileVO.setOriName(mf.getOriginalFilename());
		            qnaFileVO.setNum(qnaVO.getNum());
		            qnaMapper.setFileAdd(qnaFileVO);
		         }
		      
		}
		      return result;
		  
	}
	
	//사용자 정의 검증 메서드
	   public boolean getQnaError(QnaVO qnaVO, BindingResult bindingResult) throws Exception{
	      
	      boolean check = false;
	      //check = false : 검증성공(error 없음)
	      //check = true : 검증실패(error 있음)
	      
	      //1. Annotation검증
	      check = bindingResult.hasErrors();
	      
	      return check;
	   }
	
}
