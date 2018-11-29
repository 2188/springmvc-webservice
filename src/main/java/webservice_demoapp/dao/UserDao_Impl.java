package webservice_demoapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import webservice_demoapp.model.User;

@Repository
public class UserDao_Impl implements UserDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public SqlParameterSource getSqlParameterByModel(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		if (user != null) {
			paramSource.addValue("id", user.getId());
			paramSource.addValue("firstName", user.getFirstName());
			paramSource.addValue("lastName", user.getLastName());
			paramSource.addValue("address", user.getAddress());
		}

		return paramSource;
	}

	private static final class Mapper implements RowMapper<User> {
		public User mapRow(ResultSet arg0, int arg1) throws SQLException {
			User user = new User();
			user.setId(arg0.getInt("id"));
			user.setFirstName(arg0.getString("firstName"));
			user.setLastName(arg0.getString("lastName"));
			user.setAddress(arg0.getString("address"));
			return user;
		}
	}

	public List<User> listAllUser() {

		List<User> userList = new ArrayList<User>();

		String listAllUser_Query = "SELECT id, firstName, lastName, address FROM users";
		userList = namedParameterJdbcTemplate.query(listAllUser_Query, getSqlParameterByModel(null), new Mapper());

		return userList;
	}

	public void addUser(User user) {
		System.out.println("----------------  "+user.getFirstName());
		String sql = "INSERT INTO users (firstName, lastName, address) VALUES (:firstName, :lastName, :address)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}

	public void updateUser(User user) {

		String sql = "UPDATE users SET firstName = :firstName, lastName = :lastName, address = :address WHERE id = :id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}

	public void deleteUser(User user) {
		String sql = "DELETE FROM users WHERE id = :id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}

	public User findUserByID(User user) {
		String sql = "SELECT * FROM users WHERE id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(user), new Mapper());
	}

}
