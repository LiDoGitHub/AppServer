package com.gjyl.appserver.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjyl.appserver.dao.AppUserMapper;
import com.gjyl.appserver.dao.CommentMapper;
import com.gjyl.appserver.dao.DoctorMapper;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.Comment;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper;
	@Resource
	private DoctorMapper doctorMapper;
	@Resource
	private AppUserMapper appUserMapper;

	/**
	 * 获取用户对医生的评论
	 */
	public List<Comment> getUserComment(String docId){
		//组装数据
		List<Comment> list = commentMapper.getUserComment(docId);
		for (Comment comment : list) {
			Doctor doctor = doctorMapper.getDrInfo(comment.getDoctorid());
			comment.setDoctor(doctor);
			AppUser user = appUserMapper.getUserById(comment.getUserid());
			comment.setAppUser(user);
			System.out.println("................\n"+comment);
		}
		
		return list;
	}

	public Boolean addComment(Comment comment) {
		int result=commentMapper.addComment(comment);
		if (result>0) {
			return true;
		}
		return false;
	}
}
