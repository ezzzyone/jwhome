package com.home.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import com.home.qna.QnaFileVO;
import com.home.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManager extends AbstractView{
	// 상속 꼭!
	
	@Value("${file.download.path}")
	private String base;
	   @Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		   
		   String path = (String)model.get("path");
		   
		   QnaFileVO qnafilevo = (QnaFileVO)model.get("vo");
		   
		log.info("QNAFILEVO {}", qnafilevo);
		
		File file = new File(base+path, qnafilevo.getFileName());
				
		//한글 처리
		 response.setCharacterEncoding("UTF-8");
		 
		 //총 파일의 크기
		 response.setContentLengthLong(file.length());
		 
		 //다운로드시 파일의 이름을 인코딩
		 String oriName = URLEncoder.encode(qnafilevo.getOriName(), "UTF-8");
		 
		 //header 설정
		 response.setHeader("Content-Disposition", "attachment;filename=\""+oriName+"\"");
		 response.setHeader("Content-Transfer-Encoding", "binary");
		 
		 //HDD에서 파일을 읽고
		 FileInputStream fi = new FileInputStream(file);
		 //Client 로 전송 준비
		 OutputStream os = response.getOutputStream();
		 
		 //전송
		 FileCopyUtils.copy(fi, os);
		 
		 //자원 해제
		 os.close();
		 fi.close();
	   }

	public String saveFile(MultipartFile mf, String path)throws Exception {
		      
		      //2. 중복되지 않는 파일명 생성 (UUID, Date)
		      String fileName = UUID.randomUUID().toString();
		      
		      //3. 확장자
		      StringBuffer bf = new StringBuffer();
		      bf.append(fileName);
		      bf.append("_");
		      
		      //파일명과 확장자를 분리하는 작업
		      
		      String ex = mf.getOriginalFilename();
		      ex = ex.substring(ex.lastIndexOf("."));
		      bf.append(ex);
		      //bf.append(mf.getOriginalFilename());
		      
		     //URLEncoder.encode(mf.getOriginalFilename(), "UTF-8");
		      log.info("fileName : {} ", bf.toString());
		      
		      fileName = bf.toString();
		      
		      File file = new File(path, bf.toString());
		     //FileCopyUtils.copy(mf.getBytes(), file); 밑에꺼랑 둘다 됨
		     mf.transferTo(file);
		      
		      
		      return fileName;
		   }

}
