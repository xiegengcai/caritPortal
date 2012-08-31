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
import cn.com.carit.portal.bean.News;
import cn.com.carit.portal.dao.NewsDao;

@Repository
public class NewsDaoImpl extends BaseDaoImpl implements NewsDao<News> {

	private final RowMapper<News> rowMapper = new RowMapper<News>() {
		
		@Override
		public News mapRow(ResultSet rs, int rowNum) throws SQLException {
			News t=new News();
			t.setId(rs.getInt("id"));
			t.setType(rs.getInt("type"));
			t.setLanguage(rs.getString("language"));
			t.setTitle(rs.getString("title"));
			t.setContent(rs.getString("content"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};
	
	@Override
	public int add(News t) {
		String sql="insert into t_news (type, language, title, content, status"
				+", create_time, update_time) values(?, ?, ?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getType()
				, t.getLanguage()
				, t.getTitle()
				, t.getContent()
				, t.getStatus()
			);
	}

	@Override
	public int update(News t) {
		StringBuilder sql=new StringBuilder("update t_news set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (t.getType()!=null) {
			sql.append(", type=?");
			val.add(t.getType());
		}
		if (StringUtils.hasText(t.getTitle())) {
			sql.append(", title=?");
			val.add(t.getTitle());
		}
		if (StringUtils.hasText(t.getLanguage())) {
			sql.append(", language=?");
			val.add(t.getLanguage());
		}
		if (StringUtils.hasText(t.getContent())) {
			sql.append(", content=?");
			val.add(t.getContent());
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
		String sql="delete from t_news where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_news where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}

	@Override
	public News queryById(int id) {
		String sql = "select * from t_news where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<News> queryByExemple(News t, DataGridModel dgm) {
		JsonPage<News> jsonPage = new JsonPage<News>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_news where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_news where 1=1"
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
	public List<News> queryByExemple(News t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_news where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<News> queryAll() {
		String sql = "select * from t_news";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes, News t) {
		StringBuilder sql = new StringBuilder();
		if (t.getType()!=null) {
			sql.append(" and type=?");
			args.add(t.getType());
			argTypes.add(Types.INTEGER);
		}
		if (StringUtils.hasText(t.getLanguage())) {
			sql.append(" and language=?");
			args.add(t.getLanguage());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getTitle())) {
			sql.append(" and title like CONCAT('%',?,'%')");
			args.add(t.getTitle());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getContent())) {
			sql.append(" and content like CONCAT('%',?,'%')");
			args.add(t.getContent());
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
	public List<News> queryNews(int type, String language, int limit) {
		String sql="select * from t_news where type=? and language=? and status=? order by update_time desc limit ?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{
					type, language, Constants.STATUS_VALID, limit
				}, rowMapper);
	}

	@Override
	public List<News> queryNews(String language, int limit) {
		String sql="select * from t_news where language=? and status=? order by update_time desc limit ?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{
					language, Constants.STATUS_VALID, limit
				}, rowMapper);
	}

	@Override
	public News queryPrevNews(String language, int type, int currentId) {
		String sql="select * from t_news where language=? and status=? and type=? and id<? order by id desc limit 1";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		List<News> list=jdbcTemplate.query(sql, new Object[]{
				language, Constants.STATUS_VALID, type, currentId
		}, rowMapper);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public News queryNextNews(String language, int type, int currentId) {
		String sql="select * from t_news where language=? and status=? and type=? and id>? order by id asc limit 1";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		List<News> list=jdbcTemplate.query(sql, new Object[]{
				language, Constants.STATUS_VALID, type, currentId
		}, rowMapper);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
}
