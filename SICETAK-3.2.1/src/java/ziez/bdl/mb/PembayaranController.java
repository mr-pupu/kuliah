package ziez.bdl.mb;

import ziez.bdl.entity.Pembayaran;
import ziez.bdl.mb.util.JsfUtil;
import ziez.bdl.mb.util.PaginationHelper;
import ziez.bdl.dao.PembayaranFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "pembayaranController")
@SessionScoped
public class PembayaranController implements Serializable {

    private Pembayaran current;
    private DataModel items = null;
    @EJB
    private ziez.bdl.dao.PembayaranFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PembayaranController() {
    }

    public Pembayaran getSelected() {
        if (current == null) {
            current = new Pembayaran();
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(Pembayaran current) {
        this.current = current;
        
       
    }
    
    public Long totalBayar(long idPesanan){
        String call ="SELECT f_total(?1) FROM DUAL";
        BigDecimal total = (BigDecimal)ejbFacade.getEntityManager().createNativeQuery(call)
                .setParameter(1, 1)
                .getSingleResult();
        return total.longValue();
    }

    private PembayaranFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Pembayaran) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Pembayaran();
        selectedItemIndex = -1;
        return "Create";
    }

    public void create() {
        try {
            current.setTotalPembayaran(totalBayar(current.getIdPesanan().getIdPesanan()));
            PesananController pc = new PesananController();
            pc.recreateModel();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PembayaranCreated"));
            recreateModel();
            reset();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceError"));
        }
    }

    public String prepareEdit() {
        current = (Pembayaran) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public void update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PembayaranUpdated"));
            recreateModel();
            reset();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));

        }
    }
    
    public void delete() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PembayaranDeleted"));
            recreateModel();
            reset();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    
    
    

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PembayaranDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Pembayaran.class)
    public static class PembayaranControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PembayaranController controller = (PembayaranController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pembayaranController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pembayaran) {
                Pembayaran o = (Pembayaran) object;
                return getStringKey(o.getIdPembayaran());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PembayaranController.class.getName());
            }
        }
    }

    public void reset() {
        current = new Pembayaran();
    }
}
