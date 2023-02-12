import type { Group } from "@/models/group/Group";
import { defineStore } from "pinia";
import { getGroupList } from "@/api/group/groupApi";

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
      this.isLoading = false;
      const groupList = await getGroupList();
      this.groupList = groupList;
      this.isLoading = true;
    },
  },
});
