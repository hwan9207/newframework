package com.kh.spring.notice.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Notice {
	
	private int notieNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private String createDate;
	
}
