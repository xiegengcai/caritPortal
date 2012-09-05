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

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.StringUtil;
import cn.com.carit.portal.bean.DemoVideo;
import cn.com.carit.portal.dao.DemoVideoDao;
@Repository
public class DemoVideoDaoImpl extends BaseDaoImpl
	implements DemoVideoDao<DemoVideo>{
	
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	
	private final RowMapper<DemoVideo> rowMapper=new RowMapper<DemoVideo>() {
		
		@Override
		public DemoVideo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DemoVideo t=new DemoVideo();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setUrl(rs.getString("url"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};

	@Override
	public int add(DemoVideo t) {
		String sql="insert into t_demo_video (name, url, status, create_time, update_time)"
				+ " values(?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getName()
				, t.getUrl()
				, t.getStatus()
			);
	}

	@Override
	public int update(DemoVideo t) {
		StringBuilder sql=new StringBuilder("update t_demo_video set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (StringUtils.hasText(t.getName())) {
			sql.append(", name=?");
			val.add(t.getName());
		}
		if (StringUtils.hasText(t.getUrl())) {
			sql.append(", url=?");
			val.add(t.getUrl());
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
		String sql="delete from t_demo_video where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_demo_video where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}
	
	@Override
	public DemoVideo queryById(int id) {
		String sql="select * from t_demo_video where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<DemoVideo> queryByExemple(DemoVideo t,
			DataGridModel dgm) {
		JsonPage<DemoVideo> jsonPage = new JsonPage<DemoVideo>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_demo_video where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_demo_video where 1=1"
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
	public List<DemoVideo> queryByExemple(DemoVideo t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_demo_video where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<DemoVideo> queryAll() {
		String sql="select * from t_demo_video";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes,
			DemoVideo t) {
		StringBuilder sql = new StringBuilder();
		if (StringUtils.hasText(t.getName())) {
			sql.append(" and name like CONCAT('%',?,'%')");
			args.add(t.getName());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getUrl())) {
			sql.append(" and url like CONCAT('%',?,'%')");
			args.add(t.getUrl());
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
	public List<DemoVideo> queryNewest(int limit) {
		String sql="select * from t_demo_video order by update_time desc limit ?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{1}, rowMapper);
	}

}
