package cn.com.carit.portal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.StringUtil;
import cn.com.carit.portal.bean.AdminUser;
import cn.com.carit.portal.dao.AdminUserDao;
@Repository
public class AdminUserDaoImpl extends BaseDaoImpl implements
		AdminUserDao<AdminUser> {

	private final RowMapper<AdminUser> rowMapper = new RowMapper<AdminUser>() {

		@Override
		public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminUser t = new AdminUser();
			t.setId(rs.getInt("id"));
			t.setEmail(rs.getString("email"));
			t.setPassword(rs.getString("password"));
			t.setNickName(rs.getString("nick_name"));
			t.setRealName(rs.getString("real_name"));
			t.setGender(rs.getByte("gender"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			t.setStatus(rs.getInt("status"));
			t.setRemark(rs.getString("remark"));
			t.setLastLoginIp(rs.getString("last_login_ip"));
			t.setLastLoginTime(rs.getTimestamp("last_login_time"));
			t.setOfficePhone(rs.getString("office_phone"));
			t.setMobile(rs.getLong("mobile"));
			return t;
		}
	};
	
	@Override
	public int add(final AdminUser t) {
		final String sql = "insert into t_admin_user (" + " email"
				+ ", password" + ", nick_name" + ", real_name" + ", gender"
				+ ", update_time" + ", create_time" + ", status" + ", remark"
				+ ", office_phone" + ", mobile" + ") values (" + " ?" + ", ?"
				+ ", ?" + ", ?" + ", ?" + ", now()" + ", now()" + ", ?" + ", ?"
				+ ", ?" + ", ?" + ")";
		log.debug(String.format("\n%1$s\n", sql));
		KeyHolder gkHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, t.getEmail());
				ps.setString(2, t.getPassword());
				ps.setString(3, t.getNickName());
				ps.setString(4, t.getRealName());
				ps.setByte(5, t.getGender());
				ps.setInt(6, t.getStatus());
				ps.setString(7, t.getRemark());
				ps.setString(8, t.getOfficePhone());
				ps.setString(9, t.getMobile() == null ? "" : t.getMobile().toString());
				return ps;
			}
		}, gkHolder);
		return gkHolder.getKey().intValue();
	}

	@Override
	public int update(AdminUser t) {
		StringBuilder sql = new StringBuilder(
				"update t_admin_user set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (StringUtils.hasText(t.getEmail())) {
			sql.append(", email=?");
			val.add(t.getEmail());
		}
		if (StringUtils.hasText(t.getPassword())) {
			sql.append(", password=?");
			val.add(t.getPassword());
		}
		if (StringUtils.hasText(t.getNickName())) {
			sql.append(", nick_name=?");
			val.add(t.getNickName());
		}
		if (StringUtils.hasText(t.getRealName())) {
			sql.append(", real_name=?");
			val.add(t.getRealName());
		}
		if (t.getGender() != null) {
			sql.append(", gender=?");
			val.add(t.getGender());
		}
		if (t.getStatus() != null) {
			sql.append(", status=?");
			val.add(t.getStatus());
		}
		if (StringUtils.hasText(t.getRemark())) {
			sql.append(", remark=?");
			val.add(t.getRemark());
		}
		if (StringUtils.hasText(t.getLastLoginIp())) {
			sql.append(", last_login_ip=?");
			val.add(t.getLastLoginIp());
		}
		if (t.getLastLoginTime() != null) {
			sql.append(", last_login_time=?");
			val.add(t.getLastLoginTime());
		}
		if (StringUtils.hasText(t.getOfficePhone())) {
			sql.append(", office_phone=?");
			val.add(t.getOfficePhone());
		}
		if (t.getMobile() != null) {
			sql.append(", mobile=?");
			val.add(t.getMobile());
		}
		sql.append(" where id=?");
		val.add(t.getId());
		log.debug(String.format("\n%1$s\n", sql));
		return jdbcTemplate.update(sql.toString(), val.toArray());
	}

	@Override
	public int delete(int id) {
		String sql = "delete from t_admin_user where id=?";
		log.debug(String.format("\n%1$s\n", sql));
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public AdminUser queryById(int id) {
		String sql = "select * from t_admin_user where id=?";
		log.debug(String.format("\n%1$s\n", sql));
		return query(sql, id, rowMapper);
	}

	
	@Override
	public AdminUser queryByEmail(String email) {
		String sql = "select * from t_admin_user where email=?";
		log.debug(String.format("\n%1$s\n", sql));
		return query(sql, email, rowMapper);
	}

	@Override
	public JsonPage<AdminUser> queryByExemple(AdminUser t, DataGridModel dgm) {
		JsonPage<AdminUser> jsonPage = new JsonPage<AdminUser>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_admin_user where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_admin_user where 1=1"
				+ whereSql;
		log.debug(String.format("\n%1$s\n", countSql));
		int totalRow = queryForInt(countSql, args, argTypes);
		// 更新
		jsonPage.setTotal(totalRow);
		// 排序
		if (StringUtils.hasText(dgm.getOrder())
				&& StringUtils.hasText(dgm.getSort())) {
			sql.append(" order by ")
					.append(StringUtil.splitFieldWords(dgm.getSort()))
					.append(" ").append(dgm.getOrder());

		}
		sql.append(" limit ?, ?");
		args.add(jsonPage.getStartRow());
		args.add(jsonPage.getPageSize());
		argTypes.add(Types.INTEGER);
		argTypes.add(Types.INTEGER);
		log.debug(String.format("\n%1$s\n", sql));
		jsonPage.setRows(query(sql.toString(), args, argTypes, rowMapper));
		return jsonPage;
	}

	@Override
	public List<AdminUser> queryByExemple(AdminUser t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_admin_user where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		log.debug(String.format("\n%1$s\n", sql));
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<AdminUser> queryAll() {
		String sql="select * from t_admin_user";
		log.debug(String.format("\n%1$s\n", sql));
		return jdbcTemplate.query(sql, rowMapper);
	}

	private String buildWhere(List<Object> args, List<Integer> argTypes,
			AdminUser t) {
		StringBuilder sql = new StringBuilder();
		if (StringUtils.hasText(t.getEmail())) {
			sql.append(" and email  like CONCAT('%',?,'%')");
			args.add(t.getEmail());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getNickName())) {
			sql.append(" and nick_name  like CONCAT('%',?,'%')");
			args.add(t.getNickName());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getRealName())) {
			sql.append(" and real_name  like CONCAT('%',?,'%')");
			args.add(t.getRealName());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getGender() != null) {
			sql.append(" and gender=?");
			args.add(t.getGender());
			argTypes.add(Types.TINYINT);
		}
		if (t.getStatus() != null) {
			sql.append(" and status=?");
			args.add(t.getStatus());
			argTypes.add(Types.INTEGER);
		}
		if (StringUtils.hasText(t.getRemark())) {
			sql.append(" and remark  like CONCAT('%',?,'%')");
			args.add(t.getRemark());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getLastLoginIp())) {
			sql.append(" and last_login_ip  like CONCAT('%',?,'%')");
			args.add(t.getLastLoginIp());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getLastLoginTimeStart() != null) {
			sql.append(" and last_login_time>=?");
			args.add(t.getLastLoginTimeStart());
			argTypes.add(Types.TIMESTAMP);
		}
		if (t.getLastLoginTimeEnd() != null) {
			sql.append(" and last_login_time<=?");
			args.add(t.getLastLoginTimeEnd());
			argTypes.add(Types.TIMESTAMP);
		}
		if (StringUtils.hasText(t.getOfficePhone())) {
			sql.append(" and office_phone  like CONCAT('%',?,'%')");
			args.add(t.getOfficePhone());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getMobile() != null) {
			sql.append(" and mobile  like CONCAT('%',?,'%')");
			args.add(t.getMobile());
			argTypes.add(Types.BIGINT);
		}
		return sql.toString();
	}

	@Override
	public int checkAdminUser(String email, String nickName) {
		String sql = "select 1 from t_admin_user where 1=1";
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		if(StringUtils.hasText(email)) {
			sql+=" and email=?";
			args.add(email);
			argTypes.add(Types.VARCHAR);
		}
		if(StringUtils.hasText(nickName)){
			sql+=" and nick_name=?";
			args.add(nickName);
			argTypes.add(Types.VARCHAR);
		}
		log.debug(String.format("\n%1$s\n", sql));
		try {
			return queryForInt(sql, args, argTypes);
		} catch (Exception e) {
			log.warn("not exist record of email["+email+"] or nickName["+nickName+"]");
			log.warn(e.getMessage());
		}
		return 0;
	}
	
}
