package com.yhhl.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtil {

	/**
	 * @param data
	 *            数据模型
	 * @param templatePath
	 *            模板路径 "WEB-INF/templates"
	 * @param templateFileName
	 *            模板文件名 "main.html"
	 * @param staticPageName
	 *            生成的静态文件的文件名
	 */
	public static void createStaticPage(Configuration cfg, HttpServletRequest request, String staticPageName,
			Map<String, Object> data, String templatePath, String templateFileName) {
		try {
			String basePath = "/userfiles/htmlFiles";
			// 图片放在工程下
			String staticPagePath = request.getSession().getServletContext().getRealPath(basePath);
			File file = new File(staticPagePath);
			if(!file.exists()){
				file.mkdirs();
			}
			cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);// 设置标签
			cfg.setServletContextForTemplateLoading(request.getSession().getServletContext(), templatePath);// 设置临时加载目录。
			cfg.setDefaultEncoding("UTF-8");
			cfg.setNumberFormat("#");
			Template temp = cfg.getTemplate(templateFileName);// 获取模板对象
			Writer out = new OutputStreamWriter(new FileOutputStream(staticPagePath + "/" + staticPageName + ".html"),
					"UTF-8");
			temp.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param filePath
	 *            文件存放的路径
	 * @param fileName
	 *            文件的名称，需要扩展名
	 * @author HuifengWang
	 * @return
	 */
	public static Map<String, Object> htmlFileHasExist(HttpServletRequest request, String filePath, String fileName) {
		Map<String, Object> map = new HashMap<String, Object>();
		String htmlPath = request.getSession().getServletContext().getRealPath(filePath) + "/" + fileName;
		File htmlFile = new File(htmlPath);
		if (htmlFile.exists()) {
			map.put("exist", true);
		} else {
			map.put("exist", false);
		}
		return map;
	}
}
