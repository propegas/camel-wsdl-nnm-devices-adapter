package com.hp.ov.nms.sdk.security;

import javax.ejb.Local;

import com.hp.ov.nms.sdk.filter.Filter;

@Local
public interface NmsSecurityServiceLocal {
    /**
     * List available SecurityGroups.
     * @return an array of SecurityGroup data transfer objects..
     */
    SecurityGroupDTO [] listSecurityGroups() throws NmsSecurityException;
    /**
     * List available tenants.
     * @return an array of Tenant  data transfer objects..
     */
    TenantDTO [] listTenants() throws NmsSecurityException;
    /**
     * List available tenant Overlapping Address Maps.
     * @return an array of Tenant OAMs.
     */
    TenantOAM [] getTenantOAMs(Filter filter) throws NmsSecurityException;
    /**
     * Create a tenant.
     * @return Uuid of tenant.
     */
    String createTenant(TenantDTO tenant) throws NmsSecurityException;
    /**
     * Get the NodeSecurity  data transfer objects for the nodes identified in the nodeUUIDs array.
     * @param nodeUuids array of UUID strings of nodes to lookup.
     * @return the security data for a list of Node UUIDs.
     */
    NodeSecurityDTO[] getNodeSecurityDataByUuid(String [] nodeUuids) throws NmsSecurityException;
    /**
     * Get the NodeSecurity  data transfer objects for the nodes identified in the nodeName array.
     * @param nodeNames array of node names to lookup.
     * @return the security data for a list of Node names.
     */
    NodeSecurityDTO[] getNodeSecurityDataByName(String [] nodeNames) throws NmsSecurityException;
    /**
     * Get the UserGroupMembership records  for the user identified in the userNames array.
     * @param  userNames array of user names to lookup.
     * @return data mapping users to user groups for a list of user names.
     */
    UserGroupMembershipDTO [] getUserGroupMembership(String [] userNames) throws NmsSecurityException;
    /**
     * Get the SecurityGroupMembership records mapping user groups to security groups.
     * @param array of user group names
     * @return data mapping user groups to security groups.
     */
    SecurityGroupMembershipDTO [] getSecurityGroupMembershipByUserGroupName(
                                    String [] userGroupNames
                                  ) throws NmsSecurityException;
    /**
     * Get the SecurityGroupMembership records mapping user groups to security groups.
     * @param array of security group uuids
     * @return data mapping user groups to security groups.
     */
    SecurityGroupMembershipDTO [] getSecurityGroupMembershipBySecurityGroupUuid(
                                    String [] securityGroupUuids
                                  ) throws NmsSecurityException;
}
