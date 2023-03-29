package com.SecureSeat.Booking.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.Employee;

@Component
public class UserDetailDaoImpl implements UserDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void updateBookingDetails(LocalTime loggedIn, String status, int id) throws Exception {
		
		String sql = "UPDATE `seatsb`.`booking_details` SET `login_time` = ?, `booking_status` = ? WHERE (`booking_id` = ?)";
	    jdbcTemplate.update(sql, loggedIn, status, id);
	}
	
	
	@Override
	public Employee getEmployeeDetailsAfterValidationSuccess(String token) {
		
		String sql = "SELECT e.* FROM booking_details bd " +
                "INNER JOIN user_deatils ud ON bd.user_id = ud.user_id " +
                "INNER JOIN employee e ON ud.employee_id = e.employee_id " +
                "WHERE bd.token = ?";
		
		
		 return jdbcTemplate.queryForObject(sql, new Object[]{token}, (rs, rowNum) ->
         new Employee(
                 rs.getInt("employee_id"),
                 rs.getString("employee_name"),
                 rs.getString("employee_email"),
                 rs.getString("employee_designation"),
                 rs.getString("employee_phone_no"),
                 rs.getString("employee_gender"),
                 rs.getString("employee_personal_email")
         ));
	}
	
	
	@Override
	public Employee getAdminInfo() {
		String sql="select * from user_deatils  ud INNER JOIN employee em ON ud.employee_id=em.employee_id INNER JOIN users_roles ur  ON ur.user_id=ud.user_id \r\n"
				+ "INNER JOIN role r ON r.role_id = ur.role_id where r.role_name ='ADMIN'";
//		return jdbcTemplate.queryForObject(sql, new Object[] ,(rs,rowNum) ->
//			new UserDeatils(
//					rs.getInt("user_id"),
//					rs.getString("password"),
//					rs.getObject("employee_id")
//					)
//				);
		
		
		return (Employee) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class));
	}

	
	@Override
	public List<Employee> getemployee() {
		String sql = "select em.* FROM employee em\r\n"
				+ "LEFT JOIN user_deatils ud ON ud.employee_id = em.employee_id\r\n"
				+ "WHERE ud.employee_id IS NULL";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		List<Employee> employee = new ArrayList<>();
		for(Map row : rows) {
			Employee emp = new Employee();
			emp.setEmployeeId((Integer) row.get("EMPLOYEE_ID"));
			emp.setEmployeeName((String) row .get("EMPLOYEE_NAME"));
			emp.setEmployeeDesignation((String) row.get("EMPLOYEE_DESIGNATION"));
			emp.setEmployeeEmail((String) row.get("EMPLOYEE_EMAIL"));
			emp.setEmployeeGender((String) row.get("EMPLOYEE_GENDER"));
			emp.setEmployeePersonalEmail((String) row.get("EMPLOYEE_PERSONAL_EMAIL"));
			emp.setEmployeePhoneNo((String) row.get("EMPLOYEE_PHONE_NO"));
			employee.add(emp);
		}
		return employee;
		}
	
}
