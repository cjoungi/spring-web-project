package org.zerock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

  private Long bno;
  private String title;
  private String content;
  private String writer;
  private Date regdate;
  private Date updateDate;

  private int replyCnt;
  // 게시물 등록시 첨부파일 한번에 등록
  private List<BoardAttachVO> attachList;
}
