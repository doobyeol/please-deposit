import type { Group, UserGroupStatus } from "@/models/group/Group";
import { defineStore } from "pinia";
import { getGroupList, updateUserGroupStatus } from "@/api/group/groupApi";

export interface GroupState {
  groupList?: Array<Group>;
  isLoading: Boolean;
}

export const useGroupStore = defineStore("groupStore", {
  state: (): GroupState => {
    return {
      groupList: [],
      isLoading: false,
    };
  },
  getters: {},
  actions: {
    async getGroupList() {
      this.isLoading = true;
      const groupList = await getGroupList();
      this.groupList = groupList;
      this.isLoading = false;
    },
    async updateUserGroupStatus(groupId: number, status: UserGroupStatus) {
      this.isLoading = true;
      try {
        await updateUserGroupStatus(groupId, status);
        const groupList = await getGroupList();
        this.groupList = groupList;
      } catch (error) {
        console.error(error);
      } finally {
        this.isLoading = false;
      }
    },
  },
});
