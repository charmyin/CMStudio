package com.charmyin.cmstudio.basic.pagination.page;

import com.charmyin.cmstudio.basic.pagination.annotation.Paging;

@Paging(field="pageVO")
public class PageBase {
	private Pagination pageVO;

	public Pagination getPageVO() {
		return pageVO;
	}
	
   	public void setPageVO(Pagination pageVO) {
		this.pageVO = pageVO;
	}

}
