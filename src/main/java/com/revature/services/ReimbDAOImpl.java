package com.revature.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimb;
import com.revature.utilities.ConnectionUtil;

public class ReimbDAOImpl implements ReimbDAO {

	Connection conn = null;
	PreparedStatement stmt = null;
	Reimb reim = new Reimb();
	public static Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public boolean addNewRequest(Reimb reim) {
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO ers_schema.ers_reimbursement \r\n" + "(reimb_amount, \r\n"
					+ "reimb_submitted, \r\n" + "reimb_description, \r\n" + "reimb_resolved, \r\n"
					+ "reimb_receipt, \r\n" + "reimb_author, \r\n" + "reimb_resolver, \r\n" + "reimb_status_id, \r\n"
					+ "reimb_type_id) \r\n" + "VALUES(?, ?, ?, null, null, ?, null, 1, ?);\r\n" + "";

			System.out.println(reim.getReimb_submitted());
			System.out.println(reim.getReimb_amount());

			stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, reim.getReimb_amount());
			stmt.setDate(2, Date.valueOf(reim.getReimb_submitted()));
			stmt.setString(3, reim.getReimb_desc());
			stmt.setInt(4, reim.getReimb_author());
			stmt.setInt(5, reim.getReimb_type());
			if (stmt.executeUpdate() != 0) {
				log.info("Request created by: " + reim.getReimb_author() + " with description " + reim.getReimb_desc());
				return true;
			} else {
				return false;
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return false;

		} finally {
			closeResources();
		}
	}

	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Reimb> viewAllTickets() throws IOException {
		List<Reimb> reimbs = new ArrayList<>();

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT id, reimb_amount, reimb_submitted, reimb_description, reimb_resolved, reimb_resolver, reimb_author, ers_schema.ers_reimbursement.reimb_status_id,  reimb_status, reimb_type, ers_reimbursement.reimb_type_id FROM \r\n"
					+ "ers_schema.ers_reimbursement \r\n"
					+ "inner join ers_schema.ers_reimbursement_status on\r\n"
					+ "ers_schema.ers_reimbursement_status.reimb_status_id = ers_schema.ers_reimbursement.reimb_status_id\r\n"
					+ "inner join ers_schema.ers_reimbursement_type on\r\n"
					+ "ers_reimbursement_type.reimb_type_id = ers_reimbursement.reimb_type_id;";
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) { 
				// result
				Reimb reimb = new Reimb();

				reimb.setId(rs.getInt("id"));
				reimb.setReimb_amount(rs.getFloat("reimb_amount"));
				reimb.setReimb_submitted(rs.getDate("reimb_submitted").toLocalDate());
				reimb.setReimb_desc(rs.getString("reimb_description"));
				
				reimb.setReimb_resolver(rs.getInt("reimb_resolver"));
				
				reimb.setReimb_author(rs.getInt("reimb_author"));
				reimb.setStatus(rs.getString("reimb_status"));
				reimb.setReimb_status(rs.getInt("reimb_status_id"));
				reimb.setReimb_type(rs.getInt("reimb_type_id"));
				reimb.setType(rs.getString("reimb_type"));

				reimbs.add(reimb);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		return reimbs;
	}

	@Override
	public List<Reimb> viewPastTickets() {
		List<Reimb> reimbs = new ArrayList<>();

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT id, reimb_amount, reimb_submitted, reimb_description, reimb_resolved, reimb_resolver, reimb_author, ers_schema.ers_reimbursement.reimb_status_id,  reimb_status, reimb_type_id FROM \r\n"
					+ "ers_schema.ers_reimbursement \r\n"
					+ "inner join ers_schema.ers_reimbursement_status on\r\n"
					+ "ers_schema.ers_reimbursement_status.reimb_status_id = ers_schema.ers_reimbursement.reimb_status_id\r\n"
					+ "where ers_schema.ers_reimbursement.reimb_status_id = 1;\r\n"
					+ "";
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// result
				Reimb reimb = new Reimb();

				reimb.setId(rs.getInt("id"));
				reimb.setReimb_amount(rs.getFloat("reimb_amount"));
				reimb.setReimb_author(rs.getInt("reimb_author"));
				
				reimb.setReimb_desc(rs.getString("reimb_description"));
				reimb.setReimb_status(rs.getInt("ers_schema.ers_reimbursement.reimb_status_id"));
				reimb.setStatus(rs.getString("reimb_status"));
				reimb.setReimb_submitted(rs.getDate("reimb_submitted").toLocalDate());
				
				reimb.setReimb_type(rs.getInt("reimb_type_id"));
				reimb.setReimb_resolved(rs.getDate("reimb_resolved").toLocalDate());
				reimb.setReimb_resolver(rs.getInt("reimb_resolver"));

				reimbs.add(reimb);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return reimbs;
	}

	@Override
	public List<Reimb> viewPendingTicketsByEmployee(int author) {
		List<Reimb> reimbs = new ArrayList<>(); 

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT id, reimb_amount, reimb_submitted, reimb_description, reimb_resolved, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id FROM ers_schema.ers_reimbursement where reimb_status_id = 1 and reimb_author = ?;\r\n"
					+ "";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, author);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Reimb reimb = new Reimb();

				reimb.setId(rs.getInt("id"));
				reimb.setReimb_amount(rs.getFloat("reimb_amount"));
				reimb.setReimb_desc(rs.getString("reimb_description"));

				reimb.setReimb_author(author);
				
				reimb.setReimb_status(rs.getInt("reimb_status_id"));
				reimb.setReimb_submitted(rs.getDate("reimb_submitted").toLocalDate());
				reimb.setReimb_type(rs.getInt("reimb_type_id"));

				reimbs.add(reimb);
			}
			rs.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return reimbs;
	}

	@Override
	public List<Reimb> viewPastTicketsByEmployee(int author) {
		List<Reimb> reimbs = new ArrayList<>(); 

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT id, reimb_amount, reimb_submitted, reimb_description, reimb_resolved, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id FROM ers_schema.ers_reimbursement where reimb_status_id != 1 and reimb_author = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, author);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Reimb reimb = new Reimb();

				reimb.setId(rs.getInt("id"));
				reimb.setReimb_amount(rs.getFloat("reimb_amount"));
				reimb.setReimb_desc(rs.getString("reimb_description"));
				
				reimb.setReimb_resolved(rs.getDate("reimb_resolved").toLocalDate());
				reimb.setReimb_author(author);
				reimb.setReimb_resolver(rs.getInt("reimb_resolver"));
				
				reimb.setReimb_status(rs.getInt("reimb_status_id"));
				reimb.setReimb_submitted(rs.getDate("reimb_submitted").toLocalDate());
				reimb.setReimb_type(rs.getInt("reimb_type_id"));

				reimbs.add(reimb);
			}
			rs.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return reimbs;
	}

	@Override
	public boolean updateRequest(Reimb reim) {

		// update sql row where id = request id
		
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "UPDATE ers_schema.ers_reimbursement SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ? WHERE id = ?;";


			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(reim.getReimb_resolved()));
			stmt.setInt(2, reim.getReimb_resolver());
			stmt.setInt(3, reim.getReimb_status());
			stmt.setInt(4, reim.getId());
			if (stmt.executeUpdate() != 0) {
				System.out.println("SUCCESS!");
				return true;
			} else {
				return false;
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return false;

		} finally {
			closeResources();
		}		
	}
}
