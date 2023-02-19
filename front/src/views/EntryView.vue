<script setup lang="ts">
import GroupItem from "@/components/entry/GroupItem.vue";
import router from "@/router";
import { ref, onMounted } from "vue";
import { useGroupStore } from "@/stores/group/groupStore";
import { storeToRefs } from "pinia";
import { UserGroupStatus, type Group } from "@/models/group/Group";

const groupStore = useGroupStore();
const { groupList, isLoading } = storeToRefs(groupStore);
const { getGroupList, updateUserGroupStatus } = groupStore;

getGroupList();

const showFormPopup = ref(false);

const moveGroupPage = (group: Group) => {
  console.log("group: ", group);
  if (UserGroupStatus.WAITING === group.status) {
    // TODO: need to change to common popup or alert
    alert("모임 참가 여부를 결정해주세요!");
  } else {
    router.push("expense");
  }
};

const acceptInvitation = (groupId: number) => {
  console.log("acceptInvitation", groupId);
  updateUserGroupStatus(groupId, UserGroupStatus.ACCEPTED);
};

const rejectInvitation = (groupId: number) => {
  console.log("rejectInvitation", groupId);
  updateUserGroupStatus(groupId, UserGroupStatus.REJECTED);
};

const createGroup = () => {
  console.log("createGroup");
  showFormPopup.value = false;
};
</script>

<template>
  <div class="d-flex justify-center align-center flex-column">
    <div class="entry-title">입장할 모임을 선택하세요!</div>
    <div class="pt-5 pb-5">
      <GroupItem
        v-for="group in groupList"
        :key="group.groupId"
        :group="group"
        @clickItem="moveGroupPage"
        @acceptInvitation="acceptInvitation"
        @rejectInvitation="rejectInvitation"
      ></GroupItem>
    </div>
    <div>
      <v-btn icon="mdi-plus" flat @click="showFormPopup = true"></v-btn>
    </div>
    <div class="pt-2 text-disabled">새 모임을 만들어보세요</div>
    <v-dialog v-model="showFormPopup">
      <v-card>
        <v-card-text>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod tempor incididunt ut labore et dolore magna aliqua.
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" block @click="createGroup">생성</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style lang="scss" scoped>
.entry-title {
  padding-top: 50px;
  font-size: 25px;
  font-weight: bold;
  color: #065c00;
}
</style>
