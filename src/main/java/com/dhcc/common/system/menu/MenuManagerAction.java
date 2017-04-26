package com.dhcc.common.system.menu;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.dhcc.modal.system.Tsmenu;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

/**
 * 鏂板啓鐨勮彍鍗曠鐞哸ction
 * @author GYR
 *
 */
public class MenuManagerAction extends ActionSupport{

	/**
	 * 搴忓垪鍙�
	 */
	private static final long serialVersionUID = 1L;
	private String tid;//浼犲叆杩涙潵鐨勯《绾ц彍鍗曠殑id
	private String menuCStr = "";//鑿滃崟瀛楃涓�
	private List<MenuAuModel> list = new ArrayList<MenuAuModel>();
	private String roleid;//褰撳墠瑙掕壊鐨刬d 锛岀敤浜庢煡璇㈣瑙掕壊鎵�鎷ユ湁鐨勮彍鍗�
	
	
	public String MenuQueryList(){
		MenuDao mdn = new MenuDao();
		String userid = (String)ActionContext.getContext().getSession().get("userid");//寰楀埌鐢ㄦ埛id
		List list = mdn.menuQueryListForUser(userid);
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//鎶妉ist杞崲鎴恓son
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		mdn= null;
		return SUCCESS;
	}
	/**
	 * 鐢ㄦ埛鎵�鎷ユ湁鐨勯《绾ц彍鍗曟煡璇�
	 */
	public String topMenuQueryList(){
		MenuDao mdn = new MenuDao();
		String userid = (String)ActionContext.getContext().getSession().get("userid");//寰楀埌鐢ㄦ埛id
		List list = mdn.topMenuQueryList(userid);
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//鎶妉ist杞崲鎴恓son
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		mdn= null;
		return SUCCESS;
	}
	
	/**
	 * 鎵�鏈夌殑椤剁骇鑿滃崟鏌ヨ
	 */
	public String topMenuQueryListForAll(){
		MenuDao mdn = new MenuDao();
		List list = mdn.topMenuQueryListForAll();
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//鎶妉ist杞崲鎴恓son
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		mdn= null;
		return SUCCESS;
	}
	
	/**
	 * 鐢ㄦ埛鎵�鎷ユ湁鐨勪簩绾с�佷笁绾ц彍鍗曟煡璇�
	 */
	public String menuQueryList(){
		this.menuList(tid);
		PrintWriter pw = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(menuCStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		return SUCCESS;
	}
	
	/**
	 * 鐢ㄦ埛鎵�鎷ユ湁鐨勪簩绾с�佷笁绾ц彍鍗曟煡璇�
	 */
	private void menuList(String pid){
		MenuDao mdn = new MenuDao();
		String userid = (String)ActionContext.getContext().getSession().get("userid");//寰楀埌鐢ㄦ埛id
		List<Tsmenu> list = mdn.menuQueryList(userid,pid);
		menuStrSC(list,pid);
		mdn= null;
	}
	
	/**
	 * 鐢熸垚鍓嶅彴鎵�闇�瑕佺殑鑿滃崟瀛楃涓�
	 */
	private String menuStrSC(List<Tsmenu> list, String ppID) {
		String menuStr = "";
		for (int i = 0; i < list.size(); i++) {
			String id = ((Tsmenu) list.get(i)).getId();
			String text = ((Tsmenu) list.get(i)).getTitle();
			String url = ((Tsmenu) list.get(i)).getUrl();
			String pID = ((Tsmenu) list.get(i)).getPid();
			String img = ((Tsmenu) list.get(i)).getImage();
			if (ppID.equals(tid)) {
					menuCStr = menuCStr + "<div title='" + text + "' id='" + id
							+ "'><div style=\" height:7px;\"></div>";
					this.menuList(id);
					menuCStr = menuCStr + "</div>";
			} else {
				if (pID.equals(ppID)) {
					menuCStr = menuCStr
								+ "<a class=\"l-link\" href=\"javascript:f_addTab('"
								+ id + "','" + text + "','" + url + "')\"><image style='margin-top:4px;float:left' src='"+img+"'/><span>" + text
								+ "</span></a> ";
				}
			}

		}
		return menuStr;
	}

	/**
	 * 瑙掕壊鏉冮檺绠＄悊椤甸潰鐨勮彍鍗曠鐞�
	 */
	public String menuListForAu(){
		MenuDao mdn = new MenuDao();
		List<MenuAuModel> lis = mdn.menuQueryForAu();
		List<MenuAuModel> lisMenuAllForUser = this.menuQueryByTopIdAu(tid, lis, new ArrayList<String>(),new ArrayList<MenuAuModel>());
		List<String> listMenuForUser = mdn.getAllMenuByRole(roleid);
		list = this.listAddData(lisMenuAllForUser,listMenuForUser);
		mdn = null;
		return SUCCESS;
	}
	
	/**
	 * 閫掑綊鏌ヨ鑿滃崟涓嬫墍鏈夌殑瀛愯彍鍗�
	 * @param pid 瑕佹煡璇㈣彍鍗曠殑鐖惰彍鍗昳d
	 * @param list 褰撳墠鎵�鏈夌殑鑿滃崟闆嗗悎 
	 * @param listid 淇濆瓨宸叉湁鐨勮彍鍗曠殑id闆嗗悎锛岄槻姝㈤噸澶嶆暟鎹�
	 * @param setlist  淇濆瓨鑿滃崟鏁版嵁闆嗗悎
	 */
	private List<MenuAuModel> menuQueryByTopIdAu(String pid,List<MenuAuModel> list,List<String> listid,List<MenuAuModel> setlist){
		Set<String> setIdS = new HashSet<String>();
		for(MenuAuModel m:list){
			String id = m.getId();
			String ppid = m.getPid();
			if(id.equals(pid) || ppid.equals(pid)){
				if(!listid.contains(id)){
					setlist.add(m);
					setIdS.add(id);
					listid.add(id);
				}
			}
		}
		if(setIdS.size()>0){
			for(String menuId : setIdS){
				if(!menuId.equals(pid)){
					menuQueryByTopIdAu(menuId,list,listid,setlist);
				}
			}
		}
		return setlist;
	}
	/**
	 * 缁檒ist濉厖鏁版嵁
	 * 鍒ゆ柇璇ヨ鑹叉槸鍚︽嫢鏈夎鏉冮檺
	 * lis 褰撳墠鑿滃崟闆嗗悎锛宭ists 鐢ㄦ埛鎵�鎷ユ湁鐨勮彍鍗昳d闆嗗悎
	 * @param lis
	 */
	private List<MenuAuModel> listAddData(List<MenuAuModel> lis,List<String> lists){
		MenuAuModel mm = null;
		List<MenuAuModel> listTemp = new ArrayList<MenuAuModel>();
		if(lis != null){
			for(int i=0;i<lis.size();i++){
				String id = ((MenuAuModel) lis.get(i)).getId();
				String text = ((MenuAuModel) lis.get(i)).getTitle();
				String ordernum = ((MenuAuModel) lis.get(i)).getOrdernum()+"";
				String pID = ((MenuAuModel) lis.get(i)).getPid();
				String btnStr = ((MenuAuModel) lis.get(i)).getBtnStr();
				mm = new MenuAuModel();
				mm.setId(id);
				mm.setTitle(text);
				mm.setOrdernum(ordernum);
				mm.setPid(pID);
				mm.setBtnStr(btnStr);
				if(lists.contains(id)){
					mm.setFlag("1");
				}else{
					mm.setFlag("0");
				}
				listTemp.add(mm);
			}
		}
		return listTemp;
	}
	
	/**
	 * 鏌ヨ瀛愯彍鍗曞垪琛�
	 */
	public String treeMenuQuery(){
		MenuDao dao = new MenuDao();
		String userid = (String)ActionContext.getContext().getSession().get("userid");//寰楀埌鐢ㄦ埛id
		List<Tsmenu> listMenu1 = dao.menuQueryListForUser(userid);
		List<Tsmenu> modelList = this.menuQueryByTopId(tid, listMenu1,new ArrayList<String>(),new ArrayList<Tsmenu>());
		JSONArray json=JSONArray.fromObject(modelList);
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter pw=ServletActionContext.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 閫掑綊鏌ヨ鑿滃崟涓嬫墍鏈夌殑瀛愯彍鍗�
	 * @param pid 瑕佹煡璇㈣彍鍗曠殑鐖惰彍鍗昳d
	 * @param list 褰撳墠鎵�鏈夌殑鑿滃崟闆嗗悎 
	 * @param listid 淇濆瓨宸叉湁鐨勮彍鍗曠殑id闆嗗悎锛岄槻姝㈤噸澶嶆暟鎹�
	 * @param setlist  淇濆瓨鑿滃崟鏁版嵁闆嗗悎
	 */
	private List<Tsmenu> menuQueryByTopId(String pid,List<Tsmenu> list,List<String> listid,List<Tsmenu> setlist){
			Set<String> setIdS = new HashSet<String>();
			for(Tsmenu m:list){
				String id = m.getId();
				String ppid = m.getPid();
				if(id.equals(pid) || ppid.equals(pid)){
					if(!listid.contains(id)){
						
						if("寰呭姙宸ュ崟".equals(m.getTitle())){
							//FlowManagerDAO flowdao = new FlowManagerDAO();
//							String count = flowdao.selectDBCount();
//							m.setTitle(m.getTitle().concat(count));
						}
						setlist.add(m);
						setIdS.add(id);
						listid.add(id);
					}
				}
			}
		if(setIdS.size()>0){
			for(String menuId : setIdS){
				if(!menuId.equals(pid)){
					menuQueryByTopId(menuId,list,listid,setlist);
				}
			}
		}
		return setlist;
	}
	
	/**
	 * 鐢ㄦ埛鎵�鎷ユ湁鐨勯《绾ц彍鍗曟煡璇�
	 */
	public String topMenuQueryTree(){
		MenuDao mdn = new MenuDao();
		List list = mdn.menuQueryForTree("");
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//鎶妉ist杞崲鎴恓son
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		mdn= null;
		return SUCCESS;
	}
	
	/**
	 * 鏌ヨ鑿滃崟鐨勫浘鐗�
	 */
	public String menuIconQuery(){
		List<String> list = this.readListIcon();
		PrintWriter pw = null;
		try {
			JSONArray json = JSONArray.fromObject(list);//鎶妉ist杞崲鎴恓son
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			pw = ServletActionContext.getResponse().getWriter();
			pw.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
		return SUCCESS;
	}
	
	/**
	 * 鑾峰彇鐗瑰畾鐩綍涓嬬殑鍥剧墖璺緞锛屾斁鍏ist涓�
	 */
	private  List<String> readListIcon(){
		String path = ServletActionContext.getServletContext().getRealPath("images/Icon");
		List<String> listIcon = new ArrayList<String>();
		File file = new File(path);
		if(file.exists()){
			if(file.isDirectory()){
				File[] listFile = file.listFiles();
				for(File temp:listFile){
					String tempPath = "images/Icon/" + temp.getName();
					listIcon.add(tempPath);
				}
			}
		}
		return listIcon;
	}
	
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getMenuCStr() {
		return menuCStr;
	}

	public void setMenuCStr(String menuCStr) {
		this.menuCStr = menuCStr;
	}

	public List getList() {
		return list;
	}
	public void setList(List<MenuAuModel> list) {
		this.list = list;
	}
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
}
