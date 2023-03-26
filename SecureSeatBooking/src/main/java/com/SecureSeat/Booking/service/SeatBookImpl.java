package com.SecureSeat.Booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SecureSeat.Booking.entity.BookingDetails;
import com.SecureSeat.Booking.entity.Employee;
import com.SecureSeat.Booking.entity.HolidayDetails;
import com.SecureSeat.Booking.entity.UserDeatils;
import com.SecureSeat.Booking.repo.BookingDetailsRepo;
import com.SecureSeat.Booking.repo.HolidayDetailsRepo;

@Service
public class SeatBookImpl implements SeatBook {

	@Autowired
	private BookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private HolidayDetailsRepo holidayDetailsRepo;

	@Override
	public String saveBookedDetails(BookingDetails bookingDetails, LocalDate from, LocalDate to) {

		String seatNo = bookingDetails.getSeatNo();
		System.out.println(from.equals(to));
		if (from.equals(to)) {
			int flag3 = 0;
			int flag6 = 0;
			List<HolidayDetails> holiday = holidayDetailsRepo.findAll();
			for (HolidayDetails holi : holiday) {

				if (!(from.equals(holi.getHolidayDate()))) {
					flag3 = 1;
				} else {
					flag6 = 1;
				}
			}
			if (flag3 == 1 && flag6 == 0) {
				List<BookingDetails> userdetails = bookingDetailsRepo
						.findByUserDeatils(bookingDetails.getUserDeatils());
				if (userdetails.isEmpty()) {
					LocalDate d1 = from;
					bookingDetails.setDate(from);
					bookingDetails.setBookedTimings(LocalTime.now());
					String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
					System.out.println(date1);
					UserDeatils user = bookingDetails.getUserDeatils();
					Employee employee = user.getEmployee();
					int employeeid = employee.getEmployeeId();
					String tokenvalue = date1 + employeeid;
					bookingDetails.setToken(tokenvalue);
					bookingDetails.setBookingStatus("PENDING");
					BookingDetails b = bookingDetailsRepo.save(bookingDetails);

				} else {
					int flag1 = 0;
					for (BookingDetails book2 : userdetails) {

						if (!(!(from.equals(book2.getDate()))
								&& !(bookingDetails.getUserDeatils().equals(book2.getUserDeatils())))) {
							flag1 = 1;
						}
					}

					if (flag1 == 0) {
						LocalDate d1 = from;
						bookingDetails.setDate(from);
						bookingDetails.setBookedTimings(LocalTime.now());
						String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
						System.out.println(date1);
						UserDeatils user = bookingDetails.getUserDeatils();
						Employee employee = user.getEmployee();
						int employeeid = employee.getEmployeeId();
						String tokenvalue = date1 + employeeid;
						bookingDetails.setToken(tokenvalue);
						bookingDetails.setBookingStatus("PENDING");
						BookingDetails b = bookingDetailsRepo.save(bookingDetails);

					}
				}

			}
			return "Seat Booking has been done for today";
		}

		else {
			List<HolidayDetails> holiday = holidayDetailsRepo.findAll();
			List<BookingDetails> userdetails = bookingDetailsRepo.findByUserDeatils(bookingDetails.getUserDeatils());
			if (userdetails.isEmpty()) {

				for (LocalDate i = from; i.isBefore(to); i = i.plusDays(1)) {
					int flag4 = 0;
					int flag5 = 0;
					for (HolidayDetails holi : holiday) {
						if (!(i.equals(holi.getHolidayDate()))) {
							flag4 = 1;
						} else {
							flag5 = 1;
						}

					}
					if (flag4 == 1 && flag5 == 0) {
						LocalDate d1 = i;
						bookingDetails.setDate(i);
						bookingDetails.setBookedTimings(LocalTime.now());
						String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
						System.out.println(date1);
						UserDeatils user = bookingDetails.getUserDeatils();
						Employee employee = user.getEmployee();
						int employeeid = employee.getEmployeeId();
						String tokenvalue = date1 + employeeid;
						bookingDetails.setToken(tokenvalue);

						BookingDetails book = new BookingDetails(bookingDetails.getSeatNo(),
								bookingDetails.isFoodStatus(), d1, LocalTime.now(), null, "PENDING", tokenvalue, user,
								bookingDetails.getShiftDetails());
						BookingDetails b = bookingDetailsRepo.save(book);

					}

				}
			} else {
				for (LocalDate i = from; i.isBefore(to); i = i.plusDays(1)) {
					int flag = 0;
					for (HolidayDetails holi : holiday) {
						System.out.println(!(i.equals(holi.getHolidayDate())));
						if (!(i.equals(holi.getHolidayDate()))) {
							for (BookingDetails book2 : userdetails) {
								if (!(!(i.equals(book2.getDate()))
										&& !(bookingDetails.getUserDeatils().equals(book2.getUserDeatils())))) {
									flag = 1;
								}

							}
						} else {
							flag = 1;
						}

					}

					if (flag == 0) {
						LocalDate d1 = i;
						bookingDetails.setDate(i);
						bookingDetails.setBookedTimings(LocalTime.now());
						String date1 = d1.format(DateTimeFormatter.ofPattern("ddMMYYYY"));
						System.out.println(date1);
						UserDeatils user = bookingDetails.getUserDeatils();
						Employee employee = user.getEmployee();
						int employeeid = employee.getEmployeeId();
						String tokenvalue = date1 + employeeid;
						bookingDetails.setToken(tokenvalue);

						BookingDetails book = new BookingDetails(bookingDetails.getSeatNo(),
								bookingDetails.isFoodStatus(), d1, LocalTime.now(), null, "PENDING", tokenvalue, user,
								bookingDetails.getShiftDetails());
						BookingDetails b = bookingDetailsRepo.save(book);
					}

				}
			}
			return "Seat Booking has been done for week ";
		}

	}

}
