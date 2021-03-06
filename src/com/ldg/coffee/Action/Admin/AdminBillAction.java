package com.ldg.coffee.Action.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldg.coffee.Action.Action;
import com.ldg.coffee.Dao.BillDao;
import com.ldg.coffee.Model.User;
import com.ldg.coffee.Util.Script;
import com.ldg.coffee.ViewModel.BillUserProductVM;

public class AdminBillAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		User principal = (User) request.getSession().getAttribute("user");		
		
		if(!principal.getUsername().equals("admin")) {
			Script.back(response, "잘못된 접근입니다.");
			return;
		}
		
	    BillDao bDao = BillDao.getinstance();
	    List<BillUserProductVM> bupVMs = bDao.findByAll();
	    
	    request.setAttribute("bupVMs", bupVMs);
	    RequestDispatcher dis = request.getRequestDispatcher("/admin/billAdmin.jsp");
	    dis.forward(request, response);	    
		
	}

}
