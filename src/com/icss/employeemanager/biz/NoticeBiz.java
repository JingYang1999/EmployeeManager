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
			//(1)������������
			int pageSize=2,firstCount=(pageInfo.getCurrentPage()-1)*pageSize;//ÿҳչʾ������,��ʼ����
			//��2����ȡ��ǰҳչʾ������--�������ݲ㷽��
			ArrayList<NoticeEntity> notices=dao.findAllNotice(pageSize,firstCount);
			pageInfo.setLists(notices);
			//(3)��ȡ������--�������ݲ㷽��
			int totalCount=dao.getNoticeCount();
			pageInfo.setTotalCount(totalCount);
			//(4)������ҳ�� 
			int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			pageInfo.setTotalPage(totalPage);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//��5���ر�����
			dao.closeConnection();
		}
	}
	public void delNotice(String[] ids) {
		// TODO Auto-generated method stub
		try {
			dao.delNotice(ids);//����dao��ķ�����
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
	//����Ԥ��
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
