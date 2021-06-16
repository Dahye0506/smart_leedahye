package controller.employee;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeModifyPage {
	public void empModify(HttpServletRequest request) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeId(request.getParameter("employeeId"));
		dto.setEmpEmail(request.getParameter("empEmail"));
		dto.setEmpAddress(request.getParameter("empAddress"));
		dto.setJobId(request.getParameter("jobId"));
		dto.setOfficeNumber(request.getParameter("officeNumber"));
		dto.setPhNumber(request.getParameter("PhNumber"));
		EmployeeDAO dao = new EmployeeDAO();
		dao.empUpdate(dto);
	}
}
