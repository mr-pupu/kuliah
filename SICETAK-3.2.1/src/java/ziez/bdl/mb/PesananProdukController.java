package ziez.bdl.mb;

import ziez.bdl.entity.PesananProduk;
import ziez.bdl.mb.util.JsfUtil;
import ziez.bdl.mb.util.PaginationHelper;
import ziez.bdl.dao.PesananProdukFacade;

import java.io.Serializable;
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

@ManagedBean(name = "pesananProdukController")
@SessionScoped
public class PesananProdukController implements Serializable {

    private PesananProduk current;
    private DataModel items = null;
    @EJB
    private ziez.bdl.dao.PesananProdukFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PesananProdukController() {
    }

    public PesananProduk getSelected() {
        if (current == null) {
            current = new PesananProduk();
            current.setPesananProdukPK(new ziez.bdl.entity.PesananProdukPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    public void setSelected(PesananProduk current) {
        this.current = current;
    }

    private PesananProdukFacade getFacade() {
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
        current = (PesananProduk) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PesananProduk();
        current.setPesananProdukPK(new ziez.bdl.entity.PesananProdukPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public void create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PesananProdukCreated"));
            recreateModel();
            reset();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceError"));
        }
    }

    public String prepareEdit() {
        current = (PesananProduk) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public void update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PesananProdukUpdated"));
            recreateModel();
            reset();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));

        }
    }

    public void delete() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PesananProdukDeleted"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PesananProdukDeleted"));
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

    @FacesConverter(forClass = PesananProduk.class)
    public static class PesananProdukControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PesananProdukController controller = (PesananProdukController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pesananProdukController");
            return controller.ejbFacade.find(getKey(value));
        }

        ziez.bdl.entity.PesananProdukPK getKey(String value) {
            ziez.bdl.entity.PesananProdukPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new ziez.bdl.entity.PesananProdukPK();
            key.setIdPesanan(Long.parseLong(values[0]));
            key.setIdProduk(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(ziez.bdl.entity.PesananProdukPK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getIdPesanan());
            sb.append(SEPARATOR);
            sb.append(value.getIdProduk());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PesananProduk) {
                PesananProduk o = (PesananProduk) object;
                return getStringKey(o.getPesananProdukPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PesananProdukController.class.getName());
            }
        }
    }

    public void reset() {
        current = new PesananProduk();
    }
}
