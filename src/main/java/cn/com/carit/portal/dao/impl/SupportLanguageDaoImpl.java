package cn.com.carit.portal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.StringUtil;
import cn.com.carit.portal.bean.SupportLanguage;
import cn.com.carit.portal.dao.SupportLanguageDao;

@Repository
public class SupportLanguageDaoImpl extends BaseDaoImpl implements
		SupportLanguageDao<SupportLanguage> {

	private final RowMapper<SupportLanguage> rowMapper = new RowMapper<SupportLanguage>() {
		
		@Override
		public SupportLanguage mapRow(ResultSet rs, int rowNum) throws SQLException {
			SupportLanguage t = new SupportLanguage();
			t.setId(rs.getInt("id"));
			t.setIsoCode(rs.getString("iso_code"));
			t.setName(rs.getString("name"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};
	
	@Override
	public int add(SupportLanguage t) {
		String sql="insert into t_support_language (iso_code, name"
				+ ", status, update_time, create_time) values(?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getIsoCode()
				, t.getName()
				, t.getStatus()
			);
	}

	@Override
	public int update(SupportLanguage t) {
		StringBuilder sql=new StringBuilder("update t_support_language set update_time=now()");
		List<Object> val=new ArrayList<Object>();
		if (StringUtils.hasText(t.getIsoCode())) {
			sql.append(", iso_code=?");
			val.add(t.getIsoCode());
		}
		if (StringUtils.hasText(t.getName())) {
			sql.append(", name=?");
			val.add(t.getName());
		}
		if (t.getStatus()!=null) {
			sql.append(", status=?");
			val.add(t.getStatus());
		}
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql.toString(), val.toArray());
	}

	@Override
	public int delete(int id) {
		String sql="delete from t_support_language where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_support_language where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}

	@Override
	public SupportLanguage queryById(int id) {
		String sql="select * from t_support_language where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<SupportLanguage> queryByExemple(SupportLanguage t,
			DataGridModel dgm) {
		JsonPage<SupportLanguage> jsonPage = new JsonPage<SupportLanguage>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_support_language where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_support_language where 1=1"
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
			sql.append(" order by update_time desc");
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
	public List<SupportLanguage> queryByExemple(SupportLanguage t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_support_language where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<SupportLanguage> queryAll() {
		String sql="select * from t_support_language";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes,
			SupportLanguage t) {
		StringBuilder sql=new StringBuilder();
		if (StringUtils.hasText(t.getIsoCode())) {
			sql.append(" and iso_code like CONCAT('%',?,'%')");
			args.add(t.getIsoCode());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getName())) {
			sql.append(" and name like CONCAT('%',?,'%')");
			args.add(t.getName());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getStatus()!=null) {
			sql.append(" and status=?");
			args.add(t.getStatus());
			argTypes.add(Types.INTEGER);
		}
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return sql.toString();
	}

}
