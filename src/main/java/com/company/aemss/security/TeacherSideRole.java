package com.company.aemss.security;

import com.company.aemss.entity.Attendance;
import com.company.aemss.entity.Event;
import com.company.aemss.entity.QrCode;
import com.company.aemss.entity.Student;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "TeacherSide", code = TeacherSideRole.CODE)
public interface TeacherSideRole {
    String CODE = "teacher-side";

    @MenuPolicy(menuIds = {"Student.list", "QrCode.list", "datatl_entityInspectorListView", "Event.list", "Attendance.list"})
    @ViewPolicy(viewIds = {"Student.list", "QrCode.list", "datatl_entityInspectorListView", "QrCode.detail", "Event.list", "Student.detail", "datatl_entityInspectorDetailView", "entityInfoView", "Event.detail", "Attendance.detail", "Attendance.list"})
    void screens();

    @EntityAttributePolicy(entityClass = QrCode.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = QrCode.class, actions = EntityPolicyAction.ALL)
    void qrCode();

    @EntityAttributePolicy(entityClass = Student.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Student.class, actions = EntityPolicyAction.ALL)
    void student();

    @EntityAttributePolicy(entityClass = Attendance.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Attendance.class, actions = EntityPolicyAction.ALL)
    void attendance();

    @SpecificPolicy(resources = {"ui.loginToUi", "datatools.showEntityInfo"})
    void specific();

    @EntityAttributePolicy(entityClass = Event.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Event.class, actions = {EntityPolicyAction.UPDATE, EntityPolicyAction.READ})
    void event();
}