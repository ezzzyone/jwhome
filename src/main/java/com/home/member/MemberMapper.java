package com.home.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Mapper
public interface MemberMapper {
	
	public int setMember(MemberVO memberVO)throws Exception;
	
	public int setRole(MemberVO memberVO)throws Exception;
	
	public MemberVO getLogin(String username)throws UsernameNotFoundException;
	
	public int getIdCheck(MemberVO memberVO)throws Exception;


}
