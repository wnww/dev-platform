package com.yhhl.image.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yhhl.common.ImageUtil;
import com.yhhl.common.ResultBean;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/imageOpt")
public class ImagesController {
	
	@Autowired
	private ImageUtil imageUtil;

	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView index() {
		return new ModelAndView("images/rich-text-edit");
	}

	@RequestMapping(value = "/upload-pic")
	public synchronized void uploadPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Expires", "0");
		response.addHeader("Cache-Control", "no-store,must-revalidate");
		response.addHeader("Cache-Control", "post-check=0,pre-check=0");
		response.addHeader("Pragma", "no-cache");
		ResultBean<List<Map<String,String>>> rb = new ResultBean<List<Map<String,String>>>();
		try {
			String extName = ""; // 保存文件拓展名
			String newFileName = ""; // 保存新的文件名
			String nowTimeStr = ""; // 保存当前时间
			SimpleDateFormat sDateFormat;
			Random r = new Random();
			// 转型为MultipartHttpRequest
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			List<MultipartFile> files = multipartRequest.getFiles("file");
			String basePath = "/userfiles/imgFiles/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
			// 图片放在工程下
			String ctxPath = request.getSession().getServletContext().getRealPath(basePath);
			ctxPath = ctxPath + "/";
			// 获得文件名：
			String realFileName = null;
			String filePath = null;
			
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			
			for(MultipartFile file : files){
				Map<String,String> map = new HashMap<String,String>();
				realFileName = URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
				
				// 生成随机文件名：当前年月日时分秒+五位随机数（为了防止文件同名而进行的处理
				// 获取拓展名
				int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
				sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
				nowTimeStr = sDateFormat.format(new Date()); // 当前时间
				extName = realFileName.substring(realFileName.lastIndexOf("."));
				newFileName = "b_"+nowTimeStr + rannum + extName; // 文件重命名后的名字
				String returnFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
				filePath = ctxPath + newFileName;
	
				// 创建文件
				File dirPath = new File(ctxPath);
				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}
				File uploadFile = new File(filePath);
				FileCopyUtils.copy(file.getBytes(), uploadFile);
				imageUtil.thumbnailImage(uploadFile.getAbsolutePath(), 800, 800, "", false);
				File upf = new File(filePath);
				if(upf.exists()){
					upf.delete();
				}
				map.put("filePath", basePath + returnFileName);
				map.put("fileName", realFileName);
				map.put("flag", "T");
				result.add(map);
			}
			rb.setFlag(ResultBean.SUCCESS);
			rb.setMsg("上传成功！");
			rb.setData(result);
		} catch (Exception e) {
			rb.setFlag(ResultBean.FAIL);
			rb.setMsg(e.getMessage());
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("resultBean", rb);
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
