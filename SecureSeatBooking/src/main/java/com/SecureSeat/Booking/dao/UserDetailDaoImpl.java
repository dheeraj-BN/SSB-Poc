package com.SecureSeat.Booking.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.service.UserServiceImpl;

@Component
public class UserDetailDaoImpl implements UserDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailDaoImpl.class);
	
	/**
	 * Update the booking details with the given login time, status and ID.
	 *
	 * @param loggedIn The login time of the user
	 * @param status The new status of the booking
	 * @param id The ID of the booking to update
	 * @throws Exception If there is an error updating the booking details
	 */
	@Override
	public void updateBookingDetails(LocalTime loggedIn, String status, int id) throws Exception {
	    String sql = "UPDATE `seatsb`.`booking_details` SET `login_time` = ?, `booking_status` = ? WHERE (`booking_id` = ?)";

	    // Log the update operation with the parameters
	    LOGGER.info("Updating booking details with login time: {}, status: {} and ID: {}", loggedIn, status, id);

	    // Execute the SQL query with the given parameters
	    jdbcTemplate.update(sql, loggedIn, status, id);

	    // Log the success of the update operation
	    LOGGER.info("Booking details with ID {} updated successfully", id);
	}
	
	
	/**
	 * Retrieve the employee details associated with the booking token after successful validation.
	 *
	 * @param token The booking token to use for retrieving employee details
	 * @return The Employee object containing the employee details
	 */
	@Override
	public Employee getEmployeeDetailsAfterValidationSuccess(String token) {
	    String sql = "SELECT e.* FROM booking_details bd " +
	            "INNER JOIN user_deatils ud ON bd.user_id = ud.user_id " +
	            "INNER JOIN employee e ON ud.employee_id = e.employee_id " +
	            "WHERE bd.token = ?";

	    // Log the query with the token parameter
	    LOGGER.info("Retrieving employee details for token: {}", token);

	    // Execute the SQL query with the given token parameter
	    Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{token}, (rs, rowNum) ->
	            new Employee(
	                    rs.getInt("employee_id"),
	                    rs.getString("employee_name"),
	                    rs.getString("employee_email"),
	                    rs.getString("employee_designation"),
	                    rs.getString("employee_phone_no"),
	                    rs.getString("employee_gender"),
	                    rs.getString("employee_personal_email")
	            ));

	    // Log the success of the query with the retrieved employee details
	    LOGGER.info("Employee details retrieved successfully for token: {}, Employee: {}", token, employee);

	    return employee;
	}

	
	
	/**
	 * Retrieves the information of the admin user from the database.
	 *
	 * @return The admin user information
	 */
	@Override
	public Employee getAdminInfo() {
	    String sql = "SELECT * FROM user_deatils ud " +
	                 "INNER JOIN employee em ON ud.employee_id = em.employee_id " +
	                 "INNER JOIN users_roles ur ON ur.user_id = ud.user_id " +
	                 "INNER JOIN role r ON r.role_id = ur.role_id " +
	                 "WHERE r.role_name = 'ADMIN'";

	    // Log the query being executed
	    LOGGER.info("Retrieving admin user information with SQL: {}", sql);

	    // Execute the SQL query and return the result
	    Employee admin = (Employee) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class));

	    // Log the success of the query
	    LOGGER.info("Admin user information retrieved successfully");

	    return admin;
	}


	
	/**
	 * Get a list of all employees who do not have user details associated with them.
	 *
	 * @return A list of employees with no user details
	 */
	@Override
	public List<Employee> getemployee() {
	    String sql = "SELECT em.* FROM employee em "
	            + "LEFT JOIN user_deatils ud ON ud.employee_id = em.employee_id "
	            + "WHERE ud.employee_id IS NULL";

	    // Log the query being executed
	    LOGGER.info("Executing query to get all employees with no user details");

	    // Execute the SQL query and get the result as a list of maps
	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

	    // Create a list to hold the Employee objects to be returned
	    List<Employee> employeeList = new ArrayList<>();

	    // Loop through each row in the result set and create a new Employee object for each row
	    for (Map<String, Object> row : rows) {
	        Employee employee = new Employee();
	        employee.setEmployeeId((Integer) row.get("EMPLOYEE_ID"));
	        employee.setEmployeeName((String) row.get("EMPLOYEE_NAME"));
	        employee.setEmployeeDesignation((String) row.get("EMPLOYEE_DESIGNATION"));
	        employee.setEmployeeEmail((String) row.get("EMPLOYEE_EMAIL"));
	        employee.setEmployeeGender((String) row.get("EMPLOYEE_GENDER"));
	        employee.setEmployeePersonalEmail((String) row.get("EMPLOYEE_PERSONAL_EMAIL"));
	        employee.setEmployeePhoneNo((String) row.get("EMPLOYEE_PHONE_NO"));
	        employeeList.add(employee);
	    }

	    // Log the number of employees returned by the query
	    LOGGER.info("Found {} employees with no user details", employeeList.size());

	    return employeeList;
	}

	
	
	/**
	 * Update the booking details for holiday.
	 */
	public void updateHoliday() {
	    String sql = "UPDATE `seatsb`.`booking_details` SET `login_time` = ?, `booking_status` = ? WHERE (`booking_id` = ?)";
	    // In the current implementation, the SQL query does not specify any values to update
	    // This may result in an error if the table does not allow null values for the columns
	    // Alternatively, the SQL query could be modified to include default or valid values for the columns

	    // Log the update operation
	    LOGGER.info("Updating booking details for holiday");

	    // Execute the SQL query without any parameters
	    jdbcTemplate.update(sql);

	    // Log the success of the update operation
	    LOGGER.info("Booking details updated for holiday");
	}
	
	@Override
	public List<Employee> getRegisteredemployee() {
	    String sql = "SELECT em.* FROM seatsb.employee em INNER JOIN user_deatils ud ON ud.employee_id = em.employee_id";

	    // Log the query being executed
	    LOGGER.info("Executing query to get all employees with no user details");

	    // Execute the SQL query and get the result as a list of maps
	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

	    // Create a list to hold the Employee objects to be returned
	    List<Employee> employeeList = new ArrayList<>();

	    // Loop through each row in the result set and create a new Employee object for each row
	    for (Map<String, Object> row : rows) {
	        Employee employee = new Employee();
	        employee.setEmployeeId((Integer) row.get("EMPLOYEE_ID"));
	        employee.setEmployeeName((String) row.get("EMPLOYEE_NAME"));
	        employee.setEmployeeDesignation((String) row.get("EMPLOYEE_DESIGNATION"));
	        employee.setEmployeeEmail((String) row.get("EMPLOYEE_EMAIL"));
	        employee.setEmployeeGender((String) row.get("EMPLOYEE_GENDER"));
	        employee.setEmployeePersonalEmail((String) row.get("EMPLOYEE_PERSONAL_EMAIL"));
	        employee.setEmployeePhoneNo((String) row.get("EMPLOYEE_PHONE_NO"));
	        employeeList.add(employee);
	    }

	    // Log the number of employees returned by the query
	    LOGGER.info("Found {} employees with no user details", employeeList.size());

	    return employeeList;
	}

}
