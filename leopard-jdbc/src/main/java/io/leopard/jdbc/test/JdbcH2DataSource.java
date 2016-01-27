package io.leopard.jdbc.test;

import org.springframework.util.StringUtils;

import io.leopard.autounit.unitdb.H2Util;
import io.leopard.jdbc.JdbcDataSource;

public class JdbcH2DataSource extends JdbcDataSource {

	private String jdbcId;

	public String getJdbcId() {
		return jdbcId;
	}

	public void setJdbcId(String jdbcId) {
		this.jdbcId = jdbcId;
	}

	private String url;

	public void setUrl(String url) {
		// AssertUtil.assertNotEmpty(url, "参数url不能为空.");
		if (StringUtils.isEmpty(url)) {
			throw new IllegalArgumentException("参数url不能为空.");
		}
		logger.info("jdbcUrl:" + url);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void init() {
		String type = DefaultH2DataSource.getCategory();
		super.dataSource = H2Util.createDataSource(type, this.jdbcId, DefaultH2DataSource.isAutoCommit());
		H2SqlUtil.populate(type, dataSource);

		RsyncData.registerH2DataSource(this);
	}

	public void destroy() {

	}

}
