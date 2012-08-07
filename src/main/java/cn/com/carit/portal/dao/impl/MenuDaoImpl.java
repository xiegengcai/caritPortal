package cn.com.carit.portal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.com.carit.common.Constants;
import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.StringUtil;
import cn.com.carit.portal.bean.Menu;
import cn.com.carit.portal.dao.MenuDao;

@Repository
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao<Menu> {
	
	private final RowMapper<Menu> rowMapper=new RowMapper<Menu>() {
		
		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menu t=new Menu();
			t.setId(rs.getInt("id"));
			t.setParentId(rs.getInt("parent_id"));
			t.setCode(rs.getString("code"));
			t.setUrl(rs.getString("url"));
			t.setDisplayIndex(rs.getInt("display_index"));
			t.setLevel(rs.getInt("level"));
			t.setRemark(rs.getString("remark"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};

	@Override
	public int add(Menu t) {
		String sql="insert into t_menu (code, url, parent_id, level, display_index"
				+ ", remark, status, create_time, update_time)"
				+ " values(?, ?, ?, ?, ?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getCode()
				, t.getUrl()
				, t.getParentId()
				, t.getLevel()
				, t.getDisplayIndex()
				, t.getRemark()
				, t.getStatus());
	}

	@Override
	public int update(Menu t) {
		StringBuilder sql=new StringBuilder(
				"update t_menu set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (StringUtils.hasText(t.getCode())) {
			sql.append(", code=?");
			val.add(t.getCode());
		}
		if (StringUtils.hasText(t.getUrl())) {
			sql.append(", url=?");
			val.add(t.getUrl());
		}
		if (t.getParentId()!=null) {
			sql.append(", parent_id=?");
			val.add(t.getParentId());
		}
		if (t.getLevel()!=null) {
			sql.append(", level=?");
			val.add(t.getLevel());
		}
		if (t.getDisplayIndex()!=null) {
			sql.append(", display_index=?");
			val.add(t.getDisplayIndex());
		}
		if (StringUtils.hasText(t.getRemark())) {
			sql.append(", remark=?");
			val.add(t.getRemark());
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
		String sql="delete from t_menu where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}
	
	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_menu where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}
	
	@Override
	public Menu queryById(int id) {
		String sql="select * from t_menu where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	
	@Override
	public List<Menu> queryByParent(int parentId) {
		String sql="select * from t_menu where parent_id=? and status=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{parentId, Constants.STATUS_VALID}, rowMapper);
	}

	@Override
	public JsonPage<Menu> queryByExemple(Menu t, DataGridModel dgm) {
		JsonPage<Menu> jsonPage = new JsonPage<Menu>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_menu where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_menu where 1=1"
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
	public List<Menu> queryByExemple(Menu t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_menu where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<Menu> queryAll() {
		String sql="select * from t_menu";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes, Menu t) {
		StringBuilder sql = new StringBuilder();
		if (StringUtils.hasText(t.getCode())) {
			sql.append(" and code like CONCAT('%',?,'%')");
			args.add(t.getCode());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getUrl())) {
			sql.append(" and url like CONCAT('%',?,'%')");
			args.add(t.getUrl());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getParentId()!=null) {
			sql.append(" and parent_id=?");
			args.add(t.getParentId());
			argTypes.add(Types.INTEGER);
		}
		if (t.getLevel()!=null) {
			sql.append(" and level=?");
			args.add(t.getLevel());
			argTypes.add(Types.INTEGER);
		}
		if (t.getDisplayIndex()!=null) {
			sql.append(" and display_index=?");
			args.add(t.getDisplayIndex());
			argTypes.add(Types.INTEGER);
		}
		if (StringUtils.hasText(t.getRemark())) {
			sql.append(" and remark like CONCAT('%',?,'%')");
			args.add(t.getRemark());
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
	public List<Menu> queryTopMenus() {
		String sql="select * from t_menu where parent_id is null and status=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{Constants.STATUS_VALID}, rowMapper);
	}

	@Override
	public List<Menu> querySubMenus() {
		String sql="select * from t_menu where parent_id is not null and status=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{Constants.STATUS_VALID}, rowMapper);
	}

	@Override
	public int checkExisted(String code) {
		String sql="select 1 from t_menu where code=?";
		return checkExisted(sql, code);
	}

}
