package org.pahappa.systems.kpiTracker.core.dao.impl;

import org.pahappa.systems.kpiTracker.core.dao.DepartmentDao;
import org.pahappa.systems.kpiTracker.models.department.Department;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the DepartmentDao interface.
 * The @Repository annotation tells Spring that this is a DAO bean.
 */
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDAOImpl<Department> implements DepartmentDao {

    // The class body is empty because the actual implementation for all the basic
    // save, find, search, and delete methods is provided by the BaseDAOImpl<Department> class.

}