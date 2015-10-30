/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerothai;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author chaia_000
 */
@javax.ws.rs.ApplicationPath("mxerve")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.aerothai.ItemResource.class);
        resources.add(com.aerothai.ItemsResource.class);
        resources.add(com.aerothai.NewCrossOriginResourceSharingFilter.class);
        resources.add(com.aerothai.database.accessory.AccessoryResource.class);
        resources.add(com.aerothai.database.accessory.AccessorysResource.class);
        resources.add(com.aerothai.database.dept.DeptResource.class);
        resources.add(com.aerothai.database.dept.DeptsResource.class);
        resources.add(com.aerothai.database.device.DeviceResource.class);
        resources.add(com.aerothai.database.device.DevicesResource.class);
        resources.add(com.aerothai.database.devicetype.DeviceTypeResource.class);
        resources.add(com.aerothai.database.devicetype.DeviceTypesResource.class);
        resources.add(com.aerothai.database.job.JobResource.class);
        resources.add(com.aerothai.database.job.JobsResource.class);
        resources.add(com.aerothai.database.jobevaluate.JobevaluateResource.class);
        resources.add(com.aerothai.database.jobevaluate.JobevaluatesResource.class);
        resources.add(com.aerothai.database.model.ModelResource.class);
        resources.add(com.aerothai.database.model.ModelsResource.class);
        resources.add(com.aerothai.database.position.PositionResource.class);
        resources.add(com.aerothai.database.position.PositionsResource.class);
        resources.add(com.aerothai.database.radiosignal.RadiosignalResource.class);
        resources.add(com.aerothai.database.radiosignal.RadiosignalsResource.class);
        resources.add(com.aerothai.database.role.RoleResource.class);
        resources.add(com.aerothai.database.role.RolesResource.class);
        resources.add(com.aerothai.database.servicemethod.ServicemethodResource.class);
        resources.add(com.aerothai.database.servicemethod.ServicemethodsResource.class);
        resources.add(com.aerothai.database.servicetype.ServicetypeResource.class);
        resources.add(com.aerothai.database.servicetype.ServicetypeSResource.class);
        resources.add(com.aerothai.database.sparepart.SparepartResource.class);
        resources.add(com.aerothai.database.sparepart.SparepartsResource.class);
        resources.add(com.aerothai.database.status.StatusResource.class);
        resources.add(com.aerothai.database.status.StatussResource.class);
        resources.add(com.aerothai.database.unit.UnitResource.class);
        resources.add(com.aerothai.database.unit.UnitsResource.class);
        resources.add(com.aerothai.database.user.UserResource.class);
        resources.add(com.aerothai.database.user.UsersResource.class);
    }
    
}
