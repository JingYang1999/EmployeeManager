package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.NoticeDao;
import com.icss.employeemanager.entity.NoticeEntity;
import com.icss.employeemanager.utils.ResultPage;

public class NoticeBiz {
	NoticeDao dao=new NoticeDao();
	public void findAllNotice(ResultPage<NoticeEntity> pageInfo) {
		// TODO Auto-generated method stub
		try {
			//(1)定义两个变量
			int pageSize=2,firstCount=(pageInfo.getCurrentPage()-1)*pageSize;//每页展示的条数,起始条数
			//（2）获取当前页展示的数据--调用数据层方法
			ArrayList<NoticeEntity> notices=dao.findAllNotice(pageSize,firstCount);
			pageInfo.setLists(notices);
			//(3)获取总条数--调用数据层方法
			int totalCount=dao.getNoticeCount();
			pageInfo.setTotalCount(totalCount);
			//(4)计算总页数 
			int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			pageInfo.setTotalPage(totalPage);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//（5）关闭连接
			dao.closeConnection();
		}
	}
	public void delNotice(String[] ids) {
		// TODO Auto-generated method stub
		try {
			dao.delNotice(ids);//调用dao层的方法；
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
	}
	public ArrayList<NoticeEntity> searchNotice(String empname,String noticename, String noticecontent) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<NoticeEntity> notices=dao.searchNotice(empname,noticename,noticecontent);
		dao.closeConnection();
		return notices;
	}
	public boolean insertNotice(String noticename, String noticecontent, int empid) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			res = dao.insertNotice(noticename,noticecontent,empid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
		return res>0?true:false;
	}
	public void updateNotice(String noticename, String noticecontent, int empid, int noticeid) {
		// TODO Auto-generated method stub
		try {
			dao.updateNotice(noticename,noticecontent,empid,noticeid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
	}
	//公告预览
	public NoticeEntity findPreviewNotice(String noticeid) {
		// TODO Auto-generated method stub
		NoticeEntity notice=null;
		try {
			notice = dao.findPreviewNotice(noticeid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
		return notice;
	}

}
