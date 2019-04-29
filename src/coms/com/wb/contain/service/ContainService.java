package com.wb.contain.service;

import com.wb.jdbcutils.Page;
import com.wb.school.common.bo.TFile;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

public interface ContainService {
	public String exportExcel(OutputStream outputStream, ServletContext context, String header, String jsonData) throws IOException;
	public TFile saveFile(TFile tFile);
	public TFile saveFile(String filePath,String busiType);
	public  void querySavedFile(Page page, TFile tFile);
	public void updateToComplete(Long fileid);
}
