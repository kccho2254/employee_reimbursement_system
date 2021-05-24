package com.revature.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.revature.models.Reimb;

public interface ReimbDAO {

	public boolean addNewRequest(Reimb request);
	public List<Reimb> viewAllTickets() throws IOException;
	public List<Reimb> viewPastTickets();
	public List<Reimb> viewPendingTicketsByEmployee(int author);
	public List<Reimb> viewPastTicketsByEmployee(int author);
//	boolean updateRequest(LocalDate localDate, int resolver_id, int status_id, int author_id);
	boolean updateRequest(Reimb reim);

}
