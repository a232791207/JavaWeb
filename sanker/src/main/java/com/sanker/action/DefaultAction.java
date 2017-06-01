package com.sanker.action;
import javax.servlet.http.HttpServletRequest;
import com.sanker.comms.page.PageRequest;
import com.sanker.comms.page.PageResponse;
import com.sanker.service.utils.JSONHelper;



public class DefaultAction extends BaseAction {

    // ~ Static fields/initializers
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;

    // ~ Instance fields
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    


    // ~ Methods
    // --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void list() {

        try {
            PageRequest page = new PageRequest(getRequest());
            PageResponse response = doList(page);
            String json = JSONHelper.SerializeWithOutInnerClass(response);
            System.out.println(json);
            Write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public String add() {
        add(getRequest());

        return ADD;
    }

    public String save() {
        doSave();
        return LIST;
    }

    public String editByIntId() {
        edit(getIId(), getRequest());

        return EDIT;
    }
    
    public String edit(){
    	 edit(getId(), getRequest());

         return EDIT;
    }

    public String update() {
        doUpdate();
        return LIST;
    }

    public void ajaxDeleteByIntId() {
        ajaxDelete(getIId());
    }

    public String deleteByIntId() {
        delete(getIId());

        return LIST;
    }
    
    public void ajaxDelete() {
    	ajaxDelete(getId());
    }
    
    public String delete() {
    	delete(getId());
    	
    	return LIST;
    }
    
    public void contain(){
    	Write(doContain()+"");
    }


    public void add(HttpServletRequest req) {
    }

    public PageResponse doList(PageRequest page) {
        return null;
    }
    
    public void doSave() {
    }

    

    public void doUpdate() {
    }

    public void edit(Integer id, HttpServletRequest req) {
    }
    
    public void edit(String id, HttpServletRequest req) {
    }

    public void ajaxDelete(Integer id) {
    }

    public void delete(Integer id) {
    }
    
    public void ajaxDelete(String id) {
    }
    
    public void delete(String id) {
    }
    
    public boolean doContain(){
    	return false;
    }

   
}
