package com.company.aemss.security;

import com.company.aemss.entity.*;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.data.entity.ReferenceToEntity;
import io.jmix.datatoolsflowui.view.entityinfo.model.InfoValue;
import io.jmix.flowui.entity.filter.*;
import io.jmix.flowuidata.entity.FilterConfiguration;
import io.jmix.flowuidata.entity.UserSettingsItem;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securitydata.entity.*;
import io.jmix.securityflowui.model.*;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;
import io.jmix.securityflowui.view.resetpassword.model.UserPasswordValue;
import io.jmix.securityflowui.view.resourcepolicy.AttributeResourceModel;

@ResourceRole(name = "Full Access", code = FullAccessRole.CODE)
public interface FullAccessRole {

    String CODE = "system-full-access";

    @EntityAttributePolicy(entityName = "*", attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @ViewPolicy(viewIds = "*")
    @MenuPolicy(menuIds = "*")
    @SpecificPolicy(resources = "*")
    void fullAccess();

    @EntityAttributePolicy(entityClass = UserPasswordValue.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = UserPasswordValue.class, actions = EntityPolicyAction.ALL)
    void userPasswordValue();

    @EntityAttributePolicy(entityClass = PropertyFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PropertyFilterCondition.class, actions = EntityPolicyAction.ALL)
    void propertyFilterCondition();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Event.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Event.class, actions = EntityPolicyAction.ALL)
    void event();

    @EntityAttributePolicy(entityClass = Student.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Student.class, actions = EntityPolicyAction.ALL)
    void student();

    @EntityAttributePolicy(entityClass = Department.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Department.class, actions = EntityPolicyAction.ALL)
    void department();

    @EntityAttributePolicy(entityClass = Attendance.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Attendance.class, actions = EntityPolicyAction.ALL)
    void attendance();

    @EntityAttributePolicy(entityClass = FilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = FilterCondition.class, actions = EntityPolicyAction.ALL)
    void filterCondition();

    @EntityAttributePolicy(entityClass = LogicalFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = LogicalFilterCondition.class, actions = EntityPolicyAction.ALL)
    void logicalFilterCondition();

    @EntityAttributePolicy(entityClass = ReferenceToEntity.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ReferenceToEntity.class, actions = EntityPolicyAction.ALL)
    void referenceToEntity();

    @EntityAttributePolicy(entityClass = ResourcePolicyEntity.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ResourcePolicyEntity.class, actions = EntityPolicyAction.ALL)
    void resourcePolicyEntity();

    @EntityAttributePolicy(entityClass = ResourcePolicyModel.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ResourcePolicyModel.class, actions = EntityPolicyAction.ALL)
    void resourcePolicyModel();

    @EntityAttributePolicy(entityClass = ResourceRoleModel.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ResourceRoleModel.class, actions = EntityPolicyAction.ALL)
    void resourceRoleModel();

    @EntityAttributePolicy(entityClass = ResourceRoleEntity.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ResourceRoleEntity.class, actions = EntityPolicyAction.ALL)
    void resourceRoleEntity();

    @EntityAttributePolicy(entityClass = RowLevelPolicyEntity.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RowLevelPolicyEntity.class, actions = EntityPolicyAction.ALL)
    void rowLevelPolicyEntity();

    @EntityAttributePolicy(entityClass = RowLevelRoleModel.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RowLevelRoleModel.class, actions = EntityPolicyAction.ALL)
    void rowLevelRoleModel();

    @EntityAttributePolicy(entityClass = RowLevelRoleEntity.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RowLevelRoleEntity.class, actions = EntityPolicyAction.ALL)
    void rowLevelRoleEntity();

    @EntityAttributePolicy(entityClass = KeyValueEntity.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = KeyValueEntity.class, actions = EntityPolicyAction.ALL)
    void keyValueEntity();

    @EntityAttributePolicy(entityClass = JpqlFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = JpqlFilterCondition.class, actions = EntityPolicyAction.ALL)
    void jpqlFilterCondition();

    @EntityAttributePolicy(entityClass = BaseRoleModel.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = BaseRoleModel.class, actions = EntityPolicyAction.ALL)
    void baseRoleModel();

    @EntityAttributePolicy(entityClass = UserSettingsItem.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = UserSettingsItem.class, actions = EntityPolicyAction.ALL)
    void userSettingsItem();

    @EntityAttributePolicy(entityClass = RowLevelPolicyModel.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RowLevelPolicyModel.class, actions = EntityPolicyAction.ALL)
    void rowLevelPolicyModel();

    @EntityAttributePolicy(entityClass = RoleAssignmentEntity.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RoleAssignmentEntity.class, actions = EntityPolicyAction.ALL)
    void roleAssignmentEntity();

    @EntityAttributePolicy(entityClass = AttributeResourceModel.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = AttributeResourceModel.class, actions = EntityPolicyAction.ALL)
    void attributeResourceModel();

    @EntityAttributePolicy(entityClass = AbstractSingleFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = AbstractSingleFilterCondition.class, actions = EntityPolicyAction.ALL)
    void abstractSingleFilterCondition();

    @EntityAttributePolicy(entityClass = FilterValueComponent.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = FilterValueComponent.class, actions = EntityPolicyAction.ALL)
    void filterValueComponent();

    @EntityAttributePolicy(entityClass = FilterConfiguration.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = FilterConfiguration.class, actions = EntityPolicyAction.ALL)
    void filterConfiguration();

    @EntityAttributePolicy(entityClass = HeaderFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = HeaderFilterCondition.class, actions = EntityPolicyAction.ALL)
    void headerFilterCondition();

    @EntityAttributePolicy(entityClass = GroupFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = GroupFilterCondition.class, actions = EntityPolicyAction.ALL)
    void groupFilterCondition();

    @EntityAttributePolicy(entityClass = InfoValue.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = InfoValue.class, actions = EntityPolicyAction.ALL)
    void infoValue();

    @EntityAttributePolicy(entityClass = Section.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Section.class, actions = EntityPolicyAction.ALL)
    void section();
}