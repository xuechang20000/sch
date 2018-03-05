package com.wb.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wb.admin.bo.Group;
import com.wb.admin.bo.GroupType;
import com.wb.admin.bo.Organ;
import com.wb.admin.bo.User;
import com.wb.admin.vo.TreeDTO;
import com.wb.admin.vo.UserVO;
import com.wb.admin.vs.AdminVS;
import com.wb.contain.listener.model.APPAA09;
import com.wb.contain.listener.model.APPAA10;
import com.wb.jdbcutils.Page;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;

@Controller
@RequestMapping("/admin/")
public class AdminAction {
	public static Log log = LogFactory.getLog(AdminAction.class);
	@Autowired
	private AdminVS adminVS;

	public AdminVS getAdminVS() {
		return adminVS;
	}

	public void setAdminVS(AdminVS adminVS) {
		this.adminVS = adminVS;
	}

	@RequestMapping(value = "login.action")
	public String index() throws Exception {
		return "/pages/admin/login";
	}

	@RequestMapping(value = "queryTree.action")
	public void queryTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TreeDTO> dtos = this.adminVS.queryTree();
		Gson gson = new GsonBuilder().create();
		writeToCliet(response, gson.toJson(dtos));
	}

	@RequestMapping(value = "queryUsersByNodeid.action")
	@ResponseBody
	public String queryUsersByNodeid(HttpServletRequest request, HttpServletResponse response, String nodeid)
			throws Exception {
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);

		Page page = new Page();
		page.setPageno(pageIndex + 1);
		page.setPagesize(pageSize);
		this.adminVS.queryUsersByNodeid(page, Long.valueOf(nodeid));
		return JsonUtils.getJsonByPage(page);
	}

	@RequestMapping(value = "queryUsersByKeyWords.action")
	@ResponseBody
	public String queryUsersByKeyWords(HttpServletRequest request, HttpServletResponse response, String keywords)
			throws Exception {
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);

		Page page = new Page();
		page.setPageno(pageIndex + 1);
		page.setPagesize(pageSize);
		this.adminVS.queryUsersByKeyWords(page, keywords);
		return JsonUtils.getJsonByPage(page);
	}

	@RequestMapping(value = "queryOrganByNodeid.action")
	public void queryOrganByNodeid(HttpServletRequest request, HttpServletResponse response, String nodeid)
			throws Exception {
		Organ organ = this.adminVS.queryOrganByNodeid(Long.valueOf(nodeid));
		Gson gson = new GsonBuilder().create();
		writeToCliet(response, gson.toJson(organ));
	}

	@RequestMapping(value = "addTreeNode.action")
	@ResponseBody
	public String addTreeNode(String json, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Gson gson = new GsonBuilder().create();
		TreeDTO dto = gson.fromJson(json, TreeDTO.class);

		Organ organ = new Organ();
		organ.setNodename(dto.getText());
		organ.setNodetype("1");
		organ.setRemoved("0");
		organ.setNodeid(dto.getId());
		organ.setParentnodeid(dto.getPid());

		this.adminVS.addOrgan(organ);
		return "{\"id\":\"" + organ.getNodeid() + "\"}";
	}

	@RequestMapping(value = "deleteTreeNode.action")
	@ResponseBody
	public String deleteTreeNode(String json, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Gson gson = new GsonBuilder().create();
		TreeDTO dto = gson.fromJson(json, TreeDTO.class);
		this.adminVS.deleteOrgan(dto.getId());
		return "{}";
	}

	@RequestMapping(value = "updateTreeNode.action")
	@ResponseBody
	public String updateTreeNode(String json, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Gson gson = new GsonBuilder().create();
		TreeDTO dto = gson.fromJson(json, TreeDTO.class);

		Organ organ = this.adminVS.queryOrganByNodeid(dto.getId());
		organ.setParentnodeid(dto.getPid());

		this.adminVS.updateOrgan(organ);
		return "{}";
	}

	@RequestMapping(value = "updateOrgan.action")
	@ResponseBody
	public String updateOrgan(String json, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Gson gson = new GsonBuilder().create();
		Organ dto = gson.fromJson(json, Organ.class);
		this.adminVS.updateOrgan(dto);
		return "{}";
	}

	@RequestMapping(value = "queryAllGroups.action")
	@ResponseBody
	public String queryAllGroups(HttpServletRequest request, HttpServletResponse response, String groupname)
			throws Exception {
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);

		Page page = new Page();
		page.setPageno(pageIndex + 1);
		page.setPagesize(pageSize);
		this.adminVS.queryAllGroups(page, groupname);
		return JsonUtils.getJsonByPage(page);
	}

	@RequestMapping(value = "queryGroupByUserid.action")
	@ResponseBody
	public String queryGroupByUserid(HttpServletRequest request, HttpServletResponse response, String userid)
			throws Exception {
		List<Group> list = this.adminVS.queryGroupByUserid(Long.valueOf(userid));
		return JsonUtils.getJsonDataFromCollection(list);
	}

	@RequestMapping(value = "addUser.action")
	@ResponseBody
	public String addUser(String json, String groups, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserVO vo = (UserVO) JsonUtils.getBeanFromJsonData(json, UserVO.class);
		List<Group> groupList = JsonUtils.getBeanListFromJsonData(groups, Group.class);
		this.adminVS.addUser(vo, groupList);
		return "{}";
	}

	@RequestMapping(value = "updateUser.action")
	@ResponseBody
	public String updateUser(String json, String groups, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserVO vo = (UserVO) JsonUtils.getBeanFromJsonData(json, UserVO.class);
		List<Group> groupList = JsonUtils.getBeanListFromJsonData(groups, Group.class);
		this.adminVS.updateUser(vo, groupList);
		return "{}";
	}

	@RequestMapping(value = "initUserPassword.action")
	@ResponseBody
	public String initUserPassword(String userid, String groups, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.adminVS.initUserPassword(Long.valueOf(userid));
		return "{}";
	}

	@RequestMapping(value = "deleteUser.action")
	@ResponseBody
	public String deleteUser(String userid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.adminVS.deleteUser(Long.valueOf(userid));
		return "{}";
	}

	@RequestMapping(value = "queryUserById.action")
	public void queryUserById(HttpServletRequest request, HttpServletResponse response, String userid)
			throws Exception {
		User vo = this.adminVS.queryUserById(Long.valueOf(userid));
		Gson gson = new GsonBuilder().create();
		writeToCliet(response, gson.toJson(vo));
	}

	@RequestMapping(value = "queryAllGroupType.action")
	@ResponseBody
	public String queryAllGroupType(HttpServletRequest request, HttpServletResponse response, String userid)
			throws Exception {
		List<GroupType> list = this.adminVS.queryAllGroupType();
		return JsonUtils.getJsonDataFromCollection(list);
	}

	@RequestMapping(value = "saveGroup.action")
	@ResponseBody
	public String saveGroup(String groupName, String groupTypeId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.adminVS.saveGroup(groupName, groupTypeId);
		return "{}";
	}

	@RequestMapping(value = "updateGroup.action")
	@ResponseBody
	public String updateGroup(Group group, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.adminVS.updateGroup(group);
		return "{}";
	}

	@RequestMapping(value = "deleteGroup.action")
	@ResponseBody
	public String deleteGroup(String groupid, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Group group = new Group();
		group.setGroupid(Long.valueOf(groupid));
		this.adminVS.deleteGroup(group);
		return "{}";
	}

	@RequestMapping(value = "queryGroupByGroupid.action")
	@ResponseBody
	public String queryAllGroupType(String groupid, HttpServletRequest request, HttpServletResponse response,
			String userid) throws Exception {
		Group group = this.adminVS.queryGroupByGroupid(Long.valueOf(groupid));
		return JsonUtils.getJsonData(group);
	}

	@RequestMapping(value = "queryAa09list.action")
	@ResponseBody
	public String queryAa09list(HttpServletRequest request, HttpServletResponse response, String userid)
			throws Exception {
		List<APPAA09> aa09s = this.adminVS.queryAa09list();
		return JsonUtils.getJsonDataFromCollection(aa09s);
	}

	@RequestMapping(value = "queryAa10list.action")
	@ResponseBody
	public String queryAa09list(String aaa100, HttpServletRequest request, HttpServletResponse response, String userid)
			throws Exception {
		List<APPAA10> aa10s = this.adminVS.queryAa10list(aaa100);
		return JsonUtils.getJsonDataFromCollection(aa10s);
	}

	@RequestMapping(value = "addAa10.action")
	@ResponseBody
	public String addAa10(String aaa100, String aaa103, HttpServletRequest request) {
		this.adminVS.addAa10(aaa100, aaa103);
		ServletContext context = request.getSession().getServletContext();
		this.adminVS.refreashContext(context, aaa100);
		return "";
	}

	@RequestMapping(value = "deleteAa10.action")
	@ResponseBody
	public String deleteAa10(String aaa100, String aaa102, HttpServletRequest request) {
		this.adminVS.deleteAa10(aaa100, aaa102);
		ServletContext context = request.getSession().getServletContext();
		this.adminVS.refreashContext(context, aaa100);
		return "";
	}

	/**
	 * 输出字符流到客户端
	 * 
	 * @param response
	 * @param obj
	 */
	public void writeToCliet(HttpServletResponse response, String returnStr) {
		/**
		 * 返回字符流
		 */
		response.setCharacterEncoding("UTF-8"); // 设置编码格式
		response.setContentType("text/html"); // 设置数据格式
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		} // 获取写入对象

		log.info("返回参数：" + returnStr);
		out.print(returnStr); // 将json数据写入流中
		out.flush();
	}
}
