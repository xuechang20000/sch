package com.wb.contain.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import com.wb.contain.listener.model.APPAA10;
import com.wb.utils.web.JsonUtils;

public class ContainServiceImpl implements ContainService {
	@Override
	public String exportExcel(ServletOutputStream outputStream, ServletContext context, String header, String jsonData)
			throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();

		// 获取系统路径

		// 表头
		/*
		 * sheet.addMergedRegion(new
		 * Region(0,(short)0,0,(short)(titles.size()-1))); HSSFRow noe =
		 * sheet.createRow(0); HSSFCell eon = noe.createCell((short)0);
		 * eon.setCellValue(new HSSFRichTextString(titlename)); HSSFCellStyle
		 * styletitle = wb.createCellStyle();
		 * styletitle.setLeftBorderColor(HSSFColor.BLACK.index);
		 * styletitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		 * styletitle.setRightBorderColor(HSSFColor.BLACK.index);
		 * styletitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		 * styletitle.setBottomBorderColor(HSSFColor.BLACK.index);
		 * styletitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		 * styletitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		 * styletitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); HSSFFont font =
		 * wb.createFont(); font.setFontHeightInPoints((short)24);
		 * styletitle.setFont(font); eon.setCellStyle(styletitle);
		 */

		HSSFDataFormat format = wb.createDataFormat();

		// 标题栏样式
		HSSFCellStyle styleTitle = wb.createCellStyle();
		styleTitle.setLeftBorderColor(HSSFColor.BLACK.index);
		styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleTitle.setRightBorderColor(HSSFColor.BLACK.index);
		styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleTitle.setBottomBorderColor(HSSFColor.BLACK.index);
		styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleTitle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleTitle.setDataFormat(format.getFormat("@"));
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		styleTitle.setFont(font);

		List<Map<String, Object>> titles = JsonUtils.parseJSON2List(header);
		// 标题栏
		HSSFRow rowTtile = sheet.createRow(0);
		for (int j = 0; j < titles.size(); j++) {
			HSSFCell cell = rowTtile.createCell((short) j);
			Map<String, Object> map = titles.get(j);
			String value = map.get("header").toString();
			String widthStr = map.get("width").toString();
			int width = 0;
			if (widthStr != null && widthStr.length() > 0) {
				width = Integer.parseInt(widthStr.replace("px", "")) * 35;
			} else {
				width = (value.getBytes().length * 300);
			}
			cell.setCellValue(new HSSFRichTextString(value));
			sheet.setColumnWidth((short) j, (short) width);
			cell.setCellStyle(styleTitle);
		}

		// 设置样式
		HSSFCellStyle styleCell1 = wb.createCellStyle();
		styleCell1.setLeftBorderColor(HSSFColor.BLACK.index);
		styleCell1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleCell1.setRightBorderColor(HSSFColor.BLACK.index);
		styleCell1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleCell1.setBottomBorderColor(HSSFColor.BLACK.index);
		styleCell1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleCell1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleCell1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleCell1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		styleCell1.setDataFormat(format.getFormat("@"));
		HSSFCellStyle styleCell2 = wb.createCellStyle();
		styleCell2.setLeftBorderColor(HSSFColor.BLACK.index);
		styleCell2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleCell2.setRightBorderColor(HSSFColor.BLACK.index);
		styleCell2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleCell2.setBottomBorderColor(HSSFColor.BLACK.index);
		styleCell2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleCell2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleCell2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleCell2.setFillForegroundColor(HSSFColor.WHITE.index);
		styleCell2.setDataFormat(format.getFormat("@"));

		Map<String, Object> maps = JsonUtils.parseJSON2Map(jsonData);
		Object object = maps.get("data");
		if (object != null) {
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) object;
			for (int i = 0; i < dataList.size(); ++i) {
				HSSFRow row = sheet.createRow(i + 1);
				// 设置行高
				row.setHeight((short) 270);
				Map<String, Object> data = dataList.get(i);

				for (int j = 0; j < titles.size(); ++j) {
					Map<String, Object> map = titles.get(j);
					String field = map.get("field").toString();
					if (field != null) {
						Object objectValue = data.get(field);
						if (objectValue == null)
							continue;
						String value = objectValue.toString();

						// 是否需要字典转义
						String code = map.get("code").toString();
						if (code != null && code.equals("1")) {
							List<APPAA10> aa10s = (List<APPAA10>) context.getAttribute(field.toUpperCase());
							;
							if (aa10s != null) {
								for (int m = 0; m < aa10s.size(); m++) {
									APPAA10 aa10 = aa10s.get(m);
									if (aa10.getAaa102().equals(value)) {
										value = aa10.getAaa103();
										break;
									}
								}
							}
						}

						HSSFCell cell = row.createCell((short) j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						if (i % 2 == 0) {
							cell.setCellStyle(styleCell1);
						} else {
							cell.setCellStyle(styleCell2);
						}
						cell.setCellValue(new HSSFRichTextString(value));
					}
				}
			}
		}

		wb.write(outputStream);
		/*
		 * String filePath = path+"/dksal.xls"; File file =new File(filePath);
		 * file.mkdirs(); FileOutputStream fos = new FileOutputStream(file);
		 * wb.write(fos); fos.close();
		 */
		return "";
	}

}
