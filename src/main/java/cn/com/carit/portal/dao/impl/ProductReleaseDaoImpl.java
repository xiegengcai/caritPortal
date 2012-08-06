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
import cn.com.carit.portal.bean.ProductRelease;
import cn.com.carit.portal.dao.ProductReleaseDao;

@Repository
public class ProductReleaseDaoImpl extends BaseDaoImpl implements
		ProductReleaseDao<ProductRelease> {

	private final RowMapper<ProductRelease> rowMapper = new RowMapper<ProductRelease>() {
		
		@Override
		public ProductRelease mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductRelease t=new ProductRelease();
			t.setId(rs.getInt("id"));
			t.setCatalogId(rs.getInt("catalog_id"));
			t.setLanguage(rs.getString("language"));
			t.setTitle(rs.getString("title"));
			t.setContent(rs.getString("content"));
			t.setTop(rs.getBoolean("top"));
			t.setPicture(rs.getString("picture"));
			t.setThumb(rs.getString("thumb"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};
	@Override
	public int add(ProductRelease t) {
		String sql="insert into t_product_release (catalog_id, language, title"
				+", content, top, picture, thumb, status, update_time, create_time)"
				+" values (?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getCatalogId()
				, t.getLanguage()
				, t.getTitle()
				, t.getContent()
				, t.getTop()
				, t.getPicture()
				, t.getThumb()
				, t.getStatus()
			);
	}

	@Override
	public int update(ProductRelease t) {
		StringBuilder sql=new StringBuilder("update t_product_release set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (t.getCatalogId()!=null) {
			sql.append(", catalog_id=?");
			val.add(t.getCatalogId());
		}
		if (StringUtils.hasText(t.getLanguage())) {
			sql.append(", language=?");
			val.add(t.getLanguage());
		}
		if (StringUtils.hasText(t.getTitle())) {
			sql.append(", title=?");
			val.add(t.getTitle());
		}
		if (StringUtils.hasText(t.getContent())) {
			sql.append(", content=?");
			val.add(t.getContent());
		}
		if (t.getTop()!=null) {
			sql.append(", top=?");
			val.add(t.getTop());
		}
		if (StringUtils.hasText(t.getPicture())) {
			sql.append(", picture=?");
			val.add(t.getPicture());
		}
		if (StringUtils.hasText(t.getThumb())) {
			sql.append(", thumb=?");
			val.add(t.getThumb());
		}
		if (t.getStatus()!=null) {
			sql.append(", status=?");
			val.add(t.getStatus());
		}
		sql.append("where id=?");
		val.add(t.getId());
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql.toString(), val.toArray());
	}

	@Override
	public int delete(int id) {
		String sql="delete from t_product_release where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_product_release where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}

	@Override
	public ProductRelease queryById(int id) {
		String sql="select * from t_product_release where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<ProductRelease> queryByExemple(ProductRelease t,
			DataGridModel dgm) {
		JsonPage<ProductRelease> jsonPage = new JsonPage<ProductRelease>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_product_release where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_product_release where 1=1"
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
	public List<ProductRelease> queryByExemple(ProductRelease t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_product_release where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<ProductRelease> queryAll() {
		String sql="select * from t_product_release";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes,
			ProductRelease t) {
		StringBuilder sql=new StringBuilder();
		if (t.getCatalogId()!=null) {
			sql.append(" and catalog_id=?");
			args.add(t.getCatalogId());
			argTypes.add(Types.INTEGER);
		}
		if (StringUtils.hasText(t.getLanguage())) {
			sql.append(" and language like CONCAT('%',?,'%')");
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
		if (t.getTop()!=null) {
			sql.append(" and top=?");
			args.add(t.getTop());
			argTypes.add(Types.INTEGER);
		}
		if (StringUtils.hasText(t.getPicture())) {
			sql.append(" and picture like CONCAT('%',?,'%')");
			args.add(t.getPicture());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getThumb())) {
			sql.append(" and thumb like CONCAT('%',?,'%')");
			args.add(t.getThumb());
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
	public List<ProductRelease> queryProductRelease(String language, int limit) {
		String sql="select * from t_product_release where language=? and status=? order by update_time desc limit ?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{language, Constants.STATUS_VALID, limit}
			, rowMapper);
	}

	@Override
	public List<ProductRelease> queryTopProductRelease(String language,
			int limit) {
		String sql="select * from t_product_release where language=? and top=? and status=? order by update_time desc limit ?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, new Object[]{language, 1, Constants.STATUS_VALID, limit}
			, rowMapper);
	}

}
