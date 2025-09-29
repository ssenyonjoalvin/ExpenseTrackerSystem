
package org.pahappa.systems.kpiTracker.views.department;

import lombok.Getter;
import lombok.Setter;
import org.pahappa.systems.kpiTracker.models.department.Department;
import org.pahappa.systems.kpiTracker.security.HyperLinks;
import org.primefaces.PrimeFaces;
import org.sers.webutils.client.views.presenters.ViewPath;
import org.sers.webutils.model.exception.OperationFailedException;
import org.sers.webutils.model.exception.ValidationFailedException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage; // IMPORT THIS
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext; // IMPORT THIS
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "departmentListView")
@ViewScoped
@Getter
@Setter
@ViewPath(path = HyperLinks.DEPARTMENT_LIST_VIEW)
public class DepartmentListView implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;

    private List<Department> departments;
    private Department selectedDepartment;

    private String searchTerm;
    private final int maximumresults = 10;
    private final String fileName = "departments";

    @PostConstruct
    public void init() {
        reloadFilterReset();
    }

    public void reloadFilterReset() {
        this.departments = departmentService.getAllInstances();
    }

    public void addDepartment() {
        this.selectedDepartment = new Department();
    }

    public void editDepartment(Department department) {
        this.selectedDepartment = department;
    }

    public void saveDepartment() {
        try {
            departmentService.saveInstance(this.selectedDepartment);
            reloadFilterReset(); // Refresh the table

            // Standard JSF success message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Department saved successfully."));

            PrimeFaces.current().executeScript("PF('departmentDialog').hide()");
        } catch (ValidationFailedException | OperationFailedException e) {
            // Standard JSF error message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Operation Failed: " + e.getMessage()));
        }
    }

    public void deleteDepartment(Department department) {
        try {
            departmentService.deleteInstance(department);
            reloadFilterReset(); // Refresh the table

            // Standard JSF success message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Department deleted successfully."));

        } catch (OperationFailedException e) {
            // Standard JSF error message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Operation Failed: " + e.getMessage()));
        }
    }
}
