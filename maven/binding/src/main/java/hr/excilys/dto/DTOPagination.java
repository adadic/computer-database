package hr.excilys.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class DTOPagination {

	private String lines;
	private String page;
	private String search;
	private String order;
	private String direction;
	private String msg = "";

	public DTOPagination() {
	}
	
	public DTOPagination(String lines, String page, String search, String order, String direction, String msg) {

		this.lines = lines;
		this.page = page;
		this.search = search;
		this.order = order;
		this.direction = direction;
		this.msg = msg;
	}

	public String getLines() {

		return lines;
	}

	public void setLines(String lines) {

		this.lines = lines;
	}

	public String getPage() {

		return page;
	}

	public void setPage(String page) {

		this.page = page;
	}

	public String getSearch() {

		return search;
	}

	public void setSearch(String search) {

		this.search = search;
	}

	public String getOrder() {

		return order;
	}

	public void setOrder(String order) {

		this.order = order;
	}

	public String getMsg() {

		return msg;
	}

	public void setMsg(String msg) {

		this.msg = msg;
	}

	public void setDirection(String direction) {

		this.direction = direction;
	}

	public String getDirection() {

		return direction;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
