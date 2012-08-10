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
import cn.com.carit.portal.bean.Catalog;
import cn.com.carit.portal.dao.CatalogDao;

@Repository
public class CatalogDaoImpl extends BaseDaoImpl implements CatalogDao<Catalog> {
	
	private final RowMapper<Catalog> rowMapper = new RowMapper<Catalog>() {
		
		@Override
		public Catalog mapRow(ResultSet rs, int rowNum) throws SQLException {
			Catalog t=new Catalog();
			t.setId(rs.getInt("id"));
			t.setCatalogCode(rs.getString("catalog_code"));
			t.setDescription(rs.getString("description"));
			t.setDisplayIndex(rs.getInt("display_index"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};
	@Override
	public int add(Catalog t) {
		String sql="insert into t_catalog (catalog_code, description, display_index, status"
				+", create_time, update_time) values(?, ?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getCatalogCode()
				, t.getDescription()
				, t.getDisplayIndex()
				, t.getStatus()
			);
	}

	@Override
	public int update(Catalog t) {
		StringBuilder sql=new StringBuilder("update t_catalog set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (StringUtils.hasText(t.getCatalogCode())) {
			sql.append(", catalog_code=?");
			val.add(t.getCatalogCode());
		}
		if (StringUtils.hasText(t.getDescription())) {
			sql.append(", description=?");
			val.add(t.getDescription());
		}
		if (t.getDisplayIndex()!=null) {
			sql.append(", display_index=?");
			val.add(t.getDisplayIndex());
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
		String sql="delete from t_catalog where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_catalog where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}

	@Override
	public Catalog queryById(int id) {
		String sql = "select * from t_catalog where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<Catalog> queryByExemple(Catalog t, DataGridModel dgm) {
		JsonPage<Catalog> jsonPage = new JsonPage<Catalog>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_catalog where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_catalog where 1=1"
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
	public List<Catalog> queryByExemple(Catalog t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_catalog where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<Catalog> queryAll() {
		String sql = "select * from t_catalog";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes,
			Catalog t) {
		StringBuilder sql = new StringBuilder();
		if (StringUtils.hasText(t.getCatalogCode())) {
			sql.append(" and catalog_code like CONCAT('%',?,'%')");
			args.add(t.getCatalogCode());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getDescription())) {
			sql.append(" and description like CONCAT('%',?,'%')");
			args.add(t.getDescription());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getDisplayIndex()!=null) {
			sql.append(" and display_index=?");
			args.add(t.getDisplayIndex());
			argTypes.add(Types.INTEGER);
		}
		if (t.getStatus()!=null) {
			sql.append(" and status=?");
			args.add(t.getStatus());
			argTypes.add(Types.INTEGER);
		}
		return sql.toString();
	}

}
