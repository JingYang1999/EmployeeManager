package com.icss.employeemanager.biz;

import java.util.ArrayList;

import com.icss.employeemanager.dao.DeptDao;
import com.icss.employeemanager.entity.DeptEntity;
import com.icss.employeemanager.utils.ResultPage;

/**
*部门相关的业务逻辑层
*@author Giselle
*/
public class DeptBiz {
    DeptDao dao = new DeptDao();  //创建数据层对象
	//分页查看所有部门信息
	public void findAllDept(ResultPage<DeptEntity> pageInfo) {
		try {
//			(1)定义两个变量：pageSize=2；first= (当前页-1)*pageSize
			int pageSize = 2;//每页展示的条数
			int firstCount = (pageInfo.getCurrentPage()-1)*pageSize;  //起始条数
//			(2)获取当前页展示的数据---调用数据层方法
			ArrayList<DeptEntity> deps = dao.findAllDept(pageSize,firstCount);
			pageInfo.setLists(deps);
//			(3)获取总条数---调用数据层方法
			int totalCount = dao.getDeptCount();
			pageInfo.setTotalCount(totalCount);
//			(4)计算总页数  总条数%每页展示的条数==0?总条数/每页展示的条数:总条数/每页展示的条数+1。
			int totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		    pageInfo.setTotalPage(totalPage);
		} catch (Exception e){
			e.printStackTrace();
		}finally{
//			(5)关闭连接
			dao.closeConnection();
		}
		
	}
	
	//删除部门
	public void delDep(String[] ids) {
		try {
			//调用dao层的方法
			dao.delDep(ids);  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
            //关闭连接
			dao.closeConnection();
		}
         		
	}
     
	//检查部门名称是否添加过
	public boolean checkDepName(String depname) {
		boolean result = false;
		try {
			result = dao.checkDepName(depname);  //调用dao层的方法
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
		return result;
	}
    
	//添加部分
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
    
	//修改部门
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
