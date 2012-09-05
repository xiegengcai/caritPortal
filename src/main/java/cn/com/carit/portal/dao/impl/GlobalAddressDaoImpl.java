package cn.com.carit.portal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.com.carit.common.Constants;
import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.StringUtil;
import cn.com.carit.portal.bean.GlobalAddress;
import cn.com.carit.portal.dao.GlobalAddressDao;
@Repository
public class GlobalAddressDaoImpl extends BaseDaoImpl
	implements GlobalAddressDao<GlobalAddress>{
	
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	
	private final RowMapper<GlobalAddress> rowMapper=new RowMapper<GlobalAddress>() {
		
		@Override
		public GlobalAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
			GlobalAddress t=new GlobalAddress();
			t.setId(rs.getInt("id"));
			t.setLanguage(rs.getString("language"));
			t.setAddress(rs.getString("address"));
			t.setTelephone(rs.getString("telephone"));
			t.setFax(rs.getString("fax"));
			t.setPostalcode(rs.getString("postalcode"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};

	@Override
	public int add(GlobalAddress t) {
		String sql="insert into t_global_address (language, address, telephone, fax, postalcode, status, create_time, update_time)"
				+ " values(?, ?, ?, ?, ?, ?,now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getLanguage()
				, t.getAddress()
				, t.getTelephone()
				, t.getFax()
				, t.getPostalcode()
				, t.getStatus()
			);
	}

	@Override
	public int update(GlobalAddress t) {
		StringBuilder sql=new StringBuilder("update t_global_address set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if(StringUtils.hasText(t.getAddress())){
			sql.append(", address=?");
			val.add(t.getAddress());
		}
		if(StringUtils.hasText(t.getTelephone())){
			sql.append(", telephone=?");
			val.add(t.getTelephone());
		}
		if(StringUtils.hasText(t.getFax())){
			sql.append(", fax=?");
			val.add(t.getFax());
		}
		if(StringUtils.hasText(t.getPostalcode())){
			sql.append(", postalcode=?");
			val.add(t.getPostalcode());
		}
		if (t.getStatus()!=null) {
			sql.append(", status=?");
			val.add(t.getStatus());
		}
		sql.append(" where id=?");
		val.add(t.getId());
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql.toString(), val.toArray());
	}

	@Override
	public int delete(int id) {
		String sql="delete from t_global_address where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_global_address where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}
	
	@Override
	public GlobalAddress queryById(int id) {
		String sql="select * from t_global_address where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<GlobalAddress> queryByExemple(GlobalAddress t,
			DataGridModel dgm) {
		JsonPage<GlobalAddress> jsonPage = new JsonPage<GlobalAddress>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_global_address where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_global_address where 1=1"
				+ whereSql;
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", countSql));
		}
		int totalRow = queryForInt(countSql, args, argTypes);
		// 更新
		jsonPage.setTotal(totalRow);
		// 排序
		if (StringUtils.hasText(dgm.getOrder())
				&& StringUtils.hasText(dgm.getSort())) {
			sql.append(" order by ")
					.append(StringUtil.splitFieldWords(dgm.getSort()))
					.append(" ").append(dgm.getOrder());

		} else {
			sql.append(" order by update_time asc");
		}
		sql.append(" limit ?, ?");
		args.add(jsonPage.getStartRow());
		args.add(jsonPage.getPageSize());
		argTypes.add(Types.INTEGER);
		argTypes.add(Types.INTEGER);
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		jsonPage.setRows(query(sql.toString(), args, argTypes, rowMapper));
		return jsonPage;
	}

	@Override
	public List<GlobalAddress> queryByExemple(GlobalAddress t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_global_address where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<GlobalAddress> queryAll() {
		String sql="select * from t_global_address";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes,
			GlobalAddress t) {
		StringBuilder sql = new StringBuilder();
		if(StringUtils.hasText(t.getAddress())){
			sql.append(" and address like CONCAT('%',?,'%')");
			args.add(t.getAddress());
			argTypes.add(Types.VARCHAR);
		}
		if(StringUtils.hasText(t.getTelephone())){
			sql.append(" and telephone like CONCAT('%',?,'%')");
			args.add(t.getTelephone());
			argTypes.add(Types.VARCHAR);
		}
		if(StringUtils.hasText(t.getFax())){
			sql.append(" and fax like CONCAT('%',?,'%')");
			args.add(t.getFax());
			argTypes.add(Types.VARCHAR);
		}
		if(StringUtils.hasText(t.getPostalcode())){
			sql.append(" and postalcode like CONCAT('%',?,'%')");
			args.add(t.getPostalcode());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getStatus()!=null) {
			sql.append(" and status=?");
			args.add(t.getStatus());
			argTypes.add(Types.INTEGER);
		}
		return sql.toString();
	}

	@Override
	public List<GlobalAddress> query(String language) {
		String sql="select * from t_global_address where status=? and language=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{Constants.STATUS_VALID, language}, new int[]{Types.INTEGER, Types.VARCHAR}, rowMapper);
	}


}
