package cn.com.carit.portal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.common.utils.StringUtil;
import cn.com.carit.portal.bean.MediaGallery;
import cn.com.carit.portal.dao.MediaGalleryDao;
@Repository
public class MediaGalleryDaoImpl extends BaseDaoImpl
	implements MediaGalleryDao<MediaGallery>{
	
	private final Logger log=Logger.getLogger(getClass());
	
	private final RowMapper<MediaGallery> rowMapper=new RowMapper<MediaGallery>() {
		
		@Override
		public MediaGallery mapRow(ResultSet rs, int rowNum) throws SQLException {
			MediaGallery t=new MediaGallery();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setUrl(rs.getString("url"));
			t.setType(rs.getInt("type"));
			t.setRemark(rs.getString("remark"));
			t.setStatus(rs.getInt("status"));
			t.setUpdateTime(rs.getTimestamp("update_time"));
			t.setCreateTime(rs.getTimestamp("create_time"));
			return t;
		}
	};

	@Override
	public int add(MediaGallery t) {
		String sql="insert into t_media_gallery (url, name, type, remark"
				+ ", status, create_time, update_time)"
				+ " values(?, ?, ?, ?, ?, now(), now())";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql
				, t.getUrl()
				, t.getName()
				, t.getType()
				, t.getRemark()
				, t.getStatus()
			);
	}

	@Override
	public int update(MediaGallery t) {
		StringBuilder sql=new StringBuilder("update t_media_gallery set update_time=now()");
		List<Object> val = new ArrayList<Object>();
		if (StringUtils.hasText(t.getUrl())) {
			sql.append(", url=?");
			val.add(t.getUrl());
		}
		if (StringUtils.hasText(t.getName())) {
			sql.append(", name=?");
			val.add(t.getName());
		}
		if (t.getType()!=null) {
			sql.append(", type=?");
			val.add(t.getType());
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
		String sql="delete from t_media_gallery where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int batchDelete(String ids) {
		String sql="delete from t_media_gallery where id in("+ids+")";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.update(sql);
	}
	
	@Override
	public MediaGallery queryById(int id) {
		String sql="select * from t_media_gallery where id=?";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql, id, rowMapper);
	}

	@Override
	public JsonPage<MediaGallery> queryByExemple(MediaGallery t,
			DataGridModel dgm) {
		JsonPage<MediaGallery> jsonPage = new JsonPage<MediaGallery>(dgm.getPage(), dgm.getRows());
		StringBuilder sql = new StringBuilder(
				"select * from t_media_gallery where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		String whereSql = buildWhere(args, argTypes, t);
		sql.append(whereSql);
		String countSql = "select count(1) from t_media_gallery where 1=1"
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
	public List<MediaGallery> queryByExemple(MediaGallery t) {
		StringBuilder sql = new StringBuilder(
				"select * from t_media_gallery where 1=1");
		List<Object> args = new ArrayList<Object>();
		List<Integer> argTypes = new ArrayList<Integer>();
		sql.append(buildWhere(args, argTypes, t));
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return query(sql.toString(), args, argTypes, rowMapper);
	}

	@Override
	public List<MediaGallery> queryAll() {
		String sql="select * from t_media_gallery";
		if (log.isDebugEnabled()) {
			log.debug(String.format("\n%1$s\n", sql));
		}
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public String buildWhere(List<Object> args, List<Integer> argTypes,
			MediaGallery t) {
		StringBuilder sql = new StringBuilder();
		if (StringUtils.hasText(t.getUrl())) {
			sql.append(" and url like CONCAT('%',?,'%')");
			args.add(t.getUrl());
			argTypes.add(Types.VARCHAR);
		}
		if (StringUtils.hasText(t.getName())) {
			sql.append(" and name like CONCAT('%',?,'%')");
			args.add(t.getName());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getType()!=null) {
			sql.append(" and type=?");
			args.add(t.getType());
			argTypes.add(Types.INTEGER);
		}
		if (StringUtils.hasText(t.getRemark())) {
			sql.append(" and remark like CONCAT('%',?,'%')");
			args.add(t.getRemark());
			argTypes.add(Types.VARCHAR);
		}
		if (t.getStatus()!=null) {
			sql.append(", status=?");
			args.add(t.getStatus());
			argTypes.add(Types.INTEGER);
		}
		return sql.toString();
	}

}
