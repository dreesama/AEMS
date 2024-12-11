package com.company.aemss.security;

import com.company.aemss.entity.*;
import io.jmix.flowui.entity.filter.AbstractSingleFilterCondition;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "Teacher", code = TeacherRole.CODE)
public interface TeacherRole {
    String CODE = "teacher";

    @MenuPolicy(menuIds = {"Event.list", "Department.list", "Student.list", "QrCode.list", "Attendance.list", "DashboardView"})
    @ViewPolicy(viewIds = {"Event.list", "Department.list", "Student.list", "QrCode.list", "Attendance.list", "Event.detail", "Department.detail", "Attendance.detail", "QrCode.detail", "Student.detail", "DashboardView"})
    void screens();

    @EntityAttributePolicy(entityClass = Attendance.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Attendance.class, actions = EntityPolicyAction.ALL)
    void attendance();

    @EntityAttributePolicy(entityClass = QrCode.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = QrCode.class, actions = EntityPolicyAction.ALL)
    void qrCode();

    @EntityAttributePolicy(entityClass = Event.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Event.class, actions = EntityPolicyAction.ALL)
    void event();

    @EntityAttributePolicy(entityClass = Department.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Department.class, actions = EntityPolicyAction.ALL)
    void department();

    @EntityAttributePolicy(entityClass = Student.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Student.class, actions = EntityPolicyAction.ALL)
    void student();

    @EntityAttributePolicy(entityClass = AbstractSingleFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = AbstractSingleFilterCondition.class, actions = EntityPolicyAction.ALL)
    void abstractSingleFilterCondition();
}