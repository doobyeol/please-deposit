<script setup lang="ts">
import { ref, reactive } from "vue";
import { UserGroupStatus, type Group } from "@/models/group/Group";

const emit = defineEmits<{
  (e: "clickItem", group: Group): void;
  (e: "acceptInvitation", groupId: number): void;
  (e: "rejectInvitation", groupId: number): void;
}>();

const props = defineProps<{
  group: Group;
}>();

const clickItem = () => {
  emit("clickItem", props.group);
};

const acceptInvitation = () => {
  emit("acceptInvitation", props.group.groupId!!);
};

const rejectInvitation = () => {
  emit("rejectInvitation", props.group.groupId!!);
};
</script>

<template>
  <v-card width="300" class="mt-5 rounded-xl" variant="flat">
    <v-card-item @click="clickItem">
      <v-card-title class="group-title">{{
        props.group.groupName
      }}</v-card-title>
    </v-card-item>
    <v-card-text @click="clickItem">
      <div>모임장: {{ props.group.groupOwner }}</div>
      <div>인원: {{ props.group.memberCount }} 명</div>
      <div>만든날: {{ props.group.createdAt }}</div>
    </v-card-text>
    <v-card-actions v-if="props.group.status === UserGroupStatus.WAITING">
      <v-btn
        variant="text"
        class="flex-grow-1"
        color="green-darken-1"
        prepend-icon="mdi-check-circle-outline"
        @click="acceptInvitation"
      >
        수락
      </v-btn>
      <v-btn
        variant="text"
        class="flex-grow-1"
        color="red-darken-2"
        prepend-icon="mdi-close-circle-outline"
        @click="rejectInvitation"
      >
        거절
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<style lang="scss" scoped>
.group-title {
  font-weight: bold;
}
</style>
