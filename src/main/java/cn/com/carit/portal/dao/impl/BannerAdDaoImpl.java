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
import cn.com.carit.portal.bean.BannerAd;
import cn.com.carit.portal.dao.BannerAdDao;
@Repository
public class BannerAdDaoImpl extends BaseDaoImpl
	implements BannerAdDao<BannerAd>{
	
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	
	private final RowMapper<BannerAd> rowMapper=new RowMapper<BannerAd>() {
		
		@Override
		public BannerAd mapRow(ResultSet rs, int rowNum) throws SQLException {
			BannerAd t=new BannerAd();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setImage(rs.getString("image"));
			t.setThumb(rs.getString("thumb"));
			t.setHref(rs.getString("href"));
			t.setRemark(rs.getString("remark"));
			t.setStatus(rs.getInt("status"));
			t.setDisplayIndex(rs.getInt("display_index"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};

	@Override
	public int add(BannerAd t) {
		String sql="insert into t_banner_ad (name, image, thumb"
				+ ", href, remark, status, display_index, create_time, update_time)"
				+ " values(?, ?, ?, ?, ?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getName()
				, t.getImage()
				, t.getThumb()
				, t.getHref()
				, t.getRemark()
				, t.getStatus()
				, t.getDisplayIndex()
			);
	}

	@Override
	public int update(BannerAd t) {
		StringBuilder sql=new StringBuilder("update t_banner_ad set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (StringUtils.hasText(t.getName())) {
			sql.append(", name=?");
			val.add(t.getName());
		}
		if (StringUtils.hasText(t.getImage())) {
			sql.append(", image=?");
			val.add(t.getImage());
		}
		if (StringUtils.hasText(t.getThumb())) {
			sql.append(", thumb=?");
			val.add(t.getThumb());
		}
		if (StringUtils.hasText(t.getHref())) {
			sql.append(", href=?");
			val.add(t.getHref());
		}
		if (StringUtils.hasText(t.getRemark())) {
			sql.append(", remark=?");
			val.add(t.getRemark());
		}
		if (t.getStatus()!=null) {
			sql.append(", status=?");
			val.add(t.getStatus());
		}
		if (t.getDisplayIndex()!=null) {
			sql.append(", display_index=?");
			val.add(t.getDisplayIndex());
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
		String sql="delete from t_banner_ad where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_banner_ad where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}
	
	@Override
	public BannerAd queryById(int id) {
		String sql="select * from t_banner_ad where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<BannerAd> queryByExemple(BannerAd t,
			DataGridModel dgm) {
		JsonPage<BannerAd> jsonPage = new JsonPage<BannerAd>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_banner_ad where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_banner_ad where 1=1"
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
			sql.append(" order by display_index asc");
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
	public List<BannerAd> queryByExemple(BannerAd t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_banner_ad where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<BannerAd> queryAll() {
		String sql="select * from t_banner_ad";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes,
			BannerAd t) {
		StringBuilder sql = new StringBuilder();
		if (StringUtils.hasText(t.getName())) {
			sql.append(" and name like CONCAT('%',?,'%')");
			args.add(t.getName());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getImage())) {
			sql.append(", image like CONCAT('%',?,'%')");
			args.add(t.getImage());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getThumb())) {
			sql.append(", thumb like CONCAT('%',?,'%')");
			args.add(t.getThumb());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getHref())) {
			sql.append(", href like CONCAT('%',?,'%')");
			args.add(t.getHref());
			argTypes.add(Types.VARCHAR);
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
		if (t.getDisplayIndex()!=null) {
			sql.append(" and display_index=?");
			args.add(t.getDisplayIndex());
			argTypes.add(Types.INTEGER);
		}
		return sql.toString();
	}

	@Override
	public List<BannerAd> query(int limit) {
		String sql="select * from t_banner_ad where status=? order by display_index asc limit ?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql.toString(), new Object[]{Constants.STATUS_VALID, limit}
		, new int[]{Types.INTEGER, Types.INTEGER}, rowMapper);
	}
	

}
