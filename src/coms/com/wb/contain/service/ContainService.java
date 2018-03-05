package com.wb.contain.service;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

public interface ContainService {
	public String exportExcel(ServletOutputStream outputStream, ServletContext context, String header, String jsonData) throws IOException;
}
