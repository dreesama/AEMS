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
    @ViewPolicy(viewIds = {"Event.list", "Department.list", "Student.list", "QrCode.list", "Attendance.list", "User.list", "datatl_entityInspectorListView", "sec_ResourceRoleModel.detail", "sec_ResourceRoleModel.lookup", "sec_RowLevelRoleModel.detail", "sec_RowLevelRoleModel.lookup", "resetPasswordView", "changePasswordView", "sec_EntityAttributeResourcePolicyModel.detail", "sec_EntityResourcePolicyModel.detail", "sec_ViewResourcePolicyModel.detail", "sec_GraphQLResourcePolicyModel.detail", "sec_MenuResourcePolicyModel.detail", "sec_ViewResourcePolicyModel.create", "sec_MenuResourcePolicyModel.create", "sec_ResourcePolicyModel.detail", "sec_EntityAttributeResourcePolicyModel.create", "sec_EntityResourcePolicyModel.create", "sec_SpecificResourcePolicyModel.detail", "roleAssignmentView", "sec_RowLevelPolicyModel.detail", "sec_UserSubstitution.detail", "sec_UserSubstitution.view", "flowui_PropertyFilterCondition.detail", "flowui_JpqlFilterCondition.detail", "flowui_AddConditionView", "flowui_GroupFilterCondition.detail", "headerPropertyFilterLayout", "inputDialog", "flowui_DateIntervalDialog", "multiValueSelectDialog", "entityInfoView", "datatl_entityInspectorDetailView", "MainView", "User.detail", "LoginView", "Attendance.detail", "Department.detail", "Event.detail", "QrCode.detail", "Student.detail", "FragmentRenderer"})
    @MenuPolicy(menuIds = {"Event.list", "Department.list", "Student.list", "QrCode.list", "Attendance.list", "User.list", "datatl_entityInspectorListView"})
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

    @EntityAttributePolicy(entityClass = QrCode.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = QrCode.class, actions = EntityPolicyAction.ALL)
    void qrCode();

    @EntityAttributePolicy(entityClass = FilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = FilterCondition.class, actions = EntityPolicyAction.ALL)
    void filterCondition();

    @EntityAttributePolicy(entityClass = LogicalFilterCondition.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = LogicalFilterCondition.class, actions = EntityPolicyAction.ALL)
    void logicalFilterCondition();

    @EntityPolicy(entityClass = ReferenceToEntity.class, actions = EntityPolicyAction.CREATE)
    void referenceToEntity();

    @EntityPolicy(entityClass = ResourcePolicyEntity.class, actions = EntityPolicyAction.CREATE)
    void resourcePolicyEntity();

    @EntityPolicy(entityClass = ResourcePolicyModel.class, actions = EntityPolicyAction.CREATE)
    void resourcePolicyModel();

    @EntityPolicy(entityClass = ResourceRoleModel.class, actions = EntityPolicyAction.CREATE)
    void resourceRoleModel();

    @EntityPolicy(entityClass = ResourceRoleEntity.class, actions = EntityPolicyAction.CREATE)
    void resourceRoleEntity();

    @EntityPolicy(entityClass = RoleAssignmentEntity.class, actions = EntityPolicyAction.CREATE)
    void roleAssignmentEntity();

    @EntityPolicy(entityClass = RowLevelPolicyEntity.class, actions = EntityPolicyAction.CREATE)
    void rowLevelPolicyEntity();

    @EntityPolicy(entityClass = RowLevelPolicyModel.class, actions = EntityPolicyAction.CREATE)
    void rowLevelPolicyModel();

    @EntityPolicy(entityClass = RowLevelRoleModel.class, actions = EntityPolicyAction.CREATE)
    void rowLevelRoleModel();

    @EntityPolicy(entityClass = RowLevelRoleEntity.class, actions = EntityPolicyAction.CREATE)
    void rowLevelRoleEntity();

    @EntityPolicy(entityClass = KeyValueEntity.class, actions = EntityPolicyAction.CREATE)
    void keyValueEntity();

    @EntityPolicy(entityClass = JpqlFilterCondition.class, actions = EntityPolicyAction.CREATE)
    void jpqlFilterCondition();

    @EntityPolicy(entityClass = InfoValue.class, actions = EntityPolicyAction.CREATE)
    void infoValue();

    @EntityPolicy(entityClass = GroupFilterCondition.class, actions = EntityPolicyAction.CREATE)
    void groupFilterCondition();

    @EntityPolicy(entityClass = HeaderFilterCondition.class, actions = EntityPolicyAction.CREATE)
    void headerFilterCondition();

    @EntityPolicy(entityClass = FilterConfiguration.class, actions = EntityPolicyAction.CREATE)
    void filterConfiguration();

    @EntityPolicy(entityClass = FilterValueComponent.class, actions = EntityPolicyAction.CREATE)
    void filterValueComponent();

    @EntityPolicy(entityClass = AttributeResourceModel.class, actions = EntityPolicyAction.CREATE)
    void attributeResourceModel();

    @EntityPolicy(entityClass = BaseRoleModel.class, actions = EntityPolicyAction.CREATE)
    void baseRoleModel();

    @EntityPolicy(entityClass = AbstractSingleFilterCondition.class, actions = EntityPolicyAction.CREATE)
    void abstractSingleFilterCondition();

    @EntityPolicy(entityClass = UserSubstitutionEntity.class, actions = EntityPolicyAction.CREATE)
    void userSubstitutionEntity();

    @EntityPolicy(entityClass = UserSettingsItem.class, actions = EntityPolicyAction.CREATE)
    void userSettingsItem();
}