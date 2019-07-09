package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.DeptDao;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.utils.ResultPage;

/**
*������ص�ҵ���߼���
*@author Giselle
*/
public class DeptBiz {
    DeptDao dao = new DeptDao();  //�������ݲ����
	//��ҳ�鿴���в�����Ϣ
	public void findAllDept(ResultPage<DeptEntity> pageInfo) {
		try {
//			(1)��������������pageSize=2��first= (��ǰҳ-1)*pageSize
			int pageSize = 2;//ÿҳչʾ������
			int firstCount = (pageInfo.getCurrentPage()-1)*pageSize;  //��ʼ����
//			(2)��ȡ��ǰҳչʾ������---�������ݲ㷽��
			ArrayList<DeptEntity> deps = dao.findAllDept(pageSize,firstCount);
			pageInfo.setLists(deps);
//			(3)��ȡ������---�������ݲ㷽��
			int totalCount = dao.getDeptCount();
			pageInfo.setTotalCount(totalCount);
//			(4)������ҳ��  ������%ÿҳչʾ������==0?������/ÿҳչʾ������:������/ÿҳչʾ������+1��
			int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		    pageInfo.setTotalPage(totalPage);
		} catch (Exception e){
			e.printStackTrace();
		}finally{
//			(5)�ر�����
			dao.closeConnection();
		}
		
	}
	
	//ɾ������
	public void delDep(String[] ids) {
		try {
			//����dao��ķ���
			dao.delDep(ids);  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
            //�ر�����
			dao.closeConnection();
		}
         		
	}
     
	//��鲿�������Ƿ���ӹ�
	public boolean checkDepName(String depname) {
		boolean result = false;
		try {
			result = dao.checkDepName(depname);  //����dao��ķ���
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
		return result;
	}
    
	//��Ӳ���
	public boolean insertDep(String depname, String depdetail) {
		int res = 0;
		try {
			res = dao.insertDep(depname,depdetail);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		return res>0?true:false;
	}
    
	//�޸Ĳ���
	public void updateDep(String depid, String depname, String depdetail) {
        try {
			dao.updateDep(depid,depname,depdetail);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}		
	}

}
