package com.yhhl.image.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.StringUtil;
import com.yhhl.interceptor.Token;

@Controller
@RequestMapping("/imageOpt")
public class ImagesController {

	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView index() {
		return new ModelAndView("images/rich-text-edit");
	}

	/**
	 * 进入到初始化新增、修改页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAddUser")
	@Token(save = true)
	public ModelAndView initAdd(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {

		}
		return new ModelAndView("user/addUser");
	}

	@RequestMapping(value = "/upload-pic")
	public synchronized void uploadPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Expires", "0");
		response.addHeader("Cache-Control", "no-store,must-revalidate");
		response.addHeader("Cache-Control", "post-check=0,pre-check=0");
		response.addHeader("Pragma", "no-cache");
		JSONObject json = new JSONObject();
		try {
			String extName = ""; // 保存文件拓展名
			String newFileName = ""; // 保存新的文件名
			String nowTimeStr = ""; // 保存当前时间
			SimpleDateFormat sDateFormat;
			Random r = new Random();
			// 转型为MultipartHttpRequest
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile file = multipartRequest.getFile("fileUpload");
			// 获得文件名：
			String realFileName = URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
			// 获取上传根路径

			// 获取路径
			// String basePath = System.getProperty("file.separator") +
			// "userfiles" + System.getProperty("file.separator")
			// + "file" + System.getProperty("file.separator");
			String basePath = "/userfiles/file/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
			// String ctxPath =
			// request.getSession().getServletContext().getRealPath(basePath);
			// 图片放在工程下
			String ctxPath = request.getSession().getServletContext().getRealPath(basePath);
			// 生成随机文件名：当前年月日时分秒+五位随机数（为了防止文件同名而进行的处理
			// 获取拓展名
			int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
			sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
			nowTimeStr = sDateFormat.format(new Date()); // 当前时间
			extName = realFileName.substring(realFileName.lastIndexOf("."));

			newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
			ctxPath = ctxPath + "/";// + System.getProperty("file.separator");
			String filePath = ctxPath + newFileName;

			// 创建文件
			File dirPath = new File(ctxPath);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			File uploadFile = new File(filePath);
			FileCopyUtils.copy(file.getBytes(), uploadFile);
			json.put("filePath", basePath + newFileName);
			json.put("fileName", realFileName);
			json.put("flag", "T");
		} catch (Exception e) {
			// json.put("flag", "E");
			json.put("flag", "E");
			json.put("msg", e.getMessage());
			e.printStackTrace();
		}
		response.getWriter().write(json.toString());
	}

	private String getUploadPath() {
		String path = "/home/imagesp/uploaddir/";
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			File f = new File("d:\\");
			if (f.exists()) {
				return f.getName();
			} else {
				f = new File("e:\\");
				if(f.exists()){
					return f.getName();
				}else{
					f = new File("c:\\");
					if(f.exists()){
						return f.getName();
					}else{
						return null;
					}
				}
			}
		}

		return path;
	}

	public static String jsonCallback(JSONObject jobj, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String jsonCallback = request.getParameter("jsonCallback");
		response.setContentType("text/plain; charset=UTF-8");
		response.addHeader("Expires", "0");
		response.addHeader("Cache-Control", "no-store,must-revalidate");
		response.addHeader("Cache-Control", "post-check=0,pre-check=0");
		response.addHeader("Pragma", "no-cache");
		response.getWriter().write(jsonCallback + "(" + jobj.toString() + ")");
		return null;
	}
}
