package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Address;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	CategoryService categorySrvice;
	
	@Autowired
	PostService postService;
	
	@Autowired
	FileuploadService fileuploadService;
	
	@Address
	@RequestMapping({
		"",
		"/{categoryno}",
		"/{categoryno}/{postno}",})
	public String blog(
			@PathVariable("id") String id,
			@PathVariable("categoryno") Optional<String> categoryno,
			@PathVariable("postno") Optional<String> postno,
			Model model) {
		
		String categoryNo = categoryno.orElse("");
		String postNo = postno.orElse("");
		
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryList = categorySrvice.getCategoryList(id);
		List<PostVo> postList = postService.getPostList(id, categoryNo);
		PostVo postVo = postService.getPost(id, categoryNo, postNo);
		
		model.addAttribute("blog", blogVo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		
		return "/blog/main";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic(
			@PathVariable("id") String id,
			Model model) {
		
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blog", blogVo);
		return "/blog/admin-basic";
	}
	
	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasic(
			@PathVariable("id") String id, 
			BlogVo vo, 
			MultipartFile logoFile) {
		
		String profile = fileuploadService.restore(logoFile);
		if(profile != null) {
			vo.setProfile(profile);
		}
		
		blogService.updateBlog(vo);
		
		return "redirect:/" + vo.getId() + "/admin/basic";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(
			@PathVariable("id") String id,
			Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryList = categorySrvice.getCategoryList(id);
		model.addAttribute("blog", blogVo);
		model.addAttribute("categoryList", categoryList);
		
		return "/blog/admin-category";
	}
	
	@Auth
	@RequestMapping("/admin/category/add")
	public String adminAddCategory(
			@PathVariable("id") String id,
			CategoryVo categoryVo) {
		categorySrvice.insertCategory(categoryVo);
		return "redirect:/" + categoryVo.getId() + "/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/delete/{categoryno}")
	public String adminDeleteCategory(
			@PathVariable("id") String id,
			@PathVariable("categoryno") String categoryNo) {
		
		CategoryVo categoryVo = categorySrvice.getCategory(categoryNo);
		categorySrvice.deleteCategory(categoryVo);
		
		
		return "redirect:/" + categoryVo.getId() + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String adminWritePost(
			@PathVariable("id") String id,
			Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CategoryVo> categoryList = categorySrvice.getCategoryList(id);
		model.addAttribute("blog", blogVo);
		model.addAttribute("categoryList", categoryList);
		return "/blog/admin-write";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWritePost(
			@PathVariable("id") String id,
			String title,
			String category,
			String contents) {
		Map<String, Object> map = Map.of("id", id, "title", title, "category", category, "contents", contents);
		postService.writePost(map);
		return "redirect:/" + id + "/admin/basic";
	}

}
