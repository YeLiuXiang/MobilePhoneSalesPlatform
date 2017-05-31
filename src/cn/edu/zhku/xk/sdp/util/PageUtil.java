package cn.edu.zhku.xk.sdp.util;

import java.util.List;

public class PageUtil<T> {
	
	int totalRowsAmount;// 总行�?
	int pageSize = 4;// 每页行数
	int totalPages;// 总页�?
	int currentPage = 1;	// 当前页码
	int nextPage;// 下一�?
	int previousPage;// 上一�?

	int pageStartRow;// 当前页开始行
	int pageEndRow;// 当前页要显示的行�?
	List<T> list; /*= new ArrayList<T>();*/

	public PageUtil(int totalRows, int currentPage) {
		setPageController(totalRows, currentPage);
	}

	public PageUtil(int totalRows, int currentPage, int pageSize) {
		this.pageSize = pageSize;
		this.setPageController(totalRows, currentPage);
	}

	public void setPageController(int totalRows, int currentPage) {

		setTotalRowsAmount(totalRows);
		setCurrentPage(currentPage);
	}

	/**
	 * 设置总行数和总页�?
	 * 
	 * @param i
	 *            总行�?
	 */
	private void setTotalRowsAmount(int rows) {

		if (rows < 0) {
			totalRowsAmount = 0;
		} else {
			totalRowsAmount = rows;
		}

		if (totalRowsAmount % pageSize == 0) {
			totalPages = totalRowsAmount / pageSize;
		} else {
			totalPages = totalRowsAmount / pageSize + 1;
		}
	}

	/**
	 * 设置当前页数
	 * 
	 * @param i
	 */
	public void setCurrentPage(int curPage) {

		// 计算当前页码
		if (curPage <= 0) {
			currentPage = 1;
		} else if (curPage > totalPages) {
			currentPage = totalPages;
		} else {
			currentPage = curPage;
		}

	

		// 计算上一页和下一�?
		nextPage = currentPage + 1;
		previousPage = currentPage - 1;

		// 计算初始行和结束�?
		if (currentPage != totalPages) {
			pageStartRow = (currentPage - 1) * pageSize;
			// 记录索引
			pageEndRow = currentPage * pageSize;
		} else {
			pageStartRow = (currentPage - 1) * pageSize;
			// 记录索引
			pageEndRow = totalRowsAmount;
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	

	public int getNextPage() {
		return nextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRowsAmount() {
		return totalRowsAmount;
	}

	public int getPageStartRow() {
		if (this.pageStartRow <= 0) {
			return 0;
		}
		return pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}


}
